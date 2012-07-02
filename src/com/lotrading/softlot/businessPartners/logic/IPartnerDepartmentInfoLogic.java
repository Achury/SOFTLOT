package com.lotrading.softlot.businessPartners.logic;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IPartnerDepartmentInfoLogic {

	/**
	 * 
	 * @param partnerDepartmentInfo
	 * @throws Exception 
	 */
	public boolean saveDepartmentInfo(PartnerDepartmentInfo partnerDepartmentInfo) throws Exception;

}