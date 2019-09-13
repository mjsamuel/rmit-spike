package com.sept.rest.webservices.restfulwebservices;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.sept.rest.webservices.restfulwebservices.thread.Thread;
import com.sept.rest.webservices.restfulwebservices.thread.ThreadController;

public class ThreadTest extends AbstractTest {
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	
	@Test
	public void createThreadTest() throws Exception {
		String uri = "/api/thread";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
   @Test
   public void getThreadList() throws Exception {
      String uri = "/api/thread";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Thread[] threadList = super.mapFromJson(content, Thread[].class);
      assertTrue(threadList.length > 0);
   }
   
   @Test
   public void createThread() throws Exception {
      String uri = "/api/thread";
      Thread Thread = new Thread();
      Thread.setId((long)2);
      Thread.setTitle("Title");
      String inputJson = super.mapToJson(Thread);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Thread is created successfully");
   }
   
   @Test
   public void updateProduct() throws Exception {
      String uri = "/api/thread/2";
      Thread Thread = new Thread();
      Thread.setTitle("Other Title");
      String inputJson = super.mapToJson(Thread);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Thread is updated successsfully");
   }
   
   @Test
   public void deleteProduct() throws Exception {
      String uri = "/api/thread/2";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Thread is deleted successsfully");
   }
}
