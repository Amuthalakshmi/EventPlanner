package com.anz.eventplanner.dao;

import org.springframework.stereotype.Repository;

import com.anz.eventplanner.model.Task;

@Repository("taskDAO")
public class TaskDAOImpl extends AbstractDAO<Integer, Task> implements TaskDAO{

}
