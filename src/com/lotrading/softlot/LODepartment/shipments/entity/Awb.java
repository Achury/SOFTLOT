package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;
import java.util.List;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.UtilFunctions;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class Awb {

	private int awbId;
	private String awbNumber;
	private MasterValue awbType;
	private boolean completed;
	private Partner supplier;
	private ShipPickUp pickupFrom;
	private Partner client;
	private ShipPickUp shipTo;
	private Port airportOrigin;
	private Port airportDestination;
	private Carrier carrier;
	private MasterValue region;
	private String awbFullNumberExtern;
	private Awb awbMaster;
	private String description;
	private String clientPoNumber;
	private int carrierAgentId;
	private Employee salesRep;
	private double inlandFreightCost;
	private double airFreightCost;
	private double securityCost;
	private double fuelSurchargeCost;
	private double localCost;
	private double unCost;
	private double awbReleaseCost;
	private double transferToCustomWhCost;
	private double messengerServiceCost;
	private double airFreightSale;
	private double inlandfreightSale;
	private double securitySurchargeSale;
	private double fuelSurchargeSale;
	private double localDeliverySale;
	private double unSale;
	private double serviceFee;
	private double tieDown;
	private double awbReleaseSale;
	private double transferToCustomwhSale;
	private MasterValue paymentType;
	private boolean lockCosts;
	private String manifestNumber;
	private String booking;
	private String flightNumber;
	private Date arrivalDate;
	private String departureTime;
	private boolean nonDeclaredValue;
	private boolean rated;
	private boolean noEEI;
	private int pieces;
	private double totalWeightLbs;
	private double totalWeightVol;
	private double totalWeightKgs;
	private double totalWeightVolKgs;
	private double totalCosts;
	private double totalSales;
	private boolean shipmentNotification;
	private boolean arrivalNotification;
	private boolean docsDeliveryNotification;
	private boolean shipmentDeliveryNotification;
	private boolean poeNotification;
	private String saidToContain;
	private Date createdDate;
	private double dueAgent;
	private double dueCarrier;
	private List<AwbItem> awbItems;
	private List<AwbPalletizedItem> awbPalletizedItems;
	private List<AwbFreightInvoice> awbFreightInvoices;
	private List<AwbOtherInvoice> awbOtherInvoices;
	private List<AwbUnCode> awbUnCodes;
	private List<AwbCostSale> awbCostsSales;
	private List<AwbCostSale> awbOtherCostsSales;
	private List<AwbEEI> awbEEIList;
	private List<AwbInlandCS> awbInlandCS;
	private List<Awb> awbHouses;
	private List<AwbDeclaredValue> awbDeclaredValues;
	private double declaredValue;
	
	/**
	 * Estos dos atributos son utilizados para filtrar por fechas en el awb
	 * Search.
	 */
	private Date dateFromFilter;
	private Date dateToFilter;
	private boolean savedCorrectly;
	private boolean containDangerousItems;
	private boolean containOversizeItems;
	private boolean containRefrigeratedItems;
	private boolean selected;
	private int numUNCodes;
	private boolean validEffectiveDate;
	private List awbSummarizedItems;
	
	private String whRemarks;
	
	private String concatenatedFreightInvoices;
	private String concatenatedFreightInvoices2; //para mostrar las invoices concatenadas en el search, porque concatenatedFreightInvoices en el set recorre un vector.
	private String concatenatedMerchandInvoices;
	
	private String freightInvoice; 	/*Se utiliza para filtrar por freightInvoice en el awb search*/
	private String otherInvoice;	/*Se utiliza para filtrar por MerchandiseInvoice en el awb search*/
	private String whNumber;		/*Se utiliza para filtrar por Warehouse number en el awb search*/
	
	public Awb() {
		awbType = new MasterValue();
		supplier = new Partner();
		client = new Partner();
		pickupFrom = new ShipPickUp();
		shipTo = new ShipPickUp();
		airportOrigin = new Port();
		airportDestination = new Port();
		carrier = new Carrier();
		region = new MasterValue();
		salesRep = new Employee();
		paymentType = new MasterValue(Constants.MASTER_VALUE_PREPAID);
		validEffectiveDate = false;
	}

	public int getAwbId() {
		return awbId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbId(int newVal) {
		awbId = newVal;
	}

	public String getAwbNumber() {
		return awbNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbNumber(String newVal) {
		awbNumber = newVal;
	}

	public MasterValue getAwbType() {
		return awbType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbType(MasterValue newVal) {
		awbType = newVal;
	}

	public boolean isCompleted() {
		return completed;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCompleted(boolean newVal) {
		completed = newVal;
	}

	public ShipPickUp getPickupFrom() {
		return pickupFrom;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPickupFrom(ShipPickUp newVal) {
		pickupFrom = newVal;
	}

	public Partner getClient() {
		return client;
	}

	public Partner getSupplier() {
		return supplier;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClient(Partner newVal) {
		client = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSupplier(Partner newVal) {
		supplier = newVal;
	}

	public ShipPickUp getShipTo() {
		return shipTo;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setShipTo(ShipPickUp newVal) {
		shipTo = newVal;
	}

	public Port getAirportOrigin() {
		return airportOrigin;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAirportOrigin(Port newVal) {
		airportOrigin = newVal;
	}

	public Port getAirportDestination() {
		return airportDestination;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAirportDestination(Port newVal) {
		airportDestination = newVal;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrier(Carrier newVal) {
		carrier = newVal;
	}

	public String getAwbFullNumberExtern() {
		return awbFullNumberExtern;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbFullNumberExtern(String newVal) {
		awbFullNumberExtern = newVal;
	}

	

	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDescription(String newVal) {
		description = newVal;
	}

	public String getClientPoNumber() {
		return clientPoNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClientPoNumber(String newVal) {
		clientPoNumber = newVal;
	}

	public int getCarrierAgentId() {
		return carrierAgentId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierAgentId(int newVal) {
		carrierAgentId = newVal;
	}

	public double getInlandFreightCost() {
		return inlandFreightCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setInlandFreightCost(double newVal) {
		inlandFreightCost = newVal;
	}

	public double getAirFreightCost() {
		return airFreightCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAirFreightCost(double newVal) {
		airFreightCost = newVal;
	}

	public double getSecurityCost() {
		return securityCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSecurityCost(double newVal) {
		securityCost = newVal;
	}

	public double getFuelSurchargeCost() {
		return fuelSurchargeCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFuelSurchargeCost(double newVal) {
		fuelSurchargeCost = newVal;
	}

	public double getLocalCost() {
		return localCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLocalCost(double newVal) {
		localCost = newVal;
	}

	public double getUnCost() {
		return unCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUnCost(double newVal) {
		unCost = newVal;
	}

	public double getAwbReleaseCost() {
		return awbReleaseCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbReleaseCost(double newVal) {
		awbReleaseCost = newVal;
	}

	public double getTransferToCustomWhCost() {
		return transferToCustomWhCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTransferToCustomWhCost(double newVal) {
		transferToCustomWhCost = newVal;
	}

	public double getMessengerServiceCost() {
		return messengerServiceCost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMessengerServiceCost(double newVal) {
		messengerServiceCost = newVal;
	}

	public double getInlandfreightSale() {
		return inlandfreightSale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setInlandfreightSale(double newVal) {
		inlandfreightSale = newVal;
	}

	public double getSecuritySurchargeSale() {
		return securitySurchargeSale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSecuritySurchargeSale(double newVal) {
		securitySurchargeSale = newVal;
	}

	public double getFuelSurchargeSale() {
		return fuelSurchargeSale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFuelSurchargeSale(double newVal) {
		fuelSurchargeSale = newVal;
	}

	public double getLocalDeliverySale() {
		return localDeliverySale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLocalDeliverySale(double newVal) {
		localDeliverySale = newVal;
	}

	public double getUnSale() {
		return unSale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUnSale(double newVal) {
		unSale = newVal;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setServiceFee(double newVal) {
		serviceFee = newVal;
	}

	public double getTieDown() {
		return tieDown;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTieDown(double newVal) {
		tieDown = newVal;
	}

	public double getAwbReleaseSale() {
		return awbReleaseSale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbReleaseSale(double newVal) {
		awbReleaseSale = newVal;
	}

	public double getTransferToCustomwhSale() {
		return transferToCustomwhSale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTransferToCustomwhSale(double newVal) {
		transferToCustomwhSale = newVal;
	}

	public MasterValue getPaymentType() {
		return paymentType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPaymentType(MasterValue newVal) {
		paymentType = newVal;
	}

	public boolean isLockCosts() {
		return lockCosts;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLockCosts(boolean newVal) {
		lockCosts = newVal;
	}

	public String getManifestNumber() {
		return manifestNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setManifestNumber(String newVal) {
		manifestNumber = newVal;
	}

	public String getBooking() {
		return booking;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setBooking(String newVal) {
		booking = newVal;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFlightNumber(String newVal) {
		flightNumber = newVal;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setArrivalDate(Date newVal) {
		arrivalDate = newVal;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDepartureTime(String newVal) {
		departureTime = newVal;
	}

	public boolean isNonDeclaredValue() {
		return nonDeclaredValue;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNonDeclaredValue(boolean newVal) {
		nonDeclaredValue = newVal;
	}

	public boolean isRated() {
		return rated;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRated(boolean newVal) {
		rated = newVal;
	}

	public boolean isNoEEI() {
		return noEEI;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNoEEI(boolean newVal) {
		noEEI = newVal;
	}

	public int getPieces() {
		return pieces;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPieces(int newVal) {
		pieces = newVal;
	}

	public boolean isShipmentNotification() {
		return shipmentNotification;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setShipmentNotification(boolean newVal) {
		shipmentNotification = newVal;
	}

	public boolean isArrivalNotification() {
		return arrivalNotification;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setArrivalNotification(boolean newVal) {
		arrivalNotification = newVal;
	}

	public boolean isDocsDeliveryNotification() {
		return docsDeliveryNotification;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDocsDeliveryNotification(boolean newVal) {
		docsDeliveryNotification = newVal;
	}

	public boolean isShipmentDeliveryNotification() {
		return shipmentDeliveryNotification;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setShipmentDeliveryNotification(boolean newVal) {
		shipmentDeliveryNotification = newVal;
	}

	public boolean isPoeNotification() {
		return poeNotification;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPoeNotification(boolean newVal) {
		poeNotification = newVal;
	}

	public String getSaidToContain() {
		return saidToContain;
	}

	public void setSaidToContain(String saidToContain) {
		this.saidToContain = saidToContain;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreatedDate(Date newVal) {
		createdDate = newVal;
	}

	public List<AwbItem> getAwbItems() {
		return awbItems;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbItems(List<AwbItem> newVal) {
		awbItems = newVal;
	}

	public List<AwbFreightInvoice> getAwbFreightInvoices() {
		return awbFreightInvoices;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbFreightInvoices(List<AwbFreightInvoice> newVal) {
		awbFreightInvoices = newVal;
	}

	public List<AwbOtherInvoice> getAwbOtherInvoices() {
		return awbOtherInvoices;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbOtherInvoices(List<AwbOtherInvoice> newVal) {
		awbOtherInvoices = newVal;
	}

	public List<AwbUnCode> getAwbUnCodes() {
		return awbUnCodes;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbUnCodes(List<AwbUnCode> newVal) {
		awbUnCodes = newVal;
	}

	public double getAirFreightSale() {
		return airFreightSale;
	}

	public void setAirFreightSale(double airFreightSale) {
		this.airFreightSale = airFreightSale;
	}

	public MasterValue getRegion() {
		return region;
	}

	public void setRegion(MasterValue region) {
		this.region = region;
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

	public Employee getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(Employee salesRep) {
		this.salesRep = salesRep;
	}

	public Awb getAwbMaster() {
		return awbMaster;
	}

	public void setAwbMaster(Awb awbMaster) {
		this.awbMaster = awbMaster;
	}

	public List<AwbCostSale> getAwbCostsSales() {
		return awbCostsSales;
	}

	public void setAwbCostsSales(List<AwbCostSale> awbCostsSales) {
		this.awbCostsSales = awbCostsSales;
	}

	public boolean isSavedCorrectly() {
		return savedCorrectly;
	}

	public void setSavedCorrectly(boolean savedCorrectly) {
		this.savedCorrectly = savedCorrectly;
	}

	public List<AwbEEI> getAwbEEIList() {
		return awbEEIList;
	}

	public void setAwbEEIList(List<AwbEEI> awbEEIList) {
		this.awbEEIList = awbEEIList;
	}

	public double getTotalWeightLbs() {
		return UtilFunctions.roundDecimalPlaces(totalWeightLbs, 2);
	}

	public void setTotalWeightLbs(double totalWeightLbs) {
		this.totalWeightLbs = totalWeightLbs;
	}

	public double getTotalWeightVol() {
		return UtilFunctions.roundDecimalPlaces(totalWeightVol, 2);
	}

	public void setTotalWeightVol(double totalWeightVol) {
		this.totalWeightVol = totalWeightVol;
	}

	public double getTotalCosts() {
		return totalCosts;
	}

	public void setTotalCosts(double totalCosts) {
		this.totalCosts = totalCosts;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public List<AwbCostSale> getAwbOtherCostsSales() {
		return awbOtherCostsSales;
	}

	public void setAwbOtherCostsSales(List<AwbCostSale> awbOtherCostsSales) {
		this.awbOtherCostsSales = awbOtherCostsSales;
	}

	public List<AwbInlandCS> getAwbInlandCS() {
		return awbInlandCS;
	}

	public void setAwbInlandCS(List<AwbInlandCS> awbInlandCS) {
		this.awbInlandCS = awbInlandCS;
	}

	public List<Awb> getAwbHouses() {
		return awbHouses;
	}

	public void setAwbHouses(List<Awb> awbHouses) {
		this.awbHouses = awbHouses;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getNumUNCodes() {
		this.numUNCodes = 0;
		if (getAwbUnCodes() != null) {
			for (AwbUnCode _tmp : getAwbUnCodes()) {
				if (_tmp.getUnCodeId() > 0) {
					numUNCodes++;
				}
			}
		}
		return numUNCodes;
	}
	
	public void setNumUNCodes(int numUNCodes) {
		this.numUNCodes = numUNCodes;
	}

	public boolean isValidEffectiveDate() {
		return validEffectiveDate;
	}

	public void setValidEffectiveDate(boolean validEffectiveDate) {
		this.validEffectiveDate = validEffectiveDate;
	}

	public double getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(double declaredValue) {
		this.declaredValue = declaredValue;
	}

	public List<AwbDeclaredValue> getAwbDeclaredValues() {
		return awbDeclaredValues;
	}

	public void setAwbDeclaredValues(List<AwbDeclaredValue> awbDeclaredValues) {
		this.awbDeclaredValues = awbDeclaredValues;
	}

	public List getAwbSummarizedItems() {
		return awbSummarizedItems;
	}

	public void setAwbSummarizedItems(List awbSummarizedItems) {
		this.awbSummarizedItems = awbSummarizedItems;
	}

	public boolean isRegular() {
		return this.getAwbType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_REGULAR;
	}

	public boolean isMaster() {
		return this.getAwbType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_MASTER;
	}

	public boolean isHouse() {
		return this.getAwbType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE;
	}
	
	public void setConcatenatedFreightInvoices(
			String concatenatedFreightInvoices) {
		this.concatenatedFreightInvoices = concatenatedFreightInvoices;
	}
	
	public String getConcatenatedFreightInvoices() {
		concatenatedFreightInvoices = "";
		if (getAwbFreightInvoices() != null) {
			for (AwbFreightInvoice _tmpFreightInv : getAwbFreightInvoices()) {
				if (_tmpFreightInv.getFreightInvoiceId() > 0) {
					concatenatedFreightInvoices = concatenatedFreightInvoices+ _tmpFreightInv.getInvoice().getInvoiceNumber()+ ",";
				}
			}if(concatenatedFreightInvoices.length() > 0){
				concatenatedFreightInvoices = concatenatedFreightInvoices.substring(0, concatenatedFreightInvoices.length()-1);
			}
			
		}
		return concatenatedFreightInvoices;
	}
	
	public String getConcatenatedMerchandInvoices() {
		concatenatedMerchandInvoices = "";
		if (getAwbOtherInvoices() != null) {
			for (AwbOtherInvoice _tmpOtherInv : getAwbOtherInvoices()) {
				if (_tmpOtherInv.getOtherInvoiceId() > 0) {
					concatenatedMerchandInvoices = concatenatedMerchandInvoices+ _tmpOtherInv.getInvoice().getInvoiceNumber()+ ",";
				}
			}if(concatenatedMerchandInvoices.length() > 0){
				concatenatedMerchandInvoices = concatenatedMerchandInvoices.substring(0, concatenatedMerchandInvoices.length()-1);
			}
			
		}
		return concatenatedMerchandInvoices;
	}

	public void setConcatenatedMerchandInvoices(
			String concatenatedMerchandInvoices) {
		this.concatenatedMerchandInvoices = concatenatedMerchandInvoices;
	}

	public double getTotalWeightKgs() {
		totalWeightKgs = UtilFunctions.roundUpDecimalPlaces(totalWeightLbs / Constants.KILOGRAM_TO_POUNDS,0);
		return totalWeightKgs;
	}

	public void setTotalWeightKgs(double totalWeightKgs) {
		this.totalWeightKgs = totalWeightKgs;
	}

	public double getTotalWeightVolKgs() {
		totalWeightVolKgs = UtilFunctions.roundUpDecimalPlaces(totalWeightVol / Constants.KILOGRAM_TO_POUNDS,0);
		return totalWeightVolKgs;
	}

	public void setTotalWeightVolKgs(double totalWeightVolKgs) {
		this.totalWeightVolKgs = totalWeightVolKgs;
	}
	
	public String getConcatenatedWhReceipts() {
		String concatenatedWhReceipts = "";
		if (!this.isMaster()){
			for (AwbItem _tmpItem : this.getAwbItems()){
				if (_tmpItem.getWhReceipt().getWhReceiptNumber() != null){
					concatenatedWhReceipts += _tmpItem.getWhReceipt().getWhReceiptNumber() + ",";
				}
			}
			if(concatenatedWhReceipts.length() > 0){
				concatenatedWhReceipts = concatenatedWhReceipts.substring(0, concatenatedWhReceipts.length()-1);
			}
		}
		
		
		return concatenatedWhReceipts;
	}

	public boolean isContainOversizeItems() {
		return containOversizeItems;
	}

	public void setContainOversizeItems(boolean containOversizeItems) {
		this.containOversizeItems = containOversizeItems;
	}

	public boolean isContainRefrigeratedItems() {
		return containRefrigeratedItems;
	}

	public void setContainRefrigeratedItems(boolean containRefrigeratedItems) {
		this.containRefrigeratedItems = containRefrigeratedItems;
	}

	public boolean isContainDangerousItems() {
		return containDangerousItems;
	}

	public void setContainDangerousItems(boolean containDangerousItems) {
		this.containDangerousItems = containDangerousItems;
	}

	public double getDueAgent() {
		return UtilFunctions.roundDecimalPlaces(dueAgent, 2);
	}

	public void setDueAgent(double dueAgent) {
		this.dueAgent = dueAgent;
	}

	public double getDueCarrier() {
		return UtilFunctions.roundDecimalPlaces(dueCarrier, 2);
	}

	public void setDueCarrier(double dueCarrier) {
		this.dueCarrier = dueCarrier;
	}
	
	public boolean isPrepaid(){
		if(paymentType.getValueId() == Constants.MASTER_VALUE_PREPAID){
			return true;
		}
		return false;
	}

	public String getFreightInvoice() {
		return freightInvoice;
	}

	public void setFreightInvoice(String freightInvoice) {
		this.freightInvoice = freightInvoice;
	}

	public String getOtherInvoice() {
		return otherInvoice;
	}

	public void setOtherInvoice(String otherInvoice) {
		this.otherInvoice = otherInvoice;
	}

	public String getWhNumber() {
		return whNumber;
	}

	public void setWhNumber(String whNumber) {
		this.whNumber = whNumber;
	}

	public String getConcatenatedFreightInvoices2() {
		return concatenatedFreightInvoices2;
	}

	public void setConcatenatedFreightInvoices2(
			String concatenatedFreightInvoices2) {
		this.concatenatedFreightInvoices2 = concatenatedFreightInvoices2;
	}

	public String getWhRemarks() {
		return whRemarks;
	}

	public void setWhRemarks(String whRemarks) {
		this.whRemarks = whRemarks;
	}

	public List<AwbPalletizedItem> getAwbPalletizedItems() {
		return awbPalletizedItems;
	}

	public void setAwbPalletizedItems(List<AwbPalletizedItem> awbPalletizedItems) {
		this.awbPalletizedItems = awbPalletizedItems;
	}
}