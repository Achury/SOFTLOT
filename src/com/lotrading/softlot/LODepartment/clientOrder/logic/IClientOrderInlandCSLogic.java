package com.lotrading.softlot.LODepartment.clientOrder.logic;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IClientOrderInlandCSLogic {

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public int saveClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;
	
	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public boolean deleteClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;

}