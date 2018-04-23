package com.hh.bambooapp.api;

import com.hh.bambooapp.account.model.entity.LoginResponse;

import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiaopeng on 2017/11/8.
 */

public interface UserApi {
    @PUT(BaseUrl.Url.LOGIN + "/{username}/{password}")
    Observable<LoginResponse> login(
            @Path("username") String username,
            @Path("password")String password);
}
