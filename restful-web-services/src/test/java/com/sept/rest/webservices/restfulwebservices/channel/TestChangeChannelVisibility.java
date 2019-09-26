// import org.hamcrest.CoreMatchers.instanceOf;
// import org.junit.Assert.assertNotNull;
// import org.junit.Assert.assertThat;
// import org.junit.Assert.assertTrue;

// import java.util.Date;

// import org.junit.Test;

// import com.sept.rest.webservices.restfulwebservices.model.Channel.Visibility;

// public class TestChangeChannelVisibility {
// 	private static int counter = 0;

// 	@Test
// 	public void testChangeVisibility() {
// 		// Create Object
// 		Channel c1 = new Channel(++counter, "SEPT", new Date(), Visibility.SHARED, false);

// 		Visibility visibility = c1.getVisibility();

// 		// Assert that Object is created
// 		assertThat(c1, instanceOf(Channel.class));

// 		// Assert that Object is not null
// 		assertNotNull(c1);

// 		// Change Channel Visibility
// 		c1.setVisibility(Visibility.EXCLUSIVE);

// 		// Assert that Channels visibility has been changed
// 		assertTrue("Different Visibilities", c1.getVisibility() != visibility);
// 	}
// }
