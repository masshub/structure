package com.max.structure;

import com.max.common.http.RetrofitClient;
import com.max.structure.data.DemoRepository;
import com.max.structure.data.HttpDataSource;
import com.max.structure.data.HttpDataSourceImpl;
import com.max.structure.data.LocalDataSource;
import com.max.structure.data.LocalDataSourceImpl;
import com.max.structure.service.ApiService;

/**
 * Created by Maker on 2020/9/1.
 * Description:
 */
public class Injection {
    public static DemoRepository provideDemoRepository() {
        //网络API服务
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return DemoRepository.getInstance(httpDataSource, localDataSource);
    }
}
