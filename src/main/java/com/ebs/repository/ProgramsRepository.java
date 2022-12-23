package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.ebs.entity.Programs;


@Repository
public interface ProgramsRepository extends JpaRepository<Programs, Long>{
	List findByGroupName(String groupName);
//	 @Query("select entity.program from Program entity  where entity.groupName = :groupName")
//	List findProgram(String groupName);
//	 
//	 @Query("select user.firstName, user.lastName from UserEntity user where user.userId = :userId")
//	 List<Object[]> getUserEntityFullNameById(@Param("userId") String userId);
	 @Query("SELECT entity.programs FROM  Program entity")
	List findallprograms(Programs programs);

}
