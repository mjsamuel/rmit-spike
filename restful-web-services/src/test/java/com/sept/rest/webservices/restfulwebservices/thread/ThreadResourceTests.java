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
import org.junit.jupiter.api.BeforeAll;
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
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXB0IiwiZXhwIjoxNTY5Mzg5ODc4LCJpYXQiOjE1Njg3ODUwNzh9.aPcEZ-_dk_SmkY0MYUCU1KCa28mfMMgaon0iSPUbvmPdZXL2OtLNYBJN3vrcikAhbKYmjEZLxYtOnpclDoL01A";

	@Test
	public void testAddThread() throws Exception {
        String thread = "{ \"title\": \"Example Title\", \"datetime\": \"1569764365459\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Test content\", " + "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\"}";
        MockHttpServletResponse response = testPost("/api/thread", thread);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertNotNull(response.getContentAsString());

	}

    @Test
    public void testAddThreadFailEmptyContent() throws Exception {
        String thread = "{}";
        MockHttpServletResponse response = testPost("/api/thread", thread);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testAddThreadFailConflictingId() throws Exception {
        String thread = "{ \"id\": 1, \"title\": \"Example Title\", \"datetime\": \"1569764365459\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Test content\", " + "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\"}";

        MockHttpServletResponse response = testPost("/api/thread", thread);
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

	@Test
    public void testThreadsGet() throws Exception {
	    MockHttpServletResponse response = testGet("/api/thread");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }


    @Test
    public void testThreadGetById() throws Exception {
        MockHttpServletResponse response = testGet("/api/thread/1");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testThreadGetByIdFailMissing() throws Exception {
        MockHttpServletResponse response = testGet("/api/thread/0");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testThreadGetByIdFailInvalid() throws Exception {
        MockHttpServletResponse response = testGet("/api/thread/invalid");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testThreadGetByChannelId() throws Exception {

        MockHttpServletResponse response = testGet("/api/channel/1/thread");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testUpdateThread() throws Exception {
        String thread = "{ \"title\": \"Example Title\", \"datetime\": \"1569764365459\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Test content\", " + "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\"}";

        MockHttpServletResponse response = testPut("/api/thread/1", thread);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
	}

    @Test
    public void testUpdateThreadFailMissing() throws Exception {
        String thread = "{ \"title\": \"Example Title\", \"datetime\": \"1569764365459\", \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Test content\", " + "\"archived\": false, \"authorId\": \"1\", \"channelId\": \"1\"}";

        MockHttpServletResponse response = testPut("/api/thread/10", thread);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    public MockHttpServletResponse testGet(String url) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .with(user(TEST_USER_ID))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        return result.getResponse();
    }

    public MockHttpServletResponse testPut(String url, String content) throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .with(user(TEST_USER_ID))
                .with(csrf())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return result.getResponse();
    }

    private MockHttpServletResponse testPost(String url, String content) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .with(user(TEST_USER_ID))
                .with(csrf())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        return result.getResponse();
    }

}
