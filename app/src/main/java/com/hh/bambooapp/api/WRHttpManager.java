package com.hh.bambooapp.api;

import com.hh.bamboobase.retrofit.HttpManager;

/**
 * Created by chrisw on 2017/11/8.
 */

public class WRHttpManager {
    private static class SingletonHolder {
        private static final WRHttpManager instance = new WRHttpManager();
    }
    public static WRHttpManager getInstance() {
        return WRHttpManager.SingletonHolder.instance;
    }

    public UserApi getUserApi() {
        return HttpManager.RetrofitFactory
                .create(HttpManager.getInstance().getGson(),
                        HttpManager.getInstance().getOkHttpClient(),
                        BaseUrl.USER_SERVICE_BASE_URL)
                .create(UserApi.class);
    }
}
