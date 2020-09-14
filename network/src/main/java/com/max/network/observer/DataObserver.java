package com.max.network.observer;

import android.text.TextUtils;

import com.max.network.base.BaseObserver;
import com.max.network.bean.BaseData;
import com.max.network.utils.ToastUtils;

import io.reactivex.rxjava3.disposables.Disposable;


public abstract class DataObserver<T> extends BaseObserver<BaseData<T>> {

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param data 结果
     */
    protected abstract void onSuccess(T data);

    @Override
    public void doOnSubscribe(Disposable d) {
    }

    @Override
    public void doOnError(String errorMsg) {
        if (!isHideToast() && !TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(BaseData<T> data) {
        onSuccess(data.getData());
        //可以根据需求对code统一处理
//        switch (data.getCode()) {
//            case 200:
//                onSuccess(data.getData());
//                break;
//            case 300:
//            case 500:
//                onError(data.getMsg());
//                break;
//            default:
//        }
    }

    @Override
    public void doOnCompleted() {
    }


}
