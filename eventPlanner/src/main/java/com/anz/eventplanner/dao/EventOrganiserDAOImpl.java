package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventOrganiser;

@Repository("eventOrganiserDAO")
public class EventOrganiserDAOImpl extends AbstractDAO<Integer, EventOrganiser> implements EventOrganiserDAO {

	@Override
	public EventOrganiser findById(int eventOrganiserId) {
		EventOrganiser eventOrganiser = getByKey(eventOrganiserId);
		if (eventOrganiser != null) {
			initializeCollection(eventOrganiser.getAssociatedEvents());
		}
		return eventOrganiser;
	}

	@Override
	public EventOrganiser findByLANId(String LANId) {
		EventOrganiser eventOrganiser = null;
		try {
			eventOrganiser = (EventOrganiser) getEntityManager()
					.createQuery("SELECT eo FROM EventOrganiser eo WHERE eo.LANId = :LANId ")
					.setParameter("LANId", LANId).getSingleResult();
		} catch (NoResultException nre) {

		}
		return eventOrganiser;
	}

	@Override
	public void saveEventOrganiser(EventOrganiser eventOrganiser) {
		persist(eventOrganiser);
	}

	@Override
	public void deleteEventOrganiserById(int eventOrganiserId) {
		EventOrganiser eventOrganiser = (EventOrganiser) getEntityManager()
				.createQuery("SELECT eo FROM EventOrganiser eo WHERE eo.eventOrganiserId = :eventOrganiserId ")
				.setParameter("eventOrganiserId", eventOrganiserId).getSingleResult();
		delete(eventOrganiser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllEventOrganiser() {
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) getEntityManager()
				.createQuery("SELECT eo FROM EventOrganiser eo ORDER BY eo.eventOrganiserId").getResultList();
		for (EventOrganiser eventOrganiser : eventOrganisers) {
			initializeCollection(eventOrganiser.getAssociatedEvents());
		}
		return eventOrganisers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategory(String category) {
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) getEntityManager()
				.createQuery(
						"SELECT eo FROM EventOrganiser eo WHERE eo.category LIKE CONCAT('%',:category,'%') ORDER BY eo.eventOrganiserId")
				.setParameter("category", category).getResultList();
		for (EventOrganiser eventOrganiser : eventOrganisers) {
			initializeCollection(eventOrganiser.getAssociatedEvents());
		}
		return eventOrganisers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategoryAndLocation(String category, String location) {
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) getEntityManager()
				.createQuery(
						"SELECT eo FROM EventOrganiser eo WHERE eo.category LIKE CONCAT('%',:category,'%') AND eo.location = :location  ORDER BY eo.eventOrganiserId")
				.setParameter("category", category).setParameter("location", location).getResultList();
		for (EventOrganiser eventOrganiser : eventOrganisers) {
			initializeCollection(eventOrganiser.getAssociatedEvents());
		}
		return eventOrganisers;
	}

	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

}
