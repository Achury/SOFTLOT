package com.lotrading.softlot.setup.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.setup.dao.IPortDao;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class PortDaoImpl extends DAOTemplate implements IPortDao {
	private Log log = LogFactory.getLog(PortDaoImpl.class);
	public PortDaoImpl(){

	}	

	/**
	 * 
	 * @param port
	 */
	public boolean createPort(Port port) throws Exception{
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_ports_CREATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, port.getName());
			cst.setInt(2, port.getCity().getCityId());
			cst.setString(3, port.getIataCode());
			cst.setBoolean(4, port.isAir());
			cst.setTimestamp(5, toTimeStamp(port.getEnteredDate()));
			
			if (cst.executeUpdate() > 0) {
				created = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return created;
	}

	/**
	 * 
	 * @param port
	 */
	public boolean updatePort(Port port) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_ports_UPDATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, port.getPortId());
			cst.setString(2, port.getName());
			cst.setInt(3, port.getCity().getCityId());
			cst.setString(4, port.getIataCode());
			cst.setBoolean(5, port.isAir());
			cst.setTimestamp(6, toTimeStamp(port.getEnteredDate()));
			if (port.getDisabledDate() != null) {
				cst.setTimestamp(7, toTimeStamp(port.getDisabledDate()));
			} else {
				cst.setTimestamp(7, null);
			}
			
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
	 * @param port
	 */
	public List<Port> searchPort(Port port) throws Exception{
		List<Port> ports = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_ports_SEARCH(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if(port.getName()== null) port.setName("");			
				cst.setString(1, port.getName());
			
			if(port.getCity().getName() == null) port.getCity().setName("");			
				cst.setString(2, port.getCity().getName());
			
			if(port.getIataCode() == null) port.setIataCode("");			
				cst.setString(3, port.getIataCode());
			
			if (port.getCity() != null 					
					&& port.getCity().getStateProvince() != null
					&& port.getCity().getStateProvince().getValueId() > 0) {
						cst.setInt(4, port.getCity().getStateProvince().getValueId());
			} else {
				cst.setInt(4, 0);
			}
			
			if (port.getCity() != null 					
					&& port.getCity().getCountry() != null
					&& port.getCity().getCountry().getValueId() > 0) {
						cst.setInt(5, port.getCity().getCountry().getValueId());
			} else {
				cst.setInt(5, 0);
			}
			
			rs = cst.executeQuery();

			if (rs.next()) {
				ports = new ArrayList<Port>();
				do {
					Port _tmpPort = new Port();
					_tmpPort.setPortId(rs.getInt(1));
					_tmpPort.setName(rs.getString(2));
					_tmpPort.setIataCode(rs.getString(3));
					_tmpPort.setAir(rs.getBoolean(4));
					_tmpPort.setEnteredDate(rs.getTimestamp(5));
					_tmpPort.setDisabledDate(rs.getTimestamp(6));
					
					
					//Create the City
					City _tmpCity = new City();
					_tmpCity.setCityId(rs.getInt(7));
					_tmpCity.setName(rs.getString(8));
					
					//Create the Country
					MasterValue _tmpCountry = new MasterValue();
					_tmpCountry.setValueId(rs.getInt(9));
					_tmpCountry.setValue1(rs.getString(10));
					
					//Set Country to City
					_tmpCity.setCountry(_tmpCountry);
					
					//Create the State
					MasterValue _tmpState = new MasterValue();
					_tmpState.setValueId(rs.getInt(11));
					_tmpState.setValue1(rs.getString(12));
					
					//Set State to City
					_tmpCity.setStateProvince(_tmpState);
					
					// Set City to Port
					_tmpPort.setCity(_tmpCity);
					
					ports.add(_tmpPort);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return ports;
	}

}