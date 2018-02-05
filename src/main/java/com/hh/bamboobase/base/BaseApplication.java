package com.hh.bamboobase.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.hh.bamboobase.retrofit.HttpManager;
import com.hh.bamboobase.utils.PreferencesUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by chrisw on 16/8/5.
 */
public abstract class BaseApplication extends MultiDexApplication {
    protected static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        PreferencesUtils.init(this);

        //初始化http
        HttpManager.getInstance().init(this);

        //初始化crash统计
        CrashReport.initCrashReport(mInstance, "fd8a4cd4c9", true);

        //初始化LeakCanary
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    protected abstract String getToken();
    protected abstract String getInstId();

    public String getAuthorToken(){
        return getToken();
    }
    public String getAutorInstId(){
        return getInstId();
    }

    private int appCount;

    /**
     * 方法3：通过ActivityLifecycleCallbacks来批量统计Activity的生命周期，来做判断，
     * 此方法在API 14以上均有效，但是需要在Application中注册此回调接口
     * 必须：
     * 1. 自定义Application并且注册ActivityLifecycleCallbacks接口
     * 2. AndroidManifest.xml中更改默认的Application为自定义
     * 3. 当Application因为内存不足而被Kill掉时，这个方法仍然能正常使用
     * 。虽然全局变量的值会因此丢失，但是再次进入App时候会重新统计一次的
     *
     * @return
     */
    public boolean isForeground() {
        return appCount > 0;
    }

    private class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            appCount++;
        }

        @Override
        public void onActivityResumed(Activity activity) {
            mInstance.onActivityResumed(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
            appCount--;
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }

    public void onActivityResumed(Activity activity) {

    }

    private String mCurrentActivityName;//当前activity名称

    public String getCurrentActivityName() {
        return mCurrentActivityName;
    }

    public void setCurrentActivityName(String currentActivityName) {
        Log.e("Name", currentActivityName);
        this.mCurrentActivityName = currentActivityName;
    }

}
