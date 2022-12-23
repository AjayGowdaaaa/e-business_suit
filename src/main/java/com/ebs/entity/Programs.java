package com.ebs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ebs.repository.GroupRepository;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
@Entity
public class Programs {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Id
	private String groupName;
	private String programs;
	
//	 @OneToOne(mappedBy = "group")
//	    private GroupRepository group;
	 
	public Programs(Long id, String groupName, String programs) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.programs = programs;
	}
	public Programs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
