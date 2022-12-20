package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.ebs.entity.NewDatabaseProfile;
=======
>>>>>>> d166c42dfe7ab839eb2702758a80a1775e9cc220
import com.ebs.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String userName);
	User findByEmail(String email);

	
	
}
