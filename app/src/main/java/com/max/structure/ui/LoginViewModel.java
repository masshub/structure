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
import com.max.common.utils.MD5Utils;
import com.max.common.utils.PhoneUtils;
import com.max.common.utils.RxUtils;
import com.max.common.utils.ToastUtils;
import com.max.structure.MainActivity;
import com.max.structure.R;
import com.max.structure.data.DemoRepository;
import com.max.structure.service.TestRepository;

import java.util.HashMap;
import java.util.Map;

import butterknife.internal.Utils;
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

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });
    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
            }
        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
//            login();
        }
    });

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

                    }
                }).subscribe(new DisposableObserver<BaseResponse<LoginBean>>() {
            @Override
            public void onNext(BaseResponse<LoginBean> response) {
                // 请求成功

            }

            @Override
            public void onError(Throwable e) {
                // 请求失败

            }

            @Override
            public void onComplete() {
                // 请求完成

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
