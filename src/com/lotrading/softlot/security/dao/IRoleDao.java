package com.lotrading.softlot.security.dao;
import java.util.List;

import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IRoleDao {

	/**
	 * 
	 * @param role
	 * @throws Exception 
	 */
	public int createRole(Role role) throws Exception;

	/**
	 * 
	 * @param role
	 * @throws Exception 
	 */
	public int updateRole(Role role) throws Exception;

	/**
	 * 
	 * @param role
	 * @throws Exception 
	 */
	public List<Role> searchRole(Role role) throws Exception;

	/**
	 * 
	 * @param asociated
	 * @param role
	 * @throws Exception 
	 */
	public List loadRoleOptions(boolean asociated, Role role) throws Exception;

	/**
	 * 
	 * @param aosciated
	 * @param role
	 * @throws Exception 
	 */
	public List loadRoleResources(boolean asociated, Role role) throws Exception;
	
	/**
	 * 
	 * @param resource
	 * @throws Exception 
	 */
	public Resource loadRoleActions(Resource resource)throws Exception;

	/**
	 * 
	 * @param role
	 * @throws Exception 
	 */
	public boolean updateRoleOptions(Role role) throws Exception;

	/**
	 * 
	 * @param role
	 * @throws Exception 
	 */
	public boolean updateRoleResources(Role role) throws Exception;

	public boolean updateRoleActions(Resource _tmpResource) throws Exception;

	public boolean createRoleResource(Resource _tmpResource) throws Exception;

	public boolean deleteRoleResource(Resource _tmpResource) throws Exception;

}