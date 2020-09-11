package com.max.common.http;

import android.content.Context;

import com.max.common.base.BaseApplication;
import com.max.common.constant.GlobalConfig;
import com.max.common.http.cookie.CookieJarImpl;
import com.max.common.http.cookie.PersistentCookieStore;
import com.max.common.http.interceptors.BaseInterceptor;
import com.max.common.http.interceptors.CacheInterceptor;
import com.max.common.http.interceptors.NetworkInterceptor;
import com.max.common.http.logging.Level;
import com.max.common.http.logging.LoggingInterceptor;
import com.max.common.http.utils.HttpsUtil;
import com.max.common.utils.LogUtil;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.platform.Platform;


/**
 * Created by Maker on 2020/8/27.
 * Description:
 */
public class HttpClient {
    private static Context mContext = BaseApplication.getInstance();
    private static final int DEFAULT_TIMEOUT = 20;
    private static final int CACHE_TIMEOUT = 64 * 1024 * 1024;
    private static Cache cache = null;
    private static File httpCacheDirectory;
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance(Map<String, String> headers) {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "http_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            LogUtil.e("Could not create http cache", e);
        }


        HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory();

        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
                .cache(cache)
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new CacheInterceptor(mContext))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(GlobalConfig.isDebug) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response")// Response的Tag
                        .addHeader("log-header", "The log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                        .build()
                )
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .hostnameVerifier((hostname, session) -> true)
                .build();


        return okHttpClient;

    }
}
