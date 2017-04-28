package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.EventOrganiserDAO;
import com.anz.eventplanner.model.EventOrganiser;

@Service("eventOrganiserService")
@Transactional
public class EventOrganiserServiceImpl implements EventOrganiserService{
	
	@Autowired
	private EventOrganiserDAO dao;
	
	@Override
	public EventOrganiser findById(int eventOrganiserId) {
		return dao.findById(eventOrganiserId);
	}

	@Override
	public void saveEventOrganiser(EventOrganiser eventOrganiser) {
		dao.saveEventOrganiser(eventOrganiser);
	}

	@Override
	public void updateEventOrganiser(EventOrganiser eventOrganiser) {
		EventOrganiser entity = dao.findById(eventOrganiser.getEventOrganiserId());
		if (entity != null){
			entity.setActivityId(eventOrganiser.getActivityId());
			entity.setCategory(eventOrganiser.getCategory());
			entity.setEmployeeId(eventOrganiser.getEmployeeId());
			entity.setTaskId(eventOrganiser.getTaskId());
			entity.setUserName(eventOrganiser.getUserName());
		}
	}

	@Override
	public void deleteEventOrganiserById(int eventOrganiserId) {
		dao.deleteEventOrganiserById(eventOrganiserId);
	}

	@Override
	public List<EventOrganiser> findAllEventOrganiser() {
		return dao.findAllEventOrganiser();
	}

}
