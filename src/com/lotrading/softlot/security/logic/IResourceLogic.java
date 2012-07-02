package com.lotrading.softlot.security.logic;
import java.util.List;


import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IResourceLogic {

	/**
	 * 
	 * @param resource
	 */
	public int saveResource(Resource resource);

	/**
	 * 
	 * @param resource
	 * @throws Exception 
	 */
	public List<Resource> searchResource(Resource resource) throws Exception;

	/**
	 * 
	 * @param resource
	 */
	public boolean updateResourceAction(Resource resource);

	/**
	 * 
	 * @param resourceRole
	 * @param asociated
	 * @throws Exception 
	 */
	public List<Resource> loadResourceActions(ResourceRole resourceRole, boolean asociated) throws Exception;

	public Resource loadResourceByUrl(Resource resource) throws Exception;
	
	public Resource loadResource(Resource resource) throws Exception;

}