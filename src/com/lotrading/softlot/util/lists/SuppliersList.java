package com.lotrading.softlot.util.lists;

import java.util.List;

import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.logic.IPartnerLogic;

import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

public class SuppliersList extends ListaHandler{

	private IPartnerLogic partnerLogic;
	
	public SuppliersList() {
		super();
		try {
			setListId(this.getClass().getName());
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List getElements(String arg0) throws ListaException, Exception {
		// TODO Auto-generated method stub
		return elements;
	}

	@Override
	public boolean refreshList() throws ListaException {
		boolean _tmpReturn = false;
		try {
			Partner partner = new Partner();
			partner.setName("");
			partner.setClient(false);
			List<Partner> _tmpList = partnerLogic.searchLightClient(partner);
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(Partner _tmpPartner : _tmpList){
					elements.add(_tmpPartner);
				}
				_tmpReturn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _tmpReturn;
	}

	public IPartnerLogic getPartnerLogic() {
		return partnerLogic;
	}

	public void setPartnerLogic(IPartnerLogic partnerLogic) {
		this.partnerLogic = partnerLogic;
	}

}
