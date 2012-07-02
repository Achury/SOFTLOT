package com.lotrading.softlot.businessPartners.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.dao.IPartnerContactDao;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:59 a.m.
 */
public class PartnerContactDaoImpl extends DAOTemplate implements IPartnerContactDao {
	private Log log = LogFactory.getLog(PartnerContactDaoImpl.class);
	
	public PartnerContactDaoImpl(){

	}

	/**
	 * 
	 * @param partnerContact
	 */
	public int createContact(PartnerContact partnerContact)throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_contacts_CREATE(?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerContact.getPartnerDeptInfoId());
			cst.setString(2, partnerContact.getName());
			cst.setString(3, partnerContact.getTitle());

			if (partnerContact.getAddress() != null) {
				cst.setInt(4, partnerContact.getAddress().getAddressId());
			} else {
				cst.setInt(4, 0);
			}
			
			cst.setString(5, partnerContact.getEmail());
			cst.setString(6, partnerContact.getNotes());
			cst.setBoolean(7, partnerContact.isMain());
			cst.setInt(8, partnerContact.getEmployeeCreator());								
			cst.setTimestamp(9, toTimeStamp(partnerContact.getEnteredDate()));
			cst.registerOutParameter(10, Types.INTEGER);

			if (cst.executeUpdate() > 0) {
				createdId = cst.getInt(10);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return createdId;
	}

	/**
	 * 
	 * @param partnerContact
	 */
	public boolean updatePartnerContact(PartnerContact partnerContact) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_contacts_UPDATE(?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerContact.getContactId());
			cst.setInt(2, partnerContact.getPartnerDeptInfoId());
			cst.setString(3, partnerContact.getName());
			cst.setString(4, partnerContact.getTitle());

			if (partnerContact.getAddress() != null) {
				cst.setInt(5, partnerContact.getAddress().getAddressId());
			} else {
				cst.setInt(5, 0);
			}
			
			cst.setString(6, partnerContact.getEmail());
			cst.setString(7, partnerContact.getNotes());	
			cst.setBoolean(8, partnerContact.isMain());			
			cst.setInt(9, partnerContact.getEmployeeCreator());	
			cst.setTimestamp(10, toTimeStamp(partnerContact.getEnteredDate()));
			if (partnerContact.getDisableDate() != null){
				cst.setTimestamp(11, toTimeStamp(partnerContact.getDisableDate()));
			}else{
				cst.setDate(11, null);
			}
			

			if (cst.executeUpdate() > 0) {
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

	/**
	 * 
	 * @param partnerContact
	 */
	public List<Phone> loadContactPhones(PartnerContact partnerContact) throws Exception{
		List<Phone> contactPhones = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partner_contacts_LOAD_PHONES(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerContact.getContactId());			
			rs = cst.executeQuery();
			
			
			contactPhones = new ArrayList<Phone>();
			if (rs.next()) {				
				
				do {
					Phone _tmpPhone = new Phone();
					_tmpPhone.setPhoneId((rs.getInt(1)));
					
					// ********* begin fill phoneType data *********
					MasterValue phoneType = new MasterValue();
					phoneType.setValueId(rs.getInt(2));
					phoneType.setValue1(rs.getString(3));
					phoneType.setValue2(rs.getString(4));
					// ********* end fill phoneType data *********
					
					_tmpPhone.setType(phoneType);					
					_tmpPhone.setCountryCode(rs.getString(5));
					_tmpPhone.setAreaCode(rs.getString(6));		
					_tmpPhone.setPhoneNumber(rs.getString(7));
					_tmpPhone.setPhoneExtension(rs.getString(8));
					_tmpPhone.setEnteredDate(rs.getTimestamp(9));
					_tmpPhone.setDisabledDate(rs.getTimestamp(10));
					_tmpPhone.setMainPhone(rs.getBoolean(11));
					
					contactPhones.add(_tmpPhone);
				} while (rs.next());
			}
			
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return contactPhones;
	}

	@Override
	public boolean saveContactPhone(int phoneId, int contactId, boolean isMain) throws Exception {
		boolean saved = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_contacts_SAVE_CONTACT_PHONE(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, phoneId);
			cst.setInt(2, contactId);
			if (cst.executeUpdate() > 0) {
				saved = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return saved;
	}

	@Override
	public int getDeptInfoID(PartnerContact partnerContact) throws Exception {
		int deptInfoID = 0;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_contacts_shippickups_GET_DEPT_INFO_ID(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerContact.getPartnerId());
			cst.setInt(2, partnerContact.getDepartmentLotId());
			rs = cst.executeQuery();			
			
			if (rs.next()) {
				deptInfoID= rs.getInt(1);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return deptInfoID;
	}

}