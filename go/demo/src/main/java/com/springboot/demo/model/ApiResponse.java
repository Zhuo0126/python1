package com.springboot.demo.model;

import java.io.Serializable;
import java.util.Date;

public class ApiResponse implements Serializable{
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private boolean success;
	private String message;

	public ApiResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}
