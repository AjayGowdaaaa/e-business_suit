package com.ebs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DatabaseProfile {

	@Id
    private String profileName;
    private String databaseUserName;
    private String  databaseUserPassword;
    private String archiveUnionUser;
    private String archiveUserPassword;
    private String serverName;
    private int sid;
    private int portnumber;
    private String url;
    
}
