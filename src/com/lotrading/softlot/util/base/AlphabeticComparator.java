package com.lotrading.softlot.util.base;

import java.util.Comparator;
import javax.faces.model.SelectItem;

public class AlphabeticComparator implements Comparator<SelectItem> {
	
	public int compare(SelectItem s1, SelectItem s2) {
		if(s1 == null || s2 == null){
			return 0;
		}else{
			int ret = s1.getLabel().toLowerCase().compareTo(s2.getLabel().toLowerCase());
			return ret;
		}
	}
}
