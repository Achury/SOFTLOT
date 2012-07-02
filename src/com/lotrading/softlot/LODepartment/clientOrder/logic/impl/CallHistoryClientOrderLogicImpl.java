package com.lotrading.softlot.LODepartment.clientOrder.logic.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.dao.ICallHistoryClientOrderDao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.CallHistoryClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.logic.ICallhistoryClientOrderLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 10-Nov-2011 5:00:00 PM
 */

public class CallHistoryClientOrderLogicImpl implements ICallhistoryClientOrderLogic {
	
	private ICallHistoryClientOrderDao callHistoryDao;
	private Log log = LogFactory.getLog(CallHistoryClientOrderLogicImpl.class);
	
	public int saveCallHistory(CallHistoryClientOrder callHistory)throws Exception {
		int _tmpCallId = 0;
		if(callHistory != null){
			try {
				if(callHistory.getCallId() <= 0){
					callHistory.setEnteredDate(new Date());
					_tmpCallId = callHistoryDao.createCallHistory(callHistory);
				}else{
					if(callHistoryDao.updateCallHistory(callHistory)){
						_tmpCallId = callHistory.getCallId();
					}
				}
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
		}
		return _tmpCallId;
	}

	
	public List<CallHistoryClientOrder> filterByNotes(CallHistoryClientOrder callHistory) throws Exception {
		List<CallHistoryClientOrder> callsHistory = null;
		if(callHistory != null){
			try {
				callsHistory = callHistoryDao.filterByNotes(callHistory);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
		}
		return callsHistory;
	}


	public ICallHistoryClientOrderDao getCallHistoryDao() {
		return callHistoryDao;
	}


	public void setCallHistoryDao(ICallHistoryClientOrderDao callHistoryDao) {
		this.callHistoryDao = callHistoryDao;
	}

	
}
