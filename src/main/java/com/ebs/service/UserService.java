package com.ebs.service;


import java.util.List;

import com.ebs.entity.Assigned_Programs;
//import com.ebs.entity.ChangePassword_Optim;
import com.ebs.entity.GroupCreation;
//import com.ebs.entity.Programs;
import com.ebs.entity.User;
import com.ebs.model.UserModel;

public interface UserService {

	User register(UserModel userModel);

	User getUserByUserName(String userName);
	User updatePassword(long id, String password) throws Exception, Throwable;
	
	//Group Creation
	GroupCreation newGroup(GroupCreation groupCreation);
	List  assignGroups( GroupCreation groupCreation);
	List  getPrograms( GroupCreation groupCreation);
	List  get_Particular_Program( String groupName) throws Exception;
	GroupCreation getGroupCreationByGroupName(String groupName);
	void deleteGroupbyName(String groupName);
	GroupCreation modifyGroup(String groupName, GroupCreation groupCreation);
	User changePassword(String userName, User user) throws Exception;
//Assigned Programs
	Assigned_Programs AssignedPrograms(Assigned_Programs programs);


}
