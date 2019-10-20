package com.sept.rest.webservices.restfulwebservices.channel;

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
import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.http.HttpStatus;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(classes = RestfulWebServicesApplication.class)
public class ChannelResourceTests {
	private final static String TEST_USER_ID = "sept";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired ChannelRepository channelRepository;
	private String channel = "{ "
			+ "\"id\": 1, "
			+ "\"name\": \"Test Channel\", "
			+ "\"datetime\": \"Fri Sep 20 22:00:00 AEST 2019\", "
			+ "\"visibility\": 0, "
			+ "\"archived\": 0 "
			+ "}";
	
	// Currently Not working with CollectionTable @Test
	public void testAddChannel() throws Exception {
		testChannelPost(channel);
	}

	@Test
	public void testGetChannels() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/channel/")
				.with(user(TEST_USER_ID))
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testChannelGetById() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/api/channel/1?user_id=1")
				.with(user(TEST_USER_ID))
				.with(csrf())
				.content("1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void testChannelGetByUserId() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/channel?user=1")
				.with(user(TEST_USER_ID))
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	// Currently Not working with CollectionTable @Test
	public void testUpdateChannel() throws Exception {
		testChannelPut(channel);
	}

	public void testChannelPut(String channel) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/channel/1")
				.with(user(TEST_USER_ID))
				.with(csrf())
				.content(channel)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

	private void testChannelPost(String channel) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/channel/")
				.with(user(TEST_USER_ID))
				.with(csrf())
				.content(channel)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}

}
