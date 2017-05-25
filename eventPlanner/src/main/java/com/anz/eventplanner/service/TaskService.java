package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.Task;

public interface TaskService {
	Task findById(int taskId);
	void saveTask(Task task);
	void updateTask(Task task);
	void updateTaskBlog(Task task);
	void deleteTaskById(int taskId);
	List<Task> findAllTask();	
	List<Task> findAllTaskByEvent(int eventId);
}
