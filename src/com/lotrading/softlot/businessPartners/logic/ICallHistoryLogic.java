package com.lotrading.softlot.businessPartners.logic;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.CallHistory;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICallHistoryLogic {

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public int saveCallHistory(CallHistory callHistory) throws Exception;

	/**
	 * 
	 * @param callHistory
	 * @throws Exception 
	 */
	public List filterByNotes(CallHistory callHistory) throws Exception;

}