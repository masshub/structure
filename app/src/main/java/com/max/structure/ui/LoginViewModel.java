package com.max.structure.ui;

import android.app.Application;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.max.common.App;
import com.max.common.base.BaseResponse;
import com.max.common.base.BaseViewModel;
import com.max.common.binding.command.BindingAction;
import com.max.common.binding.command.BindingCommand;
import com.max.common.binding.command.BindingConsumer;
import com.max.common.bus.event.SingleLiveEvent;
import com.max.common.http.BaseApi;
import com.max.common.utils.LogUtil;
import com.max.common.utils.MD5Utils;
import com.max.common.utils.PhoneUtils;
import com.max.common.utils.RxUtils;
import com.max.common.utils.ToastUtils;
import com.max.structure.data.DemoRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

public class LoginViewModel extends BaseViewModel<DemoRepository> {
    private DemoRepository mRepository;
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
        mRepository = repository;
        //从本地取得数据绑定到View层
        userName.set(model.getUserName());
        password.set(model.getPassword());
    }


    /**
     * 网络模拟一个登陆操作
     **/
    public void login(EditText userName,EditText password) {
        if (TextUtils.isEmpty(userName.getText().toString().trim())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }

        Map<String, String> header = new HashMap<>();
        header.put("Authorization",
                "Basic " + Base64.encodeToString((BaseApi.APP_OAUTH2_CLIENT_ID + ":" + BaseApi.APP_OAUTH2_CLIENT_SECRET).getBytes(), Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING));
        Map<String, String> params = new HashMap<>();
        params.put("mobile", userName.getText().toString().trim());
        params.put("grant_type","store_password");
        params.put("deviceId", PhoneUtils.getDeviceId(App.mApp));
        params.put("password", MD5Utils.getMD5(password.getText().toString().trim()));


        model.login(header, params)
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(this)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        // 请求开始
                        LogUtil.d("请求开始");

                    }
                }).subscribe(new DisposableObserver<LoginBean>() {
            @Override
            public void onNext(LoginBean response) {
                // 请求成功
                LogUtil.d("请求成功");
                LogUtil.d(response.getAccess_token());
                LogUtil.d(response.getAppType());
                LogUtil.d(response.getExpires_in());
                LogUtil.d(response.getRefresh_token());
                LogUtil.d(response.getTelephone());
                LogUtil.d(response.getUserId());

            }

            @Override
            public void onError(Throwable e) {
                // 请求失败
                LogUtil.d("请求失败");

            }

            @Override
            public void onComplete() {
                // 请求完成
                LogUtil.d("请求完成");

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
