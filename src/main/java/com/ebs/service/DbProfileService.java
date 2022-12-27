package com.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.*;
import com.ebs.entity.DatabaseProfile;
import com.ebs.exception.BusinessException;
import com.ebs.repository.DatabaseProfileRepository;

@Service
public class DbProfileService implements DatabaseProfileServiceInterface {

	@Autowired
	DatabaseProfileRepository dbpRepo;
	
	@Override
	public void connection(String profileName) throws Exception {
	DatabaseProfile dbp =	dbpRepo.findByProfileName(profileName);
		Class.forName("com.mysql.jdbc.Driver");  
//		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");    
		Connection connection=DriverManager.getConnection(dbp.getDbConnectionURL(), dbp.getDatabaseUserName(),dbp.getDatabaseUserPassword());   
		Statement statement=connection.createStatement();		
		ResultSet rs=statement.executeQuery("select * from mytable");  
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
			connection.close();  
	}

	@Override
	public DatabaseProfile createDbProfile (DatabaseProfile databaseProfile) {
		//Setting DatabaseProfile attributes and creating url
		databaseProfile.setDbConnectionURL(
				databaseProfile.getAPI()+":"+
						databaseProfile.getDatabase()+"://"+
						databaseProfile.getServerName()+":"+
						databaseProfile.getPortnumber()+"/"+
						databaseProfile.getSchema()
				);
		databaseProfile  = dbpRepo.save(databaseProfile);

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
