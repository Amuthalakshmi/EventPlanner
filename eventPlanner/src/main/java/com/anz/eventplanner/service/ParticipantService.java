package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.Participant;

public interface ParticipantService {
	
	Participant findById(int participantId);
	void saveParticipant(Participant participant);
	void updateParticipant(Participant participant);
	void deleteParticipantById(int participantId);
	List<Participant> findAllParticipant();
	
}
