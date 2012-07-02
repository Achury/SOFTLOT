package com.lotrading.softlot.LODepartment.clientOrder.entity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.security.entity.Employee;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrder {

	private int clientOrderId;
	private String clientOrderNo;
	private MasterValue region;
	private Partner supplier;
	private Partner client;
	private MasterValue status;
	private String numberPO;
	private String invoiceId;
	private String description;
	private City destinationCity;
	/**
	 * Contiene una lista de Objetos ClientOrderSupplierInfo
	 */
	private List<ClientOrderSupplierInfo> supplierInfoList;
	/**
	 * por el momento solo el id porque no he creado la clases carrier
	 */
	private int carrierId;
	/**
	 * solo por el momento el id porque no he creado la entidad awb
	 */
	private String awbNumber;
	private String awb;
	/**
	 * solo por el momento el id porque no he creado la entidad bl
	 */
	private String blNumber;
	private String bl;
	private MasterValue shippingType;
	private double weightKilograms;
	private double weightVolumen;
	private int numPieces;
	/**
	 * Contiene una lista de objetos de tipo ClientOrderInlandCS
	 */
	private List<ClientOrderInlandCS> inlandCostSaleList;
	private String comodity;
	private MasterValue incoterm;
	private City incotermCity;
	private ShipPickUp pickupFrom;
	private ShipPickUp shipTo;
	private double volumeCubicMeter;
	//TODO: private WhReceipt whReceipt;
	private Employee salesRep;
	private String remarks;
	private List<CallHistoryClientOrder> supplierCallsHistory;
	private List<CallHistoryClientOrder> clientCallsHistory;
	private Date scheduledPickupDate;
	private Date pickupDate;
	private Date whArrivalDate;
	private Date shippingDate;
	private Date eta;
	private Date createdDate;
	private boolean inlandSaleZero;
	
	/**
	 * Estos dos atributos son utilizados para filtrar por fechas en el clientOrder Search.
	 */
	private Date dateFromFilter;
	private Date dateToFilter;
	/**
	 * Estos dos atributos son para filtrar por recogidos y por cancelados
	 * */
	private boolean showShipped;
	private boolean showCancelled;
	private String etaDateLess;

	public ClientOrder(){
		client = new Partner();
		supplier = new Partner();
		status = new MasterValue();
		destinationCity = new City();
		salesRep = new Employee();
		dateFromFilter = null;
		dateToFilter = null;
		inlandSaleZero = false;
		shippingType = new MasterValue();
		incoterm = new MasterValue();
		incotermCity = new City();
		pickupFrom = new ShipPickUp();
		shipTo = new ShipPickUp();
		region = new MasterValue();
		supplierInfoList = new ArrayList<ClientOrderSupplierInfo>();
		inlandCostSaleList = new ArrayList<ClientOrderInlandCS>();
		supplierCallsHistory = new ArrayList<CallHistoryClientOrder>();
		clientCallsHistory = new ArrayList<CallHistoryClientOrder>();
	}

	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public Partner getSupplier() {
		return supplier;
	}

	public void setSupplier(Partner supplier) {
		this.supplier = supplier;
	}

	public Partner getClient() {
		return client;
	}

	public void setClient(Partner client) {
		this.client = client;
	}

	public MasterValue getStatus() {
		return status;
	}

	public void setStatus(MasterValue status) {
		this.status = status;
	}

	public String getNumberPO() {
		return numberPO;
	}

	public void setNumberPO(String numberPO) {
		this.numberPO = numberPO;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(City destinationCity) {
		this.destinationCity = destinationCity;
	}

	public List<ClientOrderSupplierInfo> getSupplierInfoList() {
		return supplierInfoList;
	}

	public void setSupplierInfoList(List<ClientOrderSupplierInfo> supplierInfoList) {
		this.supplierInfoList = supplierInfoList;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public String getAwb() {
		return awb;
	}

	public void setAwb(String awb) {
		this.awb = awb;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public MasterValue getShippingType() {
		return shippingType;
	}

	public void setShippingType(MasterValue shippingType) {
		this.shippingType = shippingType;
	}

	public double getWeightKilograms() {
		return weightKilograms;
	}

	public void setWeightKilograms(double weightKilograms) {
		this.weightKilograms = weightKilograms;
	}

	public double getWeightVolumen() {
		return weightVolumen;
	}

	public void setWeightVolumen(double weightVolumen) {
		this.weightVolumen = weightVolumen;
	}

	public int getNumPieces() {
		return numPieces;
	}

	public void setNumPieces(int numPieces) {
		this.numPieces = numPieces;
	}

	public List<ClientOrderInlandCS> getInlandCostSaleList() {
		return inlandCostSaleList;
	}

	public void setInlandCostSaleList(List<ClientOrderInlandCS> inlandCostSaleList) {
		this.inlandCostSaleList = inlandCostSaleList;
	}

	public String getComodity() {
		return comodity;
	}

	public void setComodity(String comodity) {
		this.comodity = comodity;
	}

	public MasterValue getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(MasterValue incoterm) {
		this.incoterm = incoterm;
	}

	public City getIncotermCity() {
		return incotermCity;
	}

	public void setIncotermCity(City incotermCity) {
		this.incotermCity = incotermCity;
	}

	public ShipPickUp getPickupFrom() {
		return pickupFrom;
	}

	public void setPickupFrom(ShipPickUp pickupFrom) {
		this.pickupFrom = pickupFrom;
	}

	public ShipPickUp getShipTo() {
		return shipTo;
	}

	public void setShipTo(ShipPickUp shipTo) {
		this.shipTo = shipTo;
	}

	public double getVolumeCubicMeter() {
		return volumeCubicMeter;
	}

	public void setVolumeCubicMeter(double volumeCubicMeter) {
		this.volumeCubicMeter = volumeCubicMeter;
	}

	public Employee getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(Employee salesRep) {
		this.salesRep = salesRep;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CallHistoryClientOrder> getSupplierCallsHistory() {
		return supplierCallsHistory;
	}

	public void setSupplierCallsHistory(List<CallHistoryClientOrder> supplierCallsHistory) {
		this.supplierCallsHistory = supplierCallsHistory;
	}

	public List<CallHistoryClientOrder> getClientCallsHistory() {
		return clientCallsHistory;
	}

	public void setClientCallsHistory(List<CallHistoryClientOrder> clientCallsHistory) {
		this.clientCallsHistory = clientCallsHistory;
	}

	public Date getScheduledPickupDate() {
		return scheduledPickupDate;
	}

	public void setScheduledPickupDate(Date scheduledPickupDate) {
		this.scheduledPickupDate = scheduledPickupDate;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getWhArrivalDate() {
		return whArrivalDate;
	}

	public void setWhArrivalDate(Date whArrivalDate) {
		this.whArrivalDate = whArrivalDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAwbNumber() {
		return awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public Date getDateFromFilter() {
		return dateFromFilter;
	}

	public void setDateFromFilter(Date dateFromFilter) {
		this.dateFromFilter = dateFromFilter;
	}

	public Date getDateToFilter() {
		return dateToFilter;
	}

	public void setDateToFilter(Date dateToFilter) {
		this.dateToFilter = dateToFilter;
	}

	public boolean isShowShipped() {
		return showShipped;
	}

	public void setShowShipped(boolean showShipped) {
		this.showShipped = showShipped;
	}

	public boolean isShowCancelled() {
		return showCancelled;
	}

	public void setShowCancelled(boolean showCancelled) {
		this.showCancelled = showCancelled;
	}

	public String getClientOrderNo() {
		return clientOrderNo;
	}

	public void setClientOrderNo(String clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}

	public MasterValue getRegion() {
		return region;
	}

	public void setRegion(MasterValue region) {
		this.region = region;
	}

	public boolean isInlandSaleZero() {
		return inlandSaleZero;
	}

	public void setInlandSaleZero(boolean inlandSaleZero) {
		this.inlandSaleZero = inlandSaleZero;
	}

	public String getEtaDateLess() {
		if(getEta() != null){
			Calendar now = Calendar.getInstance();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);	
			Date today = now.getTime();
			if(!eta.after(today) && !eta.before(today)){
				setEtaDateLess("none");
			}else if(eta.after(today)) {
				setEtaDateLess("none");
			}else{
				setEtaDateLess("#ff9900");
			}
		}
		return etaDateLess;
	}

	public void setEtaDateLess(String etaDateLess) {
		this.etaDateLess = etaDateLess;
	}
}