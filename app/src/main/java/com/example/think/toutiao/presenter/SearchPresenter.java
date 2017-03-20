package com.example.think.toutiao.presenter;

import com.example.think.toutiao.construct.IMainConstruct;
import com.example.think.toutiao.construct.ISearchConstruct;
import com.example.think.toutiao.model.MainModel;
import com.example.think.toutiao.model.SearchModel;
import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by my on 2016/11/24.
 */

public class SearchPresenter implements ISearchConstruct.ISearchPresenter {
    private SearchModel mainModel;
    private ISearchConstruct.ISearchView mainView;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void attachView(ISearchConstruct.ISearchView mainView) {
        this.mainView = mainView;
        mainModel = new SearchModel();

    }

    @Override
    public void detachView() {
        this.mainView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    @Override
    public void getSuggestWords(String keyword) {
        mainView.showLoading(0);
        Subscription subscribe = mainModel.getSuggestWords(keyword)
                .subscribe(new Observer<BaseResult<ArrayList<SearchKeyWordBean>>>() {
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
                    public void onNext(BaseResult<ArrayList<SearchKeyWordBean>> userInfo) {
                        mainView.onGetSuggestWords(userInfo.data);
                    }
                });
        subscription.add(subscribe);
    }
}
