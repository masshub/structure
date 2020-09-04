package com.max.structure;

import com.max.common.App;
import com.max.structure.data.repository.DataRepository;
import com.max.structure.persistence.AppDataBase;

/**
 * Created by Maker on 2020/8/26.
 * Description:
 */
class Application extends App {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppDataBase getDatabase() {
        return AppDataBase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
