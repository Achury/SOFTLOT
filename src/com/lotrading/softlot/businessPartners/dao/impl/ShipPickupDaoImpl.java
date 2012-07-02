package com.lotrading.softlot.businessPartners.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.dao.IShipPickupDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:04 a.m.
 */
public class ShipPickupDaoImpl extends DAOTemplate implements IShipPickupDao {

	private Log log = LogFactory.getLog(ShipPickupDaoImpl.class);
	
	public ShipPickupDaoImpl(){

	}

	/**
	 * 
	 * @param shipPickup
	 * @throws Exception 
	 */
	public int createShipPickup(ShipPickUp shipPickup) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_ship_pickup_CREATE(?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			cst.setInt(1, shipPickup.getPartnerId());
			cst.setString(2, shipPickup.getName());
			
			if (shipPickup.getAddress() != null) {
				cst.setInt(3, shipPickup.getAddress().getAddressId());
			}else{
				cst.setInt(3, 0);
			}
			
			cst.setInt(4, shipPickup.getDestinationAirport());
			cst.setInt(5, shipPickup.getDestinationPort());					
			cst.setString(6, shipPickup.getEmail());
			cst.setString(7, shipPickup.getNotes());
			cst.setString(8, shipPickup.getNotifyParty());
			cst.setTimestamp(9, toTimeStamp(shipPickup.getEnteredDate()));
			cst.registerOutParameter(10, Types.INTEGER);
			
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(10);
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
	 * @param shipPickup
	 * @throws Exception 
	 */
	public boolean updateShipPickup(ShipPickUp shipPickup) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_ship_pickup_UPDATE(?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			cst.setInt(1, shipPickup.getShipPickUpId());
			cst.setInt(2, shipPickup.getPartnerId());
			cst.setString(3, shipPickup.getName());
			cst.setInt(4, shipPickup.getAddress().getAddressId());			
			cst.setInt(5, shipPickup.getDestinationAirport());
			cst.setInt(6, shipPickup.getDestinationPort());			
			cst.setString(7, shipPickup.getEmail());
			cst.setString(8, shipPickup.getNotes());
			cst.setString(9, shipPickup.getNotifyParty());
			cst.setTimestamp(10, toTimeStamp(shipPickup.getEnteredDate()));
			if(shipPickup.getDisabledDate() != null){
				cst.setTimestamp(11, toTimeStamp(shipPickup.getDisabledDate()));
			}else{
				cst.setDate(11, null);
			}
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

	
	public List<ShipPickUp> filterByShipPickup(ShipPickUp shipPickup) throws Exception {
		List<ShipPickUp> shipPickUps = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partners_LOAD_SHIP_PICKUPS(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, shipPickup.getPartnerId());
			rs = cst.executeQuery();					
			shipPickUps = new ArrayList<ShipPickUp>();
			if (rs.next()) {
								
				do {
					ShipPickUp _tmpShipPickUp = new ShipPickUp();
					_tmpShipPickUp.setShipPickUpId((rs.getInt(1)));
					_tmpShipPickUp.setName(rs.getString(2));
					
					// ********* begin fill address data *********					
					_tmpShipPickUp.getAddress().setAddressId(rs.getInt(3));
					_tmpShipPickUp.getAddress().setAddress(rs.getString(4));
					_tmpShipPickUp.getAddress().setPostalCode(rs.getString(5));
					// ********* end fill address data *********
					
					// ********* begin fill city data *********
					_tmpShipPickUp.getAddress().getCity().setCityId(rs.getInt(6));
					_tmpShipPickUp.getAddress().getCity().setName(rs.getString(7));
					// ********* end fill city data *********
					
					// ********* begin fill state data *********
					_tmpShipPickUp.getAddress().getCity().getStateProvince().setValueId(rs.getInt(8));
					_tmpShipPickUp.getAddress().getCity().getStateProvince().setValue1(rs.getString(9));					
					// ********* end fill state data *********
					
					// ********* begin fill country data *********
					_tmpShipPickUp.getAddress().getCity().getCountry().setValueId(rs.getInt(10));
					_tmpShipPickUp.getAddress().getCity().getCountry().setValue1(rs.getString(11));					
					// ********* end fill country data *********
											
					_tmpShipPickUp.setEnteredDate( rs.getTimestamp(12));
					_tmpShipPickUp.setDisabledDate( rs.getTimestamp(13));
					_tmpShipPickUp.setPartnerId(rs.getInt(14));
					_tmpShipPickUp.setDestinationAirport(rs.getInt(15));
					_tmpShipPickUp.setDestinationPort(rs.getInt(16));
					_tmpShipPickUp.setEmail(rs.getString(17));
					_tmpShipPickUp.setNotes(rs.getString(18));
					_tmpShipPickUp.setNotifyParty(rs.getString(19));

					shipPickUps.add(_tmpShipPickUp);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return shipPickUps;
	}

}