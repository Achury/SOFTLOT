package com.lotrading.softlot.util.lists;
import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.util.base.Constants;

public class DepartmentList extends MasterValueList {
	
	
	/**
	 * Esta lista contiene todos los masterValues 
	 * que sean del tipo department y no tengan disabledDate,
	 * es decir, no traera el masterValue llamado "Everyone".
	 */
	public DepartmentList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_DEPARTMENT);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
