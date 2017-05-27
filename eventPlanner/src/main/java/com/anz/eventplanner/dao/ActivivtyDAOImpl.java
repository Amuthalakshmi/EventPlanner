package com.anz.eventplanner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Activity;

@Repository("activityDAO")
public class ActivivtyDAOImpl extends AbstractDAO<Integer, Activity> implements ActivityDAO {

	@Override
	public Activity findById(int activityId) {
		Activity activity = getByKey(activityId);
		return activity;
	}

	@Override
	public void saveActivity(Activity activity) {
		persist(activity);
	}

	@Override
	public void deleteActivityById(int activityId) {
		Activity activity = (Activity) getEntityManager().createQuery("SELECT a FROM Activity a WHERE a.activityId = :activityId ")
				.setParameter("activityId", activityId).getSingleResult();
		delete(activity);
	}

	@Override
	public List<Activity> findAllActivity() {
		@SuppressWarnings("unchecked")
		List<Activity> activities = (List<Activity>) getEntityManager().createQuery("SELECT a FROM Activity a GROUP BY a.activityStatus ORDER BY a.activityId").getResultList();
		return activities;
	}

}
