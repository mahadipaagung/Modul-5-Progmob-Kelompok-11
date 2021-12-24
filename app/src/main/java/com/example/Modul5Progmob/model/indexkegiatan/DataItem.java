package com.example.Modul5Progmob.model.indexkegiatan;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="kegiatan")
public class DataItem{

	@SerializedName("updated_at")
	@ColumnInfo(name = "updated_at")
	private String updatedAt;

	@SerializedName("nama_kegiatan")
	@ColumnInfo(name = "nama_kegiatan")
	private String namaKegiatan;

	@SerializedName("deskripsi_kegiatan")
	@ColumnInfo(name = "deskripsi_kegiatan")
	private String deskripsiKegiatan;

	@SerializedName("bidang_kegiatan")
	@ColumnInfo(name = "bidang_kegiatan")
	private String bidangKegiatan;

	@SerializedName("created_at")
	@ColumnInfo(name = "created_at")
	private String createdAt;

	@PrimaryKey(autoGenerate = true)
	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	@ColumnInfo(name = "id_user")
	private int idUser;

	@SerializedName("tgl_kegiatan")
	@ColumnInfo(name = "tgl_kegiatan")
	private String tglKegiatan;

	@Ignore
	@SerializedName("user")
	private User user;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setNamaKegiatan(String namaKegiatan){
		this.namaKegiatan = namaKegiatan;
	}

	public String getNamaKegiatan(){
		return namaKegiatan;
	}

	public void setDeskripsiKegiatan(String deskripsiKegiatan){
		this.deskripsiKegiatan = deskripsiKegiatan;
	}

	public String getDeskripsiKegiatan(){
		return deskripsiKegiatan;
	}

	public void setBidangKegiatan(String bidangKegiatan){
		this.bidangKegiatan = bidangKegiatan;
	}

	public String getBidangKegiatan(){
		return bidangKegiatan;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setTglKegiatan(String tglKegiatan){
		this.tglKegiatan = tglKegiatan;
	}

	public String getTglKegiatan(){
		return tglKegiatan;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}