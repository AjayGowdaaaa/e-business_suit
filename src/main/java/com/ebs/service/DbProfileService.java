package com.ebs.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ebs.entity.DatabaseProfile;
import com.ebs.entity.User;
import com.ebs.exception.BusinessException;
import com.ebs.exception.CustomException;
import com.ebs.repository.DatabaseProfileRepository;
import com.ebs.repository.UserRepository;

@Service
public class DbProfileService implements DatabaseProfileServiceInterface {

	@Autowired
	DatabaseProfileRepository dbpRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

	public static String hashPassword(String plainTextPassword) {
		return encoder.encode(plainTextPassword);
	}

	public static boolean checkPassword(String plainTextPassword, String hashedPassword) {

		return encoder.matches(plainTextPassword, hashedPassword);
	}

	String plainTextPassword = "mypassword";
	String hashedPassword = DbProfileService.hashPassword(plainTextPassword);
	boolean passwordMatch = DbProfileService.checkPassword(plainTextPassword, hashedPassword);

	@Override
	public String oracleConUrlGenerator(DatabaseProfile databaseProfile) {
		databaseProfile.setAPI("jdbc");
		databaseProfile.setDatabase("oracle:thin");
		String url = databaseProfile.getAPI() + ":" + databaseProfile.getDatabase() + ":@"
				+ databaseProfile.getServerName() + ":" + databaseProfile.getPortnumber() + ":"
				+ databaseProfile.getSid();
		return url;
	}

	@Override
	public List oracleUrlSeperator(String url) {
		String[] urlArray = url.split("[:@]");
		List<String> list = new ArrayList<String>();
		for (String element : urlArray) {
			list.add(element);
		}
		return list;
	}

	// CREATING DBPROFILE AND CONNECTING TO ORACLE DATABASE
	@Override
	public DatabaseProfile createOracleDbp(DatabaseProfile db) throws BusinessException, Exception {
		db.setArchiveUnionUser(db.getArchiveUnionUser().toUpperCase());
		db.setArchiveUserPassword(passwordEncoder.encode(db.getArchiveUserPassword()));

		DatabaseProfile databaseProfile = db;
		if (!(dbpRepo.findByProfileName(databaseProfile.getProfileName()) == null)) {
			throw new CustomException("DBProfile already Exsist in database");
		}
		if (db.getProfileName().isEmpty()) {
			throw new CustomException("DBProfile Name can't be empty");
		} else if (db.getProfileName().length() < 3) {
			throw new CustomException("DBProfile Name Should consist of minimum 3 character");
		}
		if (!(db.getPortnumber() == 0) && !(db.getSid() == null)) {
			String Url = oracleConUrlGenerator(databaseProfile);
			databaseProfile.setDbConnectionURL(Url);
		}
		// Connection 2
		if (db.getDbConnectionURL() != null) {
			List<String> listOfElements = oracleUrlSeperator(databaseProfile.getDbConnectionURL());
			databaseProfile.setAPI(listOfElements.get(0));
			databaseProfile.setDatabase(listOfElements.get(1) + ":" + listOfElements.get(2));
			databaseProfile.setServerName(listOfElements.get(4));
			databaseProfile.setPortnumber(Integer.parseInt(listOfElements.get(5)));
			databaseProfile.setSid(listOfElements.get(6));
		}

		User user = userRepo.findByUserName(db.getArchiveUnionUser());
		if (user == null) {
			throw new CustomException("Invaid Archive Uninon UserName");
		}
		// bCryptPasswordEncoder.encode()
		// if (user.getPassword()==(db.getArchiveUserPassword())) {
		// if (user.getPassword()==(db.getArchiveUserPassword())) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection(databaseProfile.getDbConnectionURL(),
					databaseProfile.getDatabaseUserName(), databaseProfile.getDatabaseUserPassword());
			if (!(connection == null)) {
				databaseProfile.setConnected(true);
				databaseProfile = dbpRepo.save(databaseProfile);
			} else {
				dbpRepo.delete(databaseProfile);
				throw new CustomException("Failed to Create DBProfile, Please Provide valid credentials");
			}
		} catch (CustomException e) {
			dbpRepo.delete(databaseProfile);
			throw new CustomException("Failed to Create DBProfile, Please Provide valid credentials");
		}
//		}else {
//			throw new CustomException("Please provide valid User Credential");
//		}
		return databaseProfile;
	}

	@Override
	public String mySqlConUrlGenerator(DatabaseProfile databaseProfile) {
		databaseProfile.setAPI("jdbc");
		databaseProfile.setDatabase("mysql");
		String url = databaseProfile.getAPI() + ":" + databaseProfile.getDatabase() + "://"
				+ databaseProfile.getServerName() + ":" + databaseProfile.getPortnumber() + "/"
				+ databaseProfile.getSchema();
		return url;
	}

	@Override
	public List mysqlUrlSeperator(String url) {
		String[] urlArray = url.split("[:/]");
		List<String> list = new ArrayList<String>();
		for (String element : urlArray) {
			if (element != null || element != " ") {
				list.add(element);
			}
		}
		return list;
	}

	// CREATING DBPROFILE AND CONNECTING TO MYSQL DATABASE
	@Override
	public DatabaseProfile createMysqlDbp(DatabaseProfile db) throws BusinessException, Exception {
		DatabaseProfile databaseProfile = db;
		db.setArchiveUnionUser(db.getArchiveUnionUser().toUpperCase());
		db.setArchiveUserPassword(passwordEncoder.encode(db.getArchiveUserPassword()));
		System.out.println(db.getArchiveUserPassword() + "-----------------------------");
		if (!(dbpRepo.findByProfileName(databaseProfile.getProfileName()) == null)) {
			throw new CustomException("DBProfile already Exsist in database");
		}
		if (db.getProfileName().isEmpty()) {
			throw new CustomException("DBProfile Name can't be empty");
		} else if (db.getProfileName().length() < 3) {
			throw new CustomException("DBProfile Name Should consist of minimum 3 character");
		}
		// Setting DatabaseProfile attributes and creating url
		// Connection 1
		if (!(db.getPortnumber() == 0) && !(db.getSchema() == null)) {
			String Url = mySqlConUrlGenerator(databaseProfile);
			databaseProfile.setDbConnectionURL(Url);
		}
		// Connection 2
		if (db.getDbConnectionURL() != null) {
			List<String> listOfElements = mysqlUrlSeperator(databaseProfile.getDbConnectionURL());
			databaseProfile.setAPI(listOfElements.get(0));
			databaseProfile.setDatabase(listOfElements.get(1));
			databaseProfile.setServerName(listOfElements.get(4));
			databaseProfile.setPortnumber(Integer.parseInt(listOfElements.get(5)));
			databaseProfile.setSchema(listOfElements.get(6));
		}
		// databaseProfile = dbpRepo.save(databaseProfile);

		User user = userRepo.findByUserName(db.getArchiveUnionUser());
		if (user == null) {
			throw new CustomException("Invaid Archive Uninon UserName");
		}
		// ----------------------------------------------------------------------------------------------------
		// if (user.getPassword()==(db.getArchiveUserPassword())) {
		// DbProfileService.checkPassword(plainTextPassword, hashedPassword);
		// boolean ans = DbProfileService.checkPassword(db.getArchiveUserPassword(),
		// user.getPassword());
		// System.out.println("db.getArchiveUserPassword()-->"+db.getArchiveUserPassword());
		// System.out.println("user.getPassword()-->"+user.getPassword());
		// if (ans) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(databaseProfile.getDbConnectionURL(),
					databaseProfile.getDatabaseUserName(), databaseProfile.getDatabaseUserPassword());
			if (connection != null) {
				databaseProfile.setConnected(true);
				databaseProfile = dbpRepo.save(databaseProfile);
			} else {
				dbpRepo.delete(databaseProfile);
				throw new CustomException("Failed to Update DBProfile, Please Provide valid credentials");
			}
		} catch (CustomException e) {
			dbpRepo.delete(databaseProfile);
			throw new CustomException("Failed to Create DBProfile, Please Provide valid credentials");
		}
		// }else {
		// throw new CustomException("Invalid Archive uninon user Password");
		// }
		return databaseProfile;
	}

	// UPDATING DATABASE PROFILE USING PROFILENAME
	@Override
	public DatabaseProfile updateDbProfile(String profileName, DatabaseProfile databaseProfile)
			throws BusinessException, Exception {
		DatabaseProfile existingDbP = dbpRepo.findByProfileName(profileName);
		if (existingDbP == null) {
			throw new CustomException("DBProfile Not Exsist in database");
		}
		boolean isOracleDBP = existingDbP.getDatabase().contains("oracle");
		boolean isMysqlDBP = existingDbP.getDatabase().contains("mysql");

		// TO MODIFY ORACLE DATABASE
		if (isOracleDBP && existingDbP.isConfigureNAA()) {
			existingDbP.setDatabaseUserPassword(databaseProfile.getDatabaseUserPassword());
			existingDbP.setServerName(databaseProfile.getServerName());
			existingDbP.setPortnumber(databaseProfile.getPortnumber());
			existingDbP.setSid(databaseProfile.getSid());
			existingDbP.setDbConnectionURL(databaseProfile.getDbConnectionURL());
			if (!(databaseProfile.getServerName() == null) && !(databaseProfile.getPortnumber() == 0)
					&& !(databaseProfile.getSid() == null)) {
				existingDbP.setDbConnectionURL(oracleConUrlGenerator(databaseProfile));
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(existingDbP.getDbConnectionURL(),
						existingDbP.getDatabaseUserName(), existingDbP.getDatabaseUserPassword());
				if (connection != null) {
					existingDbP.setConnected(true);
					dbpRepo.save(existingDbP);
				} else {
					dbpRepo.delete(existingDbP);
					throw new CustomException("Failed to Update DBProfile, Please Provide valid credentials");
				}
			} catch (CustomException e) {
				e.getMessage();
				throw new CustomException("Failed to Update DBProfile, Please Provide valid credentials");
			}
		}
		// TO MODIFY MySQL DATABASE
		else if (isMysqlDBP) {
			if (isMysqlDBP && existingDbP.isConfigureNAA()) {
				existingDbP.setDatabaseUserPassword(databaseProfile.getDatabaseUserPassword());
				existingDbP.setServerName(databaseProfile.getServerName());
				existingDbP.setPortnumber(databaseProfile.getPortnumber());
				existingDbP.setSchema(databaseProfile.getSchema());
				existingDbP.setDbConnectionURL(databaseProfile.getDbConnectionURL());
				existingDbP.setDbConnectionURL(mySqlConUrlGenerator(databaseProfile));
				System.out.println("generated url " + existingDbP.getDbConnectionURL());
				if (existingDbP.getPortnumber() == 0 && existingDbP.getServerName() == null
						&& existingDbP.getSchema() == null) {
					existingDbP.setDbConnectionURL(databaseProfile.getDbConnectionURL());
				}
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection;
					connection = DriverManager.getConnection(existingDbP.getDbConnectionURL(),
							existingDbP.getDatabaseUserName(), existingDbP.getDatabaseUserPassword());
					if (!(connection == null)) {
						existingDbP.setConnected(true);
						dbpRepo.save(existingDbP);
					} else {
						dbpRepo.delete(existingDbP);
						throw new CustomException("Failed to Update DBProfile, Please Provide valid credentials");
					}
				} catch (CustomException e) {
					throw new CustomException("Failed to Update DBProfile, Please Provide valid credentials");
				}
			}
			existingDbP = dbpRepo.save(existingDbP);
		}
		return existingDbP;
	}

	// Getting DbProfile by profile name
	@Override
	public DatabaseProfile getDatabaseProfileByProfileName(String profileName) throws CustomException {
		DatabaseProfile dbp = dbpRepo.findByProfileName(profileName);
		if (dbp == null) {
			throw new CustomException(profileName + " Not Found in database");
		}
		return dbp;
	}

	// DELETING DATABASEPROFILE USING PROFILENAME
	@Override
	public void deleteDbProfile(String profileName) throws CustomException {
		DatabaseProfile dbp = dbpRepo.findByProfileName(profileName);
		if (dbp == null) {
			throw new CustomException(profileName + " Not Found in database");
		}
		dbpRepo.delete(dbp);
	}

	// Getting All DBProfile
	@Override
	public List getAllDbProfie() throws CustomException {
		List<DatabaseProfile> dbp = dbpRepo.findAll();
		if (dbp == null) {
			throw new CustomException("No Data Found");
		}
		return dbp;
	}

	@Override
	public String connection(String profileName) throws Exception {
		DatabaseProfile dbp = dbpRepo.findByProfileName(profileName);

		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("registered driver successfully");
		// Create the connection and assign to connection reference
		// Connection
		// con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:CUSTDB",
		// "scott", "tiger");

		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.171:1521:vis", "pstarch",
				"pstarch");
		System.out.println("connection successsfully");
		// create a statement through connection reference and assign to statement
		// reference
		Statement stmt = connection.createStatement();
		System.out.println("statement object created successfully");
		// call the executequery method through statement reference and pass the query
		// as argument.
		ResultSet rs = stmt.executeQuery("select * from OPTEBS_A4S_PROCESS");
		System.out.println(rs.getFetchSize());
		System.out.println("query is executed");
		while (rs.next()) {
			String i = rs.getString(1);
			String str = rs.getString(2);
			String str1 = rs.getString(3);
			String i1 = rs.getString(4);
			System.out.println(i + "\t" + str + "\t" + str1 + "\t" + i1);
		}
		return "Connected";
	}

}
