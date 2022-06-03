package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.entity.Product;

@Repository
public class PgProductDao implements ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_USER = "SELECT p.id, p.product_id, p.name AS product_name"
			+ ", p.price, p.category_id, c.name AS category_name "
			+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
			+ "WHERE p.name LIKE :keyWord OR c.name LIKE :keyWord "
			+ "ORDER BY p.id";
	
	private static final String SELECT_BY_ID = "SELECT p.id, p.product_id, p.name AS product_name"
			+ ", p.price, p.category_id, c.name AS category_name, p.description "
			+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
			+ "WHERE p.id = :id";
	
	private static final String SELECT_BY_PRODUCT_ID = "SELECT * FROM products WHERE product_id = :product_id";
	
	private static final String INSERT_PRODUCTS = "INSERT INTO products "
			+ "(product_id, category_id, name, price, description) "
			+ "values(:product_id, :category_id, :name, :price, :description)";
	
	private static final String DELETE_PRODUCTS = "DELETE FROM products WHERE product_id = :product_id";
	
	private static final String UPDATE_PRODUCTS = "UPDATE products SET product_id = :product_id, "
			+ "name = :product_name, "
			+ "price = :price WHERE id = :id";
	
	@Override
	public List<Product> findProduct(String keyWord) {
		String sql = SQL_SELECT_USER;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("keyWord", "%" + keyWord + "%");
        List<Product> productList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
        return productList.isEmpty() ? null : productList;
	}
	
	@Override
	public Product findById(Integer id) {
    	String sql = SELECT_BY_ID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
        return resultList.isEmpty() ? null : resultList.get(0);
    }
	
	@Override
	public Product findByProductId(Integer productId) {
    	String sql = SELECT_BY_PRODUCT_ID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_id", productId);
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
        return resultList.isEmpty() ? null : resultList.get(0);
    }

	@Override
	public void insertProduct(Integer productId, String productName, Integer price, Integer categoryId,
			String description) {
		String sql = INSERT_PRODUCTS;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_id", productId);
        param.addValue("category_id", categoryId);
        param.addValue("name", productName);
        param.addValue("price", price);
        param.addValue("description", description);
        jdbcTemplate.update(sql, param);
	}
	
	@Override
	public void deleteProduct(Integer productId) {
    	String sql = DELETE_PRODUCTS;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_id", productId);
        jdbcTemplate.update(sql, param);
    }
	
	@Override
	public void updateProduct(Integer id, Integer productId, String productName, Integer price) {
		String sql = UPDATE_PRODUCTS;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("product_id", productId);
        param.addValue("product_name", productName);
        param.addValue("price", price);
        jdbcTemplate.update(sql, param);
	}
}