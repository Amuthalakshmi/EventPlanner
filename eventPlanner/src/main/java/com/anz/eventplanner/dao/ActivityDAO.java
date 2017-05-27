package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.Activity;

public interface ActivityDAO {
	Activity findById(int activityId);
	void saveActivity(Activity activity);
	void deleteActivityById(int activityId);	
	List<Activity> findAllActivity();
}
