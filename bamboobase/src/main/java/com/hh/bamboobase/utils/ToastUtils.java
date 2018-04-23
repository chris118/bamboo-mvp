package com.hh.bamboobase.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/23.
 */
public class ToastUtils {

    private static Toast sToast;

    public static void showToast(Context context, String text){
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int resId){
        if(context == null || resId <= 0)return;
        String text = context.getResources().getString(resId);
        showToast(context, text);
    }

    public static void showToastLong(Context context, String text){
        showToast(context, text, Toast.LENGTH_LONG);
    }

    public static void showToastLong(Context context, int resId){
        if(context == null || resId <= 0)return;
        String text = context.getResources().getString(resId);
        showToastLong(context, text);
    }

    private static void showToast(Context context, String text, int showTime){
        if(context == null || TextUtils.isEmpty(text))return;
        if (null != sToast) {
            sToast.cancel();
            sToast = null;
        }
        sToast = Toast.makeText(context.getApplicationContext(), text, showTime);
        sToast.show();
    }

    public static void showNetworkError(Context context){
        showToast(context, "网络连接失败，请检查网络设备");
    }

    public static void clear(){
        if (null != sToast) {
            sToast.cancel();
            sToast = null;
        }
    }
}
