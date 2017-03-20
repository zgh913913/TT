package com.example.think.toutiao.construct;


import com.example.think.toutiao.base.BaseConstruct;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by my on 2016/11/24.
 */

public interface ISearchConstruct {
    interface ISearchView extends BaseConstruct.IView {
        void onGetSuggestWords(ArrayList<SearchKeyWordBean> suggestWords);
    }

    interface ISearchPresenter extends BaseConstruct.IPresenter<ISearchView> {
        void getSuggestWords(String keyword);
    }

    interface ISearchModel extends BaseConstruct.IModel {
        Observable<BaseResult<ArrayList<SearchKeyWordBean>>> getSuggestWords(String keyword);
    }
}
