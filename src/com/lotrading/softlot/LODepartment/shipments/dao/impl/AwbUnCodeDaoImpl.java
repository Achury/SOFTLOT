package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;
import com.lotrading.softlot.LODepartment.shipments.dao.IAwbUnCodeDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbUnCodeDaoImpl extends DAOTemplate implements IAwbUnCodeDao {
	
	private Log log = LogFactory.getLog(AwbUnCodeDaoImpl.class);

	public AwbUnCodeDaoImpl(){

	}

	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public int createUnCode(AwbUnCode awbUnCode) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_un_code_CREATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, awbUnCode.getAwbId());
			cst.setString(3, awbUnCode.getUnCode());
			cst.setInt(4, awbUnCode.getUnClassId());
			cst.setInt(5, awbUnCode.getPackingGroupId());		
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
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public boolean updateUnCode(AwbUnCode awbUnCode) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_un_code_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbUnCode.getUnCodeId());
			cst.setString(2, awbUnCode.getUnCode());
			cst.setInt(3, awbUnCode.getUnClassId());
			cst.setInt(4, awbUnCode.getPackingGroupId());
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
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public boolean deleteUnCode(AwbUnCode awbUnCode) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_un_code_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbUnCode.getUnCodeId());
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

	public List<AwbUnCode> saveUnCodes(List<AwbUnCode> awbUnCodes) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbUnCode _tmpUnCode : awbUnCodes){
				if(_tmpUnCode.getUnCodeId() <= 0 && !_tmpUnCode.isEmpty()){
					String sql = "{call sp_awb_un_code_CREATE(?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpUnCode.getAwbId());
					cst.setString(3, _tmpUnCode.getUnCode());
					cst.setInt(4, _tmpUnCode.getUnClassId());
					cst.setInt(5, _tmpUnCode.getPackingGroupId());		
					if(cst.executeUpdate() > 0){
						_tmpUnCode.setUnCodeId(cst.getInt(1));
					}	
				}else if(_tmpUnCode.getUnCodeId() > 0){
					String sql = "{call sp_awb_un_code_UPDATE(?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpUnCode.getUnCodeId());
					cst.setString(2, _tmpUnCode.getUnCode());
					cst.setInt(3, _tmpUnCode.getUnClassId());
					cst.setInt(4, _tmpUnCode.getPackingGroupId());
					cst.executeUpdate();
				}else if(_tmpUnCode.isEmpty()){
					awbUnCodes.remove(_tmpUnCode);
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbUnCodes;
	}

}