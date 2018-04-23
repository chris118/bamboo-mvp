package com.hh.bamboobase.retrofit.model;

import java.io.Serializable;

/**
 * Created by chrisw on 2017/4/14.
 */

public class Model3<T> implements Serializable {
    protected int code;
    protected T data;
    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
