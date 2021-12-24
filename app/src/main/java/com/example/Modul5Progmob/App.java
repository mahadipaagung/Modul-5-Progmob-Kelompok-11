package com.example.Modul5Progmob;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.database.AppDatabase;
import com.example.Modul5Progmob.model.fcmtoken.Response;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;

public class App extends Application {
    public AppDatabase appDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        setupFcm(this);
        appDatabase = AppDatabase.getInstance(this);
    }

    public static void setupFcm(Context context){
        final ApiInterface[] apiInterface = new ApiInterface[1];
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE);
        int id = preferences.getInt("id", 0);
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                apiInterface[0] = ApiClient.getClient().create(ApiInterface.class);
                Call<Response> call = apiInterface[0].fcmToken(id, token);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if(response.body().isSuccess()){
                            Toast.makeText(context, "Token Disimpan", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, "Error"+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
