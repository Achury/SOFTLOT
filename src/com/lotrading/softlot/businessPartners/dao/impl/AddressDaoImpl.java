package com.lotrading.softlot.businessPartners.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.dao.IAddressDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:50 a.m.
 */
public class AddressDaoImpl extends DAOTemplate implements IAddressDao {
	
	private Log log = LogFactory.getLog(AddressDaoImpl.class);

	public AddressDaoImpl(){

	}

	/**
	 * 
	 * @param address
	 * @throws Exception 
	 */
	public int createAddress(Address address) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_addresses_CREATE(?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			cst.setString(1, address.getAddress());
			if(address.getCity() != null ){
				cst.setInt(2, address.getCity().getCityId());
			}else{
				cst.setInt(2, 0);
			}
			cst.setString(3, address.getPostalCode());
			cst.registerOutParameter(4, Types.INTEGER);
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(4);
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
	 * @param address
	 * @throws Exception 
	 */
	public boolean updateAddress(Address address) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_addresses_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			cst.setInt(1, address.getAddressId());
			cst.setString(2, address.getAddress());
			if(address.getCity() != null){
				cst.setInt(3, address.getCity().getCityId());
			}else{
				cst.setInt(3, 0);
			}
			cst.setString(4, address.getPostalCode());
			if(cst.executeUpdate() > 0){
				updated = true;
			}	
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

}