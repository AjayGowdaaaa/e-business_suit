package com.ebs.service;

import com.ebs.entity.DatabaseProfile;

public interface DbProfileServiceInterface {

	
	DatabaseProfile createDbProfile (DatabaseProfile databaseProfile);
	DatabaseProfile getDatabaseProfileByProfileName(String profileName);
	DatabaseProfile updateDbProfile (Long id, DatabaseProfile databaseProfile);
	void deleteDbProfile (String profileName);
	
}
