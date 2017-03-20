package com.example.think.toutiao.presenter;

import com.example.think.toutiao.construct.IMainConstruct;
import com.example.think.toutiao.model.MainModel;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by my on 2016/11/24.
 */

public class MainPresenter implements IMainConstruct.IMainPresenter {
    private MainModel mainModel;
    private IMainConstruct.IMainView mainView;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void attachView(IMainConstruct.IMainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModel();

    }


    @Override
    public void detachView() {
        this.mainView = null;
        if (subscription != null) subscription.unsubscribe();
    }


//    @Override
//    public void getUserInfo() {
//        mainView.showLoading(0);
//        Subscription subscribe = mainModel.getUserInfo()
//                .filter(new Func1<BaseResult<UserInfoBean>, Boolean>() {
//                    @Override
//                    public Boolean call(BaseResult<UserInfoBean> userInfo) {
//                        return userInfo != null;
//                    }
//                }).subscribe(new Observer<BaseResult<UserInfoBean>>() {
//                    @Override
//                    public void onCompleted() {
//                        mainView.hideLoading(0);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mainView.hideLoading(0);
//                        mainView.showError(e);
//                    }
//
//                    @Override
//                    public void onNext(BaseResult<UserInfoBean> userInfo) {
//                        mainView.onGetUserInfo(userInfo.data);
//                        UserInfoBean data = userInfo.data;
//                        UserInfo info = new UserInfo(data.getDeviceId(), data.getUname(), data.getUphone(),
//                                data.getCityname(), data.getUaddress(), data.getScrBenchBluetooth(), data.getAmesdialMac(), data.isAmesdialStatus());
//                        DbHelper.getInstance().getDaoSession().getUserInfoDao().insertOrReplace(info);
//                    }
//                });
//        subscription.add(subscribe);
//    }
}
