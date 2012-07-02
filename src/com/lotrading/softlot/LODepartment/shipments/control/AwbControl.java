package com.lotrading.softlot.LODepartment.shipments.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.component.html.HtmlExtendedDataTable;
import org.richfaces.component.html.HtmlScrollableDataTable;

import co.com.landsoft.devbase.util.listas.AdministradorListas;
import co.com.landsoft.reporte.util.ParametrosReporte;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.LODepartment.shipments.entity.Awb;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbEEI;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;
import com.lotrading.softlot.LODepartment.shipments.entity.ItemProrated;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbCostSaleLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbEEILogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbFreightInvoiceLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbInlandCsLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbItemsLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbOtherInvoiceLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbPalletizedItemLogic;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbUnCodeLogic;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
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
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.ICarrierAwbNumberLogic;
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

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */

public class AwbControl extends BaseControl {

	private List<Awb> awbList;
	private Awb awb;
	private Awb awbFilter;
	private AwbItem awbItem;
	private MasterValue saidToContain;
	private WhReceipt whReceipt;
	private WhItem whItem;
	private AwbCostSale awbCostSale;
	private AwbUnCode awbUnCode;
	private AwbEEI awbEEI;
	private Invoice invoice;
	private AwbFreightInvoice freightInvoice;
	private AwbOtherInvoice merchandiseInvoice;
	private PartnerContact mainContactMail;

	private List<Partner> suppliers;
	private List<Partner> clients;
	private List<Employee> employeesList;
	private List<SelectItem> awbTypesList;
	private List<SelectItem> carriersList;
	private List<SelectItem> airportsList;
	private List<SelectItem> regionsList;
	private List<SelectItem> consigneesList;
	private List<SelectItem> pickupFromsList;
	private List<ShipPickUp> consigneesObjects;
	private List<ShipPickUp> pickupFromsObjects;
	private List<Carrier> carriers;
	private List<SelectItem> unitTypesList;
	private List<SelectItem> paymentTypeList;
	private List<SelectItem> otherChargesList;
	private List<SelectItem> saidToContainList;
	private List<SelectItem> rateClassList;
	private List<SelectItem> regions;
	private List<SelectItem> classUnCodeList;
	private List<SelectItem> packingGroupUnCodeList;
	private List<Invoice> invoiceList;
	private List<?> invoiceItems;
	private List<SelectItem> truckCompanies;
	private List<Awb> awbHousesAvailableList;
	private List<SelectItem> whLocations;

	private String tableState;
	private String tableStateCostSales;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable awbTable;
	private HtmlDataTable awbItemsTable;
	private HtmlDataTable awbFreightInvoicesTable;
	private HtmlDataTable awbMerchandInvoicesTable;
	private HtmlScrollableDataTable awbCostsSalesTable;
	private HtmlScrollableDataTable awbOtherCostsSalesTable;
	private HtmlDataTable awbEeiTable;
	private HtmlDataTable awbUnCodesTable;
	private HtmlExtendedDataTable awbInvoiceItemsTable;
	private HtmlExtendedDataTable awbWhItemsTable;
	private HtmlDataTable awbInlandCSItemsTable;
	private HtmlDataTable awbHousesTable;
	private HtmlExtendedDataTable awbHousesAvailableTable;
	private HtmlExtendedDataTable awbPalletizedItemsTable;

	private String when;
	private String selectedTab;
	private String converterName;
	private String converterDollar = Constants.CONVERTER_CURRENCY_DOLLAR;
	private String converterEuro = Constants.CONVERTER_CURRENCY_EURO;
	private boolean locked;
	private int regionSwitch;
	private String superCSForm = "vacio";
	private String bodyMessage;
	private String subjectMessage;
	
	private List<String> headersMasterCS ;			/* Estos tres atributos son que conformarán*/
	private List<String> subHeadersMasterCS ;		/* la tabla CostSales de los master, que */
	List<Object[]> dataMasterCS;					/* que contendrá el resumen de costSales de las hijas*/

	private IAwbLogic awbLogic;
	private IAwbItemsLogic awbItemsLogic;
	private IShipPickupLogic shipPickupLogic;
	private IAwbCostSaleLogic awbCostSaleLogic;
	private IAwbUnCodeLogic awbUnCodeLogic;
	private IAwbEEILogic awbEEILogic;
	private IMasterValueLogic masterValueLogic;
	private IWhReceiptLogic whReceiptLogic;
	private IWhItemLogic whItemLogic;
	private IPalletizedItemLogic palletizedItemLogic;
	private IInvoiceLogic invoiceLogic;
	private IPackingListItemLogic packingListItemLogic;
	private IAwbFreightInvoiceLogic awbFreightInvoiceLogic;
	private IAwbOtherInvoiceLogic awbOtherInvoiceLogic;
	private IAwbInlandCsLogic awbInlandCSLogic;
	private IPartnerLogic partnerLogic;
	private ICarrierAwbNumberLogic carrierAwbNumberLogic;
	private IAwbPalletizedItemLogic awbPalletizedItemLogic;
	private IWhLocationLogic whLocationLogic;

	public AwbControl() {
		when = "today";
		awbFilter = new Awb();
		awb = new Awb();
		Awb _auxAwbMaster = new Awb();
		awb.setAwbMaster(_auxAwbMaster);
		awbList = new ArrayList<Awb>();
		whItem = new WhItem();
		whReceipt = new WhReceipt();
		invoice = new Invoice();
		headersMasterCS = new ArrayList<String>();
		subHeadersMasterCS = new ArrayList<String>();
		dataMasterCS = new ArrayList<Object[]>();
		consigneesList = new ArrayList<SelectItem>();
		pickupFromsList = new ArrayList<SelectItem>();
		changeDateFilter();
	}

	public void clearFilterFields() {
		awbFilter = new Awb();
		when = "all";
	}

