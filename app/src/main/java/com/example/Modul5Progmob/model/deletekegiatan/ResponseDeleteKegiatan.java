package com.example.Modul5Progmob.model.deletekegiatan;

import com.google.gson.annotations.SerializedName;

public class ResponseDeleteKegiatan{

	@SerializedName("success")
	private boolean success;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}
}