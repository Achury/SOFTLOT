package com.lotrading.softlot.setup.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.ICarrierAwbNumberDao;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class CarrierAwbNumberDaoImpl extends DAOTemplate implements ICarrierAwbNumberDao {
	
	private Log log = LogFactory.getLog(CarrierAwbNumberDaoImpl.class);
	
	public CarrierAwbNumberDaoImpl(){

	}

	/**
	 * 
	 * @param carrierAwbNumber
	 * @throws Exception 
	 */
	public List<CarrierAwbNumber> createCarrierAwbNumber(List<CarrierAwbNumber> carrierAwbNumbers) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_awb_numbers_CREATE(?,?,?,?)}";
			for(CarrierAwbNumber _tmpAwbNumber : carrierAwbNumbers){
				cst = conn.prepareCall(sql);
				cst.setString(1, _tmpAwbNumber.getAwbNumber());
				cst.setInt(2, _tmpAwbNumber.getCarrier().getCarrierId());
				cst.setBoolean(3, _tmpAwbNumber.isUsed());
				cst.registerOutParameter(4, Types.INTEGER);
				if (cst.executeUpdate() > 0) {		
					_tmpAwbNumber.setCarrierAwbNumberId(cst.getInt(4));
				}
			}
				
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return carrierAwbNumbers;
	}
	
	public boolean updateCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception{
		boolean _tmpReturn = false;
		CallableStatement cst2 = null;
		Connection conn2 = null;
		try {
			conn2 = getConnection();
			String sql = "{call lotradingdb.sp_carriers_awb_number_UPDATE(?,?)}";
			cst2 = conn2.prepareCall(sql);
			cst2.setInt(1, carrierAwbNumber.getCarrierAwbNumberId());
			cst2.setBoolean(2, carrierAwbNumber.isUsed());
			if (cst2.executeUpdate() > 0) {
				_tmpReturn = true;
			}				
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn2);
		}
		return _tmpReturn;
	}


	/**
	 * 
	 * @param carrierAwbNumber
	 * @throws Exception 
	 */
	public boolean deleteCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_awb_number_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierAwbNumber.getCarrierAwbNumberId());
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