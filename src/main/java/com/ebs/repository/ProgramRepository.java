package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebs.entity.Programs;

public interface ProgramRepository extends JpaRepository<Programs, Long> {

	

}
