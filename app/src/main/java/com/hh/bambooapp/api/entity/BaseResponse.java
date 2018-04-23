package com.hh.bambooapp.api.entity;

/**
 * Created by xiaopeng on 2017/11/8.
 */

public class BaseResponse {
    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
