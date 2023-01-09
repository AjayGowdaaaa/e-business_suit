package com.ebs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GroupData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long groupID;
	
	private String groupName;
	private String description;
	
	
	//private String assignPrograms;
	 @ManyToOne
	  @JoinColumn(name = "program_id")
    private Programs programs;
	
}