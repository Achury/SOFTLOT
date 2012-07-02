package com.lotrading.softlot.LODepartment.clientOrder.dao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IClientOrderInlandCSDao {

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public int createClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;

	/**
	 * 
	 * @param clientOrderInlandCS
	 */
	public boolean updateClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;

	/**
	 * 
	 * @param clientOrderInlandCS
	 */
	public boolean deleteClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;

}