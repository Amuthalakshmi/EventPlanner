package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.EventManager;

public interface EventManagerDAO {
	
	EventManager findById(int eventManagerId);
	List<EventManager> findByUserName(String userName);
	void saveEventManager(EventManager eventManager);
	void deleteEventManagerById(int eventManagerId);
	List<EventManager> findAllEventManager();
}
