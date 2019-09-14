package com.sept.rest.webservices.restfulwebservices.channel;

import org.junit.Test;

import com.sept.rest.webservices.restfulwebservices.channel.Channel.Visibility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;

public class TestCreateChannel {
	private static int counter = 0;

	@Test
	public void testCreateChannel() {
		// Create Object
		Channel c1 = new Channel(++counter, "SEPT", new Date(), Visibility.SHARED, false);
		// Assert that Object is created
		assertThat(c1, instanceOf(Channel.class));

		// Assert that Object is not null
		assertNotNull(c1);
	}
}
