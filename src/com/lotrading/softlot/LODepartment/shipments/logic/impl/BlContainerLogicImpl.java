package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlContainerDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlContainerLogic;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 12-Jan-2012 12:00:00 AM
 */
public class BlContainerLogicImpl implements IBlContainerLogic {

	private IBlContainerDao blContainerDao;
	private Log log = LogFactory.getLog(BlContainerLogicImpl.class);
	
	public BlContainerLogicImpl(){

	}

	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public List<BlContainer> saveBlContainers(List<BlContainer> blContainers) throws Exception{
		if(blContainers != null){
		
			try {
				blContainers = blContainerDao.saveBlContainers(blContainers);				
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		
		return blContainers;
	}

	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public boolean deleteBlContainer(BlContainer blContainer) throws Exception{
		boolean deleted = false;
		if(blContainer != null){
			try {
				deleted = blContainerDao.deleteBlContainer(blContainer);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}
	
	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public BlContainer loadBlContainer(BlContainer blContainers) throws Exception{
		if(blContainers != null){
			try {
				blContainers = blContainerDao.loadBlContainer(blContainers);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
			}
		}
		return blContainers;
	}

	public IBlContainerDao getBlContainerDao() {
		return blContainerDao;
	}

	public void setBlContainerDao(IBlContainerDao blContainerDao) {
		this.blContainerDao = blContainerDao;
	}

}