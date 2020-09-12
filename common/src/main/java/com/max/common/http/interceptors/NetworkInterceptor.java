package com.max.common.http.interceptors;

import com.google.gson.Gson;
import com.max.common.base.BaseResponse;
import com.max.custom.toast.Toasty;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.HttpException;

/**
 * Created by Maker on 2020/9/11.
 * Description:
 */
public class NetworkInterceptor implements Interceptor {

    private Object HttpException;

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain){
        Request request = chain.request();

        Response response = null;
        try {
            response = chain.proceed(request);

//            if(response!= null) {
//                Buffer buffer = response.networkResponse().body().source().getBuffer().clone();
//                String json = buffer.toString();
//                BaseResponse baseResponse = new Gson().fromJson(json, BaseResponse.class);
//                if(!baseResponse.isSuccess()){
//                    Toasty.error(baseResponse.getMessage());
//                }
//
//            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return response;
    }
}
