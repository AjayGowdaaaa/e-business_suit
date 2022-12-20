package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import com.ebs.entity.NewDatabaseProfile;
import com.ebs.entity.User;

@Repository
public interface DatabaseProfileRepository extends JpaRepository<NewDatabaseProfile,String>{

	

	
	
=======

import com.ebs.entity.DatabaseProfile;

public interface DatabaseProfileRepository extends JpaRepository<DatabaseProfile,  String > {

	DatabaseProfile findByProfileName(String profileName);
>>>>>>> d166c42dfe7ab839eb2702758a80a1775e9cc220
}
