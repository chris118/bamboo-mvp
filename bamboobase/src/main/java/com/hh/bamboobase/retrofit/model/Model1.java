package com.hh.bamboobase.retrofit.model;

import java.io.Serializable;

/**
 * Created by chrisw on 2017/4/14.
 */

public class Model1 implements Serializable {
    private int code;
    private String message;

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
