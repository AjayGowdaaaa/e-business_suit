package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.ebs.entity.GroupData;

@Repository
public interface GroupRepository extends JpaRepository<GroupData, Long>{

	GroupData save(GroupData groupData);
	GroupData findByGroupName(String groupName);
	/*
	 * Fetching all group 
	 */
	@Query("SELECT entity.groupName FROM  GroupData entity")
	List<?> findallgroups(GroupData groupData);
	/*
	 * fetching group programs
	 */
	@Query("SELECT entity.assignPrograms FROM  GroupData entity where entity.groupName = :groupName")
	List<List> findprogram(String groupName);
	
	
	
}
