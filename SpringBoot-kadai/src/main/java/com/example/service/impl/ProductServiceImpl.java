package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.form.DetailForm;
import com.example.controller.form.InsertForm;
import com.example.dao.ProductDao;
import com.example.entity.Product;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findProduct(String keyWord) {
		return productDao.findProduct(keyWord);
	}
	
	@Override
	public Product findById(Integer id) {
		return productDao.findById(id);
	}
	
	@Override
	public Product findByProductId(Integer productId) {
		return productDao.findByProductId(productId);
	}

	@Override
	public void insertProduct(InsertForm form) {
		productDao.insertProduct(form.getProductId(), form.getProductName(), form.getPrice(), form.getCategoryId(),
				form.getDescription());
	}

	@Override
	public void deleteProduct(DetailForm form) {
		productDao.deleteProduct(form.getProductId());
	}

	@Override
	public void updateProduct(Integer id, Integer productId, String productName, Integer price) {
		productDao.updateProduct(id, productId, productName, price);
	}

}