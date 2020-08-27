package com.max.common.http.logging;

import okhttp3.internal.platform.Platform;

/**
 * Created by Maker on 2020/8/27.
 * Description:
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Logger {
    void log(int level, String tag, String msg);

    Logger DEFAULT = new Logger() {
        @Override
        public void log(int level, String tag, String message) {
            Platform.get().log(message, level, null);
        }
    };
}
