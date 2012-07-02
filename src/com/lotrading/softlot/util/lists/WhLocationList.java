package com.lotrading.softlot.util.lists;

import java.util.List;
import com.lotrading.softlot.warehouse.entity.WhLocation;
import com.lotrading.softlot.warehouse.logic.IWhLocationLogic;
import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

public class WhLocationList extends ListaHandler{
	
	private IWhLocationLogic whLocationLogic;

	public WhLocationList(){
		super();
		try {
			setListId(this.getClass().getName());
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean refreshList() throws ListaException {
		boolean _tmpReturn = false;
		try {
			List<WhLocation> _tmpList = whLocationLogic.searchWhLocation(new WhLocation());
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(WhLocation _tmpWhLocation : _tmpList){					
						elements.add(_tmpWhLocation);					
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
		// TODO Auto-generated method stub
		return elements;
	}

	public IWhLocationLogic getWhLocationLogic() {
		return whLocationLogic;
	}

	public void setWhLocationLogic(IWhLocationLogic whLocationLogic) {
		this.whLocationLogic = whLocationLogic;
	}
}

