package com.hh.bamboobase.retrofit.model;

import java.io.Serializable;

/**
 * Created by chrisw on 2017/4/14.
 */

public class Model2<T> implements Serializable {

    protected int error_code;
    protected T data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
