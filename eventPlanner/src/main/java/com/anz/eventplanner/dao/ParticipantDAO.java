package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.Participant;

public interface ParticipantDAO {

	Participant findById(int participantId);
	void saveParticipant(Participant participant);
	void deleteParticipantById(int participantId);
	List<Participant> findAllParticipant();
	
}
