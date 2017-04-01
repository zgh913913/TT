package com.example.think.toutiao.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseFragment;
import com.example.think.toutiao.construct.IHomeConstruct;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.presenter.HomePresenter;
import com.example.think.toutiao.ui.ColorTrackView;
import com.example.think.toutiao.util.common.BarUtils;
import com.example.think.toutiao.util.common.SizeUtils;
import com.example.think.toutiao.view.activity.MainActivity;
import com.example.think.toutiao.view.activity.SearchActivity;
import com.example.think.toutiao.view.adapter.ContentViewPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Think on 2017/3/8.
 */

public class HomeFragment extends BaseFragment<MainActivity> implements IHomeConstruct.IHomeView {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.hsv)
    HorizontalScrollView hsv;
    @BindView(R.id.indicatorContainer)
    LinearLayout indicatorContainer;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    private ArrayList<BaseFragment> mFragments;
    private ContentViewPagerAdapter viewPagerAdapter;
    private IHomeConstruct.IHomePresenter homePresenter;

    @Override
    protected void initView() {
        homePresenter = new HomePresenter();
        homePresenter.attachView(this);
//        BarUtils.setColor(mActivity,0x00000000);
//        final List<String> titles = new ArrayList<>();
//        titles.add("推荐");
//        titles.add("军事");
//        titles.add("国际");
//        titles.add("社会");
//        titles.add("问答");
//        titles.add("娱乐");
//        titles.add("推荐");
//        titles.add("军事");
//        titles.add("国际");
//        titles.add("社会");
//        titles.add("问答");
//        titles.add("娱乐");
//        for (int i = 0; i < titles.size(); i++) {
//            final ColorTrackView ctv = (ColorTrackView) View.inflate(getActivity(), R.layout.item, null);
//            ctv.setText(titles.getCacheDao(i));
//            final int finalI = i;
//            ctv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    setCurrentItem(finalI, false);
//                    for (int i1 = 0; i1 < indicatorContainer.getChildCount(); i1++) {
//                        if (i1 != finalI)
//                            ((ColorTrackView) indicatorContainer.getChildAt(i1).findViewById(R.id.ctv)).setProgress(0);
//                        else
//                            ((ColorTrackView) indicatorContainer.getChildAt(i1).findViewById(R.id.ctv)).setProgress(1);
//
//                    }
//                }
//            });
//            indicatorContainer.addView(ctv);
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ctv.getLayoutParams();
//            if (i == 0) {
//                ctv.setProgress(1);
//                layoutParams.leftMargin = SizeUtils.dp2px(5);
//                ctv.setLayoutParams(layoutParams);
//            }
//            if (i == titles.size() - 1) {
//                layoutParams.rightMargin = SizeUtils.dp2px(47);
//                ctv.setLayoutParams(layoutParams);
//            }
//        }
        mFragments = new ArrayList<>();
//        ContentFragment e = new ContentFragment();
//        mFragments.add(e);
        viewPagerAdapter = new ContentViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.setFragments(mFragments);
        vp.setAdapter(viewPagerAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    ColorTrackView left = (ColorTrackView) indicatorContainer.getChildAt(position).findViewById(R.id.ctv);
                    ColorTrackView right = (ColorTrackView) indicatorContainer.getChildAt(position + 1).findViewById(R.id.ctv);
                    left.setDirection(1);
                    right.setDirection(0);
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
                    ColorTrackView ctv = (ColorTrackView) indicatorContainer.getChildAt(i);
                    if (i != position) {
                        ctv.setProgress(0);
                    } else {
                        ctv.setProgress(1);
                    }
                }
                View child = indicatorContainer.getChildAt(position);
                ((ColorTrackView) indicatorContainer.getChildAt(position)).setProgress(1);
                int scrollViewWidth = hsv.getWidth() - SizeUtils.dp2px(47);
                int scrollX = hsv.getScrollX();
                if ((scrollViewWidth + scrollX) < child.getRight()) {//需要向右移动
                    hsv.scrollBy(child.getRight() - (scrollViewWidth + scrollX), 0);
                }
                if (scrollX > child.getLeft()) {//需要向左移动
                    hsv.scrollBy(child.getLeft() - scrollX, 0);
                }
                ((BaseFragment) viewPagerAdapter.getItem(position)).onPageSelected();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            BarUtils.setColor(mActivity,getResources().getColor(R.color.title_bar_color_1));
        }
    }

    public int currentPage;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
//        homePresenter.getTitles();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homePresenter.getTitles();
    }

    @Override
    public void onGetTitles(HomeTitleBean homeTitleBean) {
        indicatorContainer.removeAllViews();
        viewPagerAdapter.clear();
        for (int i = 0; i < homeTitleBean.data.size(); i++) {
            final ColorTrackView ctv = (ColorTrackView) View.inflate(getActivity(), R.layout.item, null);
            ctv.setText(homeTitleBean.data.get(i).name);
            int finalI = i;
            ctv.setOnClickListener(v -> {
                ColorTrackView colorTrackView = (ColorTrackView) indicatorContainer.
                        getChildAt(currentPage).findViewById(R.id.ctv);
                colorTrackView.setProgress(0);
                ColorTrackView colorTrackView2 = (ColorTrackView) indicatorContainer.
                        getChildAt(finalI).findViewById(R.id.ctv);
                colorTrackView2.setProgress(1);
                vp.setCurrentItem(finalI, false);
                currentPage = finalI;
//                ((BaseFragment) viewPagerAdapter.getItem(finalI)).onPageSelected();
//                RxBus.getDefault().post(new FlushContentFragmentEvent(finalI));
            });
            indicatorContainer.addView(ctv);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ctv.getLayoutParams();
            if (i == 0) {
                ctv.setProgress(1);
                layoutParams.leftMargin = SizeUtils.dp2px(5);
                ctv.setLayoutParams(layoutParams);
            }
            if (i == homeTitleBean.data.size() - 1) {
                layoutParams.rightMargin = SizeUtils.dp2px(47);
                ctv.setLayoutParams(layoutParams);
            }
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setTitleDetail(homeTitleBean.data.get(i));
            viewPagerAdapter.addFragment(contentFragment);
        }
        viewPagerAdapter.notifyDataSetChanged();
        ((BaseFragment) viewPagerAdapter.getItem(0)).onPageSelected();
    }

    @Override
    public void showLoading(int type) {

    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void hideLoading(int type) {

    }

    @OnClick({R.id.tvSearch})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                Intent intent = new Intent(mActivity, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
