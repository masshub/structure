package com.max.structure;

import com.max.common.base.BaseApplication;
import com.max.common.http.HttpClient;
import com.max.custom.toast.Toasty;
import com.max.network.RxHttpUtils;
import com.max.structure.data.repository.DataRepository;
import com.max.structure.persistence.AppDataBase;


/**
 * Created by Maker on 2020/8/26.
 * Description:
 */
public class App extends BaseApplication {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();

        Toasty.init(this);

    }

    private void initHttp() {
        //一个项目多url的配置方法
//        RxUrlManager.getInstance().setMultipleUrl(AppUrlConfig.getAllUrl());

        RxHttpUtils
                .getInstance()
                .init(this)
                .config()
                //自定义factory的用法
                //.setCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.setConverterFactory(ScalarsConverterFactory.create(),GsonConverterFactory.create(GsonAdapter.buildGson()))
                //配置全局baseUrl
                .setBaseUrl("https://www.wanandroid.com/")
                //开启全局配置
                .setOkClient(HttpClient.getInstance(null));

    }

    public AppDataBase getDatabase() {
        return AppDataBase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
