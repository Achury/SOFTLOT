package com.lotrading.softlot.LODepartment.clientOrder.dao;
import java.util.List;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IClientOrderDao {

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public List<ClientOrder> searchClientOrder(ClientOrder clientOrder) throws Exception;

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder createClientOrder(ClientOrder clientOrder) throws Exception;

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public boolean updateClientOrder(ClientOrder clientOrder) throws Exception;
	
	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder loadClientOrder(ClientOrder clientOrder) throws Exception;

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public List<ClientOrderInlandCS> loadClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public List<ClientOrderSupplierInfo> loadClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;
	
}