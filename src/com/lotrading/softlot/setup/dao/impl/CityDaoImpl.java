package com.lotrading.softlot.setup.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.dao.ICityDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:52 a.m.
 */
public class CityDaoImpl extends DAOTemplate implements ICityDao {

	private Log log = LogFactory.getLog(CityDaoImpl.class);
	
	public CityDaoImpl(){

	}


	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public boolean createCity(City city) throws Exception{
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_cities_CREATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, city.getName());
			if(city.getStateProvince() != null){
				cst.setInt(2, city.getStateProvince().getValueId());
			}else{
				cst.setInt(2, 0);
			}	
			cst.setInt(3, city.getCountry().getValueId());
			cst.setTimestamp(4, toTimeStamp(city.getEnteredDate()));
			if (cst.executeUpdate() > 0) {
				created = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			if(e.toString().indexOf("Duplicate entry") > -1){
				throw new Exception("City already exist");
			}else if(e.toString().indexOf("a foreign key constraint fails") > -1){
				throw new Exception("Choose a valid country");
			}else{
				throw e;
			}
		} finally {
			close(conn);
		}
		return created;
	}
	
	
	/**
	 * 
	 * @param city
	 * @throws Exception 
	 * @created 19-may-2011 4:21:55 p.m.  JU
	 */
	@Override
	public City loadCityByid(City city) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_cities_LOAD_BY_ID(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, city.getCityId());
			rs = cst.executeQuery();			
			if (rs.next()) {			
				city.setCityId(rs.getInt(1));
				city.setName(rs.getString(2));
				MasterValue _tmpMasterValue = new MasterValue();
				_tmpMasterValue.setValueId(rs.getInt(3));
				_tmpMasterValue.setValue1(rs.getString(4));
				city.setStateProvince(_tmpMasterValue);
				_tmpMasterValue = new MasterValue();
				_tmpMasterValue.setValueId(rs.getInt(5));
				_tmpMasterValue.setValue1(rs.getString(6));
				city.setCountry(_tmpMasterValue);
			}
			System.out.println("load city by id ");
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return city;
	}
	

	/**
	 * 
	 * @param city
	 * @throws Exception 
	 */
	public boolean updateCity(City city) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_cities_UPDATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, city.getCityId());
			cst.setString(2, city.getName());
			if(city.getStateProvince() != null){
				cst.setInt(3, city.getStateProvince().getValueId());
			}else{
				cst.setInt(3, 0);
			}
			cst.setInt(4, city.getCountry().getValueId());
			cst.setTimestamp(5, toTimeStamp(city.getEnteredDate()));
			if(city.getDisabledDate() != null){
				cst.setTimestamp(6, toTimeStamp(city.getDisabledDate()));
			}else{
				cst.setTimestamp(6, null);
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
	 * @param city
	 * @throws Exception 
	 */
	public List<City> searchCity(City city) throws Exception{
		List<City> cities = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_cities_SEARCH(?,?,?)}";
			cst = conn.prepareCall(sql);
			if(city.getName() != null){
				cst.setString(1, city.getName());
			}else{
				cst.setString(1, "");
			}
			

			if (city.getStateProvince() != null
					&& city.getStateProvince().getValueId() > 0) {
						cst.setInt(2, city.getStateProvince().getValueId());
			} else {
				cst.setInt(2, 0);
			}
			
			if (city.getCountry() != null
					&& city.getCountry().getValueId() > 0) {
						cst.setInt(3, city.getCountry().getValueId());
			} else {
				cst.setInt(3, 0);
			}
			
			rs = cst.executeQuery();
			if (rs.next()) {
				cities = new ArrayList<City>();
				
				do {
					City _tmpCity = new City();
					_tmpCity.setCityId(rs.getInt(1));
					_tmpCity.setName(rs.getString(2));
					_tmpCity.setEnteredDate(rs.getTimestamp(3));
					_tmpCity.setDisabledDate(rs.getTimestamp(4));
					MasterValue _tmpMasterValue = new MasterValue();
					_tmpMasterValue.setValueId(rs.getInt(5));
					_tmpMasterValue.setValue1(rs.getString(6));
					_tmpMasterValue.setValue2(rs.getString(7));
					_tmpMasterValue.setValue3(rs.getString(8));
					_tmpCity.setStateProvince(_tmpMasterValue);
					_tmpMasterValue = new MasterValue();
					_tmpMasterValue.setValueId(rs.getInt(9));
					_tmpMasterValue.setValue1(rs.getString(10));
					_tmpMasterValue.setValue2(rs.getString(11));
					_tmpMasterValue.setValue3(rs.getString(12));	
					_tmpCity.setCountry(_tmpMasterValue);
					cities.add(_tmpCity);
				} while (rs.next());
			}
			System.out.println("search city by id ");
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return cities;
	}

}