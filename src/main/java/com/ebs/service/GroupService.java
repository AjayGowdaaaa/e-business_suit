package com.ebs.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


import com.ebs.entity.GroupData;
import com.ebs.entity.Programs;
import com.ebs.entity.User;
import com.ebs.repository.GroupRepository;
import com.ebs.repository.ProgramRepository;
import com.ebs.exception.BusinessException;
@Service
public class GroupService implements GroupServiceInterface{


	@Autowired
	GroupRepository groupRepository;
	@Autowired
	ProgramRepository programRepository;


	/*
	 * Creating a group, if Group is already exist in the database it shows exception
	 */
	@Override
	public GroupData newGroup(GroupData groupData) {
		GroupData savedGroup = groupData;
		if (!(groupRepository.findByGroupName(groupData.getGroupName()) == null))
		{
			throw new BusinessException("GroupService-Creating a Group"," Group Name is already present in database");
		}else {
			savedGroup=groupRepository.save(groupData);
		}
		return savedGroup;
	}
	/*
	 *  Fetch groups details for all the Groups
	 */
	@Override
	public List<GroupData> getAllGroupdata() {
		if(groupRepository.findAll()==null) {
			throw new BusinessException("GroupService-Get Group Data",
					" Group DataBase Table is empty");
		}
		List<GroupData> groupList = null;
		groupList = groupRepository.findAll();

		return groupList;
	}
	/*
	 * fetching particular group details based on ID
	 */
	@Override
	public GroupData getGroupById(Long id) {
		if(!groupRepository.existsById(id)) {
			throw new BusinessException("GroupService-Get Group By ID",
					" Group ID Not found in DataBase, Please enter valid ID");
		}
		GroupData groupData = groupRepository.findById(id).get();
		return groupData;
	}
	/*
	 * Assign Programs to the group based on ID
	 */
	@Override
	public GroupData assignPrograms(Long id,  GroupData groupData) {

		if(!groupRepository.existsById(id)) {
			throw new BusinessException("GroupService-Assign Programs By ID",
					" Group ID Not found in DataBase, Please enter valid ID");
		}
		GroupData savedPrograms = groupData;
		savedPrograms=groupRepository.findById(id).get();
		savedPrograms.setGroupName(groupData.getGroupName());
		savedPrograms.setAssignPrograms(groupData.getAssignPrograms());

		groupRepository.save(savedPrograms);

		return savedPrograms;
	}
	/*
	 * Modify the Group based on ID
	 * here Modify only groupName and Description not assignPrograms
	 */
	@Override
	public GroupData modifyGroup(Long id, GroupData groupData) {

		if(!groupRepository.existsById(id)) {
			throw new BusinessException("GroupService-Modify Group By ID",
					" Group ID Not found in DataBase, Please enter valid ID");
		}
		GroupData modifySaved = groupRepository.findById(id).get();
		groupData.setAssignPrograms(modifySaved.getAssignPrograms());
		modifySaved.setDescription(groupData.getDescription());

		return groupRepository.save(modifySaved);
	}
	/*
	 * fetching list of group names present in the database 
	 */
	@Override
	public List listOfGroupNames( GroupData groupData)  {
		List listofgroup=null;
		listofgroup=groupRepository.findallgroups(groupData);
		if (listofgroup.isEmpty()) {
			throw new BusinessException("Group data table is empity","  Please enter Data");
		}
		return listofgroup;
	}
	/*
	 * Fetching Each group programs only based on Group name not fetching Description
	 */
	@Override
	public List getProgram(String groupName)  {
		List<List> getprograms=groupRepository.findprogram(groupName);
		if (getprograms.isEmpty()) {
			throw new BusinessException("Group data is empity",
					"  Please enter Data");
		}
		return getprograms;
	}
	/*
	 * Deleting a Group based on Group ID
	 */
	@Override
	public void deleteGroupbyid(Long id) {
		if(!groupRepository.existsById(id)) {
			throw new BusinessException("GroupService-Delete Group By ID",
					" Group ID Not found in DataBase, Please enter valid ID");
		}
		GroupData gc =	groupRepository.findById(id).get();
		groupRepository.delete(gc);
	}

	//****************** Program Table *******************************************************

	/*
	 * add programs to available programs 
	 */
	@Override
	public Programs addprogram(Programs program) {
		Programs programs=program;
		programs=programRepository.save(program);
		return programs;
	}
	/*
	 * Fetching all the programs present in the programs table
	 */
	@Override
	public ArrayList FetchingAllPrograms(Programs program)  {
		ArrayList<Programs> listosprograms=(ArrayList<Programs>) programRepository.findallprograms(program);
		if (listosprograms.isEmpty()) {
			throw new BusinessException("Program table is empity","  Please enter Data");
		}
		return listosprograms;

	}


	@Override
	public GroupData getGroupDataByGroupName(String groupName) {
		GroupData gc = groupRepository.findByGroupName(groupName);
		return gc;
	}

}
