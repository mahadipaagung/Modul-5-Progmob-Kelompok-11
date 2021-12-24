package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private ImageView home, add, profile, mainImage;
    private TextView etUsername, etName, etPhone, etNim, etGender;
    private Button btn_logout, btn_editprofile, btn_editpostingan;
    String username, name, phone, nim, gender;
    int id_user;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        id_user = preferences.getInt("id", 0);
        username = preferences.getString("username", null);
        name = preferences.getString("name", null);
        phone = preferences.getString("phone", null);
        gender = preferences.getString("gender", null);
        nim = preferences.getString("nim", null);

        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        etUsername = findViewById(R.id.txUsername);
        etName = findViewById(R.id.txName);
        etPhone = findViewById(R.id.txPhone_Number);
        etNim = findViewById(R.id.txNIM);
        etGender = findViewById(R.id.txGender);
        btn_logout = findViewById(R.id.btnLogout);
        btn_editprofile = findViewById(R.id.btnEditProfile);
        btn_editpostingan = findViewById(R.id.btnEditEvent);
        mainImage = findViewById(R.id.mainimageView);

        etUsername.setText(username);
        etName.setText(name);
        etPhone.setText(phone);
        etNim.setText(nim);
        etGender.setText(gender);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }
        });

        btn_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditProfile();
            }
        });

        btn_editpostingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toKegiatanUser();
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

    private void toEditProfile() {
        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
//        intent.putExtra("id", id_user);
//        intent.putExtra("username", username);
//        intent.putExtra("phone", phone);
//        intent.putExtra("nim", nim);
//        intent.putExtra("gender", nim);
        startActivity(intent);
    }
    private void toKegiatanUser() {
        Intent intent = new Intent(ProfileActivity.this, DetailKegiatanUserActivity.class);
        startActivity(intent);
    }

    private void moveToLogin() {
        int id = 0;
        username = null;
        name = null;
        phone = null;
        nim = null;
        gender = null;
        editor = preferences.edit();
        editor.putInt("id", id);
        editor.putString("name", name);
        editor.putString("username", username);
        editor.putString("phone", phone);
        editor.putString("nim", nim);
        editor.putString("gender", gender);
        editor.apply();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void toAdd() {
        Intent intent = new Intent(ProfileActivity.this, AddEvent.class);
        startActivity(intent);
    }

    private void toHome() {
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}