package com.hh.bamboobase.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chrisw on 16/8/4.
 */
public class PreferencesUtils {

    private static final String ACCOUNT_CONFIG = "account_config";

    static SharedPreferences sp;

    public static void init(Context context){
        sp =  context.getApplicationContext().getSharedPreferences(ACCOUNT_CONFIG, Context.MODE_PRIVATE);
    }

    public static void setUserId(String uid) {
        sp.edit().putString("userId", uid == null ? "" : uid).apply();
    }

    public static String getUserId(){
       return sp.getString("userId", "");
    }

    public static void setToken(String token) {
        sp.edit().putString("token", token == null ? "" : token).apply();
    }

    public static String getToken(){
        return sp.getString("token", "");
    }

    public static void setInstId(String instId) {
        sp.edit().putString("instId", instId == null ? "" : instId).apply();
    }

    public static String getInstId(){
        return sp.getString("instId", "");
    }

    public static int getKeyboardHeight(int defaultValue){
        return sp.getInt("keyboard_height", defaultValue < 0 ? 0 : defaultValue);
    }

    public static void setKeyboardHeight(int height) {
        sp.edit().putInt("keyboard_height", height).apply();
    }
}
