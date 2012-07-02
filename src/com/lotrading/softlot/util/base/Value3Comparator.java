package com.lotrading.softlot.util.base;

import java.util.Comparator;

import com.lotrading.softlot.setup.entity.MasterValue;

public class Value3Comparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		MasterValue mv1 = (MasterValue) o1;
		MasterValue mv2 = (MasterValue) o2;
		if(mv1.getValue3() == null || mv2.getValue3() == null){
			return 0;
		}else{
			int ret  = mv1.getValue3().compareTo(mv2.getValue3());
			return ret;
		}
	}

}
