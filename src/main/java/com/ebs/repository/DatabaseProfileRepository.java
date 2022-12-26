package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebs.entity.DatabaseProfile;

public interface DatabaseProfileRepository extends JpaRepository<DatabaseProfile,  String > {

	DatabaseProfile findByProfileName(String profileName);
}
