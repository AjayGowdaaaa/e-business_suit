package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebs.entity.GroupCreation;

@Repository
public interface GroupRepository extends JpaRepository<GroupCreation, Long>{

	GroupCreation save(GroupCreation groupCreation);
	GroupCreation findByGroupName(String groupName);
	
	 @Query("SELECT groupcreation.groupName FROM  GroupCreation groupcreation")
	List<Object> findallgroups(GroupCreation groupCreation);
//	 @Query("select user.firstName, user.lastName from UserEntity user where user.userId = :userId")
//	 List<Object[]> getUserEntityFullNameById(@Param("userId") String userId);
	
	//List<GroupCreation> findAllGroups();
}
