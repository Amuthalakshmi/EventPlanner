package com.anz.eventplanner.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="activity")
public class Activity {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id",nullable = false)
	private int activityId;
	
	@Column(name = "activity_name")
	private String activityName;
	
	@Column(name = "activity_details")
	private String activityDetails;
	
	@Column(name = "min_age")
	private Integer minAge;	
	
	@Column(name = "max_age")
	private Integer maxAge;
	
	@Column(name = "activity_location")
	private String activityLocation;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "start_time")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	private LocalTime startTime;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "end_time")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	private LocalTime endTime;
	
	@Column(name = "home_location")
	private String homeLocation;
	
	@Column(name = "activity_status")
	private String activityStatus;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE})
    @JoinColumn(name = "event_id", referencedColumnName="event_id")
    private Event event;	
	
	@OneToMany(mappedBy = "activity", cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Child> registeredChildren = new HashSet<Child>();
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(String activityDetails) {
		this.activityDetails = activityDetails;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public String getActivityLocation() {
		return activityLocation;
	}

	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Set<Child> getRegisteredChildren() {
		return registeredChildren;
	}

	public void setRegisteredChildren(Set<Child> registeredChildren) {
		this.registeredChildren = registeredChildren;
	}

	@Override
	public String toString(){
		return "Activity [Activity ID:" + activityId + ", Activity name:" + activityName + "]";
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
