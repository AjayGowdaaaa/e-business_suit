package com.ebs.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema
@Entity

public class Assigned_Programs {
	@Id
    private String groupName;
	ArrayList programs;
}
