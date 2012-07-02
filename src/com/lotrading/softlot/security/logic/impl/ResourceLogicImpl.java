package com.lotrading.softlot.security.logic.impl;
import java.util.List;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.dao.IResourceDao;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;
import com.lotrading.softlot.security.logic.IResourceLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:02 a.m.
 */
public class ResourceLogicImpl implements IResourceLogic {

	private Log log = LogFactory.getLog(ResourceLogicImpl.class);
	private IResourceDao resourceDao;

	public ResourceLogicImpl(){

	}

	/**
	 * 
	 * @param resource
	 */
	public int saveResource(Resource resource){
		int _tmpReturn = 0;
		try {
			if(resource == null){
				return 0;
			}
			if(resource.getResourceId() <= 0){
				resource.setEnteredDate(new Date());
				_tmpReturn = resourceDao.createResource(resource);
			}else if(resource.getResourceId() > 0){
				if(resourceDao.updateResource(resource)){
					_tmpReturn = resource.getResourceId();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return _tmpReturn;
	}

	public IResourceDao getResourceDao(){
		return resourceDao;
	}

	/**
	 * 
	 * @param resource
	 * @throws Exception 
	 */
	public List<Resource> searchResource(Resource resource) throws Exception{
		List<Resource> resources = null;
		try {
			if(resource != null){
				resources = resourceDao.searchResource(resource);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return resources;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResourceDao(IResourceDao newVal){
		resourceDao = newVal;
	}

	/**
	 * 
	 * @param resource
	 */
	public boolean updateResourceAction(Resource resource){
		boolean _tmpReturn = false;
		try {
			if(resource != null){
				_tmpReturn = resourceDao.updateResource(resource);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return _tmpReturn;
	}
	
	@Override
	public Resource loadResourceByUrl(Resource resource) throws Exception {
		Resource _tmpResource = null;
		try {
			_tmpResource = resourceDao.loadResourceByUrl(resource);
		} catch (Exception e) {
			//TODO
		}
		return _tmpResource;
	}
	
	@Override
	public Resource loadResource(Resource resource) throws Exception {
		Resource _tmpResource = null;
		try {
			_tmpResource = resourceDao.loadResource(resource);
		} catch (Exception e) {
			//TODO
		}
		return _tmpResource;
	}
	
	/**
	 * 
	 * @param resourceRole
	 * @param asociated
	 */
	public Resource loadResourceAction(ResourceRole resourceRole, boolean asociated){
		return null;
	}

	/**
	 * 
	 * @param resourceRole
	 * @param asociated
	 */
	public List<Resource> loadResourceActions(ResourceRole resourceRole, boolean asociated) throws Exception{
		List<Resource> _tmpActions = null;
		try {
			_tmpActions = resourceDao.loadResourceActions(resourceRole, asociated);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _tmpActions;
	}
}