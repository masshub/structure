package com.max.common.utils;

/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public class StringUtils {

    public StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }
}
