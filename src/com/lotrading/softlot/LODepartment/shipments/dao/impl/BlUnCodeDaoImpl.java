package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;
import com.lotrading.softlot.LODepartment.shipments.dao.IBlUnCodeDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlUnCodeDaoImpl extends DAOTemplate implements IBlUnCodeDao {
	
	private Log log = LogFactory.getLog(BlUnCodeDaoImpl.class);

	public BlUnCodeDaoImpl(){

	}

	/**
	 * 
	 * @param blUnCode
	 * @throws Exception 
	 */
	public int createUnCode(BlUnCode blUnCode) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_un_code_CREATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blUnCode.getBlId());
			cst.setString(3, blUnCode.getUnCode());
			cst.setInt(4, blUnCode.getUnClassId());
			cst.setInt(5, blUnCode.getPackingGroupId());		
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
	 * @param blUnCode
	 * @throws Exception 
	 */
	public boolean updateUnCode(BlUnCode blUnCode) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_un_code_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blUnCode.getUnCodeId());
			cst.setString(2, blUnCode.getUnCode());
			cst.setInt(3, blUnCode.getUnClassId());
			cst.setInt(4, blUnCode.getPackingGroupId());
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
	 * @param blUnCode
	 * @throws Exception 
	 */
	public boolean deleteUnCode(BlUnCode blUnCode) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_un_code_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blUnCode.getUnCodeId());
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
	
	public List<BlUnCode> saveUnCodes(List<BlUnCode> blUnCodes) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlUnCode _tmpUnCode : blUnCodes){
				if(_tmpUnCode.getUnCodeId() <= 0 && !_tmpUnCode.isEmpty()){
					String sql = "{call sp_bl_un_code_CREATE(?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpUnCode.getBlId());
					cst.setString(3, _tmpUnCode.getUnCode());
					cst.setInt(4, _tmpUnCode.getUnClassId());
					cst.setInt(5, _tmpUnCode.getPackingGroupId());		
					if(cst.executeUpdate() > 0){
						_tmpUnCode.setUnCodeId(cst.getInt(1));
					}	
				}else if(_tmpUnCode.getUnCodeId() > 0){
					String sql = "{call sp_bl_un_code_UPDATE(?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpUnCode.getUnCodeId());
					cst.setString(2, _tmpUnCode.getUnCode());
					cst.setInt(3, _tmpUnCode.getUnClassId());
					cst.setInt(4, _tmpUnCode.getPackingGroupId());
					cst.executeUpdate();
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blUnCodes;
	}

}