package com.anz.eventplanner.dao;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Participant;

@Repository("participantDAO")
public class ParticipantDAOImpl extends AbstractDAO<Integer, Participant> implements ParticipantDAO{

}
