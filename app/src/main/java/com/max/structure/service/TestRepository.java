package com.max.structure.service;

import androidx.annotation.NonNull;

import com.max.common.base.BaseModel;
import com.max.common.base.BaseResponse;
import com.max.common.http.RetrofitClient;
import com.max.structure.data.HttpDataSource;
import com.max.structure.data.LocalDataSource;

import io.reactivex.Observable;

/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public class TestRepository extends BaseModel implements HttpDataSource, LocalDataSource {

    private volatile static TestRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    private TestRepository(@NonNull HttpDataSource httpDataSource,
                           @NonNull LocalDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static TestRepository getInstance(HttpDataSource httpDataSource,
                                             LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (TestRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TestRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public Observable<BaseResponse<String>> login() {
        return mHttpDataSource.login();
    }

    @Override
    public void saveUserName(String userName) {
        mLocalDataSource.saveUserName(userName);

    }

    @Override
    public void savePassword(String password) {
        mLocalDataSource.savePassword(password);

    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }
}
