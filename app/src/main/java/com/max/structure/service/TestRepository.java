package com.max.structure.service;

import com.max.common.http.RetrofitClient;

/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public class TestRepository  {

    TestApiService apiService = RetrofitClient.getInstance().create(TestApiService.class);




}
