package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Task;

@Repository("taskDAO")
public class TaskDAOImpl extends AbstractDAO<Integer, Task> implements TaskDAO{

	@Override
	public Task findById(int taskId) {
		Task task = getByKey(taskId);
		if(task != null){
			Hibernate.initialize(task.getAssociatedOrganisers());
		}
		return task;
	}

	@Override
	public void saveTask(Task task) {
		persist(task);
	}

	@Override
	public void deleteTaskById(int taskId) {
		Query query = getSession().createSQLQuery("delete from Task where task_id=:taskId");
		query.setInteger("taskId", taskId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAllTask() {
		Criteria criteria = createEntityCriteria();
		List<Task> tasks = (List<Task>) criteria.list();
		for(Task task:tasks){
			Hibernate.initialize(task.getAssociatedOrganisers());
		}
		return tasks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAllTaskByEvent(int eventId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("eventId", eventId));
		List<Task> tasks = (List<Task>) criteria.list();
		for(Task task:tasks){
			Hibernate.initialize(task.getAssociatedOrganisers());
		}
		return tasks;
	}

}
