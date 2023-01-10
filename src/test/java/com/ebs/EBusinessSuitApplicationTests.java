package com.ebs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebs.entity.GroupData;
import com.ebs.repository.GroupRepository;
import com.ebs.service.GroupService;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class EBusinessSuitApplicationTests {

	@Mock
	GroupRepository groupRepository;

	@InjectMocks
	GroupService groupService;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void create_Group() {
		GroupData group=new GroupData(1L,"Billing","testing",null);
		when(groupRepository.save(group)).thenReturn(group);
		assertEquals(group, groupService.newGroup(group));

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
		assertEquals(group, groupService.modifyGroup(1L, group));
	}


	@Test
	@Order(3)
	@Rollback(value = false)
	public void DeleteGroup() {

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
		Assertions.assertThat(group.getId()).isGreaterThan(0) ;


	}

}


