package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity")
public class Activity {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id",nullable = false)
	private int activityId;
	
	@Column(name = "activity_name")
	private int activityName;
	
	@Column(name = "activity_details")
	private int activityDetails;
	
	@Column(name = "event_id",nullable = false)
	private int eventId;	
	
	@Column(name = "min_age")
	private int minAge;	
	
	@Column(name = "max_age")
	private int maxAge;
	
	@Column(name = "location")
	private int location;		
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getActivityName() {
		return activityName;
	}

	public void setActivityName(int activityName) {
		this.activityName = activityName;
	}

	public int getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(int activityDetails) {
		this.activityDetails = activityDetails;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString(){
		return "Activty [Activity ID:" + activityId + ", Activity name:" + activityName + "]";
	}
	
	@Override
	public boolean equals(Object object){
		if (this == object){
			return true;
		}
		if (object == null){
			return false;
		}
		if (!(object instanceof Activity)){
			return false;
		}
		Activity other = (Activity) object;
		if (this.activityId != other.activityId){
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + activityId;
		return result;		
	}

}
