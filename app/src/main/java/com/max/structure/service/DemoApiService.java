package com.max.structure.service;

import com.max.common.base.BaseResponse;
import com.max.common.http.BaseApi;
import com.max.structure.ui.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public interface DemoApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<LoginBean>> demoGet();

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    Observable<BaseResponse<LoginBean>> demoPost(@Field("catalog") String catalog);

    @FormUrlEncoded
    @POST(value = BaseApi.AUTH_HOST + "oauth/token")
    Observable<LoginBean> login(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> params);
}
