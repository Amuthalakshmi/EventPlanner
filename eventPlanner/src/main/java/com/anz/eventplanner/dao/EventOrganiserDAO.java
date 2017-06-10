package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.EventOrganiser;

public interface EventOrganiserDAO {	
	EventOrganiser findById(int eventOrganiserId);
	EventOrganiser findByLANId(String LANId);
	void saveEventOrganiser(EventOrganiser eventOrganiser);
	void deleteEventOrganiserById(int eventOrganiserId);
	List<EventOrganiser> findAllEventOrganiser();	
	List<EventOrganiser> findAllOrganisersByCategory(String category);
	List<EventOrganiser> findAllOrganisersByCategoryAndLocation(String category, String location);
}
