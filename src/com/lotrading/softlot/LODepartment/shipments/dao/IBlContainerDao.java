package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;
import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;



/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 9-March-2012 12:00:00 AM
 */
public interface IBlContainerDao {

	/**
	 * 
	 * @param blContainer
	 * @throws Exception 
	 */
	public int createBlContainer(BlContainer blContainer) throws Exception;

	/**
	 * 
	 * @param blContainer
	 */
	public boolean updateBlContainer(BlContainer blContainer) throws Exception;
	
	/**
	 * 
	 * @param List<BlContainer>
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
	 */
	public BlContainer loadBlContainer(BlContainer blContainer)throws Exception;


}