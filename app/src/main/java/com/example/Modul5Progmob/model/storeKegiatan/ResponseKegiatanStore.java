package com.example.Modul5Progmob.model.storeKegiatan;

import com.google.gson.annotations.SerializedName;

public class ResponseKegiatanStore{

	@SerializedName("data")
	private Data data;

	@SerializedName("success")
	private boolean success;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}
}