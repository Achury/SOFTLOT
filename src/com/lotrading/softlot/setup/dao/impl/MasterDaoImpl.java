package com.lotrading.softlot.setup.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.entity.Master;
import com.lotrading.softlot.setup.dao.IMasterDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public class MasterDaoImpl extends DAOTemplate implements IMasterDao {

	private Log log = LogFactory.getLog(MasterDaoImpl.class);

	public MasterDaoImpl() {

	}

	/**
	 * 
	 * @param master
	 * @throws Exception
	 */
	public boolean createMaster(Master master) throws Exception{
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_masters_CREATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, master.getName());
			cst.setString(2, master.getDescription());
			cst.setString(3, master.getFormat1());
			cst.setString(4, master.getFormat2());
			cst.setString(5, master.getFormat3());
			cst.setTimestamp(6, toTimeStamp(master.getEnteredDate()));
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
	 * @param master
	 * @throws Exception 
	 */
	public boolean updateMaster(Master master) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_masters_UPDATE(?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, master.getMasterId());
			cst.setString(2, master.getName());
			cst.setString(3, master.getDescription());
			cst.setString(4, master.getFormat1());
			cst.setString(5, master.getFormat2());
			cst.setString(6, master.getFormat3());
			cst.setTimestamp(7, toTimeStamp(master.getEnteredDate()));
			if(master.getDisabledDate() != null){
				cst.setTimestamp(8, toTimeStamp(master.getDisabledDate()));
			}else{
				cst.setDate(8, null);
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
	 * @param master
	 * @throws Exception 
	 */
	public List<Master> searchMaster(Master master) throws Exception {
		List<Master> masters = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_masters_SEARCH(?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, master.getName());
			rs = cst.executeQuery();
			if(rs.next()){
				masters = new ArrayList<Master>();
				do{
					Master _tmpMaster = new Master();
					_tmpMaster.setMasterId(rs.getInt(1));
					_tmpMaster.setName(rs.getString(2));
					_tmpMaster.setDescription(rs.getString(3));
					_tmpMaster.setFormat1(rs.getString(4));
					_tmpMaster.setFormat2(rs.getString(5));
					_tmpMaster.setFormat3(rs.getString(6));
					_tmpMaster.setEnteredDate(rs.getTimestamp(7));
					_tmpMaster.setDisabledDate(rs.getTimestamp(8));
					masters.add(_tmpMaster);
				}while(rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return masters;
	}

}