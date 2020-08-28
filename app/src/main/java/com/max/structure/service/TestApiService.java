package com.max.structure.service;

import com.max.common.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public interface TestApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<String>> demoGet();

}
