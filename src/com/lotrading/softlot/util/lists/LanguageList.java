package com.lotrading.softlot.util.lists;

import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.landsoft.devbase.util.listas.ListaException;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.Value3Comparator;

public class LanguageList extends MasterValueList {

	public LanguageList() {
		super();
		try {
			setListId(this.getClass().getName());
			master.setMasterId(Constants.MASTER_LANGUAGE);
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public boolean refreshList() { boolean _tmpReturn = false; try {
	 * MasterValue masterValue = new MasterValue();
	 * masterValue.setMasterId(master.getMasterId()); List<MasterValue> _tmpList
	 * = super.getMasterLogic().searchMasterValue(masterValue); if(_tmpList !=
	 * null && !_tmpList.isEmpty()){ Collections.sort(_tmpList, new
	 * Value3Comparator()); for(MasterValue _tmpMaster: _tmpList){
	 * if(_tmpMaster.getDisabledDate() == null){ SelectItem element = new
	 * SelectItem(new Integer(_tmpMaster.getValueId()),_tmpMaster.getValue1());
	 * elements.add(element); } } _tmpReturn = true; } } catch (Exception e) {
	 * e.printStackTrace(); } return _tmpReturn; }
	 */
}
