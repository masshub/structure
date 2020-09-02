package com.max.structure.data;

import com.max.common.base.BaseResponse;
import com.max.structure.service.DemoApiService;
import com.max.structure.service.TestApiService;
import com.max.structure.ui.LoginBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class HttpDataSourceImpl implements HttpDataSource{
    private DemoApiService apiService;
    private volatile static HttpDataSourceImpl instance = null;

    public HttpDataSourceImpl(DemoApiService service) {
        this.apiService = service;
    }

    public static HttpDataSourceImpl getInstance(DemoApiService service) {
        if(instance == null){
            synchronized (HttpDataSourceImpl.class){
                if(instance == null){
                    instance = new HttpDataSourceImpl(service);
                }
            }
        }
        return instance;
    }

    @Override
    public Observable<BaseResponse<LoginBean>> login(Map<String,String> headers,Map<String,String> params) {
        return apiService.login(headers,params);
    }

    @Override
    public Observable<Object> loadMore() {
        return null;
    }

    @Override
    public Observable<BaseResponse<Object>> demoGet() {
        return null;
    }

    @Override
    public Observable<BaseResponse<Object>> demoPost(String catalog) {
        return null;
    }


}
