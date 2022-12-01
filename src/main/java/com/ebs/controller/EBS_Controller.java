package com.ebs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ebs.entity.User;
import com.ebs.model.UserModel;
import com.ebs.service.UserService;

/*
 * 29/11/2022
 * Admin acces is working fine
 * default pages also working fine
 *  NOTE-
 *  1- Register page not working
 *  2- User access is not working
 */

@RestController
@RequestMapping("/ebs")
public class EBS_Controller {

	@Autowired
	private UserService service;

	
	@GetMapping("/wc")
	public String ebs() {
		return "Welcome to EBS in EBS Controller";
	}
	@GetMapping("/home")
	public String home() {
		return "Welcome Home in EBS Controller";
	}
	@GetMapping("/user")
	public String user() {
		return "USER accesing this method in EBS Controller";
	}
	@GetMapping("/admin")
	public String admin() {
		return "Admin Method Calling";
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register( @RequestBody UserModel userModel) {
		System.out.println("Register Method Calling");
		User user = service.register(userModel);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable("userName") String userName) {	
		User user = service.getUserByUserName(userName);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);


	}


}
