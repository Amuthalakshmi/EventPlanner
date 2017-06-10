package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.Participant;

public interface ParticipantService {
	
	Participant findById(int participantId);
	List<Participant> findAllParticipantByLANId(String LANId);
	void saveParticipant(Participant participant);
	void updateParticipant(Participant participant);
	void updateRegistrationStatus(Participant participant);
	void deleteParticipantById(int participantId);
	List<Participant> findAllParticipant();
	
}
