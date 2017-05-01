package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Participant;

@Repository("participantDAO")
public class ParticipantDAOImpl extends AbstractDAO<Integer, Participant> implements ParticipantDAO{

	@Override
	public Participant findById(int participantId) {
		return getByKey(participantId);
	}

	@Override
	public void saveParticipant(Participant participant) {
		persist(participant);		
	}

	@Override
	public void deleteParticipantById(int participantId) {
		Query query = getSession().createSQLQuery("delete from Participant where participant_id=:partcipantId");
		query.setInteger("participantId", participantId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Participant> findAllParticipant() {
		Criteria criteria = createEntityCriteria();
		return (List<Participant>) criteria.list();
	}

}
