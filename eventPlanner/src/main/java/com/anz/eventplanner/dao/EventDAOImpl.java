package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Event;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractDAO<Integer, Event> implements EventDAO {

	@Override
	public Event findById(int eventId) {
		return getByKey(eventId);
	}

	@Override
	public void saveEvent(Event event) {
		persist(event);
	}

	@Override
	public void deleteEventById(int eventId) {
		Query query = getSession().createSQLQuery("delete from Event where event_id=:eventId");
		query.setInteger("eventId", eventId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEvent() {
		Criteria criteria = createEntityCriteria();
		return (List<Event>) criteria.list();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEventByName(String eventName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.like("eventName", eventName));
		return (List<Event>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEventByStatus(String eventStatus) {		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventStatus", eventStatus));		
		return (List<Event>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEventByStatusAndLocation(String eventStatus, String eventLocation) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventStatus", eventStatus));
		criteria.add(Restrictions.eq("eventLocation", eventLocation));
		return (List<Event>) criteria.list();
	}	

}
