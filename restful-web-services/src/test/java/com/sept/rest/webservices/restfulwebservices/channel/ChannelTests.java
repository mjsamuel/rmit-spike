package com.sept.rest.webservices.restfulwebservices.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sept.rest.webservices.restfulwebservices.RestfulWebServicesApplication;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.model.Channel.Visibility;
import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestfulWebServicesApplication.class)
public class ChannelTests {

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private ThreadRepository threadRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void addChannelSubscribersAndThreadsToDatabase() throws InvalidAttributeValueException {

		Channel c1 = new Channel("SEPT", Visibility.PUBLIC, new Date());

		// These threads need to be mocked. Should not be dependant on thread's actually being created - Sam 29/09/19
		List<Thread> threads = new ArrayList<>();
		Thread t1 = new Thread("How to subscribe to channel?",
				"I found a channel I like and want to subscribe to it.", new Date(), 1L, 1L, null);
		Thread t2 = new Thread("How to delete previous thread",
				"I found a solution to my previous thread and want to delete it.", new Date(), 1L, 1L, null);

		threadRepository.save(t1);
		threadRepository.save(t2);

		threads.add(t1);
		threads.add(t2);

//		List<User> subscribers = new ArrayList<>();
//		User u1 = new User("test@email.com", "Username", "Password", "FirstName", "LastName");
//		User u2 = new User("julzah@email.com", "Julzah", "password", "Julian", "Antic");
//		User u3 = new User("email@email.com", "uname", "apassword", "afname", "alname");
//
//		userRepository.save(u1);
//		userRepository.save(u2);
//		userRepository.save(u3);
//
//		subscribers.add(u1);
//		subscribers.add(u2);
//		subscribers.add(u3);
//
//		c1.setSubscribers(subscribers);
		c1.setThreads(threads);

		channelRepository.save(c1);
	}

	@Test
 	public void testAddThreadToChannel() {
		Channel c1 = new Channel("SEPT", Visibility.PUBLIC, new Date());
		Thread t1 = new Thread("How to subscribe to channel?",
				"I found a channel I like and want to subscribe to it.", new Date(), 1L, 1L, null);

 		assertNotNull(c1);
 		assertNotNull(t1);

 		assertNull(c1.getThreads());

 		c1.addThread(t1);

 		assertNotNull(c1.getThreads());
 	}

	@Test
 	public void testChangeVisibility() {
		Channel c1 = new Channel("SEPT", Visibility.PUBLIC, new Date());

 		Visibility visibility = c1.getVisibility();

 		assertNotNull(c1);

 		c1.setVisibility(Visibility.PRIVATE);

 		assertTrue("Different Visibilities", c1.getVisibility() != visibility);
 	}

	@Test
 	public void testCreateChannel() {
		Channel c1 = new Channel("SEPT", Visibility.PUBLIC, new Date());

 		assertNotNull(c1);
 	}

	@Test
 	public void testRemoveThreadFromChannel() {
		Channel c1 = new Channel("SEPT", Visibility.PUBLIC, new Date());
		Thread t1 = new Thread("How to subscribe to channel?",
				"I found a channel I like and want to subscribe to it.", new Date(), 1L, 1L, null);

 		assertNotNull(c1);
 		assertNotNull(t1);

 		c1.addThread(t1);

 		assertNotNull(c1.getThreads());

 		c1.removeThread(t1);

 		assertNull(c1.getThreads());

 	}

//	@Test
// 	public void testRemoveUserFromChannel() throws InvalidAttributeValueException {
//		Channel c1 = new Channel("SEPT", Visibility.PUBLIC);
//		User u1 = new User("julzah@email.com", "Julzah", "password", "Julian", "Antic");
//
// 		assertNotNull(c1);
// 		assertNotNull(u1);
//
// 		assertNull(c1.getSubscribers());
//
// 		c1.subscribeUser(u1);
//
// 		assertNotNull(c1.getSubscribers());
// 	}

//	@Test
// 	public void testAddUserToChannel() throws InvalidAttributeValueException {
//		Channel c1 = new Channel((long) 1, "SEPT", Visibility.SHARED, true);
//		User u1 = new User("julzah@email.com", "Julzah", "password", "Julian", "Antic");
//
// 		assertNotNull(c1);
// 		assertNotNull(u1);
//
// 		c1.subscribeUser(u1);
//
// 		assertNotNull(c1.getSubscribers());
//
// 		c1.unsubscribeUser(u1);
//
// 		assertNull(c1.getSubscribers());
//
// 	}

}
