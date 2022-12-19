package com.ebs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
@Entity(name="databaseprofile")
public class NewDatabaseProfile {
	
	@Id
	private String profilename;
	private String databaseuser;
	private String  userpassword;
	private String archiveunionuser;
	private String archiveuserpassword;
	private String servername;
	private int sid;
	private int portnumber;
	
	public String getProfilename() {
		return profilename;
	}
	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}
	public String getDatabaseuser() {
		return databaseuser;
	}
	public void setDatabaseuser(String databaseuser) {
		this.databaseuser = databaseuser;
	}
	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getArchiveunionuser() {
		return archiveunionuser;
	}
	public void setArchiveunionuser(String archiveunionuser) {
		this.archiveunionuser = archiveunionuser;
	}
	public String getArchiveuserpassword() {
		return archiveuserpassword;
	}
	public void setArchiveuserpassword(String archiveuserpassword) {
		this.archiveuserpassword = archiveuserpassword;
	}
	public String getServername() {
		return servername;
	}
	public void setServername(String servername) {
		this.servername = servername;
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
	
	
	
}
