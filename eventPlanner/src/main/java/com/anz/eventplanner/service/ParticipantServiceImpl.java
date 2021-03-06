package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.ParticipantDAO;
import com.anz.eventplanner.model.Participant;

@Service("participantService")
@Transactional
public class ParticipantServiceImpl implements ParticipantService{

	@Autowired
	private ParticipantDAO dao;
	
	@Override
	public Participant findById(int participantId){ 
		return dao.findById(participantId);
	}
	
	@Override
	public List<Participant> findAllParticipantByLANId(String LANId){
		return dao.findAllParticipantByLANId(LANId);
	}

	@Override
	public void saveParticipant(Participant participant) {
		dao.saveParticipant(participant);				
	}

	@Override
	public void updateParticipant(Participant participant) {
		Participant entity = dao.findById(participant.getParticipantId());
		if (entity != null){
			entity.setLevel(participant.getLevel());
			entity.setLocation(participant.getLocation());
			entity.setNumberOfChildren(participant.getNumberOfChildren());
			entity.setLANId(participant.getLANId());
		}		
	}
	
	@Override
	public void updateRegistrationStatus(Participant participant) {
		Participant entity = dao.findById(participant.getParticipantId());
		if (entity != null){
			entity.setRegistrationStatus(participant.getRegistrationStatus());
		} 		
	}

	@Override
	public void deleteParticipantById(int participantId) {
		dao.deleteParticipantById(participantId);
	}

	@Override
	public List<Participant> findAllParticipant() {
		return dao.findAllParticipant();
	}

}
