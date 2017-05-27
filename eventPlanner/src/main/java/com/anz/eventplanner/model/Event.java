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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

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
		 
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "planned_date")	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate eventPlannedDate;
		
	@Column(name = "target_audience")
	private String targetAudience;	
	
	@Column(name = "max_participants")
	private Integer maxParticipants;
	
	@Column(name= "event_location")
	private String eventLocation;
	
	@Column(name= "event_status")
	private String eventStatus;
		
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinTable(name = "event_organiser_events", 
			   joinColumns = { @JoinColumn(name = "event_id") }, 
			   inverseJoinColumns = { @JoinColumn(name = "event_organiser_id") })
	private Set<EventOrganiser> associatedOrganisers = new HashSet<EventOrganiser>();
	
	@OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Participant> participants = new HashSet<Participant>();
	
	@OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Task> associatedTasks = new HashSet<Task>();
	
	@OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Activity> associatedActivities = new HashSet<Activity>();

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

	public LocalDate getEventPlannedDate() {
		return eventPlannedDate;
	}

	public void setEventPlannedDate(LocalDate eventPlannedDate) {		
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

	public Set<EventOrganiser> getAssociatedOrganisers() {
		return associatedOrganisers;
	}

	public void setAssociatedOrganisers(Set<EventOrganiser> associatedOrganisers) {
		this.associatedOrganisers = associatedOrganisers;
	}
	
	public void addAssociatedOrganisers(EventOrganiser eventOrganiser){
		if(!getAssociatedOrganisers().contains(eventOrganiser)){
			getAssociatedOrganisers().add(eventOrganiser);
		}
		if(!eventOrganiser.getAssociatedEvents().contains(this)){
			eventOrganiser.getAssociatedEvents().add(this);
		}		 
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Set<Task> getAssociatedTasks() {
		return associatedTasks;
	}

	public void setAssociatedTasks(Set<Task> associatedTasks) {
		this.associatedTasks = associatedTasks;
	}

	public Set<Activity> getAssociatedActivities() {
		return associatedActivities;
	}

	public void setAssociatedActivities(Set<Activity> associatedActivities) {
		this.associatedActivities = associatedActivities;
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
