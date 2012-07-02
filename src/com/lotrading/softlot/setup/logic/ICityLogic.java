package com.lotrading.softlot.setup.logic;
import com.lotrading.softlot.setup.entity.City;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICityLogic {

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public boolean saveCity(City city) throws Exception;

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public java.util.List searchCity(City city) throws Exception;
	
	/**
	 * 
	 * @param city
	 * @throws Exception 
	 * @created 19-may-2011 4:21:55 p.m. JU
	 */
	public City loadCityById(City city) throws Exception;

}