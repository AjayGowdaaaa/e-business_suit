package com.ebs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DatabaseProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String profileName;
	
	private String databaseUserName;
	private String  databaseUserPassword;
	
	private String archiveUnionUser;
	private String archiveUserPassword;
	//Connection 1
	private String API;
	private String database;
	private String serverName;
	private int sid;
	private int portnumber;
	private String schema;
	//Connection 2
	String dbConnectionURL;
	
	
	public String getDbConnectionURL() {
		return dbConnectionURL;
	}
	public void setDbConnectionURL(String dbConnectionURL) {
		this.dbConnectionURL = dbConnectionURL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getDatabaseUserName() {
		return databaseUserName;
	}
	public void setDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
	}
	public String getDatabaseUserPassword() {
		return databaseUserPassword;
	}
	public void setDatabaseUserPassword(String databaseUserPassword) {
		this.databaseUserPassword = databaseUserPassword;
	}
	public String getArchiveUnionUser() {
		return archiveUnionUser;
	}
	public void setArchiveUnionUser(String archiveUnionUser) {
		this.archiveUnionUser = archiveUnionUser;
	}
	public String getArchiveUserPassword() {
		return archiveUserPassword;
	}
	public void setArchiveUserPassword(String archiveUserPassword) {
		this.archiveUserPassword = archiveUserPassword;
	}
	public String getAPI() {
		return API;
	}
	public void setAPI(String aPI) {
		API = aPI;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getPortnumber() {
		return portnumber;
	}
	public void setPortnumber(int portnumber) {
		this.portnumber = portnumber;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	
	
	
}
