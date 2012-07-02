package com.lotrading.softlot.util.lists;

import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class PackingGroupUnCodeList extends MasterValueList{
	public PackingGroupUnCodeList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_PACKING_GROUP_UN_CODES);
		} catch (ListaException e) {
			e.printStackTrace();
		}
	}
}
