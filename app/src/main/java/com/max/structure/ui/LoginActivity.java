package com.max.structure.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProviders;

import com.max.common.base.BaseActivity;
import com.max.common.base.BaseApplication;
import com.max.common.bus.event.ActivityEvent;
import com.max.common.http.BaseApi;
import com.max.common.http.Environment;
import com.max.structure.AppViewModelFactory;
import com.max.structure.R;
import com.trello.rxlifecycle4.LifecycleTransformer;

import io.reactivex.rxjava3.core.Observable;

public class LoginActivity extends BaseActivity<LoginViewModel> {
    private EditText mUserName, mUserPassword;
    private LinearLayout mLogin;


    @Override
    public void initParam() {
        super.initParam();
        try {
            BaseApi.setEnvironment(Environment.PRE_OFFICIAL.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected LoginViewModel getViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    protected void initView() {
        mUserName = findViewById(R.id.et_username);
        mUserPassword = findViewById(R.id.et_password);
        mLogin = findViewById(R.id.ll_login);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.login(mUserName,mUserPassword);

            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }


    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public Observable<ActivityEvent> lifecycle() {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event) {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return null;
    }
}
