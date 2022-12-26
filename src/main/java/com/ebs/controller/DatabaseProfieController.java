package com.ebs.controller;

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

import com.ebs.entity.DatabaseProfile;
import com.ebs.service.DatabaseProfileServiceInterface;
import com.ebs.service.DbProfileService;

@RestController
@RequestMapping("/dbp")
public class DatabaseProfieController {

	@Autowired
	private DatabaseProfileServiceInterface service;
	
	@PostMapping("/createDbProfile")
	public ResponseEntity<?> createDbProfile( @RequestBody DatabaseProfile databaseProfile) {
		DatabaseProfile dbProfile = service.createDbProfile(databaseProfile);
		return new ResponseEntity<DatabaseProfile>(dbProfile, HttpStatus.CREATED);
	}
	
	@GetMapping("/databaseProfile/{profileName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable("profileName") String profileName) {	
		DatabaseProfile dbProfile = service.getDatabaseProfileByProfileName(profileName);
		return new ResponseEntity<DatabaseProfile>(dbProfile, HttpStatus.ACCEPTED);
	}
	@PutMapping("/updateDbProfile/{profileName}")
	public ResponseEntity<?> updateDbProfile(@PathVariable  String profileName,@RequestBody DatabaseProfile databaseProfile) {	
		DatabaseProfile dbProfile = service.updateDbProfile(profileName, databaseProfile);
		return new ResponseEntity<DatabaseProfile>(dbProfile, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{profileName}")
	public ResponseEntity<?> deleteDbProfile(@PathVariable("profileName") String profileName) {
		service.deleteDbProfile(profileName);
		return new ResponseEntity<String>("profileName ---> Deleted Sucessfuly",HttpStatus.ACCEPTED);

	}
	
}
