package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.EventDAO;
import com.anz.eventplanner.model.Event;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAO dao;

	public Event findById(int eventId) {
		return dao.findById(eventId);
	}

	@Override
	public void saveEvent(Event event) {
		dao.saveEvent(event);
	}

	/**
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from Database and update it with proper values within transaction.
	 * It will be updated in Database once transaction ends.
	 */
	@Override
	public void updateEvent(Event event) {
		Event entity = dao.findById(event.getEventId());
		if (entity != null) {
			entity.setEventName(event.getEventName());
			entity.setEventLocation(event.getEventLocation());
			entity.setEventBranch(event.getEventBranch());
			entity.setEventPlannedDate(event.getEventPlannedDate());
			entity.setMaxParticipants(event.getMaxParticipants());
			entity.setTargetAudience(event.getTargetAudience());
			entity.setEventDescription(event.getEventDescription());
			entity.setAssociatedOrganisers(event.getAssociatedOrganisers());
		}
	}
	
	/**
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from Database and update it with proper values within transaction.
	 * It will be updated in Database once transaction ends.
	 */
	@Override
	public void updateEventStatus(Event event) {
		Event entity = dao.findById(event.getEventId());
		if (entity != null) {
			entity.setEventName(event.getEventName());
			entity.setEventLocation(event.getEventLocation());
			entity.setEventBranch(event.getEventBranch());
			entity.setEventPlannedDate(event.getEventPlannedDate());
			entity.setMaxParticipants(event.getMaxParticipants());
			entity.setTargetAudience(event.getTargetAudience());
			entity.setEventDescription(event.getEventDescription());
			entity.setAssociatedOrganisers(event.getAssociatedOrganisers());
			entity.setEventStatus(event.getEventStatus());
		}
	}	

	@Override
	public void updateRegisteredParticipants(Event event) {
		Event entity = dao.findById(event.getEventId());
		if (entity != null) {
			entity.setRegisteredParticipants(event.getRegisteredParticipants());		
		}
	}	
	
	@Override
	public void deleteEventById(int eventId) {
		dao.deleteEventById(eventId);
	}

	@Override
	public List<Event> findAllEvent() {
		return dao.findAllEvent();
	}

	@Override
	public List<Event> findAllEventByName(String eventName) {
		return dao.findAllEventByName(eventName);
	}

	@Override
	public List<Event> findAllEventByStatus(String eventStatus) {
		return dao.findAllEventByStatus(eventStatus);
	}

	@Override
	public List<Event> findAllEventByStatusAndLocation(String eventStatus, String eventLocation) {
		return dao.findAllEventByStatusAndLocation(eventStatus, eventLocation);
	}	


}
