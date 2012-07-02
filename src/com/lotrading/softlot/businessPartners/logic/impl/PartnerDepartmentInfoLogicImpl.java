package com.lotrading.softlot.businessPartners.logic.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.IPartnerDeparmentInfoDao;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.logic.IPartnerDepartmentInfoLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:00 a.m.
 */
public class PartnerDepartmentInfoLogicImpl implements IPartnerDepartmentInfoLogic {

	private Log log = LogFactory.getLog(PartnerDepartmentInfoLogicImpl.class);
	private IPartnerDeparmentInfoDao partnerDepartmentInfoDao;

	public PartnerDepartmentInfoLogicImpl(){

	}

	/**
	 * 
	 * @param partnerDepartmentInfo
	 * @throws Exception 
	 */
	public boolean saveDepartmentInfo(PartnerDepartmentInfo partnerDepartmentInfo) throws Exception{
		boolean _tmpReturn = false;
		try {
			if(partnerDepartmentInfo == null){
				return false;
			}
			if(partnerDepartmentInfo.getPartnerDepartmentInfoId() <= 0){
				_tmpReturn = partnerDepartmentInfoDao.createDepartmentInfo(partnerDepartmentInfo);
			}else{
				_tmpReturn = partnerDepartmentInfoDao.updateDepartmentInfo(partnerDepartmentInfo);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	public IPartnerDeparmentInfoDao getPartnerDepartmentInfoDao(){
		return partnerDepartmentInfoDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPartnerDepartmentInfoDao(IPartnerDeparmentInfoDao newVal){
		partnerDepartmentInfoDao = newVal;
	}

}