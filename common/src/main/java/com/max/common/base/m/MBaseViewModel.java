package com.max.common.base.m;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public abstract class MBaseViewModel<T extends MBaseRepository> extends AndroidViewModel {

    protected Context context;

    protected T repository;

    public MBaseViewModel(@NonNull Application application) {
        super(application);
        context = application;
        repository = getRepository();
    }

    protected abstract T getRepository();
}
