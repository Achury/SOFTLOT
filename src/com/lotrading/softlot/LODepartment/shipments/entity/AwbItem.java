package com.lotrading.softlot.LODepartment.shipments.entity;
import java.util.Date;

import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.warehouse.entity.WhLocation;
import com.lotrading.softlot.warehouse.entity.WhReceipt;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbItem {

	private int itemId;
	private int awbId;
	private int pieces;
	private MasterValue type;
	private double itemLength;
	private double itemWidth;
	private double itemHeight;
	private double weightLbs;
	private double itemVolume;
	private MasterValue rateClass;
	private int whItemId;
	private WhReceipt whReceipt;
	private String poNumber;
	private Invoice invoice;
	private String remarks;
	private WhLocation whLocation;
	private boolean hazardous;
	private Date createdDate;
	private int clientOrderId;
	private String palletId;
	private double chargeableWeight; //to AWB Document
	private String characterRate; //to AWB Document
	private double rate; //to AWB Document
	private boolean minimun;  //to AWB Document
	private double rateTotal; //to AWB Document
	private boolean empty; // to AWB Document
	private double volumeTotal; // to AWB Document

	public AwbItem(){
		type = new MasterValue();
		rateClass = new MasterValue();
		invoice = new Invoice();
		whReceipt = new WhReceipt();
		whLocation = new WhLocation();
	}
	
	public AwbItem(int awbId){
		this.awbId = awbId;
	}

	public int getItemId(){
		return itemId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItemId(int newVal){
		itemId = newVal;
	}

	public int getPieces(){
		return pieces;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPieces(int newVal){
		pieces = newVal;
	}

	public MasterValue getType(){
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(MasterValue newVal){
		type = newVal;
	}

	public double getItemLength(){
		return itemLength;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItemLength(double newVal){
		itemLength = newVal;
	}

	public double getItemWidth(){
		return itemWidth;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItemWidth(double newVal){
		itemWidth = newVal;
	}

	public double getItemHeight(){
		return itemHeight;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItemHeight(double newVal){
		itemHeight = newVal;
	}

	public double getWeightLbs(){
		return weightLbs;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setWeightLbs(double newVal){
		weightLbs = newVal;
	}

	public MasterValue getRateClass(){
		return rateClass;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRateClass(MasterValue newVal){
		rateClass = newVal;
	}

	public String getPoNumber(){
		return poNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPoNumber(String newVal){
		poNumber = newVal;
	}

	public String getRemarks(){
		return remarks;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRemarks(String newVal){
		remarks = newVal;
	}

	public Date getCreatedDate(){
		return createdDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreatedDate(Date newVal){
		createdDate = newVal;
	}

	public int getAwbId() {
		return awbId;
	}

	public void setAwbId(int awbId) {
		this.awbId = awbId;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public boolean isHazardous() {
		return hazardous;
	}

	public void setHazardous(boolean hazardous) {
		this.hazardous = hazardous;
	}

	public WhReceipt getWhReceipt() {
		return whReceipt;
	}

	public void setWhReceipt(WhReceipt whReceipt) {
		this.whReceipt = whReceipt;
	}

	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public double getChargeableWeight() {
		return chargeableWeight;
	}

	public void setChargeableWeight(double chargeableWeight) {
		this.chargeableWeight = chargeableWeight;
	}

	public String getCharacterRate() {
		return characterRate;
	}

	public void setCharacterRate(String rate) {
		this.characterRate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	

	public boolean isMinimun() {
		return minimun;
	}

	public void setMinimun(boolean minimun) {
		this.minimun = minimun;
	}

	public double getRateTotal() {
		return rateTotal;
	}

	public void setRateTotal(double rateTotal) {
		this.rateTotal = rateTotal;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public double getVolumeTotal() {
		return volumeTotal;
	}

	public void setVolumeTotal(double volumeTotal) {
		this.volumeTotal = volumeTotal;
	}

	public int getWhItemId() {
		return whItemId;
	}

	public void setWhItemId(int whItemId) {
		this.whItemId = whItemId;
	}

	public double getItemVolume() {
		itemVolume =  this.itemLength * this.itemWidth * this.itemHeight * this.pieces * Constants.VOL_INCHES_CUBIC_TO_VOL_FEET_CUBIC;
		return itemVolume;
	}

	public void setItemVolume(double itemVolume) {
		this.itemVolume = itemVolume;
	}

	public String getPalletId() {
		return palletId;
	}

	public void setPalletId(String palletId) {
		this.palletId = palletId;
	}

	public WhLocation getWhLocation() {
		return whLocation;
	}

	public void setWhLocation(WhLocation whLocation) {
		this.whLocation = whLocation;
	}

}