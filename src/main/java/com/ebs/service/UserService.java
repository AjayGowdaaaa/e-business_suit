package com.ebs.service;


import java.util.List;

import com.ebs.entity.Assigned_Programs;
//import com.ebs.entity.ChangePassword_Optim;
import com.ebs.entity.GroupData;
//import com.ebs.entity.Programs;
import com.ebs.entity.User;
import com.ebs.model.UserModel;

public interface UserService {

	User register(UserModel userModel);

	User getUserByUserName(String userName);
	
	
	//Group Creation
	GroupData newGroup(GroupData groupData);
	
	GroupData getGroupCreationByGroupName(String groupName);
	void deleteGroupbyName(String groupName);
	GroupData modifyGroup(String groupName, GroupData groupData);
	User changePassword(String userName, User user) throws Exception;
//Assigned Programs
	Assigned_Programs AssignedPrograms(Assigned_Programs programs) throws Throwable;
	List  assignGroups( GroupData groupData) throws Exception, Throwable;
	List  getPrograms( GroupData groupData) throws Throwable;
	List  get_Particular_Program( String groupName) throws Exception;

}
