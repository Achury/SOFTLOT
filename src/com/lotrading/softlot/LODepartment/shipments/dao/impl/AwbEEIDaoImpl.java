package com.lotrading.softlot.LODepartment.shipments.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbEEIDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbEEI;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbEEIDaoImpl extends DAOTemplate implements IAwbEEIDao {

	private Log log = LogFactory.getLog(AwbEEIDaoImpl.class);
	
	public List<AwbEEI> saveAwbEEI(List<AwbEEI> awbEEIList) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbEEI _tmpEEI : awbEEIList){
				if(_tmpEEI.getEeiId() <= 0 && !_tmpEEI.isEmpty()){
					String sql = "{call sp_awb_eei_CREATE(?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpEEI.getAwbId());
					cst.setString(3, _tmpEEI.getEei());
					cst.setString(4, _tmpEEI.getSupplier());
					if(cst.executeUpdate() > 0){
						_tmpEEI.setEeiId(cst.getInt(1));
					}
				}else if(_tmpEEI.getEeiId() > 0){
					String sql = "{call sp_awb_eei_UPDATE(?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpEEI.getEeiId());
					cst.setString(2, _tmpEEI.getEei());
					cst.setString(3, _tmpEEI.getSupplier());
					cst.executeUpdate();
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbEEIList;
	}
	
	public boolean deleteAwbEEI(AwbEEI awbEEI) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_eei_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbEEI.getEeiId());
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
