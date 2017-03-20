package com.example.think.toutiao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Think on 2017/3/16.
 */
@Entity
public class TitleCache {
    @Id
    public long id;
    public String data;
    @Generated(hash = 217988752)
    public TitleCache(long id, String data) {
        this.id = id;
        this.data = data;
    }
    @Generated(hash = 1376745511)
    public TitleCache() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
