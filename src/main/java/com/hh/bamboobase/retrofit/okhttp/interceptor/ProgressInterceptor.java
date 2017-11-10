package com.hh.bamboobase.retrofit.okhttp.interceptor;


import com.hh.bamboobase.retrofit.okhttp.progress.ProgressResponseBody;
import com.hh.bamboobase.retrofit.okhttp.progress.ProgressResponseListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 进度拦截器
 * Created by chrisw on 2015/10/26.
 */
public class ProgressInterceptor implements Interceptor {
    private ProgressResponseListener progressListener;

    public ProgressInterceptor(ProgressResponseListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
    }
}
