package com.lotrading.softlot.util.lists;

import com.lotrading.softlot.util.base.Constants;

import co.com.landsoft.devbase.util.listas.ListaException;

public class CourierList extends MasterValueList{
	
	public CourierList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_COURIER);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}