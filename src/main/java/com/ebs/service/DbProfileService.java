package com.ebs.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.BusinessException;
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
	@Override
	public String mySqlConUrlGenerator(DatabaseProfile databaseProfile) {
		databaseProfile.setAPI("jdbc");
		databaseProfile.setDatabase("mysql");
		String url=databaseProfile.getAPI()+":"+
				databaseProfile.getDatabase()+"://"+
				databaseProfile.getServerName()+":"+
				databaseProfile.getPortnumber()+"/"+
				databaseProfile.getSchema();
		return url;
	}
	@Override
	public String oracleConUrlGenerator(DatabaseProfile databaseProfile) {
		databaseProfile.setAPI("jdbc");
		databaseProfile.setDatabase("oracle:thin");
		String url=databaseProfile.getAPI()+":"+
				databaseProfile.getDatabase()+":@"+
				databaseProfile.getServerName()+":"+
				databaseProfile.getPortnumber()+":"+
				databaseProfile.getSid();
		return url;
	}
	// CREATING DBPROFILE AND CONNECTING TO ORACLE DATABASE
	@Override
	public DatabaseProfile createOracleDbp (DatabaseProfile databaseProfile) throws BusinessException, Exception{

		if (!(dbpRepo.findByProfileName(databaseProfile.getProfileName()) == null)) {
			throw new BusinessException("Duplicate DBProfile", "DBProfile already EXsist in database");
		}
		databaseProfile.setDbConnectionURL(oracleConUrlGenerator(databaseProfile) );
		databaseProfile  = dbpRepo.save(databaseProfile);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection connection=DriverManager.getConnection(databaseProfile.getDbConnectionURL(), databaseProfile.getDatabaseUserName(),databaseProfile.getDatabaseUserPassword());  
			if (!(connection == null)) {
				databaseProfile.setConnected(true);
				databaseProfile  = dbpRepo.save(databaseProfile);
			}else {
				dbpRepo.delete(databaseProfile);
				throw new BusinessException("Failed to connect to DataBase Schema "," Provide Valid Credential ");

			}
		} catch (BusinessException e ) {
			dbpRepo.delete(databaseProfile);
			e.setErrorCode("Failed to connect to DataBase Schema ");
			e.setErrorMessage(" Provide Valid Credential ");
			e.getMessage();
			System.out.println(e.getErrorMessage());
			System.out.println(e.getErrorCode());

			throw new BusinessException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
		}

		return databaseProfile;
	}



	private BusinessException BusinessException(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	// CREATING DBPROFILE AND CONNECTING TO MYSQL DATABASE
	@Override
	public DatabaseProfile createMysqlDbp (DatabaseProfile databaseProfile) throws BusinessException, Exception{
		if (!(dbpRepo.findByProfileName(databaseProfile.getProfileName()) == null)) {
			throw new BusinessException("Duplicate DBProfile", "DBProfile already Exsist in database");
		}
		//Setting DatabaseProfile attributes and creating url
		//Connection 1 
		if (	!(databaseProfile.getPortnumber()==0) &&
				!(databaseProfile.getSchema()==null) ) {
			String Url = mySqlConUrlGenerator(databaseProfile);
			databaseProfile.setDbConnectionURL(Url);
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
				throw new BusinessException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
			}
		} catch (BusinessException e ) {
			dbpRepo.delete(databaseProfile);
			e.setErrorCode("Failed to connect to DataBase Schema ");
			e.setErrorMessage(" Provide Valid Credential ");
			e.getMessage();
			System.out.println(e.getErrorMessage());
			System.out.println(e.getErrorCode());
			throw new BusinessException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
		}
		return databaseProfile;
	}



	//UPDATING DATABASE PROFILE USING PROFILENAME
	@Override
	public DatabaseProfile updateDbProfile(String profileName, DatabaseProfile databaseProfile) {
		//	DatabaseProfile existingDbP = dbpRepo.findById(id).orElseThrow(() -> new BusinessException("profileName not exsits in Repository", "Please Enter valid profileName"));
		DatabaseProfile existingDbP =dbpRepo.findByProfileName(profileName);
		if (existingDbP == null) {
			throw new BusinessException("profileName not exsits in Repository", "Please Enter valid profileName");
		}

		boolean isOracleDBP = existingDbP.getDatabase().contains("oracle");
		boolean isMysqlDBP = existingDbP.getDatabase().contains("mysql");

		//TO MODIFY ORACLE DATABASE	
		if (isOracleDBP && existingDbP.isConfigureNAA() ) {

			existingDbP.setDatabaseUserPassword(databaseProfile.getDatabaseUserPassword());
			existingDbP.setServerName(databaseProfile.getServerName());
			existingDbP.setPortnumber(databaseProfile.getPortnumber());
			existingDbP.setSid(databaseProfile.getSid());
			existingDbP.setDbConnectionURL(databaseProfile.getDbConnectionURL());
			//Connection 1
			if ( !(databaseProfile.getServerName()==null) && 
					!(databaseProfile.getPortnumber()==0) &&
					!(databaseProfile.getSid()==null) ) {
				existingDbP.setDbConnectionURL(oracleConUrlGenerator(databaseProfile));
			}
			//dbpRepo.save(existingDbP);
			//JDBC (Connecting DbProfile to Database
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection(existingDbP.getDbConnectionURL(), existingDbP.getDatabaseUserName(),existingDbP.getDatabaseUserPassword());  
				if (!(connection == null)) {
					existingDbP.setConnected(true);
					dbpRepo.save(existingDbP);
				}else {
					dbpRepo.delete(existingDbP);
					throw new BusinessException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
				}
			} catch (Exception e) {
				e.getMessage();
				throw new BusinessException("update DatabaseProfile ","Failed to Update DatabaseProfile " + e.getMessage());
			}  
		}
		//TO MODIFY MySQL DATABASE		
		else if (isMysqlDBP) {	
			if (isMysqlDBP && existingDbP.isConfigureNAA() ) {
				existingDbP.setDatabaseUserPassword(databaseProfile.getDatabaseUserPassword());
				existingDbP.setServerName(databaseProfile.getServerName());
				existingDbP.setPortnumber(databaseProfile.getPortnumber());
				existingDbP.setSchema(databaseProfile.getSchema());
				existingDbP.setDbConnectionURL(databaseProfile.getDbConnectionURL());
				//Connection 1
				existingDbP.setDbConnectionURL(mySqlConUrlGenerator(databaseProfile));
				System.out.println("generated url " +existingDbP.getDbConnectionURL());
				//Connection 2
				if (existingDbP.getPortnumber()== 0 && existingDbP.getServerName()==null && existingDbP.getSchema() == null  ) {
					existingDbP.setDbConnectionURL(databaseProfile.getDbConnectionURL());
				}
				//JDBC (Connecting DbProfile to Database
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");  		
					Connection connection;
					connection = DriverManager.getConnection(existingDbP.getDbConnectionURL(), existingDbP.getDatabaseUserName(),existingDbP.getDatabaseUserPassword());   
					if (!(connection == null)) {
						existingDbP.setConnected(true);
						dbpRepo.save(existingDbP);
					}else {
						dbpRepo.delete(existingDbP);
						throw new BusinessException("Failed to connect to DataBase Schema "," Provide Valid Credential ");
					}
				} catch (Exception e) {
					System.out.println();
					throw new BusinessException("Failed to connect", " Failed t connect to database");
				}  
			}

			try {
				existingDbP = dbpRepo.save(existingDbP);
			} catch (BusinessException e) {
				e.getMessage();
				throw new BusinessException("update DatabaseProfile ","Failed to Update DatabaseProfile " + e.getMessage());		
			}
		}
		return existingDbP;
	}

	//Getting DbProfile by profile name
	@Override
	public DatabaseProfile getDatabaseProfileByProfileName(String profileName) {
		DatabaseProfile dbp =	dbpRepo.findByProfileName(profileName);
		return dbp ;
	}

	//DELETING DATABASEPROFILE USING PROFILENAME
	@Override
	public void deleteDbProfile(String profileName) {
		DatabaseProfile dbp = dbpRepo.findByProfileName(profileName);
		if (dbp==null) {
			throw new BusinessException("Failed to Delete DatabaseProfile ","profileName not found");		
		}
		dbpRepo.delete(dbp);

	}

}
