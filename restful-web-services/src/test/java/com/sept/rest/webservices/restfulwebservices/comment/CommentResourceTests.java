import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.MatcherAssert.assertThat;
import com.jcabi.matchers.RegexMatchers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sept.rest.webservices.restfulwebservices.resource.CommentResource;
import com.sept.rest.webservices.restfulwebservices.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXB0IiwiZXhwIjoxNTY4OTUyMjA5LCJpYXQiOjE1NjgzNDc0MDl9.1hWHGUWtnSUF57lc_53vEPJwKloGmViJrGqr5Anxl-S01UCMsoT2lXVxwel5JOS4-lM0tylOmbatH811bVGkbw";


	@Test
	public void testAddComment() throws Exception {

        String comment = "{ \"userId\": 1, \"upspikes\": 10, \"downspikes\": 3, \"content\": \"Hi John, I'm not sure I agree with your sentiment. SEPT is far too hard.\", \"threadId\": 1 }";
        testCommentPost(comment);

	}


    @Test
    public void testCommentGetById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/comment/1")
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

    @Test
    public void testCommentGetByThreadId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/thread/1/comment")
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

    @Test
    public void testUpdateComment() throws Exception {
        String comment = "{ \"id\":1, \"archived\": true }";
        testCommentPut(comment);
    }



    public void testCommentPut(String comment) throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/comment/1")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .content(comment)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    private void testCommentPost(String comment) throws Exception {
        System.out.println("Comment: " + comment);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/thread/1/comment")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .header("authorization", "Bearer " + token)
                .content(comment)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertThat(response.getHeader(HttpHeaders.LOCATION), 
            RegexMatchers.matchesPattern("^http://localhost/api/thread/1/comment/\\d"));
        assertNotNull(response.getContentAsString());

    }


}
