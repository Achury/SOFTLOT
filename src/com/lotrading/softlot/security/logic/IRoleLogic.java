package com.lotrading.softlot.security.logic;
import java.util.List;

import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IRoleLogic {

	/**
	 * 
	 * @param role
	 * @throws Exception 
	 */
	public int saveRole(Role role) throws Exception;

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
	 */
	public List<Resource> loadRoleResources(boolean asociated, Role role) throws Exception;
	
	public Resource loadRoleActions(Resource resource) throws Exception;

	/**
	 * 
	 * @param role
	 */
	public boolean updateRoleResources(Role role);

	/**
	 * 
	 * @param asociated
	 * @param role
	 */
	public List loadRoleOptions(boolean asociated, Role role) throws Exception;

	/**
	 * 
	 * @param role
	 */
	public boolean updateRoleOptions(Role role);

	public boolean saveResourceRole(Resource _tmpResource, boolean update) throws Exception;

	public boolean deleteResourceRole(Resource _tmpResource) throws Exception;

}