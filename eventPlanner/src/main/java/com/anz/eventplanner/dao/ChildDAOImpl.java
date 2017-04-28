package com.anz.eventplanner.dao;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Child;

@Repository("childDAO")
public class ChildDAOImpl extends AbstractDAO<Integer, Child> implements ChildDAO {

}
