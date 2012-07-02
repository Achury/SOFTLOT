package com.lotrading.softlot.businessPartners.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.dao.IPhoneDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class PhoneDaoImpl extends DAOTemplate implements IPhoneDao {

	private Log log = LogFactory.getLog(PhoneDaoImpl.class);
	
	public PhoneDaoImpl(){

	}

	/**
	 * 
	 * @param phone
	 * @throws Exception 
	 */
	public int createPhone(Phone phone) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_phone_CREATE(?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			cst.setInt(1, phone.getType().getValueId());
			cst.setString(2, phone.getCountryCode());
			cst.setString(3, phone.getAreaCode());
			cst.setString(4, phone.getPhoneNumber());
			cst.setString(5, phone.getPhoneExtension());
			cst.setBoolean(6, phone.isMainPhone());
			cst.setTimestamp(7, toTimeStamp(phone.getEnteredDate()));
			cst.registerOutParameter(8, Types.INTEGER);			
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(8);
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
	 * @param phone
	 * @throws Exception 
	 */
	public boolean updatePhone(Phone phone) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_phone_UPDATE(?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			cst.setInt(1, phone.getPhoneId());
			cst.setInt(2, phone.getType().getValueId());
			cst.setString(3, phone.getCountryCode());
			cst.setString(4, phone.getAreaCode());
			cst.setString(5, phone.getPhoneNumber());
			cst.setString(6, phone.getPhoneExtension());
			cst.setBoolean(7, phone.isMainPhone());
			if(phone.getDisabledDate() != null){
				cst.setTimestamp(8, toTimeStamp(phone.getDisabledDate()));
			}else{
				cst.setDate(8, null);
			}
			if(cst.executeUpdate() > 0){
				updated = true;
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

}