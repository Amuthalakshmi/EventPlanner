package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Event;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractDAO<Integer, Event> implements EventDAO {

	@Override
	public Event findById(int eventId) {
		Event event = getByKey(eventId);
		if (event != null) {
			initializeCollection(event.getAssociatedOrganisers());
		}
		return event;
	}

	@Override
	public void saveEvent(Event event) {
		persist(event);
	}

	@Override
	public void deleteEventById(int eventId) {
		Event event = (Event) getEntityManager().createQuery("SELECT e FROM Event e WHERE e.eventId = :eventId ")
				.setParameter("eventId", eventId).getSingleResult();
		delete(event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEvent() {
		List<Event> events = (List<Event>) getEntityManager().createQuery("SELECT e FROM Event e ORDER BY e.eventId").getResultList();
		for (Event event : events) {
			initializeCollection(event.getAssociatedOrganisers());
		}
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEventByName(String eventName) {
		List<Event> events = (List<Event>) getEntityManager()
				.createQuery("SELECT e FROM Event e WHERE e.eventName LIKE :eventName ORDER BY e.eventId")
				.setParameter("eventName", eventName).getResultList();
		for (Event event : events) {
			initializeCollection(event.getAssociatedOrganisers());
		}
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEventByStatus(String eventStatus) {
		List<Event> events = (List<Event>) getEntityManager()
				.createQuery("SELECT e FROM Event e WHERE e.eventStatus = :eventStatus ORDER BY e.eventId")
				.setParameter("eventStatus", eventStatus).getResultList();
		for (Event event : events) {
			initializeCollection(event.getAssociatedOrganisers());
		}
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEventByStatusAndLocation(String eventStatus, String eventLocation) {
		List<Event> events = (List<Event>) getEntityManager()
				.createQuery(
						"SELECT e FROM Event e WHERE e.eventStatus = :eventStatus AND e.eventLocation = :eventLocation")
				.setParameter("eventStatus", eventStatus).setParameter("eventLocation", eventLocation).getResultList();
		for (Event event : events) {
			initializeCollection(event.getAssociatedOrganisers());
		}
		return events;
	}

	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

}
