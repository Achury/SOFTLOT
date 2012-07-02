package com.lotrading.softlot.util.lists;
import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class TruckCompanyList extends MasterValueList {
	
	public TruckCompanyList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_TRUCK_COMPANY);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
