package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_organiser_events")
public class EventSpecificOrganiser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private int Id;

	@Column(name = "event_organiser_id", nullable = false)
	private int eventOrganiserId;

	@Column(name = "event_id", nullable = false)
	private int eventId;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getEventOrganiserId() {
		return eventOrganiserId;
	}

	public void setEventOrganiserId(int eventOrganiserId) {
		this.eventOrganiserId = eventOrganiserId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "Event Specific Organiser [ID:" + Id + ", Event Organiser ID:" + eventOrganiserId + ", Event ID:"
				+ eventId + "]";
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
		EventSpecificOrganiser other = (EventSpecificOrganiser) object;
		if (this.Id != other.Id) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		return result;
	}

}
