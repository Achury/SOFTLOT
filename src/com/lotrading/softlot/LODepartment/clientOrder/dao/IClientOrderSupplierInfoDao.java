package com.lotrading.softlot.LODepartment.clientOrder.dao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IClientOrderSupplierInfoDao {

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public int createClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 */
	public boolean updateClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 */
	public boolean deleteClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;

}