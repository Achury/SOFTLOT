package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 9-March-2012 12:00:00 AM
 */
public interface IBlContainerLogic {

	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public List<BlContainer> saveBlContainers(List<BlContainer> blContainers) throws Exception;

	/**
	 * 
	 * @param blContainer
	 */
	public boolean deleteBlContainer(BlContainer blContainer) throws Exception;
	
	
	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public BlContainer loadBlContainer(BlContainer blContainer) throws Exception;
	
	

}