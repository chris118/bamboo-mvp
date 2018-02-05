package com.hh.bamboobase.retrofit;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hh.bamboobase.base.BaseApplication;
import com.hh.bamboobase.retrofit.okhttp.AppUtils;
import com.hh.bamboobase.retrofit.okhttp.Logger;
import com.hh.bamboobase.retrofit.okhttp.interceptor.CacheStrategyInterceptor;
import com.hh.bamboobase.retrofit.okhttp.interceptor.HeaderInfoInterceptor;
import com.hh.bamboobase.retrofit.okhttp.interceptor.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chrisw on 2017/9/16.
 */

public class HttpManager {
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系，
     * 而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static final HttpManager instance = new HttpManager();
    }
    public static HttpManager getInstance() {
        return SingletonHolder.instance;
    }


    /**
     * 构造方法
     */
    public HttpManager() {
    }

    protected Application mAppliction;
    protected OkHttpClient mOkHttpClient;
    protected Gson mGson;

    public void init(Application application) {
        mAppliction = application;
        mOkHttpClient = buildOkhttpClient();
        mGson = getGson();
    }

    public Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    private OkHttpClient buildOkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.httpLog(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new ResponseInterceptor());
        builder.addInterceptor(new CacheStrategyInterceptor(mAppliction));
        builder.addInterceptor(new HeaderInfoInterceptor(0, 6, AppUtils.getRealDeviceId(mAppliction)));

        int cacheSize = 20 * 1024 * 1024; // 20 MiB
        Cache cache = new Cache(mAppliction.getCacheDir(), cacheSize);
        builder.cache(cache);

        return builder.build();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


    public static class RetrofitFactory {
        public static Retrofit create(Gson gson, OkHttpClient okHttpClient, String baseUrl) {
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
    }
}
