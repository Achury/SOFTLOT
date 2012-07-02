package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbDao;
import com.lotrading.softlot.LODepartment.shipments.entity.Awb;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbDeclaredValue;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbEEI;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;
import com.lotrading.softlot.LODepartment.shipments.entity.ItemProrated;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbDaoImpl extends DAOTemplate implements IAwbDao {

	private Log log = LogFactory.getLog(AwbDaoImpl.class);
	
	public AwbDaoImpl(){

	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public Awb createAwb(Awb awb) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.setInt(3, awb.getAwbType().getValueId());
			cst.setBoolean(4, awb.isCompleted());
			cst.setInt(5, awb.getSupplier().getPartnerId());
			cst.setInt(6, awb.getPickupFrom().getShipPickUpId());
			cst.setInt(7, awb.getClient().getPartnerId());
			cst.setInt(8, awb.getShipTo().getShipPickUpId());
			cst.setInt(9, awb.getAirportOrigin().getPortId());
			cst.setInt(10, awb.getAirportDestination().getPortId());
			cst.setInt(11, awb.getCarrier().getCarrierId());			
			cst.setString(12, awb.getAwbFullNumberExtern());
			cst.setInt(13, awb.getAwbMaster().getAwbId());
			cst.setString(14, awb.getDescription());
			cst.setString(15, awb.getClientPoNumber());
			cst.setInt(16, awb.getCarrierAgentId());
			cst.setInt(17, awb.getSalesRep().getEmployeeId());
			cst.setDouble(18, awb.getInlandFreightCost());
			cst.setDouble(19, awb.getAirFreightCost());
			cst.setDouble(20, awb.getSecurityCost());
			cst.setDouble(21, awb.getFuelSurchargeCost());
			cst.setDouble(22, awb.getLocalCost());
			cst.setDouble(23, awb.getUnCost());
			cst.setDouble(24, awb.getAwbReleaseCost());
			cst.setDouble(25, awb.getTransferToCustomWhCost());
			cst.setDouble(26, awb.getMessengerServiceCost());
			cst.setDouble(27, awb.getInlandFreightCost());
			cst.setDouble(28, awb.getAirFreightSale());
			cst.setDouble(29, awb.getSecuritySurchargeSale());
			cst.setDouble(30, awb.getFuelSurchargeSale());
			cst.setDouble(31, awb.getLocalDeliverySale());
			cst.setDouble(32, awb.getUnSale());
			cst.setDouble(33, awb.getServiceFee());
			cst.setDouble(34, awb.getTieDown());
			cst.setDouble(35, awb.getAwbReleaseSale());
			cst.setDouble(36, awb.getTransferToCustomwhSale());
			cst.setInt(37, awb.getPaymentType().getValueId());
			cst.setBoolean(38, awb.isLockCosts());
			cst.setString(39, awb.getManifestNumber());
			cst.setString(40, awb.getBooking());		
			cst.setString(41, awb.getFlightNumber());
			if(awb.getArrivalDate() != null){
				cst.setDate(42, toDate(awb.getArrivalDate()));
			}else{
				cst.setDate(42, null);
			}		
			
			cst.setString(43, awb.getDepartureTime());
			cst.setBoolean(44, awb.isNonDeclaredValue());
			cst.setBoolean(45, awb.isRated());
			cst.setBoolean(46, awb.isNoEEI());
			cst.setInt(47, awb.getPieces());
			cst.setDouble(48, awb.getTotalWeightLbs());
			cst.setDouble(49, awb.getTotalWeightVol());
			cst.setDouble(50, awb.getTotalCosts());
			cst.setDouble(51, awb.getTotalSales());
			cst.setBoolean(52, awb.isShipmentNotification());
			cst.setBoolean(53, awb.isArrivalNotification());
			cst.setBoolean(54, awb.isDocsDeliveryNotification());
			cst.setBoolean(55, awb.isShipmentDeliveryNotification());
			cst.setBoolean(56, awb.isPoeNotification());
			cst.setInt(57, awb.getRegion().getValueId());
			cst.setString(58, awb.getSaidToContain());
			cst.setDouble(59, awb.getDueAgent());
			cst.setDouble(60, awb.getDueCarrier());
			cst.setString(61, awb.getWhRemarks());
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(1);
				awb.setAwbNumber(cst.getString(2));
				awb.setAwbId(createdId);
			}			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awb;
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public boolean updateAwb(Awb awb) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awb.getAwbId());
			cst.setBoolean(2, awb.isCompleted());
			cst.setInt(3, awb.getSupplier().getPartnerId());
			cst.setInt(4, awb.getPickupFrom().getShipPickUpId());
			cst.setInt(5, awb.getClient().getPartnerId());
			cst.setInt(6, awb.getShipTo().getShipPickUpId());
			cst.setInt(7, awb.getAirportOrigin().getPortId());
			cst.setInt(8, awb.getAirportDestination().getPortId());
			cst.setInt(9, awb.getCarrier().getCarrierId());			
			cst.setString(10, awb.getAwbFullNumberExtern());
			cst.setInt(11, awb.getAwbMaster().getAwbId());
			cst.setString(12, awb.getDescription());
			cst.setString(13, awb.getClientPoNumber());
			cst.setInt(14, awb.getCarrierAgentId());
			cst.setInt(15, awb.getSalesRep().getEmployeeId());
			cst.setDouble(16, awb.getInlandFreightCost());
			cst.setDouble(17, awb.getAirFreightCost());
			cst.setDouble(18, awb.getSecurityCost());
			cst.setDouble(19, awb.getFuelSurchargeCost());
			cst.setDouble(20, awb.getLocalCost());
			cst.setDouble(21, awb.getUnCost());
			cst.setDouble(22, awb.getAwbReleaseCost());
			cst.setDouble(23, awb.getTransferToCustomWhCost());
			cst.setDouble(24, awb.getMessengerServiceCost());
			cst.setDouble(25, awb.getInlandfreightSale());
			cst.setDouble(26, awb.getAirFreightSale());
			cst.setDouble(27, awb.getSecuritySurchargeSale());
			cst.setDouble(28, awb.getFuelSurchargeSale());
			cst.setDouble(29, awb.getLocalDeliverySale());
			cst.setDouble(30, awb.getUnSale());
			cst.setDouble(31, awb.getServiceFee());
			cst.setDouble(32, awb.getTieDown());
			cst.setDouble(33, awb.getAwbReleaseSale());
			cst.setDouble(34, awb.getTransferToCustomwhSale());
			cst.setInt(35, awb.getPaymentType().getValueId());
			cst.setBoolean(36, awb.isLockCosts());
			cst.setString(37, awb.getManifestNumber());
			cst.setString(38, awb.getBooking());
			cst.setString(39, awb.getFlightNumber());
			if(awb.getArrivalDate() != null){
				cst.setDate(40, toDate(awb.getArrivalDate()));
			}else{
				cst.setDate(40, null);
			}
			cst.setString(41, awb.getDepartureTime());
			cst.setBoolean(42, awb.isNonDeclaredValue());
			cst.setBoolean(43, awb.isRated());
			cst.setBoolean(44, awb.isNoEEI());
			cst.setInt(45, awb.getPieces());
			cst.setDouble(46, awb.getTotalWeightLbs());
			cst.setDouble(47, awb.getTotalWeightVol());
			cst.setDouble(48, awb.getTotalCosts());
			cst.setDouble(49, awb.getTotalSales());
			cst.setBoolean(50, awb.isShipmentNotification());
			cst.setBoolean(51, awb.isArrivalNotification());
			cst.setBoolean(52, awb.isDocsDeliveryNotification());
			cst.setBoolean(53, awb.isShipmentDeliveryNotification());
			cst.setBoolean(54, awb.isPoeNotification());
			cst.setInt(55, awb.getRegion().getValueId());
			cst.setString(56, awb.getSaidToContain());
			cst.setDouble(57, awb.getDeclaredValue());
			cst.setDouble(58, awb.getDueAgent());
			cst.setDouble(59, awb.getDueCarrier());
			cst.setString(60, awb.getWhRemarks());
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
	 * @param awb
	 * @throws Exception 
	 */
	public List<Awb> searchAwb(Awb awb) throws Exception{
		List<Awb> awbList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if(awb != null){
				if(awb.getClient() != null){
					cst.setString(1, awb.getClient().getCode());
					cst.setInt(2, awb.getClient().getPartnerId());
				}else{
					cst.setString(1, null);
					cst.setInt(2, 0);
				}
				if(awb.getShipTo() != null){
					cst.setInt(3, awb.getShipTo().getShipPickUpId());
				}else{
					cst.setInt(3, 0);
				}
				if(awb.getAwbType() != null){
					cst.setInt(4, awb.getAwbType().getValueId());
				}else{
					cst.setInt(4, 0);
				}
				if(awb.getSalesRep() != null){
					cst.setInt(5, awb.getSalesRep().getEmployeeId());
				}else{
					cst.setInt(5, 0);
				}
				cst.setString(6, awb.getAwbFullNumberExtern());
				cst.setString(7, awb.getAwbNumber());
				cst.setString(8, awb.getClientPoNumber());
				if(awb.getAirportOrigin() != null){
					cst.setInt(9, awb.getAirportOrigin().getPortId());
				}else{
					cst.setInt(9, 0);
				}
				if(awb.getAirportDestination() != null){
					cst.setInt(10, awb.getAirportDestination().getPortId());
				}else{
					cst.setInt(10, 0);
				}
				if(awb.getCarrier() != null){
					cst.setInt(11, awb.getCarrier().getCarrierId());
				}else{
					cst.setInt(11, 0);
				}
				cst.setString(12, awb.getDescription());
				if(awb.getDateFromFilter() != null){
					cst.setTimestamp(13, toTimeStamp(awb.getDateFromFilter()));
				}else{
					cst.setTimestamp(13, null);
				}
				if(awb.getDateToFilter() != null){
					cst.setTimestamp(14, toTimeStamp(awb.getDateToFilter()));
				}else{
					cst.setTimestamp(14, null);
				}
				if(awb.getRegion() != null){
					cst.setInt(15, awb.getRegion().getValueId());
				}else{
					cst.setInt(15, 0);
				}
				if(awb.getFreightInvoice() != null){
					cst.setString(16, awb.getFreightInvoice());
				}else{
					cst.setString(16, "");
				}
				if(awb.getOtherInvoice() != null){
					cst.setString(17, awb.getOtherInvoice());
				}else{
					cst.setString(17, "");
				}
				if(awb.getWhNumber() != null){
					cst.setString(18, awb.getWhNumber());
				}else{
					cst.setString(18, "");
				}
				
				rs = cst.executeQuery();
				if(rs.next()){
					awbList = new ArrayList<Awb>();
					do{
						awb = new Awb();
						awb.setAwbId(rs.getInt(1));
						awb.setAwbNumber(rs.getString(2));
						awb.setCompleted(rs.getBoolean(3));
						awb.setCreatedDate(rs.getDate(4));
						awb.setDescription(rs.getString(5));
						awb.setClientPoNumber(rs.getString(6));
						awb.getCarrier().setCarrierId(rs.getInt(7));
						awb.getCarrier().setName(rs.getString(8));
						awb.setAwbFullNumberExtern(rs.getString(9));

						awb.getClient().setPartnerId(rs.getInt(10));
						awb.getClient().setCode(rs.getString(11));
						awb.getClient().setName(rs.getString(12));
						awb.getSupplier().setPartnerId(rs.getInt(13));
						awb.getSupplier().setName(rs.getString(14));
						
						
						awb.setArrivalDate(rs.getDate(15));
						awb.getAirportOrigin().setPortId(rs.getInt(16));
						awb.getAirportOrigin().setName(rs.getString(17));
						awb.getAirportDestination().setPortId(rs.getInt(18));
						awb.getAirportDestination().setName(rs.getString(19));
						awb.getSalesRep().setEmployeeId(rs.getInt(20));
						awb.getSalesRep().setLogin(rs.getString(21));
						
						awb.getAwbType().setValueId(rs.getInt(22));
						awb.getAwbType().setValue1(rs.getString(23));
						
						awb.setConcatenatedFreightInvoices2(rs.getString(24));
						awb.setShipmentNotification(rs.getBoolean(25));
						awb.setArrivalNotification(rs.getBoolean(26));
						awb.setDocsDeliveryNotification(rs.getBoolean(27));
						awb.setShipmentDeliveryNotification(rs.getBoolean(28));
						awb.setPoeNotification(rs.getBoolean(29));
						awb.getCarrier().setCarrierCode(rs.getString(30));
						
						awbList.add(awb);
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
		return awbList;
	}
	
	public List<Awb> loadAwbList(Awb awb) throws Exception{
		List<Awb> awbList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_list_LOAD(?,?)}";
			cst = conn.prepareCall(sql);				
			cst.setInt(1, awb.getAwbType().getValueId());
			if (awb.getAwbMaster() != null){
				cst.setString(2, awb.getAwbMaster().getAwbNumber());
			}else{
				cst.setString(2, null);
			}
			rs = cst.executeQuery();
			if(rs.next()){
				awbList = new ArrayList<Awb>();
				do{
					Awb _tmpAwb = new Awb();
					_tmpAwb.setAwbId(rs.getInt(1));
					_tmpAwb.setAwbNumber(rs.getString(2));
					_tmpAwb.getAwbType().setValueId(rs.getInt(3));
					_tmpAwb.getAwbType().setValue1(rs.getString(4));
					_tmpAwb.getSupplier().setName(rs.getString(5));
					_tmpAwb.getClient().setName(rs.getString(6));
					_tmpAwb.getCarrier().setCarrierId(rs.getInt(7));
					_tmpAwb.getCarrier().setName(rs.getString(8));
					_tmpAwb.getCarrier().setCarrierCode(rs.getString(9));
					_tmpAwb.setAwbFullNumberExtern(rs.getString(10));
					awbList.add(_tmpAwb);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbList;
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public Awb loadAwb(Awb awb) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awb.getAwbId());
			rs = cst.executeQuery();
			if(rs.next()){
				
				awb.setAwbId(rs.getInt(1));
				awb.setAwbNumber(rs.getString(2));
				awb.setCompleted(rs.getBoolean(3));
				awb.setCreatedDate(rs.getDate(4));
				awb.setDescription(rs.getString(5));
				awb.setClientPoNumber(rs.getString(6));
				awb.getCarrier().setCarrierId(rs.getInt(7));
				awb.getCarrier().setName(rs.getString(8));
				awb.setAwbFullNumberExtern(rs.getString(9));
				awb.getShipTo().setShipPickUpId(rs.getInt(10));
				awb.getShipTo().setName(rs.getString(11));
				awb.getClient().setPartnerId(rs.getInt(12));
				awb.getClient().setCode(rs.getString(13));
				awb.getClient().setName(rs.getString(14));
				awb.getPickupFrom().setShipPickUpId(rs.getInt(15));
				awb.getPickupFrom().setName(rs.getString(16));
				awb.setArrivalDate(rs.getDate(17));
				awb.getAirportOrigin().setPortId(rs.getInt(18));
				awb.getAirportOrigin().setName(rs.getString(19));
				awb.getAirportDestination().setPortId(rs.getInt(20));
				awb.getAirportDestination().setName(rs.getString(21));
				awb.getSalesRep().setEmployeeId(rs.getInt(22));
				awb.getSalesRep().setFirstName(rs.getString(23));
				awb.getSalesRep().setLastName(rs.getString(24));
				awb.getSalesRep().setLogin(rs.getString(25));
				awb.getSupplier().setPartnerId(rs.getInt(26));
				awb.getSupplier().setName(rs.getString(27));
				
				awb.getAwbType().setValueId(rs.getInt(28));
				awb.getSupplier().setPartnerId(rs.getInt(29));
				awb.getSupplier().setName(rs.getString(30));
				awb.getSupplier().getAddress().setAddressId(rs.getInt(31));
				awb.getSupplier().getAddress().setAddress(rs.getString(32));
				awb.getSupplier().getAddress().setPostalCode(rs.getString(33));
				awb.getSupplier().getAddress().getCity().setCityId(rs.getInt(34));
				awb.getSupplier().getAddress().getCity().setName(rs.getString(35));
				awb.getSupplier().getAddress().getCity().getStateProvince().setValue1(rs.getString(36));
				awb.getSupplier().getAddress().getCity().getCountry().setValue1(rs.getString(37));
				
				awb.getPickupFrom().setShipPickUpId(rs.getInt(38));
				awb.getPickupFrom().setName(rs.getString(39));
				awb.getPickupFrom().getAddress().setAddressId(rs.getInt(40));
				awb.getPickupFrom().getAddress().setAddress(rs.getString(41));
				awb.getPickupFrom().getAddress().setPostalCode(rs.getString(42));
				awb.getPickupFrom().getAddress().getCity().setCityId(rs.getInt(43));
				awb.getPickupFrom().getAddress().getCity().setName(rs.getString(44));
				awb.getPickupFrom().getAddress().getCity().getStateProvince().setValue1(rs.getString(45));
				awb.getPickupFrom().getAddress().getCity().getCountry().setValue1(rs.getString(46));
				
				awb.getClient().getAddress().setAddressId(rs.getInt(47));
				awb.getClient().getAddress().setAddress(rs.getString(48));
				awb.getClient().getAddress().setPostalCode(rs.getString(49));
				awb.getClient().getAddress().getCity().setCityId(rs.getInt(50));
				awb.getClient().getAddress().getCity().setName(rs.getString(51));
				awb.getClient().getAddress().getCity().getStateProvince().setValue1(rs.getString(52));
				awb.getClient().getAddress().getCity().getCountry().setValue1(rs.getString(53));
				
				awb.getShipTo().getAddress().setAddressId(rs.getInt(54));
				awb.getShipTo().getAddress().setAddress(rs.getString(55));
				awb.getShipTo().getAddress().getCity().setCityId(rs.getInt(56));
				awb.getShipTo().getAddress().getCity().setName(rs.getString(57));
				awb.getShipTo().getAddress().getCity().getStateProvince().setValue1(rs.getString(58));
				awb.getShipTo().getAddress().getCity().getCountry().setValue1(rs.getString(59));
				
				awb.setAwbFullNumberExtern(rs.getString(60));
				
				Awb _auxAwbMaster = new Awb();
				awb.setAwbMaster(_auxAwbMaster);
				
				awb.getAwbMaster().setAwbId(rs.getInt(61));
				awb.getAwbMaster().setAwbNumber(rs.getString(62));
				awb.setDescription(rs.getString(63));
				awb.setInlandFreightCost(rs.getDouble(64));
				awb.setAirFreightCost(rs.getDouble(65));
				awb.setSecurityCost(rs.getDouble(66));
				awb.setFuelSurchargeCost(rs.getDouble(67));
				awb.setLocalCost(rs.getDouble(68));
				awb.setUnCost(rs.getDouble(69));
				awb.setAwbReleaseCost(rs.getDouble(70));
				awb.setTransferToCustomWhCost(rs.getDouble(71));
				awb.setMessengerServiceCost(rs.getDouble(72));
				awb.setInlandfreightSale(rs.getDouble(73));
				awb.setAirFreightSale(rs.getDouble(74));
				awb.setSecuritySurchargeSale(rs.getDouble(75));
				awb.setFuelSurchargeSale(rs.getDouble(76));
				awb.setLocalDeliverySale(rs.getDouble(77));
				awb.setUnSale(rs.getDouble(78));
				awb.setServiceFee(rs.getDouble(79));
				awb.setTieDown(rs.getDouble(80));
				awb.setAwbReleaseSale(rs.getDouble(81));
				awb.setTransferToCustomwhSale(rs.getDouble(82));
				awb.getPaymentType().setValueId(rs.getInt(83));
				awb.setLockCosts(rs.getBoolean(84));
				awb.setManifestNumber(rs.getString(85));
				awb.setBooking(rs.getString(86));
				awb.setFlightNumber(rs.getString(87));
				awb.setDepartureTime(rs.getString(88));
				awb.setNonDeclaredValue(rs.getBoolean(89));
				awb.setRated(rs.getBoolean(90));
				awb.setNoEEI(rs.getBoolean(91));
				awb.setPieces(rs.getInt(92));
				awb.setShipmentNotification(rs.getBoolean(93));
				awb.setArrivalNotification(rs.getBoolean(94));
				awb.setDocsDeliveryNotification(rs.getBoolean(95));
				awb.setShipmentDeliveryNotification(rs.getBoolean(96));
				awb.setPoeNotification(rs.getBoolean(97));
				awb.getRegion().setValueId(rs.getInt(98));
				awb.getRegion().setValue1(rs.getString(99));
				awb.setCarrierAgentId(rs.getInt(100));
				awb.getAwbType().setValue1(rs.getString(101));
				awb.setSaidToContain(rs.getString(102));
				awb.setDeclaredValue(rs.getDouble(103));
				awb.getAwbMaster().setAwbFullNumberExtern(rs.getString(104));
				awb.setDueAgent(rs.getDouble(105));
				awb.setDueCarrier(rs.getDouble(106));
				awb.getCarrier().setCarrierCode(rs.getString(107));				
				awb.getAirportOrigin().setIataCode(rs.getString(108));
				awb.getAirportDestination().setIataCode(rs.getString(109));
				//awb.getClient().setSearchAndInspConsent(rs.getBoolean(110));
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awb;
	}
	
	public List<Awb> loadHouseAwbs(Awb awb) throws Exception{
		List<Awb> houseAwbs = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_houses_LOAD(?)}";
			cst = conn.prepareCall(sql);
			if(awb != null){
				cst.setInt(1, awb.getAwbId());
				rs = cst.executeQuery();
				if(rs.next()){
					houseAwbs = new ArrayList<Awb>();
					do{
						awb = new Awb();
						awb.setAwbId(rs.getInt(1));
						awb.setAwbNumber(rs.getString(2));
						awb.getClient().setPartnerId(rs.getInt(3));
						awb.getClient().setCode(rs.getString(4));
						awb.getClient().setName(rs.getString(5));
						awb.getSupplier().setPartnerId(rs.getInt(6));
						awb.getSupplier().setName(rs.getString(7));
						awb.setClientPoNumber(rs.getString(8));
						awb.setTotalWeightLbs(rs.getDouble(9));
						awb.setTotalWeightVol(rs.getDouble(10));
						awb.setPieces(rs.getInt(11));
						if(awb.getAwbMaster() == null){
							awb.setAwbMaster(new Awb());
						}
						awb.getAwbMaster().setAwbId(rs.getInt(12));
						awb.getAwbType().setValueId(rs.getInt(13));
						houseAwbs.add(awb);
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
		return houseAwbs;
	}
	
	public boolean updateAwbHouse(Awb awb) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_house_UPDATE_MASTER_ID(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awb.getAwbId());
			cst.setInt(2, awb.getAwbMaster().getAwbId());
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
	
	public List<Awb> updateAwbHouseList(List<Awb> awbHousesList) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(Awb _tmpAwbHouse : awbHousesList){
				String sql = "{call sp_awb_house_UPDATE_MASTER_ID(?,?)}";
				cst = conn.prepareCall(sql);
				cst.setInt(1, _tmpAwbHouse.getAwbId());
				cst.setInt(2, _tmpAwbHouse.getAwbMaster().getAwbId());
				cst.executeUpdate();
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbHousesList;
	}
	/**
	 * 
	 * @param awbItem
	 * @throws Exception 
	 */
	public List<AwbItem> loadAwbItems(AwbItem awbItem) throws Exception{
		List<AwbItem> awbDetails = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_items_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbItem.getAwbId());
			rs = cst.executeQuery();
			awbDetails = new ArrayList<AwbItem>();
			if(rs.next()){
				do{
					AwbItem _tmpAwbDetail = new AwbItem();
					_tmpAwbDetail.setItemId(rs.getInt(1));
					_tmpAwbDetail.setPieces(rs.getInt(2));
					_tmpAwbDetail.getType().setValueId(rs.getInt(3));
					_tmpAwbDetail.setItemLength(rs.getDouble(4));
					_tmpAwbDetail.setItemWidth(rs.getDouble(5));
					_tmpAwbDetail.setItemHeight(rs.getDouble(6));
					_tmpAwbDetail.setWeightLbs(rs.getDouble(7));
					_tmpAwbDetail.getRateClass().setValueId(rs.getInt(8));
					_tmpAwbDetail.setWhItemId(rs.getInt(9));
					_tmpAwbDetail.setPoNumber(rs.getString(10));
					_tmpAwbDetail.getInvoice().setInvoiceId(rs.getInt(11));
					_tmpAwbDetail.getInvoice().setInvoiceNumber(rs.getString(12));
					_tmpAwbDetail.setRemarks(rs.getString(13));
					_tmpAwbDetail.setCreatedDate(rs.getDate(15));
					_tmpAwbDetail.setHazardous(rs.getBoolean(16));
					_tmpAwbDetail.getWhReceipt().setWhReceiptId(rs.getInt(17));
					_tmpAwbDetail.getWhReceipt().setWhReceiptNumber(rs.getString(18));
					_tmpAwbDetail.setClientOrderId(rs.getInt(19));
					_tmpAwbDetail.getWhLocation().setWhLocationId(rs.getString(20));
					_tmpAwbDetail.getWhReceipt().setRemarks(rs.getString(21));
					if(rs.getInt(22) > 0){
						_tmpAwbDetail.setPalletId(String.valueOf(rs.getInt(22)));
					}else{
						_tmpAwbDetail.setPalletId(null);
					}
					
					awbDetails.add(_tmpAwbDetail);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbDetails;
	}
	
	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<AwbPalletizedItem> loadAwbPalletizedItems(Awb awb) throws Exception{
		List<AwbPalletizedItem> awbItems = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_palletized_items_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awb.getAwbId());
			rs = cst.executeQuery();
			awbItems = new ArrayList<AwbPalletizedItem>();
			if(rs.next()){
				do{
					AwbPalletizedItem _tmpAwbPalletizedItem = new AwbPalletizedItem();
					_tmpAwbPalletizedItem.setPalletizedItemId(rs.getInt(1));
					_tmpAwbPalletizedItem.setPieces(rs.getInt(2));
					_tmpAwbPalletizedItem.getType().setValueId(rs.getInt(3));
					_tmpAwbPalletizedItem.setItemLength(rs.getDouble(4));
					_tmpAwbPalletizedItem.setItemWidth(rs.getDouble(5));
					_tmpAwbPalletizedItem.setItemHeight(rs.getDouble(6));
					_tmpAwbPalletizedItem.setItemWeight(rs.getDouble(7));
					
					if(rs.getInt(8)>0){
						_tmpAwbPalletizedItem.setPalletId(rs.getInt(8)+"");
					}else{
						_tmpAwbPalletizedItem.setPalletId("");
					}
					
					_tmpAwbPalletizedItem.setRemarks(rs.getString(9));
					_tmpAwbPalletizedItem.getWhLocation().setWhLocationId(rs.getString(10));
					_tmpAwbPalletizedItem.setCreatedDate(rs.getDate(11));
					_tmpAwbPalletizedItem.getRateClass().setValueId(rs.getInt(12));
					_tmpAwbPalletizedItem.setItemVolume(_tmpAwbPalletizedItem.getItemHeight() * _tmpAwbPalletizedItem.getItemWidth() * _tmpAwbPalletizedItem.getItemLength()  / Math.pow(Constants.FOOT_TO_INCHES,3));

					awbItems.add(_tmpAwbPalletizedItem);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbItems;
	}

	/**
	 * 
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public List<AwbFreightInvoice> loadFreightInvoices(AwbFreightInvoice awbFreightInvoice) throws Exception{
		List<AwbFreightInvoice> awbFreightInvoices = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_freight_invoice_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbFreightInvoice.getAwbId());
			rs = cst.executeQuery();
			awbFreightInvoices = new ArrayList<AwbFreightInvoice>();
			if(rs.next()){
				do{
					AwbFreightInvoice _tmpAwbFreightInvoice = new AwbFreightInvoice();
					_tmpAwbFreightInvoice.setFreightInvoiceId(rs.getInt(1));
					_tmpAwbFreightInvoice.getInvoice().setInvoiceId(rs.getInt(2));
					_tmpAwbFreightInvoice.getInvoice().setInvoiceNumber(rs.getString(3));
					_tmpAwbFreightInvoice.setCreatedDate(rs.getDate(4));
					awbFreightInvoices.add(_tmpAwbFreightInvoice);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbFreightInvoices;
	}

	/**
	 * 
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public List<AwbOtherInvoice> loadOtherInvoices(AwbOtherInvoice awbOtherInvoice) throws Exception{
		List<AwbOtherInvoice> awbOtherInvoices = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_other_invoice_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbOtherInvoice.getAwbId());
			rs = cst.executeQuery();
			awbOtherInvoices = new ArrayList<AwbOtherInvoice>();
			if(rs.next()){
				do{
					AwbOtherInvoice _tmpAwbOtherInvoice = new AwbOtherInvoice();
					_tmpAwbOtherInvoice.setOtherInvoiceId(rs.getInt(1));
					_tmpAwbOtherInvoice.getInvoice().setInvoiceId(rs.getInt(2));
					_tmpAwbOtherInvoice.getInvoice().setInvoiceNumber(rs.getString(3));
					_tmpAwbOtherInvoice.setCreatedDate(rs.getDate(4));
					awbOtherInvoices.add(_tmpAwbOtherInvoice);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbOtherInvoices;
	}


	/**
	 * 
	 * @param awbInlandCS
	 * @throws Exception 
	 */
	public List<AwbInlandCS> loadInlandsCS(AwbInlandCS awbInlandCS) throws Exception{
		List<AwbInlandCS> awbInlandsCS = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_inland_cs_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbInlandCS.getAwbId());
			rs = cst.executeQuery();
			awbInlandsCS = new ArrayList<AwbInlandCS>();
			if(rs.next()){
				do{
					AwbInlandCS _tmpAwbInlandCS = new AwbInlandCS();
					_tmpAwbInlandCS.setInlandCsId(rs.getInt(1));
					_tmpAwbInlandCS.setCost(rs.getDouble(2));
					_tmpAwbInlandCS.setSale(rs.getDouble(3));
					_tmpAwbInlandCS.getTruckCompany().setValueId(rs.getInt(4));
					_tmpAwbInlandCS.setTrackingNumber(rs.getString(5));
					_tmpAwbInlandCS.setCreatedDate(rs.getDate(6));
					_tmpAwbInlandCS.setPoNumber(rs.getString(7));
					awbInlandsCS.add(_tmpAwbInlandCS);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbInlandsCS;
	}

	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public List<AwbUnCode> loadUnCodes(AwbUnCode awbUnCode) throws Exception{
		List<AwbUnCode> awbUnCodes = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_un_code_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbUnCode.getAwbId());
			rs = cst.executeQuery();
			awbUnCodes = new ArrayList<AwbUnCode>();
			if(rs.next()){
				do{
					AwbUnCode _tmpAwbUnCode = new AwbUnCode();
					_tmpAwbUnCode.setUnCodeId(rs.getInt(1));
					_tmpAwbUnCode.setUnCode(rs.getString(2));
					_tmpAwbUnCode.setUnClassId(rs.getInt(3));
					_tmpAwbUnCode.setPackingGroupId(rs.getInt(4));
					awbUnCodes.add(_tmpAwbUnCode);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbUnCodes;
	}

	public List<AwbCostSale> loadCostsSales(AwbCostSale awbCostSale) throws Exception {
		List<AwbCostSale> awbCostsSales = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_cost_sale_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbCostSale.getAwbId());
			rs = cst.executeQuery();
			awbCostsSales = new ArrayList<AwbCostSale>();
			if(rs.next()){
				do{
					AwbCostSale _tmpAwbCostSale = new AwbCostSale();
					_tmpAwbCostSale.setAwbId(awbCostSale.getAwbId());
					_tmpAwbCostSale.setAwbCostsaleId(rs.getInt(1));
					_tmpAwbCostSale.setCost(rs.getDouble(2));
					_tmpAwbCostSale.setSale(rs.getDouble(3));
					_tmpAwbCostSale.getChargeType().setValueId(rs.getInt(4));
					_tmpAwbCostSale.getChargeType().setValue1(rs.getString(5));
					_tmpAwbCostSale.setOtherCost(rs.getBoolean(6));
					_tmpAwbCostSale.setReviewed(rs.getBoolean(7));
					_tmpAwbCostSale.setCreatedDate(rs.getDate(8));
					_tmpAwbCostSale.setNotes(rs.getString(9));
					_tmpAwbCostSale.setShowInMaster(rs.getBoolean(10));
					_tmpAwbCostSale.setSelectedToAwbDoc(rs.getBoolean(11));
					awbCostsSales.add(_tmpAwbCostSale);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbCostsSales;
	}
	
	public List<AwbCostSale> loadInitialCostsSales(Awb awb) throws Exception {
		List<AwbCostSale> awbCostsSales = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_fixed_cost_sales_LOAD(?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, Constants.MASTER_CHARGE_TYPE_AIR);
			if(awb.getAwbType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_REGULAR){// Si el tipo de la guia es regular.
				cst.setBoolean(2, false);
			}else if(awb.getAwbType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE){// Si el tipo de la guia es house.
				cst.setBoolean(2, true);
			}
			cst.setBoolean(3, awb.isContainDangerousItems());
			cst.setBoolean(4, awb.isContainOversizeItems());
			cst.setBoolean(5, awb.isContainRefrigeratedItems());
			rs = cst.executeQuery();
			awbCostsSales = new ArrayList<AwbCostSale>();
			if(rs.next()){
				do{
					AwbCostSale _tmpAwbCostSale = new AwbCostSale();
					_tmpAwbCostSale.getChargeType().setValueId(rs.getInt(1));
					_tmpAwbCostSale.getChargeType().setValue1(rs.getString(2));
					awbCostsSales.add(_tmpAwbCostSale);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbCostsSales;
	}

	public List<AwbEEI> loadEEIs(AwbEEI awbEEI) throws Exception {
		List<AwbEEI> awbEEIList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_eei_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbEEI.getAwbId());
			rs = cst.executeQuery();
			awbEEIList = new ArrayList<AwbEEI>();
			if(rs.next()){
				do{
					AwbEEI _tmpAwbEEI = new AwbEEI();
					_tmpAwbEEI.setEeiId(rs.getInt(1));
					_tmpAwbEEI.setAwbId(rs.getInt(2));
					_tmpAwbEEI.setEei(rs.getString(3));
					_tmpAwbEEI.setSupplier(rs.getString(4));
					awbEEIList.add(_tmpAwbEEI);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbEEIList;
	}
	
	public List<ClientRate> loadClientRates(ClientRatesPort clientRatesPort) throws Exception{
		List<ClientRate> clientRates = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_rate_LOAD_ONE_PORT_RATES(?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientRatesPort.getClientId());
			cst.setInt(2, clientRatesPort.getCarrierId());
			cst.setInt(3, clientRatesPort.getPortOrigin());
			cst.setInt(4, clientRatesPort.getPortDestination());
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
					_tmpClientRate.setSelectedToAwbDoc(rs.getBoolean(12));
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
			String sql = "{call lotradingdb.sp_carriers_LOAD_ONE_PORT_RATES(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, carrierPorts.getCarrierId());
			cst.setInt(2, carrierPorts.getPortOrigin());
			cst.setInt(3, carrierPorts.getPortDestination());
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
					_tmpCarrierRate.setShowInMaster(rs.getBoolean(10));
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
	
	public List<AwbDeclaredValue> loadAwbDeclaredValues(Awb awb) throws Exception {
		
		List<AwbDeclaredValue> awbDeclaredValues = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_declared_values_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awb.getAwbId());
			rs = cst.executeQuery();
			awbDeclaredValues = new ArrayList<AwbDeclaredValue>();
			if(rs.next()){
				do{
					AwbDeclaredValue _tmpAwbDeclaredValue = new AwbDeclaredValue();
					_tmpAwbDeclaredValue.setInvoiceNumber(rs.getString(1));					
					_tmpAwbDeclaredValue.setTotalInvoce(rs.getDouble(2));
					_tmpAwbDeclaredValue.getSupplier().setPartnerId(rs.getInt(3));
					_tmpAwbDeclaredValue.getSupplier().setName(rs.getString(4));
					
					awbDeclaredValues.add(_tmpAwbDeclaredValue);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return awbDeclaredValues;
	}
	
	public List<ItemProrated> fillAwbItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception{		
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
}