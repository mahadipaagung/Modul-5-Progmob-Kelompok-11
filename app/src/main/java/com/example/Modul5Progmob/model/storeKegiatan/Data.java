package com.example.Modul5Progmob.model.storeKegiatan;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("nama_kegiatan")
	private String namaKegiatan;

	@SerializedName("deskripsi_kegiatan")
	private String deskripsiKegiatan;

	@SerializedName("bidang_kegiatan")
	private String bidangKegiatan;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id")
	private int id;

	@SerializedName("tgl_kegiatan")
	private String tglKegiatan;

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

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTglKegiatan(String tglKegiatan){
		this.tglKegiatan = tglKegiatan;
	}

	public String getTglKegiatan(){
		return tglKegiatan;
	}
}