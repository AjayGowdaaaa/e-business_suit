package com.ebs.service;

import java.util.List;

import com.ebs.entity.Assigned_Programs;
import com.ebs.entity.GroupData;

import io.swagger.v3.oas.annotations.servers.Server;
@Server
public interface GroupService {
	//Assigned Programs
		Assigned_Programs AssignedPrograms(Assigned_Programs programs) throws Throwable;
		List  assignGroups( GroupData groupData) throws Exception, Throwable;
		List  getPrograms( GroupData groupData) throws Throwable;
		List  get_Particular_Program( String groupName) throws Exception;
}
