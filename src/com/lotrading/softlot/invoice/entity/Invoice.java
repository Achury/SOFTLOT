package com.lotrading.softlot.invoice.entity;

import java.util.Date;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.Partner;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public class Invoice {

	private int invoiceId;
	private String invoiceNumber;
	private Partner client;
	private String billingAddress;
	private String shippingAddress;
	private String order;
	private int paymentTermId;
	private int incoterm;
	private String way;
	private String awb_bl;
	private String otherReferences;
	private double subtotal;
	private double tax;
	private double inlandFreight;
	private double otherCharges;
	private double total;
	private int poNumberId;
	private String awbNumber;
	private String blNumber;
	private int status;
	private Date date;
	private int incotermCity;
	private int shipToAddressId;
	private String description;
	private double totalCost;
	private int employeeId;
	private double inlandCost;
	private double margin;
	private String notes;
	private boolean reviewed;
	private int groupId;
	private double ensurance;
	private double otherCost;
	private String remarks;
	private List<PalletizedItem> palletizedItemsList;
	private List<PackingListItem> packingListItemsList;
	
	public Invoice(){
		client = new Partner();
		invoiceNumber = "";
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Partner getClient() {
		return client;
	}
	public void setClient(Partner client) {
		this.client = client;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(int incoterm) {
		this.incoterm = incoterm;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getAwb_bl() {
		return awb_bl;
	}
	public void setAwb_bl(String awb_bl) {
		this.awb_bl = awb_bl;
	}
	public String getOtherReferences() {
		return otherReferences;
	}
	public void setOtherReferences(String otherReferences) {
		this.otherReferences = otherReferences;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getInlandFreight() {
		return inlandFreight;
	}
	public void setInlandFreight(double inlandFreight) {
		this.inlandFreight = inlandFreight;
	}
	public double getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(double otherCharges) {
		this.otherCharges = otherCharges;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getPoNumberId() {
		return poNumberId;
	}
	public void setPoNumberId(int poNumberId) {
		this.poNumberId = poNumberId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getIncotermCity() {
		return incotermCity;
	}
	public void setIncotermCity(int incotermCity) {
		this.incotermCity = incotermCity;
	}
	public int getShipToAddressId() {
		return shipToAddressId;
	}
	public void setShipToAddressId(int shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getInlandCost() {
		return inlandCost;
	}
	public void setInlandCost(double inlandCost) {
		this.inlandCost = inlandCost;
	}
	public double getMargin() {
		return margin;
	}
	public void setMargin(double margin) {
		this.margin = margin;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean isReviewed() {
		return reviewed;
	}
	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public double getEnsurance() {
		return ensurance;
	}
	public void setEnsurance(double ensurance) {
		this.ensurance = ensurance;
	}
	public double getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(double otherCost) {
		this.otherCost = otherCost;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<PalletizedItem> getPalletizedItemsList() {
		return palletizedItemsList;
	}
	public void setPalletizedItemsList(List<PalletizedItem> palletizedItemsList) {
		this.palletizedItemsList = palletizedItemsList;
	}

	public int getPaymentTermId() {
		return paymentTermId;
	}

	public void setPaymentTermId(int paymentTermId) {
		this.paymentTermId = paymentTermId;
	}

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public String getAwbNumber() {
		return awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}

	public List<PackingListItem> getPackingListItemsList() {
		return packingListItemsList;
	}

	public void setPackingListItemsList(List<PackingListItem> packingListItemsList) {
		this.packingListItemsList = packingListItemsList;
	}
}
