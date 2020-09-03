package com.max.common.http;

import com.max.common.utils.SPUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Maker on 2020/8/29.
 * Description:
 */
public class BaseApi {
    public static final String AGREEMENT_HOST = "";
    public static final String CREDIT_HOST = "";
    public static final String SHOP_HOST = "";
    public static final String BASE_HOST ="https://api-t.zhilunkeji.com/";
    public static final String AUTH_HOST = "https://auth-t.zhilunkeji.com/";
    public static final String BASE_NEW_HOST = "";
    public static final String APP_OAUTH2_CLIENT_ID = "MOBILE_SERVICE_CAR";
    public static final String APP_OAUTH2_CLIENT_SECRET = "134805dbeb286af9754c539823dbf128";
    public static final String LOG_CAT = "";
    public static final String POINT = "";
    public static final String TCP = "";
    public static final String TIM_SDKAPPID = "";
    private static String mEnvironment;


    public String getEnvironment() {
        return mEnvironment;
    }

    public static void setEnvironment(String environment) throws Exception {
        mEnvironment = environment;
        SPUtils.getInstance().put("environment",environment);
        currentApiConfig(environment);
    }

    public static String getAgreementHost() {
        return AGREEMENT_HOST;
    }

    public static String getCreditHost() {
        return CREDIT_HOST;
    }

    public static String getShopHost() {
        return SHOP_HOST;
    }

    public static String getBaseHost() {
        return BASE_HOST;
    }

    public static String getAuthHost() {
        return AUTH_HOST;
    }

    public static String getBaseNewHost() {
        return BASE_NEW_HOST;
    }

    public static String getAppOauth2ClientId() {
        return APP_OAUTH2_CLIENT_ID;
    }

    public static String getAppOauth2ClientSecret() {
        return APP_OAUTH2_CLIENT_SECRET;
    }

    public static String getLogCat() {
        return LOG_CAT;
    }

    public static String getPOINT() {
        return POINT;
    }

    public static String getTCP() {
        return TCP;
    }

    public static String getTimSdkappid() {
        return TIM_SDKAPPID;
    }

    public static String getmEnvironment() {
        return mEnvironment;
    }

    //    public BaseApi(String environment) {
//        currentApiConfig(environment);
//    }
//
//    public BaseApi() {
//    }


