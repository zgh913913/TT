package com.example.think.toutiao.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.think.toutiao.util.common.ConstUtils;
import com.example.think.toutiao.util.common.L;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2017/3/8.
 */

public abstract class BaseFragment<T extends BaseActivity> extends Fragment {
    private Unbinder binder;
    public String Tag;
    public T mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Tag = this.getClass().getSimpleName();
        View view = inflater.inflate(getLayoutRes(), null);
        binder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (T) getActivity();
//        initData();
    }

    protected abstract void initView();

    @LayoutRes
    public abstract int getLayoutRes();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    public void onPageSelected() {
        initData();
    }
}
