package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlDao;
import com.lotrading.softlot.LODepartment.shipments.entity.Awb;
import com.lotrading.softlot.LODepartment.shipments.entity.BLDeclaredValue;
import com.lotrading.softlot.LODepartment.shipments.entity.Bl;
import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;
import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;
import com.lotrading.softlot.LODepartment.shipments.entity.BlEEI;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;
import com.lotrading.softlot.LODepartment.shipments.entity.ItemProrated;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.UtilFunctions;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 6-Dic-2012 10:40:00 AM
 */
public class BlDaoImpl extends DAOTemplate implements IBlDao {

	private Log log = LogFactory.getLog(BlDaoImpl.class);
	
	public BlDaoImpl(){

	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public int createBl(Bl bl) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.setInt(3, bl.getRegion().getValueId());			
			cst.setBoolean(4, bl.isCompleted());
			cst.setInt(5, bl.getBlType().getValueId());	
			cst.setInt(6, bl.getBlShippingType().getValueId());
			cst.setInt(7, bl.getSupplier().getPartnerId());
			cst.setInt(8, bl.getPickupFrom().getShipPickUpId());
			cst.setInt(9, bl.getClient().getPartnerId());
			cst.setInt(10, bl.getShipTo().getShipPickUpId());
			cst.setInt(11, bl.getSalesRep().getEmployeeId());
			cst.setString(12, bl.getClientPoNumber());
			cst.setInt(13, bl.getCarrier().getCarrierId());
			cst.setString(14, bl.getNotifyParty());
			cst.setInt(15, bl.getPlaceOfReceipt().getCityId());
			cst.setInt(16, bl.getPortOrigin().getPortId());
			cst.setInt(17, bl.getPortDestination().getPortId());
			cst.setString(18, bl.getBooking());
			cst.setString(19, bl.getFullBlNumber());			
			cst.setString(20, bl.getRemarks());			
			cst.setBoolean(21, bl.isShipDelNotif());
			cst.setBoolean(22, bl.isArrivalNotif());
			cst.setBoolean(23, bl.isDocsDelNotif());
			cst.setBoolean(24, bl.isShipDelNotif());
			cst.setInt(25, bl.getBlMaster().getBlId());
			cst.setDouble(26, bl.getTotalCost());
			cst.setDouble(27, bl.getTotalSale());			
			cst.setDouble(28, bl.getMargin());			
			cst.setString(29, bl.getVessel_voyage());
			cst.setDouble(30, bl.getDeclaredValue());
			cst.setInt(31, bl.getPlaceOfDelivery().getCityId());
			cst.setString(32, bl.getPierOfOrigin());
			cst.setString(33, bl.getAdditionalNumbers());
			cst.setString(34, bl.getExportInstructions());			
			
			if(bl.getEtd() != null){
				cst.setTimestamp(35, toTimeStamp(bl.getEtd()));
			}else{
				cst.setTimestamp(35, null);
			}
			
			if(bl.getEta() != null){
				cst.setTimestamp(36, toTimeStamp(bl.getEta()));
			}else{
				cst.setTimestamp(36, null);
			}			
			
			cst.setBoolean(37, bl.isLockCosts());	
			cst.setString(38, bl.getSaidToContain());
			cst.setBoolean(39, bl.isNonDeclaredValue());	
			cst.setBoolean(40, bl.isRated());	
			cst.setBoolean(41, bl.isNoEEI());
			cst.setDouble(42, bl.getTotalRealWLb());
			cst.setDouble(43, bl.getTotalOceanVolF3());
			cst.setDouble(44, bl.getTotalPieces());
			cst.setInt(45, bl.getPaymentType().getValueId());
			cst.setBoolean(46, bl.isPoeNotif());
			cst.setBoolean(47, bl.isPrintDraft());	
			
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(1);
				bl.setBlNumber(cst.getString(2));
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
	 * @param bl
	 * @throws Exception 
	 */
	public boolean updateBl(Bl bl) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);		
			cst.setInt(1, bl.getBlId());
			cst.setInt(2, bl.getRegion().getValueId());
			cst.setString(3, bl.getBlNumber());
			cst.setBoolean(4, bl.isCompleted());
			cst.setInt(5, bl.getBlType().getValueId());	
			cst.setInt(6, bl.getBlShippingType().getValueId());
			cst.setInt(7, bl.getSupplier().getPartnerId());
			cst.setInt(8, bl.getPickupFrom().getShipPickUpId());
			cst.setInt(9, bl.getClient().getPartnerId());
			cst.setInt(10, bl.getShipTo().getShipPickUpId());
			cst.setInt(11, bl.getSalesRep().getEmployeeId());
			cst.setString(12, bl.getClientPoNumber());
			cst.setInt(13, bl.getCarrier().getCarrierId());
			cst.setString(14, bl.getNotifyParty());
			cst.setInt(15, bl.getPlaceOfReceipt().getCityId());
			cst.setInt(16, bl.getPortOrigin().getPortId());
			cst.setInt(17, bl.getPortDestination().getPortId());
			cst.setString(18, bl.getBooking());
			cst.setString(19, bl.getFullBlNumber());			
			cst.setString(20, bl.getRemarks());			
			cst.setBoolean(21, bl.isShipDelNotif());
			cst.setBoolean(22, bl.isArrivalNotif());
			cst.setBoolean(23, bl.isDocsDelNotif());
			cst.setBoolean(24, bl.isShipDelNotif());
			cst.setInt(25, bl.getBlMaster().getBlId());
			cst.setDouble(26, bl.getTotalCost());
			cst.setDouble(27, bl.getTotalSale());			
			cst.setDouble(28, bl.getMargin());			
			cst.setString(29, bl.getVessel_voyage());
			cst.setDouble(30, bl.getDeclaredValue());
			cst.setInt(31, bl.getPlaceOfDelivery().getCityId());
			cst.setString(32, bl.getPierOfOrigin());
			cst.setString(33, bl.getAdditionalNumbers());
			cst.setString(34, bl.getExportInstructions());
			
			
			if(bl.getEtd() != null){
				cst.setTimestamp(35, toTimeStamp(bl.getEtd()));
			}else{
				cst.setTimestamp(35, null);
			}
			
			if(bl.getEta() != null){
				cst.setTimestamp(36, toTimeStamp(bl.getEta()));
			}else{
				cst.setTimestamp(36, null);
			}			
			
			cst.setBoolean(37, bl.isLockCosts());
			cst.setString(38,bl.getSaidToContain());
			cst.setBoolean(39, bl.isNonDeclaredValue());	
			cst.setBoolean(40, bl.isRated());	
			cst.setBoolean(41, bl.isNoEEI());	
			cst.setDouble(42, bl.getTotalRealWLb());
			cst.setDouble(43, bl.getTotalOceanVolF3());
			cst.setDouble(44, bl.getTotalPieces());
			cst.setInt(45, bl.getTypeOfMove().getValueId());			
			cst.setInt(46, bl.getPaymentType().getValueId());		
			cst.setBoolean(47, bl.isPoeNotif());
			cst.setBoolean(48, bl.isPrintDraft());
			
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

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<Bl> searchBl(Bl bl) throws Exception{
		List<Bl> blList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if(bl != null){
				if(bl.getClient() != null){
					cst.setString(1, bl.getClient().getCode());
					cst.setInt(2, bl.getClient().getPartnerId());
				}else{
					cst.setString(1, null);
					cst.setInt(2, 0);
				}
				
				if (bl.getWhNumber() != null){
					cst.setString(3, bl.getWhNumber());
				}else{
					cst.setString(3,"");
				}
				
				if(bl.getSupplier() != null){
					cst.setInt(4, bl.getSupplier().getPartnerId());
				}else{
					cst.setInt(4, 0);
				}
				
				if(bl.getCarrier() != null){
					cst.setInt(5, bl.getCarrier().getCarrierId());
				}else{
					cst.setInt(5, 0);
				}
				
				if(bl.getSalesRep() != null){
					cst.setInt(6, bl.getSalesRep().getEmployeeId());
				}else{
					cst.setInt(6, 0);
				}
				
				cst.setString(7, bl.getClientPoNumber());				
				cst.setString(8, bl.getFullBlNumber());
				
				if (bl.getBlOtherInvoice() != null){
					cst.setString(9, bl.getBlOtherInvoice()); 
				}else{
					cst.setString(9, ""); 
				}
				if (bl.getBlFreightInvoice() != null){
					cst.setString(10, bl.getBlFreightInvoice());
				}else{
					cst.setString(10, "");
				}
				
				cst.setString(11, bl.getBlNumber());
							
				
				if(bl.getPortOrigin() != null){
					cst.setInt(12, bl.getPortOrigin().getPortId());
				}else{
					cst.setInt(12, 0);
				}
				if(bl.getPortDestination() != null){
					cst.setInt(13, bl.getPortDestination().getPortId());
				}else{
					cst.setInt(13, 0);
				}
				
				cst.setString(14, bl.getRemarks());
				
				if(bl.getBlType() != null){
					cst.setInt(15, bl.getBlType().getValueId());
				}else{
					cst.setInt(15, 0);
				}
				
				if(bl.getBlShippingType() != null){
					cst.setInt(16, bl.getBlShippingType().getValueId());
				}else{
					cst.setInt(16, 0);
				}
								
				if(bl.getDateFromFilter() != null){
					cst.setTimestamp(17, toTimeStamp(bl.getDateFromFilter()));
				}else{
					cst.setTimestamp(17, null);
				}
				if(bl.getDateToFilter() != null){
					cst.setTimestamp(18, toTimeStamp(bl.getDateToFilter()));
				}else{
					cst.setTimestamp(18, null);
				}
				if(bl.getRegion() != null){
					cst.setInt(19, bl.getRegion().getValueId());
				}else{
					cst.setInt(19, 0);
				}
				
				rs = cst.executeQuery();
				if(rs.next()){
					blList = new ArrayList<Bl>();
					do{
						bl = new Bl();
						bl.setBlId(rs.getInt(1));
						bl.setBlNumber(rs.getString(2));
						bl.setCompleted(rs.getBoolean(3));
						bl.setCreatedDate(rs.getDate(4));						
						bl.setClientPoNumber(rs.getString(5));
						bl.getCarrier().setCarrierId(rs.getInt(6));
						bl.getCarrier().setName(rs.getString(7));
						bl.setFullBlNumber(rs.getString(8));
						bl.getShipTo().setShipPickUpId(rs.getInt(9));
						bl.getShipTo().setName(rs.getString(10));
						bl.getClient().setPartnerId(rs.getInt(11));
						bl.getClient().setCode(rs.getString(12));
						bl.getClient().setName(rs.getString(13));
						bl.getPickupFrom().setShipPickUpId(rs.getInt(14));
						bl.getPickupFrom().setName(rs.getString(15));
						bl.setEta(rs.getDate(16));
						bl.setEtd(rs.getDate(17));
						bl.getPortOrigin().setPortId(rs.getInt(18));
						bl.getPortOrigin().setName(rs.getString(19));
						bl.getPortDestination().setPortId(rs.getInt(20));
						bl.getPortDestination().setName(rs.getString(21));
						bl.getSalesRep().setEmployeeId(rs.getInt(22));
						bl.getSalesRep().setFirstName(rs.getString(23));
						bl.getSalesRep().setLastName(rs.getString(24));
						bl.getSalesRep().setLogin(rs.getString(25));
						bl.getRegion().setValueId(rs.getInt(26));
						bl.setShipmentNotif(rs.getBoolean(27));
						bl.setArrivalNotif(rs.getBoolean(28));
						bl.setDocsDelNotif(rs.getBoolean(29));
						bl.setShipDelNotif(rs.getBoolean(30));
						bl.setPoeNotif(rs.getBoolean(31));
						bl.getSupplier().setPartnerId(rs.getInt(32));
						bl.getSupplier().setName(rs.getString(33));
						bl.setRemarks(rs.getString(34));
						bl.setConcatenatedFreightInvoices2(rs.getString(35));
						
						blList.add(bl);
					}while(rs.next());
				}
				rs.close();
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blList;
	}
	
	public List<Bl> loadBlList(Bl bl) throws Exception{
		List<Bl> blList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_list_LOAD(?,?)}";
			cst = conn.prepareCall(sql);				
			cst.setInt(1, bl.getBlType().getValueId());
			if (bl.getBlMaster() != null){
				cst.setString(2, bl.getBlMaster().getBlNumber());
			}else{
				cst.setString(2, null);
			}			
			
			rs = cst.executeQuery();
			if(rs.next()){
				blList = new ArrayList<Bl>();
				do{
					Bl _tmpBl = new Bl();
					_tmpBl.setBlId(rs.getInt(1));
					_tmpBl.setBlNumber(rs.getString(2));
					_tmpBl.getBlType().setValueId(rs.getInt(3));
					_tmpBl.getBlType().setValue1(rs.getString(4));
					_tmpBl.getSupplier().setName(rs.getString(5));
					_tmpBl.getClient().setName(rs.getString(6));
					_tmpBl.getCarrier().setCarrierId(rs.getInt(7));
					_tmpBl.getCarrier().setName(rs.getString(8));
					_tmpBl.getCarrier().setCarrierCode(rs.getString(9));
					_tmpBl.setFullBlNumber(rs.getString(10));
					_tmpBl.getBlShippingType().setValueId(rs.getInt(11));
					_tmpBl.getClient().setPartnerId(rs.getInt(12));
					_tmpBl.getClient().setCode(rs.getString(13));
					_tmpBl.getSupplier().setPartnerId(rs.getInt(14));
					_tmpBl.setClientPoNumber(rs.getString(15));
					
					_tmpBl.setTotalRealWLb(rs.getDouble(16));						
					_tmpBl.setTotalRealWKg(UtilFunctions.roundDecimalPlaces(_tmpBl.getTotalRealWLb()/Constants.KILOGRAM_TO_POUNDS,2) );	
					_tmpBl.setTotalRealWTon(UtilFunctions.roundDecimalPlaces(_tmpBl.getTotalRealWLb()/(Constants.KILOGRAM_TO_POUNDS * 1000),2) );
					_tmpBl.setTotalOceanVolF3(rs.getDouble(17));
					_tmpBl.setTotalOceanVolM3(UtilFunctions.roundDecimalPlaces(_tmpBl.getTotalOceanVolF3() *  Math.pow(Constants.FOOT_TO_METERS, 3),2));
					
					_tmpBl.setTotalPieces(rs.getInt(18));
					if(_tmpBl.getBlMaster() == null){
						_tmpBl.setBlMaster(new Bl());
					}
					_tmpBl.getBlMaster().setBlId(rs.getInt(19));
									
					blList.add(_tmpBl);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blList;
	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public Bl loadBl(Bl bl) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			if(rs.next()){
				bl.getRegion().setValueId(rs.getInt(2));
				bl.setBlNumber(rs.getString(3));
				bl.setCompleted(rs.getBoolean(4));
				bl.setCreatedDate(toDate(rs.getDate(5)));				
				bl.getBlType().setValueId(rs.getInt(6));
				bl.getBlType().setValue1(rs.getString(7));
				bl.getBlShippingType().setValueId(rs.getInt(8));
				bl.getSupplier().setPartnerId(rs.getInt(9));
				bl.getSupplier().setName(rs.getString(10));
				bl.getSupplier().getAddress().setAddressId(rs.getInt(11));
				bl.getSupplier().getAddress().setAddress(rs.getString(12));
				bl.getSupplier().getAddress().setPostalCode(rs.getString(13));
				bl.getSupplier().getAddress().getCity().setCityId(rs.getInt(14));
				bl.getSupplier().getAddress().getCity().setName(rs.getString(15));
				bl.getSupplier().getAddress().getCity().getStateProvince().setValue1(rs.getString(16));
				bl.getSupplier().getAddress().getCity().getCountry().setValue1(rs.getString(17));
				
				bl.getPickupFrom().setShipPickUpId(rs.getInt(18));
				bl.getPickupFrom().setName(rs.getString(19));
				bl.getPickupFrom().getAddress().setAddressId(rs.getInt(20));
				bl.getPickupFrom().getAddress().setAddress(rs.getString(21));
				bl.getPickupFrom().getAddress().setPostalCode(rs.getString(22));
				bl.getPickupFrom().getAddress().getCity().setCityId(rs.getInt(23));
				bl.getPickupFrom().getAddress().getCity().setName(rs.getString(24));
				bl.getPickupFrom().getAddress().getCity().getStateProvince().setValue1(rs.getString(25));
				bl.getPickupFrom().getAddress().getCity().getCountry().setValue1(rs.getString(26));
				
				bl.getClient().setPartnerId(rs.getInt(27));
				bl.getClient().getAddress().setAddressId(rs.getInt(28));
				bl.getClient().getAddress().setAddress(rs.getString(29));
				bl.getClient().getAddress().setPostalCode(rs.getString(30));
				bl.getClient().getAddress().getCity().setCityId(rs.getInt(31));
				bl.getClient().getAddress().getCity().setName(rs.getString(32));
				bl.getClient().getAddress().getCity().getStateProvince().setValue1(rs.getString(33));
				bl.getClient().getAddress().getCity().getCountry().setValue1(rs.getString(34));
				
				bl.getShipTo().setShipPickUpId(rs.getInt(35));
				bl.getShipTo().getAddress().setAddressId(rs.getInt(36));
				bl.getShipTo().getAddress().setAddress(rs.getString(37));
				bl.getShipTo().getAddress().getCity().setCityId(rs.getInt(38));
				bl.getShipTo().getAddress().getCity().setName(rs.getString(39));
				bl.getShipTo().getAddress().getCity().getStateProvince().setValue1(rs.getString(40));
				bl.getShipTo().getAddress().getCity().getCountry().setValue1(rs.getString(41));
				
				bl.getSalesRep().setEmployeeId(rs.getInt(42));
				bl.setClientPoNumber(rs.getString(43));
				bl.getCarrier().setCarrierId(rs.getInt(44));
				bl.setNotifyParty(rs.getString(45));
				bl.getPlaceOfReceipt().setCityId(rs.getInt(46));
				bl.getPortOrigin().setPortId(rs.getInt(47));
				bl.getPortDestination().setPortId(rs.getInt(48));
				bl.setBooking(rs.getString(49));
				bl.setFullBlNumber(rs.getString(50));
				bl.setRemarks(rs.getString(51));
				bl.setShipmentNotif(rs.getBoolean(52));
				bl.setArrivalNotif(rs.getBoolean(53));
				bl.setDocsDelNotif(rs.getBoolean(54));
				bl.setShipDelNotif(rs.getBoolean(55));
				
				Bl _auxBlMaster = new Bl();
				bl.setBlMaster(_auxBlMaster);				
				bl.getBlMaster().setBlId(rs.getInt(56));
				bl.getBlMaster().setBlNumber(rs.getString(57));
			
				bl.setTotalCost(rs.getDouble(58));				
				bl.setTotalSale(rs.getDouble(59));
				
				bl.setMargin(rs.getDouble(60));
				bl.setVessel_voyage(rs.getString(61));
				bl.setDeclaredValue(rs.getDouble(62));
				bl.getPlaceOfDelivery().setCityId((rs.getInt(63)));
				bl.setPierOfOrigin(rs.getString(64));
				bl.setAdditionalNumbers(rs.getString(65));
				bl.setExportInstructions(rs.getString(66));				
				bl.setEtd(rs.getTimestamp(67));
				bl.setEta( rs.getTimestamp(68));
				bl.setLockCosts(rs.getBoolean(69));
				bl.getBlShippingType().setValue1(rs.getString(70));
				bl.getPlaceOfReceipt().setName(rs.getString(71));
				bl.getTypeOfMove().setValueId(rs.getInt(72));
				bl.setSaidToContain(rs.getString(73));
				bl.setNonDeclaredValue(rs.getBoolean(74));
				bl.setRated(rs.getBoolean(75));
				bl.setNoEEI(rs.getBoolean(76));
				bl.getPlaceOfDelivery().setName(rs.getString(77));
				bl.getPaymentType().setValueId(rs.getInt(78));		
				bl.getBlMaster().setFullBlNumber(rs.getString(79));
				bl.setPoeNotif(rs.getBoolean(80));
				bl.getBlMasterContainer().setContainerId(rs.getInt(81));
				bl.getClient().setSearchAndInspConsent(rs.getBoolean(82));
				bl.setPrintDraft(rs.getBoolean(83));
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return bl;
	}
	
	public List<Bl> loadHouseBls(Bl bl) throws Exception{
		List<Bl> houseBls = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_houses_LOAD(?)}";
			cst = conn.prepareCall(sql);
			if(bl != null){
				cst.setInt(1, bl.getBlId());
				rs = cst.executeQuery();
				if(rs.next()){
					houseBls = new ArrayList<Bl>();
					do{
						bl = new Bl();
						bl.setBlId(rs.getInt(1));
						bl.setBlNumber(rs.getString(2));
						bl.getClient().setPartnerId(rs.getInt(3));
						bl.getClient().setCode(rs.getString(4));
						bl.getClient().setName(rs.getString(5));
						bl.getSupplier().setPartnerId(rs.getInt(6));
						bl.getSupplier().setName(rs.getString(7));
						bl.setClientPoNumber(rs.getString(8));
						bl.setTotalRealWLb(rs.getDouble(9));						
						bl.setTotalRealWKg(UtilFunctions.roundDecimalPlaces(bl.getTotalRealWLb()/Constants.KILOGRAM_TO_POUNDS,2) );	
						bl.setTotalRealWTon(UtilFunctions.roundDecimalPlaces(bl.getTotalRealWLb()/(Constants.KILOGRAM_TO_POUNDS * 1000),2) );
						bl.setTotalOceanVolF3(rs.getDouble(10));
						bl.setTotalOceanVolM3(UtilFunctions.roundDecimalPlaces(bl.getTotalOceanVolF3() *  Math.pow(Constants.FOOT_TO_METERS, 3),2));
						
						bl.setTotalPieces(rs.getInt(11));
						if(bl.getBlMaster() == null){
							bl.setBlMaster(new Bl());
						}
						bl.getBlMaster().setBlId(rs.getInt(12));
						bl.getBlType().setValueId(rs.getInt(13));	
						bl.getBlShippingType().setValueId(rs.getInt(14));
						bl.getBlMasterContainer().setContainerId(rs.getInt(15));
						houseBls.add(bl);
					}while(rs.next());
				}
				rs.close();
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return houseBls;
	}
	
	public boolean updateBlHouse(Bl bl) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_house_UPDATE_MASTER_ID(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			cst.setInt(2, bl.getBlMaster().getBlId());
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
	
	public List<Bl> updateBlHouseList(List<Bl> blHousesList) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(Bl _tmpBlHouse : blHousesList){
				String sql = "{call sp_bl_houses_UPDATE(?,?,?)}";
				cst = conn.prepareCall(sql);
				cst.setInt(1, _tmpBlHouse.getBlId());
				cst.setInt(2, _tmpBlHouse.getBlMaster().getBlId());
				cst.setInt(3, _tmpBlHouse.getBlMasterContainer().getContainerId());
				cst.executeUpdate();
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blHousesList;
	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlItem> loadBlItems(Bl bl) throws Exception{
		List<BlItem> blItems = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_items_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blItems = new ArrayList<BlItem>();
			if(rs.next()){
				do{
					BlItem _tmpBlItem = new BlItem();
					_tmpBlItem.setItemId(rs.getInt(1));
					_tmpBlItem.setPieces(rs.getInt(2));
					_tmpBlItem.getType().setValueId(rs.getInt(3));
					_tmpBlItem.setItemLength(rs.getDouble(4));
					_tmpBlItem.setItemWidth(rs.getDouble(5));
					_tmpBlItem.setItemHeight(rs.getDouble(6));
					_tmpBlItem.setItemWeight(rs.getDouble(7));					
					_tmpBlItem.setWhItemId(rs.getInt(8));
					_tmpBlItem.setPoNumber(rs.getString(9));
					_tmpBlItem.getInvoice().setInvoiceId(rs.getInt(10));
					_tmpBlItem.setRemarks(rs.getString(11));
					_tmpBlItem.getWhLocation().setWhLocationId( rs.getString(12));
					_tmpBlItem.getContainer().setContainerId(rs.getInt(13));
					_tmpBlItem.setCreatedDate(rs.getDate(14));
					_tmpBlItem.getWhReceipt().setWhReceiptId(rs.getInt(15));
					_tmpBlItem.getWhReceipt().setWhReceiptNumber(rs.getString(16));
					_tmpBlItem.getInvoice().setInvoiceNumber(rs.getString(17));
					_tmpBlItem.setHazardous(rs.getBoolean(18));
					_tmpBlItem.setClientOrderId(rs.getInt(19));					
					
					if(rs.getInt(20)>0){
						_tmpBlItem.setPalletId(rs.getInt(20)+"");
					}else{
						_tmpBlItem.setPalletId(null);
					}
					
					_tmpBlItem.getWhReceipt().setRemarks(rs.getString(21));
					_tmpBlItem.setItemVolume(_tmpBlItem.getItemHeight() * _tmpBlItem.getItemWidth() * _tmpBlItem.getItemLength()  / Math.pow(Constants.FOOT_TO_INCHES,3));
										
					blItems.add(_tmpBlItem);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blItems;
	}
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlPalletizedItem> loadBlPalletizedItems(Bl bl) throws Exception{
		List<BlPalletizedItem> blItems = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_palletized_items_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blItems = new ArrayList<BlPalletizedItem>();
			if(rs.next()){
				do{
					BlPalletizedItem _tmpBlPalletizedItem = new BlPalletizedItem();
					_tmpBlPalletizedItem.setPalletizedItemId(rs.getInt(1));
					_tmpBlPalletizedItem.setPieces(rs.getInt(2));
					_tmpBlPalletizedItem.getType().setValueId(rs.getInt(3));
					_tmpBlPalletizedItem.setItemLength(rs.getDouble(4));
					_tmpBlPalletizedItem.setItemWidth(rs.getDouble(5));
					_tmpBlPalletizedItem.setItemHeight(rs.getDouble(6));
					_tmpBlPalletizedItem.setItemWeight(rs.getDouble(7));
					
					if(rs.getInt(8)>0){
						_tmpBlPalletizedItem.setPalletId(rs.getInt(8)+"");
					}else{
						_tmpBlPalletizedItem.setPalletId(null);
					}
					
					_tmpBlPalletizedItem.setRemarks(rs.getString(9));
					_tmpBlPalletizedItem.getWhLocation().setWhLocationId(rs.getString(10));
					_tmpBlPalletizedItem.getContainer().setContainerId(rs.getInt(11));
					_tmpBlPalletizedItem.setCreatedDate(rs.getDate(12));
					_tmpBlPalletizedItem.setHazardous(rs.getBoolean(13));
					_tmpBlPalletizedItem.setItemVolume(_tmpBlPalletizedItem.getItemHeight() * _tmpBlPalletizedItem.getItemWidth() * _tmpBlPalletizedItem.getItemLength()  / Math.pow(Constants.FOOT_TO_INCHES,3));
										
					blItems.add(_tmpBlPalletizedItem);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blItems;
	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlFreightInvoice> loadFreightInvoices(Bl bl) throws Exception{
		List<BlFreightInvoice> blFreightInvoices = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_freight_invoice_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blFreightInvoices = new ArrayList<BlFreightInvoice>();
			if(rs.next()){
				do{
					BlFreightInvoice _tmpBlFreightInvoice = new BlFreightInvoice();
					_tmpBlFreightInvoice.setFreightInvoiceId(rs.getInt(1));
					_tmpBlFreightInvoice.getInvoice().setInvoiceId(rs.getInt(2));
					_tmpBlFreightInvoice.getInvoice().setInvoiceNumber(rs.getString(3));
					_tmpBlFreightInvoice.setCreatedDate(rs.getDate(4));
					blFreightInvoices.add(_tmpBlFreightInvoice);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blFreightInvoices;
	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlOtherInvoice> loadOtherInvoices(Bl bl) throws Exception{
		List<BlOtherInvoice> blOtherInvoices = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_other_invoice_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blOtherInvoices = new ArrayList<BlOtherInvoice>();
			if(rs.next()){
				do{
					BlOtherInvoice _tmpBlOtherInvoice = new BlOtherInvoice();
					_tmpBlOtherInvoice.setOtherInvoiceId(rs.getInt(1));
					_tmpBlOtherInvoice.getInvoice().setInvoiceId(rs.getInt(2));
					_tmpBlOtherInvoice.getInvoice().setInvoiceNumber(rs.getString(3));
					_tmpBlOtherInvoice.setCreatedDate(rs.getDate(4));
					
					blOtherInvoices.add(_tmpBlOtherInvoice);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blOtherInvoices;
	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlInlandCS> loadInlandsCS(Bl bl) throws Exception{
		List<BlInlandCS> blInlandsCS = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_inland_cs_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blInlandsCS = new ArrayList<BlInlandCS>();
			if(rs.next()){
				do{
					BlInlandCS _tmpBlInlandCS = new BlInlandCS();
					_tmpBlInlandCS.setInlandCSId(rs.getInt(1));
					_tmpBlInlandCS.setCost(rs.getDouble(2));
					_tmpBlInlandCS.setSale(rs.getDouble(3));
					_tmpBlInlandCS.getTruckCompany().setValueId(rs.getInt(4));
					_tmpBlInlandCS.setTrackingNumber(rs.getString(5));
					_tmpBlInlandCS.setCreatedDate(rs.getDate(6));
					_tmpBlInlandCS.setPoNumber(rs.getString(7));
					blInlandsCS.add(_tmpBlInlandCS);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blInlandsCS;
	}

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlUnCode> loadUnCodes(Bl bl) throws Exception{
		List<BlUnCode> blUnCodes = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_un_code_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blUnCodes = new ArrayList<BlUnCode>();
			if(rs.next()){
				do{
					BlUnCode _tmpBlUnCode = new BlUnCode();
					_tmpBlUnCode.setUnCodeId(rs.getInt(1));
					_tmpBlUnCode.setUnCode(rs.getString(2));
					_tmpBlUnCode.setUnClassId(rs.getInt(3));
					_tmpBlUnCode.setPackingGroupId(rs.getInt(4));
					blUnCodes.add(_tmpBlUnCode);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blUnCodes;
	}
	
	
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlCostSale> loadCostsSales(Bl bl) throws Exception{
		List<BlCostSale> blCostsSales = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_cost_sale_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blCostsSales = new ArrayList<BlCostSale>();
			if(rs.next()){
				do{
					BlCostSale _tmpBlCostSale = new BlCostSale();
					_tmpBlCostSale.setBlCostSaleId(rs.getInt(1));
					_tmpBlCostSale.getChargeType().setValueId(rs.getInt(2));					
					_tmpBlCostSale.setCost(rs.getDouble(3));
					_tmpBlCostSale.setSale(rs.getDouble(4));
					_tmpBlCostSale.setNotes(rs.getString(5));
					_tmpBlCostSale.setReviewed(rs.getBoolean(6));
					_tmpBlCostSale.setOtherCost(rs.getBoolean(7));
					_tmpBlCostSale.setSelectedToBlDoc(rs.getBoolean(8));
					_tmpBlCostSale.getChargeType().setValue1(rs.getString(9));
					_tmpBlCostSale.setShowInMaster(rs.getBoolean(10));
					
					
					blCostsSales.add(_tmpBlCostSale);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blCostsSales;
	}
	
	public List<BlCostSale> loadInitialCostsSales(Bl bl) throws Exception {
			
		List<BlCostSale> blCostsSales = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_fixed_cost_sales_LOAD(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if (bl.getBlShippingType().getValueId() == Constants.MASTER_VALUE_BL_SHIPPING_TYPE_LCL){
				cst.setInt(1, Constants.MASTER_CHARGE_TYPE_OCEAN_LCL); 
			}else if (bl.getBlShippingType().getValueId() == Constants.MASTER_VALUE_BL_SHIPPING_TYPE_FCL){
				cst.setInt(1, Constants.MASTER_CHARGE_TYPE_OCEAN_20_40); 
			}				
			
			if(bl.getBlType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_REGULAR){// Si el tipo de la guia es regular.
				cst.setBoolean(2, false);
			}else if(bl.getBlType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE){// Si el tipo de la guia es house.
				cst.setBoolean(2, true);
			}
			cst.setBoolean(3, bl.isContainHazardousItems());
			cst.setBoolean(4, false);
			cst.setBoolean(5, false);
			rs = cst.executeQuery();
			blCostsSales = new ArrayList<BlCostSale>();
			if(rs.next()){
				do{
					BlCostSale _tmpBlCostSale = new BlCostSale();
					_tmpBlCostSale.getChargeType().setValueId(rs.getInt(1));
					_tmpBlCostSale.getChargeType().setValue1(rs.getString(2));
					_tmpBlCostSale.setShowInMaster(rs.getBoolean(3));
					blCostsSales.add(_tmpBlCostSale);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blCostsSales;
	}
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlEEI> loadEEIs(Bl bl) throws Exception {
		List<BlEEI> blEEIList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_eei_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blEEIList = new ArrayList<BlEEI>();
			if(rs.next()){
				do{
					BlEEI _tmpBlEEI = new BlEEI();
					_tmpBlEEI.setEeiId(rs.getInt(1));
					_tmpBlEEI.setBlId(rs.getInt(2));
					_tmpBlEEI.setEei(rs.getString(3));
					_tmpBlEEI.setSupplier(rs.getString(4));
					blEEIList.add(_tmpBlEEI);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blEEIList;
	}
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlContainer> loadBlContainers(Bl bl) throws Exception{
		List<BlContainer> blContainers = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_container_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blContainers = new ArrayList<BlContainer>();
			if(rs.next()){
				do{
					BlContainer _tmpBlContainer = new BlContainer();
					_tmpBlContainer.setContainerId(rs.getInt(1));					
					_tmpBlContainer.getType().setValueId(rs.getInt(2));
					_tmpBlContainer.setLineNumber(rs.getInt(3));
					_tmpBlContainer.setContainerNumber(rs.getString(4));				
					_tmpBlContainer.setSeal(rs.getString(5));
					
					blContainers.add(_tmpBlContainer);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blContainers;
	}
	
	public List<ClientRate> loadClientRates(ClientRatesPort clientRatesPort) throws Exception{
		List<ClientRate> clientRates = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_rate_LOAD_ALL_PORT_RATES(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRatesPort.getClientId());
			cst.setInt(2, clientRatesPort.getCarrierId());
			cst.setInt(3, clientRatesPort.getPortOrigin());
			cst.setInt(4, clientRatesPort.getPortDestination());
			cst.setInt(5, clientRatesPort.getRateType().getValueId());
			
			rs = cst.executeQuery();
			clientRates = new ArrayList<ClientRate>();
			if(rs.next()){
				do{
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
					_tmpClientRate.getRateType().setValueId(rs.getInt(12));
					
					clientRates.add(_tmpClientRate);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}		
		return clientRates;
	}
	
	
	public List<CarrierRate> loadCarrierRates(CarrierPorts carrierPorts) throws Exception{
		List<CarrierRate> carrierRates = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_LOAD_ALL_PORT_RATES(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierPorts.getCarrierId());
			cst.setInt(2, carrierPorts.getPortOrigin());
			cst.setInt(3, carrierPorts.getPortDestination());
			cst.setInt(4, carrierPorts.getRateType().getValueId());
			rs = cst.executeQuery();
			carrierRates = new ArrayList<CarrierRate>();
			if(rs.next()){
				do{
					CarrierRate _tmpCarrierRate = new CarrierRate();
					_tmpCarrierRate.setCarrierRateId(rs.getInt(1));
					_tmpCarrierRate.getChargeType().setValueId(rs.getInt(2));
					_tmpCarrierRate.setRate(rs.getDouble(3));

					_tmpCarrierRate.setMinimum(rs.getDouble(4));

					_tmpCarrierRate.setOtherCharge(rs.getBoolean(5));
					_tmpCarrierRate.setCreatedDate(rs.getDate(6));
					_tmpCarrierRate.getChargeType().setValue1(rs.getString(7));
					_tmpCarrierRate.setFlat(rs.getBoolean(8));
					_tmpCarrierRate.getRateType().setValueId((rs.getInt(9)));
					carrierRates.add(_tmpCarrierRate);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}		
		return carrierRates;
	}

	
	public List<BLDeclaredValue> loadBlDeclaredValues(Bl bl) throws Exception {
		
		List<BLDeclaredValue> blDeclaredValues = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_declared_values_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, bl.getBlId());
			rs = cst.executeQuery();
			blDeclaredValues = new ArrayList<BLDeclaredValue>();
			if(rs.next()){
				do{
					BLDeclaredValue _tmpBlDeclaredValue = new BLDeclaredValue();
					_tmpBlDeclaredValue.setInvoiceNumber(rs.getString(1));					
					_tmpBlDeclaredValue.setTotalInvoce(rs.getDouble(2));
					_tmpBlDeclaredValue.getSupplier().setPartnerId(rs.getInt(3));
					_tmpBlDeclaredValue.getSupplier().setName(rs.getString(4));
					
					
					blDeclaredValues.add(_tmpBlDeclaredValue);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blDeclaredValues;
	}
	
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_carriers_LOAD_EFFECTIVE_DATE(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierPort.getCarrierId());
			cst.setInt(2, carrierPort.getPortOrigin());
			cst.setInt(3, carrierPort.getPortDestination());
			rs = cst.executeQuery();
			if(rs.next()){
				carrierPort.setEffectiveDate(rs.getDate(1));
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return carrierPort;		
	}
	
	public List<ItemProrated> fillBlItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception{		
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = getConnection();
			
			for(ItemProrated _tmpItemProrated : itemsProrated){
				if(_tmpItemProrated.isByInvoice()){
					sql = "{call sp_invoice_LOAD(?)}";
					cst = conn.prepareCall(sql);
					cst.setString(1, _tmpItemProrated.getInvoice().getInvoiceNumber());					
					rs = cst.executeQuery();
					
					if(rs.next()){
							_tmpItemProrated.setOrder(rs.getString(6));
					}					
				}
				if(_tmpItemProrated.isByPoNumber()){
					sql = "{call sp_client_orders_LOAD(?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpItemProrated.getClientOrder().getClientOrderId());					
					rs = cst.executeQuery();
					
					if(rs.next()){
							_tmpItemProrated.getSupplier().setName((rs.getString(56)));
					}		
				}
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return itemsProrated;
	}

	@Override
	public List<BlInlandCS> createInlandCSFromClientOrder(BlItem blItem) throws Exception{
		List<BlInlandCS> blInlandsCS = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_inland_cs_CREATE_FROM_CLIENT_ORDER(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blItem.getBlId());
			cst.setInt(2, blItem.getClientOrderId());
			rs = cst.executeQuery();
			
			sql = "{call lotradingdb.sp_bl_inland_cs_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blItem.getBlId());
			rs = cst.executeQuery();
			blInlandsCS = new ArrayList<BlInlandCS>();
			if(rs.next()){
				do{
					BlInlandCS _tmpBlInlandCS = new BlInlandCS();
					_tmpBlInlandCS.setInlandCSId(rs.getInt(1));
					_tmpBlInlandCS.setCost(rs.getDouble(2));
					_tmpBlInlandCS.setSale(rs.getDouble(3));
					_tmpBlInlandCS.getTruckCompany().setValueId(rs.getInt(4));
					_tmpBlInlandCS.setTrackingNumber(rs.getString(5));
					_tmpBlInlandCS.setCreatedDate(rs.getDate(6));
					_tmpBlInlandCS.setPoNumber(rs.getString(7));
					blInlandsCS.add(_tmpBlInlandCS);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return blInlandsCS;
	}
}