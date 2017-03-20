package com.example.think.toutiao.ui.pullresfresh;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.toutiao.R;
import com.example.think.toutiao.util.common.SizeUtils;


/**
 * Created by Administrator on 2016/3/23.
 */
public class TodayHeader extends BaseHeader {

    private RectProgressView pv1;
    private RectProgressView pv2;
    private RectProgressView pv3;
    private RectProgressView pv4;
    private RectProgressView pv5;
    private RectProgressView pv6;
    private RectProgressView pv7;
    private RectProgressView pv8;
    private RectProgressView pv9;
    private View viewRoot;
    private TextView title;
    private ImageView ivAnim;
    private View ivContainer;

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        viewRoot = inflater.inflate(R.layout.header_view, viewGroup, true);
        pv1 = (RectProgressView) viewRoot.findViewById(R.id.pv1);
        pv2 = (RectProgressView) viewRoot.findViewById(R.id.pv2);
        pv3 = (RectProgressView) viewRoot.findViewById(R.id.pv3);
        pv4 = (RectProgressView) viewRoot.findViewById(R.id.pv4);
        pv5 = (RectProgressView) viewRoot.findViewById(R.id.pv5);
        pv6 = (RectProgressView) viewRoot.findViewById(R.id.pv6);
        pv7 = (RectProgressView) viewRoot.findViewById(R.id.pv7);
        pv8 = (RectProgressView) viewRoot.findViewById(R.id.pv8);
        pv9 = (RectProgressView) viewRoot.findViewById(R.id.pv9);
        title = (TextView) viewRoot.findViewById(R.id.title);
        ivContainer = viewRoot.findViewById(R.id.container);
        ivAnim = ((ImageView) viewRoot.findViewById(R.id.ivAnim));
        return viewRoot;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
        int progress = (int) (((dy - SizeUtils.dp2px(48))) * 1.5);//高度50-margintop9  *2 提高速度
        pv1.setProgress(progress);
        pv2.setProgress(progress);
        pv3.setProgress(progress);
        pv4.setProgress(progress - 10);
        pv5.setProgress(progress - 20);
        pv6.setProgress(progress - 30);
        pv7.setProgress(progress - 40);
        pv8.setProgress(progress - 60);
        pv9.setProgress(progress - 80);

    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown) {
            title.setText("松开刷新数据");
        } else {
            title.setText("下拉刷新");
        }
    }

    @Override
    public void onStartAnim() {
        title.setText("正在刷新");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivContainer.setVisibility(View.INVISIBLE);
            ivAnim.setVisibility(View.VISIBLE);
            AnimatedVectorDrawable background = (AnimatedVectorDrawable) ivAnim.getBackground();
            background.start();
        }
    }

    @Override
    public void onFinishAnim() {
        title.setText("下拉刷新");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivAnim.setVisibility(View.INVISIBLE);
            ivContainer.setVisibility(View.VISIBLE);
            AnimatedVectorDrawable background = (AnimatedVectorDrawable) ivAnim.getBackground();
            background.stop();
        }
    }
}
