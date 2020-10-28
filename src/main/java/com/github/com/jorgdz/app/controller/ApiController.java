package com.github.com.jorgdz.app.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {
	
	@GetMapping(value = "/api/data", produces = "application/json")
	public ResponseEntity<?> getData () {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("nombre", "Martha");
		data.put("apellido", "Kent");
		data.put("fechaNac", "1994-05-11");
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
