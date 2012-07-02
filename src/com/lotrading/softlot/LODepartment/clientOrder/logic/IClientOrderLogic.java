package com.lotrading.softlot.LODepartment.clientOrder.logic;
import java.util.List;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IClientOrderLogic {

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder saveClientOrder(ClientOrder clientOrder) throws Exception;

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public List<ClientOrder> searchClientOrders(ClientOrder clientOrder) throws Exception;

	/**
	 * 
	 * @param clientOrder
	 */
	public ClientOrder loadClientOrder(ClientOrder clientOrder) throws Exception;
	
	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public List<ClientOrderSupplierInfo> loadClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception;

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public List<ClientOrderInlandCS> loadClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception;
	
}