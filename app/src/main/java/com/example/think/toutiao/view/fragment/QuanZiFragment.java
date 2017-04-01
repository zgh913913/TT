package com.example.think.toutiao.view.fragment;

import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseFragment;
import com.example.think.toutiao.util.common.BarUtils;
import com.example.think.toutiao.util.common.L;
import com.example.think.toutiao.view.activity.MainActivity;

/**
 * Created by Think on 2017/3/8.
 */

public class QuanZiFragment extends BaseFragment<MainActivity> {
    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_quanzi;
    }

    @Override
    protected void initData() {
        L.e(Tag, "initData");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            BarUtils.setColor(mActivity,0xFFCCCCCC);
        }
    }
}
