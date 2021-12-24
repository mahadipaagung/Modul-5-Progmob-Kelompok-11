package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetailActivity extends AppCompatActivity {

    private ImageView home, add, profile;
    private TextView name_tv, nama_kegiatan_tv, deskripsi_tv, bidang_tv, tgl_kegiatan_tv;
    private String name, nama_keg, deskripsi, bidang_kegiatan, tgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        name_tv = findViewById(R.id.txName);
        nama_kegiatan_tv = findViewById(R.id.txNama_Kegiatan);
        bidang_tv = findViewById(R.id.txBidang_Kegiatan);
        deskripsi_tv = findViewById(R.id.txDeskripsi_Kegiatan);
        tgl_kegiatan_tv = findViewById(R.id.txTgl_Kegiatan);

        name = getIntent().getStringExtra("nama");
        nama_keg = getIntent().getStringExtra("nama_kegiatan");
        deskripsi = getIntent().getStringExtra("deskripsi");
        bidang_kegiatan = getIntent().getStringExtra("bidang");
        tgl = getIntent().getStringExtra("tgl_kegiatan");

        name_tv.setText(name);
        nama_kegiatan_tv.setText(nama_keg);
        deskripsi_tv.setText(deskripsi);
        bidang_tv.setText(bidang_kegiatan);
        tgl_kegiatan_tv.setText(tgl);

    }
    private void toHome() {
        Intent intent = new Intent(ShowDetailActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}

