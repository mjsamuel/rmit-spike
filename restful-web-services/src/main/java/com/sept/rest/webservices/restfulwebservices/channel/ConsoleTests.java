package com.sept.rest.webservices.restfulwebservices.channel;

import java.util.Date;

import com.sept.rest.webservices.restfulwebservices.channel.Channel;
import com.sept.rest.webservices.restfulwebservices.channel.Channel.Visibility;

// Testing without database
public class ConsoleTests {

	public static void main(String[] args)
	{
		// Create User
		User u1 = new User(1, "JulZ");
		User u2 = new User(2, "Ali");
		User u3 = new User(3, "Luke");
		User u4 = new User(4, "Sam");
		User u5 = new User(5, "Matt");
		
		// Create Channel
		Channel c1 = new Channel(1, "Programming 1", new Date(), Visibility.EXCLUSIVE, true);
		Channel c2 = new Channel(2, "Intro to IT", new Date(), Visibility.SHARED, false);

		// Create Thread
		Thread t1 = new Thread(1, "How to subscribe to channel?");
		Thread t2 = new Thread(2, "How to delete previous thread");
		Thread t3 = new Thread(3, "Testing 123");
		
		System.out.println("Empty Channels:");
		System.out.println(c1);
		System.out.println(c2);
		
		System.out.println("------------------------------------");
		
		// Subscribe Users to Channel
		c1.subscribe(u1);
		c1.subscribe(u2);
		c1.subscribe(u3);
		
		c2.subscribe(u4);
		c2.subscribe(u5);
		c2.subscribe(u1);
		c2.subscribe(u2);
		
		System.out.println("After adding Subscribers:");
		System.out.println(c1);
		System.out.println(c2);
		
		System.out.println("------------------------------------");
		
		// Unsubscribe Users from Channel
		c1.unSubscribe(u2);
		c1.unSubscribe(u3);
		
		System.out.println("After removing Subscribers:");
		System.out.println(c1);
		System.out.println(c2);
		
		System.out.println("------------------------------------");
		
		// Add thread to Channel
		c1.addThread(t1);
		c1.addThread(t2);
		c1.addThread(t3);
		
		System.out.println("After adding Threads:");
		System.out.println(c1);
		System.out.println(c2);
		
		System.out.println("------------------------------------");
		
		// Remove Thread from Channel
		c1.removeThread(t1);
		c1.removeThread(t2);
		
		System.out.println("After removing Threads:");
		System.out.println(c1);
		System.out.println(c2);
	}
}
