package com.lotrading.softlot.LODepartment.clientOrder.logic.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.dao.IClientOrderInlandCSDao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;
import com.lotrading.softlot.LODepartment.clientOrder.logic.IClientOrderInlandCSLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderInlandCSLogicImpl implements IClientOrderInlandCSLogic {

	private IClientOrderInlandCSDao clientOrderInlandCSDao;
	private Log log = LogFactory.getLog(ClientOrderInlandCSLogicImpl.class);
	
	public ClientOrderInlandCSLogicImpl(){

	}

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public int saveClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception{
		int _tmpReturnedId = 0;
		if(clientOrderInlandCS != null){
			try {
				if(clientOrderInlandCS.getClientOrderInlandCSId() <= 0){
					_tmpReturnedId = clientOrderInlandCSDao.createClientOrderInlandCS(clientOrderInlandCS);
				}else{
					if(clientOrderInlandCSDao.updateClientOrderInlandCS(clientOrderInlandCS)){
						_tmpReturnedId = clientOrderInlandCS.getClientOrderInlandCSId();
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
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public boolean deleteClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception {
		boolean deleted = false;
		if(clientOrderInlandCS != null){
			try {
				if(clientOrderInlandCS.getClientOrderInlandCSId() > 0){
					deleted = clientOrderInlandCSDao.deleteClientOrderInlandCS(clientOrderInlandCS);
				}
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IClientOrderInlandCSDao getClientOrderInlandCSDao() {
		return clientOrderInlandCSDao;
	}

	public void setClientOrderInlandCSDao(IClientOrderInlandCSDao clientOrderInlandCSDao) {
		this.clientOrderInlandCSDao = clientOrderInlandCSDao;
	}

}