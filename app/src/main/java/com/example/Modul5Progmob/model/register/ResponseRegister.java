package com.example.Modul5Progmob.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("success")
	private boolean success;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}
}