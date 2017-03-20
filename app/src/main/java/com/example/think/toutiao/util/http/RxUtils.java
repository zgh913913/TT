package com.example.think.toutiao.util.http;

import android.app.usage.NetworkStats;
import android.support.annotation.NonNull;

import com.example.think.toutiao.util.common.L;
import com.example.think.toutiao.util.common.NetworkUtils;
import com.example.think.toutiao.util.common.SPUtils;
import com.example.think.toutiao.util.common.ToastUtils;
import com.example.think.toutiao.util.common.Utils;
import com.example.think.toutiao.util.event.RxBus;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by my on 2016/10/31.
 */

public class RxUtils {
    public static ApiService getApi(String baseUrl) {
        OkHttpClient client = OkHttpClientManager.getInstance();
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(ApiService.class);
    }

    @NonNull
    public static HashMap<String, Object> getCommonRequestMap() {
        HashMap<String, Object> map = new HashMap<>();
        //8806035382  35477989439  8863760774 35510474091
//        map.put("iid","8805409684");//8805409684    35477662628  842A7283F  20CE16FB6
        map.put("device_id", "35479696720");
        map.put("ac", NetworkUtils.getNetworkType().toString());
//        map.put("ac", "wifi");
        map.put("channel", "ucad");
        map.put("aid", "13");
        map.put("app_name", "news_article");
        map.put("version_code", "605");
        map.put("version_name", "6.0.5");
        map.put("device_platform", "android");
//        map.put("ab_version", "102739,112375,111304,101786,112146,111548,112157,101533,109464,110341,109890,112477,106784,97143,31240,112531,111344,101558,104322,112163,112159,94045,112532,112098,105610,112343,105821,111316,108977,111796,112533,108488,111897,110795,111417,98046,105475");
//        map.put("ab_client", "a1,c4,e1,f2,g2,f7");
//        map.put("ab_group", "100170");
//        map.put("ab_feature", "94563,102749");
        map.put("abflag", "3");
        map.put("ssmix", "a");
        map.put("device_type", "MI 4LTE");
        map.put("device_brand", "Xiaomi");
        map.put("language", "zh");
        map.put("os_api", "23");
        map.put("os_version", "6.0.1");
//        map.put("uuid", "868568022429671");
//        map.put("openudid", "346349f51618d754");
        map.put("manifest_version_code", "605");
        map.put("resolution", "1080*1920");
        map.put("dpi", "480");
//        map.put("update_version_code", "6055");
//        map.put("_rticket", "1489505026985");
        L.e("TEST  id====》", Utils.getSpUtils().getString("id", "空"));
        return map;
    }

//    8746606365  32147560984
//    8761910149  35452720929
//    8788689555  35468463324

}
