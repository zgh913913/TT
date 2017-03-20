package com.example.think.toutiao.construct;


import com.example.think.toutiao.base.BaseConstruct;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by my on 2016/11/24.
 */

public interface IHomeConstruct {
    interface IHomeView extends BaseConstruct.IView {
        void onGetTitles(HomeTitleBean homeTitleBean);
    }

    interface IHomePresenter extends BaseConstruct.IPresenter<IHomeView> {
        void getTitles();
    }

    interface IHomeModel extends BaseConstruct.IModel {
        Observable<BaseResult<HomeTitleBean>> getTitles();
    }
}
