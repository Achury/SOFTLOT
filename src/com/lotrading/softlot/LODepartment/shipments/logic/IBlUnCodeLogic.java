package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;


import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlUnCodeLogic {

	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public List<BlUnCode> saveUnCode(List<BlUnCode> blUnCode) throws Exception;

	/**
	 * 
	 * @param blUnCode
	 */
	public boolean deleteUnCode(BlUnCode blUnCode) throws Exception;

}