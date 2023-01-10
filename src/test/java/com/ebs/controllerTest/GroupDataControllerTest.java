package com.ebs.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ebs.entity.GroupData;
import com.ebs.repository.GroupRepository;
import com.ebs.service.GroupServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupDataControllerTest {

	@MockBean
	private GroupServiceInterface groupService;

	@Mock
	private GroupRepository groupRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void create_Group() {
		GroupData group=new GroupData(1L,"Billing","testing",null);
		when(groupRepository.save(group)).thenReturn(group);
		try {
			this.mockMvc.perform(post("/newGroup")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(group)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(group.getId())))
			.andExpect(jsonPath("$.groupName", is(group.getGroupName())))
			.andExpect(jsonPath("$.description", is(group.getDescription())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	@Test
	@Order(2)
	@Rollback(value = false)
	public void modify_Groups() {
		GroupData group=new GroupData(1L,"Billing","testing for ModifyGroup",null);
		Optional<GroupData> groupdata = Optional.of((GroupData.builder()
				.id(1L)
				.groupName("Billing")
				.description("testing for ModifyGroup")
				.build()));
		Mockito.when(groupRepository.existsById(1L)).thenReturn(true);
		Mockito.when(groupRepository.findById(1L)).thenReturn(groupdata);
		Mockito.when(groupRepository.save(group)).thenReturn(group);
		try {
			this.mockMvc.perform(post("/Modify/{id}",1L)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(group)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(group.getId())))
			.andExpect(jsonPath("$.groupName", is(group.getGroupName())))
			.andExpect(jsonPath("$.description", is(group.getDescription())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	public void DeleteGroup() throws Exception {

		GroupData group=new GroupData(1L,"Billing","testing",null);
		Optional<GroupData> groupdata = Optional.of((GroupData.builder()
				.id(1L)
				.groupName("Billing")
				.description("testing")
				.assignPrograms(null)
				.build()));
		Mockito.when(groupRepository.existsById(1L)).thenReturn(true);
		Mockito.when(groupRepository.findById(1L)).thenReturn(groupdata);
		groupRepository.delete(group);
		groupService.deleteGroupbyid(1L);
	
			this.mockMvc.perform(post("/delete/{id}",1L))
					.andExpect(status().isNoContent());
	}
	
}


