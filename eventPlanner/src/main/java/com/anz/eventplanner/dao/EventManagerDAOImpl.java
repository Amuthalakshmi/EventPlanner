package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventManager;

@Repository("eventManagerDAO")
public class EventManagerDAOImpl extends AbstractDAO<Integer, EventManager> implements EventManagerDAO {

	@Override
	public EventManager findById(int eventManagerId) {
		return getByKey(eventManagerId);
	}

	@Override
	public EventManager findByLANId(String LANId) {
		EventManager eventManager = null;
		try {
			eventManager = (EventManager) getEntityManager()
					.createQuery("SELECT em FROM EventManager em WHERE em.LANId = :LANId").setParameter("LANId", LANId)
					.getSingleResult();
		} catch (NoResultException nre) {

		}
		return eventManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventManager> findByUserName(String userName) {
		List<EventManager> eventManagers = (List<EventManager>) getEntityManager()
				.createQuery(
						"SELECT em FROM EventManager em WHERE em.userName LIKE userName ORDER BY em.eventManagerId")
				.setParameter("userName", userName).getResultList();
		return eventManagers;
	}

	@Override
	public void saveEventManager(EventManager eventManager) {
		persist(eventManager);
	}

	@Override
	public void deleteEventManagerById(int eventManagerId) {
		EventManager eventManager = (EventManager) getEntityManager()
				.createQuery("SELECT em FROM EventManager em WHERE em.eventManagerId = :eventManagerId")
				.setParameter("eventManagerId", eventManagerId).getSingleResult();
		delete(eventManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventManager> findAllEventManager() {
		List<EventManager> eventManagers = (List<EventManager>) getEntityManager()
				.createQuery("SELECT em FROM EventManager em ORDER BY em.eventManagerId").getResultList();
		return eventManagers;
	}

	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}
}
