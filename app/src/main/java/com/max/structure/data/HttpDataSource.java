package com.max.structure.data;


import com.max.common.base.BaseResponse;

import io.reactivex.Observable;

public interface HttpDataSource {


    Observable<BaseResponse<String>> login();
}
