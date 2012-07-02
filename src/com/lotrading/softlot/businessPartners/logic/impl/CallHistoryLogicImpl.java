package com.lotrading.softlot.businessPartners.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.ICallHistoryDao;
import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.logic.ICallHistoryLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:50 a.m.
 */
public class CallHistoryLogicImpl implements ICallHistoryLogic {

	private Log log = LogFactory.getLog(CallHistoryLogicImpl.class);
	private ICallHistoryDao callHistoryDao;

	public CallHistoryLogicImpl() {

	}

	/**
	 * 
	 * @param callHistory
	 * @throws Exception
	 */
	public int saveCallHistory(CallHistory callHistory) throws Exception {
		int _tmpReturnId = 0;
		try {
			if (callHistory == null) {
				return _tmpReturnId;
			}
			if (callHistory.getPartner().getPartnerId()<=0) {
				return _tmpReturnId;
			}
			if (callHistory.getCallId() <= 0) {
				//callHistory.setEnteredDate(new Date());
				_tmpReturnId = callHistoryDao.createCallHistory(callHistory);
			} else if (callHistory.getCallId() > 0) {
				if(callHistoryDao.updateCallHistory(callHistory)){
					_tmpReturnId = callHistory.getCallId();
				}				
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturnId;
	}

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public List<CallHistory> filterByNotes(CallHistory callHistory) throws Exception {
		List<CallHistory> callsHistory = null;
		try {
			if (callHistory != null) {
				callsHistory = new ArrayList<CallHistory>();
				callsHistory = callHistoryDao.filterByNotes(callHistory);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return callsHistory;
	}

	public ICallHistoryDao getCallHistoryDao() {
		return callHistoryDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCallHistoryDao(ICallHistoryDao newVal) {
		callHistoryDao = newVal;
	}

}