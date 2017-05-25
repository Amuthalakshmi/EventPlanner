package com.anz.eventplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.eventplanner.dao.ChildDAO;
import com.anz.eventplanner.model.Child;

@Service("childService")
@Transactional
public class ChildServiceImpl implements ChildService{
	
	@Autowired
	private ChildDAO dao;

	@Override
	public Child findById(int childId) {
		return dao.findById(childId);
	}

	@Override
	public void saveChild(Child child) {
		dao.saveChild(child);
	}

	@Override
	public void updateChild(Child child) {
		Child entity = dao.findById(child.getChildId());
		if(entity != null){
			entity.setActivityId(child.getActivityId());
			entity.setChildAge(child.getChildAge());
			entity.setIsChildAllergic(child.getIsChildAllergic());
			entity.setHasDietRequirement(child.getHasDietRequirement());
			entity.setChildFoodPreference(child.getChildFoodPreference());
			entity.setChildGender(child.getChildGender());
			entity.setChildName(child.getChildName());
			entity.setOtherDetails(child.getOtherDetails());
		}
	}

	@Override
	public void deleteChildById(int childId) {
		dao.deleteChildById(childId);		
	}

	@Override
	public List<Child> findAllChild() {
		return dao.findAllChild();
	}

	@Override
	public List<Child> findByParentParticipantId(int parentParticipantId) {
		return dao.findByParentParticipantId(parentParticipantId);
	}

}
