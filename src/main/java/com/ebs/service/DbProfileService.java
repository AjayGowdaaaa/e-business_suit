package com.ebs.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.BusinessException;
import com.ebs.exception.DbConnectionException;
import com.ebs.repository.DatabaseProfileRepository;

@Service
public class DbProfileService implements DatabaseProfileServiceInterface {

	@Autowired
	DatabaseProfileRepository dbpRepo;

	//	@Override
	//	public String  connection(String profileName) throws Exception {
	//		DatabaseProfile dbp =    dbpRepo.findByProfileName(profileName);
	//		//	Class.forName("com.mysql.cj.jdbc.Driver");
	//		Class.forName("oracle.jdbc.driver.OracleDriver");
	//		//   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");  
	//		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.171:1521:vis:EBSDB_171","pstarch","pstarch"); 
	//
	//		//	Connection connection=DriverManager.getConnection(dbp.getDbConnectionURL(), dbp.getDatabaseUserName(),dbp.getDatabaseUserPassword());   
	//
	//		Statement statement=connection.createStatement();        
	//		ResultSet rs=statement.executeQuery("select * from P2P_1926958649 ");  
	//		while(rs.next())  {
	//			System.out.println("--------------------------->"+rs);
	//			System.out.println(rs.getInt(1)+"  "+rs.getInt(2));  
	//		}
	//		//connection.close();  
	//		return "Connected";
	//	}
	@Override
	public String  connection(String profileName) throws Exception {
		DatabaseProfile dbp =    dbpRepo.findByProfileName(profileName);

		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("registered driver successfully");
		//Create the connection and assign to connection reference
		//   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:CUSTDB", "scott", "tiger");

		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.171:1521:vis","pstarch","pstarch"); 
		System.out.println("connection successsfully");
		//create a statement through connection reference and assign to statement reference
		Statement stmt=connection.createStatement();
		System.out.println("statement object created successfully");
		//call the executequery method through statement reference and pass the query as argument.
		ResultSet rs=stmt.executeQuery("select * from OPTEBS_A4S_PROCESS");
		System.out.println(rs.getFetchSize());
		System.out.println("query is executed");
		while(rs.next()){
			String i=rs.getString(1);
			String str=rs.getString(2);
			String str1=rs.getString(3);
			String i1=rs.getString(4);
			System.out.println(i+"\t"+str+"\t"+str1+"\t"+i1);    
		}
		return "Connected";
	}

	// CREATING DBPROFILE AND CONNECTING TO ORACLE DATABASE
	@Override
	public DatabaseProfile createOracleDbp (DatabaseProfile databaseProfile) throws DbConnectionException, Exception{
		if (!(dbpRepo.findByProfileName(databaseProfile.getProfileName()) == null)) {
			throw new BusinessException("Duplicate DBProfile", "DBProfile already EXsist in database");
		}
		databaseProfile.setAPI("jdbc");
		databaseProfile.setDatabase("oracle:thin");
		databaseProfile.setDbConnectionURL(
				databaseProfile.getAPI()+":"+
						databaseProfile.getDatabase()+":@"+
						databaseProfile.getServerName()+":"+
						databaseProfile.getPortnumber()+":"+
						databaseProfile.getSid()

				);
		databaseProfile  = dbpRepo.save(databaseProfile);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection connection=DriverManager.getConnection(databaseProfile.getDbConnectionURL(), databaseProfile.getDatabaseUserName(),databaseProfile.getDatabaseUserPassword());  
			if (!(connection == null)) {
				databaseProfile.setConnected(true);
				databaseProfile  = dbpRepo.save(databaseProfile);
			}else {
				dbpRepo.delete(databaseProfile);
				throw new DbConnectionException("Failed to connect to DataBase Schema "," Provide Valid Credential ");

			}
		} catch (DbConnectionException e ) {
			dbpRepo.delete(databaseProfile);
			e.setErrorCode("Failed to connect to DataBase Schema ");
			e.setErrorMessage(" Provide Valid Credential ");
			e.getMessage();
			System.out.println(e.getErrorMessage());
			System.out.println(e.getErrorCode());

			throw new DbConnectionException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
		}

		return databaseProfile;
	}

	// CREATING DBPROFILE AND CONNECTING TO MYSQL DATABASE
	@Override
	public DatabaseProfile createMysqlDbp (DatabaseProfile databaseProfile) throws DbConnectionException, Exception{
		if (!(dbpRepo.findByProfileName(databaseProfile.getProfileName()) == null)) {
			throw new BusinessException("Duplicate DBProfile", "DBProfile already EXsist in database");
		}
		databaseProfile.setAPI("jdbc");
		databaseProfile.setDatabase("mysql");
		//Setting DatabaseProfile attributes and creating url
		if (!(databaseProfile.getDatabase()==null) && 
				!(databaseProfile.getServerName()==null) && 
				!(databaseProfile.getPortnumber()==0) &&
				!(databaseProfile.getSchema()==null) ) {
			
			databaseProfile.setDbConnectionURL(
							databaseProfile.getAPI()+":"+
							databaseProfile.getDatabase()+"://"+
							databaseProfile.getServerName()+":"+
							databaseProfile.getPortnumber()+"/"+
							databaseProfile.getSchema()
					);
		}
		databaseProfile  = dbpRepo.save(databaseProfile);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection connection=DriverManager.getConnection(databaseProfile.getDbConnectionURL(), databaseProfile.getDatabaseUserName(),databaseProfile.getDatabaseUserPassword());  
			if (!(connection == null)) {
				databaseProfile.setConnected(true);
				databaseProfile  = dbpRepo.save(databaseProfile);
			}else {
				dbpRepo.delete(databaseProfile);
				throw new DbConnectionException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
			}
		} catch (DbConnectionException e ) {
			dbpRepo.delete(databaseProfile);
			e.setErrorCode("Failed to connect to DataBase Schema ");
			e.setErrorMessage(" Provide Valid Credential ");
			e.getMessage();
			System.out.println(e.getErrorMessage());
			System.out.println(e.getErrorCode());

			throw new DbConnectionException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
		}
		return databaseProfile;
	}

	//Getting DbProfile by profile name
	@Override
	public DatabaseProfile getDatabaseProfileByProfileName(String profileName) {
		DatabaseProfile dbp =	dbpRepo.findByProfileName(profileName);
		return dbp ;
	}

	@Override
	public DatabaseProfile updateDbProfile(Long id, DatabaseProfile databaseProfile) {
		DatabaseProfile existingDbP = dbpRepo.findById(id).orElseThrow(() -> new BusinessException("profileName not exsits in Repository", "Please Enter valid profileName"));
		existingDbP.setDatabaseUserName(databaseProfile.getDatabaseUserName());
		existingDbP.setDatabaseUserPassword(databaseProfile.getDatabaseUserPassword());
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
			throw new BusinessException("Failed to Delete DatabaseProfile ","profileName not found");		
		}
		dbpRepo.delete(dbp);

	}

}
