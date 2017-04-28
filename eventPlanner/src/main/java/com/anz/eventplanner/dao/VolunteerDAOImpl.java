package com.anz.eventplanner.dao;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Volunteer;

@Repository("volunteerDAO")
public class VolunteerDAOImpl  extends AbstractDAO<Integer, Volunteer> implements VolunteerDAO{

}
