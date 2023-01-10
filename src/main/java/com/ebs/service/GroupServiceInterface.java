package com.ebs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.ebs.entity.GroupData;
import com.ebs.entity.Programs;
@Service
public interface GroupServiceInterface {

	GroupData newGroup(GroupData groupData);
	
	List<GroupData> getAllGroupdata();

	HashMap<String, Object> getAllListOf_ProgramsAndGroup() throws Exception;

	void deleteGroupbyid(Long  id);

	GroupData assignPrograms(Long id, GroupData groupData);

	GroupData modifyGroup(Long id, GroupData groupData);

	Programs addprogram(Programs program);
	
	GroupData getGroupById(Long id);
	
	GroupData assignData(String groupName,String assignPrograms);
	
	
	
	
	
	
	
	
//
//	GroupData getGroupById(Long id);
//
//	List  listOfGroupNames( GroupData groupData) ;
//
//	ArrayList FetchingAllPrograms(Programs program);
//
//	HashMap<Long, Object>  getProgram( Long id) ;
//
//	void deleteAndSaveFormAccess(int group, List<Integer> selectedArr);
//	
	
	
	//GroupData getGroupDataByGroupName(String groupName);
	
	//Map<Long, ArrayList<String>> getAllGroupdata();

}