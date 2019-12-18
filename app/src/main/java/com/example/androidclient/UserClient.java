package com.example.androidclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

    @POST("createUser")
    Call<User> createUser(@Body User user);

    @POST("request")
    Call<User> request(@Body User user);

    @GET("usersList")
    Call<List<User>> usersList(@Header("Authorization") String authToken);
}
