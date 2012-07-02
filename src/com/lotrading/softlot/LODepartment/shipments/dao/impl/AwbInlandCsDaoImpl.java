package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbInlandCsDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbInlandCsDaoImpl extends DAOTemplate implements IAwbInlandCsDao {

	private Log log = LogFactory.getLog(AwbUnCodeDaoImpl.class);
	
	public AwbInlandCsDaoImpl(){

	}

	/**
	 * 
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public int createAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_inland_cs_CREATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, awbInlandCs.getAwbId());
			cst.setDouble(3, awbInlandCs.getCost());
			cst.setDouble(4, awbInlandCs.getSale());
			cst.setInt(5, awbInlandCs.getTruckCompany().getValueId());
			cst.setString(6, awbInlandCs.getTrackingNumber());
			cst.setString(7, awbInlandCs.getPoNumber());
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
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public boolean updateAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_inland_cs_UPDATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbInlandCs.getAwbId());
			cst.setDouble(2, awbInlandCs.getCost());
			cst.setDouble(3, awbInlandCs.getSale());
			cst.setInt(4, awbInlandCs.getTruckCompany().getValueId());
			cst.setString(5, awbInlandCs.getTrackingNumber());
			cst.setString(6, awbInlandCs.getPoNumber());
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
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public boolean deleteAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_inland_cs_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbInlandCs.getInlandCsId());
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
	
	public List<AwbInlandCS> saveAwbInlandCSItems(List<AwbInlandCS> awbInlandCSItems) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbInlandCS _tmpInland : awbInlandCSItems){
				if(_tmpInland.getInlandCsId() <= 0 && !_tmpInland.isEmpty()){
					String sql = "{call sp_awb_inland_cs_CREATE(?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpInland.getAwbId());
					cst.setDouble(3, _tmpInland.getCost());
					cst.setDouble(4, _tmpInland.getSale());
					cst.setInt(5, _tmpInland.getTruckCompany().getValueId());
					cst.setString(6, _tmpInland.getTrackingNumber());
					cst.setString(7, _tmpInland.getPoNumber());
					if(cst.executeUpdate() > 0){
						_tmpInland.setInlandCsId(cst.getInt(1));
					}
				}else if(_tmpInland.getInlandCsId() > 0){
					String sql = "{call sp_awb_inland_cs_UPDATE(?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpInland.getAwbId());
					cst.setDouble(2, _tmpInland.getCost());
					cst.setDouble(3, _tmpInland.getSale());
					cst.setInt(4, _tmpInland.getTruckCompany().getValueId());
					cst.setString(5, _tmpInland.getTrackingNumber());
					cst.setString(6, _tmpInland.getPoNumber());
					
					cst.executeUpdate();
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbInlandCSItems;
	}

}