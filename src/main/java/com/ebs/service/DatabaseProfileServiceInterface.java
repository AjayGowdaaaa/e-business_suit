package com.ebs.service;

import java.sql.SQLException;

import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.DbConnectionException;

public interface DatabaseProfileServiceInterface {

	//Generating Connection url
	String mySqlConUrlGenerator(DatabaseProfile databaseProfile);
	String oracleConUrlGenerator(DatabaseProfile databaseProfile);
	//Creating DbProfile based on database
	DatabaseProfile createMysqlDbp (DatabaseProfile databaseProfile) throws Exception;
	DatabaseProfile createOracleDbp (DatabaseProfile databaseProfile) throws Exception;

	DatabaseProfile getDatabaseProfileByProfileName(String profileName);
	DatabaseProfile updateDbProfile (String profileName, DatabaseProfile databaseProfile) throws ClassNotFoundException, SQLException, DbConnectionException;
	void deleteDbProfile (String profileName);
	//Testing
	String connection(String profileName) throws Exception;
}
