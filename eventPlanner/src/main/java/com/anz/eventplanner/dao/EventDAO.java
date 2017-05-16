package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.Event;

public interface EventDAO {
	
	Event findById(int eventId);
	void saveEvent(Event event);
	void deleteEventById(int eventId);
	List<Event> findAllEvent();
	List<Event> findAllEventByName(String eventName);
	List<Event> findAllEventByStatus(String eventStatus);
	List<Event> findAllEventByStatusAndLocation(String eventStatus, String eventLocation);
}
