package com.lotrading.softlot.setup.dao;
import com.lotrading.softlot.setup.entity.City;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICityDao {

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public boolean createCity(City city) throws Exception;

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public boolean updateCity(City city) throws Exception;

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
	 * @created 19-may-2011 4:21:55 p.m.
	 */
	public City loadCityByid(City city) throws Exception;

}