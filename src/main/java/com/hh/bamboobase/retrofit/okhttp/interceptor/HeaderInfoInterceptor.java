package com.hh.bamboobase.retrofit.okhttp.interceptor;

import com.hh.bamboobase.base.BaseApplication;

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
    private String token;
    private String instId;


    public HeaderInfoInterceptor(int deviceType, int appType, String device_id) {
        this.deviceType = deviceType;
        this.appType = appType;
        this.device_id = device_id;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String token = BaseApplication.getInstance().getAuthorToken();
        String instId = BaseApplication.getInstance().getAutorInstId();

        Request request = chain.request();
        if(token == null || token.length() == 0){
            Request newHeaderRequest = request.newBuilder()
                    .addHeader("DeviceType", String.valueOf(deviceType))
                    .addHeader("AppType", String.valueOf(appType))
                    .addHeader("DeviceUniqueId", device_id)
                    .build();
            return chain.proceed(newHeaderRequest);
        }else {
             Request newHeaderRequest = request.newBuilder()
                    .addHeader("DeviceType", String.valueOf(deviceType))
                    .addHeader("AppType", String.valueOf(appType))
                    .addHeader("DeviceUniqueId", device_id)
                     .addHeader("token", token)
                     .addHeader("InstId", instId)
                     .build();
            return chain.proceed(newHeaderRequest);
        }
    }
}
