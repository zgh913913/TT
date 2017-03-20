package com.example.think.toutiao.view.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Think on 2017/3/14.
 */
public class MultipleItem implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int TEXT_IMAGE_RIGHT = 3;
    public static final int TEXT_IMAGE_BOTTOM_1 = 4;
    public static final int TEXT_IMAGE_BOTTOM_3 = 5;
    public static final int VIDEO = 6;
    public int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
