// import com.sept.rest.webservices.restfulwebservices.model.Channel.Visibility;

// import static org.hamcrest.CoreMatchers.instanceOf;
// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertNull;
// import static org.junit.Assert.assertThat;

// import java.util.Date;

// import org.junit.Test;

// public class TestAddThreadToChannel {
// 	private static int counter = 0;

// 	@Test
// 	public void testAddThreadToChannel() {
// 		// Create Objects
// 		Channel c1 = new Channel(++counter, "SEPT", new Date(), Visibility.SHARED, false);
// 		Thread t1 = new Thread(++counter, "Test Thread");

// 		// Assert that Objects are created/not null
// 		assertThat(c1, instanceOf(Channel.class));
// 		assertThat(t1, instanceOf(Thread.class));
// 		assertNotNull(c1);
// 		assertNotNull(t1);

// 		// Assert that no threads are currently added
// 		assertNull(c1.displayThreads());

// 		// Add thread to channel
// 		c1.addThread(t1);

// 		// Assert that channel now has a thread added
// 		assertNotNull(c1.displayThreads());
// 	}
// }