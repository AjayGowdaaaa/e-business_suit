package com.ebs.service;

import com.ebs.entity.DatabaseProfile;

public interface DatabaseProfileServiceInterface {

	void setRepository(String url);
	DatabaseProfile createDbProfile (DatabaseProfile databaseProfile);
	DatabaseProfile getDatabaseProfileByProfileName(String profileName);
	DatabaseProfile updateDbProfile (int id, DatabaseProfile databaseProfile);
	void deleteDbProfile (String profileName);
	void connectionWithURL (DatabaseProfile databaseProfile);
}
