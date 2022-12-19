package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.entity.NewDatabaseProfile;
import com.ebs.entity.User;

@Repository
public interface DatabaseProfileRepository extends JpaRepository<NewDatabaseProfile,String>{

	

	
	
}
