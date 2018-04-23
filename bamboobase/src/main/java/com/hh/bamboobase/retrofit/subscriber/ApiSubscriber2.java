package com.hh.bamboobase.retrofit.subscriber;


import com.hh.bamboobase.retrofit.model.Model2;

/**
 * Created by chrisw on 2017/4/14.
 */

public abstract class ApiSubscriber2<T extends Model2> extends ApiSubscriber<T> {

    @Override
    public final void onNext(T data) {
        int code = data.getError_code();
        boolean success = (code == 0);
        onResponse(success, code, "", data);
    }
}
