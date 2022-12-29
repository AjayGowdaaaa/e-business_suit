package com.ebs.controller;

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
		GroupData savedGroup = service.newGroup(groupData);
		return new ResponseEntity<GroupData>(savedGroup, HttpStatus.CREATED);
	}
	/*
	 * List of groups 
	 */
	@GetMapping("/groupList")
	public ResponseEntity<List<GroupData>> getAllGroupCreations() {

		List<GroupData> listOfGropus = service.getAllGroupCreations();
		return new ResponseEntity<List<GroupData>>(listOfGropus, HttpStatus.ACCEPTED);

	}
	/*
	 * Fetching Group details by Id
	 */
	@GetMapping("/fetchGroup/{id}")
	public ResponseEntity<?> getGroupById(@PathVariable("id") Long id) {
		GroupData groupData = service.getGroupById(id);
		return new ResponseEntity<GroupData>(groupData, HttpStatus.ACCEPTED);
	}
	/*
	 * Assigning the programs to the Group
	 */
	@PostMapping("/assignPrograms/{id}")
	public ResponseEntity<?> assignPrograms( @RequestBody GroupData groupData,@PathVariable("id") Long id) {
		GroupData savedPrograms = service.assignPrograms(id, groupData);
		return new ResponseEntity<GroupData>(savedPrograms, HttpStatus.CREATED);
	}
	/*
	 * Modify the Group
	 */
	@PutMapping("/Modify/{id}")
	public ResponseEntity<?> modifyGroup( @RequestBody GroupData groupData,@PathVariable("id") Long id) {
		GroupData savedPrograms = service.modifyGroup(id, groupData);
		return new ResponseEntity<GroupData>(savedPrograms, HttpStatus.CREATED);
	}
	/*
	 * deleting Group
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteGroupbyid(@PathVariable("id") Long id){
		service.deleteGroupbyid(id);
		return new ResponseEntity<GroupData>(HttpStatus.OK);
	}

	/*
	 * Add programs to the Available Programs
	 */
	@PostMapping("/addPrograms")
	public ResponseEntity<?> addprogram( @RequestBody Programs program) {
		Programs programs = service.addprogram(program);
		return new ResponseEntity<Programs>(programs, HttpStatus.CREATED);
	}
	/*
	 * Fetching list of Programs
	 */
	@GetMapping("/programList")
	public ResponseEntity<List<Programs>> getAllPrograms() {

		List<Programs> listOfProgram = service.getAllPrograms();
		return new ResponseEntity<List<Programs>>(listOfProgram, HttpStatus.ACCEPTED);

	}
	

				
				
}