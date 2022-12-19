package com.ebs.service;

import java.util.InputMismatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.custom.service.CustomUserDetails;
import com.ebs.entity.NewDatabaseProfile;
import com.ebs.entity.User;
import com.ebs.model.UserModel;
import com.ebs.repository.UserRepository;
import com.ebs.repository.DatabaseProfileRepository;

@Service
public class UserServiceImplimentation implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	DatabaseProfileRepository dbRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User register(UserModel userModel) {
		System.out.println("Register Method Calling");
		User user = new User();
		if (!(userRepo.findByEmail(userModel.getEmail())==null)) {
			throw new DuplicateKeyException("Email ID Already Exsists");
		}else if (!(userRepo.findByUserName(userModel.getUserName())==null)) {
			throw new DuplicateKeyException("UserName Already Exsists");
		}else {
			user.setEmail(userModel.getEmail().toLowerCase());
			user.setUserName(userModel.getUserName().toLowerCase());
			user.setRole("ROLE_"+userModel.getRole().toUpperCase());
			if (userModel.getPassword().equals(userModel.getMatchingPassword())) {
				user.setPassword(passwordEncoder.encode(userModel.getPassword()));
			}else {
				throw new InputMismatchException();
			}
//			user.setPassword(passwordEncoder.encode(userModel.getPassword()));
			userRepo.save(user);
			
		}
		return user;
	}


	@Override
	public User getUserByUserName(String userName) {
		User user = userRepo.findByUserName(userName);
		return user;
	}


	@Override
	public NewDatabaseProfile createdatabase(NewDatabaseProfile newDatabaseProfile) {
		System.out.println("new database created");
		NewDatabaseProfile np = newDatabaseProfile;
//		NewDatabaseProfile  newuser = new NewDatabaseProfile();
		np=dbRepo.save(newDatabaseProfile);
		
		return np;
	}


	

	



}
