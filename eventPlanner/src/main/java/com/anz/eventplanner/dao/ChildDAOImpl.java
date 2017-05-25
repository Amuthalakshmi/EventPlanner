package com.anz.eventplanner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Child;

@Repository("childDAO")
public class ChildDAOImpl extends AbstractDAO<Integer, Child> implements ChildDAO {

	@Override
	public Child findById(int childId) {
		return getByKey(childId);
	}

	@Override
	public void saveChild(Child child) {
		persist(child);
	}

	@Override
	public void deleteChildById(int childId) {
		Child child = (Child) getEntityManager().createQuery("SELECT c FROM Child c WHERE c.childId = :childId ")
				.setParameter("childId", childId).getSingleResult();
		delete(child);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Child> findAllChild() {
		List<Child> children = (List<Child>) getEntityManager().createQuery("SELECT c FROM Child c ORDER BY c.childId").getResultList();
		return children;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Child> findByParentParticipantId(int parentParticipantId) {
		List<Child> children = (List<Child>) getEntityManager()
				.createQuery("SELECT c FROM Child c WHERE c.parentParticipantId = :parentParticipantId  ORDER BY c.childId")
				.setParameter("parentParticipantId", parentParticipantId).getResultList();
		return children;
	}

}