    private static void currentApiConfig(String environment) throws Exception {
        if (Environment.ALPHA_MAJOR.equals(environment)) {
            // 联调主
            modify("LOG_CAT",true);
            modify("TIM_SDKAPPID","1400302381");
            modify("TCP","tcp-t.zhilunkeji.com:11883");
            modify("POINT","http://point.zhilun-dvpt.com");
            modify("BASE_HOST","http://api.zhilun-integration-major.com/");
            modify("CREDIT_HOST","http://h5.zhilun-integration-major.com/");
            modify("SHOP_HOST","http://shop.zhilun-integration-major.com/");
            modify("AUTH_HOST","http://auth.zhilun-integration-major.com/");
            modify("BASE_NEW_HOST","http://apin.zhilun-integration-major.com/");
            modify("AGREEMENT_HOST","http://h5.zhilun-qa-major.com/agreement/");
            modify("APP_OAUTH2_CLIENT_SECRET","134805dbeb286af9754c539823dbf128");


        } else if (Environment.ALPHA_MINOR.equals(environment)) {
            // 联调次
            modify("LOG_CAT",true);
            modify("TIM_SDKAPPID","1400302381");
            modify("TCP","10.1.80.200:30006");
            modify("POINT","http://point.zhilun-dvpt.com");
            modify("BASE_HOST","http://api.zhilun-integration-minor.com/");
            modify("CREDIT_HOST","http://h5.zhilun-integration-minor.com/");
            modify("SHOP_HOST","http://shop.zhilun-integration-minor.com/");
            modify("AUTH_HOST","http://auth.zhilun-integration-minor.com/");
            modify("BASE_NEW_HOST","http://apin.zhilun-integration-minor.com/");
            modify("AGREEMENT_HOST","http://h5.zhilun-qa-minor.com/agreement/");
            modify("APP_OAUTH2_CLIENT_SECRET","134805dbeb286af9754c539823dbf128");


        } else if (Environment.BETA_MAJOR.equals(environment)) {
            // 测试主

            modify("LOG_CAT",true);
            modify("TIM_SDKAPPID","1400396489");
            modify("TCP","10.1.80.200:30756");
            modify("POINT","http://point.zhilun-dvpt.com");
            modify("BASE_HOST","http://api.zhilun-qa-major.com/");
            modify("CREDIT_HOST","http://h5.zhilun-qa-major.com/");
            modify("SHOP_HOST","http://shop.zhilun-qa-major.com/");
            modify("AUTH_HOST","http://auth.zhilun-qa-major.com/");
            modify("BASE_NEW_HOST","http://apin.zhilun-qa-major.com/");
            modify("AGREEMENT_HOST","http://h5.zhilun-qa-major.com/agreement/");
            modify("APP_OAUTH2_CLIENT_SECRET","134805dbeb286af9754c539823dbf128");


        } else if (Environment.BATE_MINOR.equals(environment)) {

            // 测试次
            modify("LOG_CAT",true);
            modify("TIM_SDKAPPID","1400396489");
            modify("TCP","10.1.80.200:30756");
            modify("POINT","http://point.zhilun-dvpt.com");
            modify("BASE_HOST","http://api.zhilun-qa-minor.com/");
            modify("CREDIT_HOST","http://h5.zhilun-qa-minor.com/");
            modify("SHOP_HOST","http://shop.zhilun-qa-minor.com/");
            modify("AUTH_HOST","http://auth.zhilun-qa-minor.com/");
            modify("BASE_NEW_HOST","http://apin.zhilun-qa-minor.com/");
            modify("AGREEMENT_HOST","http://h5.zhilun-qa-minor.com/agreement/");
            modify("APP_OAUTH2_CLIENT_SECRET","134805dbeb286af9754c539823dbf128");


        } else if (Environment.PRE_OFFICIAL.toString().equals(environment)) {

            // 准生产
            modify(LOG_CAT,"true");
            modify(TIM_SDKAPPID,"1400400811");
            modify(TCP,"tcp-t.zhilunkeji.com:11883");
            modify(POINT,"http://point.zhilun-dvpt.com");
            modify(BASE_HOST,"https://api-t.zhilunkeji.com/");
            modify(CREDIT_HOST,"https://sh-t.zhilunkeji.com/");
            modify(SHOP_HOST,"https://shop-t.zhilunkeji.com/");
            modify(AUTH_HOST,"https://auth-t.zhilunkeji.com/");
            modify(BASE_NEW_HOST,"https://apin-t.zhilunkeji.com/");
            modify(AGREEMENT_HOST,"https://sh.t.zhilun.com/agreement/");
            modify(APP_OAUTH2_CLIENT_SECRET,"134805dbeb286af9754c539823dbf128");

        } else {
            // 生产

            modify("LOG_CAT",false);
            modify("TIM_SDKAPPID","1400302381");
            modify("TCP","tcp.zhilunkeji.com:1883");
            modify("POINT","https://point.zhilun.com");
            modify("BASE_HOST","https://api.zhilunkeji.com/");
            modify("CREDIT_HOST","https://sh.zhilun.com/");
            modify("SHOP_HOST","https://shop.zhilun.co/");
            modify("AUTH_HOST","https://auth.zhilunkeji.com/");
            modify("BASE_NEW_HOST","https://apin.zhilunkeji.com/");
            modify("AGREEMENT_HOST","https://sh.zhilun.com/agreement");
            modify("APP_OAUTH2_CLIENT_SECRET","58683fe8ee604f7b919ac34a1f25d8e2");

        }


    }

    public static void modify(String fieldName, Object newFieldValue) throws Exception {
        BaseApi baseApi = new BaseApi();

        Field field = BaseApi.class.getDeclaredField("LOG_CAT");
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true); //Field 的 modifiers 是私有的
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        if(!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(baseApi, newFieldValue);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }


}
