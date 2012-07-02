package com.lotrading.softlot.security.dao;
import java.util.List;

import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IResourceDao {

	/**
	 * 
	 * @param resource
	 * @throws Exception 
	 */
	public int createResource(Resource resource) throws Exception;

	/**
	 * 
	 * @param resource
	 * @throws Exception 
	 */
	public List<Resource> searchResource(Resource resource) throws Exception;

	/**
	 * 
	 * @param resource
	 * @throws Exception 
	 */
	public boolean updateResource(Resource resource) throws Exception;

	/**
	 * 
	 * @param asociated
	 * @param resourceRole
	 * @throws Exception 
	 */
	public List <Resource> loadResourceActions(ResourceRole resourceRole, boolean asociated) throws Exception;

	/**
	 * 
	 * @param resource
	 */
	public boolean updateResourceAction(ResourceRole resourceRol)throws Exception;

	public Resource loadResourceByUrl(Resource resource) throws Exception;
	
	public Resource loadResource(Resource resource) throws Exception;

}