package com.example.think.toutiao.presenter;

import com.example.think.toutiao.construct.IContentConstruct;
import com.example.think.toutiao.model.ContentModel;
import com.example.think.toutiao.model.bean.ContentBean;

import java.util.Map;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by my on 2016/11/24.
 */

public class ContentPresenter implements IContentConstruct.IContentPresenter {
    private IContentConstruct.IContentModel mainModel;
    private IContentConstruct.IContentView mainView;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void attachView(IContentConstruct.IContentView mainView) {
        this.mainView = mainView;
        mainModel = new ContentModel();
    }

    @Override
    public void detachView() {
        this.mainView = null;
        if (subscription != null) subscription.unsubscribe();
//        this.mainModel = null;
    }

    @Override
    public void getContent(Map<String, Object> map) {
        if (mainView == null)
            return;
        mainView.showLoading(0);
        Subscription subscribe = mainModel.getContent(map)
                .doOnError(throwable -> {
                    mainView.hideLoading(0);
                    mainView.showError(throwable);
                })
                .doOnCompleted(() -> {
                    mainView.hideLoading(0);
                })
                .subscribe(contentBean -> {
                    mainView.onGetContent(contentBean);
                });
        subscription.add(subscribe);
    }


    @Override
    public void getSuggest(Map<String, Object> map) {
        mainView.showLoading(1);
        Subscription subscribe = mainModel.getSuggest(map)
                .filter(contentSuggestBean -> contentSuggestBean != null)
                .doOnError(throwable -> {
                    mainView.hideLoading(0);
                    mainView.showError(throwable);
                })
                .doOnCompleted(() -> {
                    mainView.hideLoading(0);
                })
                .subscribe(contentSuggestBean -> {
                    mainView.onGetSuggest(contentSuggestBean.data);
                });
//
//        new Observer<BaseResult<ContentSuggestBean>>() {
//            @Override
//            public void onCompleted() {
//                mainView.hideLoading(0);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mainView.hideLoading(0);
//                mainView.showError(e);
//            }
//
//            @Override
//            public void onNext(BaseResult<ContentSuggestBean> contentSuggestBean) {
//                mainView.onGetSuggest(contentSuggestBean.data);
//            }
//        });
        subscription.add(subscribe);
    }
}
