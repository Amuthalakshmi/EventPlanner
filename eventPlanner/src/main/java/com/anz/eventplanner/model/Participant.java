package com.anz.eventplanner.model;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "participant")
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_id", nullable = false)
	private int participantId;

	@Column(name = "LAN_ID")
	private String LANId;

	@Column(name = "number_of_children")
	private int numberOfChildren;

	@Column(name = "location")
	private String location;

	@Column(name = "level")
	private String level;
	
	@Column(name = "registration_status")
	private String registrationStatus;

	@OneToMany(mappedBy = "parent", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)	
	private List<Child> children = new ArrayList<Child>();
	
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE})
    @JoinColumn(name = "event_id", referencedColumnName="event_id")
    private Event event;

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String getLANId() {
		return LANId;
	}

	public void setLANId(String LANId) {
		this.LANId = LANId;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Participant [Participant ID:" + participantId + ", LAN ID:" + LANId + "]";
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Participant)) {
			return false;
		}
		Participant other = (Participant) object;
		if (this.participantId != other.participantId) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + participantId;
		return result;
	}

}
