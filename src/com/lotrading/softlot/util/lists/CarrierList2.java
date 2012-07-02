package com.lotrading.softlot.util.lists;

import java.util.List;

import javax.faces.model.SelectItem;

import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.logic.IcarrierLogic;

public class CarrierList2 extends ListaHandler {

	private IcarrierLogic carrierLogic;
	
	public CarrierList2() {
		super();
		try {
			setListId(this.getClass().getName());
		} catch (ListaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean refreshList() throws ListaException {
		boolean _tmpReturn = false;
		try {
			Carrier carrier = new Carrier();
			List<Carrier> _tmpList = carrierLogic.searchCarrier(carrier);
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(Carrier _tmpCarrier : _tmpList){
					if(_tmpCarrier.getDisabledDate() == null){
						SelectItem element = new SelectItem(new Integer(_tmpCarrier.getCarrierId()),_tmpCarrier.getName() );
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

	public List getElements(String arg0) throws ListaException, Exception {
		return elements;
	}

	public IcarrierLogic getCarrierLogic() {
		return carrierLogic;
	}

	public void setCarrierLogic(IcarrierLogic carrierLogic) {
		this.carrierLogic = carrierLogic;
	}	
}
