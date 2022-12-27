package com.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.ebs.entity.User;
import com.ebs.exception.BusinessException;
import com.ebs.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserRepository userRepo;

	public User creatingDefautUser() {
		User defaultUser=null;
		User u = userRepo.findByUserName("admin");
		User id = userRepo.findById(1L).get();
		System.out.println("FIND BY USERNAME"+u);
		System.out.println("FIND BY ID"+id);
		System.out.println(u);
		if ((u ==null && id==null)) {
			System.out.println("Creating DEFAULT USER ***********************************************************************************************************************************************************");
			 defaultUser = new User(0L, "admin@gamil.com", "admin", "admin", "ROLE_ADMIN");
			defaultUser=	userRepo.save(defaultUser);
			return defaultUser;
		}else {
			return null;
		}
		
		
	}

	@Override
	public User register(User user) {
		if (userRepo.findByUserName("admin")==null) {
			creatingDefautUser();
		}
		
		System.out.println("Register Method Calling");
		if (!(userRepo.findByEmail(user.getEmail())==null)) {
			throw new DuplicateKeyException("Email ID Already Exsists");
		}else if (!(userRepo.findByUserName(user.getUserName())==null)) {
			throw new DuplicateKeyException("UserName Already Exsists");
		}else {
			user.setEmail(user.getEmail().toLowerCase());
			user.setUserName(user.getUserName().toLowerCase());
			user.setRole("ROLE_"+user.getRole().toUpperCase());	
			userRepo.save(user);	
		}
		return user;
	}
	@Override
	public User update(Long id,User user) {
		User exsistingUser = userRepo.findById(id).orElseThrow(() -> new BusinessException("User does not exsits in Repository", "Please Enter valid profileName"));
		exsistingUser.setUserName(user.getUserName());
		exsistingUser.setPassword(user.getPassword());
		exsistingUser.setEmail(user.getEmail());
		exsistingUser.setRole("ROLE_"+user.getRole().toUpperCase());
		try {
			userRepo.save(exsistingUser);

		} catch (Exception e) {
			throw new BusinessException("update user ","Failed to Update User " + e.getMessage());	
		}
		return exsistingUser;
	}


	@Override
	public void delete(Long id) {
		User exsistingUser = userRepo.findById(id).orElseThrow(() -> new BusinessException("User does not exsits in Repository", "Please Enter valid profileName"));
		userRepo.delete(exsistingUser);	
	}

	@Override
	public User getUserById(Long id) {
		User user = userRepo.findById(id).get();
		return user;
	}

	@Override
	public User getUserByUserName(String userName) {
		User user = userRepo.findByUserName(userName);
		return user;
	}


	@Override
	public User getUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		return user;
	}

	



}
