package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlInlandCSDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlInlandCSLogic;

/**
 * @author Marlon Pineda
 * @version 1.0
 * @created 12-Jan-2012 12:00:00 AM
 */
public class BlInlandCSLogicImpl implements IBlInlandCSLogic {

	private IBlInlandCSDao blInlandCSDao;
	private Log log = LogFactory.getLog(BlInlandCSLogicImpl.class);
	
	public BlInlandCSLogicImpl(){
		
	}

	/**
	 * 
	 * @param blInlandCS
	 * @throws Exception 
	 */
	public int saveBlInlandCS(BlInlandCS blInlandCS) throws Exception{
		int _tmpReturnId = 0;
		
			try {
				if (blInlandCS == null)  return _tmpReturnId;	
				if (blInlandCS.getBlId()<=0)  return _tmpReturnId;	
				if(blInlandCS.getInlandCSId() <= 0){
					blInlandCS.setCreatedDate(new Date());
					_tmpReturnId = blInlandCSDao.createBlInlandCS(blInlandCS);
				}else if (blInlandCS.getInlandCSId()>0){
					if(blInlandCSDao.updateBlInlandCS(blInlandCS)){
						_tmpReturnId = blInlandCS.getInlandCSId();
					}
				}
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
			return _tmpReturnId;
		}
		
	/**
	 * 
	 * @param blInlandCSItems
	 * @throws Exception 
	 */
	public List<BlInlandCS> saveBlInlandCSItems(List<BlInlandCS> blInlandCSItems) throws Exception{
		if(blInlandCSItems != null){
		
			try {
				blInlandCSItems = blInlandCSDao.saveBlInlandCSItems(blInlandCSItems);				
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		
		return blInlandCSItems;
	}

	/**
	 * 
	 * @param blInlandCS
	 * @throws Exception 
	 */
	public boolean deleteBlInlandCS(BlInlandCS blInlandCS) throws Exception{
		boolean deleted = false;
		if(blInlandCS != null){
			try {
				deleted = blInlandCSDao.deleteBlInlandCS(blInlandCS);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IBlInlandCSDao getBlInlandCSDao() {
		return blInlandCSDao;
	}

	public void setBlInlandCSDao(IBlInlandCSDao blInlandCSDao) {
		this.blInlandCSDao = blInlandCSDao;
	}

}