package com.ebs.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.entity.Assigned_Programs;
//import com.ebs.entity.ChangePassword_Optim;
import com.ebs.entity.GroupData;
//import com.ebs.entity.Programs;
import com.ebs.entity.User;
import com.ebs.model.UserModel;
import com.ebs.repository.AssignedPrograms_Repository;
//import com.ebs.repository.ChangePaswordRepository;
import com.ebs.repository.GroupRepository;
//import com.ebs.repository.ProgramsRepository;
import com.ebs.repository.UserRepository;

@Service
public class UserServiceImplimentation implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	GroupRepository groupRepository;
@Autowired
AssignedPrograms_Repository assingRepo;

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
	public GroupData newGroup(GroupData groupData) {
		ArrayList<String> pgm = new ArrayList<String>();
		pgm.addAll(groupData.getPrograms());
		GroupData savedGroup = groupData;
		savedGroup.setPrograms(pgm);
		savedGroup=groupRepository.save(groupData);
		return savedGroup;
	}

	
	
	/*
	 * Deleting a Group using GroupName
	 */
	@Override
	public void deleteGroupbyName(String groupName) {
		GroupData gc =	groupRepository.findByGroupName(groupName);
		groupRepository.delete(gc);
	}
	



	@Override
	public GroupData modifyGroup(String groupName, GroupData groupData) {
		GroupData modifySaved = groupData;
		modifySaved=groupRepository.findByGroupName(modifySaved.getGroupName());
		modifySaved.setGroupName(groupData.getGroupName());	
		modifySaved.setPrograms(groupData.getPrograms()); 
		modifySaved.setDescription(groupData.getDescription());
		groupRepository.save(modifySaved);
		return modifySaved;
	}


	@Override
	public GroupData getGroupCreationByGroupName(String groupName) {
		GroupData gc = groupRepository.findByGroupName(groupName);
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
	


	



}
