package com.nmpm.vitour.Api;

import com.nmpm.vitour.model.LoginRequest;
import com.nmpm.vitour.model.LoginResponse;
import com.nmpm.vitour.model.RegisterRequest;
import com.nmpm.vitour.model.RegisterResponse;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

public interface UserService {
    @POST("/user/register/")
    void register(@Body RegisterRequest request, Callback<RegisterResponse> callback);

    @POST("/user/login")
    void logout(Callback<Response> callback);
    void login(@Body LoginRequest request, Callback<LoginResponse> callback);

}