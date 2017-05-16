package com.anz.eventplanner.service;

import java.util.List;

import com.anz.eventplanner.model.Child;

public interface ChildService {
	
	Child findById(int childId);
	void saveChild(Child child);
	void updateChild(Child child);
	void deleteChildById(int childId);
	List<Child> findAllChild();
	List<Child> findByParentParticipantId(int parentParticipantId);
}
