package com.example.Modul5Progmob;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;
    String Username, Password;
    TextView tvRegister;
    ApiInterface apiInterface;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        int id_user = preferences.getInt("id", 0);
        if(id_user > 0){
            Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(toHome);
        }
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        tvRegister = findViewById(R.id.tvCreateAccount);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Username = etUsername.getText().toString();
                Password = etPassword.getText().toString();
                login(Username,Password);
                break;
            case R.id.tvCreateAccount:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login(String username, String password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogin> loginCall = apiInterface.login(username,password);
        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.body().isSuccess()){
                    int id = response.body().getData().getId();
                    String username = response.body().getData().getUsername();
                    String name = response.body().getData().getName();
                    String phone = response.body().getData().getPhone();
                    String nim = response.body().getData().getNim();
                    String gender = response.body().getData().getGender();
                    editor = preferences.edit();
                    editor.putInt("id", id);
                    editor.putString("name", name);
                    editor.putString("username", username);
                    editor.putString("phone", phone);
                    editor.putString("nim", nim);
                    editor.putString("gender", gender);
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}