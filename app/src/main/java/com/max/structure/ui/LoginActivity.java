package com.max.structure.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProviders;

import com.max.common.base.BaseActivity;
import com.max.common.http.BaseApi;
import com.max.common.http.Environment;
import com.max.structure.AppViewModelFactory;
import com.max.structure.R;


public class LoginActivity extends BaseActivity<LoginViewModel> {
    private EditText mUserName, mUserPassword;
    private Button mLogin;


    @Override
    public int getContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

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
    public LoginViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    protected void initView() {
        mUserName = findViewById(R.id.et_username);
        mUserPassword = findViewById(R.id.et_password);
        mLogin = findViewById(R.id.bt_login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.login(mUserName,mUserPassword);

            }
        });

    }



}
