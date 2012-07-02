package com.lotrading.softlot.util.lists;

import java.util.Comparator;

import javax.faces.model.SelectItem;

public class SelectItemLabelComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		SelectItem s1 = (SelectItem)arg0;
		SelectItem s2 = (SelectItem)arg1;
		return s1.getLabel().compareTo(s1.getLabel());
	}
	
	public boolean equals(Object o){
		return this == o;
	}

}
