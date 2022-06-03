package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.entity.User;

@Repository
public class PgUserDao implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_USER = "SELECT * FROM users "
			+ "WHERE login_id = :login_id AND password = :pass";

	@Override
	public User findUser(String loginId, String pass) {
		String sql = SQL_SELECT_USER;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("login_id", loginId);
        param.addValue("pass", pass);
        List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
        return userList.isEmpty() ? null : userList.get(0);
	}
}