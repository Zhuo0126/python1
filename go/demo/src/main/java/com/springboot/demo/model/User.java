package com.springboot.demo.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	private static final long serialVersionUID = -5440372534300871944L;
	
	private String username;
	private String password;
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
