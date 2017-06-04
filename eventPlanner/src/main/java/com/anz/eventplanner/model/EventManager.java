package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_manager")
public class EventManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_manager_id",nullable = false)
	private int eventManagerId;
			
	@Column(name= "event_manager_name")
	private String eventManagerName;
	
	@Column(name = "LAN_ID", nullable = false)
	private String LANId;

	public int getEventManagerId() {
		return eventManagerId;
	}

	public void setEventManagerId(int eventManagerId) {
		this.eventManagerId = eventManagerId;
	}
	
	public String getEventManagerName() {
		return eventManagerName;
	}

	public void setEventManagerName(String eventManagerName) {
		this.eventManagerName = eventManagerName;
	}

	public String getLANID() {
		return LANId;
	}

	public void setLANID(String LANId) {
		this.LANId = LANId;
	}

	@Override
	public String toString(){
		return "EventManager [Event Manger ID:" + eventManagerId + ", LAN ID:" + LANId + "]";
	}
	
	@Override
	public boolean equals(Object object){
		if (this == object){
			return true;
		}
		if (object == null){
			return false;
		}
		if (!(object instanceof EventManager)){
			return false;
		}
		EventManager other = (EventManager) object;
		if (this.eventManagerId != other.eventManagerId){
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		final int prime =31;
		int result = 1;
		result = prime * result + eventManagerId;
		return result;		
	}
	
}
