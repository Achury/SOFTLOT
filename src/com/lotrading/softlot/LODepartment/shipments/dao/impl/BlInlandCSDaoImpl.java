package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlInlandCSDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlInlandCSDaoImpl extends DAOTemplate implements IBlInlandCSDao {

	private Log log = LogFactory.getLog(BlInlandCSDaoImpl.class);
	
	public BlInlandCSDaoImpl(){

	}

	/**
	 * 
	 * @param blInlandCS
	 * @throws Exception 
	 */
	public int createBlInlandCS(BlInlandCS blInlandCS) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_inland_cs_CREATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blInlandCS.getBlId());
			cst.setDouble(3, blInlandCS.getCost());
			cst.setDouble(4, blInlandCS.getSale());
			cst.setInt(5, blInlandCS.getTruckCompany().getValueId());
			cst.setString(6, blInlandCS.getTrackingNumber());
			cst.setString(7, blInlandCS.getPoNumber());
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(1);
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
	 * @param blInlandCS
	 * @throws Exception 
	 */
	public boolean updateBlInlandCS(BlInlandCS blInlandCS) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_inland_cs_UPDATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blInlandCS.getInlandCSId());
			cst.setDouble(2, blInlandCS.getCost());
			cst.setDouble(3, blInlandCS.getSale());
			cst.setInt(4, blInlandCS.getTruckCompany().getValueId());
			cst.setString(5, blInlandCS.getTrackingNumber());
			cst.setString(6, blInlandCS.getPoNumber());
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
	
	public List<BlInlandCS> saveBlInlandCSItems(List<BlInlandCS> blInlandCSItems) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlInlandCS _tmpItem : blInlandCSItems){
				if(_tmpItem.getInlandCSId() <= 0 && !_tmpItem.isEmpty()){
					String sql = "{call sp_bl_inland_cs_CREATE(?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpItem.getBlId());
					cst.setDouble(3, _tmpItem.getCost());
					cst.setDouble(4, _tmpItem.getSale());
					cst.setInt(5, _tmpItem.getTruckCompany().getValueId());
					cst.setString(6, _tmpItem.getTrackingNumber());
					cst.setString(7, _tmpItem.getPoNumber());						
					
					if(cst.executeUpdate() > 0){
						_tmpItem.setInlandCSId(cst.getInt(1));
					}			
				}else if(_tmpItem.getInlandCSId() > 0){
					String sql = "{call sp_bl_inland_cs_UPDATE(?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpItem.getInlandCSId());
					cst.setDouble(2, _tmpItem.getCost());
					cst.setDouble(3, _tmpItem.getSale());
					cst.setInt(4, _tmpItem.getTruckCompany().getValueId());
					cst.setString(5, _tmpItem.getTrackingNumber());
					cst.setString(6, _tmpItem.getPoNumber());
					
					cst.executeUpdate();
				}
			}		
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blInlandCSItems;
	}
	
	

	/**
	 * 
	 * @param blInlandCS
	 * @throws Exception 
	 */
	public boolean deleteBlInlandCS(BlInlandCS blInlandCS) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_inland_cs_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blInlandCS.getInlandCSId());
			if(cst.executeUpdate() > 0){
				deleted = true;
			}	
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return deleted;
	}

}