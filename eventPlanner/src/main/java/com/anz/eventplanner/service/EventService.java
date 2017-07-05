package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.Event;

public interface EventService {
	
	Event findById(int eventId);
	void saveEvent(Event event);
	void updateEvent(Event event);
	void updateEventStatus(Event event);
	void updateRegisteredParticipants(Event event);
	void updateActivitiesRegistrationStatus(Event event);
	void deleteEventById(int eventId);
	List<Event> findAllEvent();
	List<Event> findAllEventByName(String eventName);
	List<Event> findAllEventByStatus(String eventStatus);
	List<Event> findAllEventByStatusAndLocation(String eventStatus, String eventLocation);
}
