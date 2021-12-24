package com.example.Modul5Progmob.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://172.16.57.235/laravel_api/public/api/"; //Sesuaikan dengan IP pribadi yang digunakan
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

