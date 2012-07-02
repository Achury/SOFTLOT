package com.lotrading.softlot.LODepartment.clientOrder.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;
import com.lotrading.softlot.LODepartment.clientOrder.dao.IClientOrderSupplierInfoDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderSupplierInfoDaoImpl extends DAOTemplate implements IClientOrderSupplierInfoDao {

	private Log log = LogFactory.getLog(ClientOrderSupplierInfoDaoImpl.class);
	
	public ClientOrderSupplierInfoDaoImpl(){

	}
	
	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public int createClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_client_order_supplier_info_CREATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderSupplierInfo.getClientOrderId());
			cst.setString(2, clientOrderSupplierInfo.getSupplierInvoiceNum());
			cst.setDouble(3, clientOrderSupplierInfo.getTotalSupplierInvoice());
			cst.setInt(4, clientOrderSupplierInfo.getInvoiceType().getValueId());
			cst.registerOutParameter(5, Types.INTEGER);
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(5);
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
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public boolean updateClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_order_supplier_info_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, clientOrderSupplierInfo.getSupplierInvoiceNum());
			cst.setDouble(2, clientOrderSupplierInfo.getTotalSupplierInvoice());
			cst.setInt(3, clientOrderSupplierInfo.getClientOrderSupplierInfoId());
			cst.setInt(4, clientOrderSupplierInfo.getInvoiceType().getValueId());
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
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public boolean deleteClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_order_supplier_info_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderSupplierInfo.getClientOrderSupplierInfoId());
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