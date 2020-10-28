package com.github.com.jorgdz.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.com.jorgdz.app.entity.User;
import com.github.com.jorgdz.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User findByEmail(String email) 
	{
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> findAll () {
		return this.userRepo.findAll();
	}

}
