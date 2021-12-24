package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.updateprofile.ResponseUpdateProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView mainImage, home, add, profile;
    private EditText etUsername, etName, etPhone, etNim;
    private Button btn_save;
    private Spinner spGender;
    String username, name, phone, nim, gender;
    int id_user;
    SharedPreferences preferences;
    ApiInterface apiInterface;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        preferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        username = preferences.getString("username", null);
        name = preferences.getString("name", null);
        phone = preferences.getString("phone", null);
        nim = preferences.getString("nim", null);
        gender = preferences.getString("gender", null);
        id_user = preferences.getInt("id", 0);
        etUsername = (EditText)findViewById(R.id.txUsername);
        etName = (EditText)findViewById(R.id.txName);
        etNim = (EditText)findViewById(R.id.txNIM);
        etPhone = (EditText)findViewById(R.id.txPhone_Number);
        spGender= findViewById(R.id.sp_Gender);
        btn_save = findViewById(R.id.btnSave);

        etUsername.setText(username);
        etName.setText(name);
        etPhone.setText(phone);
        etNim.setText(nim);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = id_user;
                String username = etUsername.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String nim = etNim.getText().toString().trim();
                String gender = spGender.getSelectedItem().toString().trim();
                updateProfile(id, username, name, nim, phone, gender);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAdd();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toHome();
            }
        });
    }

    private void updateProfile(int id, String username, String name, String nim, String phone, String gender){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUpdateProfile> call = apiInterface.updateProfile(id, username, name, phone, nim, gender);
        call.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                if(response.body().isSuccess()){
                    int id = response.body().getData().getId();
                    String username = response.body().getData().getUsername();
                    String name = response.body().getData().getName();
                    String phone = response.body().getData().getPhone();
                    String nim = response.body().getData().getNim();
                    String gender = response.body().getData().getGender();
                    editor = preferences.edit();
                    editor.putInt("id", id);
                    editor.putString("username", username);
                    editor.putString("name", name);
                    editor.putString("phone", phone);
                    editor.putString("nim", nim);
                    editor.putString("gender", gender);
                    editor.apply();
                    Toast.makeText(EditProfileActivity.this, "Berhasil Mengupdate", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(EditProfileActivity.this, "Gagal Update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Gagal Update 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toAdd() {
        Intent intent = new Intent(EditProfileActivity.this, AddEvent.class);
        startActivity(intent);
    }

    private void toHome() {
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}