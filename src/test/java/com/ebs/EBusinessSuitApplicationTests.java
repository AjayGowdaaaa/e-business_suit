package com.ebs;


import com.ebs.entity.DatabaseProfile;
import com.ebs.service.DatabaseProfileServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class EBusinessSuitApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DatabaseProfileServiceInterface databaseProfileServiceInterface;

	private DatabaseProfile databaseProfile;

	@Before
	public void init() {

		DatabaseProfile databaseProfile = DatabaseProfile.builder()
                .profileName("TesttoOracle12")
                .databaseUserName("pstarch")
                .databaseUserPassword("pstarch")
                .archiveUnionUser("root")
                .archiveUserPassword("root")
                .serverName("192.168.0.171")
                .sid("vis")
                .portnumber(1521).build();

	}

	@Test
	public void createDbProfile() throws Exception {

		when(databaseProfileServiceInterface.createMysqlDbp(any(DatabaseProfile.class))).thenReturn(databaseProfile);

		this.mockMvc.perform(post("/dbp/createDbProfile/{db}")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(databaseProfile)))
				.andExpect(status().isCreated())
				.andExpect((ResultMatcher) jsonPath("$.profileName",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.databaseUserName",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.databaseUserPassword",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.archiveUnionUser",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.archiveUserPassword",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.serverName",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.sid",is(databaseProfile.getProfileName())))
				.andExpect((ResultMatcher) jsonPath("$.portnumber",is(databaseProfile.getProfileName())));


	}
}
