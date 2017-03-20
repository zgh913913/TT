package com.example.think.toutiao.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseFragment;
import com.example.think.toutiao.construct.IContentConstruct;
import com.example.think.toutiao.db.ContentCache;
import com.example.think.toutiao.db.DbHelper;
import com.example.think.toutiao.model.bean.ContentBean;
import com.example.think.toutiao.model.bean.ContentDetail;
import com.example.think.toutiao.model.bean.ContentSuggestBean;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.presenter.ContentPresenter;
import com.example.think.toutiao.ui.pullresfresh.DefaultFooter;
import com.example.think.toutiao.ui.pullresfresh.SpringView;
import com.example.think.toutiao.ui.pullresfresh.TodayHeader;
import com.example.think.toutiao.util.common.ConstUtils;
import com.example.think.toutiao.util.common.L;
import com.example.think.toutiao.util.event.FlushContentFragmentEvent;
import com.example.think.toutiao.util.event.RxBus;
import com.example.think.toutiao.util.http.ContentFrom;
import com.example.think.toutiao.util.http.RxUtils;
import com.example.think.toutiao.view.activity.MainActivity;
import com.example.think.toutiao.view.activity.NewsActivity;
import com.example.think.toutiao.view.adapter.ContentRvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2017/3/12.
 */

public class ContentFragment extends BaseFragment<MainActivity> implements IContentConstruct.IContentView, SpringView.OnFreshListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.sv)
    SpringView sv;
    IContentConstruct.IContentPresenter contentPresenter;
    List<ContentDetail> contentDetailList;
    ContentRvAdapter contentRvAdapter;
    Subscription subscribe;

    public void setTitleDetail(HomeTitleBean.Detail titleDetail) {
        this.titleDetail = titleDetail;
    }

    HomeTitleBean.Detail titleDetail;

    protected void initView() {
        contentDetailList = new ArrayList<>();
        contentRvAdapter = new ContentRvAdapter(contentDetailList, this);
        rv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(contentRvAdapter);
        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, NewsActivity.class);
                startActivity(intent);
                RxBus.getDefault().postSticky(contentDetailList.get(position));
            }
        });
        contentPresenter = new ContentPresenter();
        contentPresenter.attachView(this);
        rv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        sv.setHeader(new TodayHeader());
        sv.setFooter(new DefaultFooter());
        sv.setListener(this);
        subscribe = RxBus.getDefault()
                .toObservable(Object.class)
                .subscribe(o -> {
                    if (o instanceof FlushContentFragmentEvent) {
                        getContentRequest(1);
                    }
                });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initData() {
        Schedulers.io().createWorker().schedule(() -> {
            getContentRequest(0);
        }, 500, TimeUnit.MILLISECONDS);
//        contentPresenter.getSuggest(RxUtils.getCommonRequestMap());
    }

    private void getContentRequest(int page) {
        HashMap<String, Object> map = RxUtils.getCommonRequestMap();
//        map.put("concern_id", titleDetail.concern_id);
        map.put("page", page);
        List<ContentCache> list = DbHelper.getInstance().getCacheDao().queryBuilder().list();
        map.put("category", titleDetail.category);
//        map.put("category", "news_hot");
//        map.put("refer", 1);
//        map.put("count", 20);
//        long value = System.currentTimeMillis() - 10 * 60 * 60 * 1000;
//        map.put("last_refresh_sub_entrance_interval", value);
        map.put("min_behot_time", new Date(2013, 1, 1).getTime());
//        map.put("loc_mode", 6);
//        map.put("tt_from", "tab_tip");
//        map.put("cp", "5483c68b03b02q1");
        if (contentPresenter != null) contentPresenter.getContent(map);
    }

    @Override
    public void onGetContent(ContentBean contentBean) {
        Gson gson = new Gson();
        for (int i = 0; i < contentBean.data.size(); i++) {
            String content = contentBean.data.get(i).content;
            ContentDetail contentDetail = gson.fromJson(content, ContentDetail.class);
            if (sv.isRefreshMore)
                contentDetailList.add(i, contentDetail);
            else
                contentDetailList.add(contentDetail);
        }
        contentRvAdapter.notifyDataSetChanged();
        sv.onFinishFreshAndLoad();
    }

    @Override
    public void onGetSuggest(ContentSuggestBean suggestWords) {
        mActivity.setSuggest(suggestWords);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Glide.get(mActivity).clearMemory();
        if (subscribe != null)
            subscribe.unsubscribe();
        contentPresenter.detachView();
        this.contentRvAdapter = null;
        this.contentDetailList = null;
        this.contentPresenter = null;
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

    @Override
    public void onRefresh() {
        getContentRequest(1);

    }

    @Override
    public void onLoadmore() {
        getContentRequest(-1);
    }
}
