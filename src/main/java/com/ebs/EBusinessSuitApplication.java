package com.ebs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ebs.entity.User;
import com.ebs.repository.UserRepository;
import com.ebs.service.UserService;

@SpringBootApplication
public class EBusinessSuitApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EBusinessSuitApplication.class, args);
		UserRepository userRepo = context.getBean(UserRepository.class);
		User u =userRepo.findByUserName("ADMINISTRATOR");
		if (u == null) {
			User user = new User();
			user.setId(0L);
			user.setUserName("ADMINISTRATOR");
			user.setPassword("$2a$11$DyLFeqNWXGu4zEmuIy/SHuVcVNOxV2mAxPAQFl4WxIPCGnzGXyb8K");
			user.setEmail("admin@admin.com");
			user.setRole("ROLE_ADMIN");
			userRepo.save(user);
		}
		
	}
}
