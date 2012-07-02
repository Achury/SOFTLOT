package com.lotrading.softlot.warehouse.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.util.base.dao.DAOTemplate;
import com.lotrading.softlot.warehouse.dao.IWhLocationDao;
import com.lotrading.softlot.warehouse.entity.WhLocation;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 20-June-2012 10:40:00 AM
 */
public class WhLocationDaoImpl extends DAOTemplate implements IWhLocationDao{

	private Log log = LogFactory.getLog(WhLocationDaoImpl.class);

	@Override
	public List<WhLocation> searchWhLocation(WhLocation whLocation) throws Exception {
		List<WhLocation> whLocations = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_warehouse_locations_SEARCH()}";
			cst = conn.prepareCall(sql);
			
			rs = cst.executeQuery();
			if (rs.next()) {
				whLocations = new ArrayList<WhLocation>();
				
				do {
					WhLocation _tmpWhLocation = new WhLocation();
					_tmpWhLocation.setWhLocationId(rs.getString(1));
					_tmpWhLocation.setWhLocationName(rs.getString(2));
					
					whLocations.add(_tmpWhLocation);
				} while (rs.next());
			}			
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return whLocations;
	}
	
}
