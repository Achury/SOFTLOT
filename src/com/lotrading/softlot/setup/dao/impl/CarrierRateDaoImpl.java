package com.lotrading.softlot.setup.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.dao.ICarrierRateDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class CarrierRateDaoImpl extends DAOTemplate implements ICarrierRateDao {

	private Log log = LogFactory.getLog(CarrierRateDaoImpl.class);
	
	public CarrierRateDaoImpl(){

	}

	/**
	 * 
	 * @param carrierRate
	 * @throws Exception 
	 */
	public int createCarrierRate(CarrierPorts carrierPort) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_ports_CREATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, carrierPort.getCarrierId());
			cst.setInt(3, carrierPort.getPortOrigin());
			cst.setInt(4, carrierPort.getPortDestination());
			cst.setInt(5, carrierPort.getRateType().getValueId());
			cst.setTimestamp(6, toTimeStamp(carrierPort.getEffectiveDate()));
			if (cst.executeUpdate() > 0) {		
				carrierPort.setCarrierPortId(cst.getInt(1));
				for(CarrierRate _tmpCarrierRate : carrierPort.getCarrierRates()){
					cst = null;
					sql = "{call lotradingdb.sp_carriers_rate_CREATE(?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, carrierPort.getCarrierPortId());
					cst.setInt(2, _tmpCarrierRate.getChargeType().getValueId());
					cst.setDouble(3, _tmpCarrierRate.getRate());
					cst.setDouble(4, _tmpCarrierRate.getMinimum());
					cst.setBoolean(5, _tmpCarrierRate.isOtherCharge());
					cst.registerOutParameter(6, Types.INTEGER);
					if (cst.executeUpdate() > 0) {	
						_tmpCarrierRate.setCarrierRateId(cst.getInt(6));
					}
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return carrierPort.getCarrierPortId();
	}

	/**
	 * 
	 * @param carrierRate
	 * @throws Exception 
	 */
	public boolean updateCarrierRate(CarrierPorts carrierPort) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_ports_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierPort.getCarrierPortId());
			cst.setInt(2, carrierPort.getPortOrigin());
			cst.setInt(3, carrierPort.getPortDestination());
			cst.setTimestamp(4, toTimeStamp(carrierPort.getEffectiveDate()));
			if (cst.executeUpdate() > 0) {		
				updated = true;
				for(CarrierRate _tmpCarrierRate : carrierPort.getCarrierRates()){
					if(updated && _tmpCarrierRate.getCarrierRateId() > 0){
						cst = null;
						sql = "{call lotradingdb.sp_carriers_rate_UPDATE(?,?,?)}";
						cst = conn.prepareCall(sql);
						cst.setInt(1, _tmpCarrierRate.getCarrierRateId());
						cst.setDouble(2, _tmpCarrierRate.getRate());
						cst.setDouble(3, _tmpCarrierRate.getMinimum());
						if(cst.executeUpdate() > 0){
							updated = true;
						}else{
							updated = false;
						}
					}else if(updated && _tmpCarrierRate.getCarrierRateId() <= 0){
						cst = null;
						sql = "{call lotradingdb.sp_carriers_rate_CREATE(?,?,?,?,?,?)}";
						cst = conn.prepareCall(sql);
						cst.setInt(1, carrierPort.getCarrierPortId());
						cst.setInt(2, _tmpCarrierRate.getChargeType().getValueId());
						cst.setDouble(3, _tmpCarrierRate.getRate());
						cst.setDouble(4, _tmpCarrierRate.getMinimum());
						cst.setBoolean(5, _tmpCarrierRate.isOtherCharge());
						cst.registerOutParameter(6, Types.INTEGER);
						if (cst.executeUpdate() > 0) {	
							_tmpCarrierRate.setCarrierRateId(cst.getInt(6));
							updated = true;
						}else{
							updated = false;
						}
					}
				}
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
	 * @param carrierRate
	 * @throws Exception 
	 */
	public boolean deleteCarrierRate(CarrierRate carrierRate) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_rate_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierRate.getCarrierRateId());
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