package com.springboot.demo.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Login implements Serializable{
	private Integer ID;
	private String RESOURCENAME;
	private String USERID;
	private String RESOURCEURL;
}
