package com.lotrading.softlot.businessPartners.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.dao.ICourierAccountDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:52 a.m.
 */
public class CourierAccountDaoImpl extends DAOTemplate implements ICourierAccountDao {

	private Log log = LogFactory.getLog(CourierAccountDaoImpl.class);
	
	public CourierAccountDaoImpl(){

	}

	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public int createCourierAccount(CourierAccount courierAccount) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_courierAccounts_CREATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, courierAccount.getPartnerDeptInfo());
			cst.setString(2, courierAccount.getAccountNumber());
			cst.setBoolean(3, courierAccount.isMain());
			cst.setInt(4, courierAccount.getCourier().getValueId());
			cst.registerOutParameter(5, Types.INTEGER);
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(5);
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return createdId;
	}

	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public boolean updateCourierAccount(CourierAccount courierAccount) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_courierAccounts_UPDATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, courierAccount.getCourierAccountId());
			cst.setInt(2, courierAccount.getPartnerDeptInfo());		
			cst.setString(3, courierAccount.getAccountNumber());
			cst.setBoolean(4, courierAccount.isMain());
			cst.setInt(5, courierAccount.getCourier().getValueId());
			if(cst.executeUpdate() > 0){
				updated = true;
				System.out.print("paso por >>>>>>>>>>>>>");
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}
	
	public boolean deleteCourierAccount(CourierAccount courierAccount) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_courierAccounts_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, courierAccount.getCourierAccountId());
			
			if(cst.executeUpdate() > 0){
				deleted = true;
				System.out.print("paso por >>>>>>>>>>>>>deleted");
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return deleted;
	}

}