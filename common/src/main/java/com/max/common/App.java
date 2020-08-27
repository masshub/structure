package com.max.common;

import androidx.multidex.MultiDexApplication;

/**
 * Created by Maker on 2020/8/27.
 * Description:
 */
public class App extends MultiDexApplication {

    public static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

}
