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

}
