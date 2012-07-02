package com.lotrading.softlot.LODepartment.shipments.control;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.component.html.HtmlExtendedDataTable;
import org.richfaces.component.html.HtmlScrollableDataTable;

import co.com.landsoft.devbase.util.listas.AdministradorListas;
import co.com.landsoft.reporte.util.ParametrosReporte;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.shipments.entity.Awb;
import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;
import com.lotrading.softlot.LODepartment.shipments.entity.BlEEI;
import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;
import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.Bl;
import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.ItemProrated;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlContainerLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlCostSaleLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlEEILogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlFreightInvoiceLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlInlandCSLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlItemLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlOtherInvoiceLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlPalletizedItemLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlUnCodeLogic;
import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.logic.IPartnerLogic;
import com.lotrading.softlot.businessPartners.logic.IShipPickupLogic;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PackingListItem;
import com.lotrading.softlot.invoice.entity.PalletizedItem;
import com.lotrading.softlot.invoice.logic.IInvoiceLogic;
import com.lotrading.softlot.invoice.logic.IPackingListItemLogic;
import com.lotrading.softlot.invoice.logic.IPalletizedItemLogic;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.UtilFunctions;
import com.lotrading.softlot.util.base.control.BaseControl;
import com.lotrading.softlot.warehouse.entity.WhItem;
import com.lotrading.softlot.warehouse.entity.WhLocation;
import com.lotrading.softlot.warehouse.entity.WhReceipt;
import com.lotrading.softlot.warehouse.logic.IWhItemLogic;
import com.lotrading.softlot.warehouse.logic.IWhLocationLogic;
import com.lotrading.softlot.warehouse.logic.IWhReceiptLogic;
import com.sun.corba.se.impl.orbutil.closure.Constant;



public class BlControl extends BaseControl{

	private Bl bl;
	private Bl blFilter;
	private List<Bl> bls;
	private MasterValue saidToContain;
	private WhReceipt whReceipt;
	private BlCostSale blCostSale;
	private Invoice invoice;
	private BlFreightInvoice freightInvoice;
	private BlOtherInvoice merchandiseInvoice;
	private BlItem blItem;
	private BlContainer blContainer;
	private BlInlandCS blInlandCS;
	private BlUnCode blUnCode;
	private BlEEI blEEI;
	private PartnerContact mainContactMail;
	private String bodyMessage;
	private String subjectMessage;
	
	private String tableState;
	private String tableStateCS;
	private String sortMode = "single";
	private String selectionMode = "single";
	
	private String housesTabName = "tabHouseBl";
	private String blItemsTabName = "blItems";
	private String containersTabName = "containers";	
	private String costsAndSalesTabName = "costsAndSales";
	private String moreInfoTabName = "moreInfo";
	private String productInfoTabName = "productInfo";
	
	private List<Partner> clients;
	private List<Partner> suppliers;
	private List<SelectItem> carriers;	
	private List<Invoice> invoiceList;	
	private List<Bl> blHousesAvailableList;

	private List<String> headersMasterCS ;
	private List<String> subHeadersMasterCS ;
	List<Object[]> dataMasterCS;
	
	
	
	private List<City> cities;	
	private List employees;
	private List blShippingTypes;
	private List unitTypes;
	private List containerTypes;
	private List truckCompanies;
	private List<?> invoiceItems;
	
	
	private List<SelectItem> ports;
	private List<SelectItem> whLocations;
	private List<SelectItem> pickupFroms;
	private List<SelectItem> shipTos;
	private List<SelectItem> otherChargesList;
	private List<SelectItem> saidToContainList;
	private List<SelectItem> containers;
	private List<SelectItem> blShippingTypeList;
	private List<SelectItem> blTypesList;
	private List<SelectItem> regionsList;
	private List<SelectItem> classUnCodeList;
	private List<SelectItem> packingGroupUnCodeList;
	private List<SelectItem> paymentTypeList;
	private List<SelectItem> blTypeOfMovesList;
	
	
	private List<ShipPickUp> pickupFromsObjects;
	private List<ShipPickUp> shipTosObjects;
	
	private HtmlExtendedDataTable blTable;
	private HtmlExtendedDataTable blPalletizedItemsTable;
	private HtmlExtendedDataTable blHousesAvailableTable;
	private HtmlScrollableDataTable blCostsSalesTable;
	private HtmlScrollableDataTable blOtherCostsSalesTable;	
	private HtmlExtendedDataTable blWhItemsTable;
	private HtmlExtendedDataTable blInvoiceItemsTable;
	private HtmlExtendedDataTable blContainersTable;
	private HtmlExtendedDataTable blInlandCSItemsTable;
	private HtmlDataTable blFreightInvoicesTable;
	private HtmlDataTable blMerchandiseInvoicesTable;
	private HtmlDataTable blHousesTable;
	private HtmlDataTable blItemsTable;
	private HtmlDataTable blUnCodesTable;
	private HtmlDataTable blEeiTable;
	
	private String when;
	private String selectedTab;
	private String converterName;
	private String converterDollar = Constants.CONVERTER_CURRENCY_DOLLAR;
	private String converterEuro = Constants.CONVERTER_CURRENCY_EURO;
	private boolean locked;	
	private String concatenatedFreightInvoices;
	private String concatenatedMerchandInvoices;
	
	private IBlLogic blLogic;
	private IBlItemLogic blItemLogic;
	private IBlPalletizedItemLogic blPalletizedItemLogic;
	private IShipPickupLogic shipPickupLogic;
	private IMasterValueLogic masterValueLogic;
	private IWhReceiptLogic whReceiptLogic;
	private IBlCostSaleLogic blCostSaleLogic;
	private IBlEEILogic blEEILogic;
	private IBlUnCodeLogic blUnCodeLogic;
	private IWhItemLogic whItemLogic;
	private IWhLocationLogic whLocationLogic;
	private IPalletizedItemLogic palletizedItemLogic;
	private IBlContainerLogic blContainerLogic;
	private IInvoiceLogic invoiceLogic;
	private IPackingListItemLogic packingListItemLogic;
	private IBlInlandCSLogic blInlandCSLogic;
	private IBlFreightInvoiceLogic blFreightInvoiceLogic;
	private IBlOtherInvoiceLogic blOtherInvoiceLogic;
	private IPartnerLogic partnerLogic;
	
	private int regionSwitch;
	
	private String superCSForm = "vacio";
	
	
		
	public BlControl(){
		bl = new Bl();
		blFilter = new Bl();
		bls = new ArrayList<Bl>();
		when = "today";
		pickupFroms = new ArrayList<SelectItem>();
		shipTos = new ArrayList<SelectItem>();		
		whReceipt = new WhReceipt();
		invoice = new Invoice();		
		headersMasterCS = new ArrayList<String>();
		subHeadersMasterCS = new ArrayList<String>();
		dataMasterCS = new ArrayList<Object[]>(); 
		changeDateFilter();
	
	}
	
