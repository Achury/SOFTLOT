/**
 * 
 */
package com.lotrading.softlot.businessPartners.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.IClientRateDao;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 04-Ene-2012 2:39:00 p.m.
 */

public class ClientRateDaoImpl extends DAOTemplate implements IClientRateDao {
	
	private Log log = LogFactory.getLog(ClientRateDaoImpl.class);
	
	public int createClientRate(ClientRatesPort clientRatesPort) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_client_rates_ports_CREATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, clientRatesPort.getClientId());
			cst.setInt(3, clientRatesPort.getCarrierId());
			cst.setInt(4, clientRatesPort.getPortDestination());
			cst.setInt(5, clientRatesPort.getPortOrigin());
			cst.setInt(6, clientRatesPort.getRateType().getValueId());
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(1);
				clientRatesPort.setClientRatePortId(createdId);
				for(ClientRate _tmpClientRate : clientRatesPort.getClientRates()){
					cst = null;
					sql = "{call sp_client_rate_CREATE(?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, clientRatesPort.getClientRatePortId());
					cst.setInt(3, _tmpClientRate.getChargeType().getValueId());
					cst.setDouble(4, _tmpClientRate.getRate());
					cst.setDouble(5, _tmpClientRate.getRateOffset());
					cst.setDouble(6, _tmpClientRate.getMinimumRate());
					cst.setDouble(7, _tmpClientRate.getMinimumOffset());
					cst.setBoolean(8, _tmpClientRate.isOtherCharge());
					cst.setBoolean(9, _tmpClientRate.isSelectedToAwbDoc());
					if(cst.executeUpdate() > 0){
						_tmpClientRate.setClientRateId(cst.getInt(1));
					}
				}
			}			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return createdId;
	}
	
	public boolean updateClientRate(ClientRatesPort clientRatesPort) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_client_rates_ports_UPDATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRatesPort.getClientRatePortId());
			cst.setInt(2, clientRatesPort.getCarrierId());
			cst.setInt(3, clientRatesPort.getPortDestination());
			cst.setInt(4, clientRatesPort.getPortOrigin());
			if(cst.executeUpdate() > 0){
				updated = true;
				for(ClientRate _tmpClientRate : clientRatesPort.getClientRates()){
					if(updated && _tmpClientRate.getClientRateId() > 0){
						cst = null;
						sql = "{call lotradingdb.sp_client_rate_UPDATE(?,?,?,?,?,?)}";
						cst = conn.prepareCall(sql);
						cst.setInt(1, _tmpClientRate.getClientRateId());
						cst.setDouble(2, _tmpClientRate.getRate());
						cst.setDouble(3, _tmpClientRate.getRateOffset());
						cst.setDouble(4, _tmpClientRate.getMinimumRate());
						cst.setDouble(5, _tmpClientRate.getMinimumOffset());
						cst.setBoolean(6, _tmpClientRate.isSelectedToAwbDoc());
						if(cst.executeUpdate() > 0){
							updated = true;
						}else{
							updated = false;
						}
					}else if(updated && _tmpClientRate.getClientRateId() <= 0){
						cst = null;
						sql = "{call sp_client_rate_CREATE(?,?,?,?,?,?,?,?,?)}";
						cst = conn.prepareCall(sql);
						cst.registerOutParameter(1, Types.INTEGER);
						cst.setInt(2, clientRatesPort.getClientRatePortId());
						cst.setInt(3, _tmpClientRate.getChargeType().getValueId());
						cst.setDouble(4, _tmpClientRate.getRate());
						cst.setDouble(5, _tmpClientRate.getRateOffset());
						cst.setDouble(6, _tmpClientRate.getMinimumRate());
						cst.setDouble(7, _tmpClientRate.getMinimumOffset());
						cst.setBoolean(8, _tmpClientRate.isOtherCharge());
						cst.setBoolean(9, _tmpClientRate.isSelectedToAwbDoc());
						if(cst.executeUpdate() > 0){
							_tmpClientRate.setClientRateId(cst.getInt(1));
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

	public boolean deleteClientRate(ClientRate clientRate) throws Exception {
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_rate_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRate.getClientRateId());
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

	public ClientRate loadRateCost(ClientRatesPort clientRatesPort, ClientRate clientRate) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_partners_LOAD_RATE_COST(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRatesPort.getPortOrigin());
			cst.setInt(2, clientRatesPort.getPortDestination());
			cst.setInt(3, clientRatesPort.getCarrierId());
			cst.setInt(4, clientRate.getChargeType().getValueId());
			rs = cst.executeQuery();				
			if (rs.next()) {								
				clientRate.setRate(rs.getDouble(1) + clientRate.getRateOffset());     		//Esto es el costo que trae de los carriers sumado con el offset del clientRat, 
																					  		//el costo + lo que se quiere ganar = la venta.
				clientRate.setMinimumRate(rs.getDouble(2) + clientRate.getMinimumOffset());	// lo mismo de arriba pero con el minimum.
			}else{
				clientRate.setRate(clientRate.getRateOffset());
				clientRate.setMinimumRate(clientRate.getMinimumOffset());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return clientRate;
		
	}
}
