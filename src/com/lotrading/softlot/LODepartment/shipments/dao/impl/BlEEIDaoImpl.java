package com.lotrading.softlot.LODepartment.shipments.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlEEIDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlEEI;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class BlEEIDaoImpl extends DAOTemplate implements IBlEEIDao {

	private Log log = LogFactory.getLog(BlEEIDaoImpl.class);
	
	public List<BlEEI> saveBlEEI(List<BlEEI> blEEIList) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlEEI _tmpEEI : blEEIList){
				if(_tmpEEI.getEeiId() <= 0 && !_tmpEEI.isEmpty()){
					String sql = "{call sp_bl_eei_CREATE(?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpEEI.getBlId());
					cst.setString(3, _tmpEEI.getEei());
					cst.setString(4, _tmpEEI.getSupplier());
					if(cst.executeUpdate() > 0){
						_tmpEEI.setEeiId(cst.getInt(1));
					}
				}else if(_tmpEEI.getEeiId() > 0){
					String sql = "{call sp_bl_eei_UPDATE(?,?,?)}";
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
		return blEEIList;
	}
	
	public boolean deleteBlEEI(BlEEI blEEI) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_eei_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blEEI.getEeiId());
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
