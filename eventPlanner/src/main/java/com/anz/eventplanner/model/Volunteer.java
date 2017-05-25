package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "volunteer")
public class Volunteer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "volunteer_id", nullable = false)
	private int volunteerId;

	@Column(name = "employee_id", nullable = false)
	private int employeeId;

	@Column(name = "event_id", nullable = false)
	private int eventId;

	@Column(name = "user_name")
	private int userName;

	@Column(name = "activity_id")
	private int activityId;

	@Column(name = "task_id")
	private int taskId;

	public int getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(int volunteerId) {
		this.volunteerId = volunteerId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getUserName() {
		return userName;
	}

	public void setUserName(int userName) {
		this.userName = userName;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public String toString() {
		return "Volunteer [Volunteer ID:" + volunteerId + ", Employee ID:" + employeeId + ", User Name:" + userName
				+ "]";
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Volunteer)) {
			return false;
		}
		Volunteer other = (Volunteer) object;
		if (this.volunteerId != other.volunteerId) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + volunteerId;
		return result;
	}

}
