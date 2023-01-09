package com.ebs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	 * Fetching the group details
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
	 * Fetching the list of Programs and list of groups
	 */
	@Override
	public HashMap<String, Object> getAllListOf_ProgramsAndGroup() throws Exception {

		HashMap<String, Object> map =new  HashMap<String, Object>();
		List<Programs> listOfProgram=(List<Programs>) programRepository.findAll();
		//List<GroupData> list1 = groupRepository.findAll();
		//GroupData obj= groupRepository.findById(id).get();
		List<GroupData> listOfGroup=groupRepository.findAll();

		//map.put(obj.getId(), obj);
		map.put("availablePrograms",listOfProgram );
		map.put("listOfGroups",listOfGroup );
		//map.put((long) 2,list1 ); 

		return map;
	}
	
	/*
	 * Assign Programs to the group based on Group Name
	 */
	@Override
	public GroupData assignData(String groupName,String assignPrograms) {
		List<GroupData> ls=	groupRepository.findAll();
		List<GroupData> ls1=ls.stream().filter(column->column.getGroupName().equals(groupName)).collect(Collectors.toList());
		GroupData group=ls1.get(0);
		List<Programs> listOfPrograms =(List<Programs>) programRepository.findAll();
		List<Programs> listOfPrograms1= listOfPrograms.stream().filter(column->column.getAvailablePrograms().equals(assignPrograms)).collect(Collectors.toList());
		Programs programs=listOfPrograms1.get(0);
		group.setPrograms(programs);
		return 	groupRepository.save(group);

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
		groupData.setPrograms(modifySaved.getPrograms());
		modifySaved.setDescription(groupData.getDescription());

		return groupRepository.save(modifySaved);
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
	
	
	@Override
	public GroupData getGroupById(Long id) {
		if(!groupRepository.existsById(id)) {
			throw new BusinessException("GroupService-Get Group By ID",
					" Group ID Not found in DataBase, Please enter valid ID");
		}
		GroupData groupData = groupRepository.findById(id).get();
		return groupData;
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public GroupData assignPrograms(Long id,  GroupData groupData) {
		
		
		Programs programs=	programRepository.findById(id).get();
		groupData.setPrograms(programs);
		return groupRepository.save(groupData);
	

//		if(!groupRepository.existsById(id)) {
//			throw new BusinessException("GroupService-Assign Programs By ID",
//					" Group ID Not found in DataBase, Please enter valid ID");
//		}
//		//GroupData savedPrograms = groupData;
//		GroupData savedPrograms=groupRepository.findById(id).get();
//		groupData.setDescription(savedPrograms.getDescription());
//		groupData.setGroupName(savedPrograms.getGroupName());
//		//savedPrograms.setGroupName(groupData.getGroupName());
//		//savedPrograms.setPrograms(groupData.getPrograms());
//		Programs programs = programRepository.findById(id).get();
//		savedPrograms.setPrograms(programs);
//		groupRepository.save(savedPrograms);
//
//		return savedPrograms;
	}
	
	

//	/*
//	 * Fetching Each group programs only based on Group name not fetching Description
//	 */
//
//
//	@Override
//	public HashMap<Long, Object> getProgram(Long id)  {
//		HashMap<Long, Object> map =new  HashMap<Long, Object>();
//		GroupData obj=groupRepository.findById(id).get();
//		map.put(obj.getId(), obj);
//		return map;
//
//	}
//	
//	/*
//	 * fetching list of group names present in the database 
//	 */
//	@Override
//	public List listOfGroupNames( GroupData groupData)  {
//		List listofgroup=null;
//		listofgroup=groupRepository.findallgroups(groupData);
//		if (listofgroup.isEmpty()) {
//			throw new BusinessException("Group data table is empity","  Please enter Data");
//		}
//		return listofgroup;
//	}
//
//
//	/*
//	 * fetching particular group details based on ID
//	 */
//	@Override
//	public GroupData getGroupById(Long id) {
//		if(!groupRepository.existsById(id)) {
//			throw new BusinessException("GroupService-Get Group By ID",
//					" Group ID Not found in DataBase, Please enter valid ID");
//		}
//		GroupData groupData = groupRepository.findById(id).get();
//		return groupData;
//	}
//	
//	
//	/*
//	 *  Fetch groups details for all the Groups
//	 */
////	@Override
////	public Map<Long, ArrayList<String>> getAllGroupdata() {
////		if(groupRepository.findAll()==null) {
////			throw new BusinessException("GroupService-Get Group Data",
////					" Group DataBase Table is empty");
////		}
////		List<Programs> programsList=null;
////		List<GroupData> groupList = null;
////		groupList = groupRepository.findAll();
////		programsList=programRepository.findAll();
////		Map<Long, ArrayList<String>> map=new HashMap<Long, ArrayList<String>>();
////		for (GroupData groupData : groupList) {
////			map.put(groupData.getId(),groupData.getAssignPrograms());
////		}
////		for (Programs programs : programsList ) {
////			map.put(programs.getId(), programs.getAvailablePrograms());
////		}
////
////		return map;
////	}
//	/*
//	 * Fetching all the programs present in the programs table
//	 */
//	@Override
//	public ArrayList FetchingAllPrograms(Programs program)  {
//		ArrayList<Programs> listosprograms=(ArrayList<Programs>) programRepository.findallprograms(program);
//		if (listosprograms.isEmpty()) {
//			throw new BusinessException("Program table is empity","  Please enter Data");
//		}
//		return listosprograms;
//
//	}
//	@Override
//	public void deleteAndSaveFormAccess(int group, List<Integer> selectedArr) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
////	@Override
////	public GroupData getGroupDataByGroupName(String groupName) {
////		GroupData gc = groupRepository.findByGroupName(groupName);
////		return gc;
////	}
//
////	@Override
////	public void deleteAndSaveFormAccess(int group, List<Integer> selectedArr) {
////		for (Integer selected1 : selectedArr) {
////			GroupData savedPrograms = groupData;
////			savedPrograms=groupRepository.findById(id).get();
////			group.setDescription(savedPrograms.getDescription());
////			groupData.setGroupName(savedPrograms.getGroupName());
////			//savedPrograms.setGroupName(groupData.getGroupName());
////			savedPrograms.setAssignPrograms(groupData.getAssignPrograms());
////
////			groupRepository.save(savedPrograms);
//////			pre.setForm(formPre);
//////			pre.setMenu(menuPre);
//////			pre.setRole(rolePre);
//////			pre.setCreatedDate(new Date());
////
//////			sessionFactory.getCurrentSession().save(pre);
////		}
////
////	}
}
