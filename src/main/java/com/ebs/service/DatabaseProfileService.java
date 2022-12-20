package com.ebs.service;

import com.ebs.entity.DatabaseProfile;

public interface DatabaseProfileService {

	DatabaseProfile createDbProfile (DatabaseProfile databaseProfile);
	DatabaseProfile getDatabaseProfileByProfileName(String profileName);
	DatabaseProfile updateDbProfile (String profileName, DatabaseProfile databaseProfile);
	void deleteDbProfile (String profileName);
}
