package com.hh.bambooapp.api;

/**
 * Created by chrisw on 2017/11/8.
 */

public class BaseUrl {
    public static final int HOST_TEST = 0;//测试环境
    public static final int HOST_PRODUCT = 1;//正式环境

    public static String USER_SERVICE_BASE_URL = "";

    public static void init(int enviroment){
        switch (enviroment){
            case 0:
                USER_SERVICE_BASE_URL = "http://101.132.173.165/user/services/rest/";
                break;
            case 1:
                USER_SERVICE_BASE_URL = "http://116.228.105.34:8001/user/services/rest/";
                break;
        }
    }

    public static class Url {
        //user
        public static final String LOGIN = "user/Login";
    }
}
