package com.example.think.toutiao.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.think.toutiao.R;

import java.util.ArrayList;

/**
 * Created by Think on 2017/2/28.
 */

public class BottomBar extends LinearLayout {
    public ArrayList<BottomBarItem> items;
    public int selectedTitleColor;
    public int unSelectedTitleColor;
    //show title
    public boolean titleShow = true;
    public int selectedPosition;
    public Context mContext;
    private int menuRes;

    public BottomBar(Context context) {
        super(context);
        init(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        items = new ArrayList<>();
        mContext = context;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomBar, 0, 0);
            try {
                menuRes = ta.getResourceId(R.styleable.BottomBar_menu, 0);
                selectedTitleColor = ta.getColor(R.styleable.BottomBar_selectedTitleColor, 0);
                unSelectedTitleColor = ta.getColor(R.styleable.BottomBar_unSelectedTitleColor, 0);
            } finally {
                ta.recycle();
            }
        }
    }

    private void initItems() {
        Menu menu = new MenuBuilder(mContext);
        ((Activity) mContext).getMenuInflater().inflate(menuRes, menu);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        removeAllViews();
        items.clear();
        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundColor(Color.WHITE);
        setGravity(Gravity.CENTER);
        float itemWidth = getWidth() / menu.size();

        for (int i = 0; i < menu.size(); i++) {
            BottomBarItem item = new BottomBarItem();
            View view = inflater.inflate(R.layout.item_bottom, this, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = (int) itemWidth;
            view.setLayoutParams(layoutParams);
            item.icon = (ImageView) view.findViewById(R.id.item_icon);
            item.title = (TextView) view.findViewById(R.id.item_title);
            item.notify = (TextView) view.findViewById(R.id.item_notify);
            items.add(item);
            addView(view);

            item.title.setText(menu.getItem(i).getTitle());
            item.title.setVisibility(titleShow ? VISIBLE : GONE);
            item.icon.setImageDrawable(menu.getItem(i).getIcon());
            boolean b = i == selectedPosition;
            view.setSelected(b);
            item.title.setSelected(b);
            item.icon.setSelected(b);
            if (b) {
                itemAnimSelected(item);
            }
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateItems(finalI);
                }
            });
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (menuRes != 0) {
            initItems();
        }
    }

    /**
     * 最新被选中的item
     */
    private void updateItems(int newSelectedPos) {
        if (selectedPosition != newSelectedPos) {
            BottomBarItem lastItem = items.get(selectedPosition);
            itemAnimUnselected(lastItem);
            BottomBarItem newSelectedItem = items.get(newSelectedPos);
            itemAnimSelected(newSelectedItem);
        }
        if (mOnTabClickListener != null) {
            if (selectedPosition != newSelectedPos) {
                mOnTabClickListener.onTabChange(selectedPosition, newSelectedPos);
            } else {
                mOnTabClickListener.loadMore(newSelectedPos);
            }
        }
        selectedPosition = newSelectedPos;
    }

    public BottomBar addItem(BottomBarItem bottomBarItem) {
        items.add(bottomBarItem);
        return this;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    public OnTabClickListener mOnTabClickListener;

    public interface OnTabClickListener {
        void onTabChange(int lastTab, int newTab);

        /**
         * 当点击的是同一个tab时候，加载新数据
         */
        void loadMore(int selectedPos);
    }

    public class BottomBarItem {
        public ImageView icon;
        public TextView title;
        public TextView notify;
    }

    public void itemAnimSelected(BottomBarItem item) {
        item.icon.setSelected(true);
        item.title.setSelected(true);
        item.icon.animate()
                .setDuration(200)
                .scaleX(105 / 100f)
                .scaleY(105 / 100f)
                .setInterpolator(new OvershootInterpolator())
                .start();
    }

    public void itemAnimUnselected(BottomBarItem item) {
        item.icon.setSelected(false);
        item.title.setSelected(false);
        item.icon.animate()
                .setDuration(200)
                .scaleX(100 / 105f)
                .scaleY(100 / 105f)
                .start();
    }
}
