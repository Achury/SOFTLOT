package com.lotrading.softlot.setup.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.dao.ICarrierDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:51 a.m.
 */
public class CarrierDaoImpl extends DAOTemplate implements ICarrierDao {

	private Log log = LogFactory.getLog(CarrierDaoImpl.class);

	public CarrierDaoImpl() {

	}

	/**
	 * 
	 * @param carrier
	 * @throws Exception
	 */
	public int createCarrier(Carrier carrier) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_CREATE(?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, carrier.getName());
			cst.setString(2, carrier.getNotes());
			cst.setString(3, carrier.getLotAccount());
			cst.setInt(4, carrier.getCarrierType().getValueId());
			cst.setString(5, carrier.getCarrierCode());
			cst.setString(6, carrier.getIataCode());
			cst.setTimestamp(7, toTimeStamp(carrier.getEnteredDate()));
			cst.setBoolean(8, carrier.isAllowChildrenAWB());
			cst.registerOutParameter(9, Types.INTEGER);
			if (cst.executeUpdate() > 0) {		
				createdId = cst.getInt(9);
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
	 * @param carrier
	 * @throws Exception
	 */
	public boolean updateCarrier(Carrier carrier) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_UPDATE(?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrier.getCarrierId());
			cst.setString(2, carrier.getName());
			cst.setString(3, carrier.getNotes());
			cst.setString(4, carrier.getLotAccount());
			cst.setString(5, carrier.getCarrierCode());
			cst.setInt(6, carrier.getCarrierType().getValueId());
			cst.setString(7, carrier.getIataCode());
			if (carrier.getDisabledDate() != null) {
				cst.setTimestamp(8, toTimeStamp(carrier.getDisabledDate()));
			} else {
				cst.setDate(8, null);
			}
			cst.setBoolean(9, carrier.isAllowChildrenAWB());
			if (cst.executeUpdate() > 0) {
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
	 * @param carrier
	 * @throws Exception
	 */
	public List<Carrier> searchCarrier(Carrier carrier) throws Exception {
		List<Carrier> carriers = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_SEARCH(?)}";
			cst = conn.prepareCall(sql);
			if(carrier.getName() != null){
				cst.setString(1, carrier.getName());
			}else{
				cst.setString(1, "");
			}
			
			rs = cst.executeQuery();
			if (rs.next()) {
				carriers = new ArrayList<Carrier>();
				do {
					carrier = new Carrier();
					carrier.setCarrierId(rs.getInt(1));
					carrier.setName(rs.getString(2));
					carrier.setNotes(rs.getString(3));
					carrier.setLotAccount(rs.getString(4));
					carrier.setCarrierCode(rs.getString(5));
					carrier.setIataCode(rs.getString(6));
					carrier.setAllowChildrenAWB(rs.getBoolean(7));
					carrier.setEnteredDate(rs.getTimestamp(8));
					carrier.setDisabledDate(rs.getTimestamp(9));

					MasterValue _tmpMasterValue = new MasterValue(); 
					_tmpMasterValue.setValueId(rs.getInt(10));
					_tmpMasterValue.setValue1(rs.getString(11));
					
					carrier.setShowAwbBlNumbersTab(rs.getBoolean(12));
					 carrier.setCarrierType(_tmpMasterValue);
					 
					carriers.add(carrier);
				} while (rs.next());
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return carriers;
	}
	
	public List<CarrierPorts> loadCarrierPorts(CarrierPorts carrierPort) throws Exception{
		List<CarrierPorts> carrierPorts = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_carriers_LOAD_PORTS_RATES(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierPort.getCarrierId());
			cst.setInt(2, carrierPort.getRateType().getValueId());
			rs = cst.executeQuery();
			carrierPorts = new ArrayList<CarrierPorts>();
			if (rs.next()) {	
				do {
					CarrierPorts _tmpCarrierPort = new CarrierPorts();
					_tmpCarrierPort.setCarrierPortId(rs.getInt(1));
					_tmpCarrierPort.setPortOrigin(rs.getInt(2));
					_tmpCarrierPort.setPortDestination(rs.getInt(3));
					_tmpCarrierPort.setEffectiveDate(rs.getTimestamp(4));
					carrierPorts.add(_tmpCarrierPort);
				}while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return carrierPorts;
		
	}

	public List<CarrierRate> loadCarrierRates(CarrierRate carrierRate) throws Exception {
		List<CarrierRate> carrierRates = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_carriers_LOAD_RATES(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierRate.getCarrierPortId());
			rs = cst.executeQuery();
			carrierRates = new ArrayList<CarrierRate>();
			if (rs.next()) {	
				do {
					CarrierRate _tmpCarrierRate = new CarrierRate();
					_tmpCarrierRate.setCarrierRateId(rs.getInt(1));
					_tmpCarrierRate.getChargeType().setValueId(rs.getInt(2));
					_tmpCarrierRate.setRate(rs.getDouble(3));
					_tmpCarrierRate.setMinimum(rs.getDouble(4));
					_tmpCarrierRate.setOtherCharge(rs.getBoolean(5));
					_tmpCarrierRate.setCreatedDate(rs.getDate(6));
					_tmpCarrierRate.getChargeType().setValue1(rs.getString(7));
					_tmpCarrierRate.setFlat(rs.getBoolean(8));
					carrierRates.add(_tmpCarrierRate);
				}while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return carrierRates;
	}

	public List<CarrierAwbNumber> loadCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception {
		List<CarrierAwbNumber> carrierAwbNumbers = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_carriers_LOAD_AWB_NUMBERS(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierAwbNumber.getCarrier().getCarrierId());
			rs = cst.executeQuery();
			carrierAwbNumbers = new ArrayList<CarrierAwbNumber>();
			if (rs.next()) {								
				do {
					CarrierAwbNumber _tmpCarrierAwbNumber = new CarrierAwbNumber();
					_tmpCarrierAwbNumber.setCarrierAwbNumberId(rs.getInt(1));
					_tmpCarrierAwbNumber.setAwbNumber(rs.getString(2));
					_tmpCarrierAwbNumber.setUsed(rs.getBoolean(3));
					_tmpCarrierAwbNumber.setCreatedDate(rs.getDate(4));
					_tmpCarrierAwbNumber.getCarrier().setCarrierCode(rs.getString(5));
					carrierAwbNumbers.add(_tmpCarrierAwbNumber);
				}while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return carrierAwbNumbers;
	}
	
}