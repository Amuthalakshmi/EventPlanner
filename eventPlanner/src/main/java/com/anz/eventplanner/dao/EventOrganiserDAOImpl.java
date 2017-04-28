package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
		Query query = getSession().createSQLQuery("delete from EventOrganiser where event_organiser_id=:eventOrganiserId");
		query.setInteger("eventOrganiserId", eventOrganiserId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventOrganiser> findAllEventOrganiser() {
		Criteria criteria = createEntityCriteria();
		return (List<EventOrganiser>) criteria.list();
	}

}
