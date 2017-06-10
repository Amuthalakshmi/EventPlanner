package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.Participant;

public interface ParticipantDAO {

	Participant findById(int participantId);
	List<Participant> findAllParticipantByLANId(String LANId);
	void saveParticipant(Participant participant);
	void deleteParticipantById(int participantId);
	List<Participant> findAllParticipant();
	
}
