package com.lotrading.softlot.businessPartners.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.dao.ICallHistoryDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:50 a.m.
 */
public class CallHistoryDaoImpl extends DAOTemplate implements ICallHistoryDao {

	private Log log = LogFactory.getLog(CallHistoryDaoImpl.class);

	public CallHistoryDaoImpl() {

	}

	/**
	 * 
	 * @param callHistory
	 * @throws Exception
	 */
	public int createCallHistory(CallHistory callHistory) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_calls_history_CREATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if (callHistory.getContact() != null) {
				cst.setInt(1, callHistory.getContact().getContactId());
			} else {
				cst.setInt(1, 0);
			}
			if (callHistory.getPartner() == null || callHistory.getEmployeeCreator() == null) {
				return createdId;
			}
			cst.setInt(2, callHistory.getPartner().getPartnerId());
			cst.setInt(3, callHistory.getEmployeeCreator().getEmployeeId());
			cst.setString(4, callHistory.getNotes());
			cst.setInt(5, 0);
			cst.setInt(6, 0);
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
	 * @param callHistory
	 * @throws Exception
	 */
	public boolean updateCallHistory(CallHistory callHistory) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_calls_history_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, callHistory.getCallId());
			if (callHistory.getContact() != null) {
				cst.setInt(2, callHistory.getContact().getContactId());
			} else {
				cst.setInt(2, 0);
			}
			if (callHistory.getEmployeeCreator() == null) {
				return false;
			}
			cst.setInt(3, callHistory.getEmployeeCreator().getEmployeeId());
			cst.setString(4, callHistory.getNotes());
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
	 * @param callHistory
	 * @throws Exception
	 */
	public List<CallHistory> filterByNotes(CallHistory callHistory)throws Exception {
		List<CallHistory> callsHistory = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_calls_history_FILTER_by_NOTES(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if(callHistory.getNotes() == null){
				cst.setString(1, "");
			}else{
				cst.setString(1, callHistory.getNotes());
			}
			cst.setInt(2, callHistory.getPartner().getPartnerId());
			cst.setInt(3, 0);
			cst.setInt(4, 0);
			rs = cst.executeQuery();
			callsHistory = new ArrayList<CallHistory>();
			if(rs.next()){			
				do{
					CallHistory _tmpCallHist = new CallHistory();
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