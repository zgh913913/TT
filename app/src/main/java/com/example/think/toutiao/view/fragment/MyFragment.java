package com.example.think.toutiao.view.fragment;

import android.graphics.Color;
import android.os.Build;
import android.view.WindowManager;

import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseFragment;
import com.example.think.toutiao.util.common.BarUtils;
import com.example.think.toutiao.util.common.L;
import com.example.think.toutiao.view.activity.MainActivity;

/**
 * Created by Think on 2017/3/8.
 */

public class MyFragment extends BaseFragment<MainActivity> {
    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_my;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            BarUtils.setTransparentStatusBar(mActivity);
            BarUtils.setColor(mActivity,0xFF343434);
        } else {
            BarUtils.setColor(mActivity, getResources().getColor(R.color.title_bar_color_1));
        }
    }

    @Override
    protected void initData() {
        L.e(Tag, "initData");
    }

}
