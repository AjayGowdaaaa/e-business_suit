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

	/*
	 * Converted a servlet into Controller
	 * NOTE = Need to do Write Business Logic and Need to change get and post method
	 * 
	 */
	
	@GetMapping("/addPrograms")
	public String addPrograms() {
		return "/servlet/addPrograms";
	}
	@GetMapping("/Archiveenv")
	public String Archiveenv() {
		return "/servlet/Archiveenv";
	}
	@GetMapping("/ArchiveEnvironment")
	public String ArchiveEnvironment() {
		return "/servlet/ArchiveEnvironment";
	}
	@GetMapping("/GrantSys")
	public String GrantSys() {
		return "/servlet/GrantSys";
	}
	@GetMapping("/datapath")
	public String datapath() {
		return "/servlet/datapath";
	}
	@GetMapping("/ArchiveDatabase")
	public String ArchiveDatabase() {
		return "/servlet/ArchiveDatabase";
	}
	@GetMapping("/ArchiveFileInfo")
	public String ArchiveFileInfo() {
		return "/servlet/ArchiveFileInfo";
	}
	@GetMapping("/buildTree")
	public String buildTree() {
		return "/servlet/buildTree";
	}
	@GetMapping("/changePassword")
	public String changePassword() {
		return "/servlet/changePassword";
	}
	@GetMapping("/checkUser")
	public String checkUser() {
		return "/servlet/checkUser";
	}
	@GetMapping("/configuration")
	public String configuration() {
		return "/servlet/configuration";
	}
	@GetMapping("/DetailReport")
	public String DetailReport() {
		return "/servlet/DetailReport";
	}
	@GetMapping("/DBProfile")
	public String DBProfile() {
		return "/servlet/DBProfile";
	}
	@GetMapping("/Diagnostics")
	public String Diagnostics() {
		return "/servlet/Diagnostics";
	}
	@GetMapping("/Group")
	public String Group() {
		return "/servlet/Group";
	}
	@GetMapping("/Job")
	public String Job() {
		return "/servlet/Job";
	}
	@GetMapping("/logon")
	public String logon() {
		return "/servlet/logon";
	}
	@GetMapping("/logout")
	public String logout() {
		return "/servlet/logout";
	}
	@GetMapping("/programs")
	public String programs() {
		return "/servlet/programs";
	}
	@GetMapping("/purgeJobs")
	public String purgeJobs() {
		return "/servlet/purgeJobs";
	}
	@GetMapping("/User")
	public String User() {
		return "/servlet/User";
	}
	@GetMapping("/viewJobs")
	public String viewJobs() {
		return "/servlet/viewJobs";
	}
	@GetMapping("/webdav")
	public String webdav() {
		return "/servlet/webdav";
	}



}
