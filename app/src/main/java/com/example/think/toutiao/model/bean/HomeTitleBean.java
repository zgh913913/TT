package com.example.think.toutiao.model.bean;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Think on 2017/3/13.
 */

public class HomeTitleBean {

    public ArrayList<Detail> data;
    public String version;

    public class Detail {
        public String category;
        public String concern_id;
        public String default_add;
        public String flags;
        public String icon_url;
        public String name;
        public String tip_new;
        public String type;
        public String web_url;
    }
}
