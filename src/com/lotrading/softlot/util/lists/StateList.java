package com.lotrading.softlot.util.lists;
import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class StateList extends MasterValueList {
	
	public StateList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_PROVINCE);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
