package com.example.entity;

public class Category{
	private Integer id;
	private String name;
	
	public Category(){}
	
	public void setId(Integer categoryId) {
		this.id = categoryId;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String categoryName) {
		this.name = categoryName;
	}
	
	public String getName() {
		return this.name;
	}
}