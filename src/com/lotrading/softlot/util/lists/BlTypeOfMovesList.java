package com.lotrading.softlot.util.lists;
import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class BlTypeOfMovesList extends MasterValueList {
	
	public BlTypeOfMovesList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_BL_TYPE_OF_MOVES);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
