package com.ebs.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebs.entity.Programs;
@Repository
public interface ProgramRepository extends JpaRepository<Programs, Long> {


	@Query("SELECT entity.availablePrograms FROM  Programs entity")
	ArrayList<?> findallprograms(Programs program);

}
