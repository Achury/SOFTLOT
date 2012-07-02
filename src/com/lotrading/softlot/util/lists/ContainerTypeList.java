package com.lotrading.softlot.util.lists;

import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class ContainerTypeList extends MasterValueList {

	public ContainerTypeList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_CONTAINER_TYPE);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
