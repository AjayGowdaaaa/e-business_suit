package com.ebs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ebs.entity.GroupData;
import com.ebs.entity.Programs;
import com.ebs.entity.User;
import com.ebs.exception.BusinessException;
import com.ebs.exception.ControllerException;
import com.ebs.service.GroupServiceInterface;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupServiceInterface service;

	/*
	 * New Group creating
	 */
	@PostMapping("/newGroup")
	public ResponseEntity<?> newGroup( @RequestBody GroupData groupData) {
		try {
			GroupData savedGroup = service.newGroup(groupData);
			return new ResponseEntity<GroupData>(savedGroup, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Create Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * List of groups data
	 */
	@GetMapping("/groupList")
	public ResponseEntity<List<GroupData>> getAllGroupdata() {

		List<GroupData> listOfGropus = service.getAllGroupdata();
		return new ResponseEntity<List<GroupData>>(listOfGropus, HttpStatus.ACCEPTED);

	}
	/*
	 * Fetching Group details by Id
	 */
	@GetMapping("/fetchGroup/{id}")
	public ResponseEntity<?> getGroupById(@PathVariable("id") Long id) {
		try {
			GroupData groupData = service.getGroupById(id);
			return new ResponseEntity<GroupData>(groupData, HttpStatus.ACCEPTED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to get Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * Assigning the programs to the Group
	 */
	@PostMapping("/assignPrograms/{id}")
	public ResponseEntity<?> assignPrograms( @RequestBody GroupData groupData,@PathVariable("id") Long id) {
		try {
			GroupData savedPrograms = service.assignPrograms(id, groupData);
			return new ResponseEntity<GroupData>(savedPrograms, HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Assign program","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * Modify the Group
	 */
	@PutMapping("/Modify/{id}")
	public ResponseEntity<?> modifyGroup( @RequestBody GroupData groupData,@PathVariable("id") Long id) {
		try {
			GroupData savedPrograms = service.modifyGroup(id, groupData);
			return new ResponseEntity<GroupData>(savedPrograms, HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Modify Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * fetching list of group names from Group data table
	 */
	@GetMapping("/listOfGroupsName")
	public ResponseEntity<?> ListOfAllGroups( @RequestBody GroupData groupData) {
		try {
		List<GroupData> listofgroups = null;
		listofgroups = service.listOfGroupNames(groupData);
		return new ResponseEntity<List<GroupData>>(listofgroups, HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Delete Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * fetching Programs present in the particular group
	 * here we get only the group programs not description
	 */
	@GetMapping("/FetchGroupDetails/{groupName}")
	public ResponseEntity<?> particular_programs( @PathVariable String groupName )  {
		try {
		List<GroupData> particular_programs = (List<GroupData>) service.getProgram(groupName);
		return new ResponseEntity<List<GroupData>>(particular_programs, HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Delete Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * deleting Group
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteGroupbyid(@PathVariable("id") Long id){
		try {
			service.deleteGroupbyid(id);
			return new ResponseEntity<String>("Group Deleted Successfully ",HttpStatus.OK);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Delete Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
//****************** Program Table *******************************************************
	
	/*
	 * Add programs to the Available Programs
	 */
	@PostMapping("/addPrograms")
	public ResponseEntity<?> addprogram( @RequestBody Programs program) {
		try {
			Programs programs = service.addprogram(program);
			return new ResponseEntity<Programs>(programs, HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to Delete Group","Something went wrong on Controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * Fetching list of Programs in the Available Programs
	 */
	@GetMapping("/programList")
	public ResponseEntity<ArrayList<Programs>> ListOfPrograms( @RequestBody Programs program)  {

		ArrayList<Programs> AllPrograms=service.FetchingAllPrograms(program);
		return new ResponseEntity<ArrayList<Programs>>(AllPrograms, HttpStatus.CREATED);
	}
	

}