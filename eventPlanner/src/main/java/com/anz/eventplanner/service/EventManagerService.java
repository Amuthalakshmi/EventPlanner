package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.EventManager;

public interface EventManagerService {
	
	EventManager findById(int eventManagerId);
	List<EventManager> findByUserName(String userName);
	void saveEventManager(EventManager eventManager);
	void updateEventManager(EventManager eventManager);
	void deleteEventManagerById(int eventManagerId);
	List<EventManager> findAllEventManager();	
	
}
