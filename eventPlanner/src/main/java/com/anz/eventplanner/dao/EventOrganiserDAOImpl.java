package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.EventOrganiser;

@Repository("eventOrganiserDAO")
public class EventOrganiserDAOImpl  extends AbstractDAO<Integer, EventOrganiser> implements EventOrganiserDAO {

	@Override
	public EventOrganiser findById(int eventOrganiserId) {
		EventOrganiser eventOrganiser = getByKey(eventOrganiserId);
		if(eventOrganiser!=null){
			System.out.println("event Organiser ID");
            Hibernate.initialize(eventOrganiser.getAssociatedTasks());
        }
		return eventOrganiser;
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
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) criteria.list();
		for(EventOrganiser eventOrganiser : eventOrganisers){
            Hibernate.initialize(eventOrganiser.getAssociatedTasks());
        }
		return eventOrganisers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategory(String category) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ilike("category", "%"+category+"%"));	
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) criteria.list();
		for(EventOrganiser eventOrganiser : eventOrganisers){
            Hibernate.initialize(eventOrganiser.getAssociatedTasks());
        }
		return eventOrganisers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllOrganisersByCategoryAndLocation(String category, String location) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ilike("category", "%"+category+"%"));	
		criteria.add(Restrictions.eq("location", location));
		List<EventOrganiser> eventOrganisers = (List<EventOrganiser>) criteria.list();
		for(EventOrganiser eventOrganiser : eventOrganisers){
            Hibernate.initialize(eventOrganiser.getAssociatedTasks());
        }
		return eventOrganisers;
	}

}
