package com.lotrading.softlot.businessPartners.dao;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IPartnerDeparmentInfoDao {

	/**
	 * 
	 * @param partnerDepartmentInfo
	 * @throws Exception 
	 */
	public boolean createDepartmentInfo(PartnerDepartmentInfo partnerDepartmentInfo) throws Exception;

	/**
	 * 
	 * @param partnerDepartmentInfo
	 * @throws Exception 
	 */
	public boolean updateDepartmentInfo(PartnerDepartmentInfo partnerDepartmentInfo) throws Exception;

}