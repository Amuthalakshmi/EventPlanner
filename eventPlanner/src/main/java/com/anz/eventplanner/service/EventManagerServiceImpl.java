package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.EventManagerDAO;
import com.anz.eventplanner.model.EventManager;

@Service("eventManagerService")
@Transactional
public class EventManagerServiceImpl implements EventManagerService{

	@Autowired
	private EventManagerDAO dao;
	
	@Override
	public EventManager findById(int eventManagerId) {
		return dao.findById(eventManagerId);
	}

	@Override
	public List<EventManager> findByUserName(String userName) {
		return dao.findByUserName(userName);
	}

	@Override
	public void saveEventManager(EventManager eventManager) {
		dao.saveEventManager(eventManager);		
	}
	
	/**
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from Database and update it with proper values within transaction.
	 * It will be updated in Database once transaction ends.
	 */
	@Override
	public void updateEventManager(EventManager eventManager) {		
		EventManager entity = dao.findById(eventManager.getEventManagerId());		
		
		if (entity != null) {
			entity.setEmployeeId(eventManager.getEmployeeId());
			entity.setUserName(eventManager.getUserName());
		}
	}

	@Override
	public void deleteEventManagerById(int eventManagerId) {
		dao.deleteEventManagerById(eventManagerId);
	}

	@Override
	public List<EventManager> findAllEventManager() {
		return dao.findAllEventManager();
	}

}
