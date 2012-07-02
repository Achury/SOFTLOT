package com.lotrading.softlot.security.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.dao.IRoleDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:03 a.m.
 */
public class RoleDaoImpl extends DAOTemplate implements IRoleDao {

	private Log log = LogFactory.getLog(RoleDaoImpl.class);

	public RoleDaoImpl() {

	}

	/**
	 * 
	 * @param role
	 * @throws Exception
	 */
	public int createRole(Role role) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_CREATE(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, role.getName());
			cst.setString(2, role.getDescription());
			cst.setTimestamp(3, toTimeStamp(role.getEnteredDate()));
			cst.registerOutParameter(4, Types.INTEGER);

			if (cst.executeUpdate() > 0) {
				createdId = cst.getInt(4);
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
	 * @param role
	 * @throws Exception
	 */
	public int updateRole(Role role) throws Exception {
		int updatedId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_UPDATE(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, role.getRoleId());
			cst.setString(2, role.getName());
			cst.setString(3, role.getDescription());
			cst.setTimestamp(4, toTimeStamp(role.getEnteredDate()));
			if (role.getDisabledDate() != null) {
				cst.setTimestamp(5, toTimeStamp(role.getDisabledDate()));
			} else {
				cst.setDate(5, null);
			}
			if (cst.executeUpdate() > 0) {
				updatedId = role.getRoleId();
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updatedId;
	}

	/**
	 * 
	 * @param role
	 * @throws Exception
	 */
	public List<Role> searchRole(Role role) throws Exception {
		List<Role> roles = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_SEARCH(?)}";
			cst = conn.prepareCall(sql);
			if(role.getName() != null){
				cst.setString(1, role.getName());
			}else{
				cst.setString(1, "");
			}
			rs = cst.executeQuery();

			if (rs.next()) {
				roles = new ArrayList<Role>();
				do {
					Role _tmpRole = new Role();
					_tmpRole.setRoleId(rs.getInt(1));
					_tmpRole.setName(rs.getString(2));
					_tmpRole.setDescription(rs.getString(3));
					_tmpRole.setEnteredDate(rs.getTimestamp(4));
					_tmpRole.setDisabledDate(rs.getTimestamp(5));
					roles.add(_tmpRole);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return roles;
	}

	/**
	 * 
	 * @param asociated
	 * @param role
	 * @throws Exception
	 */
	public List<Option> loadRoleOptions(boolean asociated, Role role)
			throws Exception {
		List<Option> options = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_LOAD_ROLE_OPTIONS(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setBoolean(1, asociated);
			cst.setInt(2, role.getRoleId());
			rs = cst.executeQuery();
			if (rs.next()) {
				options = new ArrayList<Option>();
				do {
					Option _tmpOption = new Option();
					_tmpOption.setOptionId(rs.getInt(1));
					_tmpOption.setName(rs.getString(2));
					_tmpOption.setDescription(rs.getString(3));
					_tmpOption.setResource(new Resource(rs.getInt(4)));
					_tmpOption.setParent(rs.getBoolean(5));
					_tmpOption.setParentId(rs.getInt(6));
					_tmpOption.setOrder(rs.getInt(7));
					_tmpOption.setEnteredDate(rs.getTimestamp(8));
					_tmpOption.setDisabledDate(rs.getTimestamp(9));
					options.add(_tmpOption);
				} while (rs.next());
			}
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
	 * @param aosciated
	 * @param role
	 * @throws Exception
	 */
	public List<Resource> loadRoleResources(boolean asociated, Role role)
			throws Exception {
		List<Resource> resources = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_LOAD_ROLE_RESOURCES(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setBoolean(1, asociated);
			cst.setInt(2, role.getRoleId());
			rs = cst.executeQuery();
			if (rs.next()) {
				resources = new ArrayList<Resource>();
				do {
					Resource _tmpResource = new Resource();
					_tmpResource.setResourceId(rs.getInt(1));
					_tmpResource.setName(rs.getString(2));
					_tmpResource.setDescription(rs.getString(3));
					_tmpResource.setUrl(rs.getString(4));
					_tmpResource.setType(rs.getString(5));
					_tmpResource.setAction(rs.getString(6));
					_tmpResource.setEnteredDate(rs.getTimestamp(7));
					_tmpResource.setDisabledDate(rs.getTimestamp(8));
					_tmpResource.setRoleId(role.getRoleId());
					resources.add(_tmpResource);	
				} while (rs.next());
			}
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
	public Resource loadRoleActions(Resource resource) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_LOAD_ROLE_ACTIONS(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, resource.getRoleId());
			cst.setInt(2, resource.getResourceId());
			rs = cst.executeQuery();
			if (rs.next()){
				resource.setStringActions(rs.getString(1));	
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return resource;
	}

	/**
	 * 
	 * @param role
	 * @throws Exception
	 */
	public boolean updateRoleOptions(Role role) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_UPDATE_ROLE_OPTIONS(?,?,?)}";
			List<String> _tmpOptions = new ArrayList<String>();
			_tmpOptions = role.getOptions();
			boolean delete = true;                 //para saber si se borran o no los datos. La idea es que borre al principio y ya.
			if(_tmpOptions!=null && !_tmpOptions.isEmpty()){
				for (String _tmpOption : _tmpOptions) {
					cst = conn.prepareCall(sql);
					cst.setInt(1, role.getRoleId());
					cst.setInt(2, Integer.valueOf(_tmpOption));
					cst.setBoolean(3, delete);
					if (cst.executeUpdate() <= 0) {
						return false;
					}
					delete = false;
				}
			}else{
				cst = conn.prepareCall(sql);
				cst.setInt(1, role.getRoleId());
				cst.setInt(2, 0);
				cst.setBoolean(3, delete);
				if (cst.executeUpdate() <= 0) {
					return false;
				}
			}
			updated = true;
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
	 * @param role
	 * @throws Exception 
	 */
	public boolean updateRoleResources(Role role) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			if (role.getResources().size() > 0) {
				conn = getConnection();
				String sql = "{call lotradingdb.sp_roles_UPDATE_ROLE_RESOURCES(?,?,?)}";
				List<String> _tmpResources = new ArrayList<String>();
				_tmpResources = role.getResources();
				boolean delete = true;                 //para saber sis se borran o no los datos. La idea es que borre al principio y ya.
				for (String _tmpResource : _tmpResources) {
					cst = conn.prepareCall(sql);
					cst.setInt(1, role.getRoleId());
					cst.setInt(2, Integer.valueOf(_tmpResource));
					cst.setBoolean(3, delete);
					if (cst.executeUpdate() <= 0) {
						return false;
					}
					delete = false;
				}
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
	public boolean updateRoleActions(Resource _tmpResource) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_UPDATE_ROLE_ACTIONS(?,?,?,?)}";
			List<String> _tmpActions = new ArrayList<String>();
			if(_tmpResource.getActions()!=null && !_tmpResource.getActions().isEmpty()){
				_tmpActions = _tmpResource.getActions();
				boolean delete = true;                 //para saber sis se borran o no los datos. La idea es que borre al principio y ya.
				for (String _tmpAction : _tmpActions) {
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpResource.getRoleId());
					cst.setInt(2, _tmpResource.getResourceId());
					cst.setInt(3, Integer.valueOf(_tmpAction));
					cst.setBoolean(4, delete);
					if (cst.executeUpdate() <= 0) {
						return false;
					}
					delete = false;
				}
			}else{
				cst = conn.prepareCall(sql);
				cst.setInt(1, _tmpResource.getRoleId());
				cst.setInt(2, _tmpResource.getResourceId());
				cst.setInt(3, 0);
				cst.setBoolean(4, true);
				cst.executeUpdate();
			}
			updated = true;
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

	@Override
	public boolean createRoleResource(Resource _tmpResource) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_CREATE_ROLE_RESOURCE(?,?)}";
			
			cst = conn.prepareCall(sql);
			cst.setInt(1, _tmpResource.getRoleId());
			cst.setInt(2, _tmpResource.getResourceId());
			if (cst.executeUpdate() <= 0) {
				return false;
			}
			updated = true;
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

	@Override
	public boolean deleteRoleResource(Resource _tmpResource) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_roles_DELETE_ROLE_RESOURCE(?,?)}";
			
			cst = conn.prepareCall(sql);
			cst.setInt(1, _tmpResource.getRoleId());
			cst.setInt(2, _tmpResource.getResourceId());
			if (cst.executeUpdate() <= 0) {
				return false;
			}
			updated = true;
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}
}