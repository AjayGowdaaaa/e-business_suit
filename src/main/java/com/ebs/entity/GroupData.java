package com.ebs.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class GroupData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String groupName;
	private String description;
	/*
	 * suppose we have more than one program associated with one group
	 * for ex GL selection,delete,archive
	 */
	private ArrayList<String> assignPrograms;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String> getAssignPrograms() {
		return assignPrograms;
	}
	public void setAssignPrograms(ArrayList<String> assignPrograms) {
		this.assignPrograms = assignPrograms;
	}
	public GroupData(Long id, String groupName, String description, ArrayList<String> assignPrograms) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.description = description;
		this.assignPrograms = assignPrograms;
	}
	public GroupData() {
		super();
	}





}