package com.ebs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.entity.Assigned_Programs;
import com.ebs.entity.GroupData;
import com.ebs.repository.AssignedPrograms_Repository;
import com.ebs.repository.GroupRepository;
import com.ebs.repository.UserRepository;
@Service
public class GroupServiceImplimentations implements GroupService{

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	GroupRepository groupRepository;
@Autowired
AssignedPrograms_Repository assingRepo;

	//Fetching all groups 

		@Override
		public List assignGroups( GroupData groupData) throws Throwable {
			List listofgroup=null;
					try{listofgroup=groupRepository.findallgroups(groupData);
					}
					catch (Exception e) {
						throw new Exception("Somithing went wrong");	
					}
						
					if(listofgroup.isEmpty()) {
						throw new Exception("the List is emity ,Add some data");
					}
			return listofgroup;
		}
		


	//Fetching all programs
		@Override
		public List getPrograms(GroupData groupData) throws Throwable {
			List listofprograms =null;
			try{listofprograms=groupRepository.findallprograms(groupData);}
			catch (Exception e) {
				throw new Exception("Somithing went wrong");	
			}
				
			if(listofprograms.isEmpty()) {
				throw new Exception("the List of programs is emity ,Add some data");
			}
			return listofprograms;
		}

	//Fetching the particular Program
		@Override
		public List get_Particular_Program(String groupName) throws Exception {
			List<List> getprograms=null;
						try 
						{
						getprograms=groupRepository.getPrograms(groupName);
						}catch (Exception e) {
							throw new Exception("Somithing went wrong");	
						}
						if(getprograms.isEmpty()) {
							throw new Exception("the List of programs is emity ,Add some data");
						}
									
					return getprograms;
		}
	//saving the Assigned programs in Seperate programs
		
		@Override
		public Assigned_Programs AssignedPrograms(Assigned_Programs programs) throws Exception {
			ArrayList<String> pgm = new ArrayList<String>();
			try{pgm.addAll(programs.getPrograms());}
			catch (Exception e) {
				throw new Exception("Somithing went wrong");
			}
			Assigned_Programs assig=new Assigned_Programs();
			try{
				assig.setPrograms(pgm);}
			catch (Exception e) {
				throw new Exception("Somithing went wrong");
			}
			assig=assingRepo.save(programs);
			
			return  assig;
		}


		

		



}
