package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id",nullable = false)
	private int eventId;
	
	@Size(min=5, max=100)
	@Column(name = "event_name",nullable = false)
	private String eventName;
	
	@Column(name = "planned_date")
	private String eventPlannedDate;
		
	@Column(name = "target_audience")
	private String targetAudience;	
	
	@Column(name = "max_participants")
	private Integer maxParticipants;
	
	@Column(name= "event_location")
	private String eventLocation;
	
	@Column(name= "event_status")
	private String eventStatus;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventPlannedDate() {
		return eventPlannedDate;
	}

	public void setEventPlannedDate(String eventPlannedDate) {
		this.eventPlannedDate = eventPlannedDate;
	}

	public String getTargetAudience() {
		return targetAudience;
	}

	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	public Integer getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	@Override
	public String toString(){
		return "Event [ID:" + eventId + ", Name:" + eventName + ", Location: " + eventLocation + "]";
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
		Event other = (Event) object;
		if (this.eventId != other.eventId){
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		final int prime =31;
		int result = 1;
		result = prime * result + eventId;
		return result;		
	}	

}
