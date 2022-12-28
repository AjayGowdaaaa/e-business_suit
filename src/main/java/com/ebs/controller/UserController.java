package com.ebs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ebs.entity.User;
import com.ebs.exception.BusinessException;
import com.ebs.exception.ControllerException;
import com.ebs.service.UserServiceInterface;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceInterface service;

	
	@PostMapping("/register")
	public ResponseEntity<?> register( @RequestBody User user) {
		try {
			User savingUser = service.register(user);
			return new ResponseEntity<User>(savingUser, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to register User","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {	
		try {
			User user = service.getUserById(id);
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to get User","Something went wrong on Controller");					
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/username/{userName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable("userName") String userName) {	
		try {
			User user = service.getUserByUserName(userName);
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to get User","Something went wrong on Controller");					
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id")Long id,@RequestBody User user) {	
		try {
			User updating = service.update(id,user);
			return new ResponseEntity<User>(updating, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Update User","Something went wrong on Controller");					
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Long id) {	
		try {
			service.delete(id);

			return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Delete User","Something went wrong on Controller");					
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

}
