package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.TaskDAO;
import com.anz.eventplanner.model.Task;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskDAO dao;

	@Override
	public Task findById(int taskId) {
		return dao.findById(taskId);
	}

	@Override
	public void saveTask(Task task) {
		dao.saveTask(task);
	}

	@Override
	public void updateTask(Task task) {
		Task entity = dao.findById(task.getTaskId());
		if (entity != null){			
			entity.setTaskDescription(task.getTaskDescription());
			entity.setTaskName(task.getTaskName());						
		}
	}
	
	@Override
	public void updateTaskBlog(Task task) {
		Task entity = dao.findById(task.getTaskId());
		if (entity != null){
			if(entity.getTaskBlog() != null){
				entity.setTaskBlog(entity.getTaskBlog() + '\n' + task.getTaskBlog());
			} else {
				entity.setTaskBlog(task.getTaskBlog());
			}
			entity.setTaskStatus(task.getTaskStatus());			
		}
	}

	@Override
	public void deleteTaskById(int taskId) {
		dao.deleteTaskById(taskId);
	}	
	
	@Override
	public List<Task> findAllTask() {
		return dao.findAllTask();
	}

	@Override
	public List<Task> findAllTaskByEvent(int eventId) {
		return dao.findAllTaskByEvent(eventId);
	}

}
