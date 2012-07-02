package com.lotrading.softlot.util.lists;

import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class ShipmentStatusList extends MasterValueList{

	public ShipmentStatusList(){
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_SHIPMENT_TYPE);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
