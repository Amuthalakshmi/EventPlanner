package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventManager;

@Repository("eventManagerDAO")
public class EventManagerDAOImpl extends AbstractDAO<Integer, EventManager> implements EventManagerDAO {

	@Override
	public EventManager findById(int eventManagerId) {
		return getByKey(eventManagerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventManager> findByUserName(String userName) {
		List<EventManager> eventManagers = (List<EventManager>) getEntityManager()
				.createQuery("SELECT em FROM EventManager em WHERE em.userName LIKE userName")
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
				.createQuery("SELECT em FROM EventManager em").getSingleResult();
		return eventManagers;
	}

	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}
}
