package com.ebs.service;

import java.util.ArrayList;
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

	List<GroupData> getAllGroupdata();

	Programs addprogram(Programs program);

	GroupData getGroupById(Long id);

	List  listOfGroupNames( GroupData groupData) ;

	ArrayList FetchingAllPrograms(Programs program);

	List  getProgram( String groupName) ;

}