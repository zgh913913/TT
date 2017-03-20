package com.example.think.toutiao.construct;


import com.example.think.toutiao.base.BaseConstruct;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.ContentBean;
import com.example.think.toutiao.model.bean.ContentSuggestBean;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.util.ArrayList;
import java.util.Map;

import rx.Observable;

/**
 * Created by my on 2016/11/24.
 */

public interface IContentConstruct {
    interface IContentView extends BaseConstruct.IView {
        void onGetContent(ContentBean suggestWords);
        void onGetSuggest(ContentSuggestBean suggestWords);
    }

    interface IContentPresenter extends BaseConstruct.IPresenter<IContentView> {
        void getContent(Map<String, Object> map);
        void getSuggest(Map<String, Object> map);
    }

    interface IContentModel extends BaseConstruct.IModel {
        Observable<ContentBean> getContent(Map<String, Object> map);
        Observable<BaseResult<ContentSuggestBean>> getSuggest(Map<String, Object> map);
    }
}
