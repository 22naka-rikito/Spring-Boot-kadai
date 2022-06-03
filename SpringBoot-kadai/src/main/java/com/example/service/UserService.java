package com.example.service;

import com.example.controller.form.IndexForm;
import com.example.entity.User;

public interface UserService {
	public User findUser(IndexForm form);
}