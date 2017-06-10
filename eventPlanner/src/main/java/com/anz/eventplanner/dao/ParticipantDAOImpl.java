package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Participant;

@Repository("participantDAO")
public class ParticipantDAOImpl extends AbstractDAO<Integer, Participant> implements ParticipantDAO {

	@Override
	public Participant findById(int participantId) {
		Participant participant = getByKey(participantId);
		if (participant != null) {
			initializeCollection(participant.getChildren());
		}
		return participant;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Participant> findAllParticipantByLANId(String LANId) {
		List<Participant> participants = null;

		try {
			participants = (List<Participant>) getEntityManager()
					.createQuery("SELECT p FROM Participant p WHERE p.LANId = :LANId ORDER BY p.LANId")
					.setParameter("LANId", LANId).getResultList();
		} catch (NoResultException nre) {

		}

		for (Participant participant : participants) {
			initializeCollection(participant.getChildren());
		}

		return participants;
	}

	@Override
	public void saveParticipant(Participant participant) {
		persist(participant);
	}

	@Override
	public void deleteParticipantById(int participantId) {
		Participant participant = (Participant) getEntityManager()
				.createQuery("SELECT p FROM Participant p WHERE p.partcipantId = :partcipantId ")
				.setParameter("participantId", participantId).getSingleResult();
		delete(participant);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Participant> findAllParticipant() {
		List<Participant> participants = (List<Participant>) getEntityManager()
				.createQuery("SELECT p FROM Participant p ORDER BY p.participantId").getResultList();
		for (Participant participant: participants) {
			initializeCollection(participant.getChildren());
		}
		return participants;
	}
	
	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

}
