package com.hh.bambooapp.account.contract;

import com.hh.bambooapp.account.model.entity.LoginResponse;
import com.hh.bamboobase.base.mvp.IModel;
import com.hh.bamboobase.base.mvp.IView;

import rx.Observable;

/**
 * Created by chrisw on 2017/11/6.
 */

public class LoginContract {
    public interface View extends IView {
        void mobileError();
        void passwordError();
        void loginSuccess();
        void loginFailed();
    }

    public interface Model extends IModel {
        Observable<LoginResponse> login(String username, String password);
    }
}
