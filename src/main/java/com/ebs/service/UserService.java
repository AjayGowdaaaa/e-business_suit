package com.ebs.service;


import java.util.List;

//import com.ebs.entity.ChangePassword_Optim;
import com.ebs.entity.GroupCreation;
import com.ebs.entity.Programs;
import com.ebs.entity.User;
import com.ebs.model.UserModel;

public interface UserService {

	User register(UserModel userModel);

	User getUserByUserName(String userName);
	User updatePassword(long id, String password) throws Exception, Throwable;
	GroupCreation newGroup(GroupCreation groupCreation);
	
	
	
    // Programs new_program_creation(Programs programs );
    // public List<Programs> assingProgram(Programs programs,String groupName);
     public List  getPrograms( Programs programs);
     
 	public List  assignGroups( GroupCreation groupCreation);

 	
 	
 	
	GroupCreation getGroupCreationByGroupName(String groupName);
	void deleteGroupbyName(String groupName);


	GroupCreation modifyGroup(String groupName, GroupCreation groupCreation);

	User changePassword(String userName, User user) throws Exception;
	

//	ChangePassword_Optim changePassword_optim(String userName, ChangePassword_Optim changePasswordOptims);
}
