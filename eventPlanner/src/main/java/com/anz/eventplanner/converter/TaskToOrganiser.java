package com.anz.eventplanner.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.anz.eventplanner.model.Task;
import com.anz.eventplanner.service.TaskService;

@Component
public class TaskToOrganiser implements Converter<Object, Task>{
	
	@Autowired
	TaskService taskService;
	
	@Override
	public Task convert(Object element) {
		System.out.println("Organiser To Task");
		Integer taskId = Integer.parseInt((String)element);
		Task task = taskService.findById(taskId);
		System.out.println("Task: " + task);
		return task;
	}


}
