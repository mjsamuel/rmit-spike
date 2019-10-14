package com.sept.rest.webservices.restfulwebservices.comment;

import static org.hamcrest.MatcherAssert.assertThat;
import com.jcabi.matchers.RegexMatchers;

import static org.junit.jupiter.api.Assertions.*;
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
import org.springframework.http.HttpHeaders;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(classes = RestfulWebServicesApplication.class)
public class CommentResourceTests {
	private final static String TEST_USER_ID = "sept";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAddComment() throws Exception {
        String comment = "{ \"authorId\": 1, \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", \"threadId\": 1 }";
        String url = "/api/thread/1/comment";
        MockHttpServletResponse response = testPost(url, comment);

        assertNotNull(response.getContentAsString());
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat(response.getHeader(HttpHeaders.LOCATION),
                RegexMatchers.matchesPattern("^http://localhost/api/thread/1/comment/\\d"));
	}

	@Test
    public void testAddCommentFailNoAuthor() throws Exception {
        String comment = "{ \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", \"threadId\": 1 }";
        String url = "/api/thread/1/comment";
        MockHttpServletResponse response = testPost(url, comment);

        assertNotNull(response.getContentAsString());
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    }

    @Test
    public void testAddCommentFailNoContent() throws Exception {
        String comment = "{ \"authorId\": 1, \"upspikes\": 10, \"downspikes\": 3, \"threadId\": 1 }";
        String url = "/api/thread/1/comment";
        MockHttpServletResponse response = testPost(url, comment);

        assertNotNull(response.getContentAsString());
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    }

    @Test
    public void testAddCommentFailInvalidThreadId() throws Exception {
        String comment = "{ \"authorId\": 1, \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\" }";
        String url = "/api/thread/test_thread_id/comment";
        MockHttpServletResponse response = testPost(url, comment);

        assertNotNull(response.getContentAsString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    public void testCommentGetById() throws Exception {
        MockHttpServletResponse response = testGet("/api/comment/1");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testCommentGetByThreadId() throws Exception {
        MockHttpServletResponse response = testGet("/api/thread/1/comment");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testUpdateCommentSuccess() throws Exception {
        String comment = "{ \"id\":1, \"archived\": true }";
        String url = "/api/comment/1";
        MockHttpServletResponse response = testPut(url, comment);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testUpdateCommentFailNoId() throws Exception {
        String comment = "{ \"archived\": true }";
        String url = "/api/comment/0";
        MockHttpServletResponse response = testPut(url, comment);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
	}

    @Test
    public void testUpdateCommentFailWrongURL() throws Exception {
        String comment = "{ \"archived\": true }";
        String url = "/api/comment";
        MockHttpServletResponse response = testPut(url, comment);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }
    
	@Test
	public void testTagChannel() throws Exception {
        String comment = "{ \"authorId\": 1, \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", \"threadId\": 1, \"taggedChannels\": \"sept\" }";
        String url = "/api/thread/1/comment";
        MockHttpServletResponse response = testPost(url, comment);

        assertNotNull(response.getContentAsString());
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat(response.getHeader(HttpHeaders.LOCATION),
                RegexMatchers.matchesPattern("^http://localhost/api/thread/1/comment/\\d"));
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
