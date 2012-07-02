package com.lotrading.softlot.businessPartners.dao;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.CallHistory;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICallHistoryDao {

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public int createCallHistory(CallHistory callHistory) throws Exception;

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public boolean updateCallHistory(CallHistory callHistory) throws Exception;

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public List<CallHistory> filterByNotes(CallHistory callHistory) throws Exception;

}