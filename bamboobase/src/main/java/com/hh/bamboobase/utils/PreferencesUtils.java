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

    public static void setEnviroment(String env) {
        sp.edit().putString("env", env == null ? "" : env).apply();
    }

    public static String getEnviroment(){
        return sp.getString("env", "");
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

    public static String getNickName(){
        return sp.getString("nickname", "");
    }

    public static void setNickName(String nickName) {
        sp.edit().putString("nickname", nickName == null ? "" : nickName).apply();
    }

    public static String getRole(){
        return sp.getString("role", "");
    }

    public static void setRole(String role) {
        sp.edit().putString("role", role == null ? "" : role).apply();
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

    public static int getStartHour(int defaultValue){
        return sp.getInt("start_hour", defaultValue < 0 ? 0 : defaultValue);
    }

    public static void setStartHour(int hour) {
        sp.edit().putInt("start_hour", hour).apply();
    }

    public static int getStartMinute(int defaultValue){
        return sp.getInt("start_minute", defaultValue < 0 ? 0 : defaultValue);
    }

    public static void setStartMinute(int minute) {
        sp.edit().putInt("start_minute", minute).apply();
    }
}
