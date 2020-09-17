package com.max.structure.api;

import com.max.common.http.Environment;
import com.max.network.RxHttpUtils;
import com.max.structure.service.ApiService;

/**
 * Created by Maker on 2020/9/17.
 * Description:
 */
public class ApiHelper {

    public static ApiService getAlphaMajorApi() {
        return RxHttpUtils.createApi(Environment.ALPHA_MAJOR.toString(), AppUrlConfig.getBaseHost(Environment.ALPHA_MAJOR), ApiService.class);
    }

    public static ApiService getAlphaMinorApi() {
        return RxHttpUtils.createApi(Environment.ALPHA_MINOR.toString(), AppUrlConfig.getBaseHost(Environment.ALPHA_MINOR), ApiService.class);
    }

    public static ApiService getBetaMajorApi() {
        return RxHttpUtils.createApi(Environment.BETA_MAJOR.toString(), AppUrlConfig.getBaseHost(Environment.BETA_MAJOR), ApiService.class);
    }

    public static ApiService getBetaMinor() {
        return RxHttpUtils.createApi(Environment.BATE_MINOR.toString(), AppUrlConfig.getBaseHost(Environment.BATE_MINOR), ApiService.class);
    }

    public static ApiService getPreOfficial() {
        return RxHttpUtils.createApi(Environment.PRE_OFFICIAL.toString(), AppUrlConfig.getBaseHost(Environment.PRE_OFFICIAL), ApiService.class);
    }

    public static ApiService getOfficialApi() {
        return RxHttpUtils.createApi(Environment.OFFICIAL.toString(), AppUrlConfig.getBaseHost(Environment.OFFICIAL), ApiService.class);
    }

}
