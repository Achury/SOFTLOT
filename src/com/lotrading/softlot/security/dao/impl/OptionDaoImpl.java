package com.lotrading.softlot.security.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.dao.IOptionsDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class OptionDaoImpl extends DAOTemplate implements IOptionsDao {

	private Log log = LogFactory.getLog(OptionDaoImpl.class);

	public OptionDaoImpl() {

	}

	/**
	 * 
	 * @param option
	 * @throws Exception
	 */
	public List<Option> searchOptions(Option option) throws Exception {
		List<Option> options = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_options_SEARCH(?)}";
			cst = conn.prepareCall(sql);
			if(option.getName() != null){
				cst.setString(1, option.getName());
			}else{
				cst.setString(1, "");
			}
			rs = cst.executeQuery();
			if (rs.next()) {
				options = new ArrayList<Option>();
				do {
					Option _tmpOption = new Option();
					_tmpOption.setOptionId(rs.getInt(1));
					_tmpOption.setName(rs.getString(2));
					_tmpOption.setDescription(rs.getString(3));
					_tmpOption.setParent(rs.getBoolean(4));
					_tmpOption.setParentId(rs.getInt(5));
					_tmpOption.setOrder(rs.getInt(6));
					_tmpOption.setEnteredDate(rs.getTimestamp(7));
					_tmpOption.setDisabledDate(rs.getTimestamp(8));

					Resource _tmpResource = new Resource();
					_tmpResource.setResourceId(rs.getInt(9));
					_tmpOption.setResource(_tmpResource);
					options.add(_tmpOption);
				} while (rs.next());
			}
			rs.close();

		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return options;
	}

	/**
	 * 
	 * @param option
	 * @throws Exception
	 */
	public boolean createOption(Option option) throws Exception {
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_options_CREATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, option.getName());
			cst.setString(2, option.getDescription());
			if (option.getResource() != null) {
				cst.setInt(3, option.getResource().getResourceId());
			} else {
				cst.setInt(3, 0);
			}
			cst.setBoolean(4, option.isParent());
			cst.setInt(5, option.getParentId());
			cst.setInt(6, option.getOrder());
			cst.setTimestamp(7, toTimeStamp(option.getEnteredDate()));
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
	 * @param option
	 * @throws Exception
	 */
	public boolean updateOption(Option option) throws Exception {
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_options_UPDATE(?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, option.getOptionId());
			cst.setString(2, option.getName());
			cst.setString(3, option.getDescription());
			if (option.getResource() != null) {
				cst.setInt(4, option.getResource().getResourceId());
			} else {
				cst.setInt(4, 0);
			}
			cst.setBoolean(5, option.isParent());
			cst.setInt(6, option.getParentId());
			cst.setInt(7, option.getOrder());
			cst.setTimestamp(8, toTimeStamp(option.getEnteredDate()));
			if (option.getDisabledDate() != null) {
				cst.setTimestamp(9, toTimeStamp(option.getDisabledDate()));
			} else {
				cst.setDate(9, null);
			}
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
}