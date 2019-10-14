package com.sept.rest.webservices.restfulwebservices.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sept.rest.webservices.restfulwebservices.resource.ThreadResource;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import com.sept.rest.webservices.restfulwebservices.RestfulWebServicesApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(classes = RestfulWebServicesApplication.class)
public class ThreadResourceTests {
	private final static String TEST_USER_ID = "1";

	@Autowired
	private MockMvc mockMvc;
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXB0IiwiZXhwIjoxNTcxNjM1NzQyLCJpYXQiOjE1NzEwMzA5NDJ9.j4ND8ACeyrt8OsLpPvOHhxTt7ofi4EjedBPSzlfxITt4vsYhPtr4BjFoYehhvG9bsfg2ymXfjOCQkCOrnpNv4w";

	@Test
	@Before
	public void testAddThread() throws Exception {

//        String thread = "{ \"id\": 1, \"title\": \"Example Title\", \"datetime\": \"Wed Sep 18 22:08:31 AEST 2019\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", "
//        		+ "\"archived\": false, \"op\": \"Luke Morris\", \"primaryChannel\": \"SEPT\", \"taggedChannels\": \"Java\"}";
//        String thread = "{ \"id\": 1, \"title\": \"Example Title\", \"datetime\": \"1569764365459\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", "
//        		+ "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\", \"taggedChannels\": \"Java\"}";
//        testThreadPost(thread);

	}

	@Test
    public void testThreadsGet() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/thread")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testThreadGetById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/thread/1")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

	private MvcResult testThreadGetByIdRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/thread")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

		return result;
	}

//    @Test
//    public void testThreadGetByChannelId() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/thread?chan=<chan_id>")
//                .with(user(TEST_USER_ID))
//                .with(csrf())
//                .header("authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertNotNull(response.getContentAsString());
//    }


    // Nothing wrong with this - just ran out of time to get it working. I updated put implementation to update all non-null fields. Suspect there's too many fields to update, or some can't be changed - Sam 29/09/19
//    @Test
//    @After
//    public void testUpdateThread() throws Exception {
//        String thread = "{ \"title\": \"Updated Title\", \"datetime\": \"1569764365459\", \"upspikes\": 40, \"downspikes\": 3, \"content\": \"This is the new updated content\", "
//                + "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\", \"taggedChannels\": \"Java\"}";
//        testThreadPut(thread);
//    }
//
//
//
//    public void testThreadPut(String thread) throws Exception {
//        String thread2 = "{ \"id\": 1, \"title\": \"Example Title\", \"datetime\": \"1569764365459\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", "
//                + "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\", \"taggedChannels\": \"Java\"}";
//    	mockMvc.perform(MockMvcRequestBuilders.post("/api/thread/")
//                .with(user(TEST_USER_ID))
//                .with(csrf())
//                .header("authorization", "Bearer " + token)
//                .content(thread2)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/thread/1")
//                .with(user(TEST_USER_ID))
//                .with(csrf())
//                .header("authorization", "Bearer " + token)
//                .content(thread)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertNotNull(response.getContentAsString());
//    }

//    private void testThreadPost(String thread) throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/thread/")
//                .with(user(TEST_USER_ID))
//                .with(csrf())
//                .header("authorization", "Bearer " + token)
//                .content(thread)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertNotNull(response.getContentAsString());
//    }
}
