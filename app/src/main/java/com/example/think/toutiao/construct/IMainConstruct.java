package com.example.think.toutiao.construct;


import com.example.think.toutiao.base.BaseConstruct;

import rx.Observable;

/**
 * Created by my on 2016/11/24.
 */

public interface IMainConstruct {
    interface IMainView extends BaseConstruct.IView {

    }

    interface IMainPresenter extends BaseConstruct.IPresenter<IMainView> {
    }

    interface IMainModel extends BaseConstruct.IModel {
    }
}
