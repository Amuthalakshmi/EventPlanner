package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.EventOrganiser;

public interface EventOrganiserDAO {	
	EventOrganiser findById(int eventOrganiserId);
	void saveEventOrganiser(EventOrganiser eventOrganiser);
	void deleteEventOrganiserById(int eventOrganiserId);
	List<EventOrganiser> findAllEventOrganiser();
}
