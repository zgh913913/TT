package com.example.think.toutiao.ui.pullresfresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.think.toutiao.R;

/**
 * Created by Administrator on 2016/3/21.
 */
public class DefaultFooter extends BaseFooter {
    private Context context;
    private int rotationSrc;
    private TextView footerTitle;
    private ProgressBar footerProgressbar;


    public DefaultFooter() {
//        this.context = context;
//        this.rotationSrc = rotationSrc;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.footer_view, viewGroup, true);
        footerTitle = (TextView) view.findViewById(R.id.footer_title);
        footerProgressbar = (ProgressBar) view.findViewById(R.id.footer_progressbar);
//        footerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context,rotationSrc));
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (upORdown) {
            footerTitle.setText("松开载入更多");
        } else {
            footerTitle.setText("查看更多");
        }
    }

    @Override
    public void onStartAnim() {
//        footerTitle.setVisibility(View.INVISIBLE);
//        footerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        footerTitle.setText("查看更多");
//        footerTitle.setVisibility(View.VISIBLE);
//        footerProgressbar.setVisibility(View.INVISIBLE);
    }
}