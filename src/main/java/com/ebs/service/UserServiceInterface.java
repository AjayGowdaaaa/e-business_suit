//Service interface
package com.ebs.service;


import com.ebs.entity.User;


public interface UserServiceInterface {

	User register(User user);
	User update(Long id, User user);
	void delete (Long id);
	User getUserById(Long id);
	
	User getUserByUserName(String userName);
	User getUserByEmail(String email);
	
	
	
}
