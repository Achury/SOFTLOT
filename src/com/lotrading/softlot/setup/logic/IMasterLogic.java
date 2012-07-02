package com.lotrading.softlot.setup.logic;
import com.lotrading.softlot.setup.entity.Master;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IMasterLogic {

	/**
	 * 
	 * @param master
	 * @throws Exception 
	 */
	public boolean saveMaster(Master master) throws Exception;

	/**
	 * 
	 * @param master
	 * @throws Exception 
	 */
	public java.util.List searchMaster(Master master) throws Exception;

}