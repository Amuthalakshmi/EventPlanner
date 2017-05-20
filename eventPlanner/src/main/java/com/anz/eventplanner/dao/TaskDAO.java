package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.Task;

public interface TaskDAO {
	Task findById(int taskId);
	void saveTask(Task task);
	void deleteTaskById(int taskId);
	List<Task> findAllTask();
	List<Task> findAllTaskByEvent(int eventId);
}
