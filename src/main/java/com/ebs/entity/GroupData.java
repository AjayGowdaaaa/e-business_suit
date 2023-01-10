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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
@Builder
=======
>>>>>>> 78841a2c7ba42b4fd914a350175ca4618a50a131
public class GroupData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long groupID;
	
	private String groupName;
	private String description;
<<<<<<< HEAD
	/*
	 * suppose we have more than one program associated with one group
	 * for ex GL selection,delete,archive
	 */
	private String assignPrograms;
		
	
}
	
=======
	
	
	//private String assignPrograms;
	 @ManyToOne
	  @JoinColumn(name = "program_id")
    private Programs programs;
	
}
>>>>>>> 78841a2c7ba42b4fd914a350175ca4618a50a131
