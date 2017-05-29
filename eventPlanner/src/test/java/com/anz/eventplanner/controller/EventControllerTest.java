package com.anz.eventplanner.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.service.EventService;

public class EventControllerTest {
	@Mock
	EventService service;

	@Mock
	MessageSource messageSource;

	@InjectMocks
	EventController eventController;

	@Spy
	List<Event> events = new ArrayList<Event>();

	@Spy
	ModelMap model;

	@Mock
	BindingResult result;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		events = getEventList();
	}

	@Test
	public void listAllEvents() {
		when(service.findAllEvent()).thenReturn(events);
		Assert.assertEquals(eventController.listAllEvents(model), "listevents");
		Assert.assertEquals(model.get("events"), events);
		verify(service, atLeastOnce()).findAllEvent();
	}

	@Test
	public void newEvent() {
		Assert.assertEquals(eventController.newEvent(model), "addEvent");
		Assert.assertNotNull(model.get("event"));
		Assert.assertFalse((Boolean) model.get("edit"));
		Assert.assertEquals(((Event) model.get("event")).getEventId(), 0);
	}
	
	@Test
	public void saveEvent() {
		throw new RuntimeException("Test not implemented");
	}
	
	@Test
	public void editEvent() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void updateEvent() {
		throw new RuntimeException("Test not implemented");
	}
	
	@Test
	public void updateEventStatus() {
		throw new RuntimeException("Test not implemented");
	}
	
	@Test
	public void deleteEvent() {
		throw new RuntimeException("Test not implemented");
	}
	
	@Test
	public void toAddEventOrganiser() {
		throw new RuntimeException("Test not implemented");
	}
	
	@Test
	public void addEventOrganiser() {
		throw new RuntimeException("Test not implemented");
	}	

	@Test
	public void removeEventOrganiser() {
		throw new RuntimeException("Test not implemented");
	}	

	public List<Event> getEventList() {
		Event event1 = new Event();
		event1.setEventId(1);
		event1.setEventLocation("WLG");
		event1.setEventName("Bring kids to work");
		event1.setEventPlannedDate(new LocalDate());
		event1.setMaxParticipants(120);
		event1.setTargetAudience("Parents in Technology");

		Event event2 = new Event();
		event2.setEventId(2);
		event2.setEventLocation("WLG");
		event2.setEventName("Bring kids to work");
		event2.setEventPlannedDate(new LocalDate());
		event2.setEventStatus("Initiated");
		event2.setMaxParticipants(120);
		event2.setTargetAudience("Parents in Technology");

		events.add(event1);
		events.add(event2);

		return events;
	}

}
