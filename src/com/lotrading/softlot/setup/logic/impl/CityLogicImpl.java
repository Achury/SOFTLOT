package com.lotrading.softlot.setup.logic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.ICityDao;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.logic.ICityLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:52 a.m.
 */
public class CityLogicImpl implements ICityLogic {

	private Log log = LogFactory.getLog(CityLogicImpl.class);
	private ICityDao cityDao;

	public CityLogicImpl(){

	}

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public boolean saveCity(City city) throws Exception{
		boolean _tmpReturn = false;
		try {
			if (city == null)
				return false;
			if (city.getCityId() <= 0) {
				// TODO: Crear el teléfono del empleado
				city.setEnteredDate(new Date());
				_tmpReturn = cityDao.createCity(city);
			} else if (city.getCityId() > 0) {
				// TODO: Verificar si se actualiza el teléfono del empleado
				_tmpReturn = cityDao.updateCity(city);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public List<City> searchCity(City city) throws Exception{
		List<City> cities = null;
		try {
			if(city != null){
				cities = new ArrayList<City>();
				cities = cityDao.searchCity(city);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return cities;
	}

	public ICityDao getCityDao(){
		return cityDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCityDao(ICityDao newVal){
		cityDao = newVal;
	}

	
	/**
	 * 
	 * @param city
	 * @throws Exception 
	 * @created 19-may-2011 4:21:55 p.m.  JU
	 */
	@Override
	public City loadCityById(City city) throws Exception {
		try {
			if(city != null){
				if(city.getCityId() != 0){
					city = cityDao.loadCityByid(city);
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return city;
	}

}