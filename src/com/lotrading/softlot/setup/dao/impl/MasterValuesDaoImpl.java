package com.lotrading.softlot.setup.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.dao.IMasterValuesDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class MasterValuesDaoImpl extends DAOTemplate implements IMasterValuesDao {
	private Log log = LogFactory.getLog(MasterValuesDaoImpl.class);

	public MasterValuesDaoImpl() {

	}

	/**
	 * 
	 * @param masterValue
	 */
	public boolean createMasterValue(MasterValue masterValue) throws Exception {
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_master_values_CREATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, masterValue.getMasterId());
			cst.setString(2, masterValue.getValue1());
			cst.setString(3, masterValue.getValue2());
			cst.setString(4, masterValue.getValue3());
			cst.setTimestamp(5, toTimeStamp(masterValue.getEnteredDate()));
			if (cst.executeUpdate() > 0) {
				created = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return created;
	}

	/**
	 * 
	 * @param masterValue
	 */
	public boolean updateMasterValue(MasterValue masterValue) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_master_values_UPDATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, masterValue.getValueId());
			cst.setInt(2, masterValue.getMasterId());
			cst.setString(3, masterValue.getValue1());
			cst.setString(4, masterValue.getValue2());
			cst.setString(5,masterValue.getValue3());
			cst.setTimestamp(6, toTimeStamp(masterValue.getEnteredDate()));
			if(masterValue.getDisabledDate() != null){
				cst.setTimestamp(7, toTimeStamp(masterValue.getDisabledDate()));
			}else{
				cst.setDate(7, null);
			}
			if (cst.executeUpdate() > 0) {
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
	 * @param masterValue
	 */
	public List<MasterValue> searchMasterValue(MasterValue masterValue) throws Exception {
		List<MasterValue> masterValues = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_master_values_SEARCH(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, masterValue.getMasterId());
			rs = cst.executeQuery();
			if(rs.next()){
				masterValues = new ArrayList<MasterValue>();
				do{
					MasterValue _tmpMasterValue = new MasterValue();
					_tmpMasterValue.setMasterId(rs.getInt(1));
					_tmpMasterValue.setValueId(rs.getInt(2));
					_tmpMasterValue.setValue1(rs.getString(3));
					_tmpMasterValue.setValue2(rs.getString(4));
					_tmpMasterValue.setValue3(rs.getString(5));
					_tmpMasterValue.setEnteredDate(rs.getTimestamp(6));
					_tmpMasterValue.setDisabledDate(rs.getTimestamp(7));
					masterValues.add(_tmpMasterValue);
				}while(rs.next());
			}
			System.out.println("search master value id " + masterValue.getMasterId());
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return masterValues;
	}

}