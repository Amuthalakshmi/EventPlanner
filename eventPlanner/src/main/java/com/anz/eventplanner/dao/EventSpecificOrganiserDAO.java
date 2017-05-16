package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.EventSpecificOrganiser;

public interface EventSpecificOrganiserDAO {	
	EventSpecificOrganiser findById(int Id);
	void saveEventSpecificOrganiser(EventSpecificOrganiser eventSpecificOrganiser);
	void deleteEventSpecificOrganiserById(int Id);
	List<EventSpecificOrganiser> findAllEventSpecificOrganiser();
	List<EventSpecificOrganiser> findAllEventSpecificOrganiserByEventId(int eventId);
	List<EventSpecificOrganiser> findAllEventsSpecificToOrganiser(int eventOrganiserId);
}
