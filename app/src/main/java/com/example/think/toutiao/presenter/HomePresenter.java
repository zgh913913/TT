package com.example.think.toutiao.presenter;

import com.example.think.toutiao.construct.IHomeConstruct;
import com.example.think.toutiao.construct.ISearchConstruct;
import com.example.think.toutiao.model.HomeModel;
import com.example.think.toutiao.model.SearchModel;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by my on 2016/11/24.
 */

public class HomePresenter implements IHomeConstruct.IHomePresenter {
    private IHomeConstruct.IHomeModel mainModel;
    private IHomeConstruct.IHomeView mainView;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void attachView(IHomeConstruct.IHomeView mainView) {
        this.mainView = mainView;
        mainModel = new HomeModel();

    }

    @Override
    public void detachView() {
        this.mainView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    @Override
    public void getTitles() {
        mainView.showLoading(0);
        Subscription subscribe = mainModel.getTitles()
                .subscribe(new Observer<BaseResult<HomeTitleBean>>() {
                    @Override
                    public void onCompleted() {
                        mainView.hideLoading(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.hideLoading(0);
                        mainView.showError(e);
                    }

                    @Override
                    public void onNext(BaseResult<HomeTitleBean> homeTitleBeanBaseResult) {
                        mainView.onGetTitles(homeTitleBeanBaseResult.data);
                    }
                });
        subscription.add(subscribe);
    }
}
