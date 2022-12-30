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

	@Override
	public String  connection(String profileName) throws Exception {
		DatabaseProfile dbp =    dbpRepo.findByProfileName(profileName);
		//	Class.forName("com.mysql.cj.jdbc.Driver");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");  
		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.171:1521:vis/EBSDB_171","pstarch","pstarch"); 

		//	Connection connection=DriverManager.getConnection(dbp.getDbConnectionURL(), dbp.getDatabaseUserName(),dbp.getDatabaseUserPassword());   

		Statement statement=connection.createStatement();        
		ResultSet rs=statement.executeQuery("select * from P2P_1926958649 ");  
		while(rs.next())  {
			System.out.println("--------------------------->"+rs);
			System.out.println(rs.getInt(1)+"  "+rs.getInt(2));  
		}
		//connection.close();  
		return "Connected";
	}
	@Override
	public DatabaseProfile createDbProfile (DatabaseProfile databaseProfile) throws DbConnectionException, Exception{
		//Setting DatabaseProfile attributes and creating url
//		databaseProfile.setDbConnectionURL(
//				databaseProfile.getAPI()+":"+
//						databaseProfile.getDatabase()+"://"+
//						databaseProfile.getServerName()+":"+
//						databaseProfile.getPortnumber()+"/"+
//						databaseProfile.getSchema()
//				);
	//	databaseProfile.setDbConnectionURL( "jdbc:oracle:thin:@192.168.0.171:1521:vis/EBSDB_171"); 

		databaseProfile  = dbpRepo.save(databaseProfile);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//	Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection connection=DriverManager.getConnection(databaseProfile.getDbConnectionURL(), databaseProfile.getDatabaseUserName(),databaseProfile.getDatabaseUserPassword());  
			if (!(connection == null)) {
				databaseProfile.setConnected(true);
				databaseProfile  = dbpRepo.save(databaseProfile);
			}else {
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
