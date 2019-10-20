package com.sept.rest.webservices.restfulwebservices.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.Date;
import javax.management.InvalidAttributeValueException;

import com.sept.rest.webservices.restfulwebservices.model.Thread;

@SuppressWarnings("deprecation")
public class ThreadTests {

	private final Thread thread = new Thread();
	
	@Test
	void validValueAssignment() throws InvalidAttributeValueException {

		thread.setUpspikes(10);
		assertEquals((Object) 10, thread.getUpspikes());

		thread.setUpspikes(0);
		assertEquals((Object) 0, thread.getUpspikes());

		thread.setDownspikes(10);
		assertEquals((Object) 10, thread.getDownspikes());

		thread.setDownspikes(0);
		assertEquals((Object) 0, thread.getDownspikes());
	}

	@Test
	void faultyValueAssignment() {
		assertThrows(InvalidAttributeValueException.class, () -> {
			thread.setUpspikes(-1);
		});

		assertThrows(InvalidAttributeValueException.class, () -> {
			thread.setDownspikes(-1);
		});
	}

	@Test
	void spikeCalculation() throws InvalidAttributeValueException  {
		// Positive expectation
		thread.setUpspikes(10);
		thread.setDownspikes(5);
		assertEquals((Object) 5, thread.getSpikes());

		// Neutral expectation
		thread.setUpspikes(5);
		thread.setDownspikes(5);
		assertEquals((Object) 0, thread.getSpikes());

		// Negative expectation
		thread.setUpspikes(5);
		thread.setDownspikes(10);
		assertEquals( new Integer(-5), thread.getSpikes());
	}


	@Test
	void spikeRatioCalculation() throws InvalidAttributeValueException  {
		// Makes sure it's not integer division
		thread.setUpspikes(10);
		thread.setDownspikes(5);
		assertEquals( 0.66667, thread.getSpikeRatio(), 0.0001);


		thread.setUpspikes(5);
		thread.setDownspikes(10);
		assertEquals(0.33333, thread.getSpikeRatio(), 0.0001);
	}
	
	@Test
	void timeDeltaMomentsAgo() {
		Date newDate = new Date();
		newDate.setSeconds(newDate.getSeconds() - 2);
		thread.setDatetime(newDate);
		assertEquals("moments ago", thread.getTimeDelta());
	}
	
	@Test 
	void timeDeltaMinutesAgo() {
		Date newDate = new Date();
		newDate.setMinutes(newDate.getMinutes() - 23);
		thread.setDatetime(newDate);
		assertEquals("23 minutes ago", thread.getTimeDelta());
	}
	
	@Test 
	void timeDeltaHoursAgo() {
		Date newDate = new Date();
		newDate.setHours(newDate.getHours() - 5);
		thread.setDatetime(newDate);
		assertEquals("5 hours ago", thread.getTimeDelta());
	}
	@Test 
	void timeDeltaYearsAgo() {
		Date newDate = new Date();
		newDate.setYear(newDate.getYear() - 2);
		thread.setDatetime(newDate);
		assertEquals("2 years ago", thread.getTimeDelta());
	}
}
