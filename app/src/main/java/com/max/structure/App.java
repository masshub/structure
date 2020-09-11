package com.max.structure;

import com.max.common.base.BaseApplication;
import com.max.custom.toast.Toasty;
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

    public AppDataBase getDatabase() {
        return AppDataBase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
