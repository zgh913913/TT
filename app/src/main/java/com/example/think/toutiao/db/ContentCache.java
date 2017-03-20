package com.example.think.toutiao.db;

import com.example.think.toutiao.model.bean.ContentDetail;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Think on 2017/3/16.
 */
@Entity
public class ContentCache {
    @Id(autoincrement = true)
    public Long id;
    public String data;
    public String category;
    public boolean hasLoaded;
    @Generated(hash = 1871170747)
    public ContentCache(Long id, String data, String category, boolean hasLoaded) {
        this.id = id;
        this.data = data;
        this.category = category;
        this.hasLoaded = hasLoaded;
    }
    @Generated(hash = 761078220)
    public ContentCache() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean getHasLoaded() {
        return this.hasLoaded;
    }
    public void setHasLoaded(boolean hasLoaded) {
        this.hasLoaded = hasLoaded;
    }
}
