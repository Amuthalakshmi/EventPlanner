package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.OrganiserAndEvent;

public interface OrganiserAndEventService {
	OrganiserAndEvent findById(int Id);
	void saveOrganiserAndEvent(OrganiserAndEvent organiserAndEvent);
	void deleteOrganiserAndEventById(int Id);
	List<OrganiserAndEvent> findAllOrganiserAndEvent();
	List<OrganiserAndEvent> findAllOrganiserAndEventByEventId(int eventId);
	List<OrganiserAndEvent> findAllOrganiserAndEventByOrganiserId(int eventOrganiserId);
}
