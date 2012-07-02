package com.lotrading.softlot.util.lists;

import java.util.List;

import javax.faces.model.SelectItem;

import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;
import com.lotrading.softlot.util.base.Constants;

public class SaidToContainList extends ListaHandler{

	private IMasterValueLogic masterValueLogic;
	
	public SaidToContainList(){
		super();
		try {
			setListId(this.getClass().getName());
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean refreshList() {
		boolean _tmpReturn = false;
		try {
			MasterValue masterValue = new MasterValue();
			masterValue.setMasterId(Constants.MASTER_SAID_TO_CONTAIN);
			List<MasterValue> _tmpList = masterValueLogic.searchMasterValue(masterValue);
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(MasterValue _tmpMaster: _tmpList){
					SelectItem element = new SelectItem(new Integer(_tmpMaster.getValueId()),_tmpMaster.getValue3());
					elements.add(element);
				}
				_tmpReturn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _tmpReturn;
	}
	
	@Override
	public List<SelectItem> getElements(String arg0) throws ListaException, Exception {
		return elements;
	}

	public IMasterValueLogic getMasterValueLogic() {
		return masterValueLogic;
	}

	public void setMasterValueLogic(IMasterValueLogic masterValueLogic) {
		this.masterValueLogic = masterValueLogic;
	}
	
	
	
}
