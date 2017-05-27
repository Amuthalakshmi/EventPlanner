package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.Activity;

public interface ActivityService {
	Activity findById(int activityId);
	void saveActivity(Activity activity);
	void updateActivity(Activity activity);
	void deleteActivityById(int activityId);
	List<Activity> findAllActivities();
}
