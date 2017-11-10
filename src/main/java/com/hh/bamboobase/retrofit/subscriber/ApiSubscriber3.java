package com.hh.bamboobase.retrofit.subscriber;

import com.hh.bamboobase.retrofit.model.Model3;

import rx.Subscriber;

/**
 * Created by chrisw on 2017/4/14.
 */

public abstract class ApiSubscriber3<T> extends Subscriber<Model3<T>> {

    @Override
    public final void onNext(Model3<T> data) {
        int code = data.getCode();
        String msg = data.getMessage();
        boolean success = (code == 200);
        onResponse(success, code, msg, data.getData());
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public final void onError(Throwable e) {
        onResponse(false, -1, "您的网络不给力，请稍后重试", null);
    }

    final void onResponse(boolean success, int code, String msg, T data) {
        if (success) {
            onNext(msg, data);
        } else {
            onError(code, msg);
        }
    }

    public abstract void onNext(String msg, T t);

    public abstract void onError(int code, String msg);
}
