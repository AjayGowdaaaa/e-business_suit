package com.ebs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebs.entity.GroupData;
import com.ebs.entity.Programs;
@Service
public interface GroupServiceInterface {

	GroupData newGroup(GroupData groupData);

	GroupData getGroupDataByGroupName(String groupName);

	void deleteGroupbyid(Long  id);

	GroupData assignPrograms(Long id, GroupData groupData);

	GroupData modifyGroup(Long id, GroupData groupData);

	List<GroupData> getAllGroupCreations();

	Programs addprogram(Programs program);

	List<Programs> getAllPrograms();

	GroupData getGroupById(Long id);
	
	
}