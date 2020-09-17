package com.max.structure.api;

import com.max.common.http.Environment;
import com.max.common.utils.SPUtils;

/**
 * Created by Maker on 2020/9/17.
 * Description:
 */
public class AppUrlConfig {
    private static String alphaMajorBaseHost = "http://api.zhilun-integration-major.com/";
    private static String alphaMinorBaseHost = "http://api.zhilun-integration-minor.com/";
    private static String betaMajorBaseHost = "http://api.zhilun-qa-major.com/";
    private static String betaMinorBaseHost = "http://api.zhilun-qa-minor.com/";
    private static String preOfficialBaseHost = "https://api-t.zhilunkeji.com/";
    private static String officialBaseHost = "https://api.zhilunkeji.com/";

    private static String newAlphaMajorBaseHost = "http://apin.zhilun-integration-major.com/";
    private static String newAlphaMinorBaseHost = "http://apin.zhilun-integration-minor.com/";
    private static String newBetaMajorBaseHost = "http://apin.zhilun-qa-major.com/";
    private static String newBetaMinorBaseHost = "http://apin.zhilun-qa-minor.com/";
    private static String newPreOfficialBaseHost = "https://apin-t.zhilunkeji.com/";
    private static String newOfficialBaseHost = "https://apin.zhilunkeji.com/";

    private static String alphaMajorAuthHost = "http://auth.zhilun-integration-major.com/";
    private static String alphaMinorAuthHost = "http://auth.zhilun-integration-minor.com/";
    private static String betaMajorAuthHost = "http://auth.zhilun-qa-major.com/";
    private static String betaMinorAuthHost = "http://auth.zhilun-qa-minor.com/";
    private static String preOfficialAuthHost = "https://auth-t.zhilunkeji.com/";
    private static String officialAuthHost = "https://auth.zhilunkeji.com/";

    private static String alphaMajorShopHost = "http://shop.zhilun-integration-major.com/";
    private static String alphaMinorShopHost = "http://shop.zhilun-integration-minor.com/";
    private static String betaMajorShopHost = "http://shop.zhilun-qa-major.com/";
    private static String betaMinorShopHost = "http://shop.zhilun-qa-minor.com/";
    private static String preOfficialShopHost = "https://shop-t.zhilunkeji.com/";
    private static String officialShopHost = "https://shop.zhilun.co/";


    private static String alphaMajorCreditHost = "http://h5.zhilun-integration-major.com/";
    private static String alphaMinorCreditHost = "http://h5.zhilun-integration-minor.com/";
    private static String betaMajorCreditHost = "http://h5.zhilun-qa-major.com/";
    private static String betaMinorCreditHost = "http://h5.zhilun-qa-minor.com/";
    private static String preOfficialCreditHost = "https://sh-t.zhilunkeji.com/";
    private static String officialCreditHost = "https://sh.zhilun.com/";

    private static String alphaMajorPointHost = "http://point.zhilun-dvpt.com/";
    private static String alphaMinorPointHost = "http://point.zhilun-dvpt.com/";
    private static String betaMajorPointHost = "http://point.zhilun-dvpt.com/";
    private static String betaMinorPointHost = "http://point.zhilun-dvpt.com/";
    private static String preOfficialPointHost = "http://point.zhilun-dvpt.com/";
    private static String officialPointHost = "https://point.zhilun.com/";

    private static String alphaMajorAgreementHost = "https://sh.t.zhilun.com/agreement";
    private static String alphaMinorAgreementHost = "https://sh.t.zhilun.com/agreement";
    private static String betaMajorAgreementHost = "https://sh.t.zhilun.com/agreement";
    private static String betaMinorAgreementHost = "https://sh.t.zhilun.com/agreement";
    private static String preOfficialAgreementHost = "https://sh.t.zhilun.com/agreement";
    private static String officialAgreementHost = "https://sh.zhilun.com/agreement";

    private static String alphaMajorTcp = "tcp-t.zhilunkeji.com:11883";
    private static String alphaMinorTcp = "10.1.80.200:30006";
    private static String betaMajorTcp = "10.1.80.200:30756";
    private static String betaMinorTcp = "10.1.80.200:30756";
    private static String preOfficialTcp = "tcp-t.zhilunkeji.com:11883";
    private static String officialTcp = "tcp.zhilunkeji.com:1883";

    private static String alphaMajorSecret = "134805dbeb286af9754c539823dbf128";
    private static String alphaMinorSecret = "134805dbeb286af9754c539823dbf128";
    private static String betaMajorSecret = "134805dbeb286af9754c539823dbf128";
    private static String betaMinorSecret = "134805dbeb286af9754c539823dbf128";
    private static String preOfficialSecret = "134805dbeb286af9754c539823dbf128";
    private static String officialSecret = "58683fe8ee604f7b919ac34a1f25d8e2";

    private static String alphaMajorTimSdkAppId = "1400302381";
    private static String alphaMinorTimSdkAppId = "1400302381";
    private static String betaMajorTimSdkAppId = "1400396489";
    private static String betaMinorTimSdkAppId = "1400396489";
    private static String preOfficialTimSdkAppId = "1400400811";
    private static String officialTimSdkAppId = "1400302381";

    private static boolean alphaMajorLogCat = true;
    private static boolean alphaMinorLogCat = true;
    private static boolean betaMajorLogCat = true;
    private static boolean betaMinorLogCat = true;
    private static boolean preOfficialLogCat = true;
    private static boolean officialLogCat = false;


    public static String getBaseHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getBaseHost(Environment.valueOf(environment));
    }

    public static String getBaseHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorBaseHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorBaseHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorBaseHost;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorBaseHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialBaseHost;
        } else {
            return officialBaseHost;
        }

    }

    public static String getNewBaseHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getNewBaseHost(Environment.valueOf(environment));
    }

    public static String getNewBaseHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return newAlphaMajorBaseHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return newAlphaMinorBaseHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return newBetaMajorBaseHost;

        } else if (Environment.BATE_MINOR == environment) {
            return newBetaMinorBaseHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return newPreOfficialBaseHost;
        } else {
            return newOfficialBaseHost;
        }

    }

    public static String getAuthHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getAuthHost(Environment.valueOf(environment));
    }

    public static String getAuthHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorAuthHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorAuthHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorAuthHost;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorAuthHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialAuthHost;
        } else {
            return officialAuthHost;
        }

    }

    public static String getShopHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getShopHost(Environment.valueOf(environment));
    }

    public static String getShopHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorShopHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorShopHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorShopHost;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorShopHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialShopHost;
        } else {
            return officialShopHost;
        }

    }

    public static String getSecret() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getSecret(Environment.valueOf(environment));
    }

    public static String getSecret(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorSecret;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorSecret;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorSecret;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorSecret;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialSecret;
        } else {
            return officialSecret;
        }

    }

    public static String getCreditHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getCreditHost(Environment.valueOf(environment));
    }

    public static String getCreditHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorCreditHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorCreditHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorCreditHost;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorCreditHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialCreditHost;
        } else {
            return officialCreditHost;
        }

    }

    public static String getAgreementHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getAgreementHost(Environment.valueOf(environment));
    }

    public static String getAgreementHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorAgreementHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorAgreementHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorAgreementHost;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorAgreementHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialAgreementHost;
        } else {
            return officialAgreementHost;
        }

    }

    public static String getPointHost() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getPointHost(Environment.valueOf(environment));
    }

    public static String getPointHost(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorPointHost;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorPointHost;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorPointHost;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorPointHost;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialPointHost;
        } else {
            return officialPointHost;
        }

    }

    public static String getTcp() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getTcp(Environment.valueOf(environment));
    }

    public static String getTcp(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorTcp;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorTcp;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorTcp;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorTcp;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialTcp;
        } else {
            return officialTcp;
        }

    }

    public static String getTimSdkAppId() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getTimSdkAppId(Environment.valueOf(environment));
    }

    public static String getTimSdkAppId(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorTimSdkAppId;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorTimSdkAppId;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorTimSdkAppId;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorTimSdkAppId;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialTimSdkAppId;
        } else {
            return officialTimSdkAppId;
        }

    }

    public static boolean getLogCat() {
        String environment = SPUtils.getInstance().getString("environment", "PRE_OFFICIAL");
        return getLogCat(Environment.valueOf(environment));
    }

    public static boolean getLogCat(Environment environment) {
        SPUtils.getInstance().put("environment", environment.name());
        if (Environment.ALPHA_MAJOR == environment) {
            return alphaMajorLogCat;

        } else if (Environment.ALPHA_MINOR == environment) {
            return alphaMinorLogCat;

        } else if (Environment.BETA_MAJOR == environment) {
            return betaMajorLogCat;

        } else if (Environment.BATE_MINOR == environment) {
            return betaMinorLogCat;

        } else if (Environment.PRE_OFFICIAL == environment) {
            return preOfficialLogCat;
        } else {
            return officialLogCat;
        }
    }


}
