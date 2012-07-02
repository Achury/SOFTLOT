package com.lotrading.softlot.LODepartment.clientOrder.logic;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IClientOrderSupplierInfoLogic {

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 */
	public int saveClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;
	
	/**
	 * 
	 * @param clientOrderSupplierInfo
	 */
	public boolean deleteClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;

}