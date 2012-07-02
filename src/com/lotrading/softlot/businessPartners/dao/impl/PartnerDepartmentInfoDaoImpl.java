package com.lotrading.softlot.businessPartners.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.dao.IPartnerDeparmentInfoDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:00 a.m.
 */
public class PartnerDepartmentInfoDaoImpl extends DAOTemplate implements
		IPartnerDeparmentInfoDao {

	private Log log = LogFactory.getLog(PartnerDepartmentInfoDaoImpl.class);

	public PartnerDepartmentInfoDaoImpl() {

	}

	/**
	 * 
	 * @param partnerDepartmentInfo
	 * @throws Exception
	 */
	public boolean createDepartmentInfo(
			PartnerDepartmentInfo partnerDepartmentInfo) throws Exception {
		boolean created = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_department_CREATE(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerDepartmentInfo.getPartnerId());
			cst.setInt(2, partnerDepartmentInfo.getDepartmentLotId());			
			cst.setInt(3, partnerDepartmentInfo.getEmployeeRep());			
			cst.setString(4, partnerDepartmentInfo.getNotes());
			cst.setFloat(5, partnerDepartmentInfo.getMarkup());
			cst.setFloat(6, partnerDepartmentInfo.getMargin());
			if (cst.executeUpdate() > 0) {
				created = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return created;
	}

	/**
	 * 
	 * @param partnerDepartmentInfo
	 * @throws Exception
	 */
	public boolean updateDepartmentInfo(
			PartnerDepartmentInfo partnerDepartmentInfo) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_department_UPDATE(?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerDepartmentInfo.getPartnerDepartmentInfoId());
			cst.setInt(2, partnerDepartmentInfo.getPartnerId());
			cst.setInt(3, partnerDepartmentInfo.getDepartmentLotId());
			cst.setInt(4, partnerDepartmentInfo.getEmployeeRep());
			
			cst.setString(5, partnerDepartmentInfo.getNotes());
			cst.setFloat(6, partnerDepartmentInfo.getMarkup());
			cst.setFloat(7, partnerDepartmentInfo.getMargin());
			if(cst.executeUpdate() > 0){
				updated = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

}