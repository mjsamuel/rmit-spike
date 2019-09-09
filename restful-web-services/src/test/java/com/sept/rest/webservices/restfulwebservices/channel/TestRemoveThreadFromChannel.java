package com.sept.rest.webservices.restfulwebservices.channel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.sept.rest.webservices.restfulwebservices.channel.Channel.Visibility;

public class TestRemoveThreadFromChannel {
	private static int counter = 0;

	@Test
	public void testRemoveThreadFromChannel() {
		// Create Objects
		Channel c1 = new Channel(++counter, "SEPT", new Date(), Visibility.SHARED, false);
		Thread t1 = new Thread(++counter, "Test Thread");

		// Assert that Objects are created/not null
		assertThat(c1, instanceOf(Channel.class));
		assertThat(t1, instanceOf(Thread.class));
		assertNotNull(c1);
		assertNotNull(t1);

		// Add thread to channel
		c1.addThread(t1);

		// Assert that channel has a thread
		assertNotNull(c1.displayThreads());

		// Remove thread from channel
		c1.removeThread(t1);

		// Assert that thread has been removed
		assertNull(c1.displayThreads());

	}
}
