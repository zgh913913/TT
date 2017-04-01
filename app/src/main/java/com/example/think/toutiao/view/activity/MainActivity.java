package com.example.think.toutiao.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseActivity;
import com.example.think.toutiao.db.ContentCache;
import com.example.think.toutiao.db.DbHelper;
import com.example.think.toutiao.db.TitleCache;
import com.example.think.toutiao.model.bean.ContentSuggestBean;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.model.ContentModel;
import com.example.think.toutiao.ui.BottomBar;
import com.example.think.toutiao.util.http.RxUtils;
import com.example.think.toutiao.view.fragment.FragmentController;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bb)
    BottomBar bb;
    @BindView(R.id.flContainer)
    FrameLayout flContainer;
    FragmentController controller;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        List<TitleCache> list = DbHelper.getInstance().getTitleCacheDao().queryBuilder().list();
        List<ContentCache> list1 = DbHelper.getInstance().getCacheDao().queryBuilder().list();
        controller = FragmentController.getInstance(this, R.id.flContainer);
        controller.showFragment(0);
        bb.setOnTabClickListener(new BottomBar.OnTabClickListener() {
            @Override
            public void onTabChange(int lastTab, int newTab) {
                controller.showFragment(newTab);
            }

            @Override
            public void loadMore(int selectedPos) {
            }
        });
    }

    @Override
    public int getBarColor() {
        return getResources().getColor(R.color.title_bar_color_1);
    }


    public void setSuggest(ContentSuggestBean contentSuggestBean) {
        String homepage_search_suggest = contentSuggestBean.homepage_search_suggest;
//        tvSearch.setText(homepage_search_suggest);
    }
}
