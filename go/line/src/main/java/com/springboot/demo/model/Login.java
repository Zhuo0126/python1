package com.springboot.demo.model;

import java.io.Serializable;

public class Login implements Serializable{
	private Integer ID;
	private String RESOURCENAME;
	private String USERID;
	private String RESOURCEURL;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getRESOURCENAME() {
		return RESOURCENAME;
	}

	public void setRESOURCENAME(String RESOURCENAME) {
		this.RESOURCENAME = RESOURCENAME;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String USERID) {
		this.USERID = USERID;
	}

	public String getRESOURCEURL() {
		return RESOURCEURL;
	}

	public void setRESOURCEURL(String RESOURCEURL) {
		this.RESOURCEURL = RESOURCEURL;
	}
}
