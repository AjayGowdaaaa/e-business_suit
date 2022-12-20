package com.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.BusinessException;
import com.ebs.repository.DatabaseProfileRepository;

@Service
public class DbProfileServiceImplimentation implements DatabaseProfileService {

	@Autowired
	DatabaseProfileRepository dbpRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public DatabaseProfile createDbProfile (DatabaseProfile databaseProfile) {
		//Admin Creating User
		databaseProfile.setUserPassword(passwordEncoder.encode(databaseProfile.getUserPassword()));
		databaseProfile  = dbpRepo.save(databaseProfile);
		 
		return databaseProfile;
	}

	@Override
	public DatabaseProfile getDatabaseProfileByProfileName(String profileName) {
		DatabaseProfile dbp =	dbpRepo.findByProfileName(profileName);
		return dbp ;
	}

	@Override
	public DatabaseProfile updateDbProfile(String profileName, DatabaseProfile databaseProfile) {
		DatabaseProfile existingDbP = dbpRepo.findById(profileName).orElseThrow(() -> new BusinessException("profileName not exsits in Repository", "Please Enter valid profileName"));
		existingDbP.setDatabaseUser(databaseProfile.getDatabaseUser());
		existingDbP.setUserPassword(databaseProfile.getUserPassword());
		existingDbP.setServerName(databaseProfile.getServerName());
		existingDbP.setSid(databaseProfile.getSid());
		existingDbP.setPortnumber(databaseProfile.getPortnumber());
		try {
			existingDbP = dbpRepo.save(existingDbP);
		} catch (BusinessException e) {
			throw new BusinessException("update DatabaseProfile ","Failed to Update DatabaseProfile " + e.getMessage());		
		}
		return existingDbP;
	}

	@Override
	public void deleteDbProfile(String profileName) {
		DatabaseProfile dbp = dbpRepo.findByProfileName(profileName);
		if (dbp==null) {
			throw new BusinessException("Delete DatabaseProfile ","profileName not found");		
		}
		dbpRepo.delete(dbp);
		
	}

}
