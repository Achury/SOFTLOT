package com.lotrading.softlot.security.logic.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.dao.IRoleDao;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IRoleLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:03 a.m.
 */
public class RoleLogicImpl implements IRoleLogic {

	private Log log = LogFactory.getLog(RoleLogicImpl.class);
	private IRoleDao roleDao;

	public RoleLogicImpl() {

	}

	/**
	 * 
	 * @param role
	 * @throws Exception
	 */
	public int saveRole(Role role) throws Exception {
		int _tmpReturn = 0;
		try {
			if (role == null) {
				return _tmpReturn;
			}
			if (role.getRoleId() <= 0) {
				role.setEnteredDate(new Date());
				_tmpReturn = roleDao.createRole(role);
			} else if (role.getRoleId() > 0) {
				_tmpReturn = roleDao.updateRole(role);
				roleDao.updateRoleOptions(role);
				//roleDao.updateRoleResources(role);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	/**
	 * 
	 * @param role
	 * @throws Exception
	 */
	public List<Role> searchRole(Role role) throws Exception {
		List<Role> roles = null;
		try {
			if (role != null) {
				roles = roleDao.searchRole(role);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return roles;
	}

	/**
	 * 
	 * @param asociated
	 * @param role
	 */
	public List<Resource> loadRoleResources(boolean asociated, Role role) throws Exception {
		List<Resource> roles = null;
		try {
			if (role != null) {
				roles = roleDao.loadRoleResources(asociated, role);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return roles;
	}
	
	public Resource loadRoleActions(Resource resource) throws Exception{
		try {
			if(resource != null){
				resource = roleDao.loadRoleActions(resource);
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}		
		return resource;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRoleDao(IRoleDao newVal) {
		roleDao = newVal;
	}

	/**
	 * 
	 * @param role
	 */
	public boolean updateRoleResources(Role role) {
		return false;
	}

	/**
	 * 
	 * @param asociated
	 * @param role
	 */
	public List loadRoleOptions(boolean asociated, Role role) throws Exception {
		List<Option> options = null;
		try {
			if (role != null) {
				options = roleDao.loadRoleOptions(asociated, role);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return options;
	}

	/**
	 * 
	 * @param role
	 */
	public boolean updateRoleOptions(Role role) {
		return false;
	}

	@Override
	public boolean saveResourceRole(Resource _tmpResource, boolean update) throws Exception {
		boolean _tmpResult = false;
		try {
			if(!update){
				_tmpResult = roleDao.createRoleResource(_tmpResource);
			}else{
				_tmpResult = true;
			}
			if(_tmpResult)
				_tmpResult = roleDao.updateRoleActions(_tmpResource);
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpResult;
	}

	@Override
	public boolean deleteResourceRole(Resource _tmpResource) throws Exception {
		boolean _tmpResult = false;
		try {
			_tmpResource.setActions(null);
			_tmpResult = roleDao.updateRoleActions(_tmpResource);
			if(_tmpResult)
				_tmpResult = roleDao.deleteRoleResource(_tmpResource);
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpResult;
	}

}