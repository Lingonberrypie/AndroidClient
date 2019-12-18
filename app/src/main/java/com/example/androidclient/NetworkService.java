package com.example.androidclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    //private static NetworkService mInstance;
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL = "http://192.168.0.106/WebApi/api/";

    // инициализиция Retrofit
    static Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = builder.build();

    public static <S> S createService (Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
