package com.example.think.toutiao.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.think.toutiao.R;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by my on 2016/8/24.
 */
public class SearchSuggestRvAdapter extends RecyclerView.Adapter<SearchSuggestRvAdapter.ViewHolder> {

    /**
     * 所在的activity
     */
    private Activity mActivity;
    /**
     * 每个item的布局文件
     */
    private int itemLayout;
    /**
     * 数据list
     */
    private ArrayList<SearchKeyWordBean> suggestWords;
    private String keyWord = " ";

    public SearchSuggestRvAdapter(Activity activity, int itemLayout, ArrayList<SearchKeyWordBean> suggestWords) {
        this.mActivity = activity;
        this.itemLayout = itemLayout;
        this.suggestWords = suggestWords;
    }

    public void setList(ArrayList<SearchKeyWordBean> suggestWords, String keyWord) {
        this.suggestWords.clear();
        this.suggestWords.addAll(suggestWords);
        this.keyWord = keyWord;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String s = suggestWords.get(position).keyword;
        s = s.replace(keyWord, "<font color='#ff0000'>" + keyWord + "</font>");
        holder.tv.setText(Html.fromHtml(s));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return suggestWords == null ? 0 : suggestWords.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvKeyWord)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
