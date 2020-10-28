package com.github.com.jorgdz.app.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.com.jorgdz.app.entity.User;
import com.github.com.jorgdz.app.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService serviceUser;
	
	@GetMapping(value = {"/", "/index"})
	public String index (Map<String, Object> map) {
		map.put("message", "Bienvenido al Home Page !!");
		map.put("title", "Home");
		return "views/home";
	}
	
	@GetMapping(value = {"/create"})
	public String create (Map<String, Object> map) {
		map.put("message", "Tu rol te permite crear usuarios !!");
		map.put("title", "Crear usuario");
		return "views/create";
	}
	
	@GetMapping(value = {"/list"})
	public String all (Map<String, Object> map) {
		List<User> users = serviceUser.findAll();
		map.put("title", "Usuarios");
		map.put("message", "Tu rol te permite listar usuarios !!");
		map.put("users", users);
		return "views/list";
	}
	
	@ModelAttribute
	public User user (Principal principal) {
		if(principal != null) {
			return serviceUser.findByEmail(principal.getName());
		}
		
		return null;
	}
}
