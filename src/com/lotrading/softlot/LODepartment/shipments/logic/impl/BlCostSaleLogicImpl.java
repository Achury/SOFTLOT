package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlCostSaleDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlCostSaleLogic;

/**
 * @author Marlon Pineda
 * @version 1.0
 * @created 12-Jan-2012 12:00:00 AM
 */
public class BlCostSaleLogicImpl implements IBlCostSaleLogic {

	private IBlCostSaleDao blCostSaleDao;
	private Log log = LogFactory.getLog(BlCostSaleLogicImpl.class);
	
	public BlCostSaleLogicImpl(){

	}
	
	/**
	 * 
	 * @param blCostSale
	 * @throws Exception 
	 */
	public List<BlCostSale> saveBlCostSale(List <BlCostSale> blCostSale) throws Exception{		
		if(blCostSale != null){
			try {
					blCostSaleDao.saveBlCostSale(blCostSale);					
					
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blCostSale;
	}

	/**
	 * 
	 * @param blCostSale
	 * @throws Exception 
	 */
	public boolean deleteBlCostSale(BlCostSale blCostSale) throws Exception{
		boolean deleted = false;
		if(blCostSale != null){
			try {
				deleted = blCostSaleDao.deleteBlCostSale(blCostSale);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IBlCostSaleDao getBlCostSaleDao() {
		return blCostSaleDao;
	}

	public void setBlCostSaleDao(IBlCostSaleDao blCostSaleDao) {
		this.blCostSaleDao = blCostSaleDao;
	}

}