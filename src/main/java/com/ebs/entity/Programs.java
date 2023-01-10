package com.ebs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Programs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
<<<<<<< HEAD
	private String availablePrograms;
=======
//	@ManyToOne
//    @JoinColumn(name="id")
//    private GroupData groupData;
	

	private String availablePrograms;
	
	
>>>>>>> 78841a2c7ba42b4fd914a350175ca4618a50a131
}

