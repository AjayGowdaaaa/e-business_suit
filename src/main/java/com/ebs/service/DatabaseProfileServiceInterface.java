package com.ebs.service;

import java.sql.ResultSet;

import com.ebs.entity.DatabaseProfile;

public interface DatabaseProfileServiceInterface {

	
	DatabaseProfile createDbProfile (DatabaseProfile databaseProfile);
	DatabaseProfile getDatabaseProfileByProfileName(String profileName);
	DatabaseProfile updateDbProfile (Long id, DatabaseProfile databaseProfile);
	void deleteDbProfile (String profileName);
	//void setRepository(String url);
	//void connectionWithURL (DatabaseProfile databaseProfile);
	
	String connection(String profileName) throws Exception;
}
