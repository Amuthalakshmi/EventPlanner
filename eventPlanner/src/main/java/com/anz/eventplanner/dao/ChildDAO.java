package com.anz.eventplanner.dao;

import java.util.List;

import com.anz.eventplanner.model.Child;

public interface ChildDAO {
	
	Child findById(int childId);
	void saveChild(Child child);
	void deleteChildById(int childId);
	List<Child> findAllChild();
	List<Child> findByParentParticipantId(int parentParticipantId);
}
