package com.example.think.toutiao.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.think.toutiao.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Think on 2017/3/10.
 */
public class FragmentController {
    private final int containerId;
    FragmentManager mFragmentManager;
    ArrayList<BaseFragment> fragments;
    private FragmentManager fm;
    static FragmentController controller;

    public static FragmentController getInstance(FragmentActivity activity, int containerId) {
        if (controller == null) {
            controller = new FragmentController(activity, containerId);
        }
        return controller;
    }

    public FragmentController(FragmentActivity activity, int containerId) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    public static void onDestroy() {
        controller = null;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new VideoFragment());
        fragments.add(new QuanZiFragment());
        fragments.add(new MyFragment());

        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            ft.add(containerId, fragments.get(i), "" + i);
        }
        ft.commit();
    }

    public void showFragment(int index) {
        hideFragments();
        Fragment fragment = fragments.get(index);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }
}
