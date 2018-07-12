package com.usuario.api.util;

public abstract class CustomResponseType {

	private int responseCode;
	
	private String message;	

	public CustomResponseType() {		
	}

	public CustomResponseType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
