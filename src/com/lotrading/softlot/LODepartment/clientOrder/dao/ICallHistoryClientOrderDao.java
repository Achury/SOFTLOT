package com.lotrading.softlot.LODepartment.clientOrder.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.clientOrder.entity.CallHistoryClientOrder;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 09-Nov-2011 11:59:30 AM
 */
public interface ICallHistoryClientOrderDao {

	/**
	 * 
	 * @param callHistoryClientOrder
	 * @throws Exception 
	 */
	public int createCallHistory(CallHistoryClientOrder callHistoryClientOrder) throws Exception;

	/**
	 * 
	 * @param callHistoryClientOrder
	 * @throws Exception 
	 */
	public boolean updateCallHistory(CallHistoryClientOrder callHistoryClientOrder) throws Exception;

	/**
	 * 
	 * @param callHistoryClientOrder
	 * @throws Exception 
	 */
	public List<CallHistoryClientOrder> filterByNotes(CallHistoryClientOrder callHistoryClientOrder) throws Exception;
}
