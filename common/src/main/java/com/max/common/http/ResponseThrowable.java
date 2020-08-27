package com.max.common.http;

/**
 * Created by Maker on 2020/8/27.
 * Description:
 */
public class ResponseThrowable extends Exception {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
