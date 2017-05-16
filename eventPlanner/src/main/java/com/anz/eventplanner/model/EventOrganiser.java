package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_organiser")
public class EventOrganiser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_organiser_id", nullable = false)
	private int eventOrganiserId;

	@Column(name = "LAN_ID", nullable = false)
	private String LANId;

	@Column(name = "organiser_name", nullable = false)
	private String organiserName;

	@Column(name = "category")
	private String category;
	
	@Column(name = "location")
	private String location;

	@Column(name = "task_id")
	private int taskId;

	@Column(name = "activity_id")
	private int activityId;
	
	public int getEventOrganiserId() {
		return eventOrganiserId;
	}

	public void setEventOrganiserId(int eventOrganiserId) {
		this.eventOrganiserId = eventOrganiserId;
	}

	public String getLANId() {
		return LANId;
	}

	public void setLANId(String lANId) {
		LANId = lANId;
	}

	public String getOrganiserName() {
		return organiserName;
	}

	public void setOrganiserName(String organiserName) {
		this.organiserName = organiserName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	@Override
	public String toString() {
		return "Event Organiser [Event Organiser ID:" + eventOrganiserId + ", LAN ID:" + LANId
				+ ", Name:" + organiserName + "]";
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
		EventOrganiser other = (EventOrganiser) object;
		if (this.eventOrganiserId != other.eventOrganiserId) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventOrganiserId;
		return result;
	}

}
