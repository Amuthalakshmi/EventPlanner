package com.anz.eventplanner.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
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
		Query query = getSession().createSQLQuery("delete from Child where child_id=:childId");
		query.setInteger("childId", childId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Child> findAllChild() {
		Criteria criteria = createEntityCriteria();
		return (List<Child>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Child> findByParentParticipantId(int parentParticipantId){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("parentParticipantId", parentParticipantId));		
		return (List<Child>) criteria.list();
	}

}
