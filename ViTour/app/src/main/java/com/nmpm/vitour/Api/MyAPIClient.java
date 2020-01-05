package com.nmpm.vitour.Api;

import android.text.TextUtils;


import com.nmpm.vitour.Activity.Constants;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


public class MyAPIClient {
    private static MyAPIClient instance;

    private RestAdapter adapter;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;

    private MyAPIClient() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if (!TextUtils.isEmpty(accessToken))
                    request.addHeader("Authorization", "Bearer " + accessToken);
            }
        };
        adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.APIEndpoint)
                .setRequestInterceptor(requestInterceptor)
                .build();
    }

    public RestAdapter getAdapter() {
        return adapter;
    }

    public static MyAPIClient getInstance() {
        if (instance == null)
            instance = new MyAPIClient();
        return instance;
    }
}