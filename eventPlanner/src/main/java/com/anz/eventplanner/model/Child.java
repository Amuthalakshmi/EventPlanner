package com.anz.eventplanner.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "child")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_id", nullable = false)
	private int childId;

	@Column(name = "child_name")
	private String childName;

	@Column(name = "child_age")
	private Integer childAge;

	@Column(name = "child_gender")
	private String childGender;
	
	@Column(name = "has_diet_requirement")
	private String hasDietRequirement;
	
	@Column(name = "child_food_preference")
	private String childFoodPreference;

	@Column(name = "is_child_allergic")
	private String isChildAllergic;

	@Column(name = "other_details")
	private String otherDetails;	
		
    @ManyToOne(cascade= {CascadeType.MERGE}, fetch=FetchType.EAGER, optional = false)
    @JoinColumn(name = "parent_participant_id", referencedColumnName="participant_id")    
    private Participant parent;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE})
    @JoinColumn(name = "activity_id", referencedColumnName="activity_id")
    private Activity activity;
	
	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
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
	
	public String getHasDietRequirement() {
		return hasDietRequirement;
	}

	public void setHasDietRequirement(String hasDietRequirement) {
		this.hasDietRequirement = hasDietRequirement;
	}

	public String getChildFoodPreference() {
		return childFoodPreference;
	}

	public void setChildFoodPreference(String childFoodPreference) {
		this.childFoodPreference = childFoodPreference;
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

	public Participant getParent() {
		return parent;
	}

	public void setParent(Participant parent) {
		this.parent = parent;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "Child [Child ID:" + childId + ", Parent participant ID:" + parent.getParticipantId() + ", Child Name:"
				+ childName + "]";
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Child)) {
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
