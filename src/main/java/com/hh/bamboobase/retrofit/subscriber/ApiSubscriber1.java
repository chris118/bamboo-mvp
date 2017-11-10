package com.hh.bamboobase.retrofit.subscriber;


import com.hh.bamboobase.retrofit.model.Model1;

/**
 * Created by chrisw on 2017/4/14.
 */

public abstract class ApiSubscriber1 extends ApiSubscriber<Model1> {

    @Override
    public final void onNext(Model1 t) {
        int code = t.getCode();
        boolean success = (code == 0);
        onResponse(success, code, "", t);
    }
}