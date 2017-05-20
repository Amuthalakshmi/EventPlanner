package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.OrganiserAndEventDAO;
import com.anz.eventplanner.model.OrganiserAndEvent;

@Service("organiserAndEventService")
@Transactional
public class OrganiserAndEventServiceImpl implements OrganiserAndEventService {

	@Autowired
	private OrganiserAndEventDAO dao;

	@Override
	public OrganiserAndEvent findById(int Id) {
		return dao.findById(Id);
	}

	@Override
	public void saveOrganiserAndEvent(OrganiserAndEvent organiserAndEvent) {
		dao.saveOrganiserAndEvent(organiserAndEvent);
	}

	@Override
	public void deleteOrganiserAndEventById(int Id) {
		dao.deleteOrganiserAndEventById(Id);
	}

	@Override
	public List<OrganiserAndEvent> findAllOrganiserAndEvent() {
		return dao.findAllOrganiserAndEvent();
	}

	@Override
	public List<OrganiserAndEvent> findAllOrganiserAndEventByEventId(int eventId) {
		return dao.findAllOrganiserAndEventByEventId(eventId);
	}

	@Override
	public List<OrganiserAndEvent> findAllOrganiserAndEventByOrganiserId(int eventOrganiserId) {
		return dao.findAllOrganiserAndEventByOrganiserId(eventOrganiserId);
	}

}
