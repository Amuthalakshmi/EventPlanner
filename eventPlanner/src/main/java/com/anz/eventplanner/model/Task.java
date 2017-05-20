package com.anz.eventplanner.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id", nullable = false)
	private Integer taskId;

	@Column(name = "event_id", nullable = false)
	private Integer eventId;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "task_description")
	private String taskDescription;

	@Column(name = "task_status")
	private String taskStatus;

	@Column(name = "task_blog")
	private String taskBlog;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="associatedTasks")
	private Set<EventOrganiser> associatedOrganisers = new HashSet<EventOrganiser>();

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskBlog() {
		return taskBlog;
	}

	public void setTaskBlog(String taskBlog) {
		this.taskBlog = taskBlog;
	}

	public Set<EventOrganiser> getAssociatedOrganisers() {
		return associatedOrganisers;
	}

	public void setAssociatedOrganisers(Set<EventOrganiser> associatedOrganisers) {
		this.associatedOrganisers = associatedOrganisers;
	}

	@Override
	public String toString() {
		return "Task [Task ID:" + taskId + ", Task name:" + taskName + "]";
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Event)) {
			return false;
		}
		Task other = (Task) object;
		if (this.taskId != other.taskId) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + taskId;
		return result;
	}

}
