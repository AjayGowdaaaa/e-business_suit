package com.ebs.controller;

import java.sql.SQLException;
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
import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.BusinessException;
import com.ebs.exception.ControllerException;
import com.ebs.exception.CustomException;
import com.ebs.service.DatabaseProfileServiceInterface;

@RestController
@RequestMapping("/dbp")
public class DatabaseProfieController {

	@Autowired
	private DatabaseProfileServiceInterface service;

	//	@Autowired
	//	private DatabaseProfileRepository databaseProfileRepository;

	@PostMapping("/createDbProfile/{db}")
	public ResponseEntity<?> createDbProfile(@PathVariable("db") String db ,@RequestBody DatabaseProfile databaseProfile) throws Exception {
		try {
			db = db.toLowerCase();
			DatabaseProfile dbProfile = null;
			if (db.equals("mysql")) {
				dbProfile = service.createMysqlDbp(databaseProfile);
			}else if (db.equals("oracle")) {
				dbProfile = service.createOracleDbp(databaseProfile);
			}
			return new ResponseEntity<DatabaseProfile>(dbProfile, HttpStatus.CREATED);
		} catch (CustomException e) {
			CustomException c = new CustomException(e.getMessage());
			return new ResponseEntity<String>(c.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			CustomException ce = new CustomException("Failed to create DBProfile");
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}


	//	@PostMapping("/createDbProfile/{db}")
	//	public void createDbProfile(@PathVariable("db") String db ,@RequestBody DatabaseProfile databaseProfile) throws Exception {
	//		String profile= databaseProfile.getProfileName();
	//		System.out.println(profile+"       ");
	//
	//		db = db.toLowerCase();
	//		DatabaseProfile dbProfile = null;
	//		if (db.equals("mysql")) {
	//			List<DatabaseProfile> ls=	databaseProfileRepository.findAll();
	//			System.out.println(ls.size()+"       ");
	//			System.out.println(databaseProfile.getProfileName()+"       ");
	//			List<DatabaseProfile> listAfterValidation=	ls.stream().filter(column->column.getProfileName().equals(profile)).collect(Collectors.toList());	
	//			if(CollectionUtils.isEmpty(listAfterValidation)) {
	//				databaseProfileRepository.save(databaseProfile);
	//			}
	//			else {
	//				throw new CustomException("Already Existed");
	//			}			
	//		}else if (db.equals("oracle")) {
	//			//dbProfile = service.createOracleDbp(databaseProfile);
	//		}
	//	}


	@GetMapping("/databaseProfile/{profileName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable("profileName") String profileName) {	
		try {
			DatabaseProfile dbProfile = service.getDatabaseProfileByProfileName(profileName);		
			return new ResponseEntity<DatabaseProfile>(dbProfile, HttpStatus.ACCEPTED);
		} catch (CustomException e) {
			CustomException c = new CustomException(e.getMessage());
			return new ResponseEntity<String>(c.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to create DBProfile",
					"Something went wrong");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateDbProfile/{profileName}")
	public ResponseEntity<?> updateDbProfile(@PathVariable  String profileName ,@RequestBody DatabaseProfile databaseProfile) throws ClassNotFoundException, SQLException {	
		try {
			DatabaseProfile dbProfile = service.updateDbProfile(profileName, databaseProfile);

			return new ResponseEntity<DatabaseProfile>(dbProfile, HttpStatus.CREATED);
		} catch (CustomException e) {
			CustomException c = new CustomException(e.getMessage());
			return new ResponseEntity<String>(c.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to create DBProfile",
					"Something went wrong");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{profileName}")
	public ResponseEntity<?> deleteDbProfile(@PathVariable("profileName") String profileName) {
		try {
			service.deleteDbProfile(profileName);
			return new ResponseEntity<String>("profileName ---> Deleted Sucessfuly",HttpStatus.ACCEPTED);
		} catch (CustomException e) {
			CustomException c = new CustomException(e.getMessage());
			return new ResponseEntity<String>(c.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to create DBProfile",
					"Something went wrong");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/con/{profileName}")
	public ResponseEntity<?> connection(@PathVariable("profileName") String profileName) throws Exception {    
		try {
			String rs =service.connection(profileName);

			return new ResponseEntity<String>(rs, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException ce = new BusinessException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<BusinessException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("Failed to create DBProfile",
					"Something went wrong");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/getalldbp")
	public ResponseEntity<?> getAllDbProfie(){
			List<DatabaseProfile> dbProfile = service.getAllDbProfie();		
			return new ResponseEntity<List>(dbProfile, HttpStatus.ACCEPTED);
	}
}
