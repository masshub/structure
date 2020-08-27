package com.max.common.http;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.max.common.constant.GlobalConfig;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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


}
