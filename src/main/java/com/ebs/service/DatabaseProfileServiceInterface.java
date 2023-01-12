package com.ebs.service;

import java.sql.SQLException;
import java.util.List;

import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.BusinessException;
import com.ebs.exception.CustomException;
import com.ebs.exception.DBException;

public interface DatabaseProfileServiceInterface {

	//Generating Connection url
	String mySqlConUrlGenerator(DatabaseProfile databaseProfile);
	String oracleConUrlGenerator(DatabaseProfile databaseProfile);
	//Creating DbProfile based on database
	DatabaseProfile createMysqlDbp (DatabaseProfile databaseProfile) throws Exception;
	DatabaseProfile createOracleDbp (DatabaseProfile databaseProfile) throws Exception;

	DatabaseProfile getDatabaseProfileByProfileName(String profileName) throws CustomException;
	DatabaseProfile updateDbProfile (String profileName, DatabaseProfile databaseProfile) throws Exception;
	void deleteDbProfile (String profileName) throws CustomException;
	//Testing
	String connection(String profileName) throws Exception;
	List getAllDbProfie() throws CustomException;
	List mysqlUrlSeperator(String url);
	List oracleUrlSeperator(String url);
}
