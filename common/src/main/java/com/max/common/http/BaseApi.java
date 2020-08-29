package com.max.common.http;

/**
 * Created by Maker on 2020/8/29.
 * Description:
 */
public class BaseApi {
    private  String AGREEMENT_HOST;
    private  String CREDIT_HOST;
    private  String SHOP_HOST;
    private  String BASE_HOST;
    private  String AUTH_HOST;
    private  String BASE_NEW_HOST;
    private  String APP_OAUTH2_CLIENT_ID;
    private  String APP_OAUTH2_CLIENT_SECRET;
    private  String LOG_CAT;
    private  String POINT;
    private  String TCP;
    private  String TIM_SDKAPPID;


    public BaseApi(String environment) {
        CurrentApiConfig(environment);
    }

    public String getAGREEMENT_HOST() {
        return AGREEMENT_HOST;
    }

    public void setAGREEMENT_HOST(String AGREEMENT_HOST) {
        this.AGREEMENT_HOST = AGREEMENT_HOST;
    }

    public String getCREDIT_HOST() {
        return CREDIT_HOST;
    }

    public void setCREDIT_HOST(String CREDIT_HOST) {
        this.CREDIT_HOST = CREDIT_HOST;
    }

    public String getSHOP_HOST() {
        return SHOP_HOST;
    }

    public void setSHOP_HOST(String SHOP_HOST) {
        this.SHOP_HOST = SHOP_HOST;
    }

    public String getBASE_HOST() {
        return BASE_HOST;
    }

    public void setBASE_HOST(String BASE_HOST) {
        this.BASE_HOST = BASE_HOST;
    }

    public String getAUTH_HOST() {
        return AUTH_HOST;
    }

    public void setAUTH_HOST(String AUTH_HOST) {
        this.AUTH_HOST = AUTH_HOST;
    }

    public String getBASE_NEW_HOST() {
        return BASE_NEW_HOST;
    }

    public void setBASE_NEW_HOST(String BASE_NEW_HOST) {
        this.BASE_NEW_HOST = BASE_NEW_HOST;
    }

    public String getAPP_OAUTH2_CLIENT_ID() {
        return APP_OAUTH2_CLIENT_ID;
    }

    public void setAPP_OAUTH2_CLIENT_ID(String APP_OAUTH2_CLIENT_ID) {
        this.APP_OAUTH2_CLIENT_ID = APP_OAUTH2_CLIENT_ID;
    }

    public String getAPP_OAUTH2_CLIENT_SECRET() {
        return APP_OAUTH2_CLIENT_SECRET;
    }

    public void setAPP_OAUTH2_CLIENT_SECRET(String APP_OAUTH2_CLIENT_SECRET) {
        this.APP_OAUTH2_CLIENT_SECRET = APP_OAUTH2_CLIENT_SECRET;
    }

    public String getLOG_CAT() {
        return LOG_CAT;
    }

    public void setLOG_CAT(String LOG_CAT) {
        this.LOG_CAT = LOG_CAT;
    }

    public String getPOINT() {
        return POINT;
    }

    public void setPOINT(String POINT) {
        this.POINT = POINT;
    }

    public String getTCP() {
        return TCP;
    }

    public void setTCP(String TCP) {
        this.TCP = TCP;
    }

    public String getTIM_SDKAPPID() {
        return TIM_SDKAPPID;
    }

    public void setTIM_SDKAPPID(String TIM_SDKAPPID) {
        this.TIM_SDKAPPID = TIM_SDKAPPID;
    }

