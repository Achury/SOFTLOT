package com.lotrading.softlot.util.lists;

import java.util.List;

import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.logic.ICityLogic;

import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

public class CityList extends ListaHandler{
	
	private ICityLogic cityLogic;

	public CityList(){
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
			City city = new City();
			List<City> _tmpList = cityLogic.searchCity(city);
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(City _tmpCity : _tmpList){
					if(_tmpCity.getDisabledDate() == null){
						elements.add(_tmpCity);
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
		// TODO Auto-generated method stub
		return elements;
	}

	public ICityLogic getCityLogic() {
		return cityLogic;
	}

	public void setCityLogic(ICityLogic cityLogic) {
		this.cityLogic = cityLogic;
	}
}

