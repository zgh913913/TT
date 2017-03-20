package com.example.think.toutiao.util.http;

import com.example.think.toutiao.model.bean.BaseResult;
import com.example.think.toutiao.model.bean.ContentBean;
import com.example.think.toutiao.model.bean.ContentSuggestBean;
import com.example.think.toutiao.model.bean.HomeTitleBean;
import com.example.think.toutiao.model.bean.SearchKeyWordBean;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by my on 2016/10/31.
 */
public interface ApiService {
    @GET(Api.URL_SEARCH_WORDS)
    Observable<BaseResult<ArrayList<SearchKeyWordBean>>> getSearchKeyWords(@QueryMap Map<String, Object> map);

    @GET(Api.URL_GET_TITLES)
    Observable<BaseResult<HomeTitleBean>> getTitles(@QueryMap Map<String, Object> map);

    @GET(Api.URL_GET_CONTENT)
    Observable<ContentBean> getContent(@QueryMap Map<String, Object> map);

    @GET(Api.URL_GET_CONTENT_PAGE_SUGGEST)
    Observable<BaseResult<ContentSuggestBean>> getContentPageSuggest(@QueryMap Map<String, Object> map);

//    http://is.snssdk.com/api/news/feed/v51/?concern_id=6286225228934679042&refer=1&count=20&min_behot_time=1489393592&last_refresh_sub_entrance_interval=1489393797&loc_mode=6&tt_from=tab&cp=548ac6695b885q1&iid=8605872222&device_id=32147560984&ac=wifi&channel=wandoujia&aid=13&app_name=news_article&version_code=604&version_name=6.0.4&device_platform=android&ab_version=102739%2C110752%2C111305%2C101786%2C112146%2C111546%2C112143%2C101533%2C109464%2C110341%2C109892%2C109777%2C106784%2C97143%2C111341%2C101558%2C104325%2C94041%2C112099%2C108125%2C105610%2C111934%2C110062%2C111316%2C111613%2C108978%2C111796%2C111258%2C111581%2C108487%2C111897%2C110795%2C111417%2C98040%2C105475&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_group=100170&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=604&resolution=1080*1920&dpi=480&update_version_code=6043&_rticket=1489393797221
}