    private  void CurrentApiConfig(String environment) {
        if (Environment.ALPHA_MAJOR.equals(environment)) {
            // 联调主
            LOG_CAT = "true";
            TIM_SDKAPPID = "1400302381";
            TCP = "tcp-t.zhilunkeji.com:11883";
            POINT = "http://point.zhilun-dvpt.com";
            APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
            BASE_HOST = "http://api.zhilun-integration-major.com/";
            CREDIT_HOST = "http://h5.zhilun-integration-major.com/";
            SHOP_HOST = "http://shop.zhilun-integration-major.com/";
            AUTH_HOST = "http://auth.zhilun-integration-major.com/";
            BASE_NEW_HOST = "http://apin.zhilun-integration-major.com/";
            AGREEMENT_HOST = "http://h5.zhilun-qa-major.com/agreement/";
            APP_OAUTH2_CLIENT_SECRET = "134805dbeb286af9754c539823dbf128";

        } else if (Environment.ALPHA_MINOR.equals(environment)) {
            // 联调次
            LOG_CAT = "true";
            TIM_SDKAPPID = "1400302381";
            TCP = "10.1.80.200:30006";
            POINT = "http://point.zhilun-dvpt.com";
            APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
            BASE_HOST = "http://api.zhilun-integration-minor.com/";
            CREDIT_HOST = "http://h5.zhilun-integration-minor.com/";
            SHOP_HOST = "http://shop.zhilun-integration-minor.com/";
            AUTH_HOST = "http://auth.zhilun-integration-minor.com/";
            BASE_NEW_HOST = "http://apin.zhilun-integration-minor.com/";
            AGREEMENT_HOST = "http://h5.zhilun-qa-major.com/agreement/";
            APP_OAUTH2_CLIENT_SECRET = "134805dbeb286af9754c539823dbf128";

        } else if (Environment.BETA_MAJOR.equals(environment)) {
            // 测试主
            LOG_CAT = "true";
            TIM_SDKAPPID = "1400396489";
            TCP = "10.1.80.200:30756";
            POINT = "http://point.zhilun-dvpt.com";
            APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
            BASE_HOST = "http://api.zhilun-qa-major.com/";
            CREDIT_HOST = "http://h5.zhilun-qa-major.com/";
            SHOP_HOST = "http://shop.zhilun-qa-major.com/";
            AUTH_HOST = "http://auth.zhilun-qa-major.com/";
            BASE_NEW_HOST = "http://apin.zhilun-qa-major.com/";
            AGREEMENT_HOST = "http://h5.zhilun-qa-major.com/agreement/";
            APP_OAUTH2_CLIENT_SECRET = "134805dbeb286af9754c539823dbf128";

        } else if (Environment.BATE_MINOR.equals(environment)) {
            // 测试次
            LOG_CAT = "true";
            TIM_SDKAPPID = "1400396489";
            TCP = "10.1.80.200:30006";
            POINT = "http://point.zhilun-dvpt.com";
            APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
            BASE_HOST = "http://api.zhilun-qa-minor.com/";
            CREDIT_HOST = "http://h5.zhilun-qa-minor.com/";
            SHOP_HOST = "http://shop.zhilun-qa-minor.com/";
            AUTH_HOST = "http://auth.zhilun-qa-minor.com/";
            BASE_NEW_HOST = "http://apin.zhilun-qa-minor.com/";
            AGREEMENT_HOST = "http://h5.zhilun-qa-major.com/agreement/";
            APP_OAUTH2_CLIENT_SECRET = "134805dbeb286af9754c539823dbf128";

        } else if (Environment.PRE_OFFICIAL.equals(environment)) {
            // 准生产
            LOG_CAT = "true";
            TIM_SDKAPPID = "1400400811";
            TCP = "tcp-t.zhilunkeji.com:11883";
            POINT = "http://point.zhilun-dvpt.com";
            APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
            BASE_HOST = "https://api-t.zhilunkeji.com/";
            CREDIT_HOST = "https://h-t.zhilunkeji.com/";
            SHOP_HOST = "https://shop-t.zhilunkeji.com/";
            AUTH_HOST = "https://auth-t.zhilunkeji.com/";
            BASE_NEW_HOST = "https://apin-t.zhilunkeji.com/";
            AGREEMENT_HOST = "https://sh.t.zhilun.com/agreement";
            APP_OAUTH2_CLIENT_SECRET = "134805dbeb286af9754c539823dbf128";

        } else {
            // 生产
            LOG_CAT = "false";
            TIM_SDKAPPID = "1400302381";
            TCP = "tcp.zhilunkeji.com:1883";
            POINT = "https://point.zhilun.com";
            APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
            BASE_HOST = "https://api.zhilunkeji.com/";
            CREDIT_HOST = "https://h.zhilunkeji.com/";
            SHOP_HOST = "https://shop.zhilun.co/";
            AUTH_HOST = "https://auth.zhilunkeji.com/";
            BASE_NEW_HOST = "https://apin.zhilunkeji.com/";
            AGREEMENT_HOST = "https://sh.zhilun.com/agreement";
            APP_OAUTH2_CLIENT_SECRET = "58683fe8ee604f7b919ac34a1f25d8e2";

        }


    }


}
