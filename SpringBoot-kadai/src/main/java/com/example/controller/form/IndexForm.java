package com.example.controller.form;

import javax.validation.constraints.NotBlank;

public class IndexForm{
	@NotBlank
	private String loginId;
	@NotBlank
	private String pass;
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass() {
		return this.pass;
	}
}