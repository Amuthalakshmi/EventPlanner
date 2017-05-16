package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.EventSpecificOrganiserDAO;
import com.anz.eventplanner.model.EventSpecificOrganiser;

@Service("eventSpecificOrganiserService")
@Transactional
public class EventSpecificOrganiserServiceImpl implements EventSpecificOrganiserService {

	@Autowired
	private EventSpecificOrganiserDAO dao;

	@Override
	public EventSpecificOrganiser findById(int Id) {
		return dao.findById(Id);
	}

	@Override
	public void saveEventSpecificOrganiser(EventSpecificOrganiser eventSpecificOrganiser) {
		dao.saveEventSpecificOrganiser(eventSpecificOrganiser);
	}

	@Override
	public void deleteEventSpecificOrganiserById(int Id) {
		dao.deleteEventSpecificOrganiserById(Id);
	}

	@Override
	public List<EventSpecificOrganiser> findAllEventSpecificOrganiser() {
		return dao.findAllEventSpecificOrganiser();
	}

	@Override
	public List<EventSpecificOrganiser> findAllEventSpecificOrganiserByEventId(int eventId) {
		return dao.findAllEventSpecificOrganiserByEventId(eventId);
	}

	@Override
	public List<EventSpecificOrganiser> findAllEventsSpecificToOrganiser(int eventOrganiserId) {
		return dao.findAllEventsSpecificToOrganiser(eventOrganiserId);
	}

}
