package com.max.structure.ui;

import android.os.Bundle;

import com.max.common.base.BaseActivity;
import com.max.common.bus.event.ActivityEvent;
import com.max.structure.BR;
import com.max.structure.R;
import com.max.structure.databinding.ActivityLoginBinding;
import com.trello.rxlifecycle4.LifecycleTransformer;

import io.reactivex.rxjava3.core.Observable;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
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
