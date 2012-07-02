package com.lotrading.softlot.util.lists;

import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

import com.lotrading.softlot.setup.entity.Master;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;
import com.lotrading.softlot.util.base.Value3Comparator;

public class MasterValueList extends ListaHandler {
	
	private IMasterValueLogic masterValueLogic;
	protected Master master;
	
	public MasterValueList() {
		super();
		master = new Master();
	}

	@Override
	public boolean refreshList() {
		boolean _tmpReturn = false;
		try {
			MasterValue masterValue = new MasterValue();
			masterValue.setMasterId(master.getMasterId());
			List<MasterValue> _tmpList = masterValueLogic.searchMasterValue(masterValue);
			if(_tmpList != null && !_tmpList.isEmpty()){
				Collections.sort(_tmpList, new Value3Comparator());
				for(MasterValue _tmpMaster: _tmpList){
					if(_tmpMaster.getDisabledDate() == null){
						SelectItem element = new SelectItem(new Integer(_tmpMaster.getValueId()),_tmpMaster.getValue1());
						//ElementoBase element = new ElementoBase(_tmpMaster.getValueId(), null, _tmpMaster.getValue1());
						elements.add(element);
					}
				}
				_tmpReturn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _tmpReturn;
	}
	

	@Override
	public List getElements(String arg0) throws ListaException, Exception {
		return elements;
	}

	public IMasterValueLogic getMasterValueLogic() {
		return masterValueLogic;
	}

	public void setMasterValueLogic(IMasterValueLogic masterLogic) {
		this.masterValueLogic = masterLogic;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}
}
