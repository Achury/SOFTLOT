package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.Bl;
import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;
import com.lotrading.softlot.LODepartment.shipments.dao.IBlContainerDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 9-March-2012 12:00:00 AM
 */
public class BlContainerDaoImpl extends DAOTemplate implements IBlContainerDao {

	private Log log = LogFactory.getLog(BlContainerDaoImpl.class);
	
	public BlContainerDaoImpl(){

	}

	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public int createBlContainer(BlContainer blContainer) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_container_CREATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blContainer.getBlId());			
			cst.setInt(3, blContainer.getType().getValueId());			
			cst.setInt(4, blContainer.getLineNumber());
			cst.setString(5, blContainer.getContainerNumber());			
			cst.setString(6, blContainer.getSeal());
			
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
	 * @param blContainer
	 * @throws Exception 
	 */
	public boolean updateBlContainer(BlContainer blContainer) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_container_UPDATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blContainer.getContainerId());
			cst.setInt(2, blContainer.getType().getValueId());	
			cst.setInt(3, blContainer.getLineNumber());
			cst.setString(4, blContainer.getContainerNumber());
			cst.setString(5, blContainer.getSeal());
			
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
	
	public List<BlContainer> saveBlContainers(List<BlContainer> blContainers) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlContainer _tmpContainer : blContainers){
				if(_tmpContainer.getContainerId() <= 0 && !_tmpContainer.isEmpty()){
					String sql = "{call sp_bl_container_CREATE(?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpContainer.getBlId());			
					cst.setInt(3, _tmpContainer.getType().getValueId());					
					cst.setInt(4, _tmpContainer.getLineNumber());
					cst.setString(5, _tmpContainer.getContainerNumber());
					cst.setString(6, _tmpContainer.getSeal());
					
					if(cst.executeUpdate() > 0){
						_tmpContainer.setContainerId(cst.getInt(1));
					}			
				}else if(_tmpContainer.getContainerId() > 0){
					String sql = "{call sp_bl_container_UPDATE(?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpContainer.getContainerId());					
					cst.setInt(2, _tmpContainer.getType().getValueId());					
					cst.setInt(3, _tmpContainer.getLineNumber());
					cst.setString(4, _tmpContainer.getContainerNumber());
					cst.setString(5, _tmpContainer.getSeal());				
					
					cst.executeUpdate();
				}
			}		
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blContainers;
	}

	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public boolean deleteBlContainer(BlContainer blContainer) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_container_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blContainer.getContainerId());
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
	
	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public BlContainer loadBlContainer(BlContainer blContainer)throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_container_LOAD_ONE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blContainer.getContainerId());
			rs = cst.executeQuery();
			if(rs.next()){
				blContainer.getType().setValueId(rs.getInt(1));
				blContainer.setLineNumber(rs.getInt(2));
				blContainer.setContainerNumber(rs.getString(3));
				blContainer.setSeal(rs.getString(4));
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blContainer;
	}

}