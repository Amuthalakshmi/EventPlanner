package com.anz.eventplanner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="child")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_id", nullable = false)
	private int childId;

	@Column(name = "parent_participant_id", nullable = false)
	private int parentParticipantId;

	@Column(name = "event_id", nullable = false)
	private int eventId;

	@Column(name = "child_name")
	private String childName;

	@Column(name = "child_age")
	private Integer childAge;
	
	@Column(name = "child_gender")
	private String childGender;
	
	@Column(name = "child_dietary_requirement")
	private String childDietaryRequirement;
	
	@Column(name = "is_child_allergic")
	private String isChildAllergic;
	
	@Column(name = "other_details")
	private String otherDetails;
	
	@Column(name = "activity_id")
	private int activityId;

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public int getParentParticipantId() {
		return parentParticipantId;
	}

	public void setParentParticipantId(int parentParticipantId) {
		this.parentParticipantId = parentParticipantId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Integer getChildAge() {
		return childAge;
	}

	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}

	public String getChildGender() {
		return childGender;
	}

	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}

	public String getChildDietaryRequirement() {
		return childDietaryRequirement;
	}

	public void setChildDietaryRequirement(String childDietaryRequirement) {
		this.childDietaryRequirement = childDietaryRequirement;
	}

	public String getIsChildAllergic() {
		return isChildAllergic;
	}

	public void setIsChildAllergic(String isChildAllergic) {
		this.isChildAllergic = isChildAllergic;
	}


	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	@Override
	public String toString() {
		return "Child [Child ID:" + childId + ", Parent participant ID:" + parentParticipantId + ", Child Name:" + childName
				+ "]";
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
		Child other = (Child) object;
		if (this.childId != other.childId) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + childId;
		return result;
	}


}
