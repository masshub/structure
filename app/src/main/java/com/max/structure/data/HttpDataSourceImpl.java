package com.max.structure.data;

import com.max.common.base.BaseResponse;
import com.max.structure.service.TestApiService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class HttpDataSourceImpl implements HttpDataSource{
    private TestApiService apiService;
    private volatile static HttpDataSourceImpl instance = null;

    public HttpDataSourceImpl(TestApiService service) {
        this.apiService = service;
    }

    public static HttpDataSourceImpl getInstance(TestApiService service) {
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
    public Observable<BaseResponse<String>> login() {
//        return Observable.just(new Object()).delay(3, TimeUnit.SECONDS);
        return apiService.demoGet();
    }






}
