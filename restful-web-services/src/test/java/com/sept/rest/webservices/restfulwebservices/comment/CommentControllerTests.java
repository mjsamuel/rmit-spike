
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sept.rest.webservices.restfulwebservices.comment.CommentController;
import org.junit.jupiter.api.Test;
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

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CommentController.class}, initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
@WebMvcTest
public class CommentControllerTests {
	private final static String TEST_USER_ID = "sept";
	@Autowired
	private MockMvc mockMvc;

	// @MockBean
	// CommentService commentService;

	@Test
	public void testAddComment() throws Exception {
		// Comment comment = new Comment();
		// testCommentPost(comment, "value");
		String comment = "Test comment";
		testCommentPostBasic(comment, "Test comment");

	}

    private void testCommentPostBasic(String comment, String expectation) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/thread/1/comment")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .content(comment)
//             .content(TestUtils.objectToJson(comment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
        assertEquals(expectation, response);
    }

}
