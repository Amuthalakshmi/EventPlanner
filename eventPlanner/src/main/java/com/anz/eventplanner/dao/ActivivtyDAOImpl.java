package com.anz.eventplanner.dao;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Activity;

@Repository("activityDAO")
public class ActivivtyDAOImpl extends AbstractDAO<Integer, Activity> implements ActivityDAO {

}
