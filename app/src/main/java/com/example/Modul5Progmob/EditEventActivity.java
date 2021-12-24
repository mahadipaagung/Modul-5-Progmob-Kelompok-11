package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.deletekegiatan.ResponseDeleteKegiatan;
import com.example.Modul5Progmob.model.updatekegiatan.ResponseUpdateKegiatan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEventActivity extends AppCompatActivity {
    private ImageView home, add, profile;
    private TextView nama_kegiatan_tv, bidang_kegiatan_tv, deskripsi_kegiatan_tv, tgl_kegiatan_tv;
    private Button btn_save, btn_delete;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    String nama_kegiatan, bidang, deskripsi, tgl;
    int id_kegiatan;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);

        id_kegiatan = getIntent().getIntExtra("id_kegiatan", 0);
        nama_kegiatan = getIntent().getStringExtra("nama_kegiatan");
        bidang = getIntent().getStringExtra("bidang");
        deskripsi = getIntent().getStringExtra("deskripsi");
        tgl = getIntent().getStringExtra("tgl_kegiatan");

        nama_kegiatan_tv = (EditText)findViewById(R.id.txNama_Kegiatan);
        bidang_kegiatan_tv = (EditText)findViewById(R.id.txBidang_Kegiatan);
        deskripsi_kegiatan_tv = (EditText)findViewById(R.id.txDeskripsi_Kegiatan);
        btn_save = findViewById(R.id.btnSave);
        btn_delete = findViewById(R.id.btnDelete);
        tgl_kegiatan_tv = (EditText) findViewById(R.id.txTgl_Kegiatan);

        nama_kegiatan_tv.setText(nama_kegiatan);
        bidang_kegiatan_tv.setText(bidang);
        deskripsi_kegiatan_tv.setText(deskripsi);
        tgl_kegiatan_tv.setText(tgl);

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
                String deskripsi = deskripsi_kegiatan_tv.getText().toString().trim();
                String bidang_kegiatan = bidang_kegiatan_tv.getText().toString().trim();
                String tgl = tgl_kegiatan_tv.getText().toString().trim();
                updateKegiatan(id_kegiatan, nama_kegiatan_va, deskripsi, bidang_kegiatan, tgl);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKegiatan(id_kegiatan);
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

    private void deleteKegiatan(int id_kegiatan) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDeleteKegiatan> call = apiInterface.deleteKegiatan(id_kegiatan);
        call.enqueue(new Callback<ResponseDeleteKegiatan>() {
            @Override
            public void onResponse(Call<ResponseDeleteKegiatan> call, Response<ResponseDeleteKegiatan> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditEventActivity.this);
                    builder.setMessage("Data ini akan dihapus.");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(EditEventActivity.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditEventActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            @Override
            public void onFailure(Call<ResponseDeleteKegiatan> call, Throwable t) {
                Toast.makeText(EditEventActivity.this, "Gagal Delete 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateKegiatan(int id, String nama_kegiatan, String deskripsi, String bidang, String tgl) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUpdateKegiatan> call = apiInterface.updateKegiatan(id, nama_kegiatan, bidang, deskripsi, tgl);
        call.enqueue(new Callback<ResponseUpdateKegiatan>() {
            @Override
            public void onResponse(Call<ResponseUpdateKegiatan> call, Response<ResponseUpdateKegiatan> response) {
                if(response.body().isSuccess()){
                    Toast.makeText(EditEventActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditEventActivity.this, DetailKegiatanUserActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(EditEventActivity.this, "Gagal Update 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateKegiatan> call, Throwable t) {
                Toast.makeText(EditEventActivity.this, "response :"+t, Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(EditEventActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void toProfile() {
        Intent intent = new Intent(EditEventActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}