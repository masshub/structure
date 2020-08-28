package com.max.common.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public class PhoneUtils {
    /**
     * 获取手机唯一标识符
     *
     * @return 标识符
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        String deviceId = null;
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = TelephonyMgr.getDeviceId();
            long imeiInt = Long.parseLong(deviceId);
            deviceId = (imeiInt == 0) ? null : deviceId;
        } catch (Exception e) {
            deviceId = getAndroidID(context);
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = UUID.randomUUID().toString();
        }
        return deviceId;
    }

    /**
     * 获取androidID
     *
     * @param context 上下文
     * @return
     */
    public static String getAndroidID(Context context) {
        try {
            ContentResolver cr = context.getContentResolver();
            String androiId = Settings.System.getString(cr, Settings.System.ANDROID_ID);
            return androiId == null ? "" : androiId;
        } catch (Exception e) {
            return "";
        }
    }
}
