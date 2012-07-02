package com.lotrading.softlot.LODepartment.clientOrder.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;
import com.lotrading.softlot.LODepartment.clientOrder.dao.IClientOrderInlandCSDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderInlandCSDaoImpl extends DAOTemplate implements IClientOrderInlandCSDao {

	private Log log = LogFactory.getLog(ClientOrderInlandCSDaoImpl.class);
	
	public ClientOrderInlandCSDaoImpl(){

	}

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public int createClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_client_order_inlandCS_CREATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderInlandCS.getClientOrderId());
			cst.setInt(2, clientOrderInlandCS.getTruckCompany().getValueId());
			cst.setDouble(3, clientOrderInlandCS.getCost());
			cst.setDouble(4, clientOrderInlandCS.getSales());
			cst.setString(5, clientOrderInlandCS.getTrackingNumber());
			cst.registerOutParameter(6, Types.INTEGER);
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(6);
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
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public boolean updateClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_order_inlandCS_UPDATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderInlandCS.getClientOrderInlandCSId());
			cst.setInt(2, clientOrderInlandCS.getTruckCompany().getValueId());
			cst.setDouble(3, clientOrderInlandCS.getCost());
			cst.setDouble(4, clientOrderInlandCS.getSales());
			cst.setString(5, clientOrderInlandCS.getTrackingNumber());
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
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public boolean deleteClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_order_inlandCS_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderInlandCS.getClientOrderInlandCSId());
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