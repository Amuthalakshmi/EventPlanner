package com.anz.eventplanner.model;

import java.util.ArrayList;
import java.util.List;

public class ChildListWrapper {

	private List<Child> childList;

	public ChildListWrapper() {
		this.childList = new ArrayList<Child>();
	}

	public List<Child> getChildList() {
		return childList;
	}

	public void setChildList(List<Child> childList) {
		this.childList = childList;
	}

	public void addChild(Child child) {
		this.childList.add(child);
	}

	@Override
	public String toString() {
		return ("Children: " + childList);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof ChildListWrapper)) {
			return false;
		}
		ChildListWrapper other = (ChildListWrapper) object;
		if (this.childList.size() != other.childList.size()) {
			return false;
		}
		
		if (this.childList.size() == other.childList.size()) {
			for (int i = 0; i < this.childList.size(); i++) {
				if (this.childList.get(i) != other.childList.get(i)){
					return false;
				} else if (this.childList.get(i) == other.childList.get(i)){
					return true;
				}
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int childListWrapperId = 0;
		for (int i = 0; i < childList.size(); i++) {
			childListWrapperId = childList.get(i).getChildId();
		}
		result = prime * result + childListWrapperId;
		return result;
	}
}
