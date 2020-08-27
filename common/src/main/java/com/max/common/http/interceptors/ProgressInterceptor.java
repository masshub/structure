package com.max.common.http.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Maker on 2020/8/27.
 * Description:
 */
public class ProgressInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
//                .body(new ProgressResponseBody(originalResponse.body()))
                .build();
    }
}
