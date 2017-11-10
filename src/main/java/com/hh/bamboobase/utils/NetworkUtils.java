package com.hh.bamboobase.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chrisw on 2017/3/7.
 */

public class NetworkUtils {

    /**
     * 检测网络是否可用
     * @param context
     * @return
     */
    public static boolean netIsAvailable(Context context) {
        Context app = context.getApplicationContext();
        ConnectivityManager manager = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo[] networkinfos = manager.getAllNetworkInfo();
        if (networkinfos == null || networkinfos.length < 1) {
            return false;
        }
        for (NetworkInfo networkinfo : networkinfos) {
            if (networkinfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}
