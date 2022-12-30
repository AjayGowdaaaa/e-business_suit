package com.ebs.service;

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
		if (!(groupRepository.findByGroupName(savedGroup.getGroupName()) == null))
		{
			throw new DuplicateKeyException("GroupName Already Exsists");
		}else {
			savedGroup=groupRepository.save(groupData);
		}
		return savedGroup;
	}
	/*
	 * list of groups
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
	 * fetching particular group details
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
	 * Deleting a Group using Group ID
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
	/*
	 * Assign Programs to the group
	 */
	@Override
	public GroupData assignPrograms(Long id,  GroupData groupData) {
		GroupData savedPrograms = groupData;
		savedPrograms=groupRepository.findById(id).get();
		savedPrograms.setGroupName(groupData.getGroupName());
		savedPrograms.setAssignPrograms(groupData.getAssignPrograms());

		groupRepository.save(savedPrograms);

		return savedPrograms;
	}
	/*
	 * Modify the Group 
	 */
	@Override
	public GroupData modifyGroup(Long id, GroupData groupData) {
		//List<GroupCreation> groups=null;
		GroupData modifySaved = groupRepository.findById(id).get();
		groupData.setAssignPrograms(modifySaved.getAssignPrograms());
		return groupRepository.save(groupData);
	}

	@Override
	public GroupData getGroupDataByGroupName(String groupName) {
		GroupData gc = groupRepository.findByGroupName(groupName);
		return gc;
	}
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
	 * programs List
	 */
	@Override
	public List<Programs> getAllPrograms() {
		List<Programs> programList = null;
		programList = programRepository.findAll();
		return programList;
	}
}
