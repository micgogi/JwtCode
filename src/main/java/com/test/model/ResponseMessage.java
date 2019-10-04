package com.test.model;

public class ResponseMessage {

	String message;
	int statusCode;


	public ResponseMessage(String message, int statusCode) {
		
		this.message = message;
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
