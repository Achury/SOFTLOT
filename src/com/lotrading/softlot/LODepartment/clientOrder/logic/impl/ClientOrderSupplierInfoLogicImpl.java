package com.lotrading.softlot.LODepartment.clientOrder.logic.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.dao.IClientOrderSupplierInfoDao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;
import com.lotrading.softlot.LODepartment.clientOrder.logic.IClientOrderSupplierInfoLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderSupplierInfoLogicImpl implements IClientOrderSupplierInfoLogic {

	private IClientOrderSupplierInfoDao clientOrderSuppInfoDao;
	private Log log = LogFactory.getLog(ClientOrderSupplierInfoLogicImpl.class);
	
	public ClientOrderSupplierInfoLogicImpl(){

	}
	
	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception
	 */
	public int saveClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception{
		int _tmpReturnedId = 0;
		if(clientOrderSupplierInfo != null){
			try {
				if(clientOrderSupplierInfo.getClientOrderSupplierInfoId() <= 0){
					_tmpReturnedId = clientOrderSuppInfoDao.createClientOrderSupplierInfo(clientOrderSupplierInfo);
				}else{
					if(clientOrderSuppInfoDao.updateClientOrderSupplierInfo(clientOrderSupplierInfo)){
						_tmpReturnedId = clientOrderSupplierInfo.getClientOrderSupplierInfoId();
					}
				}
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
		}
		return _tmpReturnedId;
	}

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception
	 */
	public boolean deleteClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception {
		boolean deleted = false;
		if(clientOrderSupplierInfo != null){
			try {
				if(clientOrderSupplierInfo.getClientOrderSupplierInfoId() > 0){
					deleted = clientOrderSuppInfoDao.deleteClientOrderSupplierInfo(clientOrderSupplierInfo);
				}
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public void setClientOrderSuppInfoDao(
			IClientOrderSupplierInfoDao clientOrderSuppInfoDao) {
		this.clientOrderSuppInfoDao = clientOrderSuppInfoDao;
	}
	
	public IClientOrderSupplierInfoDao getClientOrderSuppInfoDao() {
		return clientOrderSuppInfoDao;
	}
	
}