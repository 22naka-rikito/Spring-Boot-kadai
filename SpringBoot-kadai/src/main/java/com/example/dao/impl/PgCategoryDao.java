package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryDao;
import com.example.entity.Category;

@Repository
public class PgCategoryDao implements CategoryDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String CATEGORY_ALL = "SELECT id, name FROM categories";

	@Override
	public List<Category> findAll() {
		String sql = CATEGORY_ALL;
		MapSqlParameterSource param = new MapSqlParameterSource();
		List<Category> categoryList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));
		return categoryList.isEmpty() ? null : categoryList;
	}
	
}