package com.max.structure.data;

import com.max.common.base.BaseResponse;
import com.max.structure.service.ApiService;
import com.max.structure.ui.login.LoginBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;


public class HttpDataSourceImpl implements HttpDataSource{
    private ApiService apiService;
    private volatile static HttpDataSourceImpl instance = null;

    public HttpDataSourceImpl(ApiService service) {
        this.apiService = service;
    }

    public static HttpDataSourceImpl getInstance(ApiService service) {
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
    public Observable<LoginBean> login(Map<String,String> headers, Map<String,String> params) {
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
