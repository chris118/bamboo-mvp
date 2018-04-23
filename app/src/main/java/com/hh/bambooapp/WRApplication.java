package com.hh.bambooapp;

import com.hh.bambooapp.api.BaseUrl;
import com.hh.bambooapp.api.WRHttpManager;
import com.hh.bamboobase.base.BaseApplication;
import com.hh.bamboobase.utils.PreferencesUtils;

/**
 * Created by chrisw on 2017/10/30.
 */

public class WRApplication extends BaseApplication {
    public WRApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(PreferencesUtils.getEnviroment().equals("TEST")){
            BaseUrl.init(BaseUrl.HOST_TEST);
        }else {
            BaseUrl.init(BaseUrl.HOST_PRODUCT);
        }
    }
}
