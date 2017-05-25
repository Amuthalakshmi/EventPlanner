package com.anz.eventplanner.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Task;

@Repository("taskDAO")
public class TaskDAOImpl extends AbstractDAO<Integer, Task> implements TaskDAO {

	@Override
	public Task findById(int taskId) {
		Task task = getByKey(taskId);
		return task;
	}

	@Override
	public void saveTask(Task task) {
		persist(task);
	}

	@Override
	public void deleteTaskById(int taskId) {
		Task task = (Task) getEntityManager().createQuery("SELECT t FROM Task t WHERE t.taskId = :taskId ")
				.setParameter("taskId", taskId).getSingleResult();
		delete(task);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAllTask() {
		List<Task> tasks = (List<Task>) getEntityManager().createQuery("SELECT t FROM Task t GROUP BY t.tastStatus ORDER BY t.taskId").getResultList();
		return tasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAllTaskByEvent(int eventId) {
		List<Task> tasks = (List<Task>) getEntityManager()
				.createQuery("SELECT t FROM Task t WHERE t.eventId = :eventId ORDER BY t.taskId").setParameter("eventId", eventId)
				.getResultList();
		return tasks;
	}

	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}
}
