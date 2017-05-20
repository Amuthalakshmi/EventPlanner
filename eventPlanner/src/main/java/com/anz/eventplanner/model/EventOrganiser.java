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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

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

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "event_organiser_tasks", 
			   joinColumns = { @JoinColumn(name = "event_organiser_id") }, 
			   inverseJoinColumns = { @JoinColumn(name = "task_id") })
	private Set<Task> associatedTasks = new HashSet<Task>();

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

	public Set<Task> getAssociatedTasks() {
		System.out.println("get Tasks");
		return associatedTasks;
	}

	public void setAssociatedTasks(Set<Task> associatedTasks) {
		System.out.println("set Tasks");
		this.associatedTasks = associatedTasks;
	}

	@Override
	public String toString() {
		return "Event Organiser [Event Organiser ID:" + eventOrganiserId + ", LAN ID:" + LANId + ", Name:"
				+ organiserName + "]";
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
