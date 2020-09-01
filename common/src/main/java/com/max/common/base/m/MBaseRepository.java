package com.max.common.base.m;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.RequestManager;

import io.reactivex.Observable;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public abstract class MBaseRepository<T> {

    protected Context context;
    //
    protected final MutableLiveData<T> liveData;

    public MBaseRepository(Context context) {
        this.context = context;
        liveData = new MutableLiveData<>();
    }

    /**
     * 请求数据
     */
    public void loadData() {
//        RequestManager.get().async(
//                getReqTag(),
//                getApiService(),
//                new IResponseCallback<T>() {
//                    @Override
//                    public void onSuccess(ReqTag reqTag, T response) {
//                        responseSuccess(reqTag, response);
//                    }
//
//                    @Override
//                    public void onError(ReqTag reqTag, ErrorBean errorBean) {
//                        responseError(reqTag, errorBean);
//                    }
//                }
//        );
    }

    /**
     * 获取数据
     */
    public MutableLiveData<T> getData() {
        return liveData;
    }

    protected ReqTag getReqTag() {
        return null;
    }

    public abstract Observable<T> getApiService();

    public abstract void responseSuccess(ReqTag reqTag, T response);

//    public abstract void responseError(ReqTag reqTag, ErrorBean errorBean);

}
