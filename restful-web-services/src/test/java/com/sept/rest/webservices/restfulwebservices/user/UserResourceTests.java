package com.sept.rest.webservices.restfulwebservices.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.sept.rest.webservices.restfulwebservices.RestfulWebServicesApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.http.HttpStatus;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(classes = RestfulWebServicesApplication.class)
public class UserResourceTests {

	@Autowired
	private MockMvc mockMvc;
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTcwMjQ2NDI5LCJpYXQiOjE1Njk2NDE2Mjl9.MBeFTkew5P3o2S-0LMNcQ89XXip-ozFpJn4MlKqjZ4e0LzK4vWpn8ROdqHe7LBqVJ77GTKoPp4DBAII2ThBfMQ";
	private String user = "{ \"id\": 1, \"username\": \"TestUser\", \"password\": \"password\", \"firstname\": \"First\", \"lastname\": \"Last\", \"upspikes\": 10 }";

	@Test
	public void testAddUser() throws Exception {
		testUserPost(user);
	}

	@Test
	public void testGetUsers() throws Exception {
		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/api/user/").with(csrf()).header("authorization", "Bearer " + token)
								.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testUserGetById() throws Exception {
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/user/1").with(csrf()).header("authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testChannelGetByUserId() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/channel?user=<user_id>").with(user(user))
				.with(csrf()).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testUpdateUser() throws Exception {
		testUserPut(user);
	}

	public void testUserPut(String user) throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/api/user/1").with(csrf())
						.header("authorization", "Bearer " + token).content(user)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	private void testUserPost(String user) throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/user/").with(csrf())
						.header("authorization", "Bearer " + token).content(user)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
}
