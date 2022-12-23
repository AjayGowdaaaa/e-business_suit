package com.ebs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema
@Entity
public class GroupCreation {


	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Id
	private String groupName;
	private String description;
	private String assignPrograms;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "programs_groupName", referencedColumnName = "groupName")
//    private Programs programs;
	public GroupCreation() {
		super();
 	}


	public GroupCreation(Long id, String groupName, String description, String assignPrograms) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.description = description;
		this.assignPrograms = assignPrograms;
	}
	
	
}
