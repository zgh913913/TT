package com.example.think.toutiao.model;

import com.example.think.toutiao.AppClient;
import com.example.think.toutiao.construct.IContentConstruct;
import com.example.think.toutiao.db.ContentCache;
import com.example.think.toutiao.db.ContentCacheDao;
import com.example.think.toutiao.db.DbHelper;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.ContentBean;
import com.example.think.toutiao.model.bean.ContentSuggestBean;
import com.example.think.toutiao.util.common.L;
import com.example.think.toutiao.util.http.Api;
import com.example.think.toutiao.util.http.RxUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2017/3/18.
 */

public class ContentModel implements IContentConstruct.IContentModel {
    @Override
    public Observable<ContentBean> getContent(final Map<String, Object> map) {
        Observable<ContentBean> cache = Observable.create(new Observable.OnSubscribe<ContentBean>() {
            @Override
            public void call(Subscriber<? super ContentBean> subscriber) {
                ContentCacheDao cacheDb = DbHelper.getInstance().getCacheDao();
                if (map.get("page").equals(0)) {
                    List<ContentCache> list = cacheDb.queryBuilder().list();
                    for (ContentCache content : list) {
                        content.setHasLoaded(false);
                    }
                    cacheDb.updateInTx(list);
                }
                ContentCache content;
                List<ContentCache> list = cacheDb.queryBuilder()
                        .where(ContentCacheDao.Properties.Category.eq(map.get("category"))
                                , ContentCacheDao.Properties.HasLoaded.eq(false))
                        .list();
                if (list == null || list.size() == 0) {
                    subscriber.onCompleted();
                    return;
                }
                content = list.get(0);
                if (content != null) {
                    content.setHasLoaded(true);
                    cacheDb.insertOrReplace(content);
                    Gson gson = new Gson();
                    ContentBean contentBean = gson.fromJson(content.data, ContentBean.class);
                    subscriber.onNext(contentBean);
                }
                L.e("ZHJJJJ", Thread.currentThread().getName());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        Observable<ContentBean> net = RxUtils.getApi(Api.BASE_HTTP_URL)
                .getContent(map)
                .doOnNext(contentBean -> {
                    ContentCacheDao cacheDb = DbHelper.getInstance().getCacheDao();
                    Gson gson = new Gson();
                    String s = gson.toJson(contentBean, ContentBean.class);
                    ContentCache contentCache = new ContentCache();
                    contentCache.setHasLoaded(false);
                    contentCache.setData(s);
                    contentCache.setCategory(map.get("category").toString());
                    cacheDb.insert(contentCache);
                    L.e("ZHJ", Thread.currentThread().getName());
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        String ac = (String) map.get("ac");
        if (ac.equals("wifi") && !map.get("page").equals(0)) {
            return net;
        } else {
            return Observable.concat(cache, net).first();
        }
    }

    @Override
    public Observable<BaseResult<ContentSuggestBean>> getSuggest(Map<String, Object> map) {
        return RxUtils.getApi(Api.BASE_HTTP_URL)
                .getContentPageSuggest(map)
                .filter(contentSuggestBean
                        -> contentSuggestBean != null
                        && contentSuggestBean.data != null)
                .compose(AppClient.applySchedulers());
    }
}
