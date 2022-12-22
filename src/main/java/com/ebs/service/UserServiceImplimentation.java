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
//import com.ebs.entity.ChangePassword_Optim;
import com.ebs.entity.GroupCreation;
import com.ebs.entity.User;
import com.ebs.model.UserModel;
//import com.ebs.repository.ChangePaswordRepository;
import com.ebs.repository.GroupRepository;
import com.ebs.repository.UserRepository;

@Service
public class UserServiceImplimentation implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
    GroupRepository groupRepository;
	//@Autowired
//	ChangePaswordRepository OptimRepo;
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
	public User updatePassword(long id, String password) throws Exception {
	if(userRepo.findByid(id).equals(null)) {
		throw new Exception("Entered null value , Please Enter Valid ID\"");
	}
	User user= userRepo.findByid(id);
		
	user.setId(id);
	user.setPassword(passwordEncoder.encode(password));
	
	userRepo.save(user);
	
	
		return user;
	}
	
	
	@Override
	public GroupCreation newGroup(GroupCreation groupCreation) {
		GroupCreation savedGroup = groupCreation;
		if (!(groupRepository.findByGroupName(savedGroup.getGroupName()) == null))
		{
			throw new DuplicateKeyException("GroupName Already Exsists");
		}else {
		  savedGroup=groupRepository.save(groupCreation);
		}
		return savedGroup;
	}
	/*
	 * Deleting a Group using GroupName
	 */
	@Override
	public void deleteGroupbyName(String groupName) {
	GroupCreation gc =	groupRepository.findByGroupName(groupName);
	groupRepository.delete(gc);
	}
	/*
	 * Assign Programs to the group
	 */
	@Override
	public GroupCreation assignPrograms(String groupName,  GroupCreation groupCreation) {
		GroupCreation savedPrograms = groupCreation;
		savedPrograms=groupRepository.findByGroupName(savedPrograms.getGroupName());
		savedPrograms.setGroupName(groupCreation.getGroupName());	
		savedPrograms.setAssignPrograms(groupCreation.getAssignPrograms());
		
		groupRepository.save(savedPrograms);
		
		return savedPrograms;
	}
	

	@Override
	public GroupCreation modifyGroup(String groupName, GroupCreation groupCreation) {
		GroupCreation modifySaved = groupCreation;
		modifySaved=groupRepository.findByGroupName(modifySaved.getGroupName());
		modifySaved.setGroupName(groupCreation.getGroupName());	
		modifySaved.setAssignPrograms(groupCreation.getAssignPrograms());
		modifySaved.setDescription(groupCreation.getDescription());
		groupRepository.save(modifySaved);
		return modifySaved;
	}


	@Override
	public GroupCreation getGroupCreationByGroupName(String groupName) {
		GroupCreation gc = groupRepository.findByGroupName(groupName);
		return gc;
	}


	@Override
	public User changePassword(String userName, User user) throws Exception {
		User userclass=user;
		userclass=userRepo.findByUserName(userName);
		if(userclass!=null) 
		{
		userclass.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(userclass);
		}
		else {
			throw new Exception(" username not found");
		}
		return userclass;
	}


//	@Override
//	public ChangePassword_Optim changePassword_optim(String userName, ChangePassword_Optim changePasswordOptims) {
//		ChangePassword_Optim ChangePasswordOptim_store= changePasswordOptims;
//		ChangePasswordOptim_store=OptimRepo.findByUserName(userName);
//		
//		if (changePasswordOptims.getPassword().equals(changePasswordOptims.getConformPassword())) 
//		{
//			ChangePasswordOptim_store.setPassword(passwordEncoder.encode(changePasswordOptims.getPassword()));
//			ChangePasswordOptim_store.setConformPassword(passwordEncoder.encode(changePasswordOptims.getConformPassword()));
//		}
//		else {
//			throw new InputMismatchException();
//		}
//
//		OptimRepo.save(changePasswordOptims);
//		return changePasswordOptims;
//	}




}
