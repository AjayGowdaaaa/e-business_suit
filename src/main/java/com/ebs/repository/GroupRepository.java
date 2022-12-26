package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebs.entity.GroupCreation;

@Repository
public interface GroupRepository extends JpaRepository<GroupCreation, Long>{

	
	GroupCreation findByGroupName(String groupName);
	
	 @Query("SELECT groupcreation.groupName FROM  GroupCreation groupcreation")
	List<GroupCreation> findallgroups(GroupCreation groupCreation);

	 
	 @Query("SELECT entity.programs FROM  GroupCreation entity")
	List findallprograms(GroupCreation groupCreation);
	 @Query("select entity.programs FROM  GroupCreation entity where entity.groupName = :groupName")
	List<GroupCreation> getPrograms(String groupName);
	 
//	 @Query("select entity.programs FROM  GroupCreation entity where entity.groupName = :groupName")
//	List<String> findprograms(String groupName);
	
}
