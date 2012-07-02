package com.lotrading.softlot.LODepartment.shipments.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.util.base.Constants;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class Bl {

	private int blId;
	private MasterValue region;	
	private String blNumber;
	private boolean completed;
	private Date createdDate;
	private MasterValue blType;
	private MasterValue blShippingType;
	private Partner supplier;
	private ShipPickUp pickupFrom;
	private Partner client;
	private ShipPickUp shipTo;
	private Employee salesRep;
	private String clientPoNumber;
	private Carrier carrier;
	private String notifyParty;
	private City placeOfReceipt;
	private Port portOrigin;
	private Port portDestination;
	private String booking;
	private String fullBlNumber;
	private String remarks;
	private String whRemarks;
	private boolean shipmentNotif;
	private boolean arrivalNotif;
	private boolean docsDelNotif;
	private boolean shipDelNotif;
	private boolean poeNotif;	
	private Bl blMaster;
	private double totalCost;	
	private double totalSale;
	private double margin;
	private String vessel_voyage;
	private double declaredValue;
	private City placeOfDelivery;
	private String pierOfOrigin;
	private String additionalNumbers;
	private String exportInstructions;
	private Date etd;
	private Date eta;
	private String saidToContain;
	private boolean lockCosts;
	private boolean nonDeclaredValue;
	private boolean rated;
	private boolean noEEI;
	private List<BlItem> blItems;
	private List<BlPalletizedItem> blPalletizedItems;
	private List<BlFreightInvoice> blFreightInvoices;	
	private List<BlInlandCS> blInlandCS;
	private List<BlOtherInvoice> blOtherInvoices;
	private List<BlCostSale> blCostsSales;
	private List<BlCostSale> blOtherCostsSales;
	private List<BlEEI> blEEIList;	
	private List<BlUnCode> blUnCodes;
	private List<BlContainer> blContainers;
	private List<BLDeclaredValue> blDeclaredValues;
	private String blFreightInvoice;
	private String blOtherInvoice;
	private MasterValue paymentType;
	
	
	private Date dateFromFilter;
	private Date dateToFilter;
	private String whNumber;
	private int totalPieces;
	private int totalContainers;
	private double totalRealWLb;
	private double totalRealWKg;
	private double totalRealWTon;
	private double totalOceanVolF3;
	private double totalOceanVolM3;
	private boolean containHazardousItems;
	private List<Bl> blHouses;
	private boolean selected;
	private boolean validEffectiveDate;
	private String concatenatedFreightInvoices;
	private String concatenatedFreightInvoices2; //para mostrar las invoices concatenadas en el search, porque concatenatedFreightInvoices en el set recorre un vector.
	private String concatenatedMerchandInvoices;
	private String concatenatedEEICodes;
	private MasterValue typeOfMove;
	private BlContainer blMasterContainer;
	private boolean printDraft;

	public Bl(){
		region = new MasterValue();
		blType  = new MasterValue();
		blShippingType  = new MasterValue();
		supplier = new Partner();
		pickupFrom = new ShipPickUp();
		client = new Partner();
		shipTo= new ShipPickUp();
		salesRep = new Employee();
		carrier = new Carrier();		
		placeOfReceipt = new City();
		placeOfDelivery = new City();
		portOrigin = new Port();
		portDestination = new Port();		
		blHouses = new ArrayList<Bl>();
		typeOfMove = new MasterValue();
		paymentType = new MasterValue();
		blPalletizedItems = new ArrayList<BlPalletizedItem>();
		blMasterContainer = new BlContainer();
	}

	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}

	public MasterValue getRegion() {
		return region;
	}

	public void setRegion(MasterValue region) {
		this.region = region;
	}

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MasterValue getBlType() {
		return blType;
	}

	public void setBlType(MasterValue blType) {
		this.blType = blType;
	}

	public MasterValue getBlShippingType() {
		return blShippingType;
	}

	public void setBlShippingType(MasterValue blShippingType) {
		this.blShippingType = blShippingType;
	}

	public Partner getSupplier() {
		return supplier;
	}

	public void setSupplier(Partner supplier) {
		this.supplier = supplier;
	}

	public ShipPickUp getPickupFrom() {
		return pickupFrom;
	}

	public void setPickupFrom(ShipPickUp pickupFrom) {
		this.pickupFrom = pickupFrom;
	}

	public Partner getClient() {
		return client;
	}

	public void setClient(Partner client) {
		this.client = client;
	}

	public ShipPickUp getShipTo() {
		return shipTo;
	}

	public void setShipTo(ShipPickUp shipTo) {
		this.shipTo = shipTo;
	}

	public Employee getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(Employee salesRep) {
		this.salesRep = salesRep;
	}

	public String getClientPoNumber() {
		return clientPoNumber;
	}

	public void setClientPoNumber(String clientPoNumber) {
		this.clientPoNumber = clientPoNumber;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public String getNotifyParty() {
		return notifyParty;
	}

	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}

	public City getPlaceOfReceipt() {
		return placeOfReceipt;
	}

	public void setPlaceOfReceipt(City placeOfReceipt) {
		this.placeOfReceipt = placeOfReceipt;
	}

	public Port getPortOrigin() {
		return portOrigin;
	}

	public void setPortOrigin(Port portOrigin) {
		this.portOrigin = portOrigin;
	}

	public Port getPortDestination() {
		return portDestination;
	}

	public void setPortDestination(Port portDestination) {
		this.portDestination = portDestination;
	}

	public String getBooking() {
		return booking;
	}

	public void setBooking(String booking) {
		this.booking = booking;
	}

	public String getFullBlNumber() {
		return fullBlNumber;
	}

	public void setFullBlNumber(String fullBlNumber) {
		this.fullBlNumber = fullBlNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWhRemarks() {
		return whRemarks;
	}

	public void setWhRemarks(String whRemarks) {
		this.whRemarks = whRemarks;
	}

	public boolean isShipmentNotif() {
		return shipmentNotif;
	}

	public void setShipmentNotif(boolean shipmentNotif) {
		this.shipmentNotif = shipmentNotif;
	}

	public boolean isArrivalNotif() {
		return arrivalNotif;
	}

	public void setArrivalNotif(boolean arrivalNotif) {
		this.arrivalNotif = arrivalNotif;
	}

	public boolean isDocsDelNotif() {
		return docsDelNotif;
	}

	public void setDocsDelNotif(boolean docsDelNotif) {
		this.docsDelNotif = docsDelNotif;
	}

	public boolean isShipDelNotif() {
		return shipDelNotif;
	}

	public void setShipDelNotif(boolean shipDelNotif) {
		this.shipDelNotif = shipDelNotif;
	}

	public boolean isPoeNotif() {
		return poeNotif;
	}

	public void setPoeNotif(boolean poeNotif) {
		this.poeNotif = poeNotif;
	}

	public Bl getBlMaster() {
		return blMaster;
	}

	public void setBlMaster(Bl blMaster) {
		this.blMaster = blMaster;
	}
	
	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(double totalSale) {
		this.totalSale = totalSale;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public String getVessel_voyage() {
		return vessel_voyage;
	}

	public void setVessel_voyage(String vessel_voyage) {
		this.vessel_voyage = vessel_voyage;
	}

	public double getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(double declaredValue) {
		this.declaredValue = declaredValue;
	}

	public City getPlaceOfDelivery() {
		return placeOfDelivery;
	}

	public void setPlaceOfDelivery(City placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}

	public String getPierOfOrigin() {
		return pierOfOrigin;
	}

	public void setPierOfOrigin(String pierOfOrigin) {
		this.pierOfOrigin = pierOfOrigin;
	}

	public String getAdditionalNumbers() {
		return additionalNumbers;
	}

	public void setAdditionalNumbers(String additionalNumbers) {
		this.additionalNumbers = additionalNumbers;
	}

	public String getExportInstructions() {
		return exportInstructions;
	}

	public void setExportInstructions(String exportInstructions) {
		this.exportInstructions = exportInstructions;
	}

	public Date getEtd() {
		return etd;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public String getSaidToContain() {
		return saidToContain;
	}

	public void setSaidToContain(String saidToContain) {
		this.saidToContain = saidToContain;
	}

	public boolean isLockCosts() {
		return lockCosts;
	}

	public void setLockCosts(boolean lockCosts) {
		this.lockCosts = lockCosts;
	}
	
	public boolean isNonDeclaredValue(){
		return nonDeclaredValue;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNonDeclaredValue(boolean newVal){
		nonDeclaredValue = newVal;
	}

	public boolean isRated(){
		return rated;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRated(boolean newVal){
		rated = newVal;
	}

	public boolean isNoEEI(){
		return noEEI;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNoEEI(boolean newVal){
		noEEI = newVal;
	}

	public List<BlItem> getBlItems() {
		return blItems;
	}

	public void setBlItems(List<BlItem> blItems) {
		this.blItems = blItems;
	}

	public List<BlPalletizedItem> getBlPalletizedItems() {
		return blPalletizedItems;
	}

	public void setBlPalletizedItems(List<BlPalletizedItem> blPalletizedItems) {
		this.blPalletizedItems = blPalletizedItems;
	}

	public List<BlFreightInvoice> getBlFreightInvoices() {
		return blFreightInvoices;
	}

	public void setBlFreightInvoices(List<BlFreightInvoice> blFreightInvoices) {
		this.blFreightInvoices = blFreightInvoices;
	}

	public String getBlFreightInvoice() {
		return blFreightInvoice;
	}

	public void setBlFreightInvoice(String blFreightInvoice) {
		this.blFreightInvoice = blFreightInvoice;
	}

	public List<BlInlandCS> getBlInlandCS() {
		return blInlandCS;
	}

	public void setBlInlandCS(List<BlInlandCS> blInlandCS) {
		this.blInlandCS = blInlandCS;
	}

	public List<BlOtherInvoice> getBlOtherInvoices() {
		return blOtherInvoices;
	}

	public void setBlOtherInvoices(List<BlOtherInvoice> blOtherInvoices) {
		this.blOtherInvoices = blOtherInvoices;
	}

	public List<BlCostSale> getBlCostsSales() {
		return blCostsSales;
	}

	public void setBlCostsSales(List<BlCostSale> blCostsSales) {
		this.blCostsSales = blCostsSales;
	}
	
	public List<BlCostSale> getBlOtherCostsSales() {
		return blOtherCostsSales;
	}

	public void setBlOtherCostsSales(List<BlCostSale> bLOtherCostsSales) {
		blOtherCostsSales = bLOtherCostsSales;
	}

	public List<BlEEI> getBlEEIList() {
		return blEEIList;
	}

	public void setBlEEIList(List<BlEEI> blEEIList) {
		this.blEEIList = blEEIList;
	}

	public List<BlUnCode> getBlUnCodes() {
		return blUnCodes;
	}

	public void setBlUnCodes(List<BlUnCode> blUnCodes) {
		this.blUnCodes = blUnCodes;
	}
	
	public List<BlContainer> getBlContainers() {
		return blContainers;
	}

	public void setBlContainers(List<BlContainer> blContainers) {
		this.blContainers = blContainers;
	}

	

	public List<BLDeclaredValue> getBlDeclaredValues() {
		return blDeclaredValues;
	}

	public void setBlDeclaredValues(List<BLDeclaredValue> blDeclaredValues) {
		this.blDeclaredValues = blDeclaredValues;
	}

	public String getBlOtherInvoice() {
		return blOtherInvoice;
	}

	public void setBlOtherInvoice(String blOtherInvoice) {
		this.blOtherInvoice = blOtherInvoice;
	}

	public MasterValue getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(MasterValue paymentType) {
		this.paymentType = paymentType;
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

	public String getWhNumber() {
		return whNumber;
	}

	public void setWhNumber(String whNumber) {
		this.whNumber = whNumber;
	}

	public int getTotalPieces() {		
		return totalPieces;
	}

	public void setTotalPieces(int totalPieces) {
		this.totalPieces = totalPieces;
	}

	public int getTotalContainers() {			
		return totalContainers;
	}

	public void setTotalContainers(int totalContainers) {
		this.totalContainers = totalContainers;
	}

	public double getTotalRealWLb() {
		return totalRealWLb;
	}

	public void setTotalRealWLb(double totalRealWLb) {
		this.totalRealWLb = totalRealWLb;
	}

	public double getTotalRealWKg() {
		return totalRealWKg;
	}

	public void setTotalRealWKg(double totalRealWKg) {
		this.totalRealWKg = totalRealWKg;
	}

	public double getTotalRealWTon() {
		return totalRealWTon;
	}

	public void setTotalRealWTon(double totalRealWTon) {
		this.totalRealWTon = totalRealWTon;
	}

	public double getTotalOceanVolF3() {
		return totalOceanVolF3;
	}

	public void setTotalOceanVolF3(double totalOceanVolF3) {
		this.totalOceanVolF3 = totalOceanVolF3;
	}

	public double getTotalOceanVolM3() {
		return totalOceanVolM3;
	}

	public void setTotalOceanVolM3(double totalOceanVolM3) {
		this.totalOceanVolM3 = totalOceanVolM3;
	}

	public boolean isContainHazardousItems() {
		if(getBlItems() != null){
			for(BlItem item : getBlItems()){
				if(item.isHazardous()){
					return true;
				}
			}
		}
		return false;
	}

	public void setContainHazardousItems(boolean containHazardousItems) {
		this.containHazardousItems = containHazardousItems;
	}
	
	public List<Bl> getBlHouses() {
		return blHouses;
	}

	public void setBlHouses(List<Bl> blHouses) {
		this.blHouses = blHouses;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isValidEffectiveDate() {
		return validEffectiveDate;
	}

	public void setValidEffectiveDate(boolean validEffectiveDate) {
		this.validEffectiveDate = validEffectiveDate;
	}
	
	public String getConcatenatedFreightInvoices() {
		concatenatedFreightInvoices = "";
		if (getBlFreightInvoices() != null) {
			for (BlFreightInvoice _tmpFreightInv : getBlFreightInvoices()) {
				if (_tmpFreightInv.getFreightInvoiceId() > 0) {
					concatenatedFreightInvoices = concatenatedFreightInvoices+ _tmpFreightInv.getInvoice().getInvoiceNumber()+ ",";
				}
			}if(concatenatedFreightInvoices.length() > 0){
				concatenatedFreightInvoices = concatenatedFreightInvoices.substring(0, concatenatedFreightInvoices.length()-1);
			}
			
		}
		return concatenatedFreightInvoices;
	}

	public void setConcatenatedFreightInvoices(
			String concatenatedFreightInvoices) {
		this.concatenatedFreightInvoices = concatenatedFreightInvoices;
	}

	public String getConcatenatedFreightInvoices2() {
		return concatenatedFreightInvoices2;
	}

	public void setConcatenatedFreightInvoices2(String concatenatedFreightInvoices2) {
		this.concatenatedFreightInvoices2 = concatenatedFreightInvoices2;
	}

	public String getConcatenatedMerchandInvoices() {
		concatenatedMerchandInvoices = "";
		if (getBlOtherInvoices() != null) {
			for (BlOtherInvoice _tmpOtherInv :getBlOtherInvoices()) {
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

	public String getConcatenatedEEICodes() {
		concatenatedEEICodes = "";
		if (getBlEEIList() != null) {
			for (BlEEI _tmpEEICode :getBlEEIList()) {
				if (!_tmpEEICode.isEmpty()) {
					concatenatedEEICodes +=  _tmpEEICode.getEei() + ",";
				}
			}if(concatenatedEEICodes.length() > 0){
				concatenatedEEICodes = concatenatedEEICodes.substring(0, concatenatedEEICodes.length()-1);
			}
			
		}
		return concatenatedEEICodes;
	}

	public void setConcatenatedEEICodes(String concatenatedEEICodes) {
		this.concatenatedEEICodes = concatenatedEEICodes;
	}

	public MasterValue getTypeOfMove() {
		return typeOfMove;
	}

	public void setTypeOfMove(MasterValue typeOfMove) {
		this.typeOfMove = typeOfMove;
	}

	public BlContainer getBlMasterContainer() {
		return blMasterContainer;
	}

	public void setBlMasterContainer(BlContainer blMasterContainer) {
		this.blMasterContainer = blMasterContainer;
	}

	public boolean isPrintDraft() {
		return printDraft;
	}

	public void setPrintDraft(boolean printDraft) {
		this.printDraft = printDraft;
	}

	public boolean isFCL() {
		if (getBlShippingType().getValueId() != 0){
			if(getBlShippingType().getValueId() == Constants.MASTER_VALUE_BL_SHIPPING_TYPE_FCL ){
				return true;
			}
		}
		return false;
	}
	
	public boolean isLCL() {
		if (getBlShippingType().getValueId() != 0){
			if(getBlShippingType().getValueId() == Constants.MASTER_VALUE_BL_SHIPPING_TYPE_LCL ){
				return true;
			}
		}
		return false;
	}
	
	public boolean isPrepaid(){
		return (paymentType.getValueId() == Constants.MASTER_VALUE_PREPAID)?  true : false;
	}
	
	public boolean isCollect(){
		return (paymentType.getValueId() == Constants.MASTER_VALUE_COLLECT)?  true : false;
	}
	
	public boolean isRegular() {
		return this.getBlType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_REGULAR;
	}

	public boolean isMaster() {
		return this.getBlType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_MASTER;
	}

	public boolean isHouse() {
		return this.getBlType().getValueId() == Constants.MASTER_VALUE_SHIPMENT_TYPE_HOUSE;
	}
	
	public boolean isHouseLCL(){
		if (getBlHouses() != null){
			if (getBlHouses().size() >0){
				if(getBlHouses().get(0) != null){
					return getBlHouses().get(0).isLCL();
				}
			}
		}
		return false;
	}
	


}