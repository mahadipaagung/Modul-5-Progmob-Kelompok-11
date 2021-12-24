package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.storeKegiatan.ResponseKegiatanStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEvent extends AppCompatActivity {
    private ImageView home, add, profile;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btn_save;
    private static final String TAG = "AddEvent";
    int id_user;
    ApiInterface apiInterface;
    SharedPreferences preferences;
    private EditText tgl_kegiatan_tv, deskripsi_tv, bidang_kegiatan_tv, nama_kegiatan_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        preferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        id_user = preferences.getInt("id", 0);
        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tgl_kegiatan_tv = findViewById(R.id.txTgl_Kegiatan);
        nama_kegiatan_tv = findViewById(R.id.txNama_Kegiatan);
        btn_save = findViewById(R.id.btnSave);
        deskripsi_tv = findViewById(R.id.txDeskripsi_Kegiatan);
        bidang_kegiatan_tv = findViewById(R.id.txBidang_Kegiatan);
        tgl_kegiatan_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_kegiatan_va = nama_kegiatan_tv.getText().toString().trim();
                String deskripsi = deskripsi_tv.getText().toString().trim();
                String bidang_kegiatan = bidang_kegiatan_tv.getText().toString().trim();
                String tgl = tgl_kegiatan_tv.getText().toString().trim();
                storeKegiatan(id_user, nama_kegiatan_va, bidang_kegiatan, deskripsi, tgl);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toProfile();
            }
        });
    }

    private void storeKegiatan(int id_user, String nama_kegiatan, String bidang_kegiatan, String deskripsi_kegiatan, String tgl_kegiatan) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseKegiatanStore> call = apiInterface.storeKegiatan(id_user, nama_kegiatan,bidang_kegiatan, deskripsi_kegiatan, tgl_kegiatan);
        call.enqueue(new Callback<ResponseKegiatanStore>() {
            @Override
            public void onResponse(Call<ResponseKegiatanStore> call, Response<ResponseKegiatanStore> response) {
                Log.d(TAG, "onResponse: "+response);
                if(response.body().isSuccess()){
                    Toast.makeText(AddEvent.this, "Kegiatan Baru Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddEvent.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddEvent.this, "Gagal Menambah Kegiatan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatanStore> call, Throwable t) {

            }

        });
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tgl_kegiatan_tv.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void toHome() {
        Intent intent = new Intent(AddEvent.this, HomeActivity.class);
        startActivity(intent);
    }

    private void toProfile() {
        Intent intent = new Intent(AddEvent.this, ProfileActivity.class);
        startActivity(intent);
    }
}