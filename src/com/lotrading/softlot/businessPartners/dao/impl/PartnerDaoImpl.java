package com.lotrading.softlot.businessPartners.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.dao.IPartnerDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 11:00:00 a.m.
 */
public class PartnerDaoImpl extends DAOTemplate implements IPartnerDao {
	private Log log = LogFactory.getLog(PartnerDaoImpl.class);

	public PartnerDaoImpl() {

	}

	/**
	 * 
	 * @param partner
	 */
	public int createPartner(Partner partner) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partners_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, partner.getName());
			cst.setString(2, partner.getCode());

			if (partner.getAddress() != null) {
				cst.setInt(3, partner.getAddress().getAddressId());
			} else {
				cst.setInt(3, 0);
			}

			if (partner.getPhone() != null) {
				cst.setInt(4, partner.getPhone().getPhoneId());
			} else {
				cst.setInt(4, 0);
			}

			if (partner.getFax() != null) {
				cst.setInt(5, partner.getFax().getPhoneId());
			} else {
				cst.setInt(5, 0);
			}

			cst.setString(6, partner.getWebsite());
			cst.setInt(7, partner.getStatus().getValueId());

			if (partner.getPaymentTerm() != null) {
				cst.setInt(8, partner.getPaymentTerm().getValueId());
			} else {
				cst.setInt(8, 0);
			}

			cst.setString(9, partner.getNotes());
			cst.setString(10, partner.getInvoiceNotes());
			cst.setBoolean(11, partner.isClient());
			cst.setBoolean(12, partner.isSupplier());
			cst.setString(13, partner.getTaxId());
			cst.setInt(14, partner.getLanguage().getValueId());

			if (partner.getTruckCompany() != null) {
				cst.setInt(15, partner.getTruckCompany().getValueId());
			} else {
				cst.setInt(15, 0);
			}

			cst.setBoolean(16, partner.isInformFinalDest());
			cst.setInt(17, partner.getEmployeeCreator().getEmployeeId());
			cst.setInt(18, partner.getEmployeeUpdate().getEmployeeId());
			cst.setTimestamp(19, toTimeStamp(partner.getEnteredDate()));
			cst.setTimestamp(20, toTimeStamp(partner.getUpdatedDate()));
			cst.setBoolean(21, partner.isRegUSA());
			cst.setBoolean(22, partner.isRegCOL());
			cst.setBoolean(23, partner.isRegGER());
			cst.setString(24, partner.getCustomerNumber());
			cst.registerOutParameter(25, Types.INTEGER);
			
			if (cst.executeUpdate() > 0) {
				createdId = cst.getInt(25);
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
	 * @param partner
	 */
	public boolean updatePartner(Partner partner) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partners_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partner.getPartnerId());
			cst.setString(2, partner.getName());
			cst.setString(3, partner.getCode());

			if (partner.getAddress() != null) {
				cst.setInt(4, partner.getAddress().getAddressId());
			} else {
				cst.setInt(4, 0);
			}

			if (partner.getPhone() != null) {
				cst.setInt(5, partner.getPhone().getPhoneId());
			} else {
				cst.setInt(5, 0);
			}

			if (partner.getFax() != null) {
				cst.setInt(6, partner.getFax().getPhoneId());
			} else {
				cst.setInt(6, 0);
			}

			cst.setString(7, partner.getWebsite());
			cst.setInt(8, partner.getStatus().getValueId());

			if (partner.getPaymentTerm() != null) {
				cst.setInt(9, partner.getPaymentTerm().getValueId());
			} else {
				cst.setInt(9, 0);
			}

			cst.setString(10, partner.getNotes());
			cst.setString(11, partner.getInvoiceNotes());
			cst.setBoolean(12, partner.isClient());
			cst.setBoolean(13, partner.isSupplier());
			cst.setString(14, partner.getTaxId());
			cst.setInt(15, partner.getLanguage().getValueId());

			if (partner.getTruckCompany() != null) {
				cst.setInt(16, partner.getTruckCompany().getValueId());
			} else {
				cst.setInt(16, 0);
			}

			cst.setBoolean(17, partner.isInformFinalDest());
			cst.setInt(18, partner.getEmployeeCreator().getEmployeeId());
			cst.setInt(19, partner.getEmployeeUpdate().getEmployeeId());
			cst.setTimestamp(20, toTimeStamp(partner.getEnteredDate()));
			cst.setTimestamp(21, toTimeStamp(partner.getUpdatedDate()));
			cst.setBoolean(22, partner.isRegUSA());
			cst.setBoolean(23, partner.isRegCOL());
			cst.setBoolean(24, partner.isRegGER());
			cst.setString(25, partner.getCustomerNumber());
			cst.setBoolean(26, partner.isSearchAndInspConsent());
			
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
	 * @param partner
	 */
	public List<Partner> searchPartner(Partner partner) throws Exception {
		List<Partner> partners = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partners_SEARCH(?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if (partner != null) {
				if (partner.getStatus() != null
						&& partner.getStatus().getValueId() > 0) {
					cst.setInt(1, partner.getStatus().getValueId());
				} else {
					cst.setInt(1, 0);
				}

				if (partner.getCode() != "") {
					cst.setString(2, partner.getCode());
				} else {
					cst.setString(2, null);
				}

				if (partner.getName() != "") {
					cst.setString(3, partner.getName());
				} else {
					cst.setString(3, null);
				}

				if (partner.getContactName() != "") {
					cst.setString(4, partner.getContactName());
				} else {
					cst.setString(4, null);
				}
				
				if (partner.getAddress() != null 
					&& partner.getAddress().getCity() != null
					&& partner.getAddress().getCity().getCityId() > 0) {
						cst.setInt(5, partner.getAddress().getCity().getCityId());
				} else {
					cst.setInt(5, 0);
				}
				if (partner.getAddress() != null 
					&& partner.getAddress().getCity() != null
					&& partner.getAddress().getCity().getStateProvince() != null
					&& partner.getAddress().getCity().getStateProvince().getValueId() > 0) {
						cst.setInt(6, partner.getAddress().getCity().getStateProvince().getValueId());
				} else {
					cst.setInt(6, 0);
				}
				if (partner.getAddress() != null 
					&& partner.getAddress().getCity() != null
					&& partner.getAddress().getCity().getCountry() != null
					&& partner.getAddress().getCity().getCountry().getValueId() > 0) {
						cst.setInt(7, partner.getAddress().getCity().getCountry().getValueId());
				} else {
					cst.setInt(7, 0);
				}
				if (partner.getNotes() != "") {
					cst.setString(8, partner.getNotes());
				} else {
					cst.setString(8, null);
				}								
				cst.setBoolean(9, partner.isClient());
				cst.setBoolean(10, partner.isSupplier());
				if(partner.getEmployeeUpdate() != null){
					cst.setInt(11, partner.getEmployeeUpdate().getEmployeeId());
				}else{
					cst.setInt(11, 0);
				}
			}
			
			
			rs = cst.executeQuery();
			if (rs.next()) {				
				partners = new ArrayList<Partner>();
				do {
					Partner _tmpPartner = new Partner();
					_tmpPartner.setPartnerId(rs.getInt(1));
					_tmpPartner.setName(rs.getString(2));
					_tmpPartner.setCode(rs.getString(3));

					// ********* begin fill Address data *********					
					_tmpPartner.getAddress().setAddressId(rs.getInt(4));
					_tmpPartner.getAddress().setAddress(rs.getString(5));
					_tmpPartner.getAddress().setPostalCode(rs.getString(6));
					// ********* end fill address data *********

					// ********* begin fill city data *********					
					_tmpPartner.getAddress().getCity().setCityId(rs.getInt(7));
					_tmpPartner.getAddress().getCity().setName(rs.getString(8));
					// ********* end fill city data *********

					// ********* begin fill state data *********					
					_tmpPartner.getAddress().getCity().getStateProvince().setValueId(rs.getInt(9));
					_tmpPartner.getAddress().getCity().getStateProvince().setValue1(rs.getString(10));
					// ********* end fill state data *********

					// ********* begin fill country data *********					
					_tmpPartner.getAddress().getCity().getCountry().setValueId(rs.getInt(11));
					_tmpPartner.getAddress().getCity().getCountry().setValue1(rs.getString(12));
					// ********* end fill country data *********					

					// ********* begin fill phone data *********					
					_tmpPartner.getPhone().setPhoneId(rs.getInt(13));
					_tmpPartner.getPhone().setCountryCode(rs.getString(14));
					_tmpPartner.getPhone().setAreaCode(rs.getString(15));
					_tmpPartner.getPhone().setPhoneNumber(rs.getString(16));
					_tmpPartner.getPhone().setPhoneExtension(rs.getString(17));
					// ********* end fill phone data *********					

					// ********* begin fill fax data *********					
					_tmpPartner.getFax().setPhoneId(rs.getInt(18));
					_tmpPartner.getFax().setCountryCode(rs.getString(19));
					_tmpPartner.getFax().setAreaCode(rs.getString(20));
					_tmpPartner.getFax().setPhoneNumber(rs.getString(21));
					_tmpPartner.getFax().setPhoneExtension(rs.getString(22));
					// ********* end fill fax data *********
					
					_tmpPartner.setWebsite(rs.getString(23));

					// ********* begin fill status data *********					
					_tmpPartner.getStatus().setValueId(rs.getInt(24));
					_tmpPartner.getStatus().setValue1(rs.getString(25));
					// ********* end fill status data *********
					
					// ********* begin fill payment terms data *********					
					_tmpPartner.getPaymentTerm().setValueId(rs.getInt(26));
					_tmpPartner.getPaymentTerm().setValue1(rs.getString(27));
					// ********* end fill payment terms data *********
					
					_tmpPartner.setNotes(rs.getString(28));
					_tmpPartner.setInvoiceNotes(rs.getString(29));
					_tmpPartner.setClient(rs.getBoolean(30));
					_tmpPartner.setSupplier(rs.getBoolean(31));
					_tmpPartner.setTaxId(rs.getString(32));
					
					// ********* begin fill language data *********					
					_tmpPartner.getLanguage().setValueId(rs.getInt(33));
					_tmpPartner.getLanguage().setValue1(rs.getString(34));					
					// ********* end fill language data *********	
					
					// ********* begin fill truck company data *********					
					_tmpPartner.getTruckCompany().setValueId(rs.getInt(35));
					_tmpPartner.getTruckCompany().setValue1(rs.getString(36));
					_tmpPartner.getTruckCompany().setValue2(rs.getString(37));
					// ********* end fill truck company data *********					
					
					_tmpPartner.setInformFinalDest(rs.getBoolean(38));					
					_tmpPartner.getEmployeeCreator().setEmployeeId(rs.getInt(39));
					_tmpPartner.getEmployeeUpdate().setEmployeeId(rs.getInt(40));
					_tmpPartner.getEmployeeCreator().setFirstName(rs.getString(41));
					_tmpPartner.getEmployeeCreator().setLastName(rs.getString(42));
					_tmpPartner.getEmployeeUpdate().setFirstName(rs.getString(43));
					_tmpPartner.getEmployeeUpdate().setLastName(rs.getString(44));
					_tmpPartner.setEnteredDate(rs.getTimestamp(45));
					_tmpPartner.setUpdatedDate(rs.getTimestamp(46));
					_tmpPartner.setRegUSA(rs.getBoolean(47));
					_tmpPartner.setRegCOL(rs.getBoolean(48));
					_tmpPartner.setRegGER(rs.getBoolean(49));
					_tmpPartner.setCustomerNumber(rs.getString(50));
					_tmpPartner.setSearchAndInspConsent(rs.getBoolean(51));
					partners.add(_tmpPartner);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return partners;
	}

	/**
	 * 
	 * @param partner
	 */
	public List<PartnerContact> loadPartnerContacts(Partner partner) throws Exception {
		List<PartnerContact> partnerContacts = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partners_LOAD_PARTNER_CONTACTS(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partner.getPartnerId());
			cst.setInt(2, partner.getDepartmentLotId());
			rs = cst.executeQuery();			
			
			partnerContacts = new ArrayList<PartnerContact>();
			if (rs.next()) {				
				do {
					PartnerContact _tmpPartnerContact = new PartnerContact();
					_tmpPartnerContact.setContactId((rs.getInt(1)));
					_tmpPartnerContact.setName(rs.getString(2));
					_tmpPartnerContact.setTitle(rs.getString(3));
					
					// ********* begin fill address data *********
					_tmpPartnerContact.getAddress().setAddressId(rs.getInt(4));
					_tmpPartnerContact.getAddress().setAddress(rs.getString(5));
					_tmpPartnerContact.getAddress().setPostalCode(rs.getString(6));
					_tmpPartnerContact.getAddress().getCity().setCityId(rs.getInt(7));
					_tmpPartnerContact.getAddress().getCity().setName((rs.getString(8)));
					_tmpPartnerContact.getAddress().getCity().getStateProvince().setValueId(rs.getInt(9));
					_tmpPartnerContact.getAddress().getCity().getStateProvince().setValue1((rs.getString(10)));
					_tmpPartnerContact.getAddress().getCity().getCountry().setValueId(rs.getInt(11));
					_tmpPartnerContact.getAddress().getCity().getCountry().setValue1((rs.getString(12)));
					
					// ********* end fill address data *********
					
					
					_tmpPartnerContact.setEmail(rs.getString(13));
					_tmpPartnerContact.setNotes(rs.getString(14));
					_tmpPartnerContact.setMain(rs.getBoolean(15));
					_tmpPartnerContact.setEnteredDate( rs.getTimestamp(16));
					_tmpPartnerContact.setDisableDate( rs.getTimestamp(17));
					_tmpPartnerContact.setPartnerDeptInfoId( rs.getInt(18));
					_tmpPartnerContact.setEmployeeCreator(rs.getInt(19));
					_tmpPartnerContact.setDepartmentLotId(rs.getInt(20));
					_tmpPartnerContact.setPartnerId(rs.getInt(21));
					
					
					sql = "{call lotradingdb.sp_partner_contacts_LOAD_PHONES(?)}";
					cst = null;
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpPartnerContact.getContactId());
					ResultSet rs2 = null;
					rs2 = cst.executeQuery();
					String phone = "";
					if(rs2.next()){
						do {
							phone = phone + "(";
							if(rs2.getString(5) != null && !rs2.getString(5).isEmpty()){
								phone = phone + rs2.getString(5) ;
							}
							if(rs2.getString(6) != null && !rs2.getString(6).isEmpty()){
								phone = phone + rs2.getString(6);
							}
							phone = phone + ")";
							if(rs2.getString(7) != null && !rs2.getString(7).isEmpty()){
								phone = phone + rs2.getString(7);
							}
							if(rs2.getString(8) != null && !rs2.getString(8).isEmpty()){
								phone = phone + " ext:" + rs2.getString(8);
							}
							phone = phone +" / ";
						} while (rs2.next());
					}
					_tmpPartnerContact.setConcatenatedPhones(phone);
					
					partnerContacts.add(_tmpPartnerContact);
					
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return partnerContacts;

	}

	/**
	 * 
	 * @param partner
	 */
	public List<CourierAccount> loadCourierAccounts(Partner partner) throws Exception{
		List<CourierAccount> courierAccounts = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partners_LOAD_COURIER_ACCOUNTS(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partner.getPartnerId());
			cst.setInt(2, partner.getDepartmentLotId());
			rs = cst.executeQuery();	
			
			if (rs.next()) {				
				courierAccounts = new ArrayList<CourierAccount>();
				do {
					CourierAccount _tmpCourierAccount = new CourierAccount();
					_tmpCourierAccount.setCourierAccountId((rs.getInt(1)));
					_tmpCourierAccount.setPartnerDeptInfo(rs.getInt(2));
					_tmpCourierAccount.setAccountNumber(rs.getString(3));
					_tmpCourierAccount.setMain(rs.getBoolean(4));
					_tmpCourierAccount.getCourier().setValueId( rs.getInt(5));
					_tmpCourierAccount.getCourier().setValue1( rs.getString(6));
					
					courierAccounts.add(_tmpCourierAccount);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return courierAccounts;
	}

	/**
	 * 
	 * @param partner
	 */
	public List<ShipPickUp> loadShipPickup(Partner partner) throws Exception {
		List<ShipPickUp> shipPickUps = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partners_LOAD_SHIP_PICKUPS(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partner.getPartnerId());
			cst.setString(2, "");
			rs = cst.executeQuery();			
			
			shipPickUps = new ArrayList<ShipPickUp>();
			if (rs.next()) {
								
				do {
					ShipPickUp _tmpShipPickUp = new ShipPickUp();
					_tmpShipPickUp.setShipPickUpId((rs.getInt(1)));
					_tmpShipPickUp.setName(rs.getString(2));
					
					// ********* begin fill address data *********					
					_tmpShipPickUp.getAddress().setAddressId(rs.getInt(3));
					_tmpShipPickUp.getAddress().setAddress(rs.getString(4));
					_tmpShipPickUp.getAddress().setPostalCode(rs.getString(5));
					// ********* end fill address data *********
					
					// ********* begin fill city data *********
					_tmpShipPickUp.getAddress().getCity().setCityId(rs.getInt(6));
					_tmpShipPickUp.getAddress().getCity().setName(rs.getString(7));
					// ********* end fill city data *********
					
					// ********* begin fill state data *********
					_tmpShipPickUp.getAddress().getCity().getStateProvince().setValueId(rs.getInt(8));
					_tmpShipPickUp.getAddress().getCity().getStateProvince().setValue1(rs.getString(9));					
					// ********* end fill state data *********
					
					// ********* begin fill country data *********
					_tmpShipPickUp.getAddress().getCity().getCountry().setValueId(rs.getInt(10));
					_tmpShipPickUp.getAddress().getCity().getCountry().setValue1(rs.getString(11));					
					// ********* end fill country data *********
											
					_tmpShipPickUp.setEnteredDate( rs.getTimestamp(12));
					_tmpShipPickUp.setDisabledDate( rs.getTimestamp(13));
					_tmpShipPickUp.setPartnerId(rs.getInt(14));
					_tmpShipPickUp.setDestinationAirport(rs.getInt(15));
					_tmpShipPickUp.setDestinationPort(rs.getInt(16));
					_tmpShipPickUp.setEmail(rs.getString(17));
					_tmpShipPickUp.setNotes(rs.getString(18));
					_tmpShipPickUp.setNotifyParty(rs.getString(19));

					shipPickUps.add(_tmpShipPickUp);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return shipPickUps;

	}

	/**
	 * 
	 * @param partner
	 */
	public PartnerDepartmentInfo loadDeparmentInfo(Partner partner) throws Exception {
		PartnerDepartmentInfo partnerDepartmentInfo = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partners_LOAD_DEPARTMENT_INFO(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partner.getPartnerId());
			cst.setInt(2, partner.getDepartmentLotId());
			rs = cst.executeQuery();			
			
			partnerDepartmentInfo = new PartnerDepartmentInfo();
			if (rs.next()) {
					
				partnerDepartmentInfo.setPartnerDepartmentInfoId((rs.getInt(1)));
				partnerDepartmentInfo.setPartnerId(rs.getInt(2));
				partnerDepartmentInfo.setDepartmentLotId(rs.getInt(3));					
				partnerDepartmentInfo.setEmployeeRep(rs.getInt(4));
				partnerDepartmentInfo.setNotes(rs.getString(5));	
				partnerDepartmentInfo.setMarkup(rs.getFloat(6));
				partnerDepartmentInfo.setMargin(rs.getFloat(7));				
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return partnerDepartmentInfo;
	}

	/**
	 * 
	 * @param partner
	 */
	public List<CallHistory> loadCallHistory(Partner partner) throws Exception {
		List<CallHistory> callsHistory = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			
			sql = "{call lotradingdb.sp_partners_LOAD_CALL_HISTORY(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partner.getPartnerId());
			rs = cst.executeQuery();			
			
			callsHistory = new ArrayList<CallHistory>();
			if (rs.next()) {								
				do {
					CallHistory _tmpCallHistory = new CallHistory();
					_tmpCallHistory.setCallId((rs.getInt(1)));
					
					// ********* begin fill partnerContact data *********
					PartnerContact contact = new PartnerContact();
					contact.setContactId(rs.getInt(2));
					contact.setName(rs.getString(3));
					// ********* end fill partnerContact data *********
					
					_tmpCallHistory.setContact(contact);
					
					// ********* begin fill partner data *********
					partner.setPartnerId(rs.getInt(4));
					// ********* end fill partner data *********
					
					_tmpCallHistory.setPartner(partner);
					_tmpCallHistory.setEnteredDate( rs.getTimestamp(5));
					
					// ********* begin fill employee data *********
					Employee employee = new Employee();
					employee.setEmployeeId(rs.getInt(6));
					employee.setLogin(rs.getString(7));
					employee.setFirstName(rs.getString(8));
					employee.setLastName(rs.getString(9));
					// ********* end fill employee data *********
					
					_tmpCallHistory.setEmployeeCreator(employee);
					_tmpCallHistory.setNotes( rs.getString(10));

					callsHistory.add(_tmpCallHistory);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return callsHistory;
	}

	/**
	 * 
	 * @param partner
	 */
	public Partner loadByCode(Partner partner) throws Exception {
		
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partners_LOAD_BY_CODE_OR_NAME(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setBoolean(1, partner.isClient());
			if(partner.isClient()){
				cst.setString(2, partner.getCode());
			}else{
				cst.setString(2, partner.getName());
			}
			rs = cst.executeQuery();			
			if (rs.next()) {
				
					partner.setPartnerId(rs.getInt(1));
					partner.setName(rs.getString(2));
					partner.setCode(rs.getString(3));

					// ********* begin fill Address data *********					
					partner.getAddress().setAddressId(rs.getInt(4));
					partner.getAddress().setAddress(rs.getString(5));
					partner.getAddress().setPostalCode(rs.getString(6));
					// ********* end fill address data *********

					// ********* begin fill city data *********					
					partner.getAddress().getCity().setCityId(rs.getInt(7));
					partner.getAddress().getCity().setName(rs.getString(8));
					// ********* end fill city data *********

					// ********* begin fill state data *********					
					partner.getAddress().getCity().getStateProvince().setValueId(rs.getInt(9));
					partner.getAddress().getCity().getStateProvince().setValue1(rs.getString(10));
					// ********* end fill state data *********

					// ********* begin fill country data *********					
					partner.getAddress().getCity().getCountry().setValueId(rs.getInt(11));
					partner.getAddress().getCity().getCountry().setValue1(rs.getString(12));
					// ********* end fill country data *********
		
					// ********* begin fill phone data *********
					partner.getPhone().setPhoneId(rs.getInt(13));
					partner.getPhone().setCountryCode(rs.getString(14));
					partner.getPhone().setAreaCode(rs.getString(15));
					partner.getPhone().setPhoneNumber(rs.getString(16));
					partner.getPhone().setPhoneExtension(rs.getString(17));
					// ********* end fill phone data *********

					// ********* begin fill fax data *********					
					partner.getFax().setPhoneId(rs.getInt(18));
					partner.getFax().setCountryCode(rs.getString(19));
					partner.getFax().setAreaCode(rs.getString(20));
					partner.getFax().setPhoneNumber(rs.getString(21));
					partner.getFax().setPhoneExtension(rs.getString(22));
					// ********* end fill fax data *********
					
					partner.setWebsite(rs.getString(23));

					// ********* begin fill status data *********					
					partner.getStatus().setValueId(rs.getInt(24));
					partner.getStatus().setValue1(rs.getString(25));
					// ********* end fill status data *********

					// ********* begin fill payment terms data *********					
					partner.getPaymentTerm().setValueId(rs.getInt(26));
					partner.getPaymentTerm().setValue1(rs.getString(27));
					// ********* end fill payment terms data *********
					
					partner.setNotes(rs.getString(28));
					partner.setInvoiceNotes(rs.getString(29));
					partner.setClient(rs.getBoolean(30));
					partner.setSupplier(rs.getBoolean(31));
					partner.setTaxId(rs.getString(32));
					
					// ********* begin fill language data *********					
					partner.getLanguage().setValueId(rs.getInt(33));
					partner.getLanguage().setValue1(rs.getString(34));					
					// ********* end fill language data *********
										
					// ********* begin fill truck company data *********					
					partner.getTruckCompany().setValueId(rs.getInt(35));
					partner.getTruckCompany().setValue1(rs.getString(36));
					partner.getTruckCompany().setValue2(rs.getString(37));
					// ********* end fill truck company data *********
					
					partner.setInformFinalDest(rs.getBoolean(38));					
					partner.getEmployeeCreator().setEmployeeId(rs.getInt(39));
					partner.getEmployeeUpdate().setEmployeeId(rs.getInt(40));
					partner.getEmployeeCreator().setFirstName(rs.getString(41));
					partner.getEmployeeCreator().setLastName(rs.getString(42));
					partner.getEmployeeUpdate().setFirstName(rs.getString(43));
					partner.getEmployeeUpdate().setLastName(rs.getString(44));
					partner.setEnteredDate(rs.getTimestamp(45));
					partner.setUpdatedDate(rs.getTimestamp(46));
					partner.setRegUSA(rs.getBoolean(47));
					partner.setRegCOL(rs.getBoolean(48));
					partner.setRegGER(rs.getBoolean(49));
					partner.setCustomerNumber(rs.getString(50));
					partner.setSearchAndInspConsent(rs.getBoolean(51));
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return partner;
	}
	
	public List<Partner> searchLightClient(Partner partner) throws Exception{
		List<Partner> partners = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_partner_SEARCH_BY_NAME(?,?)}";
			cst = conn.prepareCall(sql);
			if (partner != null) {
				cst.setString(1, partner.getName());
				cst.setBoolean(2, partner.isClient());
			}
			rs = cst.executeQuery();
			if (rs.next()) {				
				partners = new ArrayList<Partner>();
				do {
					Partner _tmpPartner = new Partner();
					_tmpPartner.setName(rs.getString(1));
					_tmpPartner.setPartnerId(rs.getInt(2));
					_tmpPartner.getAddress().setAddress(rs.getString(3));
					_tmpPartner.getAddress().setPostalCode(rs.getString(4));
					_tmpPartner.getAddress().getCity().setName(rs.getString(5));
					_tmpPartner.getAddress().getCity().getStateProvince().setValue1(rs.getString(6));
					_tmpPartner.getAddress().getCity().getCountry().setValue1(rs.getString(7));
					_tmpPartner.setSearchAndInspConsent(rs.getBoolean(8));
					partners.add(_tmpPartner);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return partners;
	}

	public List<ClientRate> loadClientRates(ClientRate clientRate) throws Exception {
		List<ClientRate> clientRates = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_client_rate_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRate.getClientRatePortsId());
			rs = cst.executeQuery();			
			
			clientRates = new ArrayList<ClientRate>();
			if (rs.next()) {								
				do {
					ClientRate _tmpClientRate = new ClientRate();
					_tmpClientRate.setClientRateId(rs.getInt(1));
					_tmpClientRate.setClientRatePortsId(rs.getInt(2));
					_tmpClientRate.getChargeType().setValueId(rs.getInt(3));
					_tmpClientRate.setRate(rs.getDouble(4));
					_tmpClientRate.setRateOffset(rs.getDouble(5));
					_tmpClientRate.setMinimumRate(rs.getDouble(6));
					_tmpClientRate.setMinimumOffset(rs.getDouble(7));
					_tmpClientRate.setOtherCharge(rs.getBoolean(8));
					_tmpClientRate.setCreatedDate(rs.getDate(9));
					_tmpClientRate.getChargeType().setValue1(rs.getString(10));
					_tmpClientRate.setFlat(rs.getBoolean(11));
					_tmpClientRate.setSelectedToAwbDoc(rs.getBoolean(12));
					clientRates.add(_tmpClientRate);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return clientRates;
	}
	
	public List<ClientRatesPort> loadClientRatesPort(ClientRatesPort clientRatesPort) throws Exception{
		List<ClientRatesPort> clientRatesPorts = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_client_rates_ports_LOAD(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRatesPort.getClientId());
			cst.setInt(2, clientRatesPort.getRateType().getValueId());
			rs = cst.executeQuery();			
			
			clientRatesPorts = new ArrayList<ClientRatesPort>();
			if (rs.next()) {								
				do {
					ClientRatesPort _tmpClientRatePort = new ClientRatesPort();
					_tmpClientRatePort.setClientRatePortId(rs.getInt(1));
					_tmpClientRatePort.setCarrierId(rs.getInt(2));
					_tmpClientRatePort.setPortDestination(rs.getInt(3));
					_tmpClientRatePort.setPortOrigin(rs.getInt(4));
					_tmpClientRatePort.getRateType().setValueId(rs.getInt(5));
					_tmpClientRatePort.setCreated(rs.getDate(6));
					clientRatesPorts.add(_tmpClientRatePort);
				} while (rs.next());
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return clientRatesPorts;
		
	}
	
	public PartnerContact loadPartnerContactMainInfo(PartnerContact partnerContact) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_partners_LOAD_MAIN_PARTNER_CONTACT(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, partnerContact.getPartnerId());
			cst.setInt(2, partnerContact.getDepartmentLotId());
			rs = cst.executeQuery();
			if (rs.next()) {
				partnerContact.setContactId(rs.getInt(1));
				partnerContact.setName(rs.getString(2));
				partnerContact.setTitle(rs.getString(3));
				partnerContact.setEmail(rs.getString(4));
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
	return partnerContact;
	}
	
}