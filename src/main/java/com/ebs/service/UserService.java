package com.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.entity.User;
import com.ebs.exception.BusinessException;
import com.ebs.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserRepository userRepo;
	@Autowired 
	PasswordEncoder passwordEncoder;

	@Override
	public User register(User user) {
		if (!(userRepo.findByEmail(user.getEmail()) == null)) {
			throw new DuplicateKeyException("Email ID Already Exsists");
		} else if (!(userRepo.findByUserName(user.getUserName()) == null)) {
			throw new DuplicateKeyException("UserName Already Exsists");
		} else {
			user.setEmail(user.getEmail().toLowerCase());
			user.setUserName(user.getUserName().toUpperCase());
			user.setRole("ROLE_" + user.getRole().toUpperCase());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(user);
		}
		return user;
	}

	@Override
	public User update(Long id, User user) {
		User exsistingUser = userRepo.findById(id).orElseThrow(
				() -> new BusinessException("User does not exsits in Repository", "Please Enter valid profileName"));
		exsistingUser.setUserName(user.getUserName());
		exsistingUser.setPassword(user.getPassword());
		exsistingUser.setEmail(user.getEmail());
		exsistingUser.setRole("ROLE_" + user.getRole().toUpperCase());
		try {
			userRepo.save(exsistingUser);

		} catch (Exception e) {
			throw new BusinessException("update user ", "Failed to Update User " + e.getMessage());
		}
		return exsistingUser;
	}

	@Override
	public String delete(Long id) {
		return null;

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

	@Override
	public User creatingDefautUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
