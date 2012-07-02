package com.lotrading.softlot.LODepartment.clientOrder.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.clientOrder.entity.CallHistoryClientOrder;

public interface ICallhistoryClientOrderLogic {
	
	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public int saveCallHistory(CallHistoryClientOrder callHistory) throws Exception;

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public List<CallHistoryClientOrder> filterByNotes(CallHistoryClientOrder callHistory) throws Exception;
}
