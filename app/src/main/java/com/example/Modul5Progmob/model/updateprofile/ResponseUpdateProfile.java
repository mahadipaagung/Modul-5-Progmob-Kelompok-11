package com.example.Modul5Progmob.model.updateprofile;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateProfile{

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