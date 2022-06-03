package com.example.dao;

import com.example.entity.User;

public interface UserDao{
	public User findUser(String loginId, String pass);
}