package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.EventSpecificOrganiser;

public interface EventSpecificOrganiserService {
	EventSpecificOrganiser findById(int Id);
	void saveEventSpecificOrganiser(EventSpecificOrganiser eventSpecificOrganiser);
	void deleteEventSpecificOrganiserById(int Id);
	List<EventSpecificOrganiser> findAllEventSpecificOrganiser();
	List<EventSpecificOrganiser> findAllEventSpecificOrganiserByEventId(int eventId);
	List<EventSpecificOrganiser> findAllEventsSpecificToOrganiser(int eventOrganiserId);
}