	public void searchAwbAction() {
		try {
			awbList = awbLogic.searchAwb(awbFilter);
			if (awbList == null || awbList.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
	}

	public void selectAwbActionAux(ActionEvent event){
		Awb awb = (Awb) awbTable.getRowData();
		setSessionAttribute("_tmpAwb", awb);
		awb = new Awb();
	}	
	
	/* Este metodo se llamara desde el ultimo metodo SET.*/
	public void selectAwbAction(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status");
		if(null != id && !id.equals("")) {
			if(id.equals("nuevo")) {
				awb = (Awb) getSessionAttribute("_tmpAwb");
				removeSessionAttribute("_tmpAwb");
				newAwbAction();
				
			} else if(id.equals("existe")) {
				awb = (Awb) getSessionAttribute("_tmpAwb");
				id = null;
				removeSessionAttribute("_tmpAwb");
				converterName = converterDollar;
				locked = true;

				loadInvoiceList();
				try {
					awb = awbLogic.loadAwb(awb);
					
					if(!awb.isMaster()){
						awb = awbLogic.loadCostsSales(awb);
						newCostSaleAction();
			
						awb.setAwbInlandCS(awbLogic.loadInlandCS(new AwbInlandCS(awb.getAwbId())));
			
						awb.setAwbItems(awbLogic.loadAwbItems(new AwbItem(awb.getAwbId())));
			
						awb.setAwbPalletizedItems(awbLogic.loadAwbPalletizedItems(awb));
						
						awb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(awb.getAwbId())));
						newItemUnCodesAction();
			
						awb.setAwbEEIList(awbLogic.loadEEIs(new AwbEEI(awb.getAwbId())));
						newItemEEIAction();
						
						awb.setWhRemarks( loadWhRemarks( awb.getAwbItems() ) );
						loadFreightInvoicesAction();
						loadMerchandInvoicesAction();
						
						Awb _tmpAwbAux = new Awb();
						_tmpAwbAux.getAwbType().setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_MASTER);
						if(awb.isHouse()){
							awbList = awbLogic.loadAwbList(_tmpAwbAux); /* Carga la lista de guias master para cuando se vaya a */
																		/* asociar la hija a una master.*/
						}
					}
					
					if(awb.isMaster()){ /* Si la guia es MASTER carga las hijas.*/
						
						awb.setAwbFreightInvoices(new ArrayList<AwbFreightInvoice>());
						awb.setAwbOtherInvoices(new ArrayList<AwbOtherInvoice>());
						awb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(awb.getAwbId())));				
						awb.setAwbEEIList(awbLogic.loadEEIs(new AwbEEI(awb.getAwbId())));
						
						awb.setAwbHouses(awbLogic.loadHouseAwbs(awb));
						
						if(awb.getAwbHouses() != null){
							for(Awb _tmpHouseAwb : awb.getAwbHouses()){
								loadAwbHouseInformationToMaster(_tmpHouseAwb);
							}
							processMasterAwbCSAction();
						}else{
							awb.setAwbHouses(new ArrayList<Awb>());
						}
					}
					
					calculateTotalCostsSales();
					calculateTotalWeights();
		
				} catch (Exception e) {
					setErrorMessage("Error trying to retrieve the AWB Info.\n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/*public String selectAwbAction() {      Este ya no se utiliza
		Awb _tmpAwb = (Awb) awbTable.getRowData();
		converterName = converterDollar;
		locked = true;
		loadInvoiceList();
		try {
			awb = new Awb();
			awb.setAwbId(_tmpAwb.getAwbId());
			awb = awbLogic.loadAwb(awb);
			awb = awbLogic.loadCostsSales(awb);
			newCostSaleAction();

			awb.setAwbInlandCS(awbLogic.loadInlandCS(new AwbInlandCS(awb.getAwbId())));

			awb.setAwbItems(awbLogic.loadAwbItems(new AwbItem(awb.getAwbId())));

			awb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(awb.getAwbId())));
			newItemUnCodesAction();

			awb.setAwbEEIList(awbLogic.loadEEIs(new AwbEEI(awb.getAwbId())));
			newItemEEIAction();

			calculateTotalCostsSales();
			calculateTotalWeights();

			loadFreightInvoicesAction();
			loadMerchandInvoicesAction();
			
			if(isAwbMaster()){  Si la guia es MASTER carga las hijas.
				awb.setAwbHouses(awbLogic.loadHouseAwbs(awb));
				if(awb.getAwbHouses() != null){
					for(Awb _tmpHouseAwb : awb.getAwbHouses()){
						_tmpHouseAwb = awbLogic.loadCostsSales(_tmpHouseAwb); Carga todos los Costos y Ventas de esta guia

						_tmpHouseAwb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(awb.getAwbId()))); Carga todos los UN Codes de esta guia

						_tmpHouseAwb.setAwbEEIList(awbLogic.loadEEIs(new AwbEEI(awb.getAwbId()))); Carga todos los EEI de esta guia

						_tmpHouseAwb.setAwbFreightInvoices(awbLogic.loadFreightInvoices(new AwbFreightInvoice(awb.getAwbId()))); Carga todos los freightInvoices de esta guia

						_tmpHouseAwb.setAwbOtherInvoices(awbLogic.loadOtherInvoices(new AwbOtherInvoice(awb.getAwbId()))); Carga todos los MerchandiseInvoices de esta guia
					}
				}
			}

		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the AWB Info.\n" + e.getMessage());
			e.printStackTrace();
		}
		return "awb";
	}*/

	public void loadAwbHouseInformationToMaster(Awb _tmpHouseAwb){
		try {
			if(awb.getAwbCostsSales() == null){
				awb.setAwbCostsSales(new ArrayList<AwbCostSale>());
			}
			if(awb.getAwbOtherCostsSales() == null){
				awb.setAwbOtherCostsSales(new ArrayList<AwbCostSale>());
			}		
			_tmpHouseAwb = awbLogic.loadCostsSales(_tmpHouseAwb);										/* Carga todos los Costos y Ventas de esta guia.*/
			
			awb.getAwbCostsSales().addAll(_tmpHouseAwb.getAwbCostsSales()); 							/* Agrega los costos y ventas de las hijas a la master.*/
			awb.getAwbOtherCostsSales().addAll(_tmpHouseAwb.getAwbOtherCostsSales());							/* Agrega los costos y ventas de tipo Other de las hijas a las master.*/
			
			_tmpHouseAwb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(_tmpHouseAwb.getAwbId())));	/* Carga todos los UN Codes de esta guia.*/
			awb.getAwbUnCodes().addAll(_tmpHouseAwb.getAwbUnCodes());	 								/* Agrega los UN de las hijas a la master.*/
			
			_tmpHouseAwb.setAwbFreightInvoices(awbLogic.loadFreightInvoices(new AwbFreightInvoice(_tmpHouseAwb.getAwbId())));/* Carga todos los freightInvoices de esta guia.*/
			awb.getAwbFreightInvoices().addAll(_tmpHouseAwb.getAwbFreightInvoices()); 					/* Agrega los Freightinvoices de las hijas a las master.*/
			
			_tmpHouseAwb.setAwbOtherInvoices(awbLogic.loadOtherInvoices(new AwbOtherInvoice(_tmpHouseAwb.getAwbId())));/* Carga todos los MerchandiseInvoices de esta guia.*/
			awb.getAwbOtherInvoices().addAll(_tmpHouseAwb.getAwbOtherInvoices()); 						/* Agrega los Merchandise invoice de las hijas a las master.*/
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the AWB Info.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void newAwbAction() {
		converterName = converterDollar;
		locked = true;
		Awb _auxMasterAWb = new Awb();
		awb.setAwbMaster(_auxMasterAWb);		
		Employee _tmpEmpl = this.getEmployeeLogged();
		awb.setCreatedDate(new Date());
		awb.setSalesRep(_tmpEmpl);
		MasterValue _tmpMv = new MasterValue();
		_tmpMv.setValueId(_tmpEmpl.getRegion().getValueId());
		_tmpMv.setValue1(_tmpEmpl.getRegion().getValue1());
		awb.setRegion(_tmpMv);
		regionSwitch = awb.getRegion().getValueId();
	}

	public void assignAwbTypeMaster() {
		awb = new Awb();
		MasterValue _tmpMV = new MasterValue();
		_tmpMV.setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_MASTER);
		_tmpMV.setValue1("MASTER");
		awb.setAwbType(_tmpMV);
		awb.setSaidToContain(Constants.SAID_TO_CONTAIN_AWB_MASTER);
		setSessionAttribute("_tmpAwb", awb);
	}

	public void assignAwbTypeRegular() {
		awb = new Awb();
		MasterValue _tmpMV = new MasterValue();
		_tmpMV.setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_REGULAR);
		_tmpMV.setValue1("REGULAR");
		awb.setAwbType(_tmpMV);
		setSessionAttribute("_tmpAwb", awb);
	}

	public void assignAwbTypeHouse() {
		awb = new Awb();
		MasterValue _tmpMV = new MasterValue();
		_tmpMV.setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE);
		_tmpMV.setValue1("HOUSE");
		awb.setAwbType(_tmpMV);
		setSessionAttribute("_tmpAwb", awb);
	}

	public void saveAwbAction(ActionEvent event) {
		boolean valid = true;
		if (awb.getClient().getPartnerId() <= 0) {
			setErrorMessage("- Client field is required.");
			valid = false;
		}
		if (awb.getSupplier().getPartnerId() <= 0) {
			setErrorMessage("- Supplier field is required.");
			valid = false;
		}
		if (awb.getShipTo().getShipPickUpId() <= 0) {
			setErrorMessage("- Consignee field is required.");
			valid = false;
		}
		if (awb.getPickupFrom().getShipPickUpId() <= 0) {
			setErrorMessage("- Shipper field is required.");
			valid = false;
		}
		if (awb.getAirportOrigin().getPortId() <= 0) {
			setErrorMessage("- Origin Airport field is required.");
			valid = false;
		}
		if (awb.getAirportDestination().getPortId() <= 0) {
			setErrorMessage("- Destination Airport field is required.");
			valid = false;
		}
		if (awb.getCarrier().getCarrierId() <= 0) {
			setErrorMessage("- Airline field is required.");
			valid = false;
		}
		try {
			calculateTotalPieces();
			calculateTotalWeights();
			calculateTotalCostsSales();
			obtainCarrierObject();  /* Se utiliza para traer el Carriercode del carrier seleccionado cuando guarda la guia*/
			if (valid) {
				if(!awb.isMaster())loadInvoiceList();
				if (awb.getAwbId() > 0) { /* Si existe la guia*/
					if(!awb.isMaster()){
						saveUnCodesEEIAction();
						saveAwbItemsAction();
						saveAwbPalletizedItemsAction();
						updateCharges(awb);
						saveCostSalesAction();
					}
					awb = awbLogic.saveAwb(awb);
					setInfoMessage("Awb was successfully saved");
				} else { /* Si no existe la guia*/
					if(!awb.isMaster()){
						awb = awbLogic.loadCostsSales(awb); // Aqui carga los rates sin valores en costo y venta, es decir, solo los nombres de los rates. Estos rates se cargan
															// teniendo en cuenta el Cliente, Aerolinea, Aeropuertos destino y origen.
						awb = awbLogic.saveAwb(awb);
						
						recalculateCostSalesAction(); 		// Aqui rellena los valores a los rates vacios que se cargaron previamente en metodo awbLogic.loadCostsSales(awb).
						newCostSaleAction();
	
						saveCostSalesAction();
	
						awb.setAwbItems(new ArrayList<AwbItem>());
	
						awb.setAwbUnCodes(new ArrayList<AwbUnCode>());
						newItemUnCodesAction();
	
						awb.setAwbEEIList(new ArrayList<AwbEEI>());
						newItemEEIAction();
					}else{
						awb = awbLogic.saveAwb(awb);
					}
					setInfoMessage("Awb was successfully saved");
				}
			} else {
				setErrorMessage("**** Data was not saved ****");
			}

		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB. \n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveAwbItemsAction(){
		try {
			awb.setAwbItems(awbItemsLogic.saveAwbItems(awb.getAwbItems()));
		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB Items. \n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveAwbPalletizedItemsAction(){
		try {
			awb.setAwbPalletizedItems( awbPalletizedItemLogic.saveAwbPalletizedItems(awb.getAwbPalletizedItems()));			
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Awb Palletized Items . \n"+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void saveCostSalesAction() {
		try {
			for (AwbCostSale _tmpCS : awb.getAwbCostsSales()) {
				_tmpCS.setAwbId(awb.getAwbId());
			}
			for (AwbCostSale _tmpOtherCS : awb.getAwbOtherCostsSales()) {
				_tmpOtherCS.setAwbId(awb.getAwbId());
			}
			awb.setAwbCostsSales(awbCostSaleLogic.saveAwbCostSale(awb
					.getAwbCostsSales()));
			awb.setAwbOtherCostsSales(awbCostSaleLogic.saveAwbCostSale(awb
					.getAwbOtherCostsSales()));
			newCostSaleAction();
			// setInfoMessage("Costs And Sales was successfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB Costs And Sales. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean saveUnCodesEEIAction() {
		boolean _tmpReturn = false;
		try {
			awb.setAwbUnCodes(awbUnCodeLogic.saveUnCode(awb.getAwbUnCodes()));
			newItemUnCodesAction();
			_tmpReturn = true;
			// setInfoMessage("UN Codes was successfully saved");
			awb.setAwbEEIList(awbEEILogic.saveAwbEEI(awb.getAwbEEIList()));
			newItemEEIAction();
			// setInfoMessage("EEI was successfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the AWB UN Codes and EEI. \n" + e.getMessage());
			e.printStackTrace();
		}
		return _tmpReturn;

	}

	public void loadFreightInvoicesAction() {
		if(!awb.isMaster()){
			try {
				awb.setAwbFreightInvoices(awbLogic.loadFreightInvoices(new AwbFreightInvoice(awb.getAwbId())));
				newItemFreightInvoiceAction();
			} catch (Exception e) {
				setErrorMessage("Error trying to retrieve the AWB Freight Invoices. \n"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		
	}

	public void loadMerchandInvoicesAction() {
		if(!awb.isMaster()){
			try {
				awb.setAwbOtherInvoices(awbLogic.loadOtherInvoices(new AwbOtherInvoice(awb.getAwbId())));
				newItemMerchandiseInvoiceAction();
			} catch (Exception e) {
				setErrorMessage("Error trying to retrieve the AWB Merchandise Invoices. \n"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void saveFreightInvoicesAction() {
		try {
			clearMessages();
			awbFreightInvoiceLogic.saveFreightInvoice(awb.getAwbFreightInvoices());
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
			awbOtherInvoiceLogic.saveAwbOtherInvoice(awb.getAwbOtherInvoices());
			setInfoMessage("Items was succesfully saved");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Merchandise Invoices. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void newItemFreightInvoiceAction() {
		if (awb.getAwbFreightInvoices() == null) {
			awb.setAwbFreightInvoices(new ArrayList<AwbFreightInvoice>());
			awb.getAwbFreightInvoices().add(new AwbFreightInvoice());
		} else {
			int _tmpSize = awb.getAwbFreightInvoices().size();
			if (_tmpSize > 0) {
				AwbFreightInvoice _tmpFreightInv = awb.getAwbFreightInvoices()
						.get(_tmpSize - 1);
				if (!_tmpFreightInv.isEmpty()) {
					awb.getAwbFreightInvoices().add(
							new AwbFreightInvoice(awb.getAwbId()));
				}
			} else if (_tmpSize == 0) {
				awb.getAwbFreightInvoices().add(
						new AwbFreightInvoice(awb.getAwbId()));
			}
		}
	}

	public void newItemMerchandiseInvoiceAction() {
		if (awb.getAwbOtherInvoices() == null) {
			awb.setAwbOtherInvoices(new ArrayList<AwbOtherInvoice>());
			awb.getAwbOtherInvoices().add(new AwbOtherInvoice());
		} else {
			int _tmpSize = awb.getAwbOtherInvoices().size();
			if (_tmpSize > 0) {
				AwbOtherInvoice _tmpMerchInv = awb.getAwbOtherInvoices().get(
						_tmpSize - 1);
				if (!_tmpMerchInv.isEmpty()) {
					awb.getAwbOtherInvoices().add(
							new AwbOtherInvoice(awb.getAwbId()));
				}
			} else if (_tmpSize == 0) {
				awb.getAwbOtherInvoices().add(
						new AwbOtherInvoice(awb.getAwbId()));
			}
		}
	}

	public void selectToDeleteFreightInvoAction() {
		try {
			freightInvoice = new AwbFreightInvoice();
			setFreightInvoice((AwbFreightInvoice) awbFreightInvoicesTable
					.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFreightInvoicesAction() {
		try {
			if (freightInvoice != null) {
				if(awbFreightInvoiceLogic.deleteFreightInvoice(freightInvoice));
				loadFreightInvoicesAction();
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete the Freight Invoice Item. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectToDeleteMerchandInvoAction() {
		try {
			merchandiseInvoice = new AwbOtherInvoice();
			setMerchandiseInvoice((AwbOtherInvoice) awbMerchandInvoicesTable
					.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMerchandInvoicesAction() {
		try {
			if (merchandiseInvoice != null) {
				awbOtherInvoiceLogic.deleteAwbOtherInvoice(merchandiseInvoice);
				loadMerchandInvoicesAction();
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete the Merchandise Invoice Item. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void newCostSaleAction() {
		if (awb.getAwbOtherCostsSales() == null) {
			awb.setAwbOtherCostsSales(new ArrayList<AwbCostSale>());
			awb.getAwbOtherCostsSales().add(
					new AwbCostSale(awb.getAwbId(), true));
		} else {
			int _tmpSize = awb.getAwbOtherCostsSales().size();
			if (_tmpSize > 0) {
				AwbCostSale _tmpCostSale = awb.getAwbOtherCostsSales().get(
						_tmpSize - 1);
				if (!_tmpCostSale.isEmpty()) {
					awb.getAwbOtherCostsSales().add(
							new AwbCostSale(awb.getAwbId(), true));
				}
			} else if (_tmpSize == 0) {
				awb.getAwbOtherCostsSales().add(
						new AwbCostSale(awb.getAwbId(), true));
			}
		}
	}

	public void selectCSAction() {
		awbCostSale = new AwbCostSale();
		setAwbCostSale((AwbCostSale) awbCostsSalesTable.getRowData());
		if (awbCostSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_AWB) {
			setSuperCSForm("openInlandFreightCSFormJs");
			newItemInlandCSAction();
		} else {
			setSuperCSForm("");
		}
	}

	public void newItemInlandCSAction() {
		if (awb.getAwbInlandCS() == null) {
			awb.setAwbInlandCS(new ArrayList<AwbInlandCS>());
			awb.getAwbInlandCS().add(new AwbInlandCS(awb.getAwbId()));
		} else {
			int _tmpSize = awb.getAwbInlandCS().size();
			if (_tmpSize > 0) {
				AwbInlandCS _tmpInlandCS = awb.getAwbInlandCS().get(
						_tmpSize - 1);
				if (!_tmpInlandCS.isEmpty()) {
					awb.getAwbInlandCS().add(new AwbInlandCS(awb.getAwbId()));
				}
			} else if (_tmpSize == 0) {
				awb.getAwbInlandCS().add(new AwbInlandCS(awb.getAwbId()));
			}
		}
	}

	public void saveInlandCSItemsAction() {
		try {

			awb.setAwbInlandCS(awbInlandCSLogic.saveAwbInlandCsItems(awb
					.getAwbInlandCS()));
			setInfoMessage("AWB Inland Freight Costs and Sales were successfully saved");
			// suma los costos y ventas de InlandFreight
			double auxSumCosts = 0;
			double auxSumSales = 0;
			for (AwbInlandCS inlandCSItem : awb.getAwbInlandCS()) {
				if (inlandCSItem.getInlandCsId() > 0) {
					auxSumCosts = auxSumCosts + inlandCSItem.getCost();
					auxSumSales = auxSumSales + inlandCSItem.getSale();
				}
			}
			awbCostSale.setCost(auxSumCosts);
			awbCostSale.setSale(auxSumSales);

		} catch (Exception e) {
			setErrorMessage("Error trying to save the Inland Cost and Sales Items. \n"
					+ e.getMessage());
			e.printStackTrace();
		}

	}

	public void newItemUnCodesAction() {
		if (awb.getAwbUnCodes() == null) {
			awb.setAwbUnCodes(new ArrayList<AwbUnCode>());
			awb.getAwbUnCodes().add(new AwbUnCode(awb.getAwbId()));
		} else {
			int _tmpSize = awb.getAwbUnCodes().size();
			if (_tmpSize > 0 && _tmpSize < 10) {
				AwbUnCode _tmpUnCode = awb.getAwbUnCodes().get(_tmpSize - 1);
				if (!_tmpUnCode.isEmpty()) {
					awb.getAwbUnCodes().add(new AwbUnCode(awb.getAwbId()));
				}
			} else if (_tmpSize == 0) {
				awb.getAwbUnCodes().add(new AwbUnCode(awb.getAwbId()));
			}
		}
	}

	public void newItemEEIAction() {
		if (awb.getAwbEEIList() == null) {
			awb.setAwbEEIList(new ArrayList<AwbEEI>());
			awb.getAwbEEIList().add(new AwbEEI(awb.getAwbId()));
		} else {
			int _tmpSize = awb.getAwbEEIList().size();
			if (_tmpSize > 0) {
				AwbEEI _tmpEEI = awb.getAwbEEIList().get(_tmpSize - 1);
				if (!_tmpEEI.isEmpty()) {
					awb.getAwbEEIList().add(new AwbEEI(awb.getAwbId()));
				}
			} else if (_tmpSize == 0) {
				awb.getAwbEEIList().add(new AwbEEI(awb.getAwbId()));
			}
		}
	}

	public void newSaidToContainAction() {
		saidToContain = new MasterValue();
		saidToContain.setMasterId(Constants.MASTER_SAID_TO_CONTAIN);
		saidToContain.setEnteredDate(new Date());
	}

	public void saveSaidToContainAction() {
		try {
			if (!saidToContain.getValue3().isEmpty()) {
				masterValueLogic.saveMasterValue(saidToContain);
				setInfoMessage("Said To Contain was successfully saved");
				AdministradorListas.obtenerLista(
						"com.lotrading.softlot.util.lists.SaidToContainList")
						.clearElements();
				AdministradorListas.obtenerLista(
						"com.lotrading.softlot.util.lists.SaidToContainList")
						.refreshList();
			} else {
				setErrorMessage("This fiel can not be empty");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Said To Contain. \n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectToDeleteCostSaleItemAction() {
		try {
			awbCostSale = new AwbCostSale();
			setAwbCostSale((AwbCostSale) awbOtherCostsSalesTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCostSaleItemAction() {
		if (awbCostSale != null) {
			if (awbCostSale.getAwbCostsaleId() > 0) {
				boolean deleted = false;
				try {
					deleted = awbCostSaleLogic.deleteCostSale(awbCostSale);
				} catch (Exception e) {
					setErrorMessage("Error trying to delete the Cost & Sale Item. \n"
							+ e.getMessage());
					e.printStackTrace();
				}
				if (deleted) {
					if (awb.getAwbOtherCostsSales().remove(awbCostSale)) {
						setInfoMessage("Item was successfully removed");
					}
				}
			}
		}
	}

	public void selectToDeleteUnCodeItemAction() {
		try {
			awbUnCode = new AwbUnCode();
			setAwbUnCode((AwbUnCode) awbUnCodesTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUnCodeItemAction() {
		if (awbUnCode != null) {
			if (awbUnCode.getUnCodeId() > 0) {
				boolean deleted = false;
				try {
					deleted = awbUnCodeLogic.deleteUnCode(awbUnCode);
				} catch (Exception e) {
					setErrorMessage("Error trying to delete UN Code Item. \n"
							+ e.getMessage());
					e.printStackTrace();
				}
				if (deleted) {
					if (awb.getAwbUnCodes().remove(awbUnCode)) {
						setInfoMessage("Item was successfully removed");
					}
				}
			}
		}
	}

	public void selectToDeleteEeiItemAction() {
		try {
			awbEEI = new AwbEEI();
			setAwbEEI((AwbEEI) awbEeiTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEeiItemAction() {
		if (awbEEI != null) {
			if (awbEEI.getEeiId() > 0) {
				boolean deleted = false;
				try {
					deleted = awbEEILogic.deleteAwbEEI(awbEEI);
				} catch (Exception e) {
					setErrorMessage("Error trying to delete EEI Item. \n"
							+ e.getMessage());
					e.printStackTrace();
				}
				if (deleted) {
					if (awb.getAwbEEIList().remove(awbEEI)) {
						setInfoMessage("Item was successfully removed");
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

	private void loadConsignees() {
		if (awb.getClient() != null) {
			if (awb.getClient().getPartnerId() > 0) {
				ShipPickUp _tmpShipPickup = new ShipPickUp();
				_tmpShipPickup.setPartnerId(awb.getClient().getPartnerId());
				try {
					consigneesObjects = shipPickupLogic
							.filterByShipPickup(_tmpShipPickup);
					consigneesList = new ArrayList<SelectItem>();
					for (ShipPickUp _tmp : consigneesObjects) {
						consigneesList.add(new SelectItem(new Integer(_tmp
								.getShipPickUpId()), _tmp.getName()));
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to get the Consignees.\n"
							+ e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	private void loadPickupsFrom() {
		if (awb.getSupplier() != null) {
			if (awb.getSupplier().getPartnerId() > 0) {
				ShipPickUp _tmpShipPickup = new ShipPickUp();
				_tmpShipPickup.setPartnerId(awb.getSupplier().getPartnerId());
				try {
					pickupFromsObjects = shipPickupLogic
							.filterByShipPickup(_tmpShipPickup);
					pickupFromsList = new ArrayList<SelectItem>();
					for (ShipPickUp _tmp : pickupFromsObjects) {
						pickupFromsList.add(new SelectItem(new Integer(_tmp
								.getShipPickUpId()), _tmp.getName()));
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to get the pickups from.\n"
							+ e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public void assignAddressToConsignee() {
		int idPickup = awb.getShipTo().getShipPickUpId();
		if (consigneesObjects != null) {
			for (ShipPickUp _tmp : consigneesObjects) {
				if (_tmp.getShipPickUpId() == idPickup) {
					awb.getShipTo().setAddress(_tmp.getAddress());
				}
			}
		}
	}

	public void assignAddressToPickup() {
		int idPickup = awb.getPickupFrom().getShipPickUpId();
		if (pickupFromsObjects != null) {
			for (ShipPickUp _tmp : pickupFromsObjects) {
				if (_tmp.getShipPickUpId() == idPickup) {
					awb.getPickupFrom().setAddress(_tmp.getAddress());
				}
			}
		}
	}

	public void changeDateFilter() {
		if (when.equals("all")) {
			awbFilter.setDateFromFilter(null);
			awbFilter.setDateToFilter(null);
		} else if (when.equals("today")) {
			awbFilter.setDateFromFilter(new Date());
			awbFilter.setDateToFilter(new Date());
		} else if (when.equals("thisWeek")) {
			Calendar cal = Calendar.getInstance();
			int firstDayWeek = (cal.get(Calendar.DATE))
					- (cal.get(Calendar.DAY_OF_WEEK)) + 1;
			int lastDayWeek = firstDayWeek + 6;
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					firstDayWeek);
			awbFilter.setDateFromFilter(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					lastDayWeek);
			awbFilter.setDateToFilter(cal.getTime());
		} else if (when.equals("thisMonth")) {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
			awbFilter.setDateFromFilter(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			awbFilter.setDateToFilter(cal.getTime());
		} else {
			System.out.println("selecciono rango");
		}
	}

	public void changeLockedValueAction() {
		if (locked) {
			locked = false;
		} else {
			locked = true;
		}
	}

	public void setNumPiecesToShipInvoiceItemsAction() {
		try {
			PalletizedItem _tmpItemPalletized = new PalletizedItem();
			PackingListItem _tmpItemPacking = new PackingListItem();
			Object obj = awbInvoiceItemsTable.getRowData();
			if (obj.getClass().getName()
					.equals(_tmpItemPacking.getClass().getName())) {
				_tmpItemPacking = (PackingListItem) obj;
				if (_tmpItemPacking.isShip()) {
					_tmpItemPacking.setNumberPiecesToShip(_tmpItemPacking
							.getPieces());
				} else {
					_tmpItemPacking.setNumberPiecesToShip(0);
				}
			} else if (obj.getClass().getName()
					.equals(_tmpItemPalletized.getClass().getName())) {
				_tmpItemPalletized = (PalletizedItem) obj;
				if (_tmpItemPalletized.isShip()) {
					_tmpItemPalletized.setNumberPiecesToShip(_tmpItemPalletized
							.getPieces());
				} else {
					_tmpItemPalletized.setNumberPiecesToShip(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setNumPiecesToShipWhItemsAction() {
		try {
			WhItem _tmpWhItem = new WhItem();
			_tmpWhItem = (WhItem) awbWhItemsTable.getRowData();
			if (_tmpWhItem.isShip()) {
				_tmpWhItem.setNumberPiecesToShip(_tmpWhItem.getPieces());
			} else {
				_tmpWhItem.setNumberPiecesToShip(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getSize() {
		if (awbList == null) {
			return 0;
		} else {
			return awbList.size();
		}
	}

	public void calculateTotalCostsSales() {
		awb.setTotalCosts(0);
		awb.setTotalSales(0);
		if(!awb.isMaster()){
			if (awb.getAwbCostsSales() != null) {
				double _tmp = 0;
				for (AwbCostSale _tmpCostSale : awb.getAwbCostsSales()) {
					_tmp = _tmpCostSale.getCost() + awb.getTotalCosts();
					awb.setTotalCosts(_tmp);
					_tmp = _tmpCostSale.getSale() + awb.getTotalSales();
					awb.setTotalSales(_tmp);
				}
				for (AwbCostSale _tmpCostSale : awb.getAwbOtherCostsSales()) {
					_tmp = _tmpCostSale.getCost() + awb.getTotalCosts();
					awb.setTotalCosts(_tmp);
					_tmp = _tmpCostSale.getSale() + awb.getTotalSales();
					awb.setTotalSales(_tmp);
				}
			}
		}
	}

	public void calculateTotalWeights() {
		awb.setTotalWeightVol(0);
		awb.setTotalWeightLbs(0);
		int pieces = 0;
		if(!awb.isMaster()){
			if (awb.getAwbItems() != null) {
				for (AwbItem _tmpItem : awb.getAwbItems()) {
					if(_tmpItem.getPalletId() == null || _tmpItem.getPalletId().isEmpty()){		/* Recorre los items que no esten paletizados. */
						double _tmpVol = ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth()) / 166) * _tmpItem.getPieces();
						/* ---> Vol WT[lb]= ((alto * ancho * largo)/166)*numPieces */
						/* ---> Vol WT[Kg]= (((alto * ancho * largo)/166)*numPieces)/2.20462262 */
						double _tmp = awb.getTotalWeightVol() + _tmpVol;
						awb.setTotalWeightVol(_tmp);
		
						_tmp = awb.getTotalWeightLbs() + (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
						awb.setTotalWeightLbs(_tmp);
						
						pieces += _tmpItem.getPieces();
					}
				}
			}
			if (awb.getAwbPalletizedItems() != null) {
				for(AwbPalletizedItem _tmpItemPalletized : awb.getAwbPalletizedItems()){
					double _tmpVol = ((_tmpItemPalletized.getItemHeight() * _tmpItemPalletized.getItemLength() * _tmpItemPalletized.getItemWidth()) / 166) * _tmpItemPalletized.getPieces();
					/* ---> Vol WT[lb]= ((alto * ancho * largo)/166)*numPieces */
					/* ---> Vol WT[Kg]= (((alto * ancho * largo)/166)*numPieces)/2.20462262 */
					double _tmp = awb.getTotalWeightVol() + _tmpVol;
					awb.setTotalWeightVol(_tmp);
	
					_tmp = awb.getTotalWeightLbs() + (_tmpItemPalletized.getItemWeight() * _tmpItemPalletized.getPieces());
					awb.setTotalWeightLbs(_tmp);
					
					pieces += _tmpItemPalletized.getPieces();
				}
				awb.setPieces(pieces);
			}
		}else{
			double weightLbs = 0;
			double weightVol = 0;
			if(awb.getAwbHouses() != null){
				for(Awb _tmpAwbHouse : awb.getAwbHouses()){
					weightLbs += _tmpAwbHouse.getTotalWeightLbs();
					weightVol += _tmpAwbHouse.getTotalWeightVol();
					pieces += _tmpAwbHouse.getPieces();
				}
				awb.setTotalWeightLbs(weightLbs);
				awb.setTotalWeightVol(weightVol);
				awb.setPieces(pieces);
			}
		}
	}

	public void calculateTotalPieces() {
		int pieces = 0;
		if(!awb.isMaster()){
			if (awb.getAwbItems() != null) {
				for (AwbItem _tmpItem : awb.getAwbItems()) {
					if(_tmpItem.getPalletId().isEmpty()){	
						pieces = pieces + _tmpItem.getPieces();
					}
				}
			}
		}
		awb.setPieces(pieces);
	}

	public void clearMessages() {
		Iterator<FacesMessage> msgIterator = FacesContext.getCurrentInstance().getMessages();
		while (msgIterator.hasNext()) {
			msgIterator.next();
			msgIterator.remove();
		}
	}
	
	public void loadWhItemsAction() {
		if (whReceipt == null) {
			whReceipt = new WhReceipt();
		}
		if (!whReceipt.getWhReceiptNumber().isEmpty()) {
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
				setErrorMessage("Error trying to get the WhItems.\n"
						+ e.getMessage());
				e.printStackTrace();
			}
		} else {
			whReceipt = new WhReceipt();
		}
	}
	
	public void loadDeclaredValuesAction(){
		try {
			awb.setAwbDeclaredValues(awbLogic.loadAwbDeclaredValues(awb));
			double suma=0;
			for(int i=0;i<awb.getAwbDeclaredValues().size();i++) suma = suma+ awb.getAwbDeclaredValues().get(i).getTotalInvoce();
			awb.setDeclaredValue(suma);
			
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the BL Declared Values. \n"+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void processWhItemsAction() {
		if (whReceipt.getWhItems() != null) {
			boolean itemSelected = false; /* sirve para que cuando no se seleccione ningun item de wh no se haga guardado en la BD.*/
			List<WhItem> _tmpWhItemsList = new ArrayList<WhItem>();
			for (WhItem item : whReceipt.getWhItems()) {
				if (item.isShip() || item.getNumberPiecesToShip() >= item.getPieces()) {
					itemSelected = true;
					// agreguelo a los items de awb.
					AwbItem _tmpAwbItem = new AwbItem();
					_tmpAwbItem.setAwbId(awb.getAwbId());
					_tmpAwbItem.setPieces(item.getPieces());
					_tmpAwbItem.getType().setValueId(item.getType().getValueId());
					_tmpAwbItem.setItemLength(item.getItemLength());
					_tmpAwbItem.setItemWidth(item.getItemWidth());
					_tmpAwbItem.setItemHeight(item.getItemHeight());
					_tmpAwbItem.setWeightLbs(item.getItemWeight());
					_tmpAwbItem.getWhReceipt().setWhReceiptNumber(item.getWhReceipt().getWhReceiptNumber());
					_tmpAwbItem.getWhReceipt().setWhReceiptId(item.getWhReceipt().getWhReceiptId());
					_tmpAwbItem.setClientOrderId(item.getClientOrderId());
					_tmpAwbItem.setWhItemId(item.getWhItemId());
					_tmpAwbItem.setPoNumber(item.getPoNumber());
					_tmpAwbItem.setHazardous(item.isHazardous());
					if (item.isHazardous()) {
						_tmpAwbItem.getRateClass().setValueId(Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS);
					}
					_tmpAwbItem.setRemarks(item.getRemarks());
					_tmpAwbItem.getWhLocation().setWhLocationId(item.getLocationId()); //cambiar esto a locationId cuando se haga modulo WH.
					_tmpAwbItem.setCreatedDate(new Date());
					awb.getAwbItems().add(_tmpAwbItem);
					if(!awb.getClientPoNumber().isEmpty() && item.getPoNumber() != null){
						if(!item.getPoNumber().isEmpty()){
							awb.setClientPoNumber(awb.getClientPoNumber().concat(","));
						}	
					}
					if(item.getPoNumber() != null){
						awb.setClientPoNumber(awb.getClientPoNumber().concat(item.getPoNumber())); /*Concatena el PO Number al campo clientPO de la guia. */
					}
		
					// acualice el estado del item, es decir, poner -1 en el
					// campo shipped.(Al final del ciclo se hace un update del
					// campo shipped en al BD.)
					item.setShipped(true);

				} else if (item.getNumberPiecesToShip() > 0 && item.getNumberPiecesToShip() < item.getPieces()) {
					itemSelected = true;
					// agregue ese item a items de awb.
					AwbItem _tmpAwbItem = new AwbItem();
					_tmpAwbItem.setAwbId(awb.getAwbId());
					_tmpAwbItem.setPieces(item.getNumberPiecesToShip()); /* aca se pone el numero de pieces seleccionadas*/
					_tmpAwbItem.getType().setValueId(item.getType().getValueId());
					_tmpAwbItem.setItemLength(item.getItemLength());
					_tmpAwbItem.setItemWidth(item.getItemWidth());
					_tmpAwbItem.setItemHeight(item.getItemHeight());
					_tmpAwbItem.setWeightLbs(item.getItemWeight());
					_tmpAwbItem.getWhReceipt().setWhReceiptNumber(item.getWhReceipt().getWhReceiptNumber());
					_tmpAwbItem.getWhReceipt().setWhReceiptId(item.getWhReceipt().getWhReceiptId());
					_tmpAwbItem.setClientOrderId(item.getClientOrderId());
					_tmpAwbItem.setWhItemId(item.getWhItemId());
					_tmpAwbItem.setPoNumber(item.getPoNumber());
					_tmpAwbItem.setHazardous(item.isHazardous());
					if (item.isHazardous()) {
						_tmpAwbItem.getRateClass().setValueId(Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS);
					}
					_tmpAwbItem.setRemarks(item.getRemarks());
					_tmpAwbItem.getWhLocation().setWhLocationId(item.getLocationId()); //cambiar esto a locationId cuando se haga modulo WH.
					_tmpAwbItem.setCreatedDate(new Date());
					awb.getAwbItems().add(_tmpAwbItem);

					// y cree el item de las piezas sobrantes en la tabla
					// whDetails.
					WhItem _tmpWhItem = (WhItem) item.clone();
					_tmpWhItem.setWhItemId(0);
					_tmpWhItem.setPieces(item.getPieces() - item.getNumberPiecesToShip());
					_tmpWhItem.setNumberPiecesToShip(0);
					_tmpWhItemsList.add(_tmpWhItem);
					if(!awb.getClientPoNumber().isEmpty()){
						awb.setClientPoNumber(awb.getClientPoNumber().concat(","));
					}
					awb.setClientPoNumber(awb.getClientPoNumber().concat(item.getPoNumber()));	/*Concatena el PO Number al campo clientPO de la guia. */
					
					if(!awb.getWhRemarks().isEmpty()){
						awb.setWhRemarks(awb.getWhRemarks().concat(","));
					}
					awb.setWhRemarks(awb.getWhRemarks().concat(whReceipt.getRemarks()));
					
					// acualice el estado del item, es decir, poner -1 en el
					// campo shipped.
					item.setShipped(true);
					item.setPieces(item.getNumberPiecesToShip());

					// Al final del ciclo se hace un create en la BD de los
					// items que resultaron nuevos
					// y se hace update del campo shipped de los items que se
					// escogieron.
				}
			}
			if(awb.getWhRemarks() != null){
				if(!awb.getWhRemarks().isEmpty()){
					if(awb.getWhRemarks().charAt(awb.getWhRemarks().length()-1) != '.'){
						awb.setWhRemarks(awb.getWhRemarks().concat("."));
					}
				}
			}else{
				awb.setWhRemarks("");
			}
			awb.setWhRemarks(awb.getWhRemarks().concat(whReceipt.getRemarks()));
			
			
			if (itemSelected) {
				whReceipt.getWhItems().addAll(_tmpWhItemsList);
				try {
					whItemLogic.saveWhItem(whReceipt.getWhItems());
				} catch (Exception e) {
					setErrorMessage("Error trying to save the WhItems.\n" + e.getMessage());
					e.printStackTrace();
				}
			}
			saveAwbItemsAction();
			loadDeclaredValuesAction();
		}
		clearListsPalletizedAndWhItems();
	}

	private void loadPalletizedItemsAction() {
		try {
			invoice.setPalletizedItemsList(palletizedItemLogic
					.loadPalletizedItems(invoice));
			if (invoice.getPalletizedItemsList() != null) {
				if (invoice.getPalletizedItemsList().size() == 0) {
					setErrorMessage("The Invoice does not have items to ship.");
				} else {
					invoiceItems = invoice.getPalletizedItemsList();
				}
			} else {
				setErrorMessage("The Invoice does not have items to ship.");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Palletizer Items.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void processPalletizedItemsAction() {
		if (invoiceItems != null) {
			for (Object _tmpItem : invoiceItems) {
				PalletizedItem item = new PalletizedItem();
				item = (PalletizedItem) _tmpItem;
				if (item.getNumberPiecesToShip() > 0) {

					AwbItem _tmpAwbItem = new AwbItem();
					_tmpAwbItem.setAwbId(awb.getAwbId());
					if (item.getNumberPiecesToShip() > item.getPieces()) {
						_tmpAwbItem.setPieces(item.getPieces());
					} else {
						_tmpAwbItem.setPieces(item.getNumberPiecesToShip());
					}

					_tmpAwbItem.getType().setValueId(
							item.getType().getValueId());
					_tmpAwbItem.setItemLength(item.getLength());
					_tmpAwbItem.setItemWidth(item.getWidth());
					_tmpAwbItem.setItemHeight(item.getHeight());
					_tmpAwbItem.setWeightLbs(item.getWeight());
					_tmpAwbItem.getInvoice().setInvoiceId(
							invoice.getInvoiceId());
					_tmpAwbItem.getInvoice().setInvoiceNumber(
							invoice.getInvoiceNumber());
					_tmpAwbItem.setHazardous(item.isHazardous());
					if (_tmpAwbItem.isHazardous()) {
						_tmpAwbItem.getRateClass().setValueId(
								Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS);
					}
					_tmpAwbItem.setCreatedDate(new Date());
					awb.getAwbItems().add(_tmpAwbItem);
				}
			}
		}
		clearListsPalletizedAndWhItems();
	}

	private void loadPackingListItemAction() {
		try {
			invoice.setPackingListItemsList(packingListItemLogic.loadPackingListItems(invoice));
			if (invoice.getPackingListItemsList() != null) {
				if (invoice.getPackingListItemsList().size() == 0) {
					setErrorMessage("The Invoice does not have items to ship.");
				} else {
					invoiceItems = invoice.getPackingListItemsList();
				}
			} else {
				setErrorMessage("The Invoice does not have items to ship.");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Palletizer Items.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private void processPackingListItemsAction() {
		if (invoiceItems != null) {
			for (Object _tmpItem : invoiceItems) {
				PackingListItem item = new PackingListItem();
				item = (PackingListItem) _tmpItem;
				if (item.getNumberPiecesToShip() > 0) {

					AwbItem _tmpAwbItem = new AwbItem();
					_tmpAwbItem.setAwbId(awb.getAwbId());
					if (item.getNumberPiecesToShip() > item.getPieces()) {
						_tmpAwbItem.setPieces(item.getPieces());
					} else {
						_tmpAwbItem.setPieces(item.getNumberPiecesToShip());
					}

					_tmpAwbItem.getType().setValueId(
							item.getType().getValueId());
					_tmpAwbItem.setItemLength(item.getLength());
					_tmpAwbItem.setItemWidth(item.getWidth());
					_tmpAwbItem.setItemHeight(item.getHeight());
					_tmpAwbItem.setWeightLbs(item.getWeight());
					_tmpAwbItem.getInvoice().setInvoiceId(
							invoice.getInvoiceId());
					_tmpAwbItem.getInvoice().setInvoiceNumber(
							invoice.getInvoiceNumber());
					_tmpAwbItem.setHazardous(item.isHazardous());
					if (_tmpAwbItem.isHazardous()) {
						_tmpAwbItem.getRateClass().setValueId(
								Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS);
					}
					_tmpAwbItem.setWhReceipt(item.getWhReceipt());
					_tmpAwbItem.setCreatedDate(new Date());
					awb.getAwbItems().add(_tmpAwbItem);
				}
			}
		}
		clearListsPalletizedAndWhItems();
	}

	public void loadInvoiceAction() {
		invoiceItems = null;
		String invoiceNumber = invoice.getInvoiceNumber();
		if (!invoiceNumber.isEmpty()) {
			try {
				invoice = new Invoice();
				invoice.setInvoiceNumber(invoiceNumber);
				invoice = invoiceLogic.loadInvoice(invoice);

				if (invoice.getInvoiceId() > 0) {
					if (invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_RM) {
						loadPalletizedItemsAction();
					} else if (invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
						loadPackingListItemAction();
					} else {
						setErrorMessage("The Invoice does not have items to ship.");
					}
				} else {
					setErrorMessage("The Invoice does not exist.");
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to get the Palletizer Items.\n"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void processInvoiceItemsAction() {
		if (invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_RM) {
			processPalletizedItemsAction();
		} else if (invoice.getGroupId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
			processPackingListItemsAction();
		}
	}

	public void clearListsPalletizedAndWhItems() {
		whReceipt = new WhReceipt();
		invoice = new Invoice();
		invoiceItems = null;
	}

	public void selectToDeleteAwbItemAction() {
		try {
			awbItem = new AwbItem();
			setAwbItem((AwbItem) awbItemsTable.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAwbItemAction() {
		if (awbItem.getWhItemId() > 0) {
			// La busco en la BD y le actualizo el campo shipped a false.
			WhItem _tmpWhItem = new WhItem();
			_tmpWhItem.setWhItemId(awbItem.getWhItemId());
			try {
				_tmpWhItem = whItemLogic.loadWhItem(_tmpWhItem);
				_tmpWhItem.setShipped(false);
				if (whItemLogic.updateWhItem(_tmpWhItem)) {
					if (awbItemsLogic.deleteAwbItem(awbItem)) { /* Elimino el item de la tabla AwbItems*/
						if (awb.getAwbItems().remove(awbItem)) { /* Elimino de la lista*/
							saveAwb();
							loadDeclaredValuesAction();
							setInfoMessage("Item was successfully removed");
						} else {
							setErrorMessage("Error trying to remove the Item.\n");
						}
					} else {
						setErrorMessage("Error trying to remove the Item.\n");
					}
				} else {
					setWarningMessage("Error trying to update the Item");
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to remove the Item.\n"
						+ e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
				if (awbItemsLogic.deleteAwbItem(awbItem)) { /* Elimino el item de la tabla AwbItems*/
					if (awb.getAwbItems().remove(awbItem)) { /* Elimino de la lista*/
						saveAwb();
						setInfoMessage("Item was successfully removed");
					} else {
						setErrorMessage("Error trying to remove the Item.\n");
					}
				} else {
					setErrorMessage("Error trying to remove the Item.\n");
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to remove the Item.\n"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void saveAwb() {
		calculateTotalPieces();
		calculateTotalWeights();
		try {
			awb = awbLogic.saveAwb(awb);
		} catch (Exception e) {
			setErrorMessage("Error.\n");
			e.printStackTrace();
		}
	}
	
	public boolean isContainHazardous(){
		for(AwbItem item : awb.getAwbItems()){/* Compureba si existen items con hazardous.*/	
			if(item.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
				return true;
			}
		}
		return false;
	}

	private Awb updateCharges(Awb awb) {	
		
		awb.setContainDangerousItems(false);
		awb.setContainOversizeItems(false);
		awb.setContainRefrigeratedItems(false);
		for(AwbItem item : awb.getAwbItems()){/* Compureba si existen items con hazardous.*/	
			if(item.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
				awb.setContainDangerousItems(true);
			}else if(item.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
				awb.setContainOversizeItems(true);
			}else if(item.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
				awb.setContainRefrigeratedItems(true);
			}
		}
		List<AwbCostSale> awbInitialCostsSales = new ArrayList<AwbCostSale>();
		try {
			awbInitialCostsSales = awbLogic.loadInitialCostsSales(awb);  /*Carga los CostSales que asigna por defecto el sistema.*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Lo recorre en un sentido*/
		for (AwbCostSale _tmpCS : awbInitialCostsSales) {
			boolean exist = false;
			for (AwbCostSale _tmpCostSale : awb.getAwbCostsSales()) {
				if (_tmpCS.getChargeType().getValueId() == _tmpCostSale.getChargeType().getValueId()) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				awb.getAwbCostsSales().add(_tmpCS);
			}
		}
		
		/*Lo recorre en el otro sentido*/
		List<AwbCostSale> _tmpListCS = new ArrayList<AwbCostSale>();
		for (AwbCostSale _tmpCostSale : awb.getAwbCostsSales()) {
			boolean exist = false;
			for (AwbCostSale _tmpCS : awbInitialCostsSales) {
				if (_tmpCS.getChargeType().getValueId() == _tmpCostSale.getChargeType().getValueId()) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				_tmpListCS.add(_tmpCostSale);
			}
		}
		awb.getAwbCostsSales().removeAll(_tmpListCS);
		for (AwbCostSale _tmpCostSale : _tmpListCS) {  /* Recorre la lista para luego eliminarla de la base de datos*/
			if (_tmpCostSale.getAwbCostsaleId() > 0) {
				try {
					if(awbCostSaleLogic.deleteCostSale(_tmpCostSale)){
						setInfoMessage("Item was successfully removed");
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to delete the Cost & Sale Item. \n"
							+ e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return awb;
	}
	
	public void processAwbHouses(){
		for(Awb _tmpAwb : awbHousesAvailableList){
			if(_tmpAwb.isSelected()){
				_tmpAwb.setAwbMaster(new Awb());
				_tmpAwb.getAwbMaster().setAwbId(awb.getAwbId());
				awb.getAwbHouses().add(_tmpAwb);
			}
		}
		try {
			awb.setAwbHouses(awbLogic.updateAwbHouseList(awb.getAwbHouses()));
			awb.setAwbFreightInvoices(new ArrayList<AwbFreightInvoice>());
			awb.setAwbOtherInvoices(new ArrayList<AwbOtherInvoice>());
			awb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(awb.getAwbId())));				
			if(awb.getAwbHouses() != null){
				for(Awb _tmpHouseAwb : awb.getAwbHouses()){
					loadAwbHouseInformationToMaster(_tmpHouseAwb);
				}
				processMasterAwbCSAction();
			}else{
				awb.setAwbHouses(new ArrayList<Awb>());
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to update the Awb Houses");
			e.printStackTrace();
		}
		
	}
	
	public void removeAwbHouseFromMaster(){
		Awb _tmpAwbHouse = (Awb) awbHousesTable.getRowData();
		_tmpAwbHouse.getAwbMaster().setAwbId(0);
		try {
			if(awbLogic.updateHouse(_tmpAwbHouse)){
				if(awb.getAwbHouses().remove(_tmpAwbHouse)){
					setInfoMessage("AWB "+_tmpAwbHouse.getAwbNumber() +" was sucessfully removed.");
					processMasterAwbCSAction();
					awb.getAwbUnCodes().removeAll(_tmpAwbHouse.getAwbUnCodes());
					calculateTotalWeights();
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to remove the Awb House from Master.");
			e.printStackTrace();
		}
	}
	
	public void selectHousesFromMaster(ActionEvent event){
		Awb _tmpAwb = (Awb) awbHousesTable.getRowData();
		setSessionAttribute("_tmpAwb", _tmpAwb);
		_tmpAwb = new Awb();
	}
	
	public void processMasterAwbCSAction(){
		HashMap <Integer,String> awbCSNames;
		ArrayList<String> _auxData;
		boolean wasFound = false;
		String currencySimbol= "$";
		headersMasterCS = new ArrayList<String>();
		subHeadersMasterCS = new ArrayList<String>();
		dataMasterCS = new ArrayList<Object[]>();
		
		if (converterName.equals("converterDollar")) currencySimbol = "$";
		if (converterName.equals("converterEuro")) currencySimbol = "€";
		
		awbCSNames = new HashMap<Integer, String>();
		_auxData = new ArrayList<String>();
		
		if (awb.getAwbCostsSales() != null && awb.getAwbOtherCostsSales() != null) {
			
			 //save  labels from cost sales
			 for(AwbCostSale _tmpCS:  awb.getAwbCostsSales()){
				 if(_tmpCS.isShowInMaster()){
					 awbCSNames.put(_tmpCS.getChargeType().getValueId(), _tmpCS.getChargeType().getValue1());
				 }
			 }		
					
			// load Cost Sale values
			for(Awb _tmpAwb:  awb.getAwbHouses()){
				_auxData.add(_tmpAwb.getAwbNumber());	
					
				Iterator itr = awbCSNames.entrySet().iterator();
				while (itr.hasNext()) {
					Map.Entry e = (Map.Entry)itr.next();				
					wasFound = false;
					// for each normal Costs and Sales
					for(AwbCostSale _tmpCS:  _tmpAwb.getAwbCostsSales()){	
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
				
				 /*// If it is Other Cost
				 if (awb.getAwbOtherCostsSales() != null){
					 double _acumCost = 0;
					 double _acumSale = 0;
					// for each Other Costs and Sales
					 for(AwbCostSale _tmpCS:  _tmpAwb.getAwbOtherCostsSales()){								
						 _acumCost = _acumCost + _tmpCS.getCost();
						 _acumSale = _acumSale + _tmpCS.getSale();
					 }
					 _auxData.add(currencySimbol + UtilFunctions.roundDecimalPlaces(_acumCost, 2));
					 _auxData.add(currencySimbol + UtilFunctions.roundDecimalPlaces(_acumSale, 2));	
				 }*/
				
				//add values to show in table
				String []strArray = _auxData.toArray(new String[_auxData.size()]);		
				dataMasterCS.add(strArray);
				_auxData =  new ArrayList<String>();			
			}
				
			//add headers to show in table
			subHeadersMasterCS.add("AWB #");
			Iterator itr = awbCSNames.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry e = (Map.Entry)itr.next();			
				headersMasterCS.add(e.getValue() + "");
				subHeadersMasterCS.add("Cost");
				subHeadersMasterCS.add("Sale");
			}
			 /*// If it is Other Cost
			 if (awb.getAwbOtherCostsSales() != null){
				 headersMasterCS.add("Other");
				 subHeadersMasterCS.add("Cost");
				 subHeadersMasterCS.add("Sale");
			 }	*/	 
		}	
	}

	private boolean checkHazardousItem(Awb awb) {
		if (awb.getAwbItems() != null) {
			for (AwbItem item : awb.getAwbItems()) {
				if (item.isHazardous()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/* Comprueba si esta vigente los costos del carrier respectoa a la fecha de creacion de la guia*/
	public void validEffectiveDate(){
		boolean isValid = true;
		if(awb.isRegular()){
			CarrierPorts _tmpCarrierPort = new CarrierPorts();
			_tmpCarrierPort.setCarrierId(awb.getCarrier().getCarrierId());
			_tmpCarrierPort.setPortOrigin(awb.getAirportOrigin().getPortId());
			_tmpCarrierPort.setPortDestination(awb.getAirportDestination().getPortId());
			Date effectiveDate = null;
			try {
				effectiveDate = (awbLogic.loadEffectiveDate(_tmpCarrierPort)).getEffectiveDate();
			} catch (Exception e) {
				setErrorMessage("Error trying to retrieve the effective Date.\n" + e.getMessage());
				e.printStackTrace();
			}
			if(effectiveDate != null) isValid = awb.getCreatedDate().before(effectiveDate);
			awb.setValidEffectiveDate(!isValid);
		}else if(awb.isHouse()){
			awb.setValidEffectiveDate(true);
		}
		
	}

	public void recalculateCostSalesAction() {
		List<ClientRate> clientRates = null;
		clientRates = awbLogic.loadClientRates(awb);			/* Carga los rates de clientRates*/
		
		List<CarrierRate> carrierRates = null;
		carrierRates = awbLogic.loadCarrierRates(awb);		/* Carga los rates de carrierRates*/
		
		awb = awbLogic.recalculateAwbSales(awb, clientRates);
		if(!awb.isHouse()){ 								/* No recalcula los costos de una guia hija desde el boton recalculate, porque se recalcula desde la guia master que contiene a esta house.*/
			awb = awbLogic.recalculateAwbCosts(awb, carrierRates);
		}
		calculateTotalCostsSales();
	}
	
	public void prorateAwbAction() {	
		try {
			if(null != awb && awb.getAwbId() > 0){
				List<ItemProrated> itemsProrated = new ArrayList<ItemProrated>();
				itemsProrated = getAwbItemsToProrate();
				
				for(ItemProrated _itemProrated: itemsProrated){
					for(AwbCostSale _tmpItemPCS : _itemProrated.getAwbCostSalesProrated()){
						if (awb.getTotalWeightKgs() != 0){
							//Awb se va por peso 
							if(awb.getTotalWeightLbs() > awb.getTotalWeightVol()){
								_tmpItemPCS.setSale(UtilFunctions.roundDecimalPlaces((_itemProrated.getWeight()* _tmpItemPCS.getSale())/awb.getTotalWeightKgs(),2)) ;
								
							//Awb se va por volumen	
							}else{
								_tmpItemPCS.setSale(UtilFunctions.roundDecimalPlaces((_itemProrated.getVolume()* _tmpItemPCS.getSale())/awb.getTotalWeightVolKgs(),2) );
							}
						}
					}
				}
					
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext ectx = ctx.getExternalContext();
				ServletContext context = (ServletContext)ectx.getContext();
				
				//Ruta y nombre del jasper del cual se desea generar el reporte 
				String _tmpFile = context.getRealPath("WEB-INF/reports/LODepartment/awb/AWBProrate.jasper");
				
				
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("shipper", awb.getPickupFrom().getAddress().getAddress());
				parametros.put("consignee", awb.getShipTo().getAddress().getAddress());
				parametros.put("booking",( awb.getBooking().equals(""))? awb.getShipTo().getAddress().getAddress() : awb.getBooking());
				parametros.put("destinationPort", awb.getAirportDestination().getName());
				parametros.put("freightInvoices", awb.getConcatenatedFreightInvoices());
				
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
	
	private List<ItemProrated> getAwbItemsToProrate(){
		List<ItemProrated> itemsProrated = new ArrayList<ItemProrated>();
		List<Integer> invoicesId = new ArrayList<Integer>();
		List<String> poNumbers = new ArrayList<String>();
		
		//agregue los InvoicesId y PoNumbers
		for(AwbItem _tmpAwbItem : awb.getAwbItems()){
			if( _tmpAwbItem.getInvoice().getInvoiceId() > 0 && (_tmpAwbItem.getPalletId() == null ||_tmpAwbItem.getPalletId().equals(""))){
				if(!invoicesId.contains(new Integer(_tmpAwbItem.getInvoice().getInvoiceId()))){
					invoicesId.add(new Integer(_tmpAwbItem.getInvoice().getInvoiceId()) );
				}
			}else{
				if(!poNumbers.contains(_tmpAwbItem.getPoNumber()) && (_tmpAwbItem.getPalletId() == null ||_tmpAwbItem.getPalletId().equals(""))){
					poNumbers.add(_tmpAwbItem.getPoNumber());
				}
			}
		}
		
		//agregue los items palletizados a los items a prorratear
		for(AwbPalletizedItem _tmpAwbPalletizedItem : awb.getAwbPalletizedItems()){
			ItemProrated _itemProrated = new ItemProrated();
			_itemProrated.setByPallet(true);
			_itemProrated.setOrder("Consolidated " + _tmpAwbPalletizedItem.getPalletId() );
			_itemProrated.getSupplier().setName("LOT LOGISTICS");
			_itemProrated.setOriginalInvoice(true);
			_itemProrated.setPieces( _tmpAwbPalletizedItem.getPieces());
			// en kg
			_itemProrated.setWeight( UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(_tmpAwbPalletizedItem.getItemWeight() *_tmpAwbPalletizedItem.getPieces()),2));
			// en Kg
			_itemProrated.setVolume( UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(((_tmpAwbPalletizedItem.getItemHeight() * _tmpAwbPalletizedItem.getItemLength() * _tmpAwbPalletizedItem.getItemWidth()) /166) *_tmpAwbPalletizedItem.getPieces()),2));
			//Agregar CostSales del Awb al ItemProrated
			for (AwbCostSale _tmpAwbCS : awb.getAwbCostsSales())	_itemProrated.getAwbCostSalesProrated().add((AwbCostSale)_tmpAwbCS.clone());
			//Agregar Other CostSales del Awb al ItemProrated
			for (AwbCostSale __tmpAwbCS : awb.getAwbOtherCostsSales())	_itemProrated.getAwbCostSalesProrated().add((AwbCostSale)__tmpAwbCS.clone());
			itemsProrated.add(_itemProrated);	
		}
		
		
		//agregue los items con Factura a los items a prorratear
		for(Integer _tmpAwbItemInvoiceId : invoicesId){
			ItemProrated _itemProrated = new ItemProrated();
			for(AwbItem _tmpAwbItem : awb.getAwbItems()){
				if(_tmpAwbItemInvoiceId.intValue() == _tmpAwbItem.getInvoice().getInvoiceId() && (_tmpAwbItem.getPalletId() == null ||_tmpAwbItem.getPalletId().equals(""))){
					_itemProrated.setByInvoice(true);
					_itemProrated.setInvoice(_tmpAwbItem.getInvoice());					
					_itemProrated.getSupplier().setName("LOT LOGISTICS");
					_itemProrated.setOriginalInvoice(true);
					_itemProrated.setPieces(_itemProrated.getPieces() + _tmpAwbItem.getPieces());
					// en kg
					_itemProrated.setWeight(_itemProrated.getWeight() + UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(_tmpAwbItem.getWeightLbs() *_tmpAwbItem.getPieces()),2));
					// en kg
					_itemProrated.setVolume(UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(((_tmpAwbItem.getItemHeight() * _tmpAwbItem.getItemLength() * _tmpAwbItem.getItemWidth()) /166) *_tmpAwbItem.getPieces()),2));
				}
			}
			//Agregar CostSales del Awb al ItemProrated
			for (AwbCostSale _tmpAwbCS : awb.getAwbCostsSales())	_itemProrated.getAwbCostSalesProrated().add((AwbCostSale)_tmpAwbCS.clone());
			//Agregar Other CostSales del Awb al ItemProrated
			for (AwbCostSale _tmpAwbCS : awb.getAwbOtherCostsSales())	_itemProrated.getAwbCostSalesProrated().add((AwbCostSale)_tmpAwbCS.clone());
			itemsProrated.add(_itemProrated);	
		}
		
		//agregue los items con WH a los items a prorratear
		for(String _tmpAwbItemPoNumber : poNumbers){
			ItemProrated _itemProrated = new ItemProrated();
			for(AwbItem _tmpAwbItem : awb.getAwbItems()){
				if(_tmpAwbItemPoNumber.equals(_tmpAwbItem.getPoNumber()) && (_tmpAwbItem.getPalletId() == null ||_tmpAwbItem.getPalletId().equals(""))){
					_itemProrated.setByPoNumber(true);
					
					ClientOrder _auxCO = new ClientOrder();
					_auxCO.setClientOrderId(_tmpAwbItem.getClientOrderId());
					
					_itemProrated.setClientOrder(_auxCO);
					_itemProrated.setOrder(_tmpAwbItem.getPoNumber());			
					
					//TODO: VER SI SE HACE DE UNA SOLA LLAMADA		
					//_itemProrated.setOriginalInvoice(_itemProrated.getClientOrder().isOriginalInvoice);
					_itemProrated.setOriginalInvoice(false);
					
					_itemProrated.setPieces(_itemProrated.getPieces() + _tmpAwbItem.getPieces());
					// en kg
					_itemProrated.setWeight(_itemProrated.getWeight() + UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(_tmpAwbItem.getWeightLbs() *_tmpAwbItem.getPieces()),2));
					// en kg
					_itemProrated.setVolume(UtilFunctions.roundDecimalPlaces(UtilFunctions.convertLbToKg(((_tmpAwbItem.getItemHeight() * _tmpAwbItem.getItemLength() * _tmpAwbItem.getItemWidth()) /166) *_tmpAwbItem.getPieces()),2));
				}
			}
			//Agregar CostSales del Awb al ItemProrated
			for (AwbCostSale _tmpAwbCS : awb.getAwbCostsSales())	_itemProrated.getAwbCostSalesProrated().add((AwbCostSale)_tmpAwbCS.clone());
			//Agregar Other CostSales del Awb al ItemProrated
			for (AwbCostSale _tmpAwbCS : awb.getAwbOtherCostsSales())	_itemProrated.getAwbCostSalesProrated().add((AwbCostSale)_tmpAwbCS.clone());
			itemsProrated.add(_itemProrated);	
		}
		try {
			itemsProrated = awbLogic.fillAwbItemsProratedInformation(itemsProrated);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return  itemsProrated;
	}
	
	public void reportAwbAction() {		
		List<AwbItem> _items = new ArrayList<AwbItem>();
		List<AwbCostSale> _OtherCharges = new ArrayList<AwbCostSale>();
		
		double totalPrice = 0;
		double totalOtherCharges = 0;
		boolean isDG = false;
		int piecesDG = 0;
		
		_items = awbLogic.loadAwbSummarizedItems(awb);
		_OtherCharges =  loadAwbSummarizedCS();
		
		for(AwbItem _auxItem : _items){
			totalPrice += _auxItem.getRateTotal();
			if(!_auxItem.isEmpty()){				
				// si el item con DG no esta vacio entonces hay DGs  
				if(_auxItem.getRateClass().getValue1().equals("DG")){				
					isDG = true;
					piecesDG = _auxItem.getPieces();
				}
			}
		}
		
		for(AwbCostSale _auxCS : _OtherCharges){
			totalOtherCharges += _auxCS.getSale();
		}
		totalPrice = UtilFunctions.roundDecimalPlaces(totalPrice, 2);
		totalOtherCharges =UtilFunctions.roundDecimalPlaces(totalOtherCharges, 2);
		
	
		
		try {
			if(null != awb && awb.getAwbId()>0){
									
				FacesContext ctx = FacesContext.getCurrentInstance();
				ExternalContext ectx = ctx.getExternalContext();
				ServletContext context = (ServletContext)ectx.getContext();
				
				//Ruta y nombre del jasper del cual se desea generar el reporte 
				String _tmpFile = context.getRealPath("WEB-INF/reports/LODepartment/awb/AWB.jasper");
				
				
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("currency", "USD");
				parametros.put("currencySimbol", "$");
				parametros.put("awbSummarizedItems", _items);
				parametros.put("awbTotalVolumeM3", awbLogic.calculateTotalVolumeM3(awb));				
				parametros.put("awbSummarizedCS", _OtherCharges);
				parametros.put("totalRate", totalPrice);
				parametros.put("totalOtherCharges", totalOtherCharges);
				parametros.put("isDG", isDG);
				parametros.put("piecesDG", piecesDG);
				
			
								
				//Se llena el objeto con la informacion que se desea imprimir
				ParametrosReporte parametrosReporte = new ParametrosReporte();
				
				// poner los master values a payment type
				if ( awb.getPaymentType().getValueId() == Constants.MASTER_VALUE_PREPAID){
					awb.getPaymentType().setValue1("PRE-PAID");
				}else if(awb.getPaymentType().getValueId() == Constants.MASTER_VALUE_COLLECT){
					awb.getPaymentType().setValue1("COLLECT");
				}
				
				// poner la ciudad al puerto de destino				
				for ( Port _auxPort : getAllPortsList()){
					if (awb.getAirportDestination().getPortId() == _auxPort.getPortId()){
						awb.getAirportDestination().getCity().setName(_auxPort.getCity().getName());
					}
					
				}
				
				// poner el nombre al carrier
				for (SelectItem _auxCarrier : getCarriersList() ){
					Integer auxCarrierId = (Integer) _auxCarrier.getValue();
					if(awb.getCarrier().getCarrierId() == auxCarrierId.intValue()){
						awb.getCarrier().setName(_auxCarrier.getLabel());						
					}
				}
				
				
				List <Awb> _tmpAWBList = new ArrayList<Awb>();
				_tmpAWBList.add(awb);
				
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
				parametrosReporte.setListaObjetos(_tmpAWBList);
				
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
	
	public List<AwbCostSale> loadAwbSummarizedCS() {
		List<AwbCostSale> awbCostSales = new ArrayList<AwbCostSale>();
		HashMap <Integer,AwbCostSale> awbCSNames;
		awbCSNames = new HashMap<Integer, AwbCostSale>();
		
		int counter = 0;	
		
		//Recorre los Cost y Sales buscando los UN Charges
		if (awb.isMaster()){			
			for(Awb _tmpHouse : awb.getAwbHouses()){				
				awbCostSales.addAll( loadUNCodesSummarized( _tmpHouse));
			}
			
		}else{				
			awbCostSales.addAll( loadUNCodesSummarized( awb));
			
		}
		counter += awbCostSales.size();
		
		if (awb.isMaster()){	
			
			/* Recorre los Cost y Sales y los Other Cost y sales para agregarlos a una lista auxiliar. En la lista solo caben 10 elementos		 
			 */
			
			 for(AwbCostSale _tmpCS:  awb.getAwbCostsSales()){
				 if(_tmpCS.isShowInMaster()&& _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT &&
										    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS &&
										    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE &&
										    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED &&
										    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
					 
					 if(awbCSNames.containsKey(_tmpCS.getChargeType().getValueId())){
						 awbCSNames.get(_tmpCS.getChargeType().getValueId()).setSale( awbCSNames.get(_tmpCS.getChargeType().getValueId()).getSale() + _tmpCS.getSale());
						 System.out.println("Valor de venta :  " +  _tmpCS.getSale());
					 }else{
						 awbCSNames.put(_tmpCS.getChargeType().getValueId(), (AwbCostSale)_tmpCS.clone());
						 System.out.println("Valor de venta inicial  :  " +  _tmpCS.getSale());
					 }
				 }
			 }	
			Iterator itr = awbCSNames.entrySet().iterator();
			AwbCostSale _awbCS ;
			while (itr.hasNext() && counter <10) {
				Map.Entry e = (Map.Entry)itr.next();	
				_awbCS =  new AwbCostSale();
				_awbCS = (AwbCostSale)e.getValue();
				_awbCS.setSale( UtilFunctions.roundDecimalPlaces(_awbCS.getSale(), 2));
				awbCostSales.add(_awbCS);		
				counter++;
			}
		}else{
			/* Recorre los Cost y Sales y los Other Cost y sales para agregarlos a una lista auxiliar. En la lista solo caben 10 elementos		 
			 */
			for(AwbCostSale _tmpCS: awb.getAwbCostsSales()){
				if (_tmpCS.isSelectedToAwbDoc()){
					if (counter < 10 && _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT &&
									    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS &&
									    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE &&
									    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED &&
									    _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
											
							awbCostSales.add(_tmpCS);
							counter++;
						}				
					}				
				}				
				
			for(AwbCostSale _tmpCS: awb.getAwbOtherCostsSales()){
				if (_tmpCS.isSelectedToAwbDoc()){
					if (counter < 10 ){
						awbCostSales.add(_tmpCS);
						counter++;
					}
				}
			}
		}						
		return awbCostSales;
	}
	
	
	public List<AwbCostSale> loadUNCodesSummarized(Awb awb){
		List<AwbCostSale> awbCostSales = new ArrayList<AwbCostSale>();
		int numUnCodes = 0;
		
		if (awb.getAwbUnCodes() != null && !awb.getAwbUnCodes().isEmpty() )
		numUnCodes =  (awb.getAwbUnCodes().get(awb.getAwbUnCodes().size()-1).isEmpty() ) ? awb.getAwbUnCodes().size() - 1: awb.getAwbUnCodes().size();
		
		
		//Recorre los Cost y Sales buscando los UN Charges
		for(AwbCostSale _tmpCS: awb.getAwbCostsSales()){
			if (_tmpCS.isSelectedToAwbDoc() || this.awb.isMaster()){
				if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
					//agregue cada uno de los UN Codes a la lista auxiliar
					for (AwbUnCode _tmpUNCode : awb.getAwbUnCodes()){
						if ( !_tmpUNCode.isEmpty()){
							AwbCostSale temporaryCS = new AwbCostSale();
							temporaryCS = (AwbCostSale)_tmpCS.clone();
							String chargeName = "Dangerous Goods: " + _tmpUNCode.getUnCode();
							for (SelectItem _auxSelectItem : getClassUnCodeList()){
								 Integer auxClassId = (Integer) _auxSelectItem.getValue();
								if (_tmpUNCode.getUnClassId() == auxClassId.intValue() ){
									chargeName += " " + _auxSelectItem.getLabel();
									break;
								}
							}
							
							temporaryCS.getChargeType().setValue1(chargeName);	
							temporaryCS.setSale( UtilFunctions.roundDecimalPlaces( temporaryCS.getSale()/ numUnCodes, 2));
							awbCostSales.add(temporaryCS);						
						}
					}
				}
			}
		}
		return awbCostSales;
	}

	public void loadInvoiceList() {
		try {
			invoice = new Invoice();
			invoice.setInvoiceNumber(null);
			invoiceList = invoiceLogic.loadInvoicesList(invoice);
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Invoices List.\n" + e.getMessage());
			e.printStackTrace();
		}
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

	public List<Awb> autoCompleteAwb(Object suggest) {
		String number = ((String) suggest).toUpperCase();
		List<Awb> result = new ArrayList<Awb>();
		try {
			for (Awb tmpAwb : awbList) {
				if ((tmpAwb != null && tmpAwb.getAwbNumber().indexOf(number) == 0 || "".equalsIgnoreCase(number))) {
					result.add(tmpAwb);
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public void loadAwbHousesAvailableList(){
		Awb _tmpAwbAux = new Awb();
		_tmpAwbAux.getAwbType().setValueId(Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE);
		_tmpAwbAux.setAwbMaster(new Awb());
		_tmpAwbAux.getAwbMaster().setAwbNumber("anything"); 	/* Este se llena para que en el procedimiento almacenado traiga solo
		 											awbHouse (Hijas) que no esten asociados a ningun awbMaster(Padre)*/
		try {
			setAwbHousesAvailableList(awbLogic.loadAwbList(_tmpAwbAux));
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Awb House List.");
			e.printStackTrace();
		}
	}
	
	public void changeRegionYesAction() {
		awb.getRegion().setValueId(regionSwitch);
		for(SelectItem reg : getRegions()){
			if((Integer)reg.getValue() == regionSwitch){
				awb.getRegion().setValue1(reg.getLabel());
			}
		}
	}
	
	public void loadContactEmailAction(){
		PartnerContact contact = new PartnerContact();
		contact.setPartnerId(awb.getClient().getPartnerId());
		contact.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
		try {
			setMainContactMail(partnerLogic.loadPartnerContactMainInfo(contact));
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Client Contact.");
			e.printStackTrace();
		}
	}
	
	private void loadCarrierMasterInfo(){ /* Carga la info del carrier de la guia master que tenga la guia house.*/
		for(Awb _tmpAwbMaster : awbList){
			if(_tmpAwbMaster.getAwbId() == awb.getAwbMaster().getAwbId()){
				awb.getAwbMaster().setCarrier(_tmpAwbMaster.getCarrier());
				return;
			}
		}
	}
	
	public void loadShipmentNotifAction(){
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String fecha = formatter.format(awb.getCreatedDate());
		
		if(awb.isRegular()){
			bodyMessage = "Cordial saludo,%0D%0DAdjuntamos Factura: "+ awb.getConcatenatedFreightInvoices()+
				"%0DDespachado por: "+awb.getCarrier().getName()+
				"%0DEl dia: "+fecha+
				"%0DCon AWB # " +awb.getCarrier().getCarrierCode()+ " - "+awb.getAwbFullNumberExtern()+
				"%0DCorresponde a la Orden:"+awb.getClientPoNumber();
		}else if(awb.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial saludo,%0D%0DAdjuntamos Factura: "+ awb.getConcatenatedFreightInvoices()+
				"%0DDespachado por: "+ awb.getCarrier().getName()+" / "+awb.getAwbMaster().getCarrier().getName()+ 
				"%0DEl dia: "+fecha+
				"%0DCon AWB #:"+awb.getAwbFullNumberExtern()+ " / "+awb.getAwbMaster().getCarrier().getCarrierCode()+ "-" +awb.getAwbMaster().getAwbFullNumberExtern()+
				"%0DCorresponde a la Orden:"+awb.getClientPoNumber();
		}else if(awb.isMaster()){
			bodyMessage = "Cordial saludo,%0D%0DAdjuntamos Factura: "+ awb.getConcatenatedFreightInvoices()+
				"%0DDespachado por: "+awb.getCarrier().getName()+
				"%0DEl dia: "+fecha+
				"%0DCon AWB #:"+awb.getAwbFullNumberExtern()+
				"%0DCorresponde a la Orden:"+awb.getClientPoNumber();
		}
		subjectMessage = "Shipment Notification - Order # "+awb.getClientPoNumber();
		loadContactEmailAction();
		if(!awb.isShipmentNotification()){
			awb.setShipmentNotification(true);
			saveAwb();
		}
	}
	
	public void loadArrivalNotifAction(){
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String fecha = new String();
		if(awb.getArrivalDate() != null){
			fecha = formatter.format(awb.getArrivalDate());  
		}
		if(awb.isRegular() || awb.isMaster()){
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # " +awb.getCarrier().getCarrierCode()+ " - " + awb.getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DAerolinea: LOT LOGISTICS + "+awb.getCarrier().getName()+
				"%0DDestino: "+awb.getAirportDestination().getName()+
				"%0DSalio en el vuelo # "+awb.getFlightNumber()+
				"%0DFecha: "+fecha+
				"%0DHora de salida: "+awb.getDepartureTime()+
				"%0D%0D*FAVOR NO RESPONDER ESTE MENSAJE%0D%0DPara liberacion de su guia puede llamar a:";
			subjectMessage = "AWB #" +awb.getCarrier().getCarrierCode()+ " - " + awb.getAwbFullNumberExtern() +" - Order # "+awb.getClientPoNumber();
		}else if(awb.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # "+awb.getAwbMaster().getCarrier().getCarrierCode()+ "-" +awb.getAwbMaster().getAwbFullNumberExtern()+ " / " +awb.getCarrier().getCarrierCode()+ "-" + awb.getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DAerolinea:"+awb.getAwbMaster().getCarrier().getName()+" + " +awb.getCarrier().getName()+
				"%0DDestino: "+awb.getAirportDestination().getName()+
				"%0DSalio en el vuelo # "+awb.getFlightNumber()+
				"%0DFecha: "+fecha+
				"%0DHora de salida: "+awb.getDepartureTime()+
				"%0D%0D*FAVOR NO RESPONDER ESTE MENSAJE%0D%0DPara liberacion de su guia puede llamar a:";
			subjectMessage = "AWB # "+ awb.getAwbMaster().getCarrier().getCarrierCode()+ "-" +awb.getAwbMaster().getAwbFullNumberExtern() +" - Order # "+awb.getClientPoNumber();
		}
		loadContactEmailAction();
		if(!awb.isArrivalNotification()){
			awb.setArrivalNotification(true);
			saveAwb();
		}
	}
	
	public void loadDocsDeliveryNotifAction(){
		if(awb.isRegular()){
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # " +awb.getCarrier().getCarrierCode()+ " - " + awb.getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DManifiesto # "+awb.getManifestNumber()+
				"%0DLa guia original fue entregada en: "+awb.getAirportDestination().getName();
		}else if(awb.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # "+ awb.getAwbFullNumberExtern()+" / "+awb.getAwbMaster().getCarrier().getCarrierCode()+"-"+ awb.getAwbMaster().getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DManifiesto # "+awb.getManifestNumber()+
				"%0DLa guia original fue entregada en: "+awb.getAirportDestination().getName();
		}else if(awb.isMaster()){
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # "+ awb.getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DManifiesto # "+awb.getManifestNumber()+
				"%0DLa guia original fue entregada en: "+awb.getAirportDestination().getName();
		}
		subjectMessage = "Documents Delivery Notification - Order # "+awb.getClientPoNumber();
		loadContactEmailAction();
		if(!awb.isDocsDeliveryNotification()){
			awb.setDocsDeliveryNotification(true);
			saveAwb();
		}
	}
	
	public void loadShipDeliveryNotifAction(){
		if(awb.isRegular()){
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # " +awb.getCarrier().getCarrierCode()+ " - " + awb.getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DLa carga fue trasladada al deposito";
		}else if(awb.isHouse()){
			loadCarrierMasterInfo(); /* Carga la info del carrier del padre de esta hija.*/
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # "+ awb.getAwbFullNumberExtern()+" / "+awb.getAwbMaster().getCarrier().getCarrierCode()+"-"+ awb.getAwbMaster().getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DLa carga fue trasladada al deposito";
		}else if(awb.isMaster()){
			bodyMessage = "Cordial saludo,%0D%0DConfirmamos que la guia # "+ awb.getAwbFullNumberExtern()+
				"%0DCorrespondiente a la Orden # "+awb.getClientPoNumber()+
				"%0DLa carga fue trasladada al deposito";
		}
		subjectMessage = "Shipment Delivery Notification - Order # "+awb.getClientPoNumber();
		loadContactEmailAction();
		if(!awb.isShipmentDeliveryNotification()){
			awb.setShipmentDeliveryNotification(true);
			saveAwb();
		}
	}
	
	public void loadPoeNotification(){
		
	}
	
	public void loadAwbNumberFromCarriers(){
		try {
			CarrierAwbNumber carrierAwbNumber = awbLogic.loadAwbNumberFromCarriers(awb);
			if(carrierAwbNumber != null){
				if(carrierAwbNumber.getCarrierAwbNumberId() > 0){
					carrierAwbNumber.setUsed(true);
					carrierAwbNumberLogic.updateCarrierAwbBlNumber(carrierAwbNumber);
					awb.setAwbFullNumberExtern(carrierAwbNumber.getAwbNumber());
					saveAwb();
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to get the Awb Number.");
			e.printStackTrace();
		}
	}
	
	public void obtainCarrierObject(){
		List<Carrier> _tmpCarriers = new ArrayList<Carrier>();
		try {
			_tmpCarriers = AdministradorListas.obtenerLista("com.lotrading.softlot.util.lists.CarrierList").getElements("faces");
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the clients List.\n" + e.getMessage());
			e.printStackTrace();
		}
		for (Carrier _tmpCarr : _tmpCarriers) {
			if(awb.getCarrier().getCarrierId() == _tmpCarr.getCarrierId()){
				awb.setCarrier(_tmpCarr);
				_tmpCarriers = null;
				return;
			}
		}
	}
	
	public void recalculateCostSalesHousesOfMaster(){
		List<CarrierRate> carrierRates = null;
		carrierRates = awbLogic.loadCarrierRates(awb);		 /* Carga los rates de carrierRates*/
		
		for(Awb _tmpHouse : awb.getAwbHouses()){
			try {
				_tmpHouse.setAwbItems(awbLogic.loadAwbItems(new AwbItem(_tmpHouse.getAwbId())));
			} catch (Exception e1) {
				setErrorMessage("Error trying to retrieve the items of AWB#: "+ _tmpHouse.getAwbNumber() +" .\n" + e1.getMessage());
				e1.printStackTrace();
			}
			updateCharges(_tmpHouse); /* Actualiza la cantidad de cargos, osea agrega los cargos OV, RF, DG si hay items de ese tipo.*/
			_tmpHouse = awbLogic.recalculatePrincipalCosts(_tmpHouse, carrierRates);
			try {
				_tmpHouse.setAwbCostsSales(awbCostSaleLogic.saveAwbCostSale(_tmpHouse.getAwbCostsSales()));
				_tmpHouse.setAwbOtherCostsSales(awbCostSaleLogic.saveAwbCostSale(_tmpHouse.getAwbOtherCostsSales()));
			} catch (Exception e) {
				setErrorMessage("Error trying to update the cost and sales.\n" + e.getMessage());
				e.printStackTrace();
			}
		}
		try {
			awb.setAwbFreightInvoices(new ArrayList<AwbFreightInvoice>());
			awb.setAwbOtherInvoices(new ArrayList<AwbOtherInvoice>());
			awb.setAwbUnCodes(awbLogic.loadUnCodes(new AwbUnCode(awb.getAwbId())));
			awb.setAwbEEIList(awbLogic.loadEEIs(new AwbEEI(awb.getAwbId())));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the AWB Info.\n" + e.getMessage());
			e.printStackTrace();
		}				
		for(Awb _tmpHouseAwb : awb.getAwbHouses()){
				loadAwbHouseInformationToMaster(_tmpHouseAwb);
		}
		processMasterAwbCSAction();
		calculateTotalCostsSales();
		calculateTotalWeights();
		
		setInfoMessage("AWB Houses were succesfuly updated");
	}
	
	public void openAwbMasterAction(ActionEvent event){
		awbFilter.getClient().setCode("");
		awbFilter.setAwbFullNumberExtern("");
		awbFilter.setClientPoNumber("");
		awbFilter.setDateFromFilter(null);
		awbFilter.setDateToFilter(null);
		awbFilter.setDescription("");
		awbFilter.setAwbNumber(awb.getAwbMaster().getAwbNumber());
		awbFilter.setAwbId(awb.getAwbMaster().getAwbId());
		searchAwbAction();
		for(Awb _tmpAwb : awbList){
			if(_tmpAwb.getAwbId() == awb.getAwbMaster().getAwbId()){
				setSessionAttribute("_tmpAwb", _tmpAwb);
				break;
			}
		}
	}
	
	private String loadWhRemarks(List<AwbItem> items){
		String result = "";
		if(items != null){
			for(AwbItem _tmpItem : items){
				String _tmp = _tmpItem.getWhReceipt().getRemarks();
				if(_tmp != null){
					result = result.concat(_tmp + ",");
				}	
			}
		}
		return result;
	}
	
	public void palletizeItemsAction(){
		/* Ciclo para crear los nuevos items paletizados sino existen */
		for(AwbItem _auxAwbItem : awb.getAwbItems() ){
			if(_auxAwbItem.getPalletId() != null && !_auxAwbItem.getPalletId().equals("") ){
				boolean wasCreated = false;
				for(AwbPalletizedItem _palletizedItem : awb.getAwbPalletizedItems()){
					if(_palletizedItem.getPalletId().equals( _auxAwbItem.getPalletId())){
						wasCreated = true;
						break;
					}
				}
				
				if(!wasCreated){
					AwbPalletizedItem newAwbPalletizedItem = new AwbPalletizedItem();
					newAwbPalletizedItem.setAwbId(awb.getAwbId());
					newAwbPalletizedItem.setPalletId(_auxAwbItem.getPalletId());					
					awb.getAwbPalletizedItems().add(newAwbPalletizedItem);
				}
			}
		}
		/* Ciclo para borrar los items paletizados que no tienen ningun item asociado */
		for( int i = 0 ; i <  awb.getAwbPalletizedItems().size(); i++){
			boolean exist = false;
			for(AwbItem _auxAwbItem : awb.getAwbItems() ){
				if(awb.getAwbPalletizedItems().get(i).getPalletId().equals(_auxAwbItem.getPalletId())){
					exist = true;
				}				
			}
			
			if(!exist){
				try {
					awbPalletizedItemLogic.deleteAwbPalletizedItem(awb.getAwbPalletizedItems().get(i));
					awb.getAwbPalletizedItems().remove(i);
					i-=1;
				} catch (Exception e) {
					setErrorMessage("Error trying to retrieve the AWB Info.\n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		
	}

	public void changeRegionNoAction() {
		setRegionSwitch(awb.getRegion().getValueId());
	}
	
	public WhItem getWhItem() {
		return whItem;
	}

	public void setWhItem(WhItem whItem) {
		this.whItem = whItem;
	}

	public IAwbLogic getAwbLogic() {
		return awbLogic;
	}

	public void setAwbLogic(IAwbLogic awbLogic) {
		this.awbLogic = awbLogic;
	}

	public List<Awb> getAwbList() {
		return awbList;
	}

	public void setAwbList(List<Awb> awbList) {
		this.awbList = awbList;
	}

	public Awb getAwb() {
		return awb;
	}

	public void setAwb(Awb awb) {
		this.awb = awb;
	}

	public Awb getAwbFilter() {
		return awbFilter;
	}

	public void setAwbFilter(Awb awbFilter) {
		this.awbFilter = awbFilter;
	}

	public AwbItem getAwbItem() {
		return awbItem;
	}

	public void setAwbItem(AwbItem awbItem) {
		this.awbItem = awbItem;
	}

	public MasterValue getSaidToContain() {
		return saidToContain;
	}

	public void setSaidToContain(MasterValue saidToContain) {
		this.saidToContain = saidToContain;
	}

	public List<Partner> getSuppliers() {
		try {
			setSuppliers(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.SuppliersList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the suppliers List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return suppliers;
	}

	public void setSuppliers(List<Partner> suppliers) {
		this.suppliers = suppliers;
	}

	public List<Partner> getClients() {
		try {
			setClients(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ClientsList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the clients List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return clients;
	}

	public void setClients(List<Partner> clients) {
		this.clients = clients;
	}

	public List<SelectItem> getAwbTypesList() {
		try {
			setAwbTypesList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ShipmentStatusList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the status.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return awbTypesList;
	}

	public void setAwbTypesList(List<SelectItem> awbTypesList) {
		this.awbTypesList = awbTypesList;
	}

	public List<SelectItem> getCarriersList() {
		try {
			List<Carrier> _tmpCarriers = AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CarrierList")
					.getElements("faces");
			carriersList = new ArrayList<SelectItem>();
			for (Carrier _tmpCarr : _tmpCarriers) {
				if (_tmpCarr.getCarrierType().getValueId() == Constants.MASTER_VALUE_SHIPPING_TYPE_AIR || _tmpCarr.getCarrierType().getValueId() == Constants.MASTER_VALUE_SHIPPING_TYPE_OCEAN_AND_AIR) {
					SelectItem _tmpItem = new SelectItem(new Integer(_tmpCarr.getCarrierId()), _tmpCarr.getName(), _tmpCarr.getCarrierCode());
					carriersList.add(_tmpItem);
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the carriers List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return carriersList;
	}

	public void setCarriersList(List<SelectItem> carriersList) {
		this.carriersList = carriersList;
	}
	
	public List<Port> getAllPortsList() {
		List<Port> allPortsList = new ArrayList<Port>();
		try {
			allPortsList = AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.PortsList").getElements(
					"faces");
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the All Ports List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return allPortsList;
	}

	public List<SelectItem> getAirportsList() {
	
			List<Port> _tmpList = getAllPortsList();
					
			if (_tmpList.size() > 0) {
				airportsList = new ArrayList<SelectItem>();
				for (Port _tmpPort : _tmpList) {
					if (_tmpPort.isAir()) {
						SelectItem element = new SelectItem(new Integer(
								_tmpPort.getPortId()), _tmpPort.getName());
						airportsList.add(element);
					}
				}
			}
		
		return airportsList;
	}
	public void setAirportsList(List<SelectItem> airportsList) {
		this.airportsList = airportsList;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
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

	public HtmlExtendedDataTable getAwbTable() {
		return awbTable;
	}

	public void setAwbTable(HtmlExtendedDataTable awbTable) {
		this.awbTable = awbTable;
	}

	public List<Employee> getEmployeesList() {
		try {
			setEmployeesList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.EmployeeList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Employees List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return employeesList;
	}

	public void setEmployeesList(List<Employee> employeesList) {
		this.employeesList = employeesList;
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

	public List<SelectItem> getConsigneesList() {
		loadConsignees();
		return consigneesList;
	}

	public void setConsigneesList(List<SelectItem> consigneesList) {
		this.consigneesList = consigneesList;
	}

	public List<SelectItem> getPickupFromsList() {
		loadPickupsFrom();
		return pickupFromsList;
	}

	public void setPickupFromsList(List<SelectItem> pickupFromsList) {
		this.pickupFromsList = pickupFromsList;
	}

	public List<ShipPickUp> getPickupFromsObjects() {
		return pickupFromsObjects;
	}

	public void setPickupFromsObjects(List<ShipPickUp> pickupFromsObjects) {
		this.pickupFromsObjects = pickupFromsObjects;
	}

	public List<ShipPickUp> getConsigneesObjects() {
		return consigneesObjects;
	}

	public void setConsigneesObjects(List<ShipPickUp> consigneesObjects) {
		this.consigneesObjects = consigneesObjects;
	}

	public IShipPickupLogic getShipPickupLogic() {
		return shipPickupLogic;
	}

	public void setShipPickupLogic(IShipPickupLogic shipPickupLogic) {
		this.shipPickupLogic = shipPickupLogic;
	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<Carrier> carriers) {
		this.carriers = carriers;
	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public HtmlDataTable getAwbItemsTable() {
		return awbItemsTable;
	}

	public void setAwbItemsTable(HtmlDataTable awbItemsTable) {
		this.awbItemsTable = awbItemsTable;
	}

	public List<SelectItem> getUnitTypesList() {
		try {
			setUnitTypesList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.UnitTypesList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Unit Types List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return unitTypesList;
	}

	public void setUnitTypesList(List<SelectItem> unitTypesList) {
		this.unitTypesList = unitTypesList;
	}

	public IAwbCostSaleLogic getAwbCostSaleLogic() {
		return awbCostSaleLogic;
	}

	public void setAwbCostSaleLogic(IAwbCostSaleLogic awbCostSaleLogic) {
		this.awbCostSaleLogic = awbCostSaleLogic;
	}

	public IAwbItemsLogic getAwbItemsLogic() {
		return awbItemsLogic;
	}

	public void setAwbItemsLogic(IAwbItemsLogic awbItemsLogic) {
		this.awbItemsLogic = awbItemsLogic;
	}

	public IAwbUnCodeLogic getAwbUnCodeLogic() {
		return awbUnCodeLogic;
	}

	public void setAwbUnCodeLogic(IAwbUnCodeLogic awbUnCodeLogic) {
		this.awbUnCodeLogic = awbUnCodeLogic;
	}

	public HtmlDataTable getAwbFreightInvoicesTable() {
		return awbFreightInvoicesTable;
	}

	public void setAwbFreightInvoicesTable(HtmlDataTable awbFreightInvoicesTable) {
		this.awbFreightInvoicesTable = awbFreightInvoicesTable;
	}

	public HtmlDataTable getAwbMerchandInvoicesTable() {
		return awbMerchandInvoicesTable;
	}

	public void setAwbMerchandInvoicesTable(
			HtmlDataTable awbMerchandInvoicesTable) {
		this.awbMerchandInvoicesTable = awbMerchandInvoicesTable;
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

	public String getConverterName() {
		return converterName;
	}

	public void setConverterName(String converterName) {
		this.converterName = converterName;
	}

	public HtmlScrollableDataTable getAwbCostsSalesTable() {
		return awbCostsSalesTable;
	}

	public void setAwbCostsSalesTable(HtmlScrollableDataTable awbCostsSalesTable) {
		this.awbCostsSalesTable = awbCostsSalesTable;
	}

	public HtmlDataTable getAwbEeiTable() {
		return awbEeiTable;
	}

	public void setAwbEeiTable(HtmlDataTable awbEeiTable) {
		this.awbEeiTable = awbEeiTable;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
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

	public HtmlDataTable getAwbUnCodesTable() {
		return awbUnCodesTable;
	}

	public void setAwbUnCodesTable(HtmlDataTable awbUnCodesTable) {
		this.awbUnCodesTable = awbUnCodesTable;
	}

	public IAwbEEILogic getAwbEEILogic() {
		return awbEEILogic;
	}

	public void setAwbEEILogic(IAwbEEILogic awbEEILogic) {
		this.awbEEILogic = awbEEILogic;
	}

	public List<SelectItem> getOtherChargesList() {
		List<MasterValue> _tmpOCList;
		try {
			_tmpOCList = masterValueLogic.searchMasterValue(new MasterValue(0,
					Constants.MASTER_CHARGE_TYPE_OTHER));
			otherChargesList = new ArrayList<SelectItem>();
			for (MasterValue _tmpMasterVal : _tmpOCList) {
				otherChargesList.add(new SelectItem(new Integer(_tmpMasterVal
						.getValueId()), _tmpMasterVal.getValue1(),
						_tmpMasterVal.getValue1()));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Other Charges.\n"
					+ e.getMessage());
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

	public String getTableStateCostSales() {
		return tableStateCostSales;
	}

	public void setTableStateCostSales(String tableStateCostSales) {
		this.tableStateCostSales = tableStateCostSales;
	}

	public WhReceipt getWhReceipt() {
		return whReceipt;
	}

	public void setWhReceipt(WhReceipt whReceipt) {
		this.whReceipt = whReceipt;
	}

	public IWhItemLogic getWhItemLogic() {
		return whItemLogic;
	}

	public void setWhItemLogic(IWhItemLogic whItemLogic) {
		this.whItemLogic = whItemLogic;
	}

	public AwbCostSale getAwbCostSale() {
		return awbCostSale;
	}

	public void setAwbCostSale(AwbCostSale awbCostSale) {
		this.awbCostSale = awbCostSale;
	}

	public AwbUnCode getAwbUnCode() {
		return awbUnCode;
	}

	public void setAwbUnCode(AwbUnCode awbUnCode) {
		this.awbUnCode = awbUnCode;
	}

	public AwbEEI getAwbEEI() {
		return awbEEI;
	}

	public void setAwbEEI(AwbEEI awbEEI) {
		this.awbEEI = awbEEI;
	}

	public HtmlExtendedDataTable getAwbWhItemsTable() {
		return awbWhItemsTable;
	}

	public void setAwbWhItemsTable(HtmlExtendedDataTable awbWhItemsTable) {
		this.awbWhItemsTable = awbWhItemsTable;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public IPalletizedItemLogic getPalletizedItemLogic() {
		return palletizedItemLogic;
	}

	public void setPalletizedItemLogic(IPalletizedItemLogic palletizedItemLogic) {
		this.palletizedItemLogic = palletizedItemLogic;
	}

	public IInvoiceLogic getInvoiceLogic() {
		return invoiceLogic;
	}

	public void setInvoiceLogic(IInvoiceLogic invoiceLogic) {
		this.invoiceLogic = invoiceLogic;
	}

	public List<SelectItem> getRateClassList() {
		try {
			setRateClassList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.RateClassList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Rate Class List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return rateClassList;
	}

	public void setRateClassList(List<SelectItem> rateClassList) {
		this.rateClassList = rateClassList;
	}

	public IPackingListItemLogic getPackingListItemLogic() {
		return packingListItemLogic;
	}

	public void setPackingListItemLogic(
			IPackingListItemLogic packingListItemLogic) {
		this.packingListItemLogic = packingListItemLogic;
	}

	public HtmlExtendedDataTable getAwbInvoiceItemsTable() {
		return awbInvoiceItemsTable;
	}

	public void setAwbInvoiceItemsTable(
			HtmlExtendedDataTable awbInvoiceItemsTable) {
		this.awbInvoiceItemsTable = awbInvoiceItemsTable;
	}

	public List<?> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<?> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public HtmlScrollableDataTable getAwbOtherCostsSalesTable() {
		return awbOtherCostsSalesTable;
	}

	public void setAwbOtherCostsSalesTable(
			HtmlScrollableDataTable awbOtherCostsSalesTable) {
		this.awbOtherCostsSalesTable = awbOtherCostsSalesTable;
	}

	public int getRegionSwitch() {
		return regionSwitch;
	}

	public void setRegionSwitch(int regionSwitch) {
		this.regionSwitch = regionSwitch;
	}

	public List<SelectItem> getRegions() {
		try {
			setRegions(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.RegionsList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Regions List.\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return regions;
	}

	public void setRegions(List<SelectItem> regions) {
		this.regions = regions;
	}

	public IAwbFreightInvoiceLogic getAwbFreightInvoiceLogic() {
		return awbFreightInvoiceLogic;
	}

	public void setAwbFreightInvoiceLogic(
			IAwbFreightInvoiceLogic awbFreightInvoiceLogic) {
		this.awbFreightInvoiceLogic = awbFreightInvoiceLogic;
	}

	public IAwbOtherInvoiceLogic getAwbOtherInvoiceLogic() {
		return awbOtherInvoiceLogic;
	}

	public void setAwbOtherInvoiceLogic(
			IAwbOtherInvoiceLogic awbOtherInvoiceLogic) {
		this.awbOtherInvoiceLogic = awbOtherInvoiceLogic;
	}

	public AwbFreightInvoice getFreightInvoice() {
		return freightInvoice;
	}

	public void setFreightInvoice(AwbFreightInvoice freightInvoice) {
		this.freightInvoice = freightInvoice;
	}

	public AwbOtherInvoice getMerchandiseInvoice() {
		return merchandiseInvoice;
	}

	public void setMerchandiseInvoice(AwbOtherInvoice merchandiseInvoice) {
		this.merchandiseInvoice = merchandiseInvoice;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getSuperCSForm() {
		return superCSForm;
	}

	public void setSuperCSForm(String superCSForm) {
		this.superCSForm = superCSForm;
	}

	public HtmlDataTable getAwbInlandCSItemsTable() {
		return awbInlandCSItemsTable;
	}

	public void setAwbInlandCSItemsTable(HtmlDataTable awbInlandCSItemsTable) {
		this.awbInlandCSItemsTable = awbInlandCSItemsTable;
	}

	public IAwbInlandCsLogic getAwbInlandCSLogic() {
		return awbInlandCSLogic;
	}

	public void setAwbInlandCSLogic(IAwbInlandCsLogic awbInlandCSLogic) {
		this.awbInlandCSLogic = awbInlandCSLogic;
	}

	public List<SelectItem> getTruckCompanies() {
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

	public void setTruckCompanies(List<SelectItem> truckCompanies) {
		this.truckCompanies = truckCompanies;
	}

	public IPartnerLogic getPartnerLogic() {
		return partnerLogic;
	}

	public void setPartnerLogic(IPartnerLogic partnerLogic) {
		this.partnerLogic = partnerLogic;
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
	
	public boolean getHaveAwbHouses() {
		if ( awb.getAwbHouses() != null) {
			if (awb.getAwbHouses().size() >0) return true;
		}
		return false;
	}

	public HtmlDataTable getAwbHousesTable() {
		return awbHousesTable;
	}

	public void setAwbHousesTable(HtmlDataTable awbHousesTable) {
		this.awbHousesTable = awbHousesTable;
		selectAwbAction();
	}

	public HtmlExtendedDataTable getAwbHousesAvailableTable() {
		return awbHousesAvailableTable;
	}

	public void setAwbHousesAvailableTable(HtmlExtendedDataTable awbHousesAvailableTable) {
		this.awbHousesAvailableTable = awbHousesAvailableTable;
	}

	public List<Awb> getAwbHousesAvailableList() {
		return awbHousesAvailableList;
	}

	public void setAwbHousesAvailableList(List<Awb> awbHousesAvailableList) {
		this.awbHousesAvailableList = awbHousesAvailableList;
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
	
	public ICarrierAwbNumberLogic getCarrierAwbNumberLogic() {
		return carrierAwbNumberLogic;
	}

	public void setCarrierAwbNumberLogic(
			ICarrierAwbNumberLogic carrierAwbNumberLogic) {
		this.carrierAwbNumberLogic = carrierAwbNumberLogic;
	}

	public IAwbPalletizedItemLogic getAwbPalletizedItemLogic() {
		return awbPalletizedItemLogic;
	}

	public void setAwbPalletizedItemLogic(IAwbPalletizedItemLogic awbPalletizedItemLogic) {
		this.awbPalletizedItemLogic = awbPalletizedItemLogic;
	}

	public HtmlExtendedDataTable getAwbPalletizedItemsTable() {
		return awbPalletizedItemsTable;
	}

	public void setAwbPalletizedItemsTable(HtmlExtendedDataTable awbPalletizedItemsTable) {
		this.awbPalletizedItemsTable = awbPalletizedItemsTable;
	}

	public List<SelectItem> getWhLocations() {			
		List <WhLocation> allWhLocations = null;
		try {
			allWhLocations= (AdministradorListas.obtenerLista( "com.lotrading.softlot.util.lists.WhLocationList") .getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Ports.\n" + e.getMessage());
		}
		List<SelectItem> auxWhLocations = new ArrayList<SelectItem>();
		for (WhLocation tmpWhLocation : allWhLocations) {			
				auxWhLocations.add(new SelectItem(tmpWhLocation.getWhLocationId(), tmpWhLocation.getWhLocationName()));
		}
		whLocations = auxWhLocations;
		return whLocations;	
	}

	public void setWhLocations(List<SelectItem> whLocations) {
		this.whLocations = whLocations;
	}

	public IWhLocationLogic getWhLocationLogic() {
		return whLocationLogic;
	}

	public void setWhLocationLogic(IWhLocationLogic whLocationLogic) {
		this.whLocationLogic = whLocationLogic;
	}
}
