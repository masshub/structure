package com.max.common.http;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.max.common.App;
import com.max.common.constant.GlobalConfig;
import com.max.common.utils.AppUtils;
import com.max.common.utils.PhoneUtils;
import com.max.common.utils.StringUtils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.*;

/**
 * Created by Maker on 2020/8/27.
 * Description:
 */
public class RetrofitClient {
    private static Retrofit retrofit;
    public static String baseUrl = GlobalConfig.baseUrl;
    private List<DateFormat> mDateFormatList = new ArrayList<>();
    private final String[] possibleDateFormats = {
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    };


    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient() {
        this(baseUrl, null);
    }


    @SuppressLint("SimpleDateFormat")
    private RetrofitClient(String url, Map<String, String> headers) {

        Map<String,String> header = new HashMap<>();

        if (url.contains("token/evict") || url.contains("sms/code") || url.contains("mars-serving-web/api/setting/app")) {
            //退出登陆 发送验证码  不需要head
            headers.put("deviceId", PhoneUtils.getDeviceId(App.mApp));
            headers.put("versionCode", "" + AppUtils.getVersionCode(App.mApp));
            headers.put("remoteip",AppUtils.getLocalIpV4Address());
            headers.put("Connection","close");
            headers.put("districtId","334128");
            headers.put("Authorization","334128");
        } else {
            if (StringUtils.isBlank(header.get("Authorization"))) {
                headers.put("Authorization","334128");
            }
        }


        mDateFormatList.clear();
        for (String format : possibleDateFormats) {
            mDateFormatList.add(new SimpleDateFormat(format));
        }


        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(HttpClient.getInstance(headers))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .serializeNulls()
                                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>(

                                ) {
                                    @Override
                                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        if (TextUtils.isEmpty(json.getAsString().trim())) {
                                            return null;
                                        }
                                        for (DateFormat simpleDateFormat : mDateFormatList) {
                                            try {
                                                return simpleDateFormat.parse(json.getAsString());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        return null;
                                    }
                                })
                                .create()
                ))
                .build();
    }



    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */
//
//    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(mainThread())
//                .subscribe(subscriber);
//
//        return null;
//    }

}
