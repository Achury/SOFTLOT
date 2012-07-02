package com.lotrading.softlot.util.lists;

import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class RateClassList extends MasterValueList{

	public RateClassList(){
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_RATE_CLASS);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
