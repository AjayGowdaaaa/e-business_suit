package com.ebs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema
@Entity
public class GroupCreation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String groupName;
	private String description;
	private String assignPrograms;
	public GroupCreation(Long id, String groupName, String description, String assignPrograms) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.description = description;
		this.assignPrograms = assignPrograms;
	}
	public GroupCreation() {
		super();
	}
	
	
}
