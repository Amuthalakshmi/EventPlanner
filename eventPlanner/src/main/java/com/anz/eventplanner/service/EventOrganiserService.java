package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.EventOrganiser;

public interface EventOrganiserService {
	
	EventOrganiser findById(int eventOrganiserId);
	void saveEventOrganiser(EventOrganiser eventOrganiser);
	void updateEventOrganiser(EventOrganiser eventOrganiser);
	void deleteEventOrganiserById(int eventOrganiserId);
	List<EventOrganiser> findAllEventOrganiser();
	List<EventOrganiser> findAllOrganisersByCategory(String category);
	List<EventOrganiser> findAllOrganisersByCategoryAndLocation(String category, String location);
}
