package com.example.entity;

public class User{
	private String loginId;
	private String pass;
	private String name;
	private Integer role;
	
	public User() {}
	
	public User(String loginId, String pass, String name) {
		this.loginId = loginId;
		this.pass = pass;
		this.name = name;
	}
	
	public void setLoginId(String id) {
		this.loginId = id;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setRole(Integer role) {
		this.role = role;
	}
	
	public Integer getRole() {
		return this.role;
	}
}