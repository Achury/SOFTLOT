package com.lotrading.softlot.LODepartment.clientOrder.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;
import com.lotrading.softlot.LODepartment.clientOrder.dao.IClientOrderDao;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderDaoImpl extends DAOTemplate implements IClientOrderDao {

	private Log log = LogFactory.getLog(ClientOrderDaoImpl.class);

	public ClientOrderDaoImpl() {

	}

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception
	 */
	public List<ClientOrder> searchClientOrder(ClientOrder clientOrder) throws Exception{
		List<ClientOrder> clientOrders = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_orders_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			if (clientOrder != null) {
				if(clientOrder.getClient() != null){
					cst.setString(1, clientOrder.getClient().getCode());
				}
				/*if(clientOrder.getWhReceipt.getWhReceiptId != null){
					cst.setInt(2, clientOrder.getWhReceipt.getWhReceiptId);
				}*/
				cst.setInt(2, 0);
				if(clientOrder.getClient() != null){
					cst.setInt(3, clientOrder.getClient().getPartnerId());
				}else{
					cst.setInt(3, 0);
				}
				cst.setInt(4, clientOrder.getStatus().getValueId());
				if(clientOrder.getSalesRep() != null){
					cst.setInt(5, clientOrder.getSalesRep().getEmployeeId());
				}else{
					cst.setInt(5, 0);
				}
				if(!clientOrder.getInvoiceId().isEmpty()){
					cst.setInt(6, Integer.parseInt(clientOrder.getInvoiceId()));
				}else{
					cst.setInt(6, 0);
				}
				cst.setString(7, clientOrder.getAwbNumber());
				cst.setString(8, clientOrder.getBlNumber());
				cst.setString(9, clientOrder.getNumberPO());
				if(clientOrder.getDestinationCity() != null){
					cst.setInt(10, clientOrder.getDestinationCity().getCityId());
				}else{
					cst.setInt(10, 0);
				}
				cst.setString(11, clientOrder.getRemarks());
				cst.setString(12, clientOrder.getClientOrderNo());
				cst.setBoolean(13, clientOrder.isInlandSaleZero());
				if(clientOrder.getDateFromFilter() != null){
					cst.setTimestamp(14, toTimeStamp(clientOrder.getDateFromFilter()));
				}else{
					cst.setTimestamp(14, null);
				}
				if(clientOrder.getDateToFilter() != null){
					cst.setTimestamp(15, toTimeStamp(clientOrder.getDateToFilter()));
				}else{
					cst.setTimestamp(15, null);
				}
				cst.setBoolean(16, clientOrder.isShowShipped());
				cst.setBoolean(17, clientOrder.isShowCancelled());
				if(clientOrder.getRegion() != null){
					cst.setInt(18, clientOrder.getRegion().getValueId());
				}else{
					cst.setInt(18, 0);
				}
				
				rs = cst.executeQuery();
				if(rs.next()){
					clientOrders = new ArrayList<ClientOrder>();
					do{
						clientOrder = new ClientOrder();
						
						clientOrder.setClientOrderId(rs.getInt(1));
						clientOrder.setClientOrderNo(rs.getString(2));
						clientOrder.setCreatedDate(rs.getTimestamp(3));
						clientOrder.setNumberPO(rs.getString(4));
						clientOrder.setPickupDate(rs.getTimestamp(5));
						clientOrder.setScheduledPickupDate(rs.getTimestamp(6));
						clientOrder.setEta(rs.getTimestamp(7));
						//TODO: clientOrder.setWhReceiptId  OJO falta crear entidades del WareHouse
						if(rs.getInt(9) != 0){
							clientOrder.setInvoiceId(String.valueOf((rs.getInt(9))));
						}else{
							clientOrder.setInvoiceId("");
						}
						Partner _tmpPartner = new Partner();
						_tmpPartner.setPartnerId(rs.getInt(10));
						_tmpPartner.setCode(rs.getString(11));
						_tmpPartner.setName(rs.getString(12));
						clientOrder.setClient(_tmpPartner);
						
						_tmpPartner = new Partner();
						_tmpPartner.setPartnerId(rs.getInt(13));
						_tmpPartner.setName(rs.getString(14));
						clientOrder.setSupplier(_tmpPartner);
						
						clientOrder.setStatus(new MasterValue());
						clientOrder.getStatus().setValueId(rs.getInt(15));
						clientOrder.getStatus().setValue1(rs.getString(16));
							
						clientOrder.setSalesRep(new Employee());
						clientOrder.getSalesRep().setEmployeeId(rs.getInt(17));
						clientOrder.getSalesRep().setFirstName(rs.getString(18));
						clientOrder.getSalesRep().setLastName(rs.getString(19));
						
						clientOrders.add(clientOrder);
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
		return clientOrders;
	}

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder createClientOrder(ClientOrder clientOrder) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_orders_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.setInt(3, clientOrder.getRegion().getValueId());
			cst.setInt(4, clientOrder.getSupplier().getPartnerId());
			cst.setInt(5, clientOrder.getClient().getPartnerId());
			cst.setInt(6, clientOrder.getStatus().getValueId());
			cst.setString(7, clientOrder.getNumberPO());
			if(!clientOrder.getInvoiceId().isEmpty()){
				cst.setInt(8, Integer.parseInt(clientOrder.getInvoiceId()));
			}else{
				cst.setInt(8, 0);
			}
			cst.setString(9, clientOrder.getDescription());
			cst.setInt(10, clientOrder.getDestinationCity().getCityId());
			cst.setInt(11, clientOrder.getCarrierId());
			cst.setString(12, clientOrder.getAwb());
			cst.setString(13, clientOrder.getBl());
			cst.setInt(14, clientOrder.getShippingType().getValueId());
			cst.setDouble(15, clientOrder.getWeightKilograms());
			cst.setDouble(16, clientOrder.getWeightVolumen());
			cst.setInt(17, clientOrder.getNumPieces());
			cst.setString(18, clientOrder.getComodity());
			cst.setInt(19, clientOrder.getIncoterm().getValueId());
			cst.setInt(20, clientOrder.getIncotermCity().getCityId());
			cst.setInt(21, clientOrder.getPickupFrom().getShipPickUpId());
			cst.setInt(22, clientOrder.getShipTo().getShipPickUpId());
			cst.setDouble(23, clientOrder.getVolumeCubicMeter());
			cst.setInt(24, 0); // TODO: esto es el wh Receipt. falta modificar
			cst.setInt(25, clientOrder.getSalesRep().getEmployeeId());
			cst.setString(26, clientOrder.getRemarks());
			if(clientOrder.getScheduledPickupDate() != null){
				cst.setTimestamp(27, toTimeStamp(clientOrder.getScheduledPickupDate()));
			}else{
				cst.setTimestamp(27, null);
			}
			if(clientOrder.getPickupDate() != null){
				cst.setTimestamp(28, toTimeStamp(clientOrder.getPickupDate()));
			}else{
				cst.setTimestamp(28, null);
			}
			if(clientOrder.getWhArrivalDate() != null){
				cst.setTimestamp(29, toTimeStamp(clientOrder.getWhArrivalDate()));
			}else{
				cst.setTimestamp(29, null);
			}
			if(clientOrder.getShippingDate() != null){
				cst.setTimestamp(30, toTimeStamp(clientOrder.getShippingDate()));
			}else{
				cst.setTimestamp(30, null);
			}
			if(clientOrder.getEta() != null){
				cst.setTimestamp(31, toTimeStamp(clientOrder.getEta()));
			}else{
				cst.setTimestamp(31, null);
			}
			if(clientOrder.getCreatedDate() != null){
				cst.setTimestamp(32, toTimeStamp(clientOrder.getCreatedDate()));
			}else{
				cst.setTimestamp(32, null);
			}
			
			if(cst.executeUpdate() > 0){
				clientOrder.setClientOrderId(cst.getInt(1));
				clientOrder.setClientOrderNo(cst.getString(2));
			}	
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return clientOrder;
	}

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public boolean updateClientOrder(ClientOrder clientOrder) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_orders_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrder.getClientOrderId());
			cst.setString(2, clientOrder.getClientOrderNo());
			cst.setInt(3, clientOrder.getRegion().getValueId());
			cst.setInt(4, clientOrder.getSupplier().getPartnerId());
			cst.setInt(5, clientOrder.getClient().getPartnerId());
			cst.setInt(6, clientOrder.getStatus().getValueId());
			cst.setString(7, clientOrder.getNumberPO());
			if(!clientOrder.getInvoiceId().isEmpty()){
				cst.setInt(8, Integer.parseInt(clientOrder.getInvoiceId()));
			}else{
				cst.setInt(8, 0);
			}
			cst.setString(9, clientOrder.getDescription());
			cst.setInt(10, clientOrder.getDestinationCity().getCityId());
			cst.setInt(11, clientOrder.getCarrierId());
			cst.setString(12, clientOrder.getAwb());
			cst.setString(13, clientOrder.getBl());
			cst.setInt(14, clientOrder.getShippingType().getValueId());
			cst.setDouble(15, clientOrder.getWeightKilograms());
			cst.setDouble(16, clientOrder.getWeightVolumen());
			cst.setInt(17, clientOrder.getNumPieces());
			cst.setString(18, clientOrder.getComodity());
			cst.setInt(19, clientOrder.getIncoterm().getValueId());
			cst.setInt(20, clientOrder.getIncotermCity().getCityId());
			cst.setInt(21, clientOrder.getPickupFrom().getShipPickUpId());
			cst.setInt(22, clientOrder.getShipTo().getShipPickUpId());
			cst.setDouble(23, clientOrder.getVolumeCubicMeter());
			 cst.setInt(24, 0);// TODO: WhReceiprts
			cst.setInt(25, clientOrder.getSalesRep().getEmployeeId());
			cst.setString(26, clientOrder.getRemarks());
			if(clientOrder.getScheduledPickupDate() != null){
				cst.setTimestamp(27, toTimeStamp(clientOrder.getScheduledPickupDate()));
			}else{
				cst.setTimestamp(27, null);
			}
			if(clientOrder.getPickupDate() != null){
				cst.setTimestamp(28, toTimeStamp(clientOrder.getPickupDate()));
			}else{
				cst.setTimestamp(28, null);
			}
			if(clientOrder.getWhArrivalDate() != null){
				cst.setTimestamp(29, toTimeStamp(clientOrder.getWhArrivalDate()));
			}else{
				cst.setTimestamp(29, null);
			}
			if(clientOrder.getShippingDate() != null){
				cst.setTimestamp(30, toTimeStamp(clientOrder.getShippingDate()));
			}else{
				cst.setTimestamp(30, null);
			}
			if(clientOrder.getEta() != null){
				cst.setTimestamp(31, toTimeStamp(clientOrder.getEta()));
			}else{
				cst.setTimestamp(31, null);
			}
		
			if(cst.executeUpdate() > 0){
				updated = true;
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return updated;
	}

	/**
	 * 
	 * @param clientOrder
	 * @throws Exception 
	 */
	public ClientOrder loadClientOrder(ClientOrder clientOrder) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_orders_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrder.getClientOrderId());
			rs = cst.executeQuery();
			if(rs.next()){
				clientOrder = new ClientOrder();
				
				Address _tmpAddress = new Address();
				_tmpAddress.setAddressId(rs.getInt(1));
				_tmpAddress.setAddress(rs.getString(2));
				_tmpAddress.setPostalCode(rs.getString(3));
				_tmpAddress.getCity().setName(rs.getString(4));
				_tmpAddress.getCity().getStateProvince().setValue1(rs.getString(5));
				_tmpAddress.getCity().getCountry().setValue1(rs.getString(6));
				clientOrder.getSupplier().setAddress(_tmpAddress);
				
				_tmpAddress = new Address();
				_tmpAddress.setAddressId(rs.getInt(7));
				_tmpAddress.setAddress(rs.getString(8));
				_tmpAddress.setPostalCode(rs.getString(9));
				_tmpAddress.getCity().setName(rs.getString(10));
				_tmpAddress.getCity().getStateProvince().setValue1(rs.getString(11));
				_tmpAddress.getCity().getCountry().setValue1(rs.getString(12));
				clientOrder.getClient().setAddress(_tmpAddress);
				
				ShipPickUp _tmpShipPick = new ShipPickUp();
				_tmpShipPick.setShipPickUpId(rs.getInt(13));
				_tmpShipPick.setName(rs.getString(14));
				_tmpAddress = new Address();
				_tmpAddress.setAddressId(rs.getInt(15));
				_tmpAddress.setAddress(rs.getString(16));
				_tmpAddress.getCity().setCityId(rs.getInt(17));
				_tmpAddress.getCity().getStateProvince().setValueId(rs.getInt(18));
				_tmpAddress.getCity().getCountry().setValueId(rs.getInt(19));
				_tmpShipPick.setAddress(_tmpAddress);
				clientOrder.setPickupFrom(_tmpShipPick);
				
				_tmpShipPick = new ShipPickUp();
				_tmpShipPick.setShipPickUpId(rs.getInt(20));
				_tmpShipPick.setName(rs.getString(21));
				_tmpAddress = new Address();
				_tmpAddress.setAddressId(rs.getInt(22));
				_tmpAddress.setAddress(rs.getString(23));
				_tmpAddress.getCity().setCityId(rs.getInt(24));
				_tmpAddress.getCity().getStateProvince().setValueId(rs.getInt(25));
				_tmpAddress.getCity().getCountry().setValueId(rs.getInt(26));
				_tmpShipPick.setAddress(_tmpAddress);
				clientOrder.setShipTo(_tmpShipPick);
				
				clientOrder.getDestinationCity().setCityId(rs.getInt(27));
				clientOrder.getDestinationCity().setName(rs.getString(28));
				clientOrder.getIncoterm().setValueId(rs.getInt(29));
				clientOrder.getIncotermCity().setCityId(rs.getInt(30));
				clientOrder.getIncotermCity().setName(rs.getString(31));
				clientOrder.getSalesRep().setEmployeeId(rs.getInt(32));
				clientOrder.getRegion().setValue1(rs.getString(33));
				clientOrder.getRegion().setValueId(rs.getInt(34));
				
				clientOrder.setScheduledPickupDate(rs.getTimestamp(35));
				clientOrder.setPickupDate(rs.getTimestamp(36));
				clientOrder.setWhArrivalDate(rs.getTimestamp(37));
				clientOrder.setShippingDate(rs.getTimestamp(38));
				clientOrder.setEta(rs.getTimestamp(39));
				clientOrder.setDescription(rs.getString(40));
				clientOrder.setComodity(rs.getString(41));
				clientOrder.setRemarks(rs.getString(42));
				clientOrder.setAwb(rs.getString(43));
				clientOrder.setAwbNumber(rs.getString(44));
				clientOrder.setBl(rs.getString(45));
				clientOrder.setBlNumber(rs.getString(46));
				clientOrder.setWeightKilograms(rs.getDouble(47));
				clientOrder.setWeightVolumen(rs.getDouble(48));
				clientOrder.setVolumeCubicMeter(rs.getDouble(49));
				clientOrder.setNumPieces(rs.getInt(50));
				clientOrder.getShippingType().setValueId(rs.getInt(51));
				clientOrder.getShippingType().setValue1(rs.getString(52));
				clientOrder.setCarrierId(rs.getInt(53));
				clientOrder.setInvoiceId(String.valueOf(rs.getInt(54)));
				// TODO: clientOrder.setWhReceipt .......
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return clientOrder;
	}

	/**
	 * 
	 * @param clientOrderInlandCS
	 * @throws Exception 
	 */
	public List<ClientOrderInlandCS> loadClientOrderInlandCS(ClientOrderInlandCS clientOrderInlandCS) throws Exception {
		List<ClientOrderInlandCS> clientOrderInlands = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_order_inlandCS_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderInlandCS.getClientOrderId());
			rs = cst.executeQuery();
			clientOrderInlands = new ArrayList<ClientOrderInlandCS>();
			if(rs.next()){
				do{
					ClientOrderInlandCS _tmpClieOrdInland = new ClientOrderInlandCS();
					_tmpClieOrdInland.setClientOrderInlandCSId(rs.getInt(1));
					_tmpClieOrdInland.getTruckCompany().setValueId(rs.getInt(2));
					_tmpClieOrdInland.setCost(rs.getDouble(3));
					_tmpClieOrdInland.setSales(rs.getDouble(4));
					_tmpClieOrdInland.setTrackingNumber(rs.getString(5));
					clientOrderInlands.add(_tmpClieOrdInland);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return clientOrderInlands;
	}

	/**
	 * 
	 * @param clientOrderSupplierInfo
	 * @throws Exception 
	 */
	public List<ClientOrderSupplierInfo> loadClientOrderSupplierInfo(ClientOrderSupplierInfo clientOrderSupplierInfo) throws Exception {
		List<ClientOrderSupplierInfo> clientOrderSuppInfos = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_client_order_supplier_info_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, clientOrderSupplierInfo.getClientOrderId());
			rs = cst.executeQuery();
			clientOrderSuppInfos = new ArrayList<ClientOrderSupplierInfo>();
			if(rs.next()){
				do{
					ClientOrderSupplierInfo _tmpClieOrdSuppInfo = new ClientOrderSupplierInfo();
					_tmpClieOrdSuppInfo.setClientOrderId(clientOrderSupplierInfo.getClientOrderId());
					_tmpClieOrdSuppInfo.setClientOrderSupplierInfoId(rs.getInt(1));
					_tmpClieOrdSuppInfo.setSupplierInvoiceNum(rs.getString(2));
					_tmpClieOrdSuppInfo.setTotalSupplierInvoice(rs.getDouble(3));
					_tmpClieOrdSuppInfo.getInvoiceType().setValueId(rs.getInt(4));
					clientOrderSuppInfos.add(_tmpClieOrdSuppInfo);
				}while(rs.next());
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return clientOrderSuppInfos;
	}
}