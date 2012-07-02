package com.lotrading.softlot.LODepartment.clientOrder.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlExtendedDataTable;

import co.com.landsoft.devbase.util.listas.AdministradorListas;

import com.lotrading.softlot.LODepartment.clientOrder.entity.CallHistoryClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderInlandCS;
import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrderSupplierInfo;
import com.lotrading.softlot.LODepartment.clientOrder.logic.ICallhistoryClientOrderLogic;
import com.lotrading.softlot.LODepartment.clientOrder.logic.IClientOrderInlandCSLogic;
import com.lotrading.softlot.LODepartment.clientOrder.logic.IClientOrderLogic;
import com.lotrading.softlot.LODepartment.clientOrder.logic.IClientOrderSupplierInfoLogic;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.logic.IPartnerLogic;
import com.lotrading.softlot.businessPartners.logic.IShipPickupLogic;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.AlphabeticComparator;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.control.BaseControl;

public class ClientOrderControl extends BaseControl{

	private ClientOrder clientOrder;
	private ClientOrder clientOrderFilter;
	private List<ClientOrder> clientOrders;
	
	private CallHistoryClientOrder callHistory;
	private List<CallHistoryClientOrder> callsHistorySupplierFiltered;
	private List<CallHistoryClientOrder> callsHistoryClientFiltered;
	
	private ClientOrderInlandCS inlandCS;
	private ClientOrderSupplierInfo supplierInfo;
	
	private List status;
	private List<City> cities;
	private List employees;
	private List<SelectItem> regions;
	private List<Partner> suppliers;
	private List<Partner> clients;
	private List<SelectItem> consignees;
	private List<SelectItem> pickupFroms;
	private List<ShipPickUp> consigneesObjects;
	private List<ShipPickUp> pickupFromsObjects;
	private List<Carrier> carriers;
	private List<SelectItem> itemsCarriers;
	private List<SelectItem> incoterms;
	private List<SelectItem> shippingTypes;
	private List<SelectItem> truckCompanies;
	private List<SelectItem> partnerContactsList;
	
	private IClientOrderLogic clientOrderLogic;
	private IClientOrderInlandCSLogic clientOrderInlandCSLogic;
	private IClientOrderSupplierInfoLogic clientOrderSuppInfoLogic;
	private ICallhistoryClientOrderLogic callHistoryLogic;
	private IEmployeeLogic employeeLogic;
	private IShipPickupLogic shipPickupLogic;
	private IPartnerLogic partnerLogic;
	
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable table;
	private HtmlExtendedDataTable tableCallHistSupp;
	private HtmlExtendedDataTable tableCallHistClie;
	private HtmlExtendedDataTable tableInlandCS;
	private HtmlExtendedDataTable tableSuppInvoice;
	
	private String when;
	private String filtroCallHistory;
	private String selectedTab;
	private String suppCallHistTabName = "supplierCallHist";
	private String clieCallHistTabName = "clientCallHist";
	private double totalInlandSales;
	private double totalInlandCosts;
	private double totalSuppInvoice;
	private int regionSwitch;
	private String converterName;
	private String converterDollar = Constants.CONVERTER_CURRENCY_DOLLAR;
	private String converterEuro = Constants.CONVERTER_CURRENCY_EURO;
	private SelectItem invoiceTypeOriginal;
	private SelectItem invoiceTypeCopy;
	
	public ClientOrderControl(){
		clientOrder = new ClientOrder();
		clientOrderFilter = new ClientOrder();
		clientOrders = new ArrayList<ClientOrder>();
		when = "today";
		carriers = null;
		callHistory = new CallHistoryClientOrder();
		consignees = new ArrayList<SelectItem>();
		pickupFroms = new ArrayList<SelectItem>();
		clientOrder.setSalesRep(new Employee(this.getEmployeeLoggedId()));
		clientOrder.setDateFromFilter(new Date());
		clientOrder.setDateToFilter(new Date());
		changeDateFilter();
	}
	
	public void clearFilterFields(){
		clientOrderFilter = new ClientOrder();
		when = "all";
	}
	
	public void searchClientOrderAction(){
		try {
			clientOrders = clientOrderLogic.searchClientOrders(clientOrderFilter);
			if (clientOrders == null || clientOrders.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void selectClientOrderActionAux(ActionEvent event){
		ClientOrder clientOrder = (ClientOrder) table.getRowData();
		setSessionAttribute("_tmpClientOrder", clientOrder);
		clientOrder = new ClientOrder();
	}
	
	/* Este metodo se llamara desde el set del ultimo logic del faces-config.xml, osea que se 
	 * llamara en el set de shipPickupLogic*/
	public void selectClientOrderAction(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status");
		if(null != id && !id.equals("")) {
			if(id.equals("nuevo")) {
				newClientOrderAction();
			} else if(id.equals("existe")) {
				ClientOrder _tmpClieOrd = new ClientOrder();
				_tmpClieOrd = (ClientOrder) getSessionAttribute("_tmpClientOrder");
				id = null;
				removeSessionAttribute("_tmpClientOrder");
				try {
					clientOrder.setClientOrderId(_tmpClieOrd.getClientOrderId());
					
					clientOrder = clientOrderLogic.loadClientOrder(clientOrder);
					
					clientOrder.setClientOrderId(_tmpClieOrd.getClientOrderId());
					clientOrder.setClientOrderNo(_tmpClieOrd.getClientOrderNo());
					clientOrder.setCreatedDate(_tmpClieOrd.getCreatedDate());
					clientOrder.setNumberPO(_tmpClieOrd.getNumberPO());
					clientOrder.setPickupDate(_tmpClieOrd.getPickupDate());
					clientOrder.setScheduledPickupDate(_tmpClieOrd.getScheduledPickupDate());
					clientOrder.setEta(_tmpClieOrd.getEta());
					clientOrder.setInvoiceId(_tmpClieOrd.getInvoiceId());
					clientOrder.getClient().setPartnerId(_tmpClieOrd.getClient().getPartnerId());
					clientOrder.getClient().setCode(_tmpClieOrd.getClient().getCode());
					clientOrder.getClient().setName(_tmpClieOrd.getClient().getName());
					clientOrder.getSupplier().setPartnerId(_tmpClieOrd.getSupplier().getPartnerId());
					clientOrder.getSupplier().setName(_tmpClieOrd.getSupplier().getName());
					clientOrder.setStatus(_tmpClieOrd.getStatus());
					clientOrder.setSalesRep(_tmpClieOrd.getSalesRep());
					
					ClientOrderInlandCS _tmpInlandCS = new ClientOrderInlandCS();
					_tmpInlandCS.setClientOrderId(clientOrder.getClientOrderId());
					clientOrder.setInlandCostSaleList(clientOrderLogic.loadClientOrderInlandCS(_tmpInlandCS));
					clientOrder.getInlandCostSaleList().add(new ClientOrderInlandCS());
					
					ClientOrderSupplierInfo _tmpSuppInvo = new ClientOrderSupplierInfo();
					_tmpSuppInvo.setClientOrderId(clientOrder.getClientOrderId());
					clientOrder.setSupplierInfoList(clientOrderLogic.loadClientOrderSupplierInfo(_tmpSuppInvo));
					clientOrder.getSupplierInfoList().add(new ClientOrderSupplierInfo());
					
				} catch (Exception e) {
					setErrorMessage("Error trying to retrieve the Client Order Info.\n"+ e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/*public String selectClientOrderAction(){
		ClientOrder _tmpClieOrd = (ClientOrder) table.getRowData();
		try {
			clientOrder.setClientOrderId(_tmpClieOrd.getClientOrderId());
			
			clientOrder = clientOrderLogic.loadClientOrder(clientOrder);
			
			clientOrder.setClientOrderId(_tmpClieOrd.getClientOrderId());
			clientOrder.setClientOrderNo(_tmpClieOrd.getClientOrderNo());
			clientOrder.setCreatedDate(_tmpClieOrd.getCreatedDate());
			clientOrder.setNumberPO(_tmpClieOrd.getNumberPO());
			clientOrder.setPickupDate(_tmpClieOrd.getPickupDate());
			clientOrder.setScheduledPickupDate(_tmpClieOrd.getScheduledPickupDate());
			clientOrder.setEta(_tmpClieOrd.getEta());
			clientOrder.setInvoiceId(_tmpClieOrd.getInvoiceId());
			clientOrder.getClient().setPartnerId(_tmpClieOrd.getClient().getPartnerId());
			clientOrder.getClient().setCode(_tmpClieOrd.getClient().getCode());
			clientOrder.getClient().setName(_tmpClieOrd.getClient().getName());
			clientOrder.getSupplier().setPartnerId(_tmpClieOrd.getSupplier().getPartnerId());
			clientOrder.getSupplier().setName(_tmpClieOrd.getSupplier().getName());
			clientOrder.setStatus(_tmpClieOrd.getStatus());
			clientOrder.setSalesRep(_tmpClieOrd.getSalesRep());
			
			ClientOrderInlandCS _tmpInlandCS = new ClientOrderInlandCS();
			_tmpInlandCS.setClientOrderId(clientOrder.getClientOrderId());
			clientOrder.setInlandCostSaleList(clientOrderLogic.loadClientOrderInlandCS(_tmpInlandCS));
			clientOrder.getInlandCostSaleList().add(new ClientOrderInlandCS());
			
			ClientOrderSupplierInfo _tmpSuppInvo = new ClientOrderSupplierInfo();
			_tmpSuppInvo.setClientOrderId(clientOrder.getClientOrderId());
			clientOrder.setSupplierInfoList(clientOrderLogic.loadClientOrderSupplierInfo(_tmpSuppInvo));
			clientOrder.getSupplierInfoList().add(new ClientOrderSupplierInfo());
			
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Client Order Info.\n"+ e.getMessage());
			e.printStackTrace();
		}
		return "clientOrder";
	}*/
	
	public void newClientOrderAction(){
		clientOrder = new ClientOrder();
		Employee _tmpEmpl = this.getEmployeeLogged();
		clientOrder.setSalesRep(_tmpEmpl);
		MasterValue _tmpMv = new MasterValue();
		_tmpMv.setValueId(_tmpEmpl.getRegion().getValueId());
		_tmpMv.setValue1(_tmpEmpl.getRegion().getValue1());
		clientOrder.setRegion(_tmpMv);
		clientOrder.getStatus().setValueId(Constants.MASTER_VALUE_STATUS_UNCONFIRMED);
		clientOrder.setCreatedDate(new Date());
		regionSwitch = clientOrder.getRegion().getValueId();
	}
	
	public void saveClientOrderAction(){
		try {
			clientOrder = clientOrderLogic.saveClientOrder(clientOrder);
			if(clientOrder.getClientOrderId() > 0){
				setInfoMessage("Client Order was successfully saved");
				newSupplierInfoAction();
				newInlandCSAction();
			}else{
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Client Order.\n"+ e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void newInlandCSAction(){
		if(clientOrder.getInlandCostSaleList() == null){
			clientOrder.setInlandCostSaleList(new ArrayList<ClientOrderInlandCS>());
			clientOrder.getInlandCostSaleList().add(new ClientOrderInlandCS());
		}else{
			int _tmpSize = clientOrder.getInlandCostSaleList().size();
			if (_tmpSize > 0) {
				ClientOrderInlandCS _tmpInland = clientOrder.getInlandCostSaleList().get(_tmpSize - 1);
				if (!_tmpInland.isEmpty()) {
					clientOrder.getInlandCostSaleList().add(new ClientOrderInlandCS());
				}
			} else if (_tmpSize == 0) {
				clientOrder.getInlandCostSaleList().add(new ClientOrderInlandCS());
			}
		}
	}
	
	public void saveInlandCSAction(){
		if(clientOrder.getInlandCostSaleList() != null){
			for(ClientOrderInlandCS _tmpInland : clientOrder.getInlandCostSaleList()){
				try {
					if(!_tmpInland.isEmpty()){
						_tmpInland.setClientOrderId(clientOrder.getClientOrderId());
						int _tmpId = clientOrderInlandCSLogic.saveClientOrderInlandCS(_tmpInland);	
						_tmpInland.setClientOrderInlandCSId(_tmpId);
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to save the Inland Info.\n"+ e.getMessage());
					e.printStackTrace();
				}
			}
			newInlandCSAction();
		}
	}
	
	public void selectToDeleteInlandCSAction(){	
		inlandCS = new ClientOrderInlandCS();
		setInlandCS((ClientOrderInlandCS)tableInlandCS.getRowData());
	}
	
	public void deleteInlandCSAction(){
		try {
			boolean deleted = clientOrderInlandCSLogic.deleteClientOrderInlandCS(inlandCS);
			if(deleted){
				ClientOrderInlandCS _tmpInlandCS = new ClientOrderInlandCS();
				_tmpInlandCS.setClientOrderId(clientOrder.getClientOrderId());
				clientOrder.setInlandCostSaleList(clientOrderLogic.loadClientOrderInlandCS(_tmpInlandCS));
			}else{
				setWarningMessage("Item was not deleted");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Inland Info.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void newSupplierInfoAction(){
		if(clientOrder.getSupplierInfoList() == null){
			clientOrder.setSupplierInfoList(new ArrayList<ClientOrderSupplierInfo>());
			clientOrder.getSupplierInfoList().add(new ClientOrderSupplierInfo());
		}else{
			int _tmpSize = clientOrder.getSupplierInfoList().size();
			if(_tmpSize > 0){
				ClientOrderSupplierInfo _tmpSupplierInfo = clientOrder.getSupplierInfoList().get(_tmpSize - 1);
				if(!_tmpSupplierInfo.isEmpty()){
					clientOrder.getSupplierInfoList().add(new ClientOrderSupplierInfo());
				}
			}else if(_tmpSize == 0){
				clientOrder.getSupplierInfoList().add(new ClientOrderSupplierInfo());
			}
		}
	}
	
	public void saveSupplierInfoAction(){
		if(clientOrder.getSupplierInfoList() != null){
			for(ClientOrderSupplierInfo _tmpSuppInv : clientOrder.getSupplierInfoList()){
				try {
					if(!_tmpSuppInv.isEmpty()){
						_tmpSuppInv.setClientOrderId(clientOrder.getClientOrderId());
						int _tmpId = clientOrderSuppInfoLogic.saveClientOrderSupplierInfo(_tmpSuppInv);	
						_tmpSuppInv.setClientOrderSupplierInfoId(_tmpId);
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to save the Supplier Info.\n"+ e.getMessage());
					e.printStackTrace();
				}
			}
			newSupplierInfoAction();
		}
	}
	
	public void selectToDeleteSupplierInfoAction(){	
		supplierInfo = new ClientOrderSupplierInfo();
		setSupplierInfo((ClientOrderSupplierInfo)tableSuppInvoice.getRowData());
	}
	
	public void deleteSupplierInfoAction(){
		try {
			boolean deleted = clientOrderSuppInfoLogic.deleteClientOrderSupplierInfo(supplierInfo);
			if(deleted){
				ClientOrderSupplierInfo _tmpSuppInvo = new ClientOrderSupplierInfo();
				_tmpSuppInvo.setClientOrderId(clientOrder.getClientOrderId());
				clientOrder.setSupplierInfoList(clientOrderLogic.loadClientOrderSupplierInfo(_tmpSuppInvo));
			}else{
				setWarningMessage("Item was not deleted");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Inland Info.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadContacts( int partnerId){
		List<PartnerContact> partnerContacts = null;
		try {
			partnerContacts = partnerLogic.loadContacts(new Partner(partnerId));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the contacts.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		partnerContactsList = new ArrayList<SelectItem>();
		for (PartnerContact _tmpContact : partnerContacts) {
			SelectItem item = new SelectItem(new Integer(
					_tmpContact.getContactId()), _tmpContact.getName());
			partnerContactsList.add(item);
		}
	}
	
	public void saveCallHistoryAction(){
		try {
			callHistory.setClientOrderId(clientOrder.getClientOrderId());
			int _tmpReturn  = callHistoryLogic.saveCallHistory(callHistory);
			if(_tmpReturn > 0){
				callHistory.setCallId(_tmpReturn);
				if(selectedTab.equals(clieCallHistTabName)){
					if(clientOrder.getClientCallsHistory() != null){
						if(!clientOrder.getClientCallsHistory().isEmpty()){
							int _tmpIndex = clientOrder.getClientCallsHistory().indexOf(callHistory);
							if(_tmpIndex >= 0){
								clientOrder.getClientCallsHistory().set(_tmpIndex, callHistory);
							}else{
								clientOrder.getClientCallsHistory().add(0, callHistory);
							}
						}
					}else{
						clientOrder.setClientCallsHistory(new ArrayList<CallHistoryClientOrder>());
						clientOrder.getClientCallsHistory().add(callHistory);
					}
					callsHistoryClientFiltered = clientOrder.getClientCallsHistory();
				}else if(selectedTab.equals(suppCallHistTabName)){
					if(clientOrder.getSupplierCallsHistory() != null){
						if(!clientOrder.getSupplierCallsHistory().isEmpty()){
							int _tmpIndex = clientOrder.getSupplierCallsHistory().indexOf(callHistory);
							if(_tmpIndex >= 0){
								clientOrder.getSupplierCallsHistory().set(_tmpIndex, callHistory);
							}else{
								clientOrder.getSupplierCallsHistory().add(0, callHistory);
							}
						}
					}else{
						clientOrder.setSupplierCallsHistory(new ArrayList<CallHistoryClientOrder>());
						clientOrder.getSupplierCallsHistory().add(callHistory);
					}
					callsHistorySupplierFiltered = clientOrder.getSupplierCallsHistory();
				}
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Call History.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void newCallHistoryAction(){
		callHistory = new CallHistoryClientOrder();
		if(selectedTab.equals(clieCallHistTabName)){
			callHistory.getPartner().setPartnerId(clientOrder.getClient().getPartnerId());
			loadContacts(clientOrder.getClient().getPartnerId());
		}else if(selectedTab.equals(suppCallHistTabName)){
			callHistory.getPartner().setPartnerId(clientOrder.getSupplier().getPartnerId());
			loadContacts(clientOrder.getSupplier().getPartnerId());
		}
		callHistory.getEmployeeCreator().setEmployeeId(this.getEmployeeLoggedId());
		callHistory.setEnteredDate(new Date());
	}
	
	public void selectCallHistAction(){
		if(selectedTab.equals(suppCallHistTabName)){
			callHistory = (CallHistoryClientOrder) tableCallHistSupp.getRowData();
			loadContacts(clientOrder.getSupplier().getPartnerId());
		}else if(selectedTab.equals(clieCallHistTabName)){
			callHistory = (CallHistoryClientOrder) tableCallHistClie.getRowData();
			loadContacts(clientOrder.getClient().getPartnerId());
		}
	}
	
	public String changeCurrentTabSupplierCall() {
		selectedTab = suppCallHistTabName;
		filtroCallHistory = "";
		viewSupplierCallHistoryAction();
		return null;
	}
	
	public String changeCurrentTabClientCall() {
		selectedTab = clieCallHistTabName;
		filtroCallHistory = "";
		viewClientCallHistoryAction();
		return null;
	}
	
	private void viewSupplierCallHistoryAction() {
		try {
			if (clientOrder.getSupplierCallsHistory() == null) {
				clientOrder.setSupplierCallsHistory(new ArrayList<CallHistoryClientOrder>());
			}
			callHistory.setClientOrderId(clientOrder.getClientOrderId());
			callHistory.getPartner().setPartnerId(clientOrder.getSupplier().getPartnerId());
			clientOrder.setSupplierCallsHistory(callHistoryLogic.filterByNotes(callHistory));
			callsHistorySupplierFiltered = clientOrder.getSupplierCallsHistory();
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the calls History.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
		
	private void viewClientCallHistoryAction() {
		try {
			if (clientOrder.getClientCallsHistory() == null) {
				clientOrder.setClientCallsHistory(new ArrayList<CallHistoryClientOrder>());
			}
			callHistory.setClientOrderId(clientOrder.getClientOrderId());
			callHistory.getPartner().setPartnerId(clientOrder.getClient().getPartnerId());
			clientOrder.setClientCallsHistory(callHistoryLogic.filterByNotes(callHistory));
			
			callsHistoryClientFiltered = clientOrder.getClientCallsHistory();
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the calls History.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void filterClientCallsHistoryAction() {
		if (clientOrder.getClientCallsHistory() != null) {
			if(!clientOrder.getClientCallsHistory().isEmpty()){
				callsHistoryClientFiltered = new ArrayList<CallHistoryClientOrder>();
				List<CallHistoryClientOrder> _tmp = clientOrder.getClientCallsHistory();
				for (CallHistoryClientOrder _tmpCall : _tmp) {
					if (_tmpCall.getNotes() != null
							&& _tmpCall.getNotes().toUpperCase()
									.indexOf(filtroCallHistory.toUpperCase()) >= 0) {
						callsHistoryClientFiltered.add(_tmpCall);
					} else if (_tmpCall.getContact() != null
							&& _tmpCall.getContact().getName() != null) {
						if (_tmpCall.getContact().getName().toUpperCase()
								.indexOf(filtroCallHistory.toUpperCase()) >= 0) {
							callsHistoryClientFiltered.add(_tmpCall);
						}
					}
				}
			}	
		}
	}
	
	public void filterSupplierCallsHistoryAction() {
		
		if (clientOrder.getSupplierCallsHistory() != null) {
			if(!clientOrder.getSupplierCallsHistory().isEmpty()){
				List<CallHistoryClientOrder> _tmp = clientOrder.getSupplierCallsHistory();
				callsHistorySupplierFiltered = new ArrayList<CallHistoryClientOrder>();
				for (CallHistoryClientOrder _tmpCall : _tmp) {
					if (_tmpCall.getNotes() != null
							&& _tmpCall.getNotes().toUpperCase()
									.indexOf(filtroCallHistory.toUpperCase()) >= 0) {
						callsHistorySupplierFiltered.add(_tmpCall);
					} else if (_tmpCall.getContact() != null
							&& _tmpCall.getContact().getName() != null) {
						if (_tmpCall.getContact().getName().toUpperCase()
								.indexOf(filtroCallHistory.toUpperCase()) >= 0) {
							callsHistorySupplierFiltered.add(_tmpCall);
						}
					}
				}
			}
		}
	}
	
	public List<Partner> autoCompleteSuppliers(Object suggest) {
		String name = (String) suggest;
		ArrayList<Partner> result = new ArrayList<Partner>();
		try {
			for (Partner tmp_partner : getSuppliers()) {
				if ((tmp_partner != null
						&& tmp_partner.getName().toLowerCase()
								.indexOf(name.toLowerCase()) == 0 || ""
							.equals(name))) {
					result.add(tmp_partner);
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Partner> autoCompleteClients(Object suggest) {
		String name = (String) suggest;
		ArrayList<Partner> result = new ArrayList<Partner>();
		try {
			for (Partner tmp_partner : getClients()) {
				if ((tmp_partner != null
						&& tmp_partner.getName().toLowerCase()
								.indexOf(name.toLowerCase()) == 0 || ""
							.equals(name))) {
					result.add(tmp_partner);
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public List<City> autocompleteCity(Object suggest){
		String pref = (String) suggest;
		ArrayList<City> result = new ArrayList<City>();
		for (City city : getCities()) {
			if ((city != null
					&& city.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0 || ""
						.equals(pref))) {
				result.add(city);
			}
		}
		return result;
	}
	 
	public void loadConsignees(){
		if(getClientOrder().getClient() != null){
			if(getClientOrder().getClient().getPartnerId() > 0){
				ShipPickUp _tmpShipPickup = new ShipPickUp();
				_tmpShipPickup.setPartnerId(getClientOrder().getClient().getPartnerId());
				try {
					consigneesObjects = shipPickupLogic.filterByShipPickup(_tmpShipPickup);
					for(ShipPickUp _tmp : consigneesObjects){
						consignees.add(new SelectItem(new Integer(_tmp.getShipPickUpId()),_tmp.getName()));
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to get the Consignees.\n"+ e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	public void loadPickupsFrom(){
		if(getClientOrder().getSupplier() != null){
			if(getClientOrder().getSupplier().getPartnerId() > 0){
				ShipPickUp _tmpShipPickup = new ShipPickUp();
				_tmpShipPickup.setPartnerId(getClientOrder().getSupplier().getPartnerId());
				try {
					pickupFromsObjects = shipPickupLogic.filterByShipPickup(_tmpShipPickup);
					for(ShipPickUp _tmp : pickupFromsObjects){
						pickupFroms.add(new SelectItem(new Integer(_tmp.getShipPickUpId()),_tmp.getName()));
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to get the pickups from.\n"+ e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	public void assignAddressToConsignee(){
		int idPickup = clientOrder.getShipTo().getShipPickUpId();
		if(consigneesObjects != null){
			for(ShipPickUp _tmp : consigneesObjects){
				if(_tmp.getShipPickUpId() == idPickup){
					clientOrder.getShipTo().setAddress(_tmp.getAddress());
				}
			}
		}
	}
	
	public void assignAddressToPickup(){
		int idPickup = clientOrder.getPickupFrom().getShipPickUpId();
		if(pickupFromsObjects != null){
			for(ShipPickUp _tmp : pickupFromsObjects){
				if(_tmp.getShipPickUpId() == idPickup){
					clientOrder.getPickupFrom().setAddress(_tmp.getAddress());
				}
			}
		}
	}
	
	public void changeDateFilter(){ 
		if(when.equals("all")){
			clientOrderFilter.setDateFromFilter(null);
			clientOrderFilter.setDateToFilter(null);
		}else if(when.equals("today")){
			clientOrderFilter.setDateFromFilter(new Date());
			clientOrderFilter.setDateToFilter(new Date());
		}else if(when.equals("thisWeek")){
			Calendar cal = Calendar.getInstance();
			int firstDayWeek = (cal.get(Calendar.DATE)) - (cal.get(Calendar.DAY_OF_WEEK)) + 1;
			int lastDayWeek = firstDayWeek + 6;
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), firstDayWeek);
			clientOrderFilter.setDateFromFilter(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), lastDayWeek);
			clientOrderFilter.setDateToFilter(cal.getTime());
		}else if(when.equals("thisMonth")){
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),1);
			clientOrderFilter.setDateFromFilter(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			clientOrderFilter.setDateToFilter(cal.getTime());
		}else{
			System.out.println("selecciono rango");
		}
	}
	
	public void changeYesAction(){
		clientOrder.getRegion().setValueId(regionSwitch);
	}
	
	public void changeNoAction(){
		setRegionSwitch(clientOrder.getRegion().getValueId());
	}
	
	public void changeStatusScheduled(){
		clientOrder.getStatus().setValueId(Constants.MASTER_VALUE_STATUS_SCHEDULED);
	}
	
	public void changeStatusInTransit(){
		clientOrder.getStatus().setValueId(Constants.MASTER_VALUE_STATUS_IN_TRANSIT);
	}
	
	public String generarReport(){
		return "clientOrderReport";
	}
	
	/********************************************************************************************************************
	 * 																													*
	 * 											GETTERS AND SETTERS														*
	 * 																													*
	 * ******************************************************************************************************************/
	
	
	public IClientOrderInlandCSLogic getClientOrderInlandCSLogic() {
		return clientOrderInlandCSLogic;
	}
	public void setClientOrderInlandCSLogic(IClientOrderInlandCSLogic clientOrderInlandCSLogic) {
		this.clientOrderInlandCSLogic = clientOrderInlandCSLogic;
	}
	
	public ICallhistoryClientOrderLogic getCallHistoryLogic() {
		return callHistoryLogic;
	}
	public void setCallHistoryLogic(ICallhistoryClientOrderLogic callHistoryLogic) {
		this.callHistoryLogic = callHistoryLogic;
	}

	public IClientOrderSupplierInfoLogic getClientOrderSuppInfoLogic() {
		return clientOrderSuppInfoLogic;
	}

	public void setClientOrderSuppInfoLogic(IClientOrderSupplierInfoLogic clientOrderSuppInfoLogic) {
		this.clientOrderSuppInfoLogic = clientOrderSuppInfoLogic;
	}
	
	public IEmployeeLogic getEmployeeLogic() {
		return employeeLogic;
	}

	public void setEmployeeLogic(IEmployeeLogic employeeLogic) {
		this.employeeLogic = employeeLogic;
	}

	public IPartnerLogic getPartnerLogic() {
		return partnerLogic;
	}

	public void setPartnerLogic(IPartnerLogic partnerLogic) {
		this.partnerLogic = partnerLogic;
	}

	public ClientOrder getClientOrder() {
		return clientOrder;
	}

	public void setClientOrder(ClientOrder clientOrder) {
		this.clientOrder = clientOrder;
	}

	public ClientOrder getClientOrderFilter() {
		return clientOrderFilter;
	}

	public void setClientOrderFilter(ClientOrder clientOrderFilter) {
		this.clientOrderFilter = clientOrderFilter;
	}

	public List<ClientOrder> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<ClientOrder> clientOrders) {
		this.clientOrders = clientOrders;
	}

	public CallHistoryClientOrder getCallHistory() {
		return callHistory;
	}

	public void setCallHistory(CallHistoryClientOrder callHistory) {
		this.callHistory = callHistory;
	}

	public List<CallHistoryClientOrder> getCallsHistorySupplierFiltered() {
		return callsHistorySupplierFiltered;
	}

	public void setCallsHistorySupplierFiltered(List<CallHistoryClientOrder> callsHistorySupplierFiltered) {
		this.callsHistorySupplierFiltered = callsHistorySupplierFiltered;
	}
	
	public List<CallHistoryClientOrder> getCallsHistoryClientFiltered() {
		return callsHistoryClientFiltered;
	}

	public void setCallsHistoryClientFiltered(List<CallHistoryClientOrder> callsHistoryClientFiltered) {
		this.callsHistoryClientFiltered = callsHistoryClientFiltered;
	}
	
	public ClientOrderInlandCS getInlandCS() {
		return inlandCS;
	}

	public void setInlandCS(ClientOrderInlandCS inlandCS) {
		this.inlandCS = inlandCS;
	}

	public ClientOrderSupplierInfo getSupplierInfo() {
		return supplierInfo;
	}

	public void setSupplierInfo(ClientOrderSupplierInfo supplierInfo) {
		this.supplierInfo = supplierInfo;
	}

	public List getStatus() {
		try {
			setStatus(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ClientOrderStatusList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the status.\n" + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public void setStatus(List status) {
		this.status = status;
	}
	
	public List<City> getCities() {
		try {
			setCities(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CityList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the cities.\n" + e.getMessage());
			e.printStackTrace();
		}
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List getEmployees() {
		try {
			setEmployees(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.EmployeeList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Employee List.\n" + e.getMessage());
			e.printStackTrace();
		}
		return employees;
	}

	public void setEmployees(List employees) {
		this.employees = employees;
	}

	public List<SelectItem> getRegions() {
		try {
			setRegions(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.RegionsList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Regions List.\n" + e.getMessage());
			e.printStackTrace();
		}
		return regions;
	}

	public void setRegions(List<SelectItem> regions) {
		this.regions = regions;
	}

	public List<Partner> getSuppliers() {
		try {
			setSuppliers(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.SuppliersList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the suppliers List.\n" + e.getMessage());
			e.printStackTrace();
		}
		return suppliers;
	}

	public void setSuppliers(List suppliers) {
		this.suppliers = suppliers;
	}

	public List<Partner> getClients() {
		try {
			setClients(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ClientsList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the clients List.\n" + e.getMessage());
			e.printStackTrace();
		}
		return clients;
	}

	public void setClients(List<Partner> clients) {
		this.clients = clients;
	}
	
	public List<SelectItem> getConsignees() {
		loadConsignees();
		return consignees;
	}

	public void setConsignees(List<SelectItem> consignees) {
		this.consignees = consignees;
	}

	public List<SelectItem> getPickupFroms() {
		loadPickupsFrom();
		return pickupFroms;
	}

	public void setPickupFroms(List<SelectItem> pickupFroms) {
		this.pickupFroms = pickupFroms;
	}

	public List<ShipPickUp> getConsigneesObjects() {
		return consigneesObjects;
	}

	public void setConsigneesObjects(List<ShipPickUp> consigneesObjects) {
		this.consigneesObjects = consigneesObjects;
	}

	public List<ShipPickUp> getPickupFromsObjects() {
		return pickupFromsObjects;
	}

	public void setPickupFromsObjects(List<ShipPickUp> pickupFromsObjects) {
		this.pickupFromsObjects = pickupFromsObjects;
	}

	public void setClieCallHistTabName(String clieCallHistTabName) {
		this.clieCallHistTabName = clieCallHistTabName;
	}

	public List<SelectItem> getTruckCompanies() {
		try {
			setTruckCompanies(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.TruckCompanyList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the truck companies.\n" + e.getMessage());
			e.printStackTrace();
		}
		return truckCompanies;
	}

	public void setTruckCompanies(List<SelectItem> truckCompanies) {
		Collections.sort(truckCompanies, new AlphabeticComparator());
		this.truckCompanies = truckCompanies;
	}
	
	public void setPartnerContactsList(List<SelectItem> partnerContactsList) {
		this.partnerContactsList = partnerContactsList;
	}

	public List<SelectItem> getPartnerContactsList() {
		return partnerContactsList;
	}
	
	public int getSize() {
		if (clientOrders == null) {
			return 0;
		} else {
			return clientOrders.size();
		}
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public String getFiltroCallHistory() {
		return filtroCallHistory;
	}

	public void setFiltroCallHistory(String filtroCallHistory) {
		this.filtroCallHistory = filtroCallHistory;
	}

	public String getTableState() {
		return tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public HtmlExtendedDataTable getTable() {
		return table;
	}

	public void setTable(HtmlExtendedDataTable table) {
		this.table = table;
	}

	public HtmlExtendedDataTable getTableCallHistSupp() {
		return tableCallHistSupp;
	}

	public void setTableCallHistSupp(HtmlExtendedDataTable tableCallHistSupp) {
		this.tableCallHistSupp = tableCallHistSupp;
	}
	
	public HtmlExtendedDataTable getTableCallHistClie() {
		return tableCallHistClie;
	}

	public void setTableCallHistClie(HtmlExtendedDataTable tableCallHistClie) {
		this.tableCallHistClie = tableCallHistClie;
	}

	public HtmlExtendedDataTable getTableInlandCS() {
		return tableInlandCS;
	}

	public void setTableInlandCS(HtmlExtendedDataTable tableInlandCS) {
		this.tableInlandCS = tableInlandCS;
	}

	public HtmlExtendedDataTable getTableSuppInvoice() {
		return tableSuppInvoice;
	}

	public void setTableSuppInvoice(HtmlExtendedDataTable tableSuppInvoice) {
		this.tableSuppInvoice = tableSuppInvoice;
	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public String getSuppCallHistTabName() {
		return suppCallHistTabName;
	}

	public String getClieCallHistTabName() {
		return clieCallHistTabName;
	}

	public List<Carrier> getCarriers() {
		try {
			setCarriers(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CarrierList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Carriers List.\n" + e.getMessage());
			e.printStackTrace();
		}
		return carriers;
	}

	public void setCarriers(List<Carrier> carriers) {
		this.carriers = carriers;
	}

	public List<SelectItem> getItemsCarriers() {
		getCarriers();
		itemsCarriers = new ArrayList();
		if(getClientOrder().getShippingType().getValueId() != 0){
				for(Carrier _tmpCarr : carriers){
					if(_tmpCarr.getCarrierType().getValueId() == clientOrder.getShippingType().getValueId()){
						SelectItem _tmpItem = new SelectItem(new Integer(_tmpCarr.getCarrierId()), _tmpCarr.getName());
						itemsCarriers.add(_tmpItem);
					}
				}
				return itemsCarriers;
		}
		for(Carrier _tmpCarr : carriers){
			SelectItem _tmpItem = new SelectItem(new Integer(_tmpCarr.getCarrierId()), _tmpCarr.getName());
			itemsCarriers.add(_tmpItem);
		}
		return itemsCarriers;
	}

	public void setItemsCarriers(List<SelectItem> itemsCarriers) {
		this.itemsCarriers = itemsCarriers;
	}

	public List<SelectItem> getIncoterms() {
		try {
			setIncoterms(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.IncotermList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Incoterms List.\n" + e.getMessage());
			e.printStackTrace();
		}
		return incoterms;
	}

	public void setIncoterms(List<SelectItem> incoterms) {
		Collections.sort(incoterms, new AlphabeticComparator());
		this.incoterms = incoterms;
	}

	public List<SelectItem> getShippingTypes() {
		shippingTypes = new ArrayList<SelectItem>();
		SelectItem _tmpItem = new SelectItem(new Integer(Constants.MASTER_VALUE_SHIPPING_TYPE_AIR), "Air");
		shippingTypes.add(_tmpItem);
		_tmpItem = new SelectItem(new Integer(Constants.MASTER_VALUE_SHIPPING_TYPE_OCEAN), "Ocean");
		shippingTypes.add(_tmpItem);
		return shippingTypes;
	}

	public void setShippingTypes(List<SelectItem> shippingTypes) {
		this.shippingTypes = shippingTypes;
	}

	public double getTotalInlandSales() {
		if(clientOrder.getInlandCostSaleList() != null){
			double _tmpResult = 0;
			for(ClientOrderInlandCS _tmpInlandCS : clientOrder.getInlandCostSaleList()){
				_tmpResult+=_tmpInlandCS.getSales();
			}
			totalInlandSales = Math.round( _tmpResult * 100.0 ) / 100.0;
		}
		return totalInlandSales;
	}

	public double getTotalInlandCosts() {
		if(clientOrder.getInlandCostSaleList() != null){
			double _tmpResult = 0;
			for(ClientOrderInlandCS _tmpInlandCS : clientOrder.getInlandCostSaleList()){
				_tmpResult+=_tmpInlandCS.getCost();
			}
			totalInlandCosts = Math.round( _tmpResult * 100.0 ) / 100.0;
		}
		return totalInlandCosts;
	}

	public double getTotalSuppInvoice() {
		if(clientOrder.getSupplierInfoList() != null){
			double _tmpResult = 0;
			for(ClientOrderSupplierInfo _tmpSuppInfo : clientOrder.getSupplierInfoList()){
				_tmpResult+=_tmpSuppInfo.getTotalSupplierInvoice();
			}
			totalSuppInvoice = Math.round( _tmpResult * 100.0 ) / 100.0;
		}
		return totalSuppInvoice;
	}

	public int getRegionSwitch() {
		return regionSwitch;
	}

	public void setRegionSwitch(int regionSwitch) {
		this.regionSwitch = regionSwitch;
	}

	public String getConverterName() {
		if(clientOrder.getRegion() != null){
			if(clientOrder.getRegion().getValueId() == Constants.MASTER_VALUE_REGION_GER){
				converterName = converterEuro;
			}else{
				converterName = converterDollar;
			}			
		}else{
			converterName = converterDollar;
		}	
		return converterName;
	}

	public void setConverterName(String converterName) {
		this.converterName = converterName;
	}

	public SelectItem getInvoiceTypeOriginal() {
		setInvoiceTypeOriginal(new SelectItem(new Integer(Constants.MASTER_VALUE_TYPE_ORIGINAL), "Original"));
		return invoiceTypeOriginal;
	}

	public void setInvoiceTypeOriginal(SelectItem invoiceTypeOriginal) {
		this.invoiceTypeOriginal = invoiceTypeOriginal;
	}

	public SelectItem getInvoiceTypeCopy() {
		setInvoiceTypeCopy(new SelectItem(new Integer(Constants.MASTER_VALUE_TYPE_COPY), "Copy"));
		return invoiceTypeCopy;
	}

	public void setInvoiceTypeCopy(SelectItem invoiceTypeCopy) {
		this.invoiceTypeCopy = invoiceTypeCopy;
	}
	
	public IClientOrderLogic getClientOrderLogic() {
		return clientOrderLogic;
	}
	public void setClientOrderLogic(IClientOrderLogic clientOrderLogic) {
		this.clientOrderLogic = clientOrderLogic;
	}
	
	public IShipPickupLogic getShipPickupLogic() {
		return shipPickupLogic;
	}

	public void setShipPickupLogic(IShipPickupLogic shipPickupLogic) {
		this.shipPickupLogic = shipPickupLogic;
		selectClientOrderAction();
	}
}
