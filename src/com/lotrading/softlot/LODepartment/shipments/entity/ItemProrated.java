package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.ArrayList;
import java.util.List;

import com.lotrading.softlot.LODepartment.clientOrder.entity.ClientOrder;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.invoice.entity.Invoice;

public class ItemProrated {

	private String order;
	private Partner supplier;
	private Boolean originalInvoice;
	private int pieces;
	private double weight;
	private double volume;
	private List<AwbCostSale> awbCostSalesProrated;
	private List<BlCostSale> blCostSalesProrated;
	private Invoice invoice;
	private ClientOrder clientOrder;
	private boolean byInvoice;
	private boolean byPoNumber;
	private boolean byPallet;
	
	public ItemProrated(){
		supplier = new Partner();		
		awbCostSalesProrated = new ArrayList<AwbCostSale>();
		blCostSalesProrated = new ArrayList<BlCostSale>();
		invoice = new Invoice();
		clientOrder = new ClientOrder();
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Partner getSupplier() {
		return supplier;
	}

	public void setSupplier(Partner supplier) {
		this.supplier = supplier;
	}

	public Boolean getOriginalInvoice() {
		return originalInvoice;
	}

	public void setOriginalInvoice(Boolean originalInvoice) {
		this.originalInvoice = originalInvoice;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public List<AwbCostSale> getAwbCostSalesProrated() {
		return awbCostSalesProrated;
	}

	public void setAwbCostSalesProrated(List<AwbCostSale> awbCostSalesProrated) {
		this.awbCostSalesProrated = awbCostSalesProrated;
	}

	public List<BlCostSale> getBlCostSalesProrated() {
		return blCostSalesProrated;
	}

	public void setBlCostSalesProrated(List<BlCostSale> blCostSalesProrated) {
		this.blCostSalesProrated = blCostSalesProrated;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public ClientOrder getClientOrder() {
		return clientOrder;
	}

	public void setClientOrder(ClientOrder clientOrder) {
		this.clientOrder = clientOrder;
	}

	public boolean isByInvoice() {
		return byInvoice;
	}

	public void setByInvoice(boolean byInvoice) {
		this.byInvoice = byInvoice;
	}

	public boolean isByPoNumber() {
		return byPoNumber;
	}

	public void setByPoNumber(boolean byPoNumber) {
		this.byPoNumber = byPoNumber;
	}

	public boolean isByPallet() {
		return byPallet;
	}

	public void setByPallet(boolean byPallet) {
		this.byPallet = byPallet;
	}
	
	
	
}
