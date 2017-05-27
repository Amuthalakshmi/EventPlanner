package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.ActivityDAO;
import com.anz.eventplanner.model.Activity;

@Service("activityService")
@Transactional
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityDAO dao;
	
	@Override
	public Activity findById(int activityId) {
		return dao.findById(activityId);
	}

	@Override
	public void saveActivity(Activity activity) {
		dao.saveActivity(activity);
	}

	@Override
	public void updateActivity(Activity activity) {
		Activity entity = dao.findById(activity.getActivityId());
		if (entity != null){
			entity.setActivityName(activity.getActivityName());
			entity.setActivityDetails(activity.getActivityDetails());
			entity.setActivityLocation(activity.getActivityLocation());
			entity.setHomeLocation(activity.getHomeLocation());
			entity.setActivityStatus(activity.getActivityStatus());
			entity.setStartTime(activity.getStartTime());
			entity.setEndTime(activity.getEndTime());
			entity.setMinAge(activity.getMinAge());
			entity.setMaxAge(activity.getMaxAge());
		}
	}

	@Override
	public void deleteActivityById(int activityId) {
		dao.deleteActivityById(activityId);
	}

	@Override
	public List<Activity> findAllActivities() {
		return dao.findAllActivity();
	}
	

}
