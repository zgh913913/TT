package com.example.think.toutiao.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.think.toutiao.base.BaseFragment;
import com.example.think.toutiao.view.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2017/3/12.
 */
public class ContentViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = ContentViewPagerAdapter.class.getSimpleName();

    private List<BaseFragment> mFragments = new ArrayList<>();

    public ContentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(BaseFragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    public void removeFragment(Fragment fragment) {
        mFragments.remove(fragment);
    }


    public void setFragments(List<BaseFragment> fragments) {
        mFragments.addAll(fragments);
    }

    public List<BaseFragment> getFragments() {
        return mFragments;
    }

    public void clear() {
//        for (Fragment fragment : mFragments) {
//            if (fragment != null && fragment.isAdded()) {
//                fragment.onDestroy();
//            }
//        }
        mFragments.clear();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}


