package com.sept.rest.webservices.restfulwebservices.login;

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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.http.HttpStatus;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(classes = RestfulWebServicesApplication.class)
public class JwtAuthenticationRestControllerTests {
	
	private final static String REGISTERED_USER = "{ "
			+ "\"username\": \"user\", "
			+ "\"password\": \"password\""
			+ "}";
	private final static String REGISTERED_USER_INVALID_PASSWORD = "{ "
			+ "\"username\": \"sept\","
			+ "\"password\": \"dumy\""
			+ "}";
	private final static String UNREGISTERED_USER = "{ "
			+ "\"username\": \"unregistered\","
			+ "\"password\": \"yRFs$%&F853\""
			+ "}";

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSuccesfulLogin() throws Exception {
		MockHttpServletResponse response = testLoginPostBasic(REGISTERED_USER);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testWrongPassword() throws Exception {
		MockHttpServletResponse response = testLoginPostBasic(REGISTERED_USER_INVALID_PASSWORD);
		
		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
		assertEquals(response.getContentAsString(), "INVALID_CREDENTIALS");
	}
	
	@Test
	public void testUnregisteredUser() throws Exception {
		MockHttpServletResponse response = testLoginPostBasic(UNREGISTERED_USER);
		
		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
		assertEquals(response.getContentAsString(), "INVALID_CREDENTIALS");
	}
	
	private MockHttpServletResponse testLoginPostBasic(String userDetails) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .with(csrf())
                .content(userDetails)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return result.getResponse();
	}
}
