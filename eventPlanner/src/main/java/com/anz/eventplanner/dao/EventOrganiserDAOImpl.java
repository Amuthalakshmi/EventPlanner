package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventOrganiser;

@Repository("eventOrganiserDAO")
public class EventOrganiserDAOImpl  extends AbstractDAO<Integer, EventOrganiser> implements EventOrganiserDAO {

	@Override
	public EventOrganiser findById(int eventOrganiserId) {
		return getByKey(eventOrganiserId);
	}

	@Override
	public void saveEventOrganiser(EventOrganiser eventOrganiser) {
		persist(eventOrganiser);
	}

	@Override
	public void deleteEventOrganiserById(int eventOrganiserId) {
		Query query = getSession().createSQLQuery("delete from event_organiser where event_organiser_id=:eventOrganiserId");
		query.setInteger("eventOrganiserId", eventOrganiserId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllEventOrganiser() {
		Criteria criteria = createEntityCriteria();
		return (List<EventOrganiser>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategory(String category) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ilike("category", "%"+category+"%"));	
		return (List<EventOrganiser>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategoryAndLocation(String category, String location) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ilike("category", "%"+category+"%"));	
		criteria.add(Restrictions.eq("location", location));
		return (List<EventOrganiser>) criteria.list();
	}

}
