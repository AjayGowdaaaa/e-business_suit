package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.entity.GroupCreation;

@Repository
public interface GroupRepository extends JpaRepository<GroupCreation, Long>{

	GroupCreation save(GroupCreation groupCreation);
	GroupCreation findByGroupName(String groupName);
}
