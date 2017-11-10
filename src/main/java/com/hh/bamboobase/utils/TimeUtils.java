package com.hh.bamboobase.utils;

/**
 * Created by chrisw on 2017/4/10.
 */

public class TimeUtils {

    public static long getUpdateTime(){
        return System.currentTimeMillis();
    }

    public static int getServerTime(){
        return (int) (getUpdateTime() / 1000);
    }

    public static boolean isTimeOut(long createTime) {
        return getUpdateTime() - createTime >= 2 * 60 * 1000;
    }
}
