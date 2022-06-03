package com.example.dao;

import java.util.List;

import com.example.entity.Product;

public interface ProductDao{
	public List<Product> findProduct(String keyWord);
	public Product findById(Integer id);
	public Product findByProductId(Integer productId);
	public void insertProduct(Integer productId, String productName, Integer price, Integer categoryId, String description);
	public void deleteProduct(Integer productId);
	public void updateProduct(Integer id, Integer productId, String productName, Integer price);
}