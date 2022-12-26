package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.entity.Assigned_Programs;
@Repository
public interface AssignedPrograms_Repository extends JpaRepository<Assigned_Programs, String>{

	void save(String groupNam);

	
 
}
