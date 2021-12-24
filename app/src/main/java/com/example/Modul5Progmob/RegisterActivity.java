package com.example.Modul5Progmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.register.ResponseRegister;

import java.util.Objects;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword, etName, etPhone, etNim;
    Button btnRegister;
    TextView tvLogin;
    RadioGroup etjk;
    RadioButton Gender;
    String Username, Password, Name, Phone, Nim;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etRegisterUsername);
        etPassword = findViewById(R.id.etRegisterPassword);
        etName = findViewById(R.id.etRegisterName);
        etPhone = findViewById(R.id.etRegisterPhone);
        etNim = findViewById(R.id.etRegisterNim);
        etjk = findViewById(R.id.rg);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        tvLogin = findViewById(R.id.tvLoginHere);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                showDialog();
                break;
            case R.id.tvLoginHere:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void showDialog(){
        EditText editTextUname = findViewById(R.id.etRegisterUsername);
        EditText editTextnama = findViewById(R.id.etRegisterName);
        EditText editTexttelp = findViewById(R.id.etRegisterPhone);
        EditText editTextnim = findViewById(R.id.etRegisterNim);
        RadioGroup radioGroupGenders = findViewById(R.id.rg);
        RadioButton radioButtonChosen = findViewById(radioGroupGenders.getCheckedRadioButtonId());

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Konfirmasi Data");

        dialogBuilder.setMessage("Apakah Anda sudah yakin dengan data berikut?\n" +
                "Username: " + editTextUname.getText() + "\n" +
                "Nama: " + editTextnama.getText() + "\n" +
                "No. Telp " + editTexttelp.getText() + "\n" +
                "NIM: " + editTextnim.getText() + "\n" +
                "Jenis Kelamin: " + radioButtonChosen.getText() + "\n")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Username = etUsername.getText().toString();
                        Password = etPassword.getText().toString();
                        Name = etName.getText().toString();
                        Phone = etPhone.getText().toString();
                        Nim = etNim.getText().toString();
                        Gender = findViewById(etjk.getCheckedRadioButtonId());
                        register(Username, Password, Name, Phone, Nim, Gender.getText().toString());
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
                .setCancelable(true);

        AlertDialog confirmDialog = dialogBuilder.create();

        confirmDialog.show();
    }

    private void register(String username, String password, String name, String phone, String nim, String gender) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRegister> call = apiInterface.register(username, password, name, phone, nim, gender);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if(response.body().isSuccess()){
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, "REGISTRASI BERHASIL! SILAHKAN LOGIN", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Gagal Register", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Register" +t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}