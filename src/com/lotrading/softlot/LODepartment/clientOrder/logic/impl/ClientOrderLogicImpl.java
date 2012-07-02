package com.lotrading.softlot.LODepartment.clientOrder.logic.impl;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.dao.IClientOrderDao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;
import com.lotrading.softlot.LODepartment.clientOrder.logic.IClientOrderLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderLogicImpl implements IClientOrderLogic {

	private IClientOrderDao clientOrderDao;
	private Log log = LogFactory.getLog(ClientOrderLogicImpl.class);
	
	public ClientOrderLogicImpl(){

	}

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder saveClientOrder(ClientOrder clientOrder) throws Exception{
		if(clientOrder != null){
			try {
				if(clientOrder.getClientOrderId() <= 0){
					clientOrder.setCreatedDate(new Date());				
					clientOrder = clientOrderDao.createClientOrder(clientOrder);			
				}else{
					clientOrderDao.updateClientOrder(clientOrder);
				}
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
		}
		return clientOrder;
	}

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public List<ClientOrder> searchClientOrders(ClientOrder clientOrder) throws Exception{
		List <ClientOrder> clientOrders = null;
		if(clientOrder != null){
			try {
				clientOrders = clientOrderDao.searchClientOrder(clientOrder);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return clientOrders;
	}
	
	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder loadClientOrder(ClientOrder clientOrder) throws Exception {
		ClientOrder _tmpClientOrder = null;
		if(clientOrder != null){
			try {
				_tmpClientOrder = clientOrderDao.loadClientOrder(clientOrder);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpClientOrder;
	}

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception
	 */
	public List<ClientOrderSupplierInfo> loadClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception{
		List<ClientOrderSupplierInfo> _tmpClientOrderSuppInfo = null; 
		if(clientOrderSupplierInfo != null){
			try {
				_tmpClientOrderSuppInfo = clientOrderDao.loadClientOrderSupplierInfo(clientOrderSupplierInfo);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpClientOrderSuppInfo;
	}

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception
	 */
	public List<ClientOrderInlandCS> loadClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception{
		List<ClientOrderInlandCS> _tmpClientOrderInlands = null;
		if(clientOrderInlandCS != null){
			try {
				_tmpClientOrderInlands = clientOrderDao.loadClientOrderInlandCS(clientOrderInlandCS);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpClientOrderInlands;
	}

	public IClientOrderDao getClientOrderDao() {
		return clientOrderDao;
	}

	public void setClientOrderDao(IClientOrderDao clientOrderDao) {
		this.clientOrderDao = clientOrderDao;
	}

}