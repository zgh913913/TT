package com.example.think.toutiao.view.fragment;

import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseFragment;
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
    protected void initData() {
        L.e(Tag, "initData");
    }

}
