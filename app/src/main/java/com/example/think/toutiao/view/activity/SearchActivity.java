package com.example.think.toutiao.view.activity;

import android.net.http.SslError;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.toutiao.R;
import com.example.think.toutiao.base.BaseActivity;
import com.example.think.toutiao.construct.ISearchConstruct;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;
import com.example.think.toutiao.presenter.SearchPresenter;
import com.example.think.toutiao.util.common.KeyboardUtils;
import com.example.think.toutiao.util.http.Api;
import com.example.think.toutiao.view.adapter.SearchSuggestRvAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.http.Url;

/**
 * Created by Think on 2017/3/8.
 */
public class SearchActivity extends BaseActivity implements ISearchConstruct.ISearchView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.wv)
    WebView wv;
    @BindView(R.id.rvSearch)
    RecyclerView rvSearch;
    @BindView(R.id.flSearchRvContainer)
    FrameLayout flSearRvContainer;
    SearchPresenter searPresenter;
    private SearchSuggestRvAdapter suggestAdapter;
    private ArrayList<SearchKeyWordBean> suggestWords;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        setEtListener();
        setWebSettings();
        wv.loadUrl(Api.SEARCH);
        searPresenter = new SearchPresenter();
        searPresenter.attachView(this);

        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        suggestWords = new ArrayList<>();
        suggestAdapter = new SearchSuggestRvAdapter(getActivity(), R.layout.item_search, suggestWords);
        suggestAdapter.setOnItemClickListener(new SearchSuggestRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                showHide(flSearRvContainer, false);
                String keyword = suggestWords.get(pos).keyword;
                shouldGetSearchSuggestWords = false;
                etSearch.setText(keyword);
                etSearch.setSelection(keyword.length());
                tvSearch.callOnClick();
            }
        });
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
//        settings.setSupportZoom(true);//是否可以缩放，默认true
        // settings.setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
        //err_unknown_url_scheme
        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtil.isNetworkUrl(url))
                    wv.loadUrl("http://is.snssdk.com/api/2/wap/search/?" +
                            "from=search_tab&keyword=%E8%80%81%E6%A2%81%E6%8F%AD%E7%A7%98&keyword_type=hist&iid=8287714475&device_id=32147560984" +
                            "&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=602&version_name=6.0.2&device_platform=android" +
                            "&ab_version=102739%2C110752%2C111255%2C109656%2C101786%2C107660%2C101533%2C109464%2C110341%2C109892%2C109777%2C106784%2C97143%2C110311%2C111119%2C108603%2C101558%2C104325%2C94041%2C110674%2C108125%2C105610%2C111228%2C110062%2C111253%2C108978%2C110327%2C108487%2C110718%2C110795%2C98040%2C110881%2C105475%2C108568&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=94563%2C102749&abflag=3" +
                            "&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754" +
                            "&manifest_version_code=602&resolution=1080*1920&dpi=480&update_version_code=6025&_rticket=1488959195021&search_sug=1&forum=1&latitude=31.209857325026647&longitude=121.58850141281744");
                else {
                }
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

    boolean shouldGetSearchSuggestWords = true;

    @OnClick({R.id.back, R.id.ivDelete, R.id.tvSearch, R.id.flSearchRvContainer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ivDelete:
                etSearch.setText("");
                KeyboardUtils.showSoftInput(etSearch);
                break;
            case R.id.tvSearch:
                String u = null;
                try {
                    u = "http://is.snssdk.com/api/2/wap/search/?" +
                            "from=search_tab&keyword=" + URLEncoder.encode(etSearch.getText().toString(), "UTF-8") +
                            "&keyword_type=hist&iid=8287714475&device_id=32147560984&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=602&version_name=6.0.2&device_platform=android&ab_version=102739%2C110752%2C111255%2C109656%2C101786%2C107660%2C101533%2C109464%2C110341%2C109892%2C109777%2C106784%2C97143%2C110311%2C111119%2C108603%2C101558%2C104325%2C94041%2C110674%2C108125%2C105610%2C111228%2C110062%2C111253%2C108978%2C110327%2C108487%2C110718%2C110795%2C98040%2C110881%2C105475%2C108568&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=94563%2C102749&abflag=3&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=602&resolution=1080*1920&dpi=480&update_version_code=6025&_rticket=1488959195021&search_sug=1&forum=1&latitude=31.209857325026647&longitude=121.58850141281744";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                wv.loadUrl(u);
                KeyboardUtils.hideSoftInput(this);
                break;
            case R.id.flSearchRvContainer:
                showHide(flSearRvContainer, false);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (flSearRvContainer.getVisibility() == View.VISIBLE) {
            showHide(flSearRvContainer, false);
            return;
        }
        super.onBackPressed();
    }

    private void setEtListener() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    tvSearch.callOnClick();
                }
                return false;
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    ivDelete.setVisibility(View.GONE);
                    tvSearch.setEnabled(false);
                    showHide(flSearRvContainer, false);
                } else {
                    if (!shouldGetSearchSuggestWords) {
                        shouldGetSearchSuggestWords = true;
                        return;
                    }
                    ivDelete.setVisibility(View.VISIBLE);
                    tvSearch.setEnabled(true);
                    searPresenter.getSuggestWords(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onGetSuggestWords(final ArrayList<SearchKeyWordBean> suggestWords) {
        if (suggestWords == null || suggestWords.size() == 0) {
            showHide(flSearRvContainer, false);
            return;
        }
        suggestAdapter.setList(suggestWords, etSearch.getText().toString());
        rvSearch.setAdapter(suggestAdapter);
        showHide(flSearRvContainer, true);
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

    public void showHide(final View view, boolean b) {
        view.setPivotX(0);
        view.setPivotY(0);
        if (b) {
            view.setVisibility(View.VISIBLE);
            view.animate().setDuration(200)
                    .scaleY(1)
//                    .scaleX(1)
                    .alpha(1)
                    .start();
        } else {
            view.animate().setDuration(200)
                    .scaleY(0)
//                    .scaleX(0)
                    .alpha(0)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.GONE);
                        }
                    })
                    .start();
        }
    }
}
