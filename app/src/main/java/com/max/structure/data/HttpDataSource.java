package com.max.structure.data;


import com.max.common.base.BaseResponse;
import com.max.structure.ui.LoginBean;

import java.util.Map;

import io.reactivex.Observable;

public interface HttpDataSource {


    Observable<LoginBean> login(Map<String,String> headers, Map<String,String> params);

    //模拟上拉加载
    Observable<Object> loadMore();

    Observable<BaseResponse<Object>> demoGet();

    Observable<BaseResponse<Object>> demoPost(String catalog);
}
