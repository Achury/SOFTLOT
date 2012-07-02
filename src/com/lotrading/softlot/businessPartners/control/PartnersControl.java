package com.lotrading.softlot.businessPartners.control;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.richfaces.component.html.HtmlDataList;
import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.component.html.HtmlExtendedDataTable;

import co.com.landsoft.devbase.util.listas.AdministradorListas;
import co.com.landsoft.reporte.util.ParametrosReporte;

import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.logic.ICallHistoryLogic;
import com.lotrading.softlot.businessPartners.logic.IClientRateLogic;
import com.lotrading.softlot.businessPartners.logic.ICourierAccountLogic;
import com.lotrading.softlot.businessPartners.logic.IPartnerContactLogic;
import com.lotrading.softlot.businessPartners.logic.IPartnerDepartmentInfoLogic;
import com.lotrading.softlot.businessPartners.logic.IPartnerLogic;
import com.lotrading.softlot.businessPartners.logic.IPhoneLogic;
import com.lotrading.softlot.businessPartners.logic.IShipPickupLogic;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.ICityLogic;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;
import com.lotrading.softlot.setup.logic.IPortLogic;
import com.lotrading.softlot.setup.logic.IcarrierLogic;
import com.lotrading.softlot.util.base.AlphabeticComparator;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.control.BaseControl;

/**
 * @author MARLON PINEDA - JUAN URIBE
 * @version 1.0
 * @created 13-May-2011
 */
public class PartnersControl extends BaseControl {

	private IPartnerLogic partnerLogic;
	private IPartnerDepartmentInfoLogic partnerDepartmentInfoLogic;
	private IPartnerContactLogic partnerContactLogic;
	private IShipPickupLogic shipPickupLogic;
	private ICourierAccountLogic courierAccountLogic;
	private ICityLogic cityLogic;
	private ICallHistoryLogic callHistoryLogic;
	private IPortLogic portLogic;
	private IPhoneLogic phoneLogic;
	private IResourceLogic resourceLogic;
	private IEmployeeLogic employeeLogic;
	private IClientRateLogic clientRateLogic;
	private IMasterValueLogic masterValueLogic;
	private IcarrierLogic carrierLogic;

	private List countries;
	private List provinces;
	private List truckCompanies;
	private List couriers;
	private List paymentTermsList;
	private List languages;
	private List status;
	private List employees;
	private List<City> cities;
	private List phoneTypes;
	private List suppliers;
	private City city;
	private List<SelectItem> carriers;


	private CallHistory callHistory;
	private List callsHistoryFiltered;  /** este objeto se utiliza para que cuando se quiera filtrar los callHistory no se tenga que preguntar
										    a la BD, sino que recorre los callHistoty que estan en el objeto cliente y los que cumplan con el 
										    filtro los pone en esta lista y esta es la que se muestra en el ExtendedDatatable. **/

	private List ports;
	private List airPorts;
	private List<Port> allPorts;
	private List<SelectItem> departments;

	private Partner partner;
	private List<Partner> partners;

	private ShipPickUp shipPickup;
	private List shipPickupsFiltered;

	private CourierAccount courierAccount;

	private PartnerContact partnerContact;
	private List<SelectItem> partnerContactsList;


	private Partner quickPartner;
	private Phone phone;

	private Integer pagina;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable table;

	private Integer paginaContactsRM;
	private String tableStateContactsRM;
	private HtmlExtendedDataTable tableContactsRM;
	private Integer paginaAccountRM;
	private String tableStateAccountRM;
	private HtmlExtendedDataTable tableAccountRM;

	private Integer paginaContactsIP;
	private String tableStateContactsIP;
	private HtmlExtendedDataTable tableContactsIP;
	private Integer paginaAccountIP;
	private String tableStateAccountIP;
	private HtmlExtendedDataTable tableAccountIP;

	private Integer paginaContactsFF;
	private String tableStateContactsFF;
	private HtmlExtendedDataTable tableContactsFF;
	private Integer paginaAccountFF;
	private String tableStateAccountFF;
	private HtmlExtendedDataTable tableAccountFF;

	private String tableStateShipPickup;
	private HtmlExtendedDataTable tableShipPickup;

	private HtmlExtendedDataTable tableCallHistory;

	private HtmlExtendedDataTable tableContactPhones;

	private String selectedtab;
	private String shipPickLabel;
	/***
	 * Utilizado para poner ShipTo si se esta en client.jsp o PickUpFrom si se
	 * esta en Supplier.jsp.
	 ***/

	private boolean isJspClient;
	/***
	 * Utilizado para saber en que jsp esta ubicado actualmente (client.jsp o
	 * supplier.jsp).
	 ***/

	private String styleTableHeader;

	private String suggestion = "";

	private String filtroCallHistory;
	/*** Utilizado para filtrar los registros de llamadas o callHistory. ***/

	private String filtroShipPickup;
	/*** Utilizado para filtrar los ShipTo o PickupFrom. ***/
	private boolean changeGenInfoActive;
	/*** Utilizado para saber si esta activado o no el boton change General Info. ***/




	private List<Resource> actionsList;
	/***
	 * Utilizado para guardar la lista de acciones que tiene el rol del usuario
	 * en el recurso actual.
	 ***/


	/** Actions **/
	private boolean linkNewPartnerVisible;
	/***
	 * Utilizado para saber si se muestra o no el link de new cliente o

	 * proveedor, es decir, si el role tiene permisos para crear un cliente o
	 * proveedor.
	 ***/
	
	private boolean savePartnerVisible;
	/***
	 * Utilizado para saber si se muestra o no el boton save, es decir, si el
	 * role tiene permisos para guardar cambios al cliente.
	 ***/
	
	private boolean linkNewCityVisible;
	/***
	 * Utilizado para saber si se muestra o no el link de new city, es decir, si
	 * el role tiene permisos para crear una ciudad.
	 ***/
	
	private boolean linkDeleteContactVisible;
	/***
	 * Utilizado para saber si se muestra o no la X que elimina contactos,
	 * shipPickups, y courier acounts ,es decir, si el role tiene permisos para
	 * eliminar.

	 ***/
	
	private boolean saveDeptInfoVisible;
	/***
	 * Utilizado para saber si se muestra el boton guardar de la informacion de cada departamento
	 * como el margen, el markup, etc.
	 ***/
	
	private boolean readOnlyPaymentTerm;
	/***
	 * para saber si se ve solo como readonly la lista de truckCompanies del cliente.
	 ***/
	
	private boolean searchAndInspConsentVisible;
	/**
	 * Para saber si se muestra o no el chechbox Search & Insp. Consent que esta en la pestaña de logistica. (Solo es visible para JM, HA, EB, LEO)
	 * */
	
	private String selectedTabRates;
	private String rateTypeAirTabName = "tabAir";
	private String rateTypeOceanLCLTabName = "tabOceanLCL";
	private String rateTypeOcean20TabName = "tabOcean20";
	private String rateTypeOcean40TabName = "tabOcean40";
	private HtmlDataList dataListOceanLCL;
	private HtmlDataList dataListOcean20;
	private HtmlDataList dataListOcean40;
	private HtmlDataList dataListAir;
	private ClientRate clientRateOtherCharge;
	private ClientRate clientRate;
	private ClientRatesPort clientRatePort;
	private List<SelectItem> otherChargesList;
	
	private HtmlDataTable airRatesDataTable;
	private HtmlDataTable lclRatesDataTable;
	private HtmlDataTable ocean20RatesDataTable;
	private HtmlDataTable ocean40RatesDataTable;
	
	private List<ClientRatesPort> airRatesList;
	private List<ClientRatesPort> ocean20RatesList;
	private List<ClientRatesPort> ocean40RatesList;
	private List<ClientRatesPort> oceanLCLRatesList;
	
	private ClientRatesPort clientAirRatePortFilter;
	private ClientRatesPort clientOceanLCLRatePortFilter;
	private ClientRatesPort clientOcean20RatePortFilter;
	private ClientRatesPort clientOcean40RatePortFilter;
	
	private boolean showNotifRegionRestricted;

	public PartnersControl() {
		changeGenInfoActive = false;
		linkNewPartnerVisible = false;
		savePartnerVisible = false;
		linkNewCityVisible = false;
		readOnlyPaymentTerm = true;
		actionsList = null;
		partner = new Partner();
		partners = new ArrayList<Partner>();
		quickPartner = new Partner();
		callHistory = new CallHistory();
		shipPickup = new ShipPickUp();
		searchAndInspConsentVisible = false;
	}

	public String changeCurrentTabRM() {
		selectedtab = "rm";
		return null;
	}

	public String changeCurrentTabIP() {
		selectedtab = "ip";
		return null;
	}

	public String changeCurrentTabFF() {
		selectedtab = "lo";
		return null;
	}

	public String changeCurrentTabCall() {
		selectedtab = "call";
		viewCallHistory();
		return null;
	}

	public String changeCurrentTabShipPickup() {
		selectedtab = "shipto";
		viewShippickup();
		return null;
	}

	public void clearSearchAction() {
		partner = new Partner();
	}

