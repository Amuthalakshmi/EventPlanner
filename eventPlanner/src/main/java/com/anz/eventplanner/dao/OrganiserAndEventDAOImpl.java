package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.OrganiserAndEvent;

@Repository("organiserAndEventDAO")
public class OrganiserAndEventDAOImpl  extends AbstractDAO<Integer, OrganiserAndEvent> implements OrganiserAndEventDAO {

	@Override
	public OrganiserAndEvent findById(int Id) {
		return getByKey(Id);		
	}

	@Override
	public void saveOrganiserAndEvent(OrganiserAndEvent organiserAndEvent) {
		persist(organiserAndEvent);
	}

	@Override
	public void deleteOrganiserAndEventById(int Id) {
		Query query = getSession().createSQLQuery("delete from event_organiser_events where Id=:Id");
		query.setInteger("Id", Id);
		query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganiserAndEvent> findAllOrganiserAndEvent() {
		Criteria criteria = createEntityCriteria();
		return (List<OrganiserAndEvent>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganiserAndEvent> findAllOrganiserAndEventByEventId(int eventId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventId", eventId));
		return (List<OrganiserAndEvent>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganiserAndEvent> findAllOrganiserAndEventByOrganiserId(int eventOrganiserId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventOrganiserId", eventOrganiserId));
		return (List<OrganiserAndEvent>) criteria.list();
	}

}
