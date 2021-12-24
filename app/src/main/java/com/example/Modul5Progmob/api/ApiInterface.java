package com.example.Modul5Progmob.api;



import com.example.Modul5Progmob.model.deletekegiatan.ResponseDeleteKegiatan;
import com.example.Modul5Progmob.model.indexkegiatan.Response;
import com.example.Modul5Progmob.model.login.ResponseLogin;
import com.example.Modul5Progmob.model.register.ResponseRegister;
import com.example.Modul5Progmob.model.storeKegiatan.ResponseKegiatanStore;
import com.example.Modul5Progmob.model.updatekegiatan.ResponseUpdateKegiatan;
import com.example.Modul5Progmob.model.updateprofile.ResponseUpdateProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseRegister> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("nim") String nim,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("kegiatan/store")
    Call<ResponseKegiatanStore> storeKegiatan(
            @Field("id_user") int id_user,
            @Field("nama_kegiatan") String nama_kegiatan,
            @Field("bidang_kegiatan") String bidang_kegiatan,
            @Field("deskripsi_kegiatan") String deskripsi_kegiatan,
            @Field("tgl_kegiatan") String tgl_kegiatan
    );

    @FormUrlEncoded
    @POST("kegiatan/update/{id}")
    Call<ResponseUpdateKegiatan> updateKegiatan(
            @Path("id") int id,
            @Field("nama_kegiatan") String nama_kegiatan,
            @Field("bidang_kegiatan") String bidang_kegiatan,
            @Field("deskripsi_kegiatan") String deskripsi_kegiatan,
            @Field("tgl_kegiatan") String tgl_kegiatan
    );

    @FormUrlEncoded
    @POST("update-profile/{id}")
    Call<ResponseUpdateProfile> updateProfile(
            @Path("id") int id,
            @Field("username") String username,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("nim") String nim,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("fcm-token")
    Call<com.example.Modul5Progmob.model.fcmtoken.Response> fcmToken(
            @Field("id") int id,
            @Field("token") String token
    );

    @GET("kegiatan")
    Call<Response> getKegiatan();

    @GET("kegiatan/get-data-user/{id}")
    Call<com.example.Modul5Progmob.model.getkegiatanuser.Response> getKegiatanUser(
            @Path("id") int id
    );

    @GET("kegiatan/delete/{id}")
    Call<ResponseDeleteKegiatan> deleteKegiatan(
            @Path("id") int id
    );


}
