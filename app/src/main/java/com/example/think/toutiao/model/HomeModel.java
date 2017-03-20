package com.example.think.toutiao.model;

import com.example.think.toutiao.construct.IHomeConstruct;
import com.example.think.toutiao.construct.ISearchConstruct;
import com.example.think.toutiao.db.DbHelper;
import com.example.think.toutiao.db.TitleCache;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;
import com.example.think.toutiao.util.http.Api;
import com.example.think.toutiao.util.http.RxUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2017/3/13.
 */

public class HomeModel implements IHomeConstruct.IHomeModel {

    @Override
    public Observable<BaseResult<HomeTitleBean>> getTitles() {
        HashMap<String, Object> map = RxUtils.getCommonRequestMap();
        if (map.get("ac").equals("wifi")) {
            return RxUtils.getApi(Api.BASE_HTTP_URL).getTitles(map)
                    .doOnNext(homeTitleBeanBaseResult -> {
                        Type type = new TypeToken<BaseResult<HomeTitleBean>>() {
                        }.getType();
                        String s = new Gson().toJson(homeTitleBeanBaseResult, type);
                        TitleCache entity = new TitleCache(0, new Gson().toJson(homeTitleBeanBaseResult, type));
                        DbHelper.getInstance().getTitleCacheDao().insertOrReplace(entity);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return Observable.create(new Observable.OnSubscribe<BaseResult<HomeTitleBean>>() {
                @Override
                public void call(Subscriber<? super BaseResult<HomeTitleBean>> subscriber) {
                    TitleCache titles = DbHelper.getInstance().getTitleCacheDao()
                            .queryBuilder().limit(1).list().get(0);
                    Type type = new TypeToken<BaseResult<HomeTitleBean>>() {
                    }.getType();
                    BaseResult<HomeTitleBean> o = new Gson().fromJson(titles.data, type);
                    subscriber.onNext(o);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
