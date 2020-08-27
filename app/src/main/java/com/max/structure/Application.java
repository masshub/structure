package com.max.structure;

import com.max.common.App;

/**
 * Created by Maker on 2020/8/26.
 * Description:
 */
class Application extends App {
    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
