package com.example.think.toutiao.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.example.think.toutiao.util.common.BarUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Think on 2017/3/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
//abc  初始化
//abc 修改1
    //abc修改2







    private Unbinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        binder = ButterKnife.bind(this);
        BarUtils.setColor(this,getBarColor());
        init();
    }

    public BaseActivity getActivity() {
        return this;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binder != null)
            binder.unbind();
    }

    public int getBarColor() {
        return 0xFFCCCCCC;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
