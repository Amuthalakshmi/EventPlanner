package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventManager;

@Repository("eventManagerDAO")
public class EventManagerDAOImpl  extends AbstractDAO<Integer, EventManager> implements EventManagerDAO {

	@Override
	public EventManager findById(int eventManagerId) {
		return getByKey(eventManagerId);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventManager> findByUserName(String userName) {		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.like("user_name", userName));		
		return (List<EventManager>) criteria.list();
	}

	@Override
	public void saveEventManager(EventManager eventManager) {
		persist(eventManager);		
	}

	@Override
	public void deleteEventManagerById(int eventManagerId) {
		Query query = getSession().createSQLQuery("delete from EventManager where event_manager_id=:eventManagerId");
		query.setInteger("eventManagerId", eventManagerId);
		query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventManager> findAllEventManager() {
		Criteria criteria = createEntityCriteria();
		return (List<EventManager>) criteria.list();		
	}

}
