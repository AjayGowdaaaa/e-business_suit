package com.ebs.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	private String assignPrograms;
		
	
}
	