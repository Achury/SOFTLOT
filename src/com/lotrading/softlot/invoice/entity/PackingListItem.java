package com.lotrading.softlot.invoice.entity;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.warehouse.entity.WhReceipt;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public class PackingListItem {
	
	private int packingListId;
	private int invoiceId;
	private boolean hazardous;
	private int pieces;
	private MasterValue type;
	private double length;
	private double width;
	private double height;
	private double weight;
	private WhReceipt whReceipt;
	private int whDetailId;
	private String palletNumber;
	
	private boolean ship;
	private int numberPiecesToShip;
	
	public PackingListItem(){
		setType(new MasterValue());
	}

	public int getPackingListId() {
		return packingListId;
	}

	public void setPackingListId(int packingListId) {
		this.packingListId = packingListId;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean isHazardous() {
		return hazardous;
	}

	public void setHazardous(boolean hazardous) {
		this.hazardous = hazardous;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public MasterValue getType() {
		return type;
	}

	public void setType(MasterValue type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double lenght) {
		this.length = lenght;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getWhDetailId() {
		return whDetailId;
	}

	public void setWhDetailId(int whDetailId) {
		this.whDetailId = whDetailId;
	}

	public String getPalletNumber() {
		return palletNumber;
	}

	public void setPalletNumber(String palletNumber) {
		this.palletNumber = palletNumber;
	}

	public boolean isShip() {
		return ship;
	}

	public void setShip(boolean ship) {
		this.ship = ship;
	}

	public int getNumberPiecesToShip() {
		return numberPiecesToShip;
	}

	public void setNumberPiecesToShip(int numberPiecesToShip) {
		this.numberPiecesToShip = numberPiecesToShip;
	}

	public WhReceipt getWhReceipt() {
		return whReceipt;
	}

	public void setWhReceipt(WhReceipt whReceipt) {
		this.whReceipt = whReceipt;
	}
}
