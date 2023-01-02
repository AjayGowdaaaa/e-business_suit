package com.ebs.service;

import java.sql.ResultSet;

import com.ebs.entity.DatabaseProfile;

public interface DatabaseProfileServiceInterface {

	
	DatabaseProfile createMysqlDbp (DatabaseProfile databaseProfile) throws Exception;
	DatabaseProfile createOracleDbp (DatabaseProfile databaseProfile) throws Exception;
	
	
	DatabaseProfile getDatabaseProfileByProfileName(String profileName);
	DatabaseProfile updateDbProfile (Long id, DatabaseProfile databaseProfile);
	void deleteDbProfile (String profileName);
	
	String connection(String profileName) throws Exception;

}
