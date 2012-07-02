package com.lotrading.softlot.util.lists;

import java.util.List;

import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.IPortLogic;

import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 07:36:51 a.m.
 */

public class PortsList extends ListaHandler{

	private IPortLogic portLogic;
	
	public PortsList(){
		super();
		try {
			setListId(this.getClass().getName());
		} catch (ListaException e) {
			e.printStackTrace();
		}
	}
	
	public boolean refreshList() throws ListaException {
		boolean _tmpReturn = false;
		try {
			List<Port> _tmpList = portLogic.searchPort(new Port());
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(Port _tmpPort : _tmpList){
					if(_tmpPort.getDisabledDate() == null){
						elements.add(_tmpPort);
					}
				}
				_tmpReturn = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return _tmpReturn;
	}
	
	public List getElements(String arg0) throws ListaException, Exception {
		return elements;
	}

	public IPortLogic getPortLogic() {
		return portLogic;
	}

	public void setPortLogic(IPortLogic portLogic) {
		this.portLogic = portLogic;
	}
	
}
