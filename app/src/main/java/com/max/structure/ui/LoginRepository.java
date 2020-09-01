package com.max.structure.ui;

import android.content.Context;

import com.max.common.base.m.MBaseRepository;
import com.max.common.base.m.ReqTag;
import com.max.common.http.RetrofitClient;
import com.max.structure.service.TestApiService;

import io.reactivex.Observable;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public class LoginRepository extends MBaseRepository<LoginBean> {

    public LoginRepository(Context context) {
        super(context);
    }

    @Override
    public Observable<LoginBean> getApiService() {
        return (Observable<LoginBean>) RetrofitClient.getInstance().create(TestApiService.class);
    }

    @Override
    public void responseSuccess(ReqTag reqTag, LoginBean response) {

    }
}
