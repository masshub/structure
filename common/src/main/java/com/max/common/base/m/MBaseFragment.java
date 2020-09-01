package com.max.common.base.m;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.max.common.base.BaseViewModel;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public abstract class MBaseFragment <T extends MBaseViewModel> extends Fragment {

    protected T viewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (viewModel == null) {
            viewModel = getViewModel();
        }
    }

    protected abstract T getViewModel();
}
