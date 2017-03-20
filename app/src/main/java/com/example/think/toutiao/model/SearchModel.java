package com.example.think.toutiao.model;

import com.example.think.toutiao.construct.ISearchConstruct;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;
import com.example.think.toutiao.util.http.Api;
import com.example.think.toutiao.util.http.RxUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2017/3/13.
 */

public class SearchModel implements ISearchConstruct.ISearchModel {

    @Override
    public Observable<BaseResult<ArrayList<SearchKeyWordBean>>> getSuggestWords(String keyword) {
        HashMap<String, Object> map = RxUtils.getCommonRequestMap();
        map.put("keyword", keyword);
        return RxUtils.getApi(Api.BASE_HTTP_URL).getSearchKeyWords(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
