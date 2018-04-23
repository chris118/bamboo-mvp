package com.hh.bambooapp.account.model;

import com.hh.bambooapp.account.contract.LoginContract;
import com.hh.bambooapp.account.model.entity.LoginResponse;
import com.hh.bambooapp.api.UserApi;
import com.hh.bambooapp.api.TSHttpManager;
import com.hh.bamboobase.retrofit.RxRetrofitComposer;

import rx.Observable;

/**
 * Created by chrisw on 2017/11/6.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<LoginResponse> login(String username, String password) {
        UserApi api = TSHttpManager.getInstance().getUserApi();
        return api.login(username, password)
                .compose(RxRetrofitComposer.<LoginResponse>applySchedulers());
    }
}
