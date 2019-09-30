package com.sept.rest.webservices.restfulwebservices.wall;

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
public class WallResourceTests {

	@Autowired
	private MockMvc mockMvc;
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXB0IiwiZXhwIjoxNTcwMjYwMTczLCJpYXQiOjE1Njk2NTUzNzN9.5V2M7-scozJg2x78U92zJuHmmW4mTBCvZMIrpmcSZNKF-3xkCose7xHiH9ZOQizPtLp6AZqIkUM_ZvbkapN8QQ";
	
	@Test
	public void testUserWall() throws Exception {
		MockHttpServletResponse response = testUserWallGet(1);		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testUserWallInvalid() throws Exception {
		MockHttpServletResponse response = testUserWallGet(200);		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	private MockHttpServletResponse testUserWallGet(int userId) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + userId + "/wall")
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

		return result.getResponse();
	}
}
