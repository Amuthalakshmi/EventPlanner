package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id",nullable = false)
	private int taskId;
	
	@Column(name = "event_id",nullable = false)
	private int eventId;	
	
	@Column(name = "task_name")
	private int taskName;

	@Column(name = "task_description")
	private int taskDescription;
	
	@Column(name = "task_status")
	private int taskStatus;		
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getTaskName() {
		return taskName;
	}

	public void setTaskName(int taskName) {
		this.taskName = taskName;
	}

	public int getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(int taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString(){
		return "Task [Task ID:" + taskId + ", Task name:" + taskName + "]";
	}
	
	@Override
	public boolean equals(Object object){
		if (this == object){
			return true;
		}
		if (object == null){
			return false;
		}
		if (!(object instanceof Event)){
			return false;
		}
		Task other = (Task) object;
		if (this.taskId != other.taskId){
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + taskId;
		return result;		
	}

}
