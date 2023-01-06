package com.ebs.tests;

import com.ebs.controller.DatabaseProfieController;
import com.ebs.entity.DatabaseProfile;
import com.ebs.repository.DatabaseProfileRepository;
import com.ebs.service.DbProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
@RunWith(SpringRunner.class)
public class DatabaseProfileControllerTests {

    @Autowired
    private DbProfileService dbProfileService;

    @Autowired
    private DatabaseProfileRepository databaseProfileRepository;

    @MockBean
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    //
    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Before
//    public void setup() {
//        databaseProfileRepository.deleteAll();
//    }

//    @Test
//    public void createDBProfileTests() throws Exception {
//        DatabaseProfile databaseProfile = DatabaseProfile.builder()
//                .profileName("TesttoOracle12")
//                .databaseUserName("pstarch")
//                .databaseUserPassword("pstarch")
//                .archiveUnionUser("root")
//                .archiveUserPassword("root")
//                .serverName("192.168.0.171")
//                .sid("vis")
//                .portnumber(1521).build();
//        databaseProfileRepository.save(databaseProfile);
//
//        // when - action or behaviour that we are going test
//        ResultActions response = mockMvc.perform(post("/dbp/createDbProfile/{db}")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(databaseProfile)));
//
//        // then - verify the result or output using assert statements
//        response.andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.profileName").value(databaseProfile.getProfileName()))
//                .andExpect(jsonPath("$.databaseUserName").value(databaseProfile.getDatabaseUserName()))
//                .andExpect(jsonPath("$.databaseUserPassword").value(databaseProfile.getDatabaseUserPassword()))
//                .andExpect(jsonPath("$.archiveUnionUser").value(databaseProfile.getArchiveUnionUser()))
//                .andExpect(jsonPath("$.archiveUserPassword").value(databaseProfile.getArchiveUserPassword()))
////                .andExpect(jsonPath("$.serverName").value(databaseProfile.getServerName()))
//                .andExpect(jsonPath("$.sid").value(databaseProfile.getSid()))
//                .andExpect(jsonPath("$.portnumber").value(databaseProfile.getPortnumber()));
//
//    }

//    @Test
//    public void getAllDBProfiledetails() throws Exception {
//        List<DatabaseProfile> listOfDbProfiles = new ArrayList<>();
//        listOfDbProfiles.add(DatabaseProfile.builder().profileName("TesttoOracle12")
//                .databaseUserName("pstarch")
//                .databaseUserPassword("pstarch")
//                .archiveUnionUser("root")
//                .archiveUserPassword("root")
//                .serverName("192.168.0.171")
//                .sid("vis")
//                .portnumber(1521).build());
//        databaseProfileRepository.saveAll(listOfDbProfiles);
//        ResultActions response = mockMvc.perform(get("/dbp/databaseProfile/{profileName}"));
//
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.size()",
//                        is(listOfDbProfiles.size())));
//    }

//    @Test
//    public void getdetailsByDBProfileName() throws Exception {
//        DatabaseProfile databaseProfile = DatabaseProfile.builder()
//                .profileName("TesttoOracle12")
//                .databaseUserName("pstarch")
//                .databaseUserPassword("pstarch")
//                .archiveUnionUser("root")
//                .archiveUserPassword("root")
//                .serverName("192.168.0.171")
//                .sid("vis")
//                .portnumber(1521).build();
//        databaseProfileRepository.save(databaseProfile);
//
//        ResultActions response = mockMvc.perform(get("/dbp/databaseProfile/{profileName}", databaseProfile.getProfileName()));
//
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.profileName",
//                        is(databaseProfile.getProfileName())))
//                .andExpect(jsonPath("$.databaseUserName",
//                        is(databaseProfile.getDatabaseUserName())))
//                .andExpect(jsonPath("$.databaseUserPassword",
//                        is(databaseProfile.getDatabaseUserPassword())))
//                .andExpect(jsonPath("$.archiveUnionUser",
//                        is(databaseProfile.getArchiveUnionUser())))
//                .andExpect(jsonPath("$.archiveUserPassword",
//                        is(databaseProfile.getArchiveUserPassword())))
//                .andExpect(jsonPath("$.serverName",
//                        is(databaseProfile.getServerName())))
//                .andExpect(jsonPath("$.sid",
//                        is(databaseProfile.getSid())))
//                .andExpect(jsonPath("$.portnumber",
//                        is(databaseProfile.getPortnumber())));
//    }

//    @Test
//    public void updateDbProfileDetails() throws Exception {
//        DatabaseProfile databaseProfile=DatabaseProfile.builder()
//                .profileName("TesttoOracle12")
//                .databaseUserName("pstarch")
//                .databaseUserPassword("pstarch")
//                .archiveUnionUser("root")
//                .archiveUserPassword("root")
//                .serverName("192.168.0.171")
//                .sid("vis")
//                .portnumber(1521).build();
//
//        databaseProfileRepository.save(databaseProfile);
//
//        DatabaseProfile updateDatabaseProfile=DatabaseProfile.builder()
//                .profileName("TesttoOracle12")
//                .databaseUserName("pstarch1")
//                .databaseUserPassword("pstarch1")
//                .archiveUnionUser("root1")
//                .archiveUserPassword("root1")
//                .serverName("192.168.0.170")
//                .sid("vis1")
//                .portnumber(1521).build();
//        ResultActions response = mockMvc.perform(put("/dbp/updateDbProfile/{profileName}", databaseProfile.getProfileName())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updateDatabaseProfile)));
//
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.profileName",
//                        is(updateDatabaseProfile.getProfileName())))
//                .andExpect(jsonPath("$.databaseUserName",
//                        is(updateDatabaseProfile.getDatabaseUserName())))
//                .andExpect(jsonPath("$.databaseUserPassword",
//                        is(updateDatabaseProfile.getDatabaseUserPassword())))
//                .andExpect(jsonPath("$.archiveUnionUser",
//                        is(updateDatabaseProfile.getArchiveUnionUser())))
//                .andExpect(jsonPath("$.archiveUserPassword",
//                        is(updateDatabaseProfile.getArchiveUserPassword())))
//                .andExpect(jsonPath("$.serverName",
//                        is(updateDatabaseProfile.getServerName())))
//                .andExpect(jsonPath("$.sid",
//                        is(updateDatabaseProfile.getSid())))
//                .andExpect(jsonPath("$.portnumber",
//                        is(updateDatabaseProfile.getPortnumber())));
//
//    }

    @Test
    public void deleteDbProfileByProfileName() throws Exception {

//        DatabaseProfile databaseProfile=DatabaseProfile.builder()
//                .profileName("TesttoOracle12")
//                .databaseUserName("pstarch")
//                .databaseUserPassword("pstarch")
//                .archiveUnionUser("root")
//                .archiveUserPassword("root")
//                .serverName("192.168.0.171")
//                .sid("vis")
//                .portnumber(1521).build();
//
//        dbProfileService.deleteDbProfile("TesttoOracle12");
        // databaseProfileRepository.save(databaseProfile);

        ResultActions response = mockMvc.perform(delete("/dbp/delete/{profileName}", "TesttoOracle12"));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}
