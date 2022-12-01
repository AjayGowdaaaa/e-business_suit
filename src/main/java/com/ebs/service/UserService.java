package com.ebs.service;


import com.ebs.entity.User;
import com.ebs.model.UserModel;

public interface UserService {

	User register(UserModel userModel);

	User getUserByUserName(String userName);

}
