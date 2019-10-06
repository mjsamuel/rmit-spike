package com.sept.rest.webservices.restfulwebservices.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
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
public class SearchResourceTests {

	@Autowired
	private MockMvc mockMvc;
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXB0IiwiZXhwIjoxNTcwODc0ODU5LCJpYXQiOjE1NzAyNzAwNTl9.8a5nA1HlSMOA_i0O9RIZh5pNE6jRMdOwYATKsxCePmbA1vVLW7kCMrWQAO7zvhg5VhywCienWv3BZ2jw9n5rOA";
	
	@Test
	public void searchNoInput() throws Exception {
		MockHttpServletResponse response = makeSearch("");

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void searchNoContent() throws Exception {
		MockHttpServletResponse response = makeSearch("ZZZ");

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void validSearchUser() throws Exception {
		final String EXPECTED = "{"
				+ "\"channels\":[],"
				+ "\"users\":"
					+ "["
						+ "{"
						+ "\"id\":2,"
						+ "\"email\":\"s7654321@student.rmit.edu.au\","
						+ "\"username\":\"user\","
						+ "\"password\":\"$2a$10$r/45rWG0VCwvjrOeVkhqWucO7zmnj7TUzZWMQFH6.h12AqSLteyN2\","
						+ "\"firstName\":\"jane\","
						+ "\"lastName\":\"doe\","
						+ "\"upspikes\":0"
						+ "}"
					+ "]"
				+ "}";

		MockHttpServletResponse response = makeSearch("user");
		
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(EXPECTED, response.getContentAsString());
	}
	
	@Test
	public void validSearchChannel() throws Exception {
		final String EXPECTED = "{"
				+ "\"channels\":"
					+ "["
						+ "{\"id\":1,"
						+ "\"name\":\"SEPT\","
						+ "\"datetime\":\"2019-08-20T21:00:00.000+0000\","
						+ "\"archived\":false,"
						+ "\"visibility\":\"PRIVATE\""
						+ "}"
					+ "],"
					+ "\"users\":[]"
				+ "}";

		MockHttpServletResponse response = makeSearch("SEPT");
		
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(EXPECTED, response.getContentAsString());
	}
	
	private MockHttpServletResponse makeSearch(String query) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/search?query=" + query)
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        
        return result.getResponse();
	}

}
