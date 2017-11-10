package com.hh.bamboobase.retrofit.okhttp.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * header信息拦截器
 * Created by luowentao on 2016/10/27.
 */
public class HeaderInfoInterceptor implements Interceptor {
    private static final String TAG = HeaderInfoInterceptor.class.getSimpleName();
    private int deviceType;
    private int appType;
    private String device_id;


    public HeaderInfoInterceptor(int deviceType, int appType, String device_id) {
        this.deviceType = deviceType;
        this.appType = appType;
        this.device_id = device_id;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request newHeaderRequest = request.newBuilder()
                .addHeader("DeviceType", String.valueOf(deviceType))
                .addHeader("AppType", String.valueOf(appType))
                .addHeader("DeviceUniqueId", device_id)
                .build();

        return chain.proceed(newHeaderRequest);
    }
}
