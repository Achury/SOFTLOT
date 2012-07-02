package com.lotrading.softlot.security.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.dao.IResourceDao;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:02 a.m.
 */
public class ResourceDaoImpl extends DAOTemplate implements IResourceDao {

	private Log log = LogFactory.getLog(ResourceDaoImpl.class);

	public ResourceDaoImpl() {

	}

	/**
	 * 
	 * @param resource
	 * @throws Exception
	 */
	public List<Resource> searchResource(Resource resource) throws Exception {
		List<Resource> resources = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_resources_SEARCH(?,?)}";
			cst = conn.prepareCall(sql);
			if(resource.getName() != null){
				cst.setString(1, resource.getName());
			}else{
				cst.setString(1, "");
			}
			if(resource.getType() != null){
				cst.setString(2, resource.getType());
			}else{
				cst.setString(2, null);
			}
			rs = cst.executeQuery();

			if (rs.next()) {
				resources = new ArrayList<Resource>();
				do {
					Resource _tmpResource = new Resource();
					_tmpResource.setResourceId(rs.getInt(1));
					_tmpResource.setDescription(rs.getString(2));
					_tmpResource.setName(rs.getString(3));
					_tmpResource.setUrl(rs.getString(4));
					_tmpResource.setType(rs.getString(5));
					_tmpResource.setAction(rs.getString(6));
					_tmpResource.setEnteredDate(rs.getTimestamp(7));
					_tmpResource.setDisabledDate(rs.getTimestamp(8));
					resources.add(_tmpResource);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return resources;
	}

	/**
	 * 
	 * @param resource
	 * @throws Exception
	 */
	public int createResource(Resource resource) throws Exception {
		int created = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_resources_CREATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, resource.getName());
			cst.setString(2, resource.getDescription());
			cst.setString(3, resource.getUrl());
			cst.setString(4, resource.getType());
			cst.setString(5, resource.getAction());
			cst.setTimestamp(6, toTimeStamp(resource.getEnteredDate()));
			cst.registerOutParameter(7, Types.INTEGER);

			if (cst.executeUpdate() > 0) {
				created = cst.getInt(7);
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
	 * @param resource
	 * @throws Exception
	 */
	public boolean updateResource(Resource resource) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_resources_UPDATE(?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, resource.getResourceId());
			cst.setString(2, resource.getName());
			cst.setString(3, resource.getDescription());
			cst.setString(4, resource.getUrl());
			cst.setString(5, resource.getType());
			cst.setString(6, resource.getAction());
			cst.setTimestamp(7, toTimeStamp(resource.getEnteredDate()));
			if (resource.getDisabledDate() != null) {
				cst.setTimestamp(8, toTimeStamp(resource.getDisabledDate()));
			} else {
				cst.setDate(8, null);
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
	 * @param resource
	 */
	public boolean updateResourceAction(ResourceRole resourceRol) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_resources_UPDATE_RESOURCE_ACTION(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, resourceRol.getRoleId());
			cst.setInt(2, resourceRol.getResourceId());
			cst.setInt(3, resourceRol.getAction().getResourceId());	

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

	@Override
	public Resource loadResourceByUrl(Resource resource) throws Exception {
		Resource _tmpResource = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_resources_LOAD_by_URL(?)}";
			cst = conn.prepareCall(sql);

			cst.setString(1, resource.getUrl());

			rs = cst.executeQuery();

			if (rs.next()) {
				_tmpResource = new Resource();
				_tmpResource.setResourceId(rs.getInt(1));
				_tmpResource.setName(rs.getString(2));
				_tmpResource.setDescription(rs.getString(3));
				_tmpResource.setUrl(rs.getString(4));
				_tmpResource.setType(rs.getString(5));
				_tmpResource.setAction(rs.getString(6));
				_tmpResource.setEnteredDate(rs.getTimestamp(7));
				_tmpResource.setDisabledDate(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return _tmpResource;
	}
	
	@Override
	public Resource loadResource(Resource resource) throws Exception {
		Resource _tmpResource = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_resources_LOAD_RESOURCE(?)}";
			cst = conn.prepareCall(sql);

			cst.setInt(1, resource.getResourceId());

			rs = cst.executeQuery();

			if (rs.next()) {
				_tmpResource = new Resource();
				_tmpResource.setResourceId(rs.getInt(1));
				_tmpResource.setName(rs.getString(2));
				_tmpResource.setDescription(rs.getString(3));
				_tmpResource.setUrl(rs.getString(4));
				_tmpResource.setType(rs.getString(5));
				_tmpResource.setAction(rs.getString(6));
				_tmpResource.setEnteredDate(rs.getTimestamp(7));
				_tmpResource.setDisabledDate(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return _tmpResource;
	}
	
	@Override
	public List<Resource> loadResourceActions(ResourceRole resourceRole,
			boolean asociated) throws Exception {
		List<Resource> actions = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			if(resourceRole.getEmployeeId()>0){
				sql = "{call lotradingdb.sp_resources_LOAD_RESOURCE_ACTIONS(?,?,?)}";
				cst = conn.prepareCall(sql);
				cst.setInt(1, resourceRole.getResourceId());
				cst.setInt(2, resourceRole.getEmployeeId());
				cst.setBoolean(3, asociated);
			}else if(resourceRole.getRoleId()>0){
				sql = "{call lotradingdb.sp_resources_LOAD_ROLE_ACTIONS(?,?,?)}";
				cst = conn.prepareCall(sql);
				cst.setInt(1, resourceRole.getResourceId());
				cst.setInt(2, resourceRole.getRoleId());
				cst.setBoolean(3, asociated);
			}
			
			rs = cst.executeQuery();
			if (rs.next()) {
				actions = new ArrayList<Resource>();
				do {
					Resource _tmpResource = new Resource();
					_tmpResource.setName(rs.getString(1));
					_tmpResource.setDescription(rs.getString(2));
					_tmpResource.setUrl(rs.getString(3));
					_tmpResource.setType(rs.getString(4));
					_tmpResource.setAction(rs.getString(5));
					_tmpResource.setEnteredDate(rs.getTimestamp(6));
					_tmpResource.setDisabledDate(rs.getTimestamp(7));
					_tmpResource.setResourceId(rs.getInt(8));
					actions.add(_tmpResource);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return actions;
	}
}