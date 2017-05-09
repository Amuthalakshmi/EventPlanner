package com.anz.eventplanner.model;

import java.util.ArrayList;
import java.util.List;

public class ChildListWrapper {
	
	private List<Child> childList;

	public ChildListWrapper(){
		this.childList = new ArrayList<Child>();
	}

	public List<Child> getChildList() {
		return childList;
	}

	public void setChildList(List<Child> childList) {
		this.childList = childList;
	}
	
	public void addChild(Child child){
		this.childList.add(child);
	}
}
