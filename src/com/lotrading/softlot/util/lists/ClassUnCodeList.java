package com.lotrading.softlot.util.lists;

import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class ClassUnCodeList extends MasterValueList{
	public ClassUnCodeList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_CLASS_UN_CODES);
		} catch (ListaException e) {
			e.printStackTrace();
		}
	}
}
