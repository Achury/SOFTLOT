package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbUnCodeLogic {

	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public List<AwbUnCode> saveUnCode(List<AwbUnCode> awbUnCode) throws Exception;

	/**
	 * 
	 * @param awbUnCode
	 */
	public boolean deleteUnCode(AwbUnCode awbUnCode)  throws Exception;

}