package com.example.think.toutiao;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.think.toutiao.db.DbHelper;
import com.example.think.toutiao.util.common.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.greendao.DbUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2017/3/8.
 */

public class AppClient extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        DbHelper.getInstance().setupDatabase(getApplicationContext());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is ded  icated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
