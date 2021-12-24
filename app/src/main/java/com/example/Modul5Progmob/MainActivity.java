package com.example.Modul5Progmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Modul5Progmob.api.ApiInterface;

public class MainActivity extends AppCompatActivity {

    TextView etUsername, etName, etPhone, etAddress, etGender;
    String username, name, phone, address, gender;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etMainUsername);
        etName = findViewById(R.id.etMainName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etGender = findViewById(R.id.etGender);

        etUsername.setText(username);
        etName.setText(name);
        etPhone.setText(phone);
        etAddress.setText(address);
        etGender.setText(gender);

    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}