package com.ebs.tests;


import com.ebs.entity.DatabaseProfile;
import com.ebs.repository.DatabaseProfileRepository;
import com.ebs.service.DatabaseProfileServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class DatabaseProfileTests {

    @Autowired
    @Mock
    private DatabaseProfileRepository databaseProfileRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private DatabaseProfileServiceInterface databaseProfileServiceInterface;

    private DatabaseProfile databaseProfile1;

    @BeforeEach
    public void setup() {
        databaseProfile1=DatabaseProfile.builder()
                .profileName("TesttoOracle12")
                .databaseUserName("pstarch")
                .databaseUserPassword("pstarch")
                .archiveUnionUser("root")
                .archiveUserPassword("root")
                .serverName("192.168.0.171")
                .sid("vis")
                .portnumber(1521).build();

    }

    // JUnit test for create Database Profile method

    @DisplayName("JUnit test for create Database Profile method")
    @Test
    public void createDbProfileTests() throws Exception {

//        List<DatabaseProfile> databaseProfile= databaseProfileRepository.findAll();
//        System.out.println(databaseProfile.size());
//        given(databaseProfileRepository.findByProfileName(databaseProfile.get(0).getProfileName()));
//               // .willReturn(Optional.empty());
//
//        given(databaseProfileRepository.saveAll(databaseProfile)).willReturn(databaseProfile);
//
////        DatabaseProfile createdDbProfile=databaseProfileServiceInterface.createMysqlDbp(databaseProfile);
//
////        assertThat(createdDbProfile).isNotNull();
//
//
//        SignUpRequest signUpRequest = new SignUpRequest("1234", "JavaChinna", user.getEmail(), user.getPassword(), user.getPassword(), SocialProvider.FACEBOOK);
////        Mockito.when(userService.registerNewUser(any(SignUpRequest.class))).thenReturn(user);
//        String json = mapper.writeValueAsString(signUpRequest);
//        mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andExpect(jsonPath("$.success").value("true")).andExpect(jsonPath("$.message").value("User registered successfully"));
//
//        // Test when user provided email already exists in the database
//        Mockito.when(userService.registerNewUser(any(SignUpRequest.class))).thenThrow(new UserAlreadyExistAuthenticationException("exists"));
//        json = mapper.writeValueAsString(signUpRequest);
//        mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.success").value("false")).andExpect(jsonPath("$.message").value("Email Address already in use!"));


        DatabaseProfile dbProfile=DatabaseProfile.builder()
                .profileName("TesttoOracle12")
                .databaseUserName("pstarch")
                .databaseUserPassword("pstarch")
                .archiveUnionUser("root")
                .archiveUserPassword("root")
                .serverName("192.168.0.171")
                .sid("vis")
                .portnumber(1521).build();
        Mockito.when(databaseProfileServiceInterface.createMysqlDbp(any(DatabaseProfile.class))).thenReturn(dbProfile);
        String json1=objectMapper.writeValueAsString(dbProfile);
        mockMvc.perform(post("/dbp/createDbProfile/{db}").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.success").value("true")).andExpect(jsonPath("$.message").value("Database created successfully"));

    }
}
