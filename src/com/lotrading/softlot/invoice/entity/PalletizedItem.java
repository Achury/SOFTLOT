package com.lotrading.softlot.invoice.entity;

import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 29-Feb-2012 04:15:55 p.m.
 */

public class PalletizedItem {

	private int palletizedId;
	private boolean hazardous;
	private int pieces;
	private double length;
	private double width;
	private double height;
	private double weight;
	private MasterValue type;
	private String whPosition;
	
	private boolean ship;
	private int numberPiecesToShip;
	
	
	public PalletizedItem(){
		type = new MasterValue();
	}
	
	public int getPalletizedId() {
		return palletizedId;
	}
	public void setPalletizedId(int palletizedId) {
		this.palletizedId = palletizedId;
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
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
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
	public MasterValue getType() {
		return type;
	}
	public void setType(MasterValue type) {
		this.type = type;
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
	public String getWhPosition() {
		return whPosition;
	}
	public void setWhPosition(String whPosition) {
		this.whPosition = whPosition;
	}
	
	
}
