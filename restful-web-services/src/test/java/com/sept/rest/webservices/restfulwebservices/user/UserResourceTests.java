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
	private final static String TEST_USER_ID = "sept";	
	
	private final static String REGISTERED_USERNAME = "{ "
			+ "\"email\": \"@student.rmit.edu.au\", "
			+ "\"username\": \"sept\", "
			+ "\"password\": \"dummy\", "
			+ "\"firstName\": \"john\", "
			+ "\"lastName\": \"doe\""
			+ "}";
	private final static String REGISTERED_EMAIL = "{ "
			+ "\"email\": \"s1234567@student.rmit.edu.au\", "
			+ "\"username\": \"sept\", "
			+ "\"password\": \"dummy\", "
			+ "\"firstName\": \"john\", "
			+ "\"lastName\": \"doe\""
			+ "}";
	private final static String UNREGISTERED_USER = "{ "
			+ "\"email\": \"s013579@student.rmit.edu.au\", "
			+ "\"username\": \"new-user\", "
			+ "\"password\": \"whateverman\", "
			+ "\"firstName\": \"john\", "
			+ "\"lastName\": \"doe\""
			+ "}";
	private final static String UPDATED_USER = "{ "
			+ "\"email\": \"s1234567@student.rmit.edu.au\", "
			+ "\"username\": \"sept\", "
			+ "\"password\": \"changed-password\", "
			+ "\"firstName\": \"john\", "
			+ "\"lastName\": \"doe\""
			+ "}";

	@Test
	public void testRegisterValidUser() throws Exception {
		MockHttpServletResponse response = testUserPost(UNREGISTERED_USER);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testRegisterInvalidUsername() throws Exception {
		MockHttpServletResponse response = testUserPost(REGISTERED_USERNAME);
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}
	
	@Test
	public void testRegisterInvalidEmail() throws Exception {
		MockHttpServletResponse response = testUserPost(REGISTERED_EMAIL);
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}
	
	@Test
	public void testRegisterMissingEmail() throws Exception {
		final String UNREGISTERED_USER_MISSING_FIELD = "{ "
				+ "\"email\": \"\", "
				+ "\"username\": \"new-user\", "
				+ "\"password\": \"whateverman\", "
				+ "\"firstName\": \"john\", "
				+ "\"lastName\": \"doe\""
				+ "}";
		
		MockHttpServletResponse response = testUserPost(UNREGISTERED_USER_MISSING_FIELD);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}
	
	@Test
	public void testRegisterMissingUsername() throws Exception {
		final String UNREGISTERED_USER_MISSING_FIELD = "{ "
				+ "\"email\": \"s013579@student.rmit.edu.au\", "
				+ "\"username\": \"\", "
				+ "\"password\": \"whateverman\", "
				+ "\"firstName\": \"john\", "
				+ "\"lastName\": \"doe\""
				+ "}";
		
		MockHttpServletResponse response = testUserPost(UNREGISTERED_USER_MISSING_FIELD);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}
	
	@Test
	public void testRegisterMissingPassword() throws Exception {
		final String UNREGISTERED_USER_MISSING_FIELD = "{ "
				+ "\"email\": \"s013579@student.rmit.edu.au\", "
				+ "\"username\": \"new-user\", "
				+ "\"password\": \"\", "
				+ "\"firstName\": \"john\", "
				+ "\"lastName\": \"doe\""
				+ "}";
		
		MockHttpServletResponse response = testUserPost(UNREGISTERED_USER_MISSING_FIELD);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}
		
	@Test
	public void testRegisterMissingFirstName() throws Exception {
		final String UNREGISTERED_USER_MISSING_FIRSTNAME = "{ "
				+ "\"email\": \"s013579@student.rmit.edu.au\", "
				+ "\"username\": \"new-user\", "
				+ "\"password\": \"whateverman\", "
				+ "\"firstName\": \"\", "
				+ "\"lastName\": \"doe\""
				+ "}";
		
		MockHttpServletResponse response = testUserPost(UNREGISTERED_USER_MISSING_FIRSTNAME);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}
	
	@Test
	public void testRegisterMissingLastName() throws Exception {
		final String UNREGISTERED_USER_MISSING_FIRSTNAME = "{ "
				+ "\"email\": \"s013579@student.rmit.edu.au\", "
				+ "\"username\": \"new-user\", "
				+ "\"password\": \"whateverman\", "
				+ "\"firstName\": \"john\", "
				+ "\"lastName\": \"\""
				+ "}";
				
		MockHttpServletResponse response = testUserPost(UNREGISTERED_USER_MISSING_FIRSTNAME);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals("", response.getContentAsString());
	}

	@Test
	public void testGetUsers() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/").with(csrf())
                .with(user(TEST_USER_ID))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testUserGetById() throws Exception {
		long userId = 1;
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + userId)
				.with(csrf())
                .with(user(TEST_USER_ID))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testUpdateUser() throws Exception {
		testUserPut(1, UPDATED_USER);
	}

	public void testUserPut(long userId, String user) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/" + userId)
				.with(csrf())
                .with(user(TEST_USER_ID))
				.content(user)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	private MockHttpServletResponse testUserPost(String user) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .with(csrf())
                .content(user)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

		return result.getResponse();
	}
}
