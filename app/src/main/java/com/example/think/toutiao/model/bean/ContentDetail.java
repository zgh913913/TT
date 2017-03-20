package com.example.think.toutiao.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.think.toutiao.util.common.StringUtils;
import com.example.think.toutiao.view.adapter.MultipleItem;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Created by Think on 2017/3/14.
 */

public class ContentDetail implements MultiItemEntity {

    /**
     * read_count : 31586
     * media_name : 中国台湾网
     * ban_comment : 0
     * abstract : 马英九和柯建铭。（图片来源：台湾“东森新闻云”）中国台湾网3月14日讯　综合台媒报道，前台湾地区领导人马英九涉嫌泄密案，台北地检署14日侦结认定他明知不能非法监听还涉嫌教唆，遂以泄密罪、“个资法”等将他起诉，同时对外开记者会说明。
     * image_list : []
     * has_video : false
     * article_type : 0
     * tag : news_politics
     * has_m3u8_video : 0
     * keywords : 马英九,柯建铭,江宜桦,检察总长,黄世铭
     * rid : 20170314163754010003048091040DA9
     * show_portrait_article : false
     * user_verified : 0
     * aggr_type : 1
     * cell_type : 0
     * article_sub_type : 0
     * bury_count : 1
     * title : 台北检方正式以泄密罪起诉马英九
     * ignore_web_transform : 1
     * source_icon_style : 1
     * tip : 0
     * hot : 0
     * share_url : http://toutiao.com/a6397187517719970050/?iid=8746606365&app=news_article
     * has_mp4_video : 0
     * source : 中国台湾网
     * comment_count : 44
     * article_url : http://toutiao.com/group/6397187517719970050/
     * filter_words : [{"id":"8:0","name":"重复、旧闻","is_selected":false},{"id":"9:1","name":"内容质量差","is_selected":false},{"id":"5:9104206","name":"来源:中国台湾网","is_selected":false},{"id":"3:306442034","name":"法律","is_selected":false},{"id":"6:22164","name":"马英九","is_selected":false},{"id":"6:21639","name":"台北","is_selected":false}]
     * publish_time : 1489462055
     * action_list : [{"action":1,"extra":{},"desc":""},{"action":3,"extra":{},"desc":""},{"action":7,"extra":{},"desc":""},{"action":9,"extra":{},"desc":""}]
     * gallary_image_count : 1
     * cell_layout_style : 1
     * tag_id : 6397187517719970050
     * action_extra : {"channel_id": 3189398996}
     * video_style : 0
     * verified_content :
     * display_url : http://toutiao.com/group/6397187517719970050/
     * large_image_list : []
     * item_id : 6397190813616439810
     * is_subject : false
     * show_portrait : false
     * repin_count : 150
     * cell_flag : 11
     * user_info : {"verified_content":"","avatar_url":"http://p3.pstatp.com/thumb/11199/7570966772","user_id":5852567431,"name":"中国台湾网","follower_count":0,"follow":false,"user_auth_info":"","user_verified":false,"description":""}
     * source_open_url : sslocal://profile?uid=5852567431
     * level : 0
     * like_count : 3
     * digg_count : 3
     * behot_time : 1489478722
     * cursor : 1489478722999
     * url : http://toutiao.com/group/6397187517719970050/
     * preload_web : 1
     * user_repin : 0
     * has_image : true
     * item_version : 0
     * media_info : {"user_id":5852567431,"verified_content":"","avatar_url":"http://p3.pstatp.com/large/11199/7570966772","media_id":5852655501,"name":"中国台湾网","recommend_type":0,"follow":false,"recommend_reason":"","is_star_user":false,"user_verified":false}
     * group_id : 6397187517719970050
     * middle_image : {"url":"http://p1.pstatp.com/list/300x196/18b100045a4894d8642f.webp","width":563,"url_list":[{"url":"http://p1.pstatp.com/list/300x196/18b100045a4894d8642f.webp"},{"url":"http://p4.pstatp.com/list/300x196/18b100045a4894d8642f.webp"},{"url":"http://p.pstatp.com/list/300x196/18b100045a4894d8642f.webp"}],"uri":"list/18b100045a4894d8642f","height":316}
     */

