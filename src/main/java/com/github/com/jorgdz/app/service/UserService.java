package com.github.com.jorgdz.app.service;

import java.util.List;

import com.github.com.jorgdz.app.entity.User;

public interface UserService {
	
	User findByEmail (String email);
	
	List<User> findAll ();
	
}
