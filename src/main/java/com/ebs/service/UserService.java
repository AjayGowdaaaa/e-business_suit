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


	@Override
	public User register(User user) {
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

<<<<<<< HEAD

=======
	User getUserByUserName(String userName);
	
	
	//Group Creation
	GroupData newGroup(GroupData groupData);
	
	GroupData getGroupCreationByGroupName(String groupName);
	void deleteGroupbyName(String groupName);
	GroupData modifyGroup(String groupName, GroupData groupData);
	User changePassword(String userName, User user) throws Exception;
////Assigned Programs
//	Assigned_Programs AssignedPrograms(Assigned_Programs programs) throws Throwable;
//	List  assignGroups( GroupData groupData) throws Exception, Throwable;
//	List  getPrograms( GroupData groupData) throws Throwable;
//	List  get_Particular_Program( String groupName) throws Exception;
>>>>>>> 894987a16273b9176bb1fa2b689c212da116b41b
}
