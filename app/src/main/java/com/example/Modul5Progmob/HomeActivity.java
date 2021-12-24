package com.example.Modul5Progmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Modul5Progmob.adapter.KegiatanAdapter;
import com.example.Modul5Progmob.api.ApiClient;
import com.example.Modul5Progmob.api.ApiInterface;
import com.example.Modul5Progmob.model.indexkegiatan.DataItem;
import com.example.Modul5Progmob.model.indexkegiatan.Response;
import com.example.Modul5Progmob.model.indexkegiatan.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity {
    private ImageView home, add, profile;
    ArrayList<DataItem> list;
    ArrayList<User> listUser;
    KegiatanAdapter adapter;
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    ArrayList anu = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        listUser = new ArrayList<>();
        setContentView(R.layout.activity_home);
        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.kegiatan_rv);
        adapter = new KegiatanAdapter(list, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getDataKegiatan();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAdd();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toProfile();
            }
        });
    }

    private void getDataKegiatan() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiInterface.getKegiatan();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    list.clear();
                    list.addAll(response.body().getData());
                    for (int i=0; i<response.body().getData().size(); i++) {
                        if(!(anu.contains(response.body().getData().get(i).getUser().getId()))){
                            listUser.add(response.body().getData().get(i).getUser());
                            anu.add(response.body().getData().get(i).getUser().getId());
                        }
                    }
                    adapter.notifyDataSetChanged();
                    new insertAllKegiatanData().execute();
                    new insertUser().execute();
                }
                else {
                    list.addAll(((App) getApplication()).appDatabase.kegiatanDao().getAllkegiatan());
                    listUser.addAll(((App) getApplication()).appDatabase.kegiatanDao().getAllUser());
                    for (int i=0; i<list.size(); i++) {
                        for(int a = 0; a<listUser.size(); a++){
                            if(listUser.get(a).getId() == list.get(i).getIdUser()){
                                list.get(i).setUser(listUser.get(a));
                            }
                        }
                    }
                    updateDataView();
                    Toast.makeText(getApplicationContext(), "Koneksi gagal, fungsionalitas aplikasi mungkin berkurang, menggunakan data dari database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                list.addAll(((App) getApplication()).appDatabase.kegiatanDao().getAllkegiatan());
                listUser.addAll(((App) getApplication()).appDatabase.kegiatanDao().getAllUser());
                for (int i=0; i<list.size(); i++) {
                    for(int a = 0; a<listUser.size(); a++){
                        if(listUser.get(a).getId() == list.get(i).getIdUser()){
                            list.get(i).setUser(listUser.get(a));
                        }
                    }
                }
                updateDataView();
                Toast.makeText(getApplicationContext(), "Koneksi gagal, fungsionalitas aplikasi mungkin berkurang, menggunakan data dari database", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toAdd() {
        Intent intent = new Intent(HomeActivity.this, AddEvent.class);
        startActivity(intent);
    }

    private void toProfile() {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public class getKegiatanData extends AsyncTask<Void, Void, DataItem> {
        @Override
        protected DataItem doInBackground(Void... voids) {
            list.addAll(((App) getApplication()).appDatabase.kegiatanDao().getAllkegiatan());
            return null;
        }
    }

    public class insertUser extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... voids) {
            ((App) getApplication()).appDatabase.kegiatanDao().ledakinTabelUser();
            ((App) getApplication()).appDatabase.kegiatanDao().insertAllUser(listUser);
            return null;
        }
    }

    public class getUser extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... voids) {
            listUser.addAll(((App) getApplication()).appDatabase.kegiatanDao().getAllUser());
            return null;
        }
    }


    public class insertAllKegiatanData extends AsyncTask<Void, Void, DataItem> {
        @Override
        protected DataItem doInBackground(Void... voids) {
            ((App) getApplication()).appDatabase.kegiatanDao().ledakinTabelKegiatan();
            ((App) getApplication()).appDatabase.kegiatanDao().insertAllKegiatan(list);
            return null;
        }
    }


    public void updateDataView() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}