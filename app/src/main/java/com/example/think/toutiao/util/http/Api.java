package com.example.think.toutiao.util.http;

/**
 * Created by my on 2016/11/7.
 */

public class Api {
    public static final String BASE_HTTP_URL = "http://is.snssdk.com";
    public static final String URL_SEARCH_WORDS = "/2/article/search_sug/";
    public static final String URL_GET_TITLES = "/article/category/get_subscribed/v1/";
    public static final String URL_GET_CONTENT = "/api/news/feed/v51/";
    public static final String URL_GET_CONTENT_PAGE_SUGGEST = "/search/suggest/homepage_suggest/";
    public static final String SEARCH = "https://is.snssdk.com/search/suggest/wap/initial_page/?homepage_search_suggest=&iid=8287714475&device_id=32147560984&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=602&version_name=6.0.2&device_platform=android&ab_version=102739%2C110752%2C110440%2C109656%2C101786%2C107660%2C101533%2C109464%2C110341%2C109892%2C109777%2C106784%2C110438%2C97143%2C110311%2C111119%2C108603%2C101558%2C104325%2C94041%2C110674%2C108125%2C105610%2C111201%2C110062%2C109982%2C108978%2C110327%2C108487%2C110718%2C110795%2C98040%2C110881%2C105475%2C108568&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=94563%2C102749&abflag=3&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=602&resolution=1080*1920&dpi=480&update_version_code=6025&_rticket=1488950142569";
    //http://is.snssdk.com/user/tab/tabs/?iid=8746606365&device_id=32147560984&ac=wifi&channel=ucad&aid=13&app_name=news_article&version_code=605&version_name=6.0.5&device_platform=android&ab_version=102739%2C112375%2C111304%2C101786%2C112146%2C111548%2C112157%2C101533%2C109464%2C110341%2C109890%2C112280%2C109775%2C106784%2C97143%2C31240%2C112369%2C111344%2C101558%2C104322%2C112163%2C112159%2C94045%2C112098%2C105610%2C112343%2C105821%2C111316%2C108977%2C111796%2C111257%2C108488%2C111897%2C110795%2C111417%2C98046%2C105475&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_group=100168&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=605&resolution=1080*1920&dpi=480&update_version_code=6055&_rticket=1489480341653&iid=8746606365&device_id=32147560984&ac=wifi&channel=ucad&aid=13&app_name=news_article&version_code=605&version_name=6.0.5&device_platform=android&ab_version=102739%2C112375%2C111304%2C101786%2C112146%2C111548%2C112157%2C101533%2C109464%2C110341%2C109890%2C112280%2C109775%2C106784%2C97143%2C31240%2C112369%2C111344%2C101558%2C104322%2C112163%2C112159%2C94045%2C112098%2C105610%2C112343%2C105821%2C111316%2C108977%2C111796%2C111257%2C108488%2C111897%2C110795%2C111417%2C98046%2C105475&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_group=100168&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=605&resolution=1080*1920&dpi=480&update_version_code=6055&_rticket=1489480341655
    // http://is.snssdk.com/article/category/get_subscribed/v1/?iid=8605872222&device_id=32147560984&ac=wifi&channel=wandoujia&aid=13&app_name=news_article&version_code=604&version_name=6.0.4&device_platform=android&ab_version=102739%2C110752%2C111305%2C109656%2C101786%2C111994%2C111546%2C111993%2C101533%2C111997%2C109464%2C110341%2C109892%2C109777%2C106784%2C97143%2C111341%2C101558%2C104325%2C94041%2C111968%2C108125%2C105610%2C111934%2C110062%2C111316%2C111613%2C108978%2C111796%2C111258%2C111581%2C108487%2C111897%2C110795%2C111417%2C98040%2C111608%2C105475&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_group=100170&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=604&resolution=1080*1920&dpi=480&update_version_code=6043&_rticket=1489297506669
//    1489500593  1489505026      4433
//  1489505026   1489505435
//http://is.snssdk.com/api/news/feed/v51/?category=news_hot&refer=1&count=20&loc_mode=6&tt_from=tab_tip&cp=5f81c59135766q1&iid=8746606365&device_id=32147560984&ac=wifi&channel=ucad&aid=13&app_name=news_article&version_code=605&version_name=6.0.5&device_platform=android&ab_version=102739%2C112577%2C101786%2C111549%2C112616%2C101533%2C109464%2C110341%2C112640%2C112693%2C112070%2C106784%2C112630%2C97142%2C31243%2C112741%2C111339%2C101558%2C104324%2C112623%2C110111%2C94043%2C112532%2C112100%2C108125%2C105610%2C105824%2C112578%2C108977%2C111796%2C108486%2C111897%2C110795%2C98043%2C105475&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_group=100169&ab_feature=94563%2C102749&abflag=3&ssmix=a&device_type=MI+4LTE&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=868568022429671&openudid=346349f51618d754&manifest_version_code=605&resolution=1080*1920&dpi=480&update_version_code=6055&_rticket=1489581926932
}
