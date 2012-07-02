package com.lotrading.softlot.setup.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:51 a.m.
 */
public class Carrier {

	private int carrierId;
	private String name;
	private String notes;
	private String lotAccount;
	private String carrierCode;
	private MasterValue carrierType;
	private String iataCode;
	private boolean allowChildrenAWB;
	private boolean showAwbBlNumbersTab;
	private List<CarrierPorts> carrierAirRates;
	private List<CarrierPorts> carrierOceanRates20;
	private List<CarrierPorts> carrierOceanRates40;
	private List<CarrierPorts> carrierOceanRatesLCL;
	private List<CarrierAwbNumber> carrierAwbNumbers;
	private Date enteredDate;
	private Date disabledDate;

	public Carrier(){
		carrierType = new MasterValue();
		carrierAirRates = new ArrayList<CarrierPorts>();
		carrierOceanRates20 = new ArrayList<CarrierPorts>();
		carrierOceanRates40 = new ArrayList<CarrierPorts>();
		carrierOceanRatesLCL = new ArrayList<CarrierPorts>();
		carrierAwbNumbers = new ArrayList<CarrierAwbNumber>();
	}

	public int getCarrierId(){
		return carrierId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierId(int newVal){
		carrierId = newVal;
	}

	public String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}



	public String getNotes(){
		return notes;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNotes(String newVal){
		notes = newVal;
	}


	public String getLotAccount(){
		return lotAccount;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLotAccount(String newVal){
		lotAccount = newVal;
	}

	public String getCarrierCode(){
		return carrierCode;
	}

	public MasterValue getCarrierType(){
		return carrierType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierCode(String newVal){
		carrierCode = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierType(MasterValue newVal){
		carrierType = newVal;
	}

	public String getIataCode(){
		return iataCode;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setIataCode(String newVal){
		iataCode = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	
	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
	}

	public boolean isAllowChildrenAWB() {
		return allowChildrenAWB;
	}

	public void setAllowChildrenAWB(boolean allowChildrenAWB) {
		this.allowChildrenAWB = allowChildrenAWB;
	}

	public List<CarrierPorts> getCarrierAirRates() {
		return carrierAirRates;
	}

	public void setCarrierAirRates(List<CarrierPorts> carrierAirRates) {
		this.carrierAirRates = carrierAirRates;
	}

	public List<CarrierAwbNumber> getCarrierAwbNumbers() {
		return carrierAwbNumbers;
	}

	public void setCarrierAwbNumbers(List<CarrierAwbNumber> carrierAwbNumbers) {
		this.carrierAwbNumbers = carrierAwbNumbers;
	}

	public List<CarrierPorts> getCarrierOceanRates20() {
		return carrierOceanRates20;
	}

	public void setCarrierOceanRates20(List<CarrierPorts> carrierOceanRates20) {
		this.carrierOceanRates20 = carrierOceanRates20;
	}

	public List<CarrierPorts> getCarrierOceanRates40() {
		return carrierOceanRates40;
	}

	public void setCarrierOceanRates40(List<CarrierPorts> carrierOceanRates40) {
		this.carrierOceanRates40 = carrierOceanRates40;
	}

	public List<CarrierPorts> getCarrierOceanRatesLCL() {
		return carrierOceanRatesLCL;
	}

	public void setCarrierOceanRatesLCL(List<CarrierPorts> carrierOceanRatesLCL) {
		this.carrierOceanRatesLCL = carrierOceanRatesLCL;
	}

	public boolean isShowAwbBlNumbersTab() {
		return showAwbBlNumbersTab;
	}

	public void setShowAwbBlNumbersTab(boolean showAwbBlNumbersTab) {
		this.showAwbBlNumbersTab = showAwbBlNumbersTab;
	}
}