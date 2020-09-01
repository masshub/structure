package com.max.common.base.m;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public abstract class MBaseActivity<T extends MBaseViewModel> extends AppCompatActivity {
    protected T viewModel;

    public MBaseActivity() {
        viewModel = getViewModel();
    }

    protected abstract T getViewModel();

}
