package com.lotrading.softlot.businessPartners.entity;
import java.util.Date;
import java.util.List;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;

/**
 * Clase que se utiliza para encapsular los datos de los Clientes y Proveedores de
 * LO Trading
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 10:59:59 a.m.
 */
public class Partner {

	private int partnerId;
	private String name;
	private String CustomerNumber;
	private Address address;
	private String code;
	private Phone phone;
	private Phone fax;
	private MasterValue language;
	private MasterValue truckCompany; 
	private boolean informFinalDest;
	

	/**
	 * Campo para saber en que pestana de departamento estoy, para poder cargar los
	 * contactos y demas elementos que van separados por departamento
	 */
	private int departmentLotId;
	/**
	 * notas que saldran por defecto en la factura si  es un cliente
	 */
	private String invoiceNotes;
	private MasterValue status;
	private boolean client;
	private boolean supplier;
	private String notes;
	private MasterValue paymentTerm;
	/**
	 * numero que identifica al cliente o proveedor frente a su gobierno (NIT)
	 */
	private String taxId;
	private String website;
	private Employee employeeCreator;
	private Employee employeeUpdate;
	private Date enteredDate;
	private Date updatedDate;
	private String contactName;
	private List<CallHistory> callsHistory;
	private List<ShipPickUp> shipPickups;
	private PartnerDepartmentInfo departmentInfoLO;
	private PartnerDepartmentInfo departmentInfoIP;
	private PartnerDepartmentInfo departmentInfoRM;
	private boolean regUSA;
	private boolean regCOL;
	private boolean regGER;
	private List<ClientRatesPort> clientAirRates;
	private List<ClientRatesPort> clientOceanRates20;
	private List<ClientRatesPort> clientOceanRates40;
	private List<ClientRatesPort> clientOceanRatesLCL;
	
	private boolean searchAndInspConsent;
	private boolean validCode;

	public Partner(){
		validCode = true;
		status = new MasterValue();
		phone = new Phone();
		phone.setType(new MasterValue(Constants.MASTER_VALUE_PHONE_TYPE_TEL));
		fax = new Phone();
		fax.setType(new MasterValue(Constants.MASTER_VALUE_PHONE_TYPE_FAX));
		address = new Address();
		language  = new MasterValue();
		truckCompany = new MasterValue();
		paymentTerm = new MasterValue();
		employeeCreator = new Employee();
		employeeUpdate = new Employee();
		callsHistory = null;
		shipPickups = null;
		departmentInfoLO = new PartnerDepartmentInfo();
		departmentInfoIP = new PartnerDepartmentInfo();
		departmentInfoRM = new PartnerDepartmentInfo();
	}


	public Partner(int partnerId){
		this.partnerId = partnerId;
	}


