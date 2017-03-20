package com.example.think.toutiao.view.adapter;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.think.toutiao.R;
import com.example.think.toutiao.model.bean.ContentDetail;
import com.example.think.toutiao.util.common.GlideUtils;
import com.example.think.toutiao.util.common.StringUtils;
import com.example.think.toutiao.util.common.TimeUtils;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Think on 2017/3/14.
 */

public class ContentRvAdapter extends BaseMultiItemQuickAdapter<ContentDetail, BaseViewHolder> {
    Fragment fragment;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ContentRvAdapter(List<ContentDetail> data, Fragment fragment) {
        super(data);
        this.fragment = fragment;
        addItemType(MultipleItem.TEXT, R.layout.item_text);
        addItemType(MultipleItem.TEXT_IMAGE_RIGHT, R.layout.item_image_right);
        addItemType(MultipleItem.TEXT_IMAGE_BOTTOM_1, R.layout.item_image_bottom_1);
        addItemType(MultipleItem.TEXT_IMAGE_BOTTOM_3, R.layout.item_image_bottom_3);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContentDetail contentDetail) {
        int itemType = contentDetail.getItemType();
        helper.setVisible(R.id.label, false)
                .setText(R.id.tvTitle, contentDetail.title)
                .setText(R.id.desc, contentDetail.media_name == null ? "" : contentDetail.media_name
                        + "   " + contentDetail.comment_count + "评论"
                        + "  " + TimeUtils.millis2String(contentDetail.publish_time * 1000L));
        switch (itemType) {
            case MultipleItem.TEXT:
                break;
            case MultipleItem.IMG:
                break;
            case MultipleItem.TEXT_IMAGE_RIGHT:
                String url = contentDetail.middle_image.url;
                if (StringUtils.isEmpty(url))
                    url = ((Map<String, String>) contentDetail.large_image_list.get(0)).get("url");
                GlideUtils.loadImageView(this.fragment, url, helper.getView(R.id.iv));
                break;
            case MultipleItem.TEXT_IMAGE_BOTTOM_1:
                String u = ((Map<String, String>) contentDetail.large_image_list.get(0)).get("url");
//                String u = contentDetail.middle_image.url;
                ImageView imageView = helper.getView(R.id.iv);
                GlideUtils.loadImageView(this.fragment, u, imageView);
                break;
            case MultipleItem.TEXT_IMAGE_BOTTOM_3:
                String u1 = ((LinkedTreeMap<String, String>) contentDetail.image_list.get(0)).get("url");
                String u2 = ((LinkedTreeMap<String, String>) contentDetail.image_list.get(1)).get("url");
                String u3 = ((LinkedTreeMap<String, String>) contentDetail.image_list.get(2)).get("url");
                ImageView imageView1 = helper.getView(R.id.iv);
                ImageView imageView2 = helper.getView(R.id.iv2);
                ImageView imageView3 = helper.getView(R.id.iv3);
                GlideUtils.loadImageView(this.fragment, u1, imageView1);
                GlideUtils.loadImageView(this.fragment, u2, imageView2);
                GlideUtils.loadImageView(this.fragment, u3, imageView3);
                break;
            case MultipleItem.VIDEO:
                break;
        }
    }
}
