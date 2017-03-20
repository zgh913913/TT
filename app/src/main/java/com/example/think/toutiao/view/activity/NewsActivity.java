package com.example.think.toutiao.view.activity;

import android.net.http.SslError;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseActivity;
import com.example.think.toutiao.model.bean.ContentDetail;
import com.example.think.toutiao.util.common.GlideRoundTransform;
import com.example.think.toutiao.util.event.RxBus;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Think on 2017/3/16.
 */

public class NewsActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivTitle)
    ImageView ivTitle;
    @BindView(R.id.ivComment)
    ImageView ivComment;
    @BindView(R.id.ivCollect)
    ImageView ivCollect;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.wv)
    WebView wv;
    @BindView(R.id.rv)
    RecyclerView rv;
    public ContentDetail contentDetail;
    private Subscription subscribe;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_news;
    }

    @Override
    protected void init() {
        subscribe = RxBus.getDefault().toObservableSticky(ContentDetail.class)
                .subscribe(contentDetail1 -> {
                    NewsActivity.this.contentDetail = contentDetail1;
                    wv.loadUrl(contentDetail1.article_url);
                    if (contentDetail1.media_info == null) {
                        return;
                    }
                    tvTitle.setText(contentDetail1.media_info.name);
                    Glide.with(getActivity()).load(contentDetail1.media_info.avatar_url)
                            .bitmapTransform(new GlideRoundTransform(getActivity()))
                            .into(ivTitle);
                });
        setWebSettings();
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @OnClick({R.id.back, R.id.ivComment, R.id.ivCollect, R.id.ivShare, R.id.tvComment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tvComment:
                break;
            case R.id.ivComment:
                break;
            case R.id.ivCollect:
                break;
            case R.id.ivShare:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (wv != null) {
            wv.goBack();
            wv.destroy();
        }
        super.onDestroy();
        if (subscribe != null)
            subscribe.unsubscribe();
    }

    private void setWebSettings() {
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setDomStorageEnabled(true);//DOM Storage
//        settings.setAppCacheEnabled(true);//是否使用缓存
        settings.setSupportZoom(true);//是否可以缩放，默认true
        // settings.setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
        //err_unknown_url_scheme
        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                //https加载
                handler.proceed();
            }
        });
    }

}