	public void searchPartnerAction() {
		try {
			partners = partnerLogic.searchPartner(partner);
			if (partners == null || partners.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public void searchSupplierAction() {
		partner.setSupplier(true);
		setJspClient(false);
		setStyleTableHeader("styleExtendDataTableHeaderSupplier");
		searchPartnerAction();
	}

	public void searchClientAction() {
		partner.setClient(true);
		setJspClient(true);
		setStyleTableHeader("styleExtendDataTableHeaderClient");
		searchPartnerAction();
	}

	public void newClientAction() {
		partner = new Partner();
		setJspClient(true);
		partner.setClient(true);
		partner.setValidCode(false);
		setChangeGenInfoActive(true);
		try {
			partner.getDepartmentInfoRM().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
			partner.getDepartmentInfoIP().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
			partner.getDepartmentInfoLO().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
			Employee _tmpEmpl = this.getEmployeeLogged();
			partner.setEmployeeCreator(_tmpEmpl);
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
		isLinkNewPartnerVisible();
	}

	public void newSupplierAction() {
		partner = new Partner();
		setJspClient(false);
		partner.setSupplier(true);
		try {
			partner.getDepartmentInfoRM().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
			partner.getDepartmentInfoIP().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
			partner.getDepartmentInfoLO().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
			Employee _tmpEmpl = this.getEmployeeLogged();
			partner.setEmployeeCreator(_tmpEmpl);
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
		partner.setValidCode(false);
	}

	public void savePartnerAction() {
		try {
			int _tmpRet = partnerLogic.savePartner(partner);
			if (_tmpRet > 0) {
				setInfoMessage("Partner was successfully saved");
				partner.setPartnerId(_tmpRet);
				/* Aca se pondria el depto por defecto de quien esta logueado */
				Employee _tmpEmpl = this.getEmployeeLogged();
				partner.setEmployeeUpdate(_tmpEmpl);
				setChangeGenInfoActive(false);
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	//TODO: Reporte historial de llamdas 
	public void repotCallHistoyAction() {
		Connection conn = null;
		try {
			if(null != callsHistoryFiltered && !callsHistoryFiltered.isEmpty()){
				String idTask = "";
				Iterator<CallHistory> it = callsHistoryFiltered.iterator();
				while(it.hasNext()) {
					CallHistory _tmpCall = it.next();
					if(_tmpCall.isPrint()) {
						idTask+=_tmpCall.getCallId()+",";
					}
				}
				if(!idTask.equals("")){
					idTask = idTask.substring(0,idTask.length()-1);
					
					FacesContext ctx = FacesContext.getCurrentInstance();
					ExternalContext ectx = ctx.getExternalContext();
					ServletContext context = (ServletContext)ectx.getContext();
					
					//Ruta y nombre del jasper del cual se desea generar el reporte 
					String _tmpFile = context.getRealPath("WEB-INF/reports/businessPartners/callHistory.jasper");
					
					Date fecha = new Date();
					String tmpFecha = "EEEE, MMMM dd, yyyy ";
					SimpleDateFormat formato = new SimpleDateFormat(tmpFecha);
					
					Map parametros = new HashMap();
					parametros.put("titulo", "L.O. Trading Corp.");
					parametros.put("task", "Task List");
					parametros.put("idTask", idTask);
					parametros.put("fecha", formato.format(new Date()));
					
					
					
					//Se llena el objeto con la informacion que se desea imprimir
					ParametrosReporte parametrosReporte = new ParametrosReporte();
					
					//Se obtiene la conexion con la base de datos
					Context initCtx = new InitialContext();
					Context envCtx = (Context)initCtx.lookup("java:comp/env");
					DataSource ds = (DataSource)envCtx.lookup("jdbc/tradingDS");
					conn = ds.getConnection();
					
					//Conexion
					parametrosReporte.setConn(conn);
					//Nombre del archivo que se desea obtener
					parametrosReporte.setNombreArchivo("Archivo");
					//Informacion con la ruta del archivo y nombre del jasper
					parametrosReporte.setNombreJasper(_tmpFile);
					//Parametros enviados al jasper
					parametrosReporte.setParametros(parametros);
					//Tipo de archivo que se desea generar
					parametrosReporte.setTipoArchivo("PDF");
					//Lista de objetos para imprimir los detalles 
					parametrosReporte.setListaObjetos(null);
					
					//Se carga el objeto en sessión 
					setSessionAttribute("parametrosReporte", parametrosReporte);
					//Se redirecciona al recurso creado
					ectx.redirect("/SOFTLOT/reporte.rep");
					this.setInfoMessage("El reporte se generó correctamente.");
				} else {
					this.setInfoMessage("No existen reportes por generar.");
				}
			} else {
				this.setInfoMessage("No existen reportes por generar.");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectPartnerActionAux(ActionEvent event){
		partner = (Partner) table.getRowData();
		setSessionAttribute("partnerClient", partner);
		partner = new Partner();
	}
	
	/* Este metodo se llama en el utimo de los set de los logic que estan en el faces-config.xml, en 
	 * este caso se llama en el set de carrierLogic */
	public void selectPartnerAction(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status");
		if(null != id && !id.equals("")) {
			if(id.equals("nuevo")) {
				newClientAction();
			} else if(id.equals("existe")) {
				partner = (Partner) getSessionAttribute("partnerClient");
				id = null;
				removeSessionAttribute("partnerClient");
				try {
					
					loadRMInfoAction();
					loadIPInfoAction();
					loadFFInfoAction();
					
					/* aca se pondria el depto por defecto de quien esta logueado */
					Employee _tmpEmployee = this.getEmployeeLogged();
					partner.setDepartmentLotId(_tmpEmployee.getDepartmentLotId().getValueId());
					if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_FF) {
						changeCurrentTabFF();
					} else if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
						changeCurrentTabIP();
					} else {
						changeCurrentTabRM();
					}
					partner.getEmployeeUpdate().setEmployeeId(this.getEmployeeLoggedId());
					if (!isJspClient) {
						showNotifRegionRestricted = showNotifRegionRestricted();
					}
				} catch (Exception e) {
					setErrorMessage("Error trying to select the Partner.\n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/*public String selectPartnerAction() {
		try {
			partner = (Partner) table.getRowData();
			loadRMInfoAction();
			loadIPInfoAction();
			loadFFInfoAction();

			// aca se pondria el depto por defecto de quien se logueo
			Employee _tmpEmployee = this.getEmployeeLogged();
			partner.setDepartmentLotId(_tmpEmployee.getDepartmentLotId()
					.getValueId());
			if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_FF) {
				changeCurrentTabFF();
			} else if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
				changeCurrentTabIP();
			} else {
				changeCurrentTabRM();
			}
			partner.getEmployeeUpdate().setEmployeeId(
					this.getEmployeeLoggedId());
		} catch (Exception e) {
			setErrorMessage("Error trying to select the Partner.\n"
					+ e.getMessage());
		}
		if (isJspClient) {
			setValidCode(true);
			actionsList = null;
			return "client";
		}
		if (!isJspClient) {
			setValidCode(true);
			actionsList = null;
			showNotifRegionRestricted = showNotifRegionRestricted();
			return "supplier";
		}
		return "error"; // ir a error
	}*/

	public List<Partner> autoCompleteQuickSupplier(Object suggest) {
		String pref = (String) suggest;
		ArrayList<Partner> result = new ArrayList<Partner>();
		try {
			for (Partner tmp_partner : getSuppliers()) {
				if ((tmp_partner != null
						&& tmp_partner.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0 || "".equals(pref))) {
					result.add(tmp_partner);
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return result;
	}

	public void showQuickClientAction(ActionEvent event){
		partner.setClient(true);
		Partner _tmpPartner =  new Partner();
		try {
			_tmpPartner = partnerLogic.searchByCode(partner);
		} catch (Exception e) {
			setErrorMessage("Error trying to get the info of Client.\n" + e.getMessage());
			e.printStackTrace();
		}
		if(_tmpPartner.getPartnerId() > 0){
			_tmpPartner.setValidCode(true);
			setSessionAttribute("ClientId", _tmpPartner.getPartnerId());
			setSessionAttribute("partnerClient", _tmpPartner);
		}
		partner = new Partner();
		
	}
	
	/*public void showQuickClientAction() {
		try {
			Partner _tmp = new Partner();
			quickPartner.setClient(true);
			_tmp = partnerLogic.searchByCode(quickPartner);
			if (_tmp.getPartnerId() > 0) {
				partner = _tmp;
				setValidCode(true);
				loadRMInfoAction();
				loadIPInfoAction();
				loadFFInfoAction();
				// aca se pondria el depto por defecto de quien se logueo
				Employee _tmpEmployee = new Employee(this.getEmployeeLoggedId());
				_tmpEmployee = employeeLogic.loadEmployeeById(_tmpEmployee);
				partner.setDepartmentLotId(_tmpEmployee.getDepartmentLotId().getValueId());
				if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_FF) {
					changeCurrentTabFF();
				} else if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
					changeCurrentTabIP();
				} else {
					changeCurrentTabRM();
				}
				actionsList = null;
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to open the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
	}*/

	public void showQuickSupplierAction() {
		try {
			quickPartner = new Partner();
			loadRMInfoAction();
			loadIPInfoAction();
			loadFFInfoAction();

			// aca se pondria el depto por defecto de quien se logueo
			Employee _tmpEmployee = new Employee(this.getEmployeeLoggedId());
			_tmpEmployee = employeeLogic.loadEmployeeById(_tmpEmployee);
			partner.setDepartmentLotId(_tmpEmployee.getDepartmentLotId().getValueId());
			if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_FF) {
				changeCurrentTabFF();
			} else if (partner.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
				changeCurrentTabIP();
			} else {
				changeCurrentTabRM();
			}

		} catch (Exception e) {
			setErrorMessage("Error trying to open the Partner.\n"+ e.getMessage());
			e.printStackTrace();
			
		}
	}

	public String viewRMInfoAction() {
		try {
			partner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
			if (partner.getDepartmentInfoRM().getPartnerDepartmentInfoId() <= 0) {
				partner.setDepartmentInfoRM(partnerLogic.loadDepartmentInfo(partner));
			}
			if (partner.getDepartmentInfoRM().getPartnerContacts() == null) {
				partner.getDepartmentInfoRM().setPartnerContacts(partnerLogic.loadContacts(partner));
			}
			if (partner.getDepartmentInfoRM().getCourierAccounts() == null) {
				partner.getDepartmentInfoRM().setCourierAccounts(partnerLogic.loadCourierAccount(partner));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying retrieve the RM department info.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void loadRMInfoAction() {
		try {
			partner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);

			partner.setDepartmentInfoRM(partnerLogic.loadDepartmentInfo(partner));
			partner.getDepartmentInfoRM().setPartnerContacts(partnerLogic.loadContacts(partner));
			partner.getDepartmentInfoRM().setCourierAccounts(partnerLogic.loadCourierAccount(partner));

		} catch (Exception e) {
			setErrorMessage("Error trying retrieve the RM department info.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public String viewIPInfoAction() {
		try {
			partner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
			if (partner.getDepartmentInfoIP().getPartnerDepartmentInfoId() <= 0) {
				partner.setDepartmentInfoIP(partnerLogic.loadDepartmentInfo(partner));
			}
			if (partner.getDepartmentInfoIP().getPartnerContacts() == null) {
				partner.getDepartmentInfoIP().setPartnerContacts(partnerLogic.loadContacts(partner));
			}
			if (partner.getDepartmentInfoIP().getCourierAccounts() == null) {
				partner.getDepartmentInfoIP().setCourierAccounts(partnerLogic.loadCourierAccount(partner));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying retrieve the IP department info.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void loadIPInfoAction() {
		try {
			partner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);

			partner.setDepartmentInfoIP(partnerLogic.loadDepartmentInfo(partner));
			partner.getDepartmentInfoIP().setPartnerContacts(partnerLogic.loadContacts(partner));
			partner.getDepartmentInfoIP().setCourierAccounts(partnerLogic.loadCourierAccount(partner));

		} catch (Exception e) {
			setErrorMessage("Error trying retrieve the IP department info.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public String viewFFInfoAction() {
		try {
			partner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
			if (partner.getDepartmentInfoLO().getPartnerDepartmentInfoId() <= 0) {
				partner.setDepartmentInfoLO(partnerLogic.loadDepartmentInfo(partner));
			}
			if (partner.getDepartmentInfoLO().getPartnerContacts() == null) {
				partner.getDepartmentInfoLO().setPartnerContacts(partnerLogic.loadContacts(partner));
			}
			if (partner.getDepartmentInfoLO().getCourierAccounts() == null) {
				partner.getDepartmentInfoLO().setCourierAccounts(partnerLogic.loadCourierAccount(partner));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the FF department info.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void loadFFInfoAction() {
		try {
			partner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);

			partner.setDepartmentInfoLO(partnerLogic.loadDepartmentInfo(partner));
			partner.getDepartmentInfoLO().setPartnerContacts(partnerLogic.loadContacts(partner));
			partner.getDepartmentInfoLO().setCourierAccounts(partnerLogic.loadCourierAccount(partner));

		} catch (Exception e) {
			setErrorMessage("Error trying retrieve the FF department info.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void viewCallHistory() {
		try {
			if (partner.getCallsHistory() == null) {
				partner.setCallsHistory(new ArrayList<CallHistory>());
			}
			callHistory = new CallHistory();
			callHistory.setPartner(partner);
			partner.setCallsHistory(callHistoryLogic.filterByNotes(callHistory));
			callsHistoryFiltered = partner.getCallsHistory();
			
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the calls History.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void viewShippickup() {
		try {
			if (partner.getShipPickups() == null) {
				partner.setShipPickups(new ArrayList<ShipPickUp>());
			}
			shipPickup.setPartnerId(partner.getPartnerId());
			partner.setShipPickups(shipPickupLogic.filterByShipPickup(shipPickup));
			shipPickupsFiltered = partner.getShipPickups();
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the shipPickups.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void saveDepartmentInfoAction() {
		boolean _tmpRet = false;
		try {

			if (getSelectedtab().equalsIgnoreCase("rm")) {
				
				partner.getDepartmentInfoRM().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
				_tmpRet = partnerDepartmentInfoLogic.saveDepartmentInfo(partner.getDepartmentInfoRM());

			} else if (getSelectedtab().equalsIgnoreCase("ip")) {

				partner.getDepartmentInfoIP().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
				_tmpRet = partnerDepartmentInfoLogic.saveDepartmentInfo(partner.getDepartmentInfoIP());

			} else if (getSelectedtab().equalsIgnoreCase("lo")) {

				partner.getDepartmentInfoLO().setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
				_tmpRet = partnerDepartmentInfoLogic.saveDepartmentInfo(partner.getDepartmentInfoLO());
			}

			if (_tmpRet) {
				setInfoMessage("Partner Department Information was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner Department Information.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectToDisableContact() {
		try {
			courierAccount = null;
			shipPickup = null;
			if (getSelectedtab().equalsIgnoreCase("rm")) {
				partnerContact = (PartnerContact) tableContactsRM.getRowData();
			} else if (getSelectedtab().equalsIgnoreCase("ip")) {
				partnerContact = (PartnerContact) tableContactsIP.getRowData();
			} else if (getSelectedtab().equalsIgnoreCase("lo")) {
				partnerContact = (PartnerContact) tableContactsFF.getRowData();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectToDeleteCourier() {
		try {
			shipPickup = null;
			partnerContact = null;

			if (getSelectedtab().equalsIgnoreCase("rm")) {
				courierAccount = (CourierAccount) tableAccountRM.getRowData();
			} else if (getSelectedtab().equalsIgnoreCase("ip")) {
				courierAccount = (CourierAccount) tableAccountIP.getRowData();
			} else if (getSelectedtab().equalsIgnoreCase("lo")) {
				courierAccount = (CourierAccount) tableAccountFF.getRowData();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String disableShipPickupAction() {
		try {
			shipPickup.setDisabledDate(new Date());
			shipPickupLogic.saveShipPickup(shipPickup);
			partner.getShipPickups().remove(shipPickup);
		} catch (Exception e) {
			setErrorMessage("Error trying to disable this shipTo or PickupFrom. \n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String disableItemFromTableAction() {
		try {
			if (partnerContact != null) {
				partnerContact.setDisableDate(new Date());
				partnerContactLogic.savePartnerContact(partnerContact);

				if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_EVERYONE) {
					refreshContactListsFromDB();
				} else if (getSelectedtab().equalsIgnoreCase("rm")) {
					partner.getDepartmentInfoRM().getPartnerContacts().remove(partnerContact);
				} else if (getSelectedtab().equalsIgnoreCase("ip")) {
					partner.getDepartmentInfoIP().getPartnerContacts().remove(partnerContact);
				} else if (getSelectedtab().equalsIgnoreCase("lo")) {
					partner.getDepartmentInfoLO().getPartnerContacts().remove(partnerContact);
				}

			}
			if (courierAccount != null) {
				courierAccountLogic.deleteCourierAccount(courierAccount);
				if (getSelectedtab().equalsIgnoreCase("rm")) {
					partner.getDepartmentInfoRM().getCourierAccounts().remove(courierAccount);
				} else if (getSelectedtab().equalsIgnoreCase("ip")) {
					partner.getDepartmentInfoIP().getCourierAccounts().remove(courierAccount);
				} else if (getSelectedtab().equalsIgnoreCase("lo")) {
					partner.getDepartmentInfoLO().getCourierAccounts().remove(courierAccount);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String boundList() {
		try {
			partner.getAddress().setCity(cityLogic.loadCityById(partner.getAddress().getCity()));
		} catch (Exception e) {
			setErrorMessage("Error trying to bind city, state and country.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String boundListContact() {
		try {
			partnerContact.getAddress().setCity(cityLogic.loadCityById(partnerContact.getAddress().getCity()));
		} catch (Exception e) {
			setErrorMessage("Error trying to bind city, state and country.\n" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void boundListShipPickup() {
		try {
			shipPickup.getAddress().setCity(cityLogic.loadCityById(shipPickup.getAddress().getCity()));
		} catch (Exception e) {
			setErrorMessage("Error trying to bind city, state and country.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public int getSize() {
		if (partners == null) {
			return 0;
		} else {
			return partners.size();
		}
	}

	public void clearCity() {
		partner.getAddress().setCity(new City());
	}

	public void newShipPickupAction() {
		Address tmpAddress = new Address();
		tmpAddress = (Address) partner.getAddress().clone();
		tmpAddress.setAddress(partner.getName()+"\n"+tmpAddress.getAddress());
		tmpAddress.setAddressId(0);
		shipPickup = new ShipPickUp();
		shipPickup.setAddress(tmpAddress);
		shipPickup.setPartnerId(partner.getPartnerId());

	}

	public void selectShipPickupAction() {
		shipPickup = (ShipPickUp) tableShipPickup.getRowData();
	}

	public void selectCallHistoryAction() {
		callHistory = (CallHistory) tableCallHistory.getRowData();
	}

	public void newCourierAccountAction() {
		courierAccount = new CourierAccount();

		if (getSelectedtab().equals("rm")) {
			courierAccount.setPartnerDeptInfo(partner.getDepartmentInfoRM().getPartnerDepartmentInfoId());
		} else if (getSelectedtab().equals("ip")) {
			courierAccount.setPartnerDeptInfo(partner.getDepartmentInfoIP().getPartnerDepartmentInfoId());
		} else if (getSelectedtab().equals("lo")) {
			courierAccount.setPartnerDeptInfo(partner.getDepartmentInfoLO().getPartnerDepartmentInfoId());
		}
	}

	public void selectCourierAccountAction() {
		if (getSelectedtab().equals("rm")) {
			courierAccount = (CourierAccount) tableAccountRM.getRowData();
		} else if (getSelectedtab().equals("ip")) {
			courierAccount = (CourierAccount) tableAccountIP.getRowData();
		} else if (getSelectedtab().equals("lo")) {
			courierAccount = (CourierAccount) tableAccountFF.getRowData();
		}
	}

	public void newContactAction() {

		Address tmpAddress = new Address();
		tmpAddress = (Address) partner.getAddress().clone();
		tmpAddress.setAddressId(0);
		partnerContact = new PartnerContact();
		partnerContact.setAddress(tmpAddress);
		partnerContact.setPartnerId(partner.getPartnerId());

		if (getSelectedtab().equals("rm")) {
			partnerContact.setDepartmentLotId(partner.getDepartmentInfoRM().getDepartmentLotId());
		} else if (getSelectedtab().equals("ip")) {
			partnerContact.setDepartmentLotId(partner.getDepartmentInfoIP().getDepartmentLotId());
		} else if (getSelectedtab().equals("lo")) {
			partnerContact.setDepartmentLotId(partner.getDepartmentInfoLO().getDepartmentLotId());
		}

	}

	public void selectContactAction() {
		try {
			if (getSelectedtab().equals("rm")) {
				partnerContact = (PartnerContact) tableContactsRM.getRowData();
				partnerContact.setPhones(partnerContactLogic.loadContactPhones(partnerContact));

			} else if (getSelectedtab().equals("ip")) {
				partnerContact = (PartnerContact) tableContactsIP.getRowData();
				partnerContact.setPhones(partnerContactLogic.loadContactPhones(partnerContact));
				
			} else if (getSelectedtab().equals("lo")) {
				partnerContact = (PartnerContact) tableContactsFF.getRowData();
				partnerContact.setPhones(partnerContactLogic.loadContactPhones(partnerContact));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void savePartnerContactAction() {
		try {

			System.out.println(" entra a guardar contact partnercontaccontrol Registro");
			int _tmpRet = partnerContactLogic.savePartnerContact(partnerContact);

			if (_tmpRet > 0) {
				savePartnerContactPhonesAction();
				partnerContact.setContactId(_tmpRet);
				if (partner.getDepartmentInfoIP().getPartnerContacts() == null) {
					partner.getDepartmentInfoIP().setPartnerContacts(new ArrayList<PartnerContact>());
				}
				if (partner.getDepartmentInfoRM().getPartnerContacts() == null) {
					partner.getDepartmentInfoRM().setPartnerContacts(new ArrayList<PartnerContact>());
				}
				if (partner.getDepartmentInfoLO().getPartnerContacts() == null) {
					partner.getDepartmentInfoLO().setPartnerContacts(new ArrayList<PartnerContact>());
				}
				if (!partner.getDepartmentInfoRM().getPartnerContacts().isEmpty()) {
					partner.getDepartmentInfoRM().getPartnerContacts().remove(partnerContact);
				}
				if (!partner.getDepartmentInfoIP().getPartnerContacts().isEmpty()) {
					partner.getDepartmentInfoIP().getPartnerContacts().remove(partnerContact);
				}
				if (!partner.getDepartmentInfoLO().getPartnerContacts().isEmpty()) {
					partner.getDepartmentInfoLO().getPartnerContacts().remove(partnerContact);
				}
				if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_RM) {
					partner.getDepartmentInfoRM().getPartnerContacts().add(partnerContact);

				} else if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
					partner.getDepartmentInfoIP().getPartnerContacts().add(partnerContact);

				} else if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_FF) {
					partner.getDepartmentInfoLO().getPartnerContacts().add(partnerContact);
				} else { // refresque todas las tablas
					partner.getDepartmentInfoRM().getPartnerContacts().add(partnerContact);
					partner.getDepartmentInfoIP().getPartnerContacts().add(partnerContact);
					partner.getDepartmentInfoLO().getPartnerContacts().add(partnerContact);
				}
				setInfoMessage("Contact was successfully saved");
			} else {
				setWarningMessage("Contact was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void saveShipPickupAction() {
		try {
			boolean isnew = false;
			if (shipPickup.getShipPickUpId() <= 0) {
				isnew = true;
			}
			int _tmpRet = shipPickupLogic.saveShipPickup(shipPickup);

			if (_tmpRet > 0) {
				shipPickup.setShipPickUpId(_tmpRet);
				if (!isnew) {
					partner.getShipPickups().remove(shipPickup);
				}
				partner.getShipPickups().add(0, shipPickup);
				// setInfoMessage("Ship to was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Ship to.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void saveCourierAccountAction() {
		try {
			int _tmpRet = courierAccountLogic.saveCourierAccount(courierAccount);

			if (_tmpRet > 0) {
				// set the name of the courier
				for (Object _tmpCourier : couriers) {
					if (new Integer(courierAccount.getCourier().getValueId()).equals(((SelectItem) _tmpCourier).getValue())) {
						courierAccount.getCourier().setValue1(((SelectItem) _tmpCourier).getLabel());
						break;
					}
				}
				courierAccount.setCourierAccountId(_tmpRet);

				if (getSelectedtab().equals("rm")) {
					if (partner.getDepartmentInfoRM().getCourierAccounts() == null) {
						partner.getDepartmentInfoRM().setCourierAccounts(new ArrayList<CourierAccount>());
					}
					if (!partner.getDepartmentInfoRM().getCourierAccounts().isEmpty()) {
						partner.getDepartmentInfoRM().getCourierAccounts().remove(courierAccount);
					}
					partner.getDepartmentInfoRM().getCourierAccounts().add(courierAccount);
				} else if (getSelectedtab().equals("ip")) {
					if (partner.getDepartmentInfoIP().getCourierAccounts() == null) {
						partner.getDepartmentInfoIP().setCourierAccounts(new ArrayList<CourierAccount>());
					}
					if (!partner.getDepartmentInfoIP().getCourierAccounts().isEmpty()) {
						partner.getDepartmentInfoIP().getCourierAccounts().remove(courierAccount);
					}
					partner.getDepartmentInfoIP().getCourierAccounts().add(courierAccount);
				} else if (getSelectedtab().equals("lo")) {
					if (partner.getDepartmentInfoLO().getCourierAccounts() == null) {
						partner.getDepartmentInfoLO().setCourierAccounts(new ArrayList<CourierAccount>());
					}
					if (!partner.getDepartmentInfoLO().getCourierAccounts().isEmpty()) {
						partner.getDepartmentInfoLO().getCourierAccounts().remove(courierAccount);
					}
					partner.getDepartmentInfoLO().getCourierAccounts().add(courierAccount);
				}
				// setInfoMessage("Account was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Account.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}

	private void refreshContactListsFromDB() {
		try {
			Partner auxPartner = new Partner();
			auxPartner.setPartnerId(partner.getPartnerId());

			if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_RM) {
				auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
				partner.getDepartmentInfoRM().setPartnerContacts(partnerLogic.loadContacts(auxPartner));

			} else if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_IP) {
				auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
				partner.getDepartmentInfoIP().setPartnerContacts(partnerLogic.loadContacts(auxPartner));

			} else if (partnerContact.getDepartmentLotId() == Constants.MASTER_VALUE_DEPARTMENT_FF) {
				auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
				partner.getDepartmentInfoLO().setPartnerContacts(partnerLogic.loadContacts(auxPartner));

			} else { // refresque todas las tablas
				auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
				partner.getDepartmentInfoRM().setPartnerContacts(partnerLogic.loadContacts(auxPartner));
				auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
				partner.getDepartmentInfoIP().setPartnerContacts(partnerLogic.loadContacts(auxPartner));
				auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
				partner.getDepartmentInfoLO().setPartnerContacts(partnerLogic.loadContacts(auxPartner));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectToDeleteContactPhone() {
		try {
			phone = new Phone();
			setPhone((Phone) tableContactPhones.getRowData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteContactPhoneAction() {
		try {
			if (phone != null) {
				phone.setDisabledDate(new Date());
				int _tmp = phoneLogic.savePhone(phone);
				if (_tmp > 0) {
					partnerContact.setPhones(partnerContactLogic.loadContactPhones(partnerContact));
					partnerContact.setConcatenatedPhones(fillConcatenatedPhones(partnerContact.getPhones()));
				} else {
					setWarningMessage("Phone was not saved");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String newContactPhoneAction() {
		Phone _tmpPhone = new Phone();
		if (partnerContact.getPhones().size() > 0) {
			_tmpPhone = partnerContact.getPhones().get(partnerContact.getPhones().size() - 1);
			if (!_tmpPhone.isEmpty(_tmpPhone)) {
				partnerContact.getPhones().add(new Phone());
			}
		} else if (partnerContact.getPhones().size() == 0) {
			partnerContact.getPhones().add(new Phone());
		}
		return null;
	}

	public void savePartnerContactPhonesAction() {
		try {
			System.out.println(" entra a guardar phones ");
			List<Phone> _tmpRet = partnerContactLogic
					.saveContactPhones(partnerContact);

			if (_tmpRet != null) {
				partnerContact.setPhones(_tmpRet);
				partnerContact
						.setConcatenatedPhones(fillConcatenatedPhones(_tmpRet));
				// setInfoMessage("Phones was successfully saved");
			} else {
				setWarningMessage("Phones was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Partner.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	private String fillConcatenatedPhones(List<Phone> phones) {
		if (phones != null) {
			String _tmpphone = "";
			for (Phone phone : phones) {
				_tmpphone = _tmpphone + "(";
				if (phone.getCountryCode() != null
						&& !phone.getCountryCode().isEmpty()) {
					_tmpphone = _tmpphone + phone.getCountryCode();
				}
				if (phone.getAreaCode() != null
						&& !phone.getAreaCode().isEmpty()) {
					_tmpphone = _tmpphone + phone.getAreaCode();
				}
				_tmpphone = _tmpphone + ")";
				if (phone.getPhoneNumber() != null
						&& !phone.getPhoneNumber().isEmpty()) {
					_tmpphone = _tmpphone + phone.getPhoneNumber();
				}
				if (phone.getPhoneExtension() != null
						&& !phone.getPhoneExtension().isEmpty()) {
					_tmpphone = _tmpphone + " ext:" + phone.getPhoneExtension();
				}
				_tmpphone = _tmpphone + " / ";
			}
			return _tmpphone;
		} else {
			return "";
		}
	}
	
	public void setAsMainContactPhone(){
		Phone mainPhone = (Phone) tableContactPhones.getRowData();
		int type = mainPhone.getType().getValueId();
		List<Phone> _tmpPhones = partnerContact.getPhones();
		for(Phone _tmpPh : _tmpPhones ){
			if(_tmpPh.getType().getValueId() == type){
				if(!mainPhone.equals(_tmpPh)){
					_tmpPh.setMainPhone(false);
				}
			}
		}
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public void setPartnerContactsList(List<SelectItem> partnerContactsList) {
		this.partnerContactsList = partnerContactsList;
	}

	public List<SelectItem> getPartnerContactsList() {

		Partner _tmpPartner = new Partner();
		_tmpPartner.setPartnerId(partner.getPartnerId());
		List<PartnerContact> partnerContacts = null;
		try {
			partnerContacts = partnerLogic.loadContacts(_tmpPartner);
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the contacts.\n"
					+ e.getMessage());
		}
		partnerContactsList = new ArrayList<SelectItem>();
		for (PartnerContact _tmpContact : partnerContacts) {
			SelectItem item = new SelectItem(new Integer(
					_tmpContact.getContactId()), _tmpContact.getName());
			partnerContactsList.add(item);
		}

		return partnerContactsList;
	}

	public void setCallsHistoryFiltered(List callsHistoryFiltered) {
		this.callsHistoryFiltered = callsHistoryFiltered;
	}

	public List getCallsHistoryFiltered() {
		return callsHistoryFiltered;
	}


	public void setCallHistory(CallHistory callHistory) {
		this.callHistory = callHistory;
	}

	public CallHistory getCallHistory() {
		return callHistory;
	}

	public List getShipPickupsFiltered() {
		return shipPickupsFiltered;
	}

	public void setShipPickupsFiltered(List shipPickupsFiltered) {
		this.shipPickupsFiltered = shipPickupsFiltered;
	}

	public ShipPickUp getShipPickup() {
		return shipPickup;
	}

	public void setShipPickup(ShipPickUp shipPickup) {
		this.shipPickup = shipPickup;
	}

	public CourierAccount getCourierAccount() {
		return courierAccount;
	}

	public void setCourierAccount(CourierAccount courierAccount) {
		this.courierAccount = courierAccount;
	}

	public PartnerContact getPartnerContact() {
		return partnerContact;
	}

	public void setPartnerContact(PartnerContact partnerContact) {
		this.partnerContact = partnerContact;
	}

	public IPartnerLogic getPartnerLogic() {
		return partnerLogic;
	}

	public void setPartnerLogic(IPartnerLogic partnerLogic) {
		this.partnerLogic = partnerLogic;
	}

	public IPartnerDepartmentInfoLogic getPartnerDepartmentInfoLogic() {
		return partnerDepartmentInfoLogic;
	}

	public void setPartnerDepartmentInfoLogic(
			IPartnerDepartmentInfoLogic partnerDepartmentInfoLogic) {
		this.partnerDepartmentInfoLogic = partnerDepartmentInfoLogic;
	}

	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	public List getCountries() {
		try {
			setCountries(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CountryList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the countries.\n"
					+ e.getMessage());
		}
		return countries;
	}

	public void setCountries(List countries) {
		Collections.sort(countries, new AlphabeticComparator());
		this.countries = countries;
	}

	public List getProvinces() {
		try {
			setProvinces(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.StateList").getElements(
					"faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the states.\n"
					+ e.getMessage());
		}
		return provinces;
	}

	public void setProvinces(List provinces) {
		Collections.sort(provinces, new AlphabeticComparator());
		this.provinces = provinces;
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

	public List getCouriers() {
		try {
			setCouriers(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CourierList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Couriers.\n"
					+ e.getMessage());
		}
		return couriers;
	}

	public void setCouriers(List couriers) {
		this.couriers = couriers;
	}

	public List getPaymentTermsList() {
		try {
			setPaymentTermsList(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.PaymentTermList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the payment terms.\n"
					+ e.getMessage());
		}
		return paymentTermsList;
	}

	public void setPaymentTermsList(List paymentTermsList) {
		this.paymentTermsList = paymentTermsList;
	}

	public List getLanguages() {
		try {
			setLanguages(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.LanguageList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the languages.\n"
					+ e.getMessage());
		}
		return languages;
	}

	public void setLanguages(List languages) {
		this.languages = languages;
	}

	public List getStatus() {
		try {
			setStatus(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.ClientStatusList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the status.\n"
					+ e.getMessage());
		}
		return status;
	}

	public void setStatus(List status) {
		this.status = status;
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

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
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

	public void setSelectedtab(String selectedtab) {
		this.selectedtab = selectedtab;
	}

	public String getSelectedtab() {
		return selectedtab;
	}

	public Integer getPaginaContactsRM() {
		return paginaContactsRM;
	}

	public void setPaginaContactsRM(Integer paginaContactsRM) {
		this.paginaContactsRM = paginaContactsRM;
	}

	public String getTableStateContactsRM() {
		return tableStateContactsRM;
	}

	public void setTableStateContactsRM(String tableStateContactsRM) {
		this.tableStateContactsRM = tableStateContactsRM;
	}

	public HtmlExtendedDataTable getTableContactsRM() {
		return tableContactsRM;
	}

	public void setTableContactsRM(HtmlExtendedDataTable tableContactsRM) {
		this.tableContactsRM = tableContactsRM;
	}

	public Integer getPaginaAccountRM() {
		return paginaAccountRM;
	}

	public void setPaginaAccountRM(Integer paginaAccountRM) {
		this.paginaAccountRM = paginaAccountRM;
	}

	public String getTableStateAccountRM() {
		return tableStateAccountRM;
	}

	public void setTableStateAccountRM(String tableStateAccountRM) {
		this.tableStateAccountRM = tableStateAccountRM;
	}

	public HtmlExtendedDataTable getTableAccountRM() {
		return tableAccountRM;
	}

	public void setTableAccountRM(HtmlExtendedDataTable tableAccountRM) {
		this.tableAccountRM = tableAccountRM;
	}

	public String getTableStateShipPickup() {
		return tableStateShipPickup;
	}

	public void setTableStateShipPickup(String tableStateShipPickup) {
		this.tableStateShipPickup = tableStateShipPickup;
	}

	public HtmlExtendedDataTable getTableShipPickup() {
		return tableShipPickup;
	}

	public void setTableShipPickup(HtmlExtendedDataTable tableShipPickup) {
		this.tableShipPickup = tableShipPickup;
	}

	public Integer getPaginaContactsIP() {
		return paginaContactsIP;
	}

	public void setPaginaContactsIP(Integer paginaContactsIP) {
		this.paginaContactsIP = paginaContactsIP;
	}

	public String getTableStateContactsIP() {
		return tableStateContactsIP;
	}

	public void setTableStateContactsIP(String tableStateContactsIP) {
		this.tableStateContactsIP = tableStateContactsIP;
	}

	public HtmlExtendedDataTable getTableContactsIP() {
		return tableContactsIP;
	}

	public void setTableContactsIP(HtmlExtendedDataTable tableContactsIP) {
		this.tableContactsIP = tableContactsIP;
	}

	public Integer getPaginaAccountIP() {
		return paginaAccountIP;
	}

	public void setPaginaAccountIP(Integer paginaAccountIP) {
		this.paginaAccountIP = paginaAccountIP;
	}

	public String getTableStateAccountIP() {
		return tableStateAccountIP;
	}

	public void setTableStateAccountIP(String tableStateAccountIP) {
		this.tableStateAccountIP = tableStateAccountIP;
	}

	public HtmlExtendedDataTable getTableAccountIP() {
		return tableAccountIP;
	}

	public void setTableAccountIP(HtmlExtendedDataTable tableAccountIP) {
		this.tableAccountIP = tableAccountIP;
	}

	public Integer getPaginaContactsFF() {
		return paginaContactsFF;
	}

	public void setPaginaContactsFF(Integer paginaContactsFF) {
		this.paginaContactsFF = paginaContactsFF;
	}

	public String getTableStateContactsFF() {
		return tableStateContactsFF;
	}

	public void setTableStateContactsFF(String tableStateContactsFF) {
		this.tableStateContactsFF = tableStateContactsFF;
	}

	public HtmlExtendedDataTable getTableContactsFF() {
		return tableContactsFF;
	}

	public void setTableContactsFF(HtmlExtendedDataTable tableContactsFF) {
		this.tableContactsFF = tableContactsFF;
	}

	public Integer getPaginaAccountFF() {
		return paginaAccountFF;
	}

	public void setPaginaAccountFF(Integer paginaAccountFF) {
		this.paginaAccountFF = paginaAccountFF;
	}

	public String getTableStateAccountFF() {
		return tableStateAccountFF;
	}

	public void setTableStateAccountFF(String tableStateAccountFF) {
		this.tableStateAccountFF = tableStateAccountFF;
	}

	public HtmlExtendedDataTable getTableAccountFF() {
		return tableAccountFF;
	}

	public void setTableAccountFF(HtmlExtendedDataTable tableAccountFF) {
		this.tableAccountFF = tableAccountFF;
	}

	public void setTableCallHistory(HtmlExtendedDataTable tableCallHistory) {
		this.tableCallHistory = tableCallHistory;
	}

	public HtmlExtendedDataTable getTableCallHistory() {
		return tableCallHistory;
	}

	public HtmlExtendedDataTable getTableContactPhones() {
		return tableContactPhones;
	}

	public void setTableContactPhones(HtmlExtendedDataTable tableContactPhones) {
		this.tableContactPhones = tableContactPhones;
	}

	public void setQuickPartner(Partner quickPartner) {
		this.quickPartner = quickPartner;
	}

	public Partner getQuickPartner() {
		return quickPartner;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public IPartnerContactLogic getPartnerContactLogic() {
		return partnerContactLogic;
	}

	public void setPartnerContactLogic(IPartnerContactLogic partnerContactLogic) {
		this.partnerContactLogic = partnerContactLogic;
	}

	public IShipPickupLogic getShipPickupLogic() {
		return shipPickupLogic;
	}

	public void setShipPickupLogic(IShipPickupLogic shipPickupLogic) {
		this.shipPickupLogic = shipPickupLogic;
	}

	public ICourierAccountLogic getCourierAccountLogic() {
		return courierAccountLogic;
	}

	public void setCourierAccountLogic(ICourierAccountLogic courierAccountLogic) {
		this.courierAccountLogic = courierAccountLogic;
	}

	public ICallHistoryLogic getCallHistoryLogic() {
		return callHistoryLogic;
	}

	public void setCallHistoryLogic(ICallHistoryLogic callHistoryLogic) {
		this.callHistoryLogic = callHistoryLogic;
	}

	public void setShipPickLabel(String shipPickLabel) {
		this.shipPickLabel = shipPickLabel;
	}

	public String getShipPickLabel() {
		if (isJspClient) {
			setShipPickLabel("Ship to");
		} else if (!isJspClient) {
			setShipPickLabel("Pickup from");
		}
		return shipPickLabel;
	}

	public boolean isJspClient() {
		return isJspClient;
	}

	public void setJspClient(boolean isJspClient) {
		this.isJspClient = isJspClient;
	}

	public void setStyleTableHeader(String styleTableHeader) {
		this.styleTableHeader = styleTableHeader;
	}

	public String getStyleTableHeader() {
		return styleTableHeader;
	}

	public void setCityLogic(ICityLogic cityLogic) {
		this.cityLogic = cityLogic;
	}

	public ICityLogic getCityLogic() {
		return cityLogic;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
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

	public void setCity(City city) {
		this.city = city;
	}

	public List getPhoneTypes() {
		try {
			setPhoneTypes(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.PhoneTypeList")
					.getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Phone Types.\n"
					+ e.getMessage());
		}
		return phoneTypes;
	}

	public void setPhoneTypes(List phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public City getCity() {
		return city;
	}

	public void setFiltroCallHistory(String filtroCallHistory) {
		this.filtroCallHistory = filtroCallHistory;
	}

	public String getFiltroCallHistory() {
		return filtroCallHistory;
	}

	public String getFiltroShipPickup() {
		return filtroShipPickup;
	}

	public void setFiltroShipPickup(String filtroShipPickup) {
		this.filtroShipPickup = filtroShipPickup;
	}


	public List<City> autocompleteCity(Object suggest) {
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

	public void validateClientCode(FacesContext context, UIComponent component,
			Object value) {
		String code = (String) value;
		if (partner.getPartnerId() <= 0) {
			partner.setValidCode(false);
		}
		if (code.length() != 3) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Invalid format.");
			message.setDetail("Code must be 3 characters.");
			context.addMessage(component.getClientId(context), message);
		} else if (code.length() == 3 && !partner.isValidCode()) {
			Partner _tmp = new Partner();
			_tmp.setCode(code);
			_tmp.setClient(true);
			try {
				if (partnerLogic.searchByCode(_tmp).getPartnerId() > 0) {
					FacesMessage message = new FacesMessage();
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					message.setSummary("Code already exist.");
					message.setDetail("Code must be 3 characters.");
					context.addMessage(component.getClientId(context), message);
				} else {
					partner.setValidCode(true);
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to validate the Client Code.\n"
						+ e.getMessage());
			}
		}
	}

	public void validateSupplierName(FacesContext context,
			UIComponent component, Object value) {
		String name = (String) value;
		if (partner.getPartnerId() <= 0) {
			partner.setValidCode(false);
		}
		if (name.length() < 3) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Invalid format.");
			context.addMessage(component.getClientId(context), message);
		} else if (name.length() >= 3 && !partner.isValidCode()) {
			Partner _tmp = new Partner();
			_tmp.setName(name);
			_tmp.setClient(false);
			try {
				if (partnerLogic.searchByCode(_tmp).getPartnerId() > 0) {
					FacesMessage message = new FacesMessage();
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					message.setSummary("Name already exist.");
					context.addMessage(component.getClientId(context), message);
				} else {
					partner.setValidCode(true);
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to validate the Client Code.\n"
						+ e.getMessage());
			}
		}
	}

	public String clearValidCode() {
		partner.setValidCode(false);
		return null;
	}

	public void newCityAction() {
		setCity(new City());
	}

	public void saveCityAction() {
		try {
			cityLogic.saveCity(city);
			AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CityList")
					.clearElements();
			AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.CityList").refreshList();
			partner.getAddress().getCity().setName("");
			partner.getAddress().getCity().setCityId(0);
			partner.getAddress().getCity().getCountry().setValueId(0);
			partner.getAddress().getCity().getCountry().setValue1("");
			partner.getAddress().getCity().getStateProvince().setValueId(0);
			partner.getAddress().getCity().getStateProvince().setValue1("");
		} catch (Exception e) {
			setErrorMessage("Error trying to save the city.\n" + e.getMessage());
		}
	}

	public void newCallHistAction() {
		callHistory = new CallHistory();
		callHistory.setPartner(partner);
		callHistory.setEnteredDate(new Date());
		Employee _tmpEmpl = new Employee(this.getEmployeeLoggedId());
		try {
			_tmpEmpl = employeeLogic.loadEmployeeById(_tmpEmpl);
		} catch (Exception e) {
			setErrorMessage("Error trying to create the new CallHistory.\n"
					+ e.getMessage());
		}
		callHistory.setEmployeeCreator(_tmpEmpl);
	}

	public void saveCallHistoryAction() {
		try {
			int _tmpRet = callHistoryLogic.saveCallHistory(callHistory);
			if (_tmpRet > 0) {
				callHistory.setCallId(_tmpRet);
				if (partner.getCallsHistory() != null) {
					if (!partner.getCallsHistory().isEmpty()) {
						partner.getCallsHistory().remove(callHistory);
					}
				} else {
					partner.setCallsHistory(new ArrayList<CallHistory>());
				}
				partner.getCallsHistory().add(0, callHistory);
				callsHistoryFiltered = partner.getCallsHistory();
				for (SelectItem item : partnerContactsList) {
					if ((Integer) item.getValue() == callHistory.getContact()
							.getContactId()) {
						callHistory.getContact().setName(item.getLabel());
						break;
					}
				}
				// setInfoMessage("Partner was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}

		} catch (Exception e) {
			System.out.println("paso por el saveException");
			setErrorMessage("Error trying to save the Call History.\n"
					+ e.getMessage());
		}
	}

	public void FilterCallsHistory() {
		callsHistoryFiltered = new ArrayList();
		if (partner.getCallsHistory() != null || !partner.getCallsHistory().isEmpty()) {
			List<CallHistory> _tmp = partner.getCallsHistory();
			for (CallHistory _tmpCall : _tmp) {
				if (_tmpCall.getNotes() != null
						&& _tmpCall.getNotes().toUpperCase()
								.indexOf(filtroCallHistory.toUpperCase()) >= 0) {
					callsHistoryFiltered.add(_tmpCall);
				} else if (_tmpCall.getContact() != null
						&& _tmpCall.getContact().getName() != null) {
					if (_tmpCall.getContact().getName().toUpperCase()
							.indexOf(filtroCallHistory.toUpperCase()) >= 0) {
						callsHistoryFiltered.add(_tmpCall);
					}
				}
			}
		}
	}

	public void filterShipPickup() {
		shipPickupsFiltered = new ArrayList();
		List<ShipPickUp> _tmp = partner.getShipPickups();
		for (ShipPickUp _tmpShipPick : _tmp) {
			if (_tmpShipPick.getName() != null
					&& _tmpShipPick.getName().toUpperCase()
							.indexOf(filtroShipPickup.toUpperCase()) >= 0) {
				shipPickupsFiltered.add(_tmpShipPick);
			} else if (_tmpShipPick.getNotes() != null
					&& _tmpShipPick.getNotes().toUpperCase()
							.indexOf(filtroShipPickup.toUpperCase()) >= 0) {
				shipPickupsFiltered.add(_tmpShipPick);
			} else if (_tmpShipPick.getNotifyParty() != null
					&& _tmpShipPick.getNotifyParty().toUpperCase()
							.indexOf(filtroShipPickup.toUpperCase()) >= 0) {
				shipPickupsFiltered.add(_tmpShipPick);
			}
		}
	}

	public IPortLogic getPortLogic() {
		return portLogic;
	}

	public void setPortLogic(IPortLogic portLogic) {
		this.portLogic = portLogic;
	}

	public IPhoneLogic getPhoneLogic() {
		return phoneLogic;
	}

	public void setPhoneLogic(IPhoneLogic phoneLogic) {
		this.phoneLogic = phoneLogic;
	}

	public List getPorts() {
		if (allPorts == null)
			getAllPorts();
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

	public void setPorts(List ports) {
		this.ports = ports;
	}

	public List getAirPorts() {
		if (allPorts == null)
			getAllPorts();
		List<SelectItem> auxAirports = new ArrayList<SelectItem>();
		for (Port tmpPort : allPorts) {
			if (tmpPort.isAir()) {
				auxAirports.add(new SelectItem(tmpPort.getPortId(), tmpPort
						.getName()));
			}
		}
		airPorts = auxAirports;
		return airPorts;
	}

	public void setAirPorts(List airPorts) {
		this.airPorts = airPorts;
	}

	public void getAllPorts() {
		try {
			setAllPorts(AdministradorListas.obtenerLista( "com.lotrading.softlot.util.lists.PortsList") .getElements("faces"));
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Ports.\n"
					+ e.getMessage());
		}
	}

	public void setAllPorts(List<Port> allPorts) {
		this.allPorts = allPorts;
	}

	public void setDepartments(List<SelectItem> departments) {
		this.departments = departments;
	}

	public List<SelectItem> getDepartments() {
		try {
			setDepartments(AdministradorListas.obtenerLista(
					"com.lotrading.softlot.util.lists.DepartmentList2")
					.getElements("faces"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return departments;
	}

	public void setResourceLogic(IResourceLogic resourceLogic) {
		this.resourceLogic = resourceLogic;
	}

	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}

	public IEmployeeLogic getEmployeeLogic() {
		return employeeLogic;
	}

	public void setEmployeeLogic(IEmployeeLogic employeeLogic) {
		this.employeeLogic = employeeLogic;
	}

	public IClientRateLogic getClientRateLogic() {
		return clientRateLogic;
	}

	public void setClientRateLogic(IClientRateLogic clientRateLogic) {
		this.clientRateLogic = clientRateLogic;
	}

	public List<Resource> getActionsList() {
		return actionsList;
	}

	public void setActionsList(List<Resource> actionsList) {
		this.actionsList = actionsList;
	}

	public void setChangeGenInfoActive(boolean changeGenInfoActive) {
		this.changeGenInfoActive = changeGenInfoActive;
	}

	public boolean isChangeGenInfoActive() {
		return changeGenInfoActive;
	}

	public void setLinkNewPartnerVisible(boolean linkNewPartnerVisible) {
		this.linkNewPartnerVisible = linkNewPartnerVisible;
	}

	public boolean isLinkNewPartnerVisible() {
		loadActions();
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("NEW PARTNER")) {
					setLinkNewPartnerVisible(true);
					break;
				}
			}
		}
		return linkNewPartnerVisible;
	}

	public void setSavePartnerVisible(boolean savePartnerVisible) {
		this.savePartnerVisible = savePartnerVisible;
	}

	public boolean isSavePartnerVisible() {
		loadActions();
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("SAVE")) {
					setSavePartnerVisible(true);
					break;
				}
			}
		}
		return savePartnerVisible;
	}

	public void setLinkNewCityVisible(boolean linkNewCityVisible) {
		this.linkNewCityVisible = linkNewCityVisible;
	}

	public boolean isLinkNewCityVisible() {
		loadActions();
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("NEW CITY")) {
					setLinkNewCityVisible(true);
					break;
				}
			}
		}
		return linkNewCityVisible;
	}

	public void setLinkDeleteContactVisible(boolean linkDeleteContactVisible) {
		this.linkDeleteContactVisible = linkDeleteContactVisible;
	}

	public boolean isLinkDeleteContactVisible() {
		loadActions();
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("DELETE")) {
					setLinkDeleteContactVisible(true);
					break;
				}
			}
		}
		return linkDeleteContactVisible;
	}

	public void setSaveDeptInfoVisible(boolean saveDeptInfoVisible) {
		this.saveDeptInfoVisible = saveDeptInfoVisible;
	}

	public boolean isSaveDeptInfoVisible() {
		loadActions();
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("SAVE DEPT INFO")) {
					setSaveDeptInfoVisible(true);
					break;
				}
			}
		}
		return saveDeptInfoVisible;
	}
	
	public boolean isReadOnlyPaymentTerm() {
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("CLIENT PAYMENT TERM")) {
					setReadOnlyPaymentTerm(false);
					break;
				}
			}
		}
		return readOnlyPaymentTerm;
	}

	public void setReadOnlyPaymentTerm(boolean readOnlyPaymentTerm) {
		this.readOnlyPaymentTerm = readOnlyPaymentTerm;
	}

	public void loadActions() { // *** Este metodo se utiliza para llenar la lista de
		if (actionsList == null) { // *** acciones que tiene el usuario en el recurso(jsp)
			String urlRecurso = (String) super
					.getSessionAttribute("RECURSO_DESPLEGADO");
			Employee employee = (Employee) super
					.getSessionAttribute("#{employee_auth}");
			Resource _tmpResource = new Resource();
			ResourceRole _tmpResouceRole = new ResourceRole();
			_tmpResource.setUrl(urlRecurso);
			_tmpResouceRole.setEmployeeId(employee.getEmployeeId());
			try {
				_tmpResource = resourceLogic.loadResourceByUrl(_tmpResource);
				if (_tmpResource != null) {
					_tmpResouceRole.setResourceId(_tmpResource.getResourceId());
					actionsList = resourceLogic.loadResourceActions(
							_tmpResouceRole, true);
					if (actionsList == null) {
						actionsList = new ArrayList<Resource>();
					}
				}
			} catch (Exception e) {
				setErrorMessage("Error trying to retrieve a intern resource.\n"
						+ e.getMessage());
			}
		}
	}

	public void changeState() {
		if (isChangeGenInfoActive()) {
			setChangeGenInfoActive(false);
		} else {
			setChangeGenInfoActive(true);
		}
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
	
	public boolean showNotifRegionRestricted(){
		if(partner.isRegGER()){
			Employee _tmpEmpl = this.getEmployeeLogged();
			if(_tmpEmpl.getRegion().getValueId() != Constants.MASTER_VALUE_REGION_GER){
				return true;
			}
		}
		return false;
	}
	
	public void loadClientRatesAction(){
		try {  
			clientRateOtherCharge = new ClientRate();
			clientAirRatePortFilter = new ClientRatesPort();
			clientOceanLCLRatePortFilter = new ClientRatesPort();
			clientOcean20RatePortFilter = new ClientRatesPort();
			clientOcean40RatePortFilter = new ClientRatesPort();
			
			clientRatePort = new ClientRatesPort();
			clientRate = new ClientRate();
			
			ClientRatesPort _tmpClientRatesPort  = new ClientRatesPort();
			_tmpClientRatesPort.setClientId(partner.getPartnerId());
			_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_AIR);
			partner.setClientAirRates(partnerLogic.loadClientRates(_tmpClientRatesPort));
			airRatesList = partner.getClientAirRates();
			
			_tmpClientRatesPort  = new ClientRatesPort();
			_tmpClientRatesPort.setClientId(partner.getPartnerId());
			_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_20);
			partner.setClientOceanRates20(partnerLogic.loadClientRates(_tmpClientRatesPort));
			ocean20RatesList = partner.getClientOceanRates20();
			
			_tmpClientRatesPort  = new ClientRatesPort();
			_tmpClientRatesPort.setClientId(partner.getPartnerId());
			_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_40);
			partner.setClientOceanRates40(partnerLogic.loadClientRates(_tmpClientRatesPort));
			ocean40RatesList = partner.getClientOceanRates40();
			
			_tmpClientRatesPort  = new ClientRatesPort();
			_tmpClientRatesPort.setClientId(partner.getPartnerId());
			_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_LCL);
			partner.setClientOceanRatesLCL(partnerLogic.loadClientRates(_tmpClientRatesPort));
			oceanLCLRatesList = partner.getClientOceanRatesLCL();
			
		} catch (Exception e) {
			setErrorMessage("Error trying to load the Client Rates info .\n"
					+ e.getMessage());
		}
	}
	
	public void newRateAction(){
		ClientRatesPort _tmpRatePort = new ClientRatesPort();
		_tmpRatePort.setClientId(partner.getPartnerId());
		MasterValue _tmpMasterValue = new MasterValue();
		
		if(selectedTabRates.equals(rateTypeAirTabName)){
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_AIR);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_AIR));
			partner.getClientAirRates().add(0,_tmpRatePort);
			airRatesList = partner.getClientAirRates();
			clientAirRatePortFilter = new ClientRatesPort();
			
		}else if(selectedTabRates.equals(rateTypeOceanLCLTabName)){
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_OCEAN_LCL);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_LCL));
			partner.getClientOceanRatesLCL().add(0,_tmpRatePort);
			oceanLCLRatesList = partner.getClientOceanRatesLCL();
			clientOceanLCLRatePortFilter = new ClientRatesPort();
			
		}else if(selectedTabRates.equals(rateTypeOcean20TabName)){
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_OCEAN_20_40);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_20));
			partner.getClientOceanRates20().add(0,_tmpRatePort);
			ocean20RatesList = partner.getClientOceanRates20();
			clientOcean20RatePortFilter = new ClientRatesPort();
			
		}else if(selectedTabRates.equals(rateTypeOcean40TabName)){
			_tmpMasterValue.setMasterId(Constants.MASTER_CHARGE_TYPE_OCEAN_20_40);
			_tmpRatePort.setRateType(new MasterValue(Constants.MASTER_VALUE_RATE_TYPE_40));
			partner.getClientOceanRates40().add(0,_tmpRatePort);
			ocean40RatesList = partner.getClientOceanRates40();
			clientOcean40RatePortFilter = new ClientRatesPort();
		}
		try {
			List<MasterValue> chargesList = masterValueLogic.searchMasterValue(_tmpMasterValue);
			for(MasterValue _tmpMasterVal: chargesList){
				if(_tmpMasterVal.getDisabledDate() == null){
					ClientRate _tmpClientRate = new ClientRate();
					_tmpClientRate.setChargeType(_tmpMasterVal);
					_tmpClientRate.setFlat((_tmpMasterVal.getValue3().equalsIgnoreCase("flat")? true : false));
					_tmpRatePort.getClientRates().add(_tmpClientRate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("Error trying to retrieve the charges types.\n"+ e.getMessage());
		}
	}
	
	public void saveRateAction(){
		ClientRatesPort _tmpCarrierPort = new ClientRatesPort();
		if(selectedTabRates.equals(rateTypeAirTabName)){
			_tmpCarrierPort = (ClientRatesPort) dataListAir.getRowData();
			
		}else if(selectedTabRates.equals(rateTypeOceanLCLTabName)){
			_tmpCarrierPort = (ClientRatesPort) dataListOceanLCL.getRowData();
			
		}else if(selectedTabRates.equals(rateTypeOcean20TabName)){
			_tmpCarrierPort = (ClientRatesPort) dataListOcean20.getRowData();
			
		}else if(selectedTabRates.equals(rateTypeOcean40TabName)){
			_tmpCarrierPort = (ClientRatesPort) dataListOcean40.getRowData();
			
		}
		
		try {
			_tmpCarrierPort = clientRateLogic.saveClientRate(_tmpCarrierPort);
		} catch (Exception e) {
			setErrorMessage("Error trying to save the rates.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveOtherChargeRateAction(){
		clientRatePort.getClientRates().add(clientRateOtherCharge);
		for(SelectItem _tmpItem : otherChargesList){
			if((Integer) _tmpItem.getValue() == clientRateOtherCharge.getChargeType().getValueId()){
				clientRateOtherCharge.getChargeType().setValue1(_tmpItem.getLabel());
			}
		}
		try {
			clientRateOtherCharge = clientRateLogic.loadRateCost(clientRatePort, clientRateOtherCharge);
			clientRatePort = clientRateLogic.saveClientRate(clientRatePort);
			clearValuesRatespopups();
		} catch (Exception e) {
			setErrorMessage("Error trying to save the the other charges.\n"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void newOtherChargeRateAction(){
		clientRatePort = new ClientRatesPort();
		if(selectedTabRates.equals(rateTypeAirTabName)){
			clientRatePort = (ClientRatesPort) dataListAir.getRowData();
			
		}else if(selectedTabRates.equals(rateTypeOceanLCLTabName)){
			clientRatePort = (ClientRatesPort) dataListOceanLCL.getRowData();
			
		}else if(selectedTabRates.equals(rateTypeOcean20TabName)){
			clientRatePort = (ClientRatesPort) dataListOcean20.getRowData();
			
		}else if(selectedTabRates.equals(rateTypeOcean40TabName)){
			clientRatePort = (ClientRatesPort) dataListOcean40.getRowData();
		}
		clientRateOtherCharge = new ClientRate();
		clientRateOtherCharge.setOtherCharge(true);
	}
	
	private void clearValuesRatespopups(){
		clientRatePort = null;
		clientRateOtherCharge = null;
	}
	
	public void calculateRate(){
		try {
			clientRate = clientRateLogic.loadRateCost(clientRatePort, clientRate);
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the rate cost.\n"+ e.getMessage());
			e.printStackTrace();
		}
	} 
	
	public void selectToDeleteRateAction(){
		try{
			clientRateOtherCharge = new ClientRate();
			if (selectedTabRates.equals(rateTypeAirTabName)) {
				clientRateOtherCharge = (ClientRate) airRatesDataTable.getRowData();
			} else if (selectedTabRates.equals(rateTypeOceanLCLTabName)) {
				clientRateOtherCharge =  (ClientRate) lclRatesDataTable.getRowData();
			} else if (selectedTabRates.equals(rateTypeOcean20TabName)) {
				clientRateOtherCharge = (ClientRate) ocean20RatesDataTable.getRowData();
			} else if (selectedTabRates.equals(rateTypeOcean40TabName)) {
				clientRateOtherCharge = (ClientRate) ocean40RatesDataTable.getRowData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRateAction(){
		try {
			if (clientRateLogic != null){
				if(clientRateLogic.deleteClientRate(clientRateOtherCharge)){
					ClientRatesPort _tmpClientRatesPort  = new ClientRatesPort();
					if (selectedTabRates.equals(rateTypeAirTabName)) {		
						_tmpClientRatesPort.setClientId(partner.getPartnerId());
						_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_AIR);
						partner.setClientAirRates(partnerLogic.loadClientRates(_tmpClientRatesPort));
						airRatesList = partner.getClientAirRates();
					} else if (selectedTabRates.equals(rateTypeOcean20TabName)) {	
						_tmpClientRatesPort  = new ClientRatesPort();
						_tmpClientRatesPort.setClientId(partner.getPartnerId());
						_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_20);
						partner.setClientOceanRates20(partnerLogic.loadClientRates(_tmpClientRatesPort));
						ocean20RatesList = partner.getClientOceanRates20();
					} else if (selectedTabRates.equals(rateTypeOcean40TabName)) {
						_tmpClientRatesPort  = new ClientRatesPort();
						_tmpClientRatesPort.setClientId(partner.getPartnerId());
						_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_40);
						partner.setClientOceanRates40(partnerLogic.loadClientRates(_tmpClientRatesPort));
						ocean40RatesList = partner.getClientOceanRates40();
					} else if (selectedTabRates.equals(rateTypeOceanLCLTabName)) {
						_tmpClientRatesPort  = new ClientRatesPort();
						_tmpClientRatesPort.setClientId(partner.getPartnerId());
						_tmpClientRatesPort.getRateType().setValueId(Constants.MASTER_VALUE_RATE_TYPE_LCL);
						partner.setClientOceanRatesLCL(partnerLogic.loadClientRates(_tmpClientRatesPort));
						oceanLCLRatesList = partner.getClientOceanRatesLCL();
					}
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to delete the Rate. \n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void filtrateByPorts(){
		List<ClientRatesPort> filteredItems = new ArrayList<ClientRatesPort>();
		if (selectedTabRates.equals(rateTypeAirTabName)) {
			for(ClientRatesPort _tmpClientRatesPort: partner.getClientAirRates()){
				if (clientAirRatePortFilter.getPortDestination() == _tmpClientRatesPort.getPortDestination() || clientAirRatePortFilter.getPortDestination() == 0) {
					if(clientAirRatePortFilter.getPortOrigin() == _tmpClientRatesPort.getPortOrigin() || clientAirRatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpClientRatesPort);
					}
				}
			}
			airRatesList = filteredItems;

		} else if (selectedTabRates.equals(rateTypeOceanLCLTabName)) {
			for(ClientRatesPort _tmpClientRatesPort: partner.getClientOceanRatesLCL()){
				if (clientOceanLCLRatePortFilter.getPortDestination() == _tmpClientRatesPort.getPortDestination() || clientOceanLCLRatePortFilter.getPortDestination() == 0) {
					if(clientOceanLCLRatePortFilter.getPortOrigin() == _tmpClientRatesPort.getPortOrigin() || clientOceanLCLRatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpClientRatesPort);
					}
				}
			}
			oceanLCLRatesList = filteredItems;

		} else if (selectedTabRates.equals(rateTypeOcean20TabName)) {
			for(ClientRatesPort _tmpClientRatesPort: partner.getClientOceanRates20()){
				if (clientOcean20RatePortFilter.getPortDestination() == _tmpClientRatesPort.getPortDestination() || clientOcean20RatePortFilter.getPortDestination() == 0) {
					if(clientOcean20RatePortFilter.getPortOrigin() == _tmpClientRatesPort.getPortOrigin() || clientOcean20RatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpClientRatesPort);
					}
				}
			}
			ocean20RatesList = filteredItems;

		} else if (selectedTabRates.equals(rateTypeOcean40TabName)) {
			for(ClientRatesPort _tmpClientRatesPort: partner.getClientOceanRates40()){
				if (clientOcean40RatePortFilter.getPortDestination() == _tmpClientRatesPort.getPortDestination() || clientOcean40RatePortFilter.getPortDestination() == 0) {
					if(clientOcean40RatePortFilter.getPortOrigin() == _tmpClientRatesPort.getPortOrigin() || clientOcean40RatePortFilter.getPortOrigin() == 0){
						filteredItems.add(_tmpClientRatesPort);
					}
				}
			}
			ocean40RatesList = filteredItems;
		}
	}

	public boolean isShowNotifRegionRestricted() {
		return showNotifRegionRestricted;
	}

	public void setShowNotifRegionRestricted(boolean showNotifRegionRestricted) {
		this.showNotifRegionRestricted = showNotifRegionRestricted;
	}

	public String getSelectedTabRates() {
		return selectedTabRates;
	}

	public void setSelectedTabRates(String selectedTabRates) {
		this.selectedTabRates = selectedTabRates;
	}

	public String getRateTypeAirTabName() {
		return rateTypeAirTabName;
	}

	public void setRateTypeAirTabName(String rateTypeAirTabName) {
		this.rateTypeAirTabName = rateTypeAirTabName;
	}

	public String getRateTypeOceanLCLTabName() {
		return rateTypeOceanLCLTabName;
	}

	public void setRateTypeOceanLCLTabName(String rateTypeOceanLCLTabName) {
		this.rateTypeOceanLCLTabName = rateTypeOceanLCLTabName;
	}

	public String getRateTypeOcean20TabName() {
		return rateTypeOcean20TabName;
	}

	public void setRateTypeOcean20TabName(String rateTypeOcean20TabName) {
		this.rateTypeOcean20TabName = rateTypeOcean20TabName;
	}

	public String getRateTypeOcean40TabName() {
		return rateTypeOcean40TabName;
	}

	public void setRateTypeOcean40TabName(String rateTypeOcean40TabName) {
		this.rateTypeOcean40TabName = rateTypeOcean40TabName;
	}

	public HtmlDataList getDataListOceanLCL() {
		return dataListOceanLCL;
	}

	public void setDataListOceanLCL(HtmlDataList dataListOceanLCL) {
		this.dataListOceanLCL = dataListOceanLCL;
	}

	public HtmlDataList getDataListOcean20() {
		return dataListOcean20;
	}

	public void setDataListOcean20(HtmlDataList dataListOcean20) {
		this.dataListOcean20 = dataListOcean20;
	}

	public HtmlDataList getDataListOcean40() {
		return dataListOcean40;
	}

	public void setDataListOcean40(HtmlDataList dataListOcean40) {
		this.dataListOcean40 = dataListOcean40;
	}

	public HtmlDataList getDataListAir() {
		return dataListAir;
	}

	public void setDataListAir(HtmlDataList dataListAir) {
		this.dataListAir = dataListAir;
	}

	public IMasterValueLogic getMasterValueLogic() {
		return masterValueLogic;
	}

	public void setMasterValueLogic(IMasterValueLogic masterValueLogic) {
		this.masterValueLogic = masterValueLogic;
	}

	public List<SelectItem> getCarriers() {
		try {
			List <Carrier> _tmpCarriers = AdministradorListas.obtenerLista( "com.lotrading.softlot.util.lists.CarrierList").getElements( "faces");
			carriers = new ArrayList<SelectItem>();
			for(Carrier _tmpItem : _tmpCarriers){
				carriers.add(new SelectItem(new Integer(_tmpItem.getCarrierId()), _tmpItem.getName()));
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to retrieve the Carrier List.\n" + e.getMessage());
		}
		return carriers;
	}

	public void setCarriers(List<SelectItem> carriers) {
		this.carriers = carriers;
	}

	public ClientRate getClientRateOtherCharge() {
		return clientRateOtherCharge;
	}

	public void setClientRateOtherCharge(ClientRate clientRateOtherCharge) {
		this.clientRateOtherCharge = clientRateOtherCharge;
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
		Collections.sort(otherChargesList, new AlphabeticComparator());
		return otherChargesList;
	}

	public void setOtherChargesList(List<SelectItem> otherChargesList) {
		this.otherChargesList = otherChargesList;
	}

	public ClientRatesPort getClientRatePort() {
		return clientRatePort;
	}

	public void setClientRatePort(ClientRatesPort clientRatePort) {
		this.clientRatePort = clientRatePort;
	}

	public ClientRate getClientRate() {
		return clientRate;
	}

	public void setClientRate(ClientRate clientRate) {
		this.clientRate = clientRate;
	}
	
	public IcarrierLogic getCarrierLogic() {
		return carrierLogic;
	}

	public void setCarrierLogic(IcarrierLogic carrierLogic) {
		selectPartnerAction();
		this.carrierLogic = carrierLogic;
	}

	public List<ClientRatesPort> getAirRatesList() {
		return airRatesList;
	}

	public void setAirRatesList(List<ClientRatesPort> airRatesList) {
		this.airRatesList = airRatesList;
	}

	public List<ClientRatesPort> getOcean20RatesList() {
		return ocean20RatesList;
	}

	public void setOcean20RatesList(List<ClientRatesPort> ocean20RatesList) {
		this.ocean20RatesList = ocean20RatesList;
	}

	public List<ClientRatesPort> getOcean40RatesList() {
		return ocean40RatesList;
	}

	public void setOcean40RatesList(List<ClientRatesPort> ocean40RatesList) {
		this.ocean40RatesList = ocean40RatesList;
	}

	public List<ClientRatesPort> getOceanLCLRatesList() {
		return oceanLCLRatesList;
	}

	public void setOceanLCLRatesList(List<ClientRatesPort> oceanLCLRatesList) {
		this.oceanLCLRatesList = oceanLCLRatesList;
	}

	public ClientRatesPort getClientAirRatePortFilter() {
		return clientAirRatePortFilter;
	}

	public void setClientAirRatePortFilter(ClientRatesPort clientAirRatePortFilter) {
		this.clientAirRatePortFilter = clientAirRatePortFilter;
	}

	public ClientRatesPort getClientOceanLCLRatePortFilter() {
		return clientOceanLCLRatePortFilter;
	}

	public void setClientOceanLCLRatePortFilter(
			ClientRatesPort clientOceanLCLRatePortFilter) {
		this.clientOceanLCLRatePortFilter = clientOceanLCLRatePortFilter;
	}

	public ClientRatesPort getClientOcean20RatePortFilter() {
		return clientOcean20RatePortFilter;
	}

	public void setClientOcean20RatePortFilter(
			ClientRatesPort clientOcean20RatePortFilter) {
		this.clientOcean20RatePortFilter = clientOcean20RatePortFilter;
	}

	public ClientRatesPort getClientOcean40RatePortFilter() {
		return clientOcean40RatePortFilter;
	}

	public void setClientOcean40RatePortFilter(
			ClientRatesPort clientOcean40RatePortFilter) {
		this.clientOcean40RatePortFilter = clientOcean40RatePortFilter;
	}

	public HtmlDataTable getAirRatesDataTable() {
		return airRatesDataTable;
	}

	public void setAirRatesDataTable(HtmlDataTable airRatesDataTable) {
		this.airRatesDataTable = airRatesDataTable;
	}

	public HtmlDataTable getLclRatesDataTable() {
		return lclRatesDataTable;
	}

	public void setLclRatesDataTable(HtmlDataTable lclRatesDataTable) {
		this.lclRatesDataTable = lclRatesDataTable;
	}

	public HtmlDataTable getOcean20RatesDataTable() {
		return ocean20RatesDataTable;
	}

	public void setOcean20RatesDataTable(HtmlDataTable ocean20RatesDataTable) {
		this.ocean20RatesDataTable = ocean20RatesDataTable;
	}

	public HtmlDataTable getOcean40RatesDataTable() {
		return ocean40RatesDataTable;
	}

	public void setOcean40RatesDataTable(HtmlDataTable ocean40RatesDataTable) {
		this.ocean40RatesDataTable = ocean40RatesDataTable;
	}

	public boolean isSearchAndInspConsentVisible() {
		if (actionsList != null) {
			for (Resource _tmpResource : actionsList) {
				if (_tmpResource.getName().equalsIgnoreCase("SEARCH & INSP. CONSENT")) {
					setSearchAndInspConsentVisible(true);
					break;
				}
			}
		}
		return searchAndInspConsentVisible;
	}

	public void setSearchAndInspConsentVisible(boolean searchAndInspConsentVisible) {
		this.searchAndInspConsentVisible = searchAndInspConsentVisible;
	}

}
