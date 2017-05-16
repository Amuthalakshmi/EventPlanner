package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventSpecificOrganiser;

@Repository("eventSpecificOrganiserDAO")
public class EventSpecificOrganiserDAOImpl  extends AbstractDAO<Integer, EventSpecificOrganiser> implements EventSpecificOrganiserDAO {

	@Override
	public EventSpecificOrganiser findById(int Id) {
		return getByKey(Id);		
	}

	@Override
	public void saveEventSpecificOrganiser(EventSpecificOrganiser eventSpecificOrganiser) {
		persist(eventSpecificOrganiser);
	}

	@Override
	public void deleteEventSpecificOrganiserById(int Id) {
		Query query = getSession().createSQLQuery("delete from event_organiser_events where Id=:Id");
		query.setInteger("Id", Id);
		query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventSpecificOrganiser> findAllEventSpecificOrganiser() {
		Criteria criteria = createEntityCriteria();
		return (List<EventSpecificOrganiser>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventSpecificOrganiser> findAllEventSpecificOrganiserByEventId(int eventId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventId", eventId));
		return (List<EventSpecificOrganiser>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventSpecificOrganiser> findAllEventsSpecificToOrganiser(int eventOrganiserId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventOrganiserId", eventOrganiserId));
		return (List<EventSpecificOrganiser>) criteria.list();
	}

}
