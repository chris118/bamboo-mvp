package com.hh.bamboobase.retrofit.okhttp.interceptor;

import com.hh.bamboobase.base.BaseApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * header信息拦截器
 * Created by wangxiaopeng on 2016/10/27.
 */
public class HeaderInfoInterceptor implements Interceptor {
    private static final String TAG = HeaderInfoInterceptor.class.getSimpleName();
    private int deviceType;
    private int appType;
    private String deviceID;
    private String token;
    private String instId;
    private String versionCode;


    public HeaderInfoInterceptor(int deviceType, int appType, String device_id, String version_code) {
        this.deviceType = deviceType;
        this.appType = appType;
        this.deviceID = device_id;
        this.versionCode = version_code;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (token == null || token.length() == 0) {
            Request newHeaderRequest = request.newBuilder()
                    .addHeader("DeviceType", String.valueOf(deviceType))
                    .addHeader("AppType", String.valueOf(appType))
                    .addHeader("DeviceUniqueId", deviceID)
                    .addHeader("AppVersion", this.versionCode)
                    .build();
            return chain.proceed(newHeaderRequest);
        } else {
            Request newHeaderRequest = request.newBuilder()
                    .addHeader("DeviceType", String.valueOf(deviceType))
                    .addHeader("AppType", String.valueOf(appType))
                    .addHeader("DeviceUniqueId", deviceID)
                    .addHeader("token", token)
                    .addHeader("InstId", instId)
                    .addHeader("AppVersion", this.versionCode)
                    .build();
            return chain.proceed(newHeaderRequest);
        }
    }
}
