package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Modul5Progmob.adapter.KegiatanUserAdapter;
import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.getkegiatanuser.DataItem;
import com.example.Modul5Progmob.model.getkegiatanuser.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailKegiatanUserActivity extends AppCompatActivity {
    private ImageView home, add, profile;
    ArrayList list = new ArrayList<DataItem>();
    KegiatanUserAdapter adapter;
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    SharedPreferences preferences;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan_user);
        preferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
        id = preferences.getInt("id", 0);
        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.kegiatan_rv);
        adapter = new KegiatanUserAdapter(list, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getKegiatanUser(id);

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
    private void getKegiatanUser(int id) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<com.example.Modul5Progmob.model.getkegiatanuser.Response> call = apiInterface.getKegiatanUser(id);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                list.clear();
                list.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(DetailKegiatanUserActivity.this, "Gagal peroleh data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void toAdd() {
        Intent intent = new Intent(DetailKegiatanUserActivity.this, AddEvent.class);
        startActivity(intent);
    }

    private void toHome() {
        Intent intent = new Intent(DetailKegiatanUserActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}