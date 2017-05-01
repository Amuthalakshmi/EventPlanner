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
		
	@Column(name = "employee_id", nullable = false)
	private Integer employeeId;
	
	@Column(name = "user_name", nullable = false)
	private String userName;

	public int getEventManagerId() {
		return eventManagerId;
	}

	public void setEventManagerId(int eventManagerId) {
		this.eventManagerId = eventManagerId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString(){
		return "EventManager [Employee ID:" + employeeId + ", User name:" + userName + "]";
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
