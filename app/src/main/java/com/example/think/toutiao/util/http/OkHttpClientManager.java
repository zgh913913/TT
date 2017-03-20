package com.example.think.toutiao.util.http;

import com.example.think.toutiao.util.common.SizeUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.HeaderMap;


public class OkHttpClientManager {
    private static OkHttpClient sInstance;
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", String.format("max-age=%d", 60)).build();
        }
    };
    static Map<String, String> headerMap;

    @SuppressWarnings("ConstantConditions")
    public static OkHttpClient getInstance() {
        synchronized (OkHttpClientManager.class) {
            if (sInstance == null) {
                SSLSocketFactory sslSocketFactory = null;
                //不读取证书了,信任所有证书

                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        return true;
                    }
                };
                SSLContext sc = null;
                try {
                    sc = SSLContext.getInstance("SSL");
                    sc.init(null, new TrustManager[]{new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }}, new SecureRandom());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.addInterceptor(new HeaderInterceptor())
                        .addInterceptor(new LoggingInterceptor())
                        .retryOnConnectionFailure(true).connectTimeout(15, TimeUnit.SECONDS)
                        .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
                builder.sslSocketFactory(sc.getSocketFactory()).hostnameVerifier(hv);
                sInstance = builder.build();
            }
        }
        return sInstance;
    }


    /**
     * see https://github.com/square/okhttp/wiki/Interceptors
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            String token = "";
            long timestamp = System.currentTimeMillis();
            timestamp = timestamp / 1000;
            HashMap<String, Object> map = new HashMap<String, Object>();
            JSONObject s = new JSONObject(map);
            String versioncode = "";
            try {
                token = "";
            } catch (Exception e) {
                e.printStackTrace();
            }
            Request request = original.newBuilder()
                    .header("token", token)
                    .header("app-version", versioncode)
                    .method(original.method(), original.body()).build();

            Response response = chain.proceed(request);
            return response;
        }
    }

    static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Headers headers = Headers.of(
                    "User-Agent", "Dalvik/2.1.0 (Linux; U; Android 6.0.1; MI 4LTE MIUI/7.1.5) NewsArticle/6.0.4 okhttp/3.4.1",
                    "X-SS-REQ-TICKET", "1489373479573");
            Request request = original.newBuilder()
                    .headers(headers)
//                    .addHeader("Cookie", "uuid=868568022429671")
                    .method(original.method(), original.body()).build();
            Response response = chain.proceed(request);
            return response;
        }
    }
}