    public int read_count;
    public String media_name;
    public int ban_comment;
    @SerializedName("abstract")
    public String abstractX;
    public boolean has_video;
    public int article_type;
    public String tag;
    public int has_m3u8_video;
    public String keywords;
    public String rid;
    public boolean show_portrait_article;
//    public int user_verified;
    public int aggr_type;
    public int cell_type;
    public int article_sub_type;
    public int bury_count;
    public String title;
    public int ignore_web_transform;
    public int source_icon_style;
    public int tip;
    public int hot;
    public String share_url;
    public int has_mp4_video;
    public String source;
    public int comment_count;
    public String article_url;
    public int publish_time;
    public int gallary_image_count;
    public int cell_layout_style;
    public long tag_id;
    public String action_extra;
    public int video_style;
    public String verified_content;
    public String display_url;
    public long item_id;
    public boolean is_subject;
    public boolean show_portrait;
    public int repin_count;
    public int cell_flag;
    /**
     * verified_content :
     * avatar_url : http://p3.pstatp.com/thumb/11199/7570966772
     * user_id : 5852567431
     * name : 中国台湾网
     * follower_count : 0
     * follow : false
     * user_auth_info :
     * user_verified : false
     * description :
     */

    public UserInfoBean user_info;
    public String source_open_url;
    public int level;
    public int like_count;
    public int digg_count;
    public int behot_time;
    public long cursor;
    public String url;
    public int preload_web;
    public int user_repin;
    public boolean has_image;
    public int item_version;
    /**
     * user_id : 5852567431
     * verified_content :
     * avatar_url : http://p3.pstatp.com/large/11199/7570966772
     * media_id : 5852655501
     * name : 中国台湾网
     * recommend_type : 0
     * follow : false
     * recommend_reason :
     * is_star_user : false
     * user_verified : false
     */

    public MediaInfoBean media_info;
    public long group_id;
    /**
     * url : http://p1.pstatp.com/list/300x196/18b100045a4894d8642f.webp
     * width : 563
     * url_list : [{"url":"http://p1.pstatp.com/list/300x196/18b100045a4894d8642f.webp"},{"url":"http://p4.pstatp.com/list/300x196/18b100045a4894d8642f.webp"},{"url":"http://p.pstatp.com/list/300x196/18b100045a4894d8642f.webp"}]
     * uri : list/18b100045a4894d8642f
     * height : 316
     */

    public MiddleImageBean middle_image;
    public List<?> image_list;
    /**
     * id : 8:0
     * name : 重复、旧闻
     * is_selected : false
     */

    public List<FilterWordsBean> filter_words;
    /**
     * action : 1
     * extra : {}
     * desc :
     */

    public List<ActionListBean> action_list;
    public List<?> large_image_list;

    @Override
    public int getItemType() {
//        if (has_image = false)
//            return MultipleItem.TEXT;

        if (large_image_list != null && large_image_list.size() == 1)
            return MultipleItem.TEXT_IMAGE_BOTTOM_1;
        if (image_list != null) {
            if (image_list.size() == 1) {
                return MultipleItem.TEXT_IMAGE_RIGHT;
            } else if (image_list.size() == 3) {
                return MultipleItem.TEXT_IMAGE_BOTTOM_3;
            }
        }
        if (middle_image != null && middle_image.url_list != null) {
            return MultipleItem.TEXT_IMAGE_RIGHT;
        }
        return MultipleItem.TEXT;
    }

    public static class UserInfoBean {
        public String verified_content;
        public String avatar_url;
        public long user_id;
        public String name;
        public int follower_count;
        public boolean follow;
        public String user_auth_info;
        public boolean user_verified;
        public String description;
    }

    public static class MediaInfoBean {
        public long user_id;
        public String verified_content;
        public String avatar_url;
        public long media_id;
        public String name;
        public int recommend_type;
        public boolean follow;
        public String recommend_reason;
        public boolean is_star_user;
        public boolean user_verified;
    }

    public static class MiddleImageBean {
        public String url;
        public int width;
        public String uri;
        public int height;
        /**
         * url : http://p1.pstatp.com/list/300x196/18b100045a4894d8642f.webp
         */

        public List<UrlListBean> url_list;

        public static class UrlListBean {
            public String url;
        }
    }

    public static class FilterWordsBean {
        public String id;
        public String name;
        public boolean is_selected;
    }

    public static class ActionListBean {
        public int action;
        public String desc;
    }
}
