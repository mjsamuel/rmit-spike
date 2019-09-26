// import org.hamcrest.CoreMatchers.instanceOf;
// import org.junit.Assert.assertNotNull;
// import org.junit.Assert.assertNull;
// import org.junit.Assert.assertThat;

// import java.util.Date;

// import org.junit.Test;

// import com.sept.rest.webservices.restfulwebservices.model.Channel.Visibility;

// public class TestUnsubscribeUserFromChannel {
// 	private static int counter = 0;

// 	@Test
// 	public void testAddUserToChannel() {
// 		// Create Objects
// 		Channel c1 = new Channel(++counter, "SEPT", new Date(), Visibility.SHARED, false);
// 		User u1 = new User(++counter, "Test User");

// 		// Assert that Objects are created/not null
// 		assertThat(c1, instanceOf(Channel.class));
// 		assertThat(u1, instanceOf(User.class));
// 		assertNotNull(c1);
// 		assertNotNull(u1);

// 		// Add subscriber to channel
// 		c1.subscribe(u1);

// 		// Assert that channel now has a subscriber added
// 		assertNotNull(c1.displaySubscribers());

// 		// Remove subscriber from Channel
// 		c1.unSubscribe(u1);

// 		// Assert that no Users are currently subscribed to thread
// 		assertNull(c1.displaySubscribers());

// 	}
// }
