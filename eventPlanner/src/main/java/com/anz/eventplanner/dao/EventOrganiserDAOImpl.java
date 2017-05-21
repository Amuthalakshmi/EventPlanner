package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventOrganiser;

@Repository("eventOrganiserDAO")
public class EventOrganiserDAOImpl extends AbstractDAO<Integer, EventOrganiser> implements EventOrganiserDAO {

	@Override
	public EventOrganiser findById(int eventOrganiserId) {
		EventOrganiser eventOrganiser = getByKey(eventOrganiserId);
		if (eventOrganiser != null) {
			initializeCollection(eventOrganiser.getAssociatedTasks());
			initializeCollection(eventOrganiser.getAssociatedEvents());
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
				.createQuery("SELECT eo FROM EventOrganiser eo").getResultList();
		for (EventOrganiser eventOrganiser : eventOrganisers) {
			initializeCollection(eventOrganiser.getAssociatedTasks());
			initializeCollection(eventOrganiser.getAssociatedEvents());
		}
		return eventOrganisers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategory(String category) {
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) getEntityManager()
				.createQuery("SELECT eo FROM EventOrganiser eo WHERE eo.category LIKE CONCAT('%',:category,'%')")
				.setParameter("category", category).getResultList();
		for (EventOrganiser eventOrganiser : eventOrganisers) {
			initializeCollection(eventOrganiser.getAssociatedTasks());
			initializeCollection(eventOrganiser.getAssociatedEvents());
		}
		return eventOrganisers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategoryAndLocation(String category, String location) {
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) getEntityManager()
				.createQuery(
						"SELECT eo FROM EventOrganiser eo WHERE eo.category LIKE CONCAT('%',:category,'%') AND eo.location = :location")
				.setParameter("category", category).setParameter("location", location).getSingleResult();
		for (EventOrganiser eventOrganiser : eventOrganisers) {
			initializeCollection(eventOrganiser.getAssociatedTasks());
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
