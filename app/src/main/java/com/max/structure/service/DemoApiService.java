package com.max.structure.service;

import com.max.common.base.BaseResponse;
import com.max.structure.ui.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
}