	/*public void populateRowList() 
	{ 
	  dataMasterCS = new ArrayList<Object[]>(); 
	  Object[] names1=new Object[] {"Test1","test2"};
	  Object[] names2=new Object[] {"Test4","Test5"};	  
	  dataMasterCS.add(names1);
	  dataMasterCS.add(names2);
	}	
	*/
	public void searchBlAction(){
		try {
			bls = blLogic.searchBl(blFilter);
			if (bls == null || bls.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
	}
	
	public void clearFilterFields(){
		blFilter = new Bl();
		when = "all";
	}
	
	public void changeCurrentTabHouses() {
		selectedTab = housesTabName;		
	}
	
	public void changeCurrentTabContainers() {
		selectedTab = containersTabName;		
	}
	public void changeCurrentTabBlItems() {
		selectedTab = blItemsTabName;		
	}
	public void changeCurrentTabCostsAndSales() {
		selectedTab = costsAndSalesTabName;		
	}
	public void changeCurrentTabMoreInfo() {
		selectedTab = moreInfoTabName;		
	}
	public void changeCurrentTabProductInfo() {
		selectedTab = productInfoTabName;		
	}
	
	public void selectBlActionAux(ActionEvent event){		
		bl = (Bl) blTable.getRowData();
		setSessionAttribute("_tmpBl", bl);
		bl = new Bl();
	}
	
	/* Este metodo se llamara desde el ultimo metodo SET.*/
	public void selectBlAction(){
		/*Se modifica el método para que cargue la información en el objeto partner, eso depende de:
		 * si llega o no información en la variable id a través del request; 
		 * si llega la palabra "existe" ejecuta la información que permite mostrar los datos del partner seleccionado,
		 * si llega "nuevo", llama el método para cargar los datos nuevos,
		 * si no llegan datos, continua su proceso normal.
		 * */
			 
			String blCreatedStatus = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status");
			if(null != blCreatedStatus && !blCreatedStatus.equals("")) {
				if(blCreatedStatus.equals("nuevo")) {
					bl = (Bl) getSessionAttribute("_tmpBl");
					removeSessionAttribute("_tmpBl");
					newBlAction();
				} else if(blCreatedStatus.equals("existe")) {					
					bl = (Bl) getSessionAttribute("_tmpBl");
					blCreatedStatus = null;
					removeSessionAttribute("_tmpBl");
					
						
					converterName = converterDollar;
					locked = true;
					loadInvoiceList();
					try {
											
						bl = blLogic.loadBl(bl);
						//TODO: revisar para no volver a traer repetidos desde la bd
						//bl.getClient().setName(_tmpBl.getClient().getName());
						//bl.setSalesRep(_tmpBl.getSalesRep());
						//bl.getCarrier().setName(_tmpBl.getCarrier().getName());
						//bl.getPortOrigin().setName(_tmpBl.getPortOrigin().getName());
						//bl.getPortDestination().setName(_tmpBl.getPortDestination().getName());
										
						if(!bl.isMaster()){
							bl = blLogic.loadCostsSales(bl);
							newOtherCostSaleAction();
							
							
							bl.setBlItems(blLogic.loadBlItems(bl));							
							
							bl.setBlUnCodes(blLogic.loadUnCodes(bl));
							newItemUnCodesAction();
							
							bl.setBlEEIList(blLogic.loadEEIs(bl));
							newItemEEIAction();
							
							bl.setBlContainers(blLogic.loadBlContainers(bl));
							newBlContainerAction();
							
							bl.setBlPalletizedItems(blLogic.loadBlPalletizedItems(bl));
							
							bl.setBlInlandCS(blLogic.loadInlandCS(bl));
							newItemInlandCSAction();
							
							bl.setWhRemarks(blLogic.loadWhRemarks( bl ) );
							
							loadFreightInvoicesAction();
							loadMerchandInvoicesAction();
							
							if(bl.isHouse()){
								Bl _tmpBlAux = new Bl();
								_tmpBlAux.getBlType().setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_MASTER);
								bls = blLogic.loadBlList(_tmpBlAux);
							}
							
							
							calculateBlItemsInformation();
							calculateTotalCostsSales();
							calculateTotalContainers();
						}
						if(bl.isMaster()){/* Si la guia es MASTER carga las hijas.*/
							
							bl.setBlCostsSales(new ArrayList<BlCostSale>());
							bl.setBlOtherCostsSales(new ArrayList<BlCostSale>());							
							bl.setBlUnCodes(new ArrayList<BlUnCode>());
							bl.setBlEEIList(new ArrayList<BlEEI>());
							bl.setBlFreightInvoices(new ArrayList<BlFreightInvoice>());
							bl.setBlOtherInvoices(new ArrayList<BlOtherInvoice>());
							bl.setBlContainers(new ArrayList<BlContainer>());
							
							bl.setBlHouses(blLogic.loadHouseBls(bl));
							
							if(bl.getBlHouses() != null){ // si la master tiene Guias Hijas								
								
								for(Bl _tmpBl:  bl.getBlHouses()){
									loadBLHouseInformationToMaster(_tmpBl);
								}
								processMasterBlCSAction();
							}else{
								bl.setBlHouses(new ArrayList<Bl>()); // si la master no tiene guias hijas inicialice la lista vacia
							}
							
							//si la guia tiene hijas LCL -> cargue los contenedores de la master
							if (bl.isHouseLCL()){
								bl.setBlContainers(blLogic.loadBlContainers(bl));
								newBlContainerAction();								
							}
							
							calculateBlItemsInformation();								
							calculateTotalContainers();
								
						}
						
						
					} catch (Exception e) {
						setErrorMessage(e.getMessage());
					}
					
				}
			}
	}
	
	private void loadBLHouseInformationToMaster(Bl _tmpBl) {
		try{
		blLogic.loadCostsSales(_tmpBl); // carga todos los costos y ventas de esta guia
		bl.getBlCostsSales().addAll(_tmpBl.getBlCostsSales()); // agrega los costos de la hija a la master		
		bl.getBlOtherCostsSales().addAll(_tmpBl.getBlOtherCostsSales());// agrega los otros costos de la hija a la master				
		
		_tmpBl.setBlUnCodes(blLogic.loadUnCodes(_tmpBl)); // carga los UN codes de esta guia
		bl.getBlUnCodes().addAll(_tmpBl.getBlUnCodes()); // agrega los un codes de la hija a la master
		
		//_tmpBl.setBlEEIList(blLogic.loadEEIs(_tmpBl)); // carga los EEIs de esta guia
		//bl.getBlEEIList().addAll(_tmpBl.getBlEEIList()); // agrega los EEIs de la hija a la master
		
		_tmpBl.setBlFreightInvoices(blLogic.loadFreightInvoices(_tmpBl)); // carga las Freight Invoices de esta guia
		bl.getBlFreightInvoices().addAll(_tmpBl.getBlFreightInvoices()); // agrega las Freight Invoices de la hija a la master
		
		_tmpBl.setBlOtherInvoices(blLogic.loadOtherInvoices(_tmpBl)); // carga las Other Invoices de esta guia
		bl.getBlOtherInvoices().addAll(_tmpBl.getBlOtherInvoices()); // agrega las Other Invoices de la hija a la master
		
		// si Bl es FCL y los Hijos FCL -> se pone el resumen de containers de los hijos
		if (bl.isFCL() && _tmpBl.isFCL()){
			_tmpBl.setBlContainers(blLogic.loadBlContainers(_tmpBl)); // carga los contenedores de esta guia
			bl.getBlContainers().addAll(_tmpBl.getBlContainers()); // agrega los Contenedores de la hija a la master
		}
		
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		
	}
	
	public void loadFreightInvoicesAction(){
		try {
			bl.setBlFreightInvoices(blLogic.loadFreightInvoices(bl));
			newItemFreightInvoiceAction();
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the BL Freight Invoices. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadMerchandInvoicesAction(){
		try {
			bl.setBlOtherInvoices(blLogic.loadOtherInvoices(bl));
			newItemMerchandiseInvoiceAction();
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the AWB Merchandise Invoices. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadWhItemsAction(){
		if(whReceipt == null){
			whReceipt = new WhReceipt();
		}
		if(!whReceipt.getWhReceiptNumber().isEmpty()){
			try {
				WhReceipt _tmpReceipt = new WhReceipt();
				_tmpReceipt.setWhReceiptNumber(whReceipt.getWhReceiptNumber());
				_tmpReceipt = whReceiptLogic.loadWhReceipt(_tmpReceipt);
				if (_tmpReceipt.getClient().getName() != null || _tmpReceipt.getSupplier().getName() != null) {
					boolean hasClientOrder = false;
					whReceipt = new WhReceipt();
					whReceipt.setWhReceiptId(_tmpReceipt.getWhReceiptId());
					whReceipt.setClient(_tmpReceipt.getClient());
					whReceipt.setSupplier(_tmpReceipt.getSupplier());
					whReceipt.setWhItems(new ArrayList<WhItem>());
					whReceipt.setRemarks(_tmpReceipt.getRemarks());
					for (WhItem _tmpItem : _tmpReceipt.getWhItems()) {
						if(_tmpItem.getClientOrderId() > 0){
							hasClientOrder = true;
						}
						if (!_tmpItem.isShipped()) {
							whReceipt.getWhItems().add(_tmpItem);
						}
					}
					if (_tmpReceipt.getWhItems().size() == 0) {
						setErrorMessage("The WhReceipt does not have items.");
					} else if (_tmpReceipt.getWhItems().size() > 0
							&& whReceipt.getWhItems().size() == 0) {
						setErrorMessage("All the items in this WHReceipt were already included into an AWB or BL.");
					}else if(!hasClientOrder){
						whReceipt.getWhItems().clear();
						setErrorMessage("This Warehouse does not have a Client Order associated.");
					}
				} else {
					whReceipt = _tmpReceipt;
					setErrorMessage("The WhReceipt does not exist.");
				}
				
			} catch (Exception e) {
				setErrorMessage("Error trying to get the WhItems.\n"+ e.getMessage());
				e.printStackTrace();
			}
		}else {
			whReceipt = new WhReceipt();
		}
	}
	
	public void loadDeclaredValuesAction(){
		try {
			bl.setBlDeclaredValues(blLogic.loadBlDeclaredValues(bl));
			double suma=0;
			for(int i=0;i<bl.getBlDeclaredValues().size();i++) suma = suma+ bl.getBlDeclaredValues().get(i).getTotalInvoce();
			bl.setDeclaredValue(suma);
			
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the BL Declared Values. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadShipmentNotifAction(){
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String fecha = formatter.format(bl.getCreatedDate());
		String etd = bl.getEtd() == null? "": formatter.format(bl.getEtd());
		String eta = bl.getEta() == null? "": formatter.format(bl.getEta());
		if(bl.isRegular()){
			bodyMessage = "Cordial Saludo,%0D%0DAdjuntamos Factura: "+ bl.getConcatenatedFreightInvoices()+
				"%0DDespachado por: "+bl.getCarrier().getName()+
				"%0DEl día: "+fecha+
				"%0DCon BL #: " +bl.getFullBlNumber()+
				"%0DETD: " + etd+
				"%0DETA: " + eta+
				"%0DCorresponde a la Orden:"+bl.getClientPoNumber();
		}else if(bl.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial Saludo,%0D%0DAdjuntamos Factura: "+ bl.getConcatenatedFreightInvoices()+
				"%0DDespachado por: "+ bl.getCarrier().getName()+" / "+bl.getBlMaster().getCarrier().getName()+ 
				"%0DEl día: "+fecha+
				"%0DCon BL #:"+bl.getFullBlNumber()+ " / "+bl.getBlMaster().getFullBlNumber()+
				"%0DETD: " + etd+
				"%0DETA: " + eta+
				"%0DCorresponde a la Orden:"+bl.getClientPoNumber();
		}else if(bl.isMaster()){
			bodyMessage = "Cordial Saludo,%0D%0DAdjuntamos Factura: "+ bl.getConcatenatedFreightInvoices()+
				"%0DDespachado por: "+bl.getCarrier().getName()+
				"%0DEl día: "+fecha+
				"%0DCon BL #:"+bl.getFullBlNumber()+
				"%0DETD: " + etd+
				"%0DETA: " + eta+
				"%0DCorresponde a la Orden:"+bl.getClientPoNumber();
		}
		subjectMessage = "Shipment Notification - Order # " +bl.getClientPoNumber();
		loadContactEmailAction();
		if(!bl.isShipmentNotif()){
			bl.setShipmentNotif(true);
			saveBl();
		}
	}
	
public void loadArrivalNotifAction(){
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String etd = bl.getEtd() == null? "": formatter.format(bl.getEtd());
		String eta = bl.getEta() == null? "": formatter.format(bl.getEta());
		
		if(bl.isRegular() || bl.isMaster()){
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el House Bill of Lading # " + bl.getFullBlNumber()+
				"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+
				"%0DNaviera: "+bl.getCarrier().getName()+
				"%0DPuerto de Cargue: "+bl.getPortOrigin().getName()+
				"%0DPuerto de Descargue: "+bl.getPortDestination().getName()+
				"%0DSalió el "+ etd+
				"%0DEn el Barco/Viaje: "+bl.getVessel_voyage()+
				"%0DLlegada Estimada: "+ eta+
				"%0D%0D*FAVOR NO RESPONDER ESTE MENSAJE%0D%0DPara liberación de su Bill Of Lading en destino, favor comunicarse con:";
			subjectMessage = "BL #"  + bl.getFullBlNumber() +" - Order # "+bl.getClientPoNumber();
		}else if(bl.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el House Bill of Lading # " + bl.getBlMaster().getFullBlNumber() + " / " + bl.getFullBlNumber()+
				"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+
				"%0DNaviera: "+bl.getBlMaster().getCarrier().getName()+
				"%0DPuerto de Cargue: "+bl.getPortOrigin().getName()+
				"%0DPuerto de Descargue: "+bl.getPortDestination().getName()+
				"%0DSalió el "+ etd+
				"%0DEn el Barco/Viaje: "+bl.getVessel_voyage()+
				"%0DLlegada Estimada: "+ eta+
				"%0D%0D*FAVOR NO RESPONDER ESTE MENSAJE%0D%0DPara liberación de su Bill Of Lading en destino, favor comunicarse con:";
			subjectMessage = "BL #"  + bl.getBlMaster().getFullBlNumber() +" - Order # "+bl.getClientPoNumber();
		}
		loadContactEmailAction();
		if(!bl.isArrivalNotif()){
			bl.setArrivalNotif(true);
			saveBl();
		}
	}
	
	public void loadDocsDeliveryNotifAction(){
		if(bl.isRegular()){
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el BL # " + bl.getFullBlNumber()+
				"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+				
				"%0DEl BL original fue entregado en sus instalaciones ";
		}else if(bl.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el BL # " + bl.getFullBlNumber()+ " / "+ bl.getBlMaster().getFullBlNumber() +
					"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+				
					"%0DEl BL original fue entregado en sus instalaciones ";
		}else if(bl.isMaster()){
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el BL # " + bl.getFullBlNumber()+
					"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+				
					"%0DEl BL original fue entregado en sus instalaciones ";
		}
		subjectMessage = "Documents Delivery Notification - Order # "+bl.getClientPoNumber();
		loadContactEmailAction();
		if(!bl.isDocsDelNotif()){
			bl.setDocsDelNotif(true);
			saveBl();
		}
	}
	
	public void loadShipDeliveryNotifAction(){
		if(bl.isRegular()){
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el BL # "  + bl.getFullBlNumber()+
				"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+
				"%0DLa carga fue trasladada al deposito";
		}else if(bl.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el BL # "  + bl.getFullBlNumber()+ " / "+ bl.getBlMaster().getFullBlNumber() +
				"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+
				"%0DLa carga fue trasladada al deposito";
		}else if(bl.isMaster()){
			bodyMessage = "Cordial Saludo,%0D%0DConfirmamos que el BL # "  + bl.getFullBlNumber()+
				"%0DCorrespondiente a la orden # "+bl.getClientPoNumber()+
				"%0DLa carga fue trasladada al deposito";
		}
		subjectMessage = "Shipment Delivery Notification - Order # "+bl.getClientPoNumber();
		loadContactEmailAction();
		if(!bl.isShipDelNotif()){
			bl.setShipDelNotif(true);
			saveBl();
		}
	}

	private void loadCarrierMasterInfo(){ /* Carga la info del carrier de la guia master que tenga la guia house.*/
		for(Bl _tmpBlMaster : bls){
			if(_tmpBlMaster.getBlId() == bl.getBlMaster().getBlId()){
				bl.getBlMaster().setCarrier(_tmpBlMaster.getCarrier());
				return;
			}
		}
	}
	
	public void selectHousesFromMaster(ActionEvent event){
		Bl _tmpBl = (Bl) blHousesTable.getRowData();
		setSessionAttribute("_tmpBl", _tmpBl);
		_tmpBl = new Bl();
	}
	
	
	public void processMasterBlCSAction(){
		HashMap <Integer,String> blCSNames;
		ArrayList<String> _auxData;
		boolean wasFound = false;
		String currencySimbol= "$";
		headersMasterCS = new ArrayList<String>();
		subHeadersMasterCS = new ArrayList<String>();
		dataMasterCS = new ArrayList<Object[]>();
		
		if (converterName.equals("converterDollar")) currencySimbol = "$";
		if (converterName.equals("converterEuro")) currencySimbol = "€";
		
		blCSNames = new HashMap<Integer, String>();
		_auxData = new ArrayList<String>();
		
		if (bl.getBlCostsSales() != null && bl.getBlOtherCostsSales() != null) {
					
			 //save  labels from cost sales
			 for(BlCostSale _tmpCS:  bl.getBlCostsSales()){
				 if(_tmpCS.isShowInMaster()){
					 blCSNames.put(_tmpCS.getChargeType().getValueId(), _tmpCS.getChargeType().getValue1());
				 }
			 }
			
					
			// load Cost Sale values
			for(Bl _tmpBl:  bl.getBlHouses()){
				_auxData.add(_tmpBl.getBlNumber());	
					
				Iterator itr = blCSNames.entrySet().iterator();
				while (itr.hasNext()) {
					Map.Entry e = (Map.Entry)itr.next();				
					wasFound = false;
					// for each normal Costs and Sales
					 for(BlCostSale _tmpCS:  _tmpBl.getBlCostsSales()){
						if (e.getKey().equals(_tmpCS.getChargeType().getValueId())){
							_auxData.add(currencySimbol + _tmpCS.getCost());
							_auxData.add(currencySimbol + _tmpCS.getSale());
							wasFound = true;
							break;
						}
						
					 }					
					 
					 if (!wasFound){
						_auxData.add(currencySimbol + "0.0");
						_auxData.add(currencySimbol + "0.0");
					 }		
					 
				}
								
				//add values to show in table
				String []strArray = _auxData.toArray(new String[_auxData.size()]);		
				dataMasterCS.add(strArray);
				_auxData =  new ArrayList<String>();
				
			}
			
			
			//add headers to show in table
			subHeadersMasterCS.add("BL #");
			Iterator itr = blCSNames.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry e = (Map.Entry)itr.next();			
				headersMasterCS.add(e.getValue() + "");
				subHeadersMasterCS.add("Cost");
				subHeadersMasterCS.add("Sale");
			}
			
		}
		
	}
	
	public void loadInvoiceAction(){
		invoiceItems = null;
		String invoiceNumber = invoice.getInvoiceNumber();
		if(!invoiceNumber.isEmpty()){
			try {
				invoice = new Invoice(); 
				invoice.setInvoiceNumber(invoiceNumber);
				invoice = invoiceLogic.loadInvoice(invoice);
				
				if(invoice.getInvoiceId() > 0){
					if(invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_RM){
						loadPalletizedItemsAction();
					}else if(invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_IP){
						loadPackingListItemAction();
					}else{
						setErrorMessage("The Invoice does not have items.");
					}
				}else{
					setErrorMessage("The Invoice does not exist.");
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to get the Palletizer Items.\n"+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void loadPalletizedItemsAction(){
		try {
			invoice.setPalletizedItemsList(palletizedItemLogic.loadPalletizedItems(invoice));
			if(invoice.getPalletizedItemsList() != null){
				if(invoice.getPalletizedItemsList().size() == 0){
					setErrorMessage("The Invoice does not have items.");
				}else{
					invoiceItems = invoice.getPalletizedItemsList();
				}
			}else{
				setErrorMessage("The Invoice does not have items.");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Palletizer Items.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void loadPackingListItemAction(){
		try {
			invoice.setPackingListItemsList(packingListItemLogic.loadPackingListItems(invoice));
			if(invoice.getPackingListItemsList() != null){
				if(invoice.getPackingListItemsList().size() == 0){
					setErrorMessage("The Invoice does not have items.");
				}else{
					invoiceItems = invoice.getPackingListItemsList();
				}
			}else{
				setErrorMessage("The Invoice does not have items.");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Palletizer Items.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadBlHousesAvailableList(){
		Bl _tmpBlAux = new Bl();
		_tmpBlAux.getBlType().setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE);
		_tmpBlAux.setBlMaster(new Bl());
		_tmpBlAux.getBlMaster().setBlNumber("anything"); /* Este se llena para que en el procedimiento almacenado traiga solo
		 										blHouse (Hijas) que no esten asociados a ningun blMaster(Padre)*/
		try {
			setBlHousesAvailableList(blLogic.loadBlList(_tmpBlAux));
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Bl House List.");
			e.printStackTrace();
		}
	}
	
	public void loadContactEmailAction(){
		PartnerContact contact = new PartnerContact();
		contact.setPartnerId(bl.getClient().getPartnerId());
		contact.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
		try {
			setMainContactMail(partnerLogic.loadPartnerContactMainInfo(contact));
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Client Contact.");
			e.printStackTrace();
		}
	}
	
	public void newBlAction(  ){
		converterName = converterDollar;
		locked = true;
		Bl _auxMasterBl = new Bl();
		bl.setBlMaster(_auxMasterBl);
		Employee _tmpEmpl = this.getEmployeeLogged();
		bl.setCreatedDate(new Date());
		bl.setSalesRep(_tmpEmpl);
		bl.getRegion().setValueId(_tmpEmpl.getRegion().getValueId());
		bl.getRegion().setValue1(_tmpEmpl.getRegion().getValue1());
		regionSwitch = bl.getRegion().getValueId();	
		
	}
	
	public void assignBlTypeRegular(){
		bl = new Bl();
		MasterValue _tmpMV = new MasterValue();
		_tmpMV.setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_REGULAR);
		_tmpMV.setValue1("REGULAR");
		bl.setBlType(_tmpMV);
		setSessionAttribute("_tmpBl", bl);
	}
	
	public void assignBlTypeHouse(){
		bl = new Bl();
		MasterValue _tmpMV = new MasterValue();
		_tmpMV.setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE);
		_tmpMV.setValue1("HOUSE");
		bl.setBlType(_tmpMV);
		setSessionAttribute("_tmpBl", bl);
	}
	
	public void assignBlTypeMaster(){
		bl = new Bl();
		MasterValue _tmpMV = new MasterValue();
		_tmpMV.setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_MASTER);
		_tmpMV.setValue1("MASTER");
		bl.setBlType(_tmpMV);
		setSessionAttribute("_tmpBl", bl);
	}
	
	private void saveBl() {
		calculateBlItemsInformation();
		calculateTotalCostsSales();			
		calculateTotalContainers();
		try {
			bl.setBlId( blLogic.saveBl(bl));
		} catch (Exception e) {
			setErrorMessage("Error.\n");
			e.printStackTrace();
		}
	}
	
	public void saveBlAction(){
		boolean valid = true;
		
		if(bl.getClient().getPartnerId() <= 0){
			setErrorMessage("- Client field is required.");
			valid = false;
		}
		if(bl.getSupplier().getPartnerId() <= 0){
			setErrorMessage("- Supplier field is required.");
			valid = false;
		}
		if(bl.getShipTo().getShipPickUpId() <= 0){
			setErrorMessage("- Consignee field is required.");
			valid = false;
		}
		if(bl.getPickupFrom().getShipPickUpId() <= 0){
			setErrorMessage("- Shipper field is required.");
			valid = false;
		}	
		if(bl.getBlShippingType().getValueId() <= 0){
			setErrorMessage("- Shipping Type field is required.");
			valid = false;
			
		}if(!bl.getClient().isSearchAndInspConsent() ){			
			valid = false;
		}	
		try {
			
			calculateBlItemsInformation();
			calculateTotalCostsSales();			
			calculateTotalContainers();
			
			
			if(valid){
				if(!bl.isMaster())loadInvoiceList();
				
				if(bl.getBlId() > 0){
					int _tmpRet = blLogic.saveBl(bl);
					
					if (_tmpRet > 0) {
						bl.setBlId(_tmpRet);
						if(!bl.isMaster()){
							saveBlItemsAction();
							saveBlPalletizedItemsAction();
							updateCostSales(bl);
							saveCostSales(bl);
							newOtherCostSaleAction();
							
							saveBlContainersAction();
							saveEEIAction();
							saveUnCodesAction();							
						}else{
							// si el bl master tiene hijos LCL
							if (bl.isHouseLCL()){
								saveBlContainersAction();
								blLogic.updateBlHouseList(bl.getBlHouses());
							}
						}
						
						
						setInfoMessage("Bl was successfully saved");				
					}else{
						setInfoMessage("Data was not saved");
					}
				}else{
					if(!bl.isMaster()){
					
						bl = blLogic.loadCostsSales(bl);
						blLogic.loadInitialOtherCostsSalesFromClientAndCarrier(bl);	
					}
					
					bl.setBlId( blLogic.saveBl(bl));
					
					if(!bl.isMaster()){
						saveCostSales(bl);
						newOtherCostSaleAction();
						newItemEEIAction();
						newItemUnCodesAction();
						newBlContainerAction();
						bl.setBlItems(new ArrayList<BlItem>());
					}					
					
					setInfoMessage("Bl was successfully saved");
				}
				
			}else{
				setErrorMessage("**** Data was not saved ****");
			}
			
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Bl.\n"+ e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public String cancelAction(){
		bl = new Bl();
		searchBlAction();
		return "blSearch";
	}
	
	public void saveCostSales(Bl bl){
		try {
			for(BlCostSale _tmpCS: bl.getBlCostsSales()){
				_tmpCS.setBlId(bl.getBlId());
			}			
			for(BlCostSale _tmpOtherCS: bl.getBlOtherCostsSales()){
				_tmpOtherCS.setBlId(bl.getBlId());
			}
			
			bl.setBlCostsSales(blCostSaleLogic.saveBlCostSale(bl.getBlCostsSales()));
			bl.setBlOtherCostsSales(blCostSaleLogic.saveBlCostSale(bl.getBlOtherCostsSales()));
			
		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB Costs And Sales. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveBlItemsAction(){
		try {
			
			bl.setBlItems( blItemLogic.saveBlItems(bl.getBlItems()));			
			//setInfoMessage("BL Items were successfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the BL Items. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveBlPalletizedItemsAction(){
		try {
			
			bl.setBlPalletizedItems( blPalletizedItemLogic.saveBlPalletizedItems(bl.getBlPalletizedItems()));			
			//setInfoMessage("BL Items were successfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the BL Items. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void saveEEIAction(){
		try {
			
			bl.setBlEEIList(blEEILogic.saveBlEEI(bl.getBlEEIList()));
			newItemEEIAction();
			//setInfoMessage("EEI was successfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB EEI. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveUnCodesAction(){
		try {
			bl.setBlUnCodes(blUnCodeLogic.saveUnCode(bl.getBlUnCodes()));
			newItemUnCodesAction();
			//setInfoMessage("UN Codes was successfully saved");
			
		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB UN Codes. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveBlContainersAction(){
		try {
			
			bl.setBlContainers( blContainerLogic.saveBlContainers(bl.getBlContainers()));	
			newBlContainerAction();
			//setInfoMessage("BL Containers were successfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the BL Containers. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveFreightInvoicesAction() {
		try {
			clearMessages();
			blFreightInvoiceLogic.saveFreightInvoices(bl.getBlFreightInvoices());
			setInfoMessage("Items was succesfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Freight Invoices. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void saveMerchandInvoicesAction() {
		try {
			clearMessages();
			blOtherInvoiceLogic.saveBlOtherInvoices(bl.getBlOtherInvoices());
			setInfoMessage("Items was succesfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Merchandise Invoices. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void newOtherCostSaleAction(){
		if(bl.getBlOtherCostsSales() == null){
			bl.setBlOtherCostsSales(new ArrayList<BlCostSale>());
			bl.getBlOtherCostsSales().add(new BlCostSale(bl.getBlId(), true));
		}else{
			int _tmpSize = bl.getBlOtherCostsSales().size();
			if(_tmpSize > 0){
				BlCostSale _tmpCostSale = bl.getBlOtherCostsSales().get(_tmpSize-1);
				if(!_tmpCostSale.isEmpty()){
					bl.getBlOtherCostsSales().add(new BlCostSale(bl.getBlId(), true));
				}
			}else if(_tmpSize == 0){
				bl.getBlOtherCostsSales().add(new BlCostSale(bl.getBlId(), true));
			}
		}
	}
	
	public void newBlContainerAction(){
		if(bl.getBlContainers() == null){
			bl.setBlContainers(new ArrayList<BlContainer>());
			bl.getBlContainers().add(new BlContainer(bl.getBlId(), 1));
		}else{
			int _tmpSize = bl.getBlContainers().size();
			if(_tmpSize > 0){
				BlContainer _tmpContainer = bl.getBlContainers().get(_tmpSize-1);
				if(!_tmpContainer.isEmpty()){
					bl.getBlContainers().add(new BlContainer(bl.getBlId(),_tmpSize +1));
				}
			}else if(_tmpSize == 0){
				bl.getBlContainers().add(new BlContainer(bl.getBlId(),1));
			}
		}
	}
	
	public void changeLockedValueAction(){
		if(locked){
			locked = false;
		}else{
			locked = true;
		}
	}
	
	public void changeRegionYesAction() {
		bl.getRegion().setValueId(regionSwitch);
		for(SelectItem reg : getRegionsList()){
			if((Integer)reg.getValue() == regionSwitch){
				bl.getRegion().setValue1(reg.getLabel());
			}
		}
	}

	public void changeRegionNoAction() {
		setRegionSwitch(bl.getRegion().getValueId());
	}
	
	public void newSaidToContainAction(){
		saidToContain = new MasterValue();
		saidToContain.setMasterId(Constants.MASTER_SAID_TO_CONTAIN);
		saidToContain.setEnteredDate(new Date());
	}	
	
	
	public void saveSaidToContainAction(){
		try {
			if(!saidToContain.getValue3().isEmpty()){
				masterValueLogic.saveMasterValue(saidToContain);
				setInfoMessage("Said To Contain was successfully saved");
				AdministradorListas.obtenerLista(
						"com.lotrading.softlot.util.lists.SaidToContainList")
						.clearElements();
				AdministradorListas.obtenerLista(
						"com.lotrading.softlot.util.lists.SaidToContainList").refreshList();
			}else{
				setErrorMessage("This fiel can not be empty");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Said To Contain. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void newItemFreightInvoiceAction(){
		if(bl.getBlFreightInvoices() == null){
			bl.setBlFreightInvoices(new ArrayList<BlFreightInvoice>());
			bl.getBlFreightInvoices().add(new BlFreightInvoice());
		}else{
			int _tmpSize = bl.getBlFreightInvoices().size();
			if(_tmpSize > 0){
				BlFreightInvoice _tmpFreightInv = bl.getBlFreightInvoices().get(_tmpSize - 1);
				if(!_tmpFreightInv.isEmpty()){
					bl.getBlFreightInvoices().add(new BlFreightInvoice(bl.getBlId() ));
				}
			}else if (_tmpSize == 0) {
				bl.getBlFreightInvoices().add(new BlFreightInvoice(bl.getBlId()));
			}
		}
	}
	
	public void newItemMerchandiseInvoiceAction(){
		if(bl.getBlOtherInvoices() == null){
			bl.setBlOtherInvoices(new ArrayList<BlOtherInvoice>());
			bl.getBlOtherInvoices().add(new BlOtherInvoice());
		}else{
			int _tmpSize = bl.getBlOtherInvoices().size();
			if(_tmpSize > 0){
				BlOtherInvoice _tmpMerchInv = bl.getBlOtherInvoices().get(_tmpSize - 1);
				if(!_tmpMerchInv.isEmpty()){
					bl.getBlOtherInvoices().add(new BlOtherInvoice(bl.getBlId() ));
				}
			}else if (_tmpSize == 0) {
				bl.getBlOtherInvoices().add(new BlOtherInvoice(bl.getBlId()));
			}
		}
	}
	
	public void newItemUnCodesAction(){
		if(bl.getBlUnCodes() == null){
			bl.setBlUnCodes(new ArrayList<BlUnCode>());
			bl.getBlUnCodes().add(new BlUnCode(bl.getBlId()));
		}else{
			int _tmpSize = bl.getBlUnCodes().size();
			if(_tmpSize > 0 ){
				BlUnCode _tmpUnCode = bl.getBlUnCodes().get(_tmpSize - 1);
				if(!_tmpUnCode.isEmpty()){
					bl.getBlUnCodes().add(new BlUnCode(bl.getBlId()));
				}
			}else if(_tmpSize == 0){
				bl.getBlUnCodes().add(new BlUnCode(bl.getBlId()));
			}
		}
	}
	
	public void newItemEEIAction(){
		if(bl.getBlEEIList() == null){
			bl.setBlEEIList(new ArrayList<BlEEI>());
			bl.getBlEEIList().add(new BlEEI(bl.getBlId()));
		}else{
			int _tmpSize = bl.getBlEEIList().size();
			if(_tmpSize > 0){
				BlEEI _tmpEEI = bl.getBlEEIList().get(_tmpSize - 1);
				if(!_tmpEEI.isEmpty()){
					bl.getBlEEIList().add(new BlEEI(bl.getBlId()));
				}
			}else if(_tmpSize == 0){
				bl.getBlEEIList().add(new BlEEI(bl.getBlId()));
			}
		}
	}
	
	public void newItemInlandCSAction(){
		if(bl.getBlInlandCS() == null){
			bl.setBlInlandCS(new ArrayList<BlInlandCS>());
			bl.getBlInlandCS().add(new BlInlandCS(bl.getBlId()));
		}else{
			int _tmpSize = bl.getBlInlandCS().size();
			if(_tmpSize > 0 ){
				BlInlandCS _tmpInlandCS = bl.getBlInlandCS().get(_tmpSize - 1);
				if(!_tmpInlandCS.isEmpty()){
					bl.getBlInlandCS().add(new BlInlandCS(bl.getBlId()));
				}
			}else if(_tmpSize == 0){
				bl.getBlInlandCS().add(new BlInlandCS(bl.getBlId()));
			}
		}
	}
	
	
	public void selectCSAction(){
		blCostSale = new BlCostSale();
		setBlCostSale((BlCostSale)blCostsSalesTable.getRowData());
		
		if (blCostSale.isInlandFreight()) {
			superCSForm = "openInlandFreightCSFormJs";
		}else{
			superCSForm = "";
		}
	}
	
	public void selectToDeleteFIAction (){
		try {
			freightInvoice = new BlFreightInvoice();
			setFreightInvoice((BlFreightInvoice) blFreightInvoicesTable
					.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFreightInvoicesAction() {
		try {
			if (freightInvoice != null) {
				blFreightInvoiceLogic.deleteFreightInvoice(freightInvoice);
				bl.getBlFreightInvoices().remove(freightInvoice); // Elimino de la lista
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete the Freight Invoice Item. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void selectToDeleteMerchandInvoAction() {
		try {
			merchandiseInvoice = new BlOtherInvoice();
			setMerchandiseInvoice((BlOtherInvoice) blMerchandiseInvoicesTable
					.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMerchandInvoicesAction() {
		try {
			if (merchandiseInvoice != null) {
				blOtherInvoiceLogic.deleteBlOtherInvoice(merchandiseInvoice);
				bl.getBlOtherInvoices().remove(merchandiseInvoice); // Elimino de la lista
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete the Merchandise Invoice Item. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void selectToDeleteCSAction(){
		blCostSale = new BlCostSale();
		setBlCostSale((BlCostSale)blOtherCostsSalesTable.getRowData());
	}
	
	public void selectToDeleteBlItemAction(){
		try{
			blItem = new BlItem();
			setBlItem((BlItem) blItemsTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectToDeleteBlContainerAction(){
		try{
			blContainer = new BlContainer();
			setBlContainer((BlContainer) blContainersTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCSAction(){
		try {
			boolean deleted =blCostSaleLogic.deleteBlCostSale(blCostSale);
			if(deleted){				
				bl = blLogic.loadCostsSales(bl);
				newOtherCostSaleAction();
			}else{
				setWarningMessage("Item was not deleted");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Inland Info.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void selectToDeleteUnCodeItemAction() {
		try {
			blUnCode = new BlUnCode();
			setBlUnCode((BlUnCode) blUnCodesTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUnCodeItemAction() {
		if (blUnCode != null) {
			if (blUnCode.getUnCodeId() > 0) {
				boolean deleted = false;
				try {
					deleted = blUnCodeLogic.deleteUnCode(blUnCode);
				} catch (Exception e) {
					setErrorMessage("Error trying to delete UN Code Item. \n"
							+ e.getMessage());
					e.printStackTrace();
				}
				if (deleted) {
					if (bl.getBlUnCodes().remove(blUnCode)) {
						setInfoMessage("Item was successfully removed");
					}
				}
			}
		}
	}
	
	public void selectToDeleteEeiItemAction() {
		try {
			blEEI = new BlEEI();
			setBlEEI((BlEEI) blEeiTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEeiItemAction() {
		if (blEEI != null) {
			if (blEEI.getEeiId() > 0) {
				boolean deleted = false;
				try {
					deleted = blEEILogic.deleteBlEEI(blEEI);
				} catch (Exception e) {
					setErrorMessage("Error trying to delete EEI Item. \n"
							+ e.getMessage());
					e.printStackTrace();
				}
				if (deleted) {
					if (bl.getBlEEIList().remove(blEEI)) {
						setInfoMessage("Item was successfully removed");
					}
				}
			}
		}
	}
	
	public void deleteBlItemAction(){
		if(blItem.getWhItemId() > 0){
			// busco el item de recibo de bodega asociado al item de BL en la BD y le actualizo el campo shipped a false.
			WhItem _tmpWhItem = new WhItem();
			_tmpWhItem.setWhItemId(blItem.getWhItemId());
			try {
				_tmpWhItem = whItemLogic.loadWhItem(_tmpWhItem);
				_tmpWhItem.setShipped(false);
				if(whItemLogic.updateWhItem(_tmpWhItem)){
					if(blItemLogic.deleteBlItem(blItem)){ // Elimino el item de la tabla BlItems
						if(bl.getBlItems().remove(blItem)){  // Elimino de la lista
							loadDeclaredValuesAction();
							setInfoMessage("Item was successfully removed");
						}else{
							setErrorMessage("Error trying to remove the Item.\n");
						}
					}else{
						setErrorMessage("Error trying to remove the Item.\n");
					}
				}else{
					setWarningMessage("Error trying to update the Item");
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to remove the Item.\n"+ e.getMessage());
				e.printStackTrace();
			}
		}else{
			try {
				if(blItemLogic.deleteBlItem(blItem)){ // Elimino el item de la tabla BlItems
					if(bl.getBlItems().remove(blItem)){  // Elimino de la lista
						setInfoMessage("Item was successfully removed");
					}else{
						setErrorMessage("Error trying to remove the Item.\n");
					}
				}else{
					setErrorMessage("Error trying to remove the Item.\n");
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to remove the Item.\n"+ e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteBlContainerAction(){
		try {
			boolean deleted =blContainerLogic.deleteBlContainer(blContainer);
			if(deleted){			
				if(bl.getBlContainers().remove(blContainer)){  // Elimino de la lista
					setInfoMessage("Container was successfully removed");
				}else{
					setErrorMessage("Error trying to remove the Container.\n");
				}
				bl.setBlContainers(blLogic.loadBlContainers(bl));
				newBlContainerAction();
			}else{
				setWarningMessage("Container was not deleted");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete Container.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void removeBlHouseFromMaster(){
		Bl _tmpBlHouse = (Bl) blHousesTable.getRowData();
		_tmpBlHouse.getBlMaster().setBlId(0);
		try {
			if(blLogic.updateHouse(_tmpBlHouse)){
				if(bl.getBlHouses().remove(_tmpBlHouse)){
					setInfoMessage("BL "+_tmpBlHouse.getBlNumber() +" was sucessfully removed.");
					
					bl.setBlCostsSales(new ArrayList<BlCostSale>());
					bl.setBlOtherCostsSales(new ArrayList<BlCostSale>());							
					bl.setBlUnCodes(new ArrayList<BlUnCode>());
					bl.setBlEEIList(new ArrayList<BlEEI>());
					bl.setBlFreightInvoices(new ArrayList<BlFreightInvoice>());
					bl.setBlOtherInvoices(new ArrayList<BlOtherInvoice>());
					
					//si la guia no tiene hijas LCL -> se borran los containers
					if (!bl.isHouseLCL()){
						bl.setBlContainers(new ArrayList<BlContainer>());							
					}
					
					
					if(bl.getBlHouses() != null){ // si la master tiene Guias Hijas								
						
						for(Bl _tmpBl:  bl.getBlHouses()){
							loadBLHouseInformationToMaster(_tmpBl);
						}
						processMasterBlCSAction();
					}else{
						bl.setBlHouses(new ArrayList<Bl>()); // si la master no tiene guias hijas inicialice la lista vacia
					}
					
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to remove the Bl House from Master.");
			e.printStackTrace();
		}
	}
		
	public void assignAddressToPickup(){
		int idPickup = bl.getPickupFrom().getShipPickUpId();
		if(pickupFromsObjects != null){
			for(ShipPickUp _tmp : pickupFromsObjects){
				if(_tmp.getShipPickUpId() == idPickup){
					bl.getPickupFrom().setAddress(_tmp.getAddress());
				}
			}
		}
	}
	
	public void assignShipToInformationToBlAction(){
		 assignAddressToShipTo();
		 assignNotifyPartyShipToToBl();
	}
	
	public void assignAddressToShipTo(){
		int idPickup = bl.getShipTo().getShipPickUpId();
		if(shipTosObjects != null){
			for(ShipPickUp _tmp : shipTosObjects){
				if(_tmp.getShipPickUpId() == idPickup){
					bl.getShipTo().setAddress(_tmp.getAddress());
				}
			}
		}
	}
	
	public void assignNotifyPartyShipToToBl(){
		int idPickup = bl.getShipTo().getShipPickUpId();
		if(shipTosObjects != null){
			for(ShipPickUp _tmp : shipTosObjects){
				if(_tmp.getShipPickUpId() == idPickup){
					if(_tmp.getNotifyParty()!= null && !_tmp.getNotifyParty().equals("") ){
						bl.setNotifyParty(_tmp.getNotifyParty());
					}else{
						if (_tmp.getAddress() != null){
							bl.setNotifyParty(_tmp.getAddress().getAddress());
						}
					}
				}
			}
		}
	}
	
	public void newItemBlAction(){
		if(bl.getBlItems() == null){
			bl.setBlItems(new ArrayList<BlItem>());
			bl.getBlItems().add(new BlItem());
		}else{
			int _tmpSize = bl.getBlItems().size();
			if (_tmpSize > 0) {
				BlItem _tmpItem = bl.getBlItems().get(_tmpSize - 1);
				if (!_tmpItem.isEmpty()) {
					bl.getBlItems().add(new BlItem());
				}
			} else if (_tmpSize == 0) {
				bl.getBlItems().add(new BlItem());
			}
		}
	}
	
	public void newCostSaleBlAction(){
		if(bl.getBlCostsSales() == null){
			bl.setBlCostsSales(new ArrayList<BlCostSale>());
			bl.getBlCostsSales().add(new BlCostSale());
		}else{
			int _tmpSize = bl.getBlCostsSales().size();
			if (_tmpSize > 0) {
				BlCostSale _tmpCostSale = bl.getBlCostsSales().get(_tmpSize - 1);
				if (!_tmpCostSale.isEmpty()) {
					bl.getBlCostsSales().add(new BlCostSale());
				}
			} else if (_tmpSize == 0) {
				bl.getBlCostsSales().add(new BlCostSale());
			}
		}
	}
	
	public void processWhItemsAction(){
		if(whReceipt.getWhItems() != null){
			boolean itemSelected = false; //sirve para que cuando no se seleccione ningun item de wh no se haga guardado en la BD.
			List<WhItem> _tmpWhItemsList = new ArrayList<WhItem>();
			for(WhItem item : whReceipt.getWhItems()){
				if(item.isShip() || item.getNumberPiecesToShip() >= item.getPieces()){
					itemSelected = true;
					//agreguelo a los items de bl.
					BlItem _tmpBlItem = new BlItem();
					_tmpBlItem.setBlId(bl.getBlId());
					_tmpBlItem.setPieces(item.getPieces());
					_tmpBlItem.getType().setValueId(item.getType().getValueId());
					_tmpBlItem.getType().setValue1(item.getType().getValue1());
					_tmpBlItem.setItemLength(item.getItemLength());
					_tmpBlItem.setItemWidth(item.getItemWidth());
					_tmpBlItem.setItemHeight(item.getItemHeight());
					_tmpBlItem.setItemWeight(item.getItemWeight());
					_tmpBlItem.setItemVolume(item.getItemWeight() * item.getItemWidth() * item.getItemLength()  / 1728);
					_tmpBlItem.getWhReceipt().setWhReceiptNumber(item.getWhReceipt().getWhReceiptNumber());
					_tmpBlItem.getWhReceipt().setWhReceiptId(item.getWhReceipt().getWhReceiptId());
					_tmpBlItem.setClientOrderId(item.getClientOrderId());
					_tmpBlItem.setWhItemId(item.getWhItemId());
					_tmpBlItem.setPoNumber(item.getPoNumber());
					_tmpBlItem.setHazardous(item.isHazardous());					
					_tmpBlItem.setRemarks(item.getRemarks());
					_tmpBlItem.getWhLocation().setWhLocationId(item.getLocationId());
					_tmpBlItem.setCreatedDate(new Date());
					bl.getBlItems().add(_tmpBlItem);
					
					// Add PoNumber to the BL
					if(!bl.getClientPoNumber().isEmpty() && item.getPoNumber() != null){
						if(!item.getPoNumber().isEmpty()){
							bl.setClientPoNumber(bl.getClientPoNumber().concat(","));
						}	
					}
					if(item.getPoNumber() != null){
						bl.setClientPoNumber(bl.getClientPoNumber().concat(item.getPoNumber())); /*Concatena el PO Number al campo clientPO de la guia. */
					}
					
					if(!bl.getWhRemarks().isEmpty()){
						bl.setWhRemarks(bl.getWhRemarks().concat(","));
					}
					bl.setWhRemarks(bl.getWhRemarks().concat(whReceipt.getRemarks()));
					
					//actualice el estado del item, es decir, poner -1 en el campo shipped.
					//(Al final del ciclo se hace un update del campo shipped en al BD.)
					item.setShipped(true);
					
				}else if(item.getNumberPiecesToShip() > 0 && item.getNumberPiecesToShip() < item.getPieces()){
					itemSelected = true;
					//agregue ese item a items de bl.
					BlItem _tmpBlItem = new BlItem();
					_tmpBlItem.setBlId(bl.getBlId());
					_tmpBlItem.setPieces(item.getNumberPiecesToShip()); // aca se pone el numero de pieces seleccionadas
					_tmpBlItem.getType().setValueId(item.getType().getValueId());
					_tmpBlItem.getType().setValue1(item.getType().getValue1());
					_tmpBlItem.setItemLength(item.getItemLength());
					_tmpBlItem.setItemWidth(item.getItemWidth());
					_tmpBlItem.setItemHeight(item.getItemHeight());
					_tmpBlItem.setItemWeight(item.getItemWeight());
					_tmpBlItem.setItemVolume(item.getItemWeight() * item.getItemWidth() * item.getItemLength()  / 1728);
					_tmpBlItem.getWhReceipt().setWhReceiptNumber(item.getWhReceipt().getWhReceiptNumber());
					_tmpBlItem.getWhReceipt().setWhReceiptId(item.getWhReceipt().getWhReceiptId());
					_tmpBlItem.setClientOrderId(item.getClientOrderId());
					_tmpBlItem.setWhItemId(item.getWhItemId());
					_tmpBlItem.setPoNumber(item.getPoNumber());
					_tmpBlItem.setHazardous(item.isHazardous());
					_tmpBlItem.setRemarks(item.getRemarks());
					_tmpBlItem.getWhLocation().setWhLocationId(item.getLocationId());
					_tmpBlItem.setCreatedDate(new Date());
					bl.getBlItems().add(_tmpBlItem);
					
					//y cree el item de las piezas sobrantes en la tabla whDetails. 
					WhItem _tmpWhItem = (WhItem) item.clone();
					_tmpWhItem.setWhItemId(0);
					_tmpWhItem.setPieces(item.getPieces() - item.getNumberPiecesToShip());
					_tmpWhItem.setNumberPiecesToShip(0);
					_tmpWhItemsList.add(_tmpWhItem);
					
					// Add PoNumber to the BL
					if(!bl.getClientPoNumber().isEmpty()){						
						bl.setClientPoNumber(bl.getClientPoNumber().concat(","));						
					}
					
					bl.setClientPoNumber(bl.getClientPoNumber().concat(item.getPoNumber())); /*Concatena el PO Number al campo clientPO de la guia. */
					
					if(!bl.getWhRemarks().isEmpty()){
						bl.setWhRemarks(bl.getWhRemarks().concat(","));
					}
					
					bl.setWhRemarks(bl.getWhRemarks().concat(whReceipt.getRemarks()));
					
					//actualice el estado del item, es decir, poner -1 en el campo shipped.
					item.setShipped(true);
					item.setPieces(item.getNumberPiecesToShip());
					
					// Al final del ciclo se hace un create en la BD de los items que resultaron nuevos 
					// y se hace update del campo shipped de los items que se escogieron.
				}
			}
			if(itemSelected){
				whReceipt.getWhItems().addAll(_tmpWhItemsList);
				try {
					whItemLogic.saveWhItem(whReceipt.getWhItems());
					
				} catch (Exception e) {
					setErrorMessage("Error trying to save the WhItems.\n"+ e.getMessage());
					e.printStackTrace();
				}		
			}
			saveBlItemsAction();
			assignInlandInfoFromClientOrder();
			loadDeclaredValuesAction();
		}
		clearListsInvoiceAndWhItems();
	}
	
	private void assignInlandInfoFromClientOrder() {		
		if(whReceipt !=null && whReceipt.getWhItems() != null &&  whReceipt.getWhItems().size() >0){
			BlItem auxBlItem = new BlItem();
			auxBlItem.setBlId(bl.getBlId());
			auxBlItem.setClientOrderId(whReceipt.getWhItems().get(0).getClientOrderId());
			try {
				bl.setBlInlandCS( blLogic.createInlandCSFromClientOrder(auxBlItem));
			} catch (Exception e) {				
				e.printStackTrace();
			}
						
			double auxSumCosts =0;
			double auxSumSales = 0;
			for(BlInlandCS inlandCSItem : bl.getBlInlandCS()){
				if (inlandCSItem.getInlandCSId() >0){
					auxSumCosts = auxSumCosts + inlandCSItem.getCost();
					auxSumSales = auxSumSales + inlandCSItem.getSale();
				}
			}
			for(BlCostSale csItem : bl.getBlCostsSales()){
				if (csItem.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL
						|| csItem.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL){
					csItem.setCost(auxSumCosts);
					csItem.setSale(auxSumSales);
				}
			}
			
		}
			
			
		
	}

	public void processInvoiceItemsAction(){
		if(invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_RM){
			processPalletizedItemsAction();
		}else if(invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_IP){
			processPackingListItemsAction();
		}
	}
	
	private void processPalletizedItemsAction(){
		if(invoiceItems != null){
			for(Object _tmpItem : invoiceItems){
				PalletizedItem item = new PalletizedItem();
				item = (PalletizedItem) _tmpItem;
				if(item.getNumberPiecesToShip() > 0){
		
					BlItem _tmpBlItem = new BlItem();
					_tmpBlItem.setBlId(bl.getBlId());
					if(item.getNumberPiecesToShip() > item.getPieces()){
						_tmpBlItem.setPieces(item.getPieces());
					}else{
						_tmpBlItem.setPieces(item.getNumberPiecesToShip());
					}
					
					_tmpBlItem.getType().setValueId(item.getType().getValueId());
					_tmpBlItem.setItemLength(item.getLength());
					_tmpBlItem.setItemWidth(item.getWidth());
					_tmpBlItem.setItemHeight(item.getHeight());
					_tmpBlItem.setItemWeight(item.getWeight());
					_tmpBlItem.setItemVolume(item.getHeight()*item.getWidth()*item.getLength()/1728);
					_tmpBlItem.getInvoice().setInvoiceId(invoice.getInvoiceId());
					_tmpBlItem.getInvoice().setInvoiceNumber(invoice.getInvoiceNumber());
					_tmpBlItem.setHazardous(item.isHazardous());
					// TODO: _tmpBlItem.setLocationId(item.getLocationId())?????
					_tmpBlItem.setCreatedDate(new Date());
					bl.getBlItems().add(_tmpBlItem);
				}
			}
		}
		clearListsInvoiceAndWhItems();
	}
	
	private void processPackingListItemsAction(){
		if(invoiceItems != null){
			for(Object _tmpItem : invoiceItems){
				PackingListItem item = new PackingListItem();
				item = (PackingListItem) _tmpItem;
				if(item.getNumberPiecesToShip() > 0){
		
					BlItem _tmpBlItem = new BlItem();
					_tmpBlItem.setBlId(bl.getBlId());
					if(item.getNumberPiecesToShip() > item.getPieces()){
						_tmpBlItem.setPieces(item.getPieces());
					}else{
						_tmpBlItem.setPieces(item.getNumberPiecesToShip());
					}
					
					_tmpBlItem.getType().setValueId(item.getType().getValueId());
					_tmpBlItem.setItemLength(item.getLength());
					_tmpBlItem.setItemWidth(item.getWidth());
					_tmpBlItem.setItemHeight(item.getHeight());
					_tmpBlItem.setItemWeight(item.getWeight());
					_tmpBlItem.setItemVolume(item.getWeight() * item.getWidth() * item.getLength()  / 1728);
					_tmpBlItem.getInvoice().setInvoiceId(invoice.getInvoiceId());
					_tmpBlItem.getInvoice().setInvoiceNumber(invoice.getInvoiceNumber());
					_tmpBlItem.setHazardous(item.isHazardous());
					_tmpBlItem.setWhReceipt(item.getWhReceipt());
					// TODO: _tmpBlItem.setLocationId(item.getLocationId())?????
					_tmpBlItem.setCreatedDate(new Date());
					bl.getBlItems().add(_tmpBlItem);
				}
			}
		}
		clearListsInvoiceAndWhItems();
	}
	
	public void clearListsInvoiceAndWhItems(){
		whReceipt = new WhReceipt();
		invoice = new Invoice();
		invoiceItems = null;
	}
	
	public void processInlandCSItemsAction(){		
		try {
			
			bl.setBlInlandCS( blInlandCSLogic.saveBlInlandCSItems(bl.getBlInlandCS()));	
			setInfoMessage("BL Inland Freight Costs and Sales were successfully saved");			
			// suma los costos y ventas de InlandFreight
			double auxSumCosts =0;
			double auxSumSales = 0;
			for(BlInlandCS inlandCSItem : bl.getBlInlandCS()){
				if (inlandCSItem.getInlandCSId() >0){
					auxSumCosts = auxSumCosts + inlandCSItem.getCost();
					auxSumSales = auxSumSales + inlandCSItem.getSale();
				}
			}
			blCostSale.setCost(auxSumCosts);
			blCostSale.setSale(auxSumSales);
			
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Inland Cost and Sales Items. \n"+ e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void blPalletizeItemsAction(){
		// Se crean los nuevos items palletizados sino existian
		for(BlItem _auxBlItem : bl.getBlItems() ){
			if(_auxBlItem.getPalletId() != null && !_auxBlItem.getPalletId().equals("") ){
				boolean wasCreated = false;
				for(BlPalletizedItem _palletizedItem : bl.getBlPalletizedItems()){
					if(_palletizedItem.getPalletId().equals( _auxBlItem.getPalletId())){
						wasCreated = true;
					}
				}
				
				if(!wasCreated){
					BlPalletizedItem newBlPalletizedItem = new BlPalletizedItem();
					newBlPalletizedItem.setBlId(bl.getBlId());
					newBlPalletizedItem.setPalletId(_auxBlItem.getPalletId());					
					bl.getBlPalletizedItems().add(newBlPalletizedItem);
				}
			}
		}
		// Se Borran los items palletizados que no tienen ningun item de BL Asociado
		for( int i = 0 ; i <  bl.getBlPalletizedItems().size(); i++){
			boolean exist = false;
			for(BlItem _auxBlItem : bl.getBlItems() ){
				if(bl.getBlPalletizedItems().get(i).getPalletId().equals(_auxBlItem.getPalletId())){
					exist = true;
				}				
			}
			
			if(!exist){
				try {
					blPalletizedItemLogic.deleteBlPalletizedItem(bl.getBlPalletizedItems().get(i));
					bl.getBlPalletizedItems().remove(i);
					i-=1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void openBlMasterAction(ActionEvent event){
		blFilter.getClient().setCode("");
		blFilter.setFullBlNumber("");
		blFilter.setClientPoNumber("");
		blFilter.setDateFromFilter(null);
		blFilter.setDateToFilter(null);
		blFilter.setRemarks("");
		blFilter.setBlNumber(bl.getBlMaster().getBlNumber());
		blFilter.setBlId(bl.getBlMaster().getBlId());
		searchBlAction();
		for(Bl _tmpBl : bls){
			if(_tmpBl.getBlId() == bl.getBlMaster().getBlId()){
				setSessionAttribute("_tmpBl", _tmpBl);
				break;
			}
		}
	}
	
	public void processBlHouses() {	
		List <Bl> _tmpBls = new ArrayList<Bl>();
		for (Bl _tmpBl : blHousesAvailableList) {
			if (_tmpBl.isSelected()) {	
				_tmpBl.setBlMaster(new Bl());
				_tmpBl.getBlMaster().setBlId(bl.getBlId());
				_tmpBls.add(_tmpBl);
			}
		}
		try {
			bl.getBlHouses().addAll(blLogic.updateBlHouseList(_tmpBls));
			for (Bl _tmpBl : _tmpBls) {
				loadBLHouseInformationToMaster(_tmpBl);
			}
			processMasterBlCSAction();
			
		} catch (Exception e) {
			setErrorMessage("Error trying to update the Bl Houses");
			e.printStackTrace();
		}

	}
	
	public void calculateTotalCostsSales(){
		bl.setTotalCost(0);
		bl.setTotalSale(0);
		if(!bl.isMaster()){
			if(bl.getBlCostsSales() != null){
				double _tmp = 0;
				for(BlCostSale _tmpCostSale : bl.getBlCostsSales()){
					_tmp = _tmpCostSale.getCost() + bl.getTotalCost();
					bl.setTotalCost(_tmp);
					_tmp = _tmpCostSale.getSale() + bl.getTotalSale();
					bl.setTotalSale(_tmp);
				}
				for(BlCostSale _tmpCostSale : bl.getBlOtherCostsSales()){
					_tmp = _tmpCostSale.getCost() + bl.getTotalCost();
					bl.setTotalCost(_tmp);
					_tmp = _tmpCostSale.getSale() + bl.getTotalSale();
					bl.setTotalSale(_tmp);
				}
			}
		}
	}
	
	public void calculateBlItemsInformation(){
		bl.setTotalRealWKg(0);
		bl.setTotalRealWLb(0);
		bl.setTotalRealWTon(0);
		bl.setTotalPieces(0);
		bl.setTotalOceanVolF3(0);
		bl.setTotalOceanVolM3(0);
		
		if(!bl.isMaster()){
			if(bl.getBlItems() != null){
				
				// items no palletizados
				for(BlItem _tmpItem : bl.getBlItems()){	
					if(_tmpItem.getPalletId() == null ||_tmpItem.getPalletId().equals("") ){
						_tmpItem.setItemVolume(_tmpItem.getItemHeight() * _tmpItem.getItemWidth() * _tmpItem.getItemLength()  / 1728 );
						bl.setTotalRealWLb( UtilFunctions.roundDecimalPlaces( bl.getTotalRealWLb() + (_tmpItem.getItemWeight() * _tmpItem.getPieces()) , 2 ));				
						bl.setTotalOceanVolF3(  UtilFunctions.roundDecimalPlaces(bl.getTotalOceanVolF3() +  _tmpItem.getPieces() * _tmpItem.getItemVolume(),2));
						bl.setTotalPieces( bl.getTotalPieces() + _tmpItem.getPieces());		
					}					
				}
				//items palletizados
				for(BlPalletizedItem _tmpItem : bl.getBlPalletizedItems()){						
						_tmpItem.setItemVolume(_tmpItem.getItemHeight() * _tmpItem.getItemWidth() * _tmpItem.getItemLength()  / 1728 );
						bl.setTotalRealWLb( UtilFunctions.roundDecimalPlaces( bl.getTotalRealWLb() + (_tmpItem.getItemWeight() * _tmpItem.getPieces()) , 2 ));				
						bl.setTotalOceanVolF3(  UtilFunctions.roundDecimalPlaces(bl.getTotalOceanVolF3() +  _tmpItem.getPieces() * _tmpItem.getItemVolume(),2));
						bl.setTotalPieces( bl.getTotalPieces() + _tmpItem.getPieces());	
				}
				
				// falta poner en weight en kg y toneladas, tambien el volumen en m3
				bl.setTotalRealWKg(  UtilFunctions.roundDecimalPlaces(bl.getTotalRealWLb()/Constants.KILOGRAM_TO_POUNDS,2));
				bl.setTotalRealWTon( UtilFunctions.roundDecimalPlaces(bl.getTotalRealWLb()/(Constants.KILOGRAM_TO_POUNDS * 1000),2));			
				bl.setTotalOceanVolM3( UtilFunctions.roundDecimalPlaces(bl.getTotalOceanVolF3() *  Math.pow(Constants.FOOT_TO_METERS, 3),2) );
			}
		}
		if(bl.isMaster()){
			if(bl.getBlHouses() != null){
				for(Bl _tmpBlHouse : bl.getBlHouses()){					
					bl.setTotalRealWLb(bl.getTotalRealWLb() + _tmpBlHouse.getTotalRealWLb());
					bl.setTotalRealWKg(bl.getTotalRealWKg() +  _tmpBlHouse.getTotalRealWKg() );
					bl.setTotalRealWTon(bl.getTotalRealWTon() +  _tmpBlHouse.getTotalRealWTon() );
					bl.setTotalPieces(bl.getTotalPieces() + _tmpBlHouse.getTotalPieces());
					bl.setTotalOceanVolF3(bl.getTotalOceanVolF3() + _tmpBlHouse.getTotalOceanVolF3());
					bl.setTotalOceanVolM3(bl.getTotalOceanVolM3() + _tmpBlHouse.getTotalOceanVolM3());
				}
				bl.setTotalRealWLb(UtilFunctions.roundDecimalPlaces(bl.getTotalRealWLb(), 2)  );
				bl.setTotalRealWKg(UtilFunctions.roundDecimalPlaces(bl.getTotalRealWKg(),2 ) );
				bl.setTotalRealWTon(UtilFunctions.roundDecimalPlaces(bl.getTotalRealWTon(),2) );				
				bl.setTotalOceanVolF3(UtilFunctions.roundDecimalPlaces(bl.getTotalOceanVolF3(),2));
				bl.setTotalOceanVolM3(UtilFunctions.roundDecimalPlaces(bl.getTotalOceanVolM3(),2));
			}
		}
	}
		
	public void calculateTotalContainers(){			
		if(bl.getBlContainers() != null){
			bl.setTotalContainers( bl.getBlContainers().size()-1 );				
		}
	}
	
	/* Comprueba si esta vigente los costos del carrier respectoa a la fecha de creacion de la guia*/
	public void validEffectiveDate(){
		CarrierPorts _tmpCarrierPort = new CarrierPorts();
		_tmpCarrierPort.setCarrierId(bl.getCarrier().getCarrierId());
		_tmpCarrierPort.setPortOrigin(bl.getPortOrigin().getPortId());
		_tmpCarrierPort.setPortDestination(bl.getPortDestination().getPortId());
		Date effectiveDate = null;
		try {
			effectiveDate = (blLogic.loadEffectiveDate(_tmpCarrierPort)).getEffectiveDate();
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the effective Date.\n" + e.getMessage());
			e.printStackTrace();
		}
		if (effectiveDate != null){
			boolean isValid = bl.getCreatedDate().before(effectiveDate);
			bl.setValidEffectiveDate(!isValid);
		}
	}
	
	public void recalculateCostSalesAction(){
		bl = blLogic.fillAutomaticCosts(bl,bl);
		bl = blLogic.fillAutomaticSales(bl);
	}
	
	public void recalculateCostSalesHousesOfMaster(){
		bl.setBlCostsSales(new ArrayList<BlCostSale>())	;
		for(Bl _tmpHouse : bl.getBlHouses()){
			try {
				_tmpHouse.setBlItems(blLogic.loadBlItems(_tmpHouse));
		
			updateCostSales(_tmpHouse);	
			blLogic.fillAutomaticCosts(_tmpHouse, bl);			
			
			bl.getBlCostsSales().addAll(_tmpHouse.getBlCostsSales()); // agrega los costos de la hija a la master	
			
			saveCostSales(_tmpHouse);
			
			} catch (Exception e1) {
				setErrorMessage("Error trying to retrieve the items of AWB#: "+ _tmpHouse.getBlNumber() +" .\n" + e1.getMessage());
				e1.printStackTrace();
			}
			
		}
		
		processMasterBlCSAction();
		calculateTotalCostsSales();
			
		setInfoMessage("AWB Houses were succesfuly updated");
	}
	
	public void updateCostSales(Bl bl){
		double auxCostInland =0;
		double auxSaleInland =0;
		List<BlCostSale> _tmpListCS = new ArrayList<BlCostSale>();		
		
		List<BlCostSale> blInitialCostsSales = new ArrayList<BlCostSale>();
		try {
			blInitialCostsSales = blLogic.loadInitialCostsSales(bl);// Carga los CostSales que asigna por defecto el sistema.
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//Add initial costs and sales if they don't exist
		for(BlCostSale _tmpCS : blInitialCostsSales){
			boolean exist = false;
			
			for(BlCostSale _tmpCostSale : bl.getBlCostsSales()){
				//save values to Inland Freight
				if ((_tmpCostSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL) ||
						(_tmpCostSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL)){
					auxCostInland = _tmpCostSale.getCost();
					auxSaleInland = _tmpCostSale.getSale();
				}
				
				if(_tmpCS.getChargeType().getValueId() == _tmpCostSale.getChargeType().getValueId()){
					exist = true;
					break;
				}
			}
			if(!exist){				
				_tmpListCS.add(_tmpCS);
			}
			
		}
		//add all new elements
		bl.getBlCostsSales().addAll ( _tmpListCS);
		
		
		//Delete costs and sales if they aren't  initial
		_tmpListCS = new ArrayList<BlCostSale>();
		for(BlCostSale _tmpCostSale : bl.getBlCostsSales()){
			boolean exist = false;
			
			//Transfer values to Inland Freight
			if ((_tmpCostSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL) ||
					(_tmpCostSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL)){
				 _tmpCostSale.setCost( auxCostInland);
				 _tmpCostSale.setSale( auxSaleInland) ;
			}
			for(BlCostSale _tmpCS : blInitialCostsSales){								
				if(_tmpCS.getChargeType().getValueId() == _tmpCostSale.getChargeType().getValueId()){
					exist = true;
					break;
				}
			}
			if(!exist){
				_tmpListCS.add(_tmpCostSale);
			}
		}
	
		//Remove from bd and table or form
		bl.getBlCostsSales().removeAll(_tmpListCS);
		for(BlCostSale _tmpCostSale : _tmpListCS){	
			try{
				blCostSaleLogic.deleteBlCostSale(_tmpCostSale);	
			}catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		
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
		}
		return result;
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
	
	public List<Invoice> autoCompleteInvoices(Object suggest) {
		String number = (String) suggest;
		List<Invoice> result = new ArrayList<Invoice>();
		try {
			for (Invoice tmpInvoice : invoiceList) {
				if ((tmpInvoice != null
						&& tmpInvoice.getInvoiceNumber().indexOf(number) == 0 || ""
							.equals(number))) {
					result.add(tmpInvoice);
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Bl> autoCompleteBl(Object suggest) {
		String number = ((String) suggest).toUpperCase();
		List<Bl> result = new ArrayList<Bl>();
		try {
			for (Bl tmpBl : bls) {
				if ((tmpBl != null && tmpBl.getBlNumber().indexOf(number) == 0 || "".equalsIgnoreCase(number))) {
					result.add(tmpBl);
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public void changeDateFilter(){ 
		if(when.equals("all")){			
			blFilter.setDateFromFilter(null);
			blFilter.setDateToFilter(null);
		}else if(when.equals("today")){
			System.out.println("cambio a today");
			blFilter.setDateFromFilter(new Date());
			blFilter.setDateToFilter(new Date());
		}else if(when.equals("thisWeek")){
			Calendar cal = Calendar.getInstance();
			int firstDayWeek = (cal.get(Calendar.DATE)) - (cal.get(Calendar.DAY_OF_WEEK)) + 1;
			int lastDayWeek = firstDayWeek + 6;
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), firstDayWeek);
			blFilter.setDateFromFilter(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), lastDayWeek);
			blFilter.setDateToFilter(cal.getTime());
		}else if(when.equals("thisMonth")){
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),1);
			blFilter.setDateFromFilter(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			blFilter.setDateToFilter(cal.getTime());			
		}else{
			System.out.println("selecciono rango");
		}
	}
	
	public void loadPickupsFrom(){
		if(getBl().getSupplier() != null){
			if(getBl().getSupplier().getPartnerId() > 0){
				ShipPickUp _tmpShipPickup = new ShipPickUp();
				_tmpShipPickup.setPartnerId(getBl().getSupplier().getPartnerId());
				try {
					pickupFromsObjects = shipPickupLogic.filterByShipPickup(_tmpShipPickup);
					for(ShipPickUp _tmp : pickupFromsObjects){
						pickupFroms.add(new SelectItem(new Integer(_tmp.getShipPickUpId()),_tmp.getName()));
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to get the pickups from.\n"+ e.getMessage());
				}
			}
		}
	}
	
	public void loadShipTos(){
		if(getBl().getClient() != null){
			if(getBl().getClient().getPartnerId() > 0){
				ShipPickUp _tmpShipPickup = new ShipPickUp();
				_tmpShipPickup.setPartnerId(getBl().getClient().getPartnerId());
				try {
					shipTosObjects = shipPickupLogic.filterByShipPickup(_tmpShipPickup);
					for(ShipPickUp _tmp : shipTosObjects){
						shipTos.add(new SelectItem(new Integer(_tmp.getShipPickUpId()),_tmp.getName()));
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to get the Consignees.\n"+ e.getMessage());
				}
			}
		}
	}
	
	public void loadInvoiceList() {
		try {
			invoice = new Invoice();
			invoice.setInvoiceNumber(null);
			invoiceList = invoiceLogic.loadInvoicesList(invoice);
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Invoices List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setNumPiecesToShipWHItemsAction(){
		try{
			WhItem _tmpWhItem = new WhItem();
			_tmpWhItem = (WhItem) blWhItemsTable.getRowData();
			if(_tmpWhItem.isShip()){
				_tmpWhItem.setNumberPiecesToShip(_tmpWhItem.getPieces());
			}else{
				_tmpWhItem.setNumberPiecesToShip(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setNumPiecesToShipInvoiceItemsAction(){
		try{
			PalletizedItem _tmpItem = new PalletizedItem();
			_tmpItem = (PalletizedItem) blInvoiceItemsTable.getRowData();
			if(_tmpItem.isShip()){
				_tmpItem.setNumberPiecesToShip(_tmpItem.getPieces());
			}else{
				_tmpItem.setNumberPiecesToShip(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void reportLabelAction() {		
		try {
			if(null != bl && bl.getBlId()>0){
									
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext ectx = ctx.getExternalContext();
				ServletContext context = (ServletContext)ectx.getContext();
				
				//Ruta y nombre del jasper del cual se desea generar el reporte 
				String _tmpFile = context.getRealPath("WEB-INF/reports/LODepartment/bl/BLlabelRegular.jasper");
				
				
				
				Map parametros = new HashMap();
				parametros.put("shipper", bl.getPickupFrom().getAddress().getAddress());
				parametros.put("consignee", bl.getShipTo().getAddress().getAddress());
				parametros.put("booking",( bl.getBooking().equals(""))? bl.getShipTo().getAddress().getAddress():bl.getBooking());
				parametros.put("destinationPort", bl.getPortDestination().getName());
				parametros.put("freightInvoices", bl.getConcatenatedFreightInvoices());
				
				//Se llena el objeto con la informacion que se desea imprimir
				ParametrosReporte parametrosReporte = new ParametrosReporte();
				
				List <Bl> _tmpBlList = new ArrayList<Bl>();
				_tmpBlList.add(bl);
				
				//Conexion
				parametrosReporte.setConn(null);
				//Nombre del archivo que se desea obtener
				parametrosReporte.setNombreArchivo("Archivo");
				//Informacion con la ruta del archivo y nombre del jasper
				parametrosReporte.setNombreJasper(_tmpFile);
				//Parametros enviados al jasper
				parametrosReporte.setParametros(parametros);					
				//Tipo de archivo que se desea generar
				parametrosReporte.setTipoArchivo("XLS");
				//Lista de objetos para imprimir los detalles 
				//parametrosReporte.setListaObjetos(getItemByItem());
				parametrosReporte.setListaObjetos(_tmpBlList);
				
				//Se carga el objeto en sessión 
				setSessionAttribute("parametrosReporte", parametrosReporte);
				//Se redirecciona al recurso creado
				ectx.redirect("/SOFTLOT/reporte.rep");
				this.setInfoMessage("El reporte se generó correctamente.");
			} else {
				this.setInfoMessage("No existen reportes por generar.");
			}
		
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prorateBlAction() {	
		try {
			if(null != bl && bl.getBlId()>0){
				List<ItemProrated> itemsProrated = new ArrayList<ItemProrated>();
				itemsProrated = getBlItemsToProrate();
				
				for(ItemProrated _itemProrated: itemsProrated){
					for(BlCostSale _itemPCS : _itemProrated.getBlCostSalesProrated()){
						if (bl.getTotalRealWKg() != 0){
							//Bl se va por peso 
							if(bl.getTotalRealWTon() > bl.getTotalOceanVolM3()){
								_itemPCS.setSale(UtilFunctions.roundDecimalPlaces((_itemProrated.getWeight()* _itemPCS.getSale())/bl.getTotalRealWKg(),2)) ;
								
							//Bl se va por volumen	
							}else{
								_itemPCS.setSale(UtilFunctions.roundDecimalPlaces((_itemProrated.getVolume()* _itemPCS.getSale())/bl.getTotalOceanVolM3(),2) );
							}
							
						}
					}
				}
		
		
									
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext ectx = ctx.getExternalContext();
				ServletContext context = (ServletContext)ectx.getContext();
				
				//Ruta y nombre del jasper del cual se desea generar el reporte 
				String _tmpFile = context.getRealPath("WEB-INF/reports/LODepartment/bl/BLProrate.jasper");
				
				
				
				Map parametros = new HashMap();
				parametros.put("shipper", bl.getPickupFrom().getAddress().getAddress());
				parametros.put("consignee", bl.getShipTo().getAddress().getAddress());
				parametros.put("booking",( bl.getBooking().equals(""))? bl.getShipTo().getAddress().getAddress():bl.getBooking());
				parametros.put("destinationPort", bl.getPortDestination().getName());
				parametros.put("freightInvoices", bl.getConcatenatedFreightInvoices());
				
				//Se llena el objeto con la informacion que se desea imprimir
				ParametrosReporte parametrosReporte = new ParametrosReporte();
											
				//Conexion
				parametrosReporte.setConn(null);
				//Nombre del archivo que se desea obtener
				parametrosReporte.setNombreArchivo("Archivo");
				//Informacion con la ruta del archivo y nombre del jasper
				parametrosReporte.setNombreJasper(_tmpFile);
				//Parametros enviados al jasper
				parametrosReporte.setParametros(parametros);					
				//Tipo de archivo que se desea generar
				parametrosReporte.setTipoArchivo("XLS");
				//Lista de objetos para imprimir los detalles 
				//parametrosReporte.setListaObjetos(getItemByItem());
				parametrosReporte.setListaObjetos(itemsProrated);
				
				//Se carga el objeto en sessión 
				setSessionAttribute("parametrosReporte", parametrosReporte);
				//Se redirecciona al recurso creado
				ectx.redirect("/SOFTLOT/reporte.rep");
				this.setInfoMessage("El reporte se generó correctamente.");
			} else {
				this.setInfoMessage("No existen reportes por generar.");
			}
		
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	private List<ItemProrated> getBlItemsToProrate() {	
		List<ItemProrated> itemsProrated = new ArrayList<ItemProrated>();
		List<Integer> invoicesId = new ArrayList<Integer>();
		List<String> poNumbers = new ArrayList<String>();
		
		//agregue los InvoicesId y PoNumbers
		for(BlItem _blItem : bl.getBlItems()){
			if( _blItem.getInvoice().getInvoiceId() >0 && (_blItem.getPalletId() == null ||_blItem.getPalletId().equals(""))){
				if(!invoicesId.contains(new Integer(_blItem.getInvoice().getInvoiceId()))){
					invoicesId.add(new Integer(_blItem.getInvoice().getInvoiceId()) );
				}
			}else{
				if(!poNumbers.contains(_blItem.getPoNumber()) && (_blItem.getPalletId() == null ||_blItem.getPalletId().equals(""))){
					poNumbers.add(_blItem.getPoNumber());
				}
			}
		}
		
		//agregue los items palletizados a los items a prorratear
		for(BlPalletizedItem _blPalletizedItem : bl.getBlPalletizedItems()){
			ItemProrated _itemProrated = new ItemProrated();
			_itemProrated.setByPallet(true);
			_itemProrated.setOrder("Consolidated " + _blPalletizedItem.getPalletId() );
			_itemProrated.getSupplier().setName("L.O. TRADING");
			_itemProrated.setOriginalInvoice(true);
			_itemProrated.setPieces( _blPalletizedItem.getPieces());
			// en kg
			_itemProrated.setWeight( UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(_blPalletizedItem.getItemWeight() *_blPalletizedItem.getPieces()),2));
			// en m3
			_itemProrated.setVolume( UtilFunctions.roundDecimalPlaces(_blPalletizedItem.getItemVolume() *_blPalletizedItem.getPieces()*  Math.pow(Constants.FOOT_TO_METERS, 3),2));
			//Agregar CostSales del Bl al ItemProrated
			for (BlCostSale _blCS : bl.getBlCostsSales())	_itemProrated.getBlCostSalesProrated().add((BlCostSale)_blCS.clone());
			//Agregar Other CostSales del Bl al ItemProrated
			for (BlCostSale _blCS : bl.getBlOtherCostsSales())	_itemProrated.getBlCostSalesProrated().add((BlCostSale)_blCS.clone());
			itemsProrated.add(_itemProrated);	
		}
		
		
		//agregue los items con Factura a los items a prorratear
		for(Integer _blItemInvoiceId : invoicesId){
			ItemProrated _itemProrated = new ItemProrated();
			for(BlItem _blItem : bl.getBlItems()){
				if(_blItemInvoiceId.intValue() == _blItem.getInvoice().getInvoiceId() && (_blItem.getPalletId() == null ||_blItem.getPalletId().equals(""))){
					_itemProrated.setByInvoice(true);
					_itemProrated.setInvoice(_blItem.getInvoice());					
					_itemProrated.getSupplier().setName("L.O. TRADING");
					_itemProrated.setOriginalInvoice(true);
					_itemProrated.setPieces(_itemProrated.getPieces() + _blItem.getPieces());
					// en kg
					_itemProrated.setWeight(_itemProrated.getWeight() + UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(_blItem.getItemWeight() *_blItem.getPieces()),2));
					// en m3
					_itemProrated.setVolume(_itemProrated.getVolume() + UtilFunctions.roundDecimalPlaces(_blItem.getItemVolume() *_blItem.getPieces()*  Math.pow(Constants.FOOT_TO_METERS, 3),2));					
				}
			}
			//Agregar CostSales del Bl al ItemProrated
			for (BlCostSale _blCS : bl.getBlCostsSales())	_itemProrated.getBlCostSalesProrated().add((BlCostSale)_blCS.clone());
			//Agregar Other CostSales del Bl al ItemProrated
			for (BlCostSale _blCS : bl.getBlOtherCostsSales())	_itemProrated.getBlCostSalesProrated().add((BlCostSale)_blCS.clone());
			itemsProrated.add(_itemProrated);	
		}
		
		//agregue los items con WH a los items a prorratear
		for(String _blItemPoNumber : poNumbers){
			ItemProrated _itemProrated = new ItemProrated();
			for(BlItem _blItem : bl.getBlItems()){
				if(_blItemPoNumber.equals(_blItem.getPoNumber()) && (_blItem.getPalletId() == null ||_blItem.getPalletId().equals(""))){
					_itemProrated.setByPoNumber(true);
					
					ClientOrder _auxCO = new ClientOrder();
					_auxCO.setClientOrderId(_blItem.getClientOrderId());
					
					_itemProrated.setClientOrder(_auxCO);
					_itemProrated.setOrder(_blItem.getPoNumber());			
					
					//TODO: VER SI SE HACE DE UNA SOLA LLAMADA		
					//_itemProrated.setOriginalInvoice(_itemProrated.getClientOrder().isOriginalInvoice);
					_itemProrated.setOriginalInvoice(false);
					
					_itemProrated.setPieces(_itemProrated.getPieces() + _blItem.getPieces());
					// en kg
					_itemProrated.setWeight(_itemProrated.getWeight() + UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(_blItem.getItemWeight() *_blItem.getPieces()),2));
					// en m3
					_itemProrated.setVolume(_itemProrated.getVolume() + UtilFunctions.roundDecimalPlaces(_blItem.getItemVolume() *_blItem.getPieces()*  Math.pow(Constants.FOOT_TO_METERS, 3),2));					
				}
			}
			//Agregar CostSales del Bl al ItemProrated
			for (BlCostSale _blCS : bl.getBlCostsSales())	_itemProrated.getBlCostSalesProrated().add((BlCostSale)_blCS.clone());
			//Agregar Other CostSales del Bl al ItemProrated
			for (BlCostSale _blCS : bl.getBlOtherCostsSales())	_itemProrated.getBlCostSalesProrated().add((BlCostSale)_blCS.clone());
			itemsProrated.add(_itemProrated);	
		}
		try {
			itemsProrated = blLogic.fillBlItemsProratedInformation(itemsProrated);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return  itemsProrated;
	}

	@SuppressWarnings("unchecked")
	public void reportBlAction() {		
		try {
			if(null != bl && bl.getBlId()>0){
				String _tmpFile;
				List<BlCostSale> _SummarizedCharges = new ArrayList<BlCostSale>();
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext ectx = ctx.getExternalContext();
				ServletContext context = (ServletContext)ectx.getContext();
				double totalContainersWeightLb = 0 ;
				int totalContainersPieces = 0 ;
				double totalCharges = 0;
				double totalContainerItemsVolume = 0;
							
				Map parametros = new HashMap();
				parametros.put("shipper", bl.getPickupFrom().getAddress().getAddress());
				parametros.put("consignee", bl.getShipTo().getAddress().getAddress());
				parametros.put("booking",( bl.getBooking().equals(""))? bl.getShipTo().getAddress().getAddress():bl.getBooking());
				parametros.put("destinationPort", bl.getPortDestination().getName());
				parametros.put("freightInvoices", bl.getConcatenatedFreightInvoices());
				
								
				//Se llena el objeto con la informacion que se desea imprimir
				ParametrosReporte parametrosReporte = new ParametrosReporte();
				
				// poner el nombre al port of loading and port of discharge
				
				for (SelectItem _auxPort : getPorts() ){
					Integer auxPortId = (Integer) _auxPort.getValue();
					if(bl.getPortOrigin().getPortId() == auxPortId.intValue()){
						bl.getPortOrigin().setName(_auxPort.getLabel());						
					}
					if(bl.getPortDestination().getPortId() == auxPortId.intValue()){
						bl.getPortDestination().setName(_auxPort.getLabel());						
					}
				}
				
				// poner el nombre al carrier
				for (SelectItem _auxCarrier : getCarriers() ){
					Integer auxCarrierId = (Integer) _auxCarrier.getValue();
					if(bl.getCarrier().getCarrierId() == auxCarrierId.intValue()){
						bl.getCarrier().setName(_auxCarrier.getLabel());						
					}
				}
				
				// poner el nombre al tipo de movimiento
				for (SelectItem _typeOfmove : getBlTypeOfMovesList() ){
					Integer auxTypeOfMoveId = (Integer) _typeOfmove.getValue();
					if(bl.getTypeOfMove().getValueId() == auxTypeOfMoveId.intValue()){
						bl.getTypeOfMove().setValue1(_typeOfmove.getLabel());		
						break;
					}
				}
									
				 //resume los cargos que se mostraran en el reporte
				 _SummarizedCharges =  loadBlSummarizedCS();
				 
				// hallar el total de los cargos resumidos
				for(BlCostSale _auxCS : _SummarizedCharges){
					 totalCharges += _auxCS.getSale();
				}
				totalCharges =UtilFunctions.roundDecimalPlaces(totalCharges, 2);
				
				// IF BL IS FCL
				if (bl.isFCL()){
					
					processFCLReportsContainers();
					
					//Ruta y nombre del jasper del cual se desea generar el reporte 
					 _tmpFile = context.getRealPath("WEB-INF/reports/LODepartment/bl/BLFCL.jasper");
															 
					 // poner el nombre al tipo de contenedor
					for(BlContainer _container : bl.getBlContainers() ){
						for (SelectItem _typeOfContainer :  (List<SelectItem>) getContainerTypes() ){
							Integer auxTypeOfContainerId = (Integer) _typeOfContainer.getValue();
							
							if(auxTypeOfContainerId.intValue() == _container.getType().getValueId() ){
								_container.getType().setValue1(_typeOfContainer.getLabel());
								
								break;
							}
						}
						if (_container.getContainerId()>0){
							totalContainersWeightLb += _container.getGrossWeight();
							totalContainersPieces += _container.getPieces();
							totalContainerItemsVolume+= _container.getItemsVolume();
						}							
						
					}
					
					parametros.put("totalContainersWeightLb",  totalContainersWeightLb);
					parametros.put("totalContainersWeightKg", UtilFunctions.roundDecimalPlaces( UtilFunctions.convertLbToKg(totalContainersWeightLb),2) );
					parametros.put("totalContainersPieces",  totalContainersPieces);
					parametros.put("totalContainerItemsVolume",  totalContainerItemsVolume);					
				
					
				}else{
					if( bl.isHouse()) {
						bl.setBlMasterContainer(blContainerLogic.loadBlContainer(bl.getBlMasterContainer()));
					}
					//Ruta y nombre del jasper del cual se desea generar el reporte 
					 _tmpFile = context.getRealPath("WEB-INF/reports/LODepartment/bl/BLLCL.jasper");					 
					
				}
			
				parametros.put("blSummarizedCharges", _SummarizedCharges);
				parametros.put("totalCharges", totalCharges);
				
				List <Bl> _tmpBlList = new ArrayList<Bl>();
				_tmpBlList.add(bl);
				
				//Conexion
				parametrosReporte.setConn(null);
				//Nombre del archivo que se desea obtener
				parametrosReporte.setNombreArchivo("Archivo");
				//Informacion con la ruta del archivo y nombre del jasper
				parametrosReporte.setNombreJasper(_tmpFile);
				//Parametros enviados al jasper
				parametrosReporte.setParametros(parametros);		
				//Tipo de archivo que se desea generar
				parametrosReporte.setTipoArchivo("PDF");
				//Lista de objetos para imprimir los detalles 				
				parametrosReporte.setListaObjetos(_tmpBlList);
				
				//Se carga el objeto en sessión 
				setSessionAttribute("parametrosReporte", parametrosReporte);
				//Se redirecciona al recurso creado
				ectx.redirect("/SOFTLOT/reporte.rep");
				this.setInfoMessage("El reporte se generó correctamente.");
			} else {
				this.setInfoMessage("No existen reportes por generar.");
			}
		
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
		
	private List<BlCostSale> loadBlSummarizedCS() {
		List<BlCostSale> blCostSales = new ArrayList<BlCostSale>();
		int counter = 0;			
		
		/* Recorre los Cost y Sales y los Other Cost y sales para agregarlos a una lista auxiliar. En la lista solo caben 10 elementos		 
		 */
		if (bl.isMaster()){
			HashMap <Integer,BlCostSale> blCSNames;
			blCSNames = new HashMap<Integer, BlCostSale>();
		
			
			if (bl.getBlCostsSales() != null ) {
						
				 //save  CostSale from cost sales
				 for(BlCostSale _tmpCS:  bl.getBlCostsSales()){
					  
					 if(_tmpCS.isShowInMaster()){
						 BlCostSale auxCS = (BlCostSale)_tmpCS.clone();
						 auxCS.setSale(0);
						 blCSNames.put(_tmpCS.getChargeType().getValueId(), auxCS);
					 }
				 }
				
						
				// load Cost Sale values
				for(Bl _tmpBl:  bl.getBlHouses()){
						
					Iterator itr = blCSNames.entrySet().iterator();
					while (itr.hasNext()) {
						Map.Entry e = (Map.Entry)itr.next();
						// for each normal Costs and Sales
						 for(BlCostSale _tmpCS:  _tmpBl.getBlCostsSales()){
							if (e.getKey().equals(_tmpCS.getChargeType().getValueId())){
								blCSNames.get(e.getKey()).setSale(blCSNames.get(e.getKey()).getSale() + _tmpCS.getSale() );
								break;
							}
						 }	
					}
				}
				
				Iterator itr = blCSNames.entrySet().iterator();
				while (itr.hasNext()) {
					Map.Entry e = (Map.Entry)itr.next();	
					blCostSales.add((BlCostSale)e.getValue());
				}
			
			}
			
		}else{
			for(BlCostSale _tmpCS: bl.getBlCostsSales()){
				if (_tmpCS.isSelectedToBlDoc()){
					if (counter < 10 ){										
							blCostSales.add(_tmpCS);
							counter++;
						}				
					}				
				}				
				
			for(BlCostSale _tmpCS: bl.getBlOtherCostsSales()){
				if (_tmpCS.isSelectedToBlDoc()){
					if (counter < 10 ){
						blCostSales.add(_tmpCS);
						counter++;
					}
				}
			}	
		}
		return blCostSales;
	}	
	
	private void processFCLReportsContainers() {
		
		 // Borre la informacion de los containers		
		 for(BlContainer _container : bl.getBlContainers() ){
				if(_container.getContainerId() > 0 ){									
					_container.setPieces(0)	;
					_container.setGrossWeight(0);
					_container.setItemsVolume(0);		
					
				}
			}	
		
		// poner la informacion de los items al contenedor
		 
		 //si es Master
		 if(bl.isMaster()){
			 List<BlItem> auxBlItems = new ArrayList<BlItem>();
			 List<BlPalletizedItem> auxBlPalletizedItems = new ArrayList<BlPalletizedItem>();
			 for(Bl _blhouse : bl.getBlHouses()){
				 if(_blhouse != null){
					 try {
						auxBlItems.addAll(blLogic.loadBlItems(_blhouse));
						auxBlPalletizedItems.addAll(blLogic.loadBlPalletizedItems(_blhouse));
					} catch (Exception e) {						
						e.printStackTrace();
					}
				 }
			 }
			 // para items NO palletizados
			 for (BlItem _blItem : auxBlItems ){							
					for(BlContainer _container : bl.getBlContainers() ){
						if(_container.getContainerId() > 0 ){
							// si el item tiene el contenedor y no esta palletizado sume sus piezas, medidas y peso
							if(_container.getContainerId() == _blItem.getContainer().getContainerId() &&(_blItem.getPalletId() == null   || _blItem.getPalletId().equals(""))){
								_container.setPieces(_container.getPieces() + _blItem.getPieces())	;
								_container.setGrossWeight(_container.getGrossWeight() + (_blItem.getItemWeight() * _blItem.getPieces()));
								_container.setItemsVolume(_container.getItemsVolume() + UtilFunctions.roundDecimalPlaces(_blItem.getItemVolume() * _blItem.getPieces() *  Math.pow(Constants.FOOT_TO_METERS, 3), 2) );
								break;
							}
						}
					}
					
				}
			 //para items palletizados
			 for (BlPalletizedItem _blPalletizedItem : auxBlPalletizedItems ){							
					for(BlContainer _container : bl.getBlContainers() ){
						if(_container.getContainerId() > 0 ){
							// si el item tiene el contenedor y no esta palletizado sume sus piezas, medidas y peso
							if(_container.getContainerId() == _blPalletizedItem.getContainer().getContainerId() ){
								_container.setPieces(_container.getPieces() + _blPalletizedItem.getPieces())	;
								_container.setGrossWeight(_container.getGrossWeight() + (_blPalletizedItem.getItemWeight() * _blPalletizedItem.getPieces()));
								_container.setItemsVolume(_container.getItemsVolume() + UtilFunctions.roundDecimalPlaces(_blPalletizedItem.getItemVolume() * _blPalletizedItem.getPieces() *  Math.pow(Constants.FOOT_TO_METERS, 3), 2) );
								break;
							}
						}
					}
					
				}
			 
		 }else{
		 // Si el Bl No es Master 	 
			 
			//para  items NO palletizados
			for (BlItem _blItem :  bl.getBlItems() ){							
				for(BlContainer _container : bl.getBlContainers() ){
					if(_container.getContainerId() > 0 ){
						// si el item tiene el contenedor y no esta palletizado sume sus piezas, medidas y peso
						if(_container.getContainerId() == _blItem.getContainer().getContainerId() && (_blItem.getPalletId() == null   || _blItem.getPalletId().equals(""))){
							_container.setPieces(_container.getPieces() + _blItem.getPieces())	;
							_container.setGrossWeight(_container.getGrossWeight() + (_blItem.getItemWeight() * _blItem.getPieces()));
							_container.setItemsVolume(_container.getItemsVolume() + UtilFunctions.roundDecimalPlaces(_blItem.getItemVolume() * _blItem.getPieces() *  Math.pow(Constants.FOOT_TO_METERS, 3), 2) );
							break;
						}
					}
				}
				
			}
			//para items palletizados
			for (BlPalletizedItem _blPalletizedItem :  bl.getBlPalletizedItems() ){							
				for(BlContainer _container : bl.getBlContainers() ){
					if(_container.getContainerId() > 0 ){
						if(_container.getContainerId() == _blPalletizedItem.getContainer().getContainerId()){
							_container.setPieces(_container.getPieces() + _blPalletizedItem.getPieces())	;
							_container.setGrossWeight(_container.getGrossWeight() + (_blPalletizedItem.getItemWeight() * _blPalletizedItem.getPieces()));
							_container.setItemsVolume(_container.getItemsVolume() + UtilFunctions.roundDecimalPlaces(_blPalletizedItem.getItemVolume() * _blPalletizedItem.getPieces() *  Math.pow(Constants.FOOT_TO_METERS, 3), 2) );
							break;
						}
					}
				}
				
			}
			
		 }
		
	}
	
	

	private List <BlItem> getItemByItem() {
		List<BlItem> _blItems = new ArrayList<BlItem>();
		for(BlItem _tmpBlItem : bl.getBlItems()){
			
			if(_tmpBlItem.getPieces() > 1 ){				
				for(int counter=  _tmpBlItem.getPieces(); counter >0; counter--){
					_blItems.add(_tmpBlItem);
				}
				
			}else{
				_blItems.add(_tmpBlItem);
			}
			
		}
		return _blItems;
		
	}
	
	

	/********************************************************************************************************************
	 * 																													*
	 * 											GETTERS AND SETTERS														*
	 * 																													*
	 * ******************************************************************************************************************/
	
			
	public Bl getBl() {
		return bl;
	}

	public void setBl(Bl bl) {
		this.bl = bl;
	}

	public Bl getBlFilter() {
		return blFilter;
	}

	public void setBlFilter(Bl blFilter) {
		this.blFilter = blFilter;
	}

	public List<Bl> getBls() {
		return bls;
	}

	public void setBls(List<Bl> bls) {
		this.bls = bls;
	}
	
	public MasterValue getSaidToContain() {
		return saidToContain;
	}

	public void setSaidToContain(MasterValue saidToContain) {
		this.saidToContain = saidToContain;
	}


	public WhReceipt getWhReceipt() {
		return whReceipt;
	}


	public void setWhReceipt(WhReceipt whReceipt) {
		this.whReceipt = whReceipt;
	}


	public BlCostSale getBlCostSale() {
		return blCostSale;
	}


	public void setBlCostSale(BlCostSale blCostSale) {
		this.blCostSale = blCostSale;
	}


	public Invoice getInvoice() {
		return invoice;
	}


	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


	public BlFreightInvoice getFreightInvoice() {
		return freightInvoice;
	}


	public void setFreightInvoice(BlFreightInvoice freightInvoice) {
		this.freightInvoice = freightInvoice;
	}


	public BlOtherInvoice getMerchandiseInvoice() {
		return merchandiseInvoice;
	}


	public void setMerchandiseInvoice(BlOtherInvoice merchandiseInvoice) {
		this.merchandiseInvoice = merchandiseInvoice;
	}


	public BlItem getBlItem() {
		return blItem;
	}


	public void setBlItem(BlItem blItem) {
		this.blItem = blItem;
	}


	public BlContainer getBlContainer() {
		return blContainer;
	}


	public void setBlContainer(BlContainer blContainer) {
		this.blContainer = blContainer;
	}


	public BlInlandCS getBlInlandCS() {
		return blInlandCS;
	}


	public void setBlInlandCS(BlInlandCS blInlandCS) {
		this.blInlandCS = blInlandCS;
	}


	public BlUnCode getBlUnCode() {
		return blUnCode;
	}

	public void setBlUnCode(BlUnCode blUnCode) {
		this.blUnCode = blUnCode;
	}

	public BlEEI getBlEEI() {
		return blEEI;
	}

	public void setBlEEI(BlEEI blEEI) {
		this.blEEI = blEEI;
	}

	public PartnerContact getMainContactMail() {
		return mainContactMail;
	}
	public void setMainContactMail(PartnerContact mainContactMail) {
		this.mainContactMail = mainContactMail;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public String getSubjectMessage() {
		return subjectMessage;
	}

	public void setSubjectMessage(String subjectMessage) {
		this.subjectMessage = subjectMessage;
	}

	public int getSize() {
		if (bls == null) {
			return 0;
		} else {
			return bls.size();
		}
	}

	public String getTableState() {
		return tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	public String getTableStateCS() {
		return tableStateCS;
	}


	public void setTableStateCS(String tableStateCS) {
		this.tableStateCS = tableStateCS;
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
	
	public String getHousesTabName() {
		return housesTabName;
	}


	public void setHousesTabName(String housesTabName) {
		this.housesTabName = housesTabName;
	}


	public String getContainersTabName() {
		return containersTabName;
	}


	public void setContainersTabName(String containersTabName) {
		this.containersTabName = containersTabName;
	}


	public String getBlItemsTabName() {
		return blItemsTabName;
	}


	public void setBlItemsTabName(String blItemsTabName) {
		this.blItemsTabName = blItemsTabName;
	}


	public String getCostsAndSalesTabName() {
		return costsAndSalesTabName;
	}


	public void setCostsAndSalesTabName(String costsAndSalesTabName) {
		this.costsAndSalesTabName = costsAndSalesTabName;
	}


	public String getMoreInfoTabName() {
		return moreInfoTabName;
	}


	public void setMoreInfoTabName(String moreInfoTabName) {
		this.moreInfoTabName = moreInfoTabName;
	}


	public String getProductInfoTabName() {
		return productInfoTabName;
	}


	public void setProductInfoTabName(String productInfoTabName) {
		this.productInfoTabName = productInfoTabName;
	}


	public List<Partner> getClients() {
		try {
			setClients(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ClientsList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the clients List.\n"
					+ e.getMessage());
		}
		return clients;
	}
	
	public void setClients(List<Partner> clients) {
		this.clients = clients;
	}
	
	public List<Partner> getSuppliers() {
		try {
			setSuppliers(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.SuppliersList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the suppliers List.\n"
					+ e.getMessage());
		}
		return suppliers;
	}

	public void setSuppliers(List suppliers) {
		this.suppliers = suppliers;
	}
	
	public List<SelectItem> getCarriers() {
		try {
			setCarriers(AdministradorListas.obtenerLista("com.lotrading.softlot.util.lists.CarrierList2")
					.getElements("faces"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return carriers;
	}
	

	public void setCarriers(List<SelectItem> carriers) {
		this.carriers = carriers;
	}
	
	
	

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}


	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}


	public List<Bl> getBlHousesAvailableList() {
		return blHousesAvailableList;
	}

	public void setBlHousesAvailableList(List<Bl> blHousesAvailableList) {
		this.blHousesAvailableList = blHousesAvailableList;
	}

	public List<SelectItem> getPorts() {
		List <Port> allPorts = null;
		try {
			allPorts= (AdministradorListas.obtenerLista( "com.lotrading.softlot.util.lists.PortsList") .getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Ports.\n"
					+ e.getMessage());
		}
		List<SelectItem> auxPorts = new ArrayList<SelectItem>();
		for (Port tmpPort : allPorts) {
			if (!tmpPort.isAir()) {
				auxPorts.add(new SelectItem(tmpPort.getPortId(), tmpPort
						.getName()));
			}
		}
		ports = auxPorts;
		return ports;
		
	}

	public void setPorts(List<SelectItem> ports) {
		this.ports = ports;
	}
	
	public List<City> getCities() {
		try {
			setCities(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CityList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the cities.\n"
					+ e.getMessage());
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
			// TODO: handle exception
		}
		return employees;
	}

	public void setEmployees(List employees) {
		this.employees = employees;
	}
	
	public List getBlShippingTypes() {
		try {
			setBlShippingTypes(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.BlShippingTypesList")
					.getElements("faces"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return blShippingTypes;
	}
	
	public void setBlShippingTypes(List blShippingTypes) {
		this.blShippingTypes = blShippingTypes;
	}
	
	public List<SelectItem> getBlTypesList() {
		try {
			setBlTypesList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ShipmentStatusList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the status.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		
		return blTypesList;
	}

	public void setBlTypesList(List<SelectItem> blTypesList) {
		this.blTypesList = blTypesList;
	}

	public List getUnitTypes() {
		try {
			setUnitTypes(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.UnitTypesList")
					.getElements("faces"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return unitTypes;
	}
	
	public void setUnitTypes(List unitTypes) {
		this.unitTypes = unitTypes;
	}
		
	public List getContainerTypes() {
		try {
			setContainerTypes(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ContainerTypeList")
					.getElements("faces"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return containerTypes;
	}


	public void setContainerTypes(List containerTypes) {
		this.containerTypes = containerTypes;
	}
	
	public List getTruckCompanies() {
		try {
			setTruckCompanies(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.TruckCompanyList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the truck companies.\n"
					+ e.getMessage());
		}
		return truckCompanies;
	}

	public void setTruckCompanies(List truckCompanies) {
		this.truckCompanies = truckCompanies;
	}

	public List<SelectItem> getBlTypeOfMovesList() {
		try {
			setBlTypeOfMovesList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.BlTypeOfMovesList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the truck companies.\n"
					+ e.getMessage());
		}
		return blTypeOfMovesList;
	}

	public void setBlTypeOfMovesList(List<SelectItem> typeOfMovesList) {
		this.blTypeOfMovesList = typeOfMovesList;
	}

	public List<?> getInvoiceItems() {
		return invoiceItems;
	}


	public void setInvoiceItems(List<?> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}


	public List<SelectItem> getWhLocations() {		
		
		List <WhLocation> allWhLocations = null;
		try {
			allWhLocations= (AdministradorListas.obtenerLista( "com.lotrading.softlot.util.lists.WhLocationList") .getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Ports.\n"
					+ e.getMessage());
		}
		List<SelectItem> auxWhLocations = new ArrayList<SelectItem>();
		for (WhLocation tmpWhLocation : allWhLocations) {			
				auxWhLocations.add(new SelectItem(tmpWhLocation.getWhLocationId(), tmpWhLocation
						.getWhLocationName()));
			
		}
		whLocations = auxWhLocations;
		return whLocations;
		
	}

	public void setWhLocations(List<SelectItem> whLocations) {
		this.whLocations = whLocations;
	}

	public List<SelectItem> getPickupFroms() {
		loadPickupsFrom();
		return pickupFroms;
	}

	public void setPickupFroms(List<SelectItem> pickupFroms) {
		this.pickupFroms = pickupFroms;
	}
	
	public List<SelectItem> getShipTos() {
		loadShipTos();
		return shipTos;
	}

	public void setShipTos(List<SelectItem> shipTos) {
		this.shipTos = shipTos;
	}
	
	public List<SelectItem> getOtherChargesList() {
		List<MasterValue> _tmpOCList;
		try {
			_tmpOCList = masterValueLogic.searchMasterValue(new MasterValue(0, Constants.MASTER_CHARGE_TYPE_OTHER));
			otherChargesList = new ArrayList<SelectItem>();
			for(MasterValue _tmpMasterVal: _tmpOCList){
				otherChargesList.add(new SelectItem(new Integer(_tmpMasterVal.getValueId()), _tmpMasterVal.getValue1(), _tmpMasterVal.getValue1()));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Other Charges.\n" + e.getMessage());
			e.printStackTrace();
		}
		return otherChargesList;
	}

	public void setOtherChargesList(List<SelectItem> otherChargesList) {
		this.otherChargesList = otherChargesList;
	}
	
	public List<SelectItem> getSaidToContainList() {
		try {
			setSaidToContainList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.SaidToContainList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Said to Contain List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return saidToContainList;
	}

	public void setSaidToContainList(List<SelectItem> saidToContainList) {
		this.saidToContainList = saidToContainList;
	}
	
	public List<SelectItem> getContainers() {
		containers = new ArrayList <SelectItem>();
		for(BlContainer _tmpBlContainer: bl.getBlContainers()){
			if (_tmpBlContainer.getContainerId()>0){
				SelectItem element = new SelectItem(new Integer(_tmpBlContainer.getContainerId()),_tmpBlContainer.getLineNumber() + "");
				containers.add(element);
			}
		}
		return containers;
	}

	public void setContainers(List<SelectItem> containers) {
		this.containers = containers;
	}
	
	public List<SelectItem> getBlShippingTypeList() {
		blShippingTypeList = new ArrayList<SelectItem>();
		SelectItem _tmpItem = new SelectItem(new Integer(Constants.MASTER_VALUE_BL_SHIPPING_TYPE_LCL), "LCL");
		blShippingTypeList.add(_tmpItem);
		_tmpItem = new SelectItem(new Integer(Constants.MASTER_VALUE_BL_SHIPPING_TYPE_FCL), "FCL");
		blShippingTypeList.add(_tmpItem);
		return blShippingTypeList;
	}

	public void setBlShippingTypeList(List<SelectItem> blShippingTypeList) {
		this.blShippingTypeList = blShippingTypeList;
	}
	
	public List<SelectItem> getRegionsList() {
		try {
			setRegionsList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.RegionsList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Regions List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return regionsList;
	}

	

	public void setRegionsList(List<SelectItem> regionsList) {
		this.regionsList = regionsList;
	}


	public List<SelectItem> getClassUnCodeList() {
		try {
			setClassUnCodeList(AdministradorListas.obtenerLista("com.lotrading.softlot.util.lists.ClassUnCodeList").getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Classes of Un Codes.\n" + e.getMessage());
		}
		return classUnCodeList;
	}

	public void setClassUnCodeList(List<SelectItem> classUnCodeList) {
		this.classUnCodeList = classUnCodeList;
	}

	public List<SelectItem> getPackingGroupUnCodeList() {
		try {
			setPackingGroupUnCodeList(AdministradorListas.obtenerLista("com.lotrading.softlot.util.lists.PackingGroupUnCodeList").getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Packing Groups of Un Codes.\n" + e.getMessage());
		}
		return packingGroupUnCodeList;
	}

	public void setPackingGroupUnCodeList(List<SelectItem> packingGroupUnCodeList) {
		this.packingGroupUnCodeList = packingGroupUnCodeList;
	}
	
	public List<SelectItem> getPaymentTypeList() {
		paymentTypeList = new ArrayList<SelectItem>();
		SelectItem _tmpItem = new SelectItem(new Integer(
				Constants.MASTER_VALUE_PREPAID), "Pre-Paid");
		paymentTypeList.add(_tmpItem);
		_tmpItem = new SelectItem(new Integer(Constants.MASTER_VALUE_COLLECT),
				"Collect");
		paymentTypeList.add(_tmpItem);
		return paymentTypeList;
	}

	public void setPaymentTypeList(List<SelectItem> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}

	public List<ShipPickUp> getPickupFromsObjects() {
		return pickupFromsObjects;
	}

	public void setPickupFromsObjects(List<ShipPickUp> pickupFromsObjects) {
		this.pickupFromsObjects = pickupFromsObjects;
	}
	
	public List<ShipPickUp> getShipTosObjects() {
		return shipTosObjects;
	}

	public void setShipTosObjects(List<ShipPickUp> shipTosObjects) {
		this.shipTosObjects = shipTosObjects;
	}

	public HtmlExtendedDataTable getBlTable() {
		return blTable;
	}

	public void setBlTable(HtmlExtendedDataTable blTable) {
		this.blTable = blTable;
	}
	
	public HtmlExtendedDataTable getBlPalletizedItemsTable() {
		return blPalletizedItemsTable;
	}

	public void setBlPalletizedItemsTable(
			HtmlExtendedDataTable blPalletizedItemsTable) {
		this.blPalletizedItemsTable = blPalletizedItemsTable;
	}

	public HtmlExtendedDataTable getBlHousesAvailableTable() {
		return blHousesAvailableTable;
	}

	public void setBlHousesAvailableTable(
			HtmlExtendedDataTable blHousesAvailableTable) {
		this.blHousesAvailableTable = blHousesAvailableTable;
	}

	public HtmlScrollableDataTable getBlCostsSalesTable() {
		return blCostsSalesTable;
	}


	public void setBlCostsSalesTable(HtmlScrollableDataTable blCostsSalesTable) {
		this.blCostsSalesTable = blCostsSalesTable;
	}
	
	public HtmlScrollableDataTable getBlOtherCostsSalesTable() {
		return blOtherCostsSalesTable;
	}


	public void setBlOtherCostsSalesTable(HtmlScrollableDataTable blOtherCostsSalesTable) {
		this.blOtherCostsSalesTable = blOtherCostsSalesTable;
	}


	public HtmlDataTable getBlFreightInvoicesTable() {
		return blFreightInvoicesTable;
	}


	public void setBlFreightInvoicesTable(
			HtmlDataTable blFreightInvoicesTable) {
		this.blFreightInvoicesTable = blFreightInvoicesTable;
	}


	public HtmlDataTable getBlMerchandiseInvoicesTable() {
		return blMerchandiseInvoicesTable;
	}


	public void setBlMerchandiseInvoicesTable(
			HtmlDataTable blMerchandiseInvoicesTable) {
		this.blMerchandiseInvoicesTable = blMerchandiseInvoicesTable;
	}


	public HtmlExtendedDataTable getBlWhItemsTable() {
		return blWhItemsTable;
	}


	public void setBlWhItemsTable(HtmlExtendedDataTable blWhItemsTable) {
		this.blWhItemsTable = blWhItemsTable;
	}


	public HtmlExtendedDataTable getBlInvoiceItemsTable() {
		return blInvoiceItemsTable;
	}


	public void setBlInvoiceItemsTable(HtmlExtendedDataTable blInvoiceItemsTable) {
		this.blInvoiceItemsTable = blInvoiceItemsTable;
	}


	public HtmlExtendedDataTable getBlContainersTable() {
		return blContainersTable;
	}


	public void setBlContainersTable(HtmlExtendedDataTable blContainersTable) {
		this.blContainersTable = blContainersTable;
	}


	public HtmlExtendedDataTable getBlInlandCSItemsTable() {
		return blInlandCSItemsTable;
	}


	public void setBlInlandCSItemsTable(HtmlExtendedDataTable blInlandCSItemsTable) {
		this.blInlandCSItemsTable = blInlandCSItemsTable;
	}


	public HtmlDataTable getBlHousesTable() {
		return blHousesTable;
	}


	public void setBlHousesTable(HtmlDataTable blHousesTable) {
		this.blHousesTable = blHousesTable;
	}


	public HtmlDataTable getBlItemsTable() {
		return blItemsTable;
	}

	public void setBlItemsTable(HtmlDataTable blItemsTable) {
		this.blItemsTable = blItemsTable;
	}
	
	public HtmlDataTable getBlUnCodesTable() {
		return blUnCodesTable;
	}

	public void setBlUnCodesTable(HtmlDataTable blUnCodesTable) {
		this.blUnCodesTable = blUnCodesTable;
	}
	
	public HtmlDataTable getBlEeiTable() {
		return blEeiTable;
	}

	public void setBlEeiTable(HtmlDataTable blEeiTable) {
		this.blEeiTable = blEeiTable;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}
	
	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	
	public String getConverterName() {
		return converterName;
	}

	public void setConverterName(String converterName) {
		this.converterName = converterName;
	}
	
	public String getConverterDollar() {
		return converterDollar;
	}

	public void setConverterDollar(String converterDollar) {
		this.converterDollar = converterDollar;
	}

	public String getConverterEuro() {
		return converterEuro;
	}

	public void setConverterEuro(String converterEuro) {
		this.converterEuro = converterEuro;
	}	
	
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public int getNumUNCodes() {
		int numUNCodes = 0;
		if(bl.getBlUnCodes() != null){
			for(BlUnCode _tmp : bl.getBlUnCodes()){
				if(_tmp.getUnCodeId()>0){
					numUNCodes++;
				}
			}			
		}
		return numUNCodes;
	}
	
	
	public int getRowsFI() {
		if ( bl.getBlFreightInvoices() != null) {
			return bl.getBlFreightInvoices().size();
		}
		return 0;
	}
	
	public int getRowsMI() {
		if ( bl.getBlOtherInvoices() != null) {
			return bl.getBlOtherInvoices().size();
		}
		return 0;
	}

	public int getRowsCS() {
		if ( bl.getBlCostsSales() != null) {
			return bl.getBlCostsSales().size();
		}
		return 0;
	}
	
	public int getRowsOtherCS() {
		if ( bl.getBlOtherCostsSales() != null) {
			return bl.getBlOtherCostsSales().size();
		}
		return 0;
	}
	
	public int getRowsHouses() {
		if (getBlHousesAvailableList() != null) {
			return getBlHousesAvailableList().size();
		}
		return 0;
	}
	
	public int getRowsDV() {
		if ( bl.getBlDeclaredValues() != null) {
			return bl.getBlDeclaredValues().size();
		}
		return 0;
	}
	
	public int getRowsUNCodes() {
		if ( bl.getBlUnCodes() != null) {
			return bl.getBlUnCodes().size();
		}
		return 0;
	}
	


	public int getRowsEEI() {
		if ( bl.getBlEEIList() != null) {
			return bl.getBlEEIList().size();
		}
		return 0;
	}
	
	public boolean getHaveBLHouses() {
		if ( bl.getBlHouses() != null) {
			if (bl.getBlHouses().size() >0) return true;
		}
		return false;
	}
	
	
	
	public IInvoiceLogic getInvoiceLogic() {
		return invoiceLogic;
	}


	public void setInvoiceLogic(IInvoiceLogic invoiceLogic) {
		this.invoiceLogic = invoiceLogic;		
	}

	public IBlItemLogic getBlItemLogic() {
		return blItemLogic;
	}


	public void setBlItemLogic(IBlItemLogic blItemLogic) {
		this.blItemLogic = blItemLogic;
	}


	public IBlPalletizedItemLogic getBlPalletizedItemLogic() {
		return blPalletizedItemLogic;
	}

	public void setBlPalletizedItemLogic(IBlPalletizedItemLogic blPalletizedItemLogic) {
		this.blPalletizedItemLogic = blPalletizedItemLogic;
	}

	public IShipPickupLogic getShipPickupLogic() {
		return shipPickupLogic;
	}


	public void setShipPickupLogic(IShipPickupLogic shipPickupLogic) {
		this.shipPickupLogic = shipPickupLogic;
	}


	public IMasterValueLogic getMasterValueLogic() {
		return masterValueLogic;
	}


	public void setMasterValueLogic(IMasterValueLogic masterValueLogic) {
		this.masterValueLogic = masterValueLogic;
	}


	public IWhReceiptLogic getWhReceiptLogic() {
		return whReceiptLogic;
	}


	public void setWhReceiptLogic(IWhReceiptLogic whReceiptLogic) {
		this.whReceiptLogic = whReceiptLogic;
	}


	public IBlCostSaleLogic getBlCostSaleLogic() {
		return blCostSaleLogic;
	}


	public void setBlCostSaleLogic(IBlCostSaleLogic blCostSaleLogic) {
		this.blCostSaleLogic = blCostSaleLogic;
	}


	public IBlEEILogic getBlEEILogic() {
		return blEEILogic;
	}


	public void setBlEEILogic(IBlEEILogic blEEILogic) {
		this.blEEILogic = blEEILogic;
	}


	public IBlUnCodeLogic getBlUnCodeLogic() {
		return blUnCodeLogic;
	}


	public void setBlUnCodeLogic(IBlUnCodeLogic blUnCodeLogic) {
		this.blUnCodeLogic = blUnCodeLogic;
	}


	public IWhItemLogic getWhItemLogic() {
		return whItemLogic;
	}


	public void setWhItemLogic(IWhItemLogic whItemLogic) {
		this.whItemLogic = whItemLogic;
	}


	public IWhLocationLogic getWhLocationLogic() {
		return whLocationLogic;
	}

	public void setWhLocationLogic(IWhLocationLogic whLocationLogic) {
		this.whLocationLogic = whLocationLogic;
	}

	public IPalletizedItemLogic getPalletizedItemLogic() {
		return palletizedItemLogic;
	}


	public void setPalletizedItemLogic(IPalletizedItemLogic palletizedItemLogic) {
		this.palletizedItemLogic = palletizedItemLogic;
	}


	public IBlContainerLogic getBlContainerLogic() {
		return blContainerLogic;
	}


	public void setBlContainerLogic(IBlContainerLogic blContainerLogic) {
		this.blContainerLogic = blContainerLogic;
	}


	public IPackingListItemLogic getPackingListItemLogic() {
		return packingListItemLogic;
	}


	public void setPackingListItemLogic(IPackingListItemLogic packingListItemLogic) {
		this.packingListItemLogic = packingListItemLogic;		
	}


	public IBlInlandCSLogic getBlInlandCSLogic() {
		return blInlandCSLogic;
	}


	public void setBlInlandCSLogic(IBlInlandCSLogic blInlandCSLogic) {
		this.blInlandCSLogic = blInlandCSLogic;		
	}


	public IBlFreightInvoiceLogic getBlFreightInvoiceLogic() {
		return blFreightInvoiceLogic;
	}


	public void setBlFreightInvoiceLogic(
			IBlFreightInvoiceLogic blFreightInvoiceLogic) {
		this.blFreightInvoiceLogic = blFreightInvoiceLogic;
	}


	public IBlOtherInvoiceLogic getBlOtherInvoiceLogic() {
		return blOtherInvoiceLogic;
	}


	public void setBlOtherInvoiceLogic(IBlOtherInvoiceLogic blOtherInvoiceLogic) {
		this.blOtherInvoiceLogic = blOtherInvoiceLogic;		
	}
	
	public IPartnerLogic getPartnerLogic() {
		return partnerLogic;
	}

	public void setPartnerLogic(IPartnerLogic partnerLogic) {
		this.partnerLogic = partnerLogic;
	}

	public IBlLogic getBlLogic() {
		return blLogic;
	}

	public void setBlLogic(IBlLogic blLogic) {	
		this.blLogic = blLogic;
		selectBlAction();
	}


	public int getRegionSwitch() {
		return regionSwitch;
	}


	public void setRegionSwitch(int regionSwitch) {
		this.regionSwitch = regionSwitch;
		
	}


	public String getSuperCSForm() {
		return superCSForm;
	}


	public void setSuperCSForm(String superCSForm) {
		this.superCSForm = superCSForm;		
	}

	
	public List<String> getHeadersMasterCS() {
		return headersMasterCS;
	}

	public void setHeadersMasterCS(List<String> headersMasterCS) {
		this.headersMasterCS = headersMasterCS;
	}



	public List<String> getSubHeadersMasterCS() {
		return subHeadersMasterCS;
	}

	public void setSubHeadersMasterCS(List<String> subHeadersMasterCS) {
		this.subHeadersMasterCS = subHeadersMasterCS;
	}

	public List<Object[]> getDataMasterCS() {
		return dataMasterCS;
	}

	public void setDataMasterCS(List<Object[]> dataMasterCS) {
		this.dataMasterCS = dataMasterCS;
	}
	
	


	
	
}
