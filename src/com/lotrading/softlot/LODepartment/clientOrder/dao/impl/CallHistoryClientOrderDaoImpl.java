package com.lotrading.softlot.LODepartment.clientOrder.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.dao.ICallHistoryClientOrderDao;
import com.lotrading.softlot.LODepartment.clientOrder.entity.CallHistoryClientOrder;
import com.lotrading.softlot.businessPartners.dao.impl.CallHistoryDaoImpl;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 10-Nov-2011 5:00:00 PM
 */

public class CallHistoryClientOrderDaoImpl extends DAOTemplate implements ICallHistoryClientOrderDao{

	private Log log = LogFactory.getLog(CallHistoryDaoImpl.class);
	
	public CallHistoryClientOrderDaoImpl() {
		
	}

	/**
	 * 
	 * @param callHistoryClientOrder
	 * @throws Exception 
	 */
	public int createCallHistory(CallHistoryClientOrder callHistoryClientOrder) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_calls_history_CREATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if (callHistoryClientOrder.getContact() != null) {
				cst.setInt(1, callHistoryClientOrder.getContact().getContactId());
			} else {
				cst.setInt(1, 0);
			}
			if (callHistoryClientOrder.getEmployeeCreator() == null || callHistoryClientOrder.getPartner() == null) {
				return createdId;
			}
			cst.setInt(2, callHistoryClientOrder.getPartner().getPartnerId());
			cst.setInt(3, callHistoryClientOrder.getEmployeeCreator().getEmployeeId());
			cst.setString(4, callHistoryClientOrder.getNotes());
			cst.setInt(5, callHistoryClientOrder.getClientOrderId());
			cst.setInt(6, callHistoryClientOrder.getModuleId());
			cst.registerOutParameter(7, Types.INTEGER);
			
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(7);
			}			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return createdId;
	}

	/**
	 * 
	 * @param callHistoryClientOrder
	 * @throws Exception 
	 */
	public boolean updateCallHistory(CallHistoryClientOrder callHistoryClientOrder) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_calls_history_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, callHistoryClientOrder.getCallId());
			if (callHistoryClientOrder.getContact() != null) {
				cst.setInt(2, callHistoryClientOrder.getContact().getContactId());
			} else {
				cst.setInt(2, 0);
			}
			if (callHistoryClientOrder.getEmployeeCreator() == null) {
				return false;
			}
			cst.setInt(3, callHistoryClientOrder.getEmployeeCreator().getEmployeeId());
			cst.setString(4, callHistoryClientOrder.getNotes());
			if(cst.executeUpdate() > 0){
				updated = true;
			}	
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

	/**
	 * 
	 * @param callHistoryClientOrder
	 * @throws Exception 
	 */
	public List<CallHistoryClientOrder> filterByNotes(CallHistoryClientOrder callHistoryClientOrder) throws Exception {
		List<CallHistoryClientOrder> callsHistory = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_calls_history_FILTER_by_NOTES(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if(callHistoryClientOrder.getNotes() == null){
				cst.setString(1, "");
			}else{
				cst.setString(1, callHistoryClientOrder.getNotes());
			}
			cst.setInt(2, callHistoryClientOrder.getPartner().getPartnerId());
			cst.setInt(3, callHistoryClientOrder.getClientOrderId());
			cst.setInt(4, callHistoryClientOrder.getModuleId());
			rs = cst.executeQuery();
			if(rs.next()){
				callsHistory = new ArrayList<CallHistoryClientOrder>();
				do{
					CallHistoryClientOrder _tmpCallHist = new CallHistoryClientOrder();
					_tmpCallHist.setCallId(rs.getInt(1));				
					
					_tmpCallHist.getContact().setContactId(rs.getInt(2));
					_tmpCallHist.getContact().setName(rs.getString(3));	
					
					_tmpCallHist.getPartner().setPartnerId(rs.getInt(4));
					_tmpCallHist.getPartner().setName(rs.getString(5));					
					
					_tmpCallHist.setEnteredDate(rs.getTimestamp(6));
					
					_tmpCallHist.getEmployeeCreator().setEmployeeId(rs.getInt(7));
					_tmpCallHist.getEmployeeCreator().setLogin(rs.getString(8));
					
					_tmpCallHist.setNotes(rs.getString(9));
					callsHistory.add(_tmpCallHist);
				}while(rs.next());
				rs.close();
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return callsHistory;
	}

}
