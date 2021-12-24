package com.example.Modul5Progmob.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Modul5Progmob.model.indexkegiatan.DataItem;
import com.example.Modul5Progmob.model.indexkegiatan.User;

import java.util.List;

@Dao
public interface KegiatanDao {
    @Query("SELECT * FROM kegiatan ORDER BY tgl_kegiatan DESC")
    List<DataItem> getAllkegiatan();

    @Update
    void updateKegiatan(DataItem dataItem);

    @Insert
    void insertAllKegiatan(List<DataItem> dataItems);

    @Delete
    void deleteKegiatan(DataItem dataItem);

    @Query("DELETE FROM kegiatan")
    void ledakinTabelKegiatan();


    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Update
    void updateUser(User user);

    @Insert
    void insertAllUser(List<User> users);

    @Delete
    void deleteAllUser(User user);

    @Query("DELETE FROM user")
    void ledakinTabelUser();
}
