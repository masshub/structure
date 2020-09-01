package com.max.structure.data;


import com.max.common.base.BaseResponse;
import com.max.structure.ui.LoginBean;

import io.reactivex.Observable;

public interface HttpDataSource {


    Observable<BaseResponse<LoginBean>> login();

    //模拟上拉加载
    Observable<Object> loadMore();

    Observable<BaseResponse<Object>> demoGet();

    Observable<BaseResponse<Object>> demoPost(String catalog);
}
