package com.example.think.toutiao.base;

/**
 * Created by my on 2016/11/24.
 */

public interface BaseConstruct {
    interface IView {
        void showLoading(int type);

        void showError(Throwable error);

        void hideLoading(int type);
    }

    interface IPresenter<T> {
        void attachView(T view);

        void detachView();
    }

    interface IModel {

    }
}