	public int getPartnerId(){
		return partnerId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPartnerId(int newVal){
		partnerId = newVal;
	}

	public java.lang.String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(java.lang.String newVal){
		name = newVal;
	}

	public java.lang.String getCode(){
		return code;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCode(java.lang.String newVal){
		code = newVal;
	}

	public java.lang.String getWebsite(){
		return website;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setWebsite(java.lang.String newVal){
		website = newVal;
	}

	public java.lang.String getNotes(){
		return notes;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNotes(java.lang.String newVal){
		notes = newVal;
	}

	public java.lang.String getInvoiceNotes(){
		return invoiceNotes;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setInvoiceNotes(java.lang.String newVal){
		invoiceNotes = newVal;
	}

	/**
	 * numero que identifica al cliente o proveedor frente a su gobierno (NIT)
	 */
	public java.lang.String getTaxId(){
		return taxId;
	}

	/**
	 * numero que identifica al cliente o proveedor frente a su gobierno (NIT)
	 * 
	 * @param newVal
	 */
	public void setTaxId(java.lang.String newVal){
		taxId = newVal;
	}

	public Address getAddress(){
		return address;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAddress(Address newVal){
		address = newVal;
	}

	public Phone getFax(){
		return fax;
	}

	public Phone getPhone(){
		return phone;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFax(Phone newVal){
		fax = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPhone(Phone newVal){
		phone = newVal;
	}

	public MasterValue getLanguage(){
		return language;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLanguage(MasterValue newVal){
		language = newVal;
	}
	
	public MasterValue getTruckCompany() {
		return truckCompany;
	}

	public void setTruckCompany(MasterValue truckCompany) {
		this.truckCompany = truckCompany;
	}

	public boolean isInformFinalDest() {
		return informFinalDest;
	}



	public void setInformFinalDest(boolean informFinalDest) {
		this.informFinalDest = informFinalDest;
	}



	public MasterValue getStatus(){
		return status;
	}

	public boolean isClient(){
		return client;
	}

	public boolean isSupplier(){
		return supplier;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClient(boolean newVal){
		client = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setStatus(MasterValue newVal){
		status = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSupplier(boolean newVal){
		supplier = newVal;
	}

	public MasterValue getPaymentTerm(){
		return paymentTerm;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPaymentTerm(MasterValue newVal){
		paymentTerm = newVal;
	}

	public Employee getEmployeeCreator(){
		return employeeCreator;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeCreator(Employee newVal){
		employeeCreator = newVal;
	}

	public Employee getEmployeeUpdate(){
		return employeeUpdate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeUpdate(Employee newVal){
		employeeUpdate = newVal;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}



	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	/**
	 * Campo para saber en que pestana de departamento estoy, para poder cargar los
	 * contactos y demas elementos que van separados por departamento
	 */
	public int getDepartmentLotId(){
		return departmentLotId;
	}

	/**
	 * Campo para saber en que pestana de departamento estoy, para poder cargar los
	 * contactos y demas elementos que van separados por departamento
	 * 
	 * @param newVal
	 */
	public void setDepartmentLotId(int newVal){
		departmentLotId = newVal;
	}



	public java.lang.String getContactName() {
		return contactName;
	}



	public void setContactName(java.lang.String contactName) {
		this.contactName = contactName;
	}


	public List<CallHistory> getCallsHistory() {
		return callsHistory;
	}


	public void setCallsHistory(List<CallHistory> callsHistory) {
		this.callsHistory = callsHistory;
	}


	public List<ShipPickUp> getShipPickups() {
		return shipPickups;
	}


	public void setShipPickups(List<ShipPickUp> shipPickups) {
		this.shipPickups = shipPickups;
	}


	public PartnerDepartmentInfo getDepartmentInfoLO() {
		return departmentInfoLO;
	}


	public void setDepartmentInfoLO(PartnerDepartmentInfo departmentInfoLO) {
		this.departmentInfoLO = departmentInfoLO;
	}


	public PartnerDepartmentInfo getDepartmentInfoIP() {
		return departmentInfoIP;
	}


	public void setDepartmentInfoIP(PartnerDepartmentInfo departmentInfoIP) {
		this.departmentInfoIP = departmentInfoIP;
	}


	public PartnerDepartmentInfo getDepartmentInfoRM() {
		return departmentInfoRM;
	}


	public void setDepartmentInfoRM(PartnerDepartmentInfo departmentInfoRM) {
		this.departmentInfoRM = departmentInfoRM;
	}

	public boolean isRegUSA() {
		return regUSA;
	}


	public void setRegUSA(boolean regUSA) {
		this.regUSA = regUSA;
	}


	public boolean isRegCOL() {
		return regCOL;
	}


	public void setRegCOL(boolean regCOL) {
		this.regCOL = regCOL;
	}


	public boolean isRegGER() {
		return regGER;
	}


	public void setRegGER(boolean regGER) {
		this.regGER = regGER;
	}


	public List<ClientRatesPort> getClientAirRates() {
		return clientAirRates;
	}


	public void setClientAirRates(List<ClientRatesPort> clientAirRates) {
		this.clientAirRates = clientAirRates;
	}


	public List<ClientRatesPort> getClientOceanRates20() {
		return clientOceanRates20;
	}


	public void setClientOceanRates20(List<ClientRatesPort> clientOceanRates20) {
		this.clientOceanRates20 = clientOceanRates20;
	}


	public List<ClientRatesPort> getClientOceanRates40() {
		return clientOceanRates40;
	}


	public void setClientOceanRates40(List<ClientRatesPort> clientOceanRates40) {
		this.clientOceanRates40 = clientOceanRates40;
	}


	public List<ClientRatesPort> getClientOceanRatesLCL() {
		return clientOceanRatesLCL;
	}


	public void setClientOceanRatesLCL(List<ClientRatesPort> clientOceanRatesLCL) {
		this.clientOceanRatesLCL = clientOceanRatesLCL;
	}


	public String getCustomerNumber() {
		return CustomerNumber;
	}


	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}


	public boolean isValidCode() {
		return validCode;
	}


	public void setValidCode(boolean validCode) {
		this.validCode = validCode;
	}


	public boolean isSearchAndInspConsent() {
		return searchAndInspConsent;
	}


	public void setSearchAndInspConsent(boolean searchAndInspConsent) {
		this.searchAndInspConsent = searchAndInspConsent;
	}
}