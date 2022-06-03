package com.example.service;

import java.util.List;

import com.example.controller.form.DetailForm;
import com.example.controller.form.InsertForm;
import com.example.entity.Product;

public interface ProductService {
	public List<Product> findProduct(String keyWord);
	public Product findById(Integer id);
	public Product findByProductId(Integer form);
	public void insertProduct(InsertForm form);
	public void deleteProduct(DetailForm form);
	public void updateProduct(Integer id, Integer productId, String productName, Integer price);
}