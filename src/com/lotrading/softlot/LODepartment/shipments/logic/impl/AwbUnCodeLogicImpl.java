package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbUnCodeDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbUnCodeLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbUnCodeLogicImpl implements IAwbUnCodeLogic {

	private IAwbUnCodeDao awbUnCodeDao;
	private Log log = LogFactory.getLog(AwbUnCodeLogicImpl.class);
	
	public AwbUnCodeLogicImpl(){

	}
	
	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public List<AwbUnCode> saveUnCode(List<AwbUnCode> awbUnCodes) throws Exception{
		if(awbUnCodes != null){
			try {
				List<AwbUnCode> _tmpUnCodes = new ArrayList<AwbUnCode>();
				for(AwbUnCode _tmpUnCode : awbUnCodes){
					if(_tmpUnCode.isEmpty()){
						_tmpUnCodes.add(_tmpUnCode);
					}
				}
				awbUnCodes.removeAll(_tmpUnCodes);
				_tmpUnCodes = null;
				awbUnCodeDao.saveUnCodes(awbUnCodes);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbUnCodes;
	}

	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public boolean deleteUnCode(AwbUnCode awbUnCode) throws Exception{
		boolean deleted = false;
		if(awbUnCode != null){
			try {
				deleted = awbUnCodeDao.deleteUnCode(awbUnCode);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbUnCodeDao getAwbUnCodeDao() {
		return awbUnCodeDao;
	}

	public void setAwbUnCodeDao(IAwbUnCodeDao awbUnCodeDao) {
		this.awbUnCodeDao = awbUnCodeDao;
	}

}