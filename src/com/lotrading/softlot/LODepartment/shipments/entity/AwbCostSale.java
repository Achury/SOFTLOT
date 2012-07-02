package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbCostSale  implements Cloneable{
	private Log log = LogFactory.getLog(AwbCostSale.class);
	private int awbCostsaleId;
	private int awbId;
	private MasterValue chargeType;
	private double cost;
	private double sale;
	private double costMin;
	private double saleMin;
	private boolean otherCost;
	private boolean reviewed;
	private boolean flat;
	private String notes;
	private Date createdDate;
	
	private boolean selectedToAwbDoc;
	private boolean showInMaster;
	
	public AwbCostSale(){
		chargeType = new MasterValue();
	}
	
	public AwbCostSale(int awbId){
		chargeType = new MasterValue();
		this.awbId = awbId;
	}
	
	public AwbCostSale(int awbId, boolean otherCost){
		chargeType = new MasterValue();
		this.awbId = awbId;
		this.otherCost = otherCost;
	}
	
	public int getAwbCostsaleId() {
		return awbCostsaleId;
	}
	public void setAwbCostsaleId(int awbCostsaleId) {
		this.awbCostsaleId = awbCostsaleId;
	}
	public int getAwbId() {
		return awbId;
	}
	public void setAwbId(int awbId) {
		this.awbId = awbId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getSale() {
		return sale;
	}
	public void setSale(double sale) {
		this.sale = sale;
	}
	public boolean isOtherCost() {
		return otherCost;
	}
	public void setOtherCost(boolean otherCost) {
		this.otherCost = otherCost;
	}
	public boolean isReviewed() {
		return reviewed;
	}
	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MasterValue getChargeType() {
		return chargeType;
	}

	public void setChargeType(MasterValue chargeType) {
		this.chargeType = chargeType;
	}
	
	public boolean isEmpty(){
		if(this.awbCostsaleId > 0){
			return false;
		}else if( this.chargeType.getValueId() > 0 ){
			return false;
		}
		return true;
	}

	public boolean isFlat() {
		return flat;
	}

	public void setFlat(boolean flat) {
		this.flat = flat;
	}

	public double getCostMin() {
		return costMin;
	}

	public void setCostMin(double costMin) {
		this.costMin = costMin;
	}

	public double getSaleMin() {
		return saleMin;
	}

	public void setSaleMin(double saleMin) {
		this.saleMin = saleMin;
	}

	public boolean isSelectedToAwbDoc() {
		return selectedToAwbDoc;
	}

	public void setSelectedToAwbDoc(boolean selectedToAwbDoc) {
		this.selectedToAwbDoc = selectedToAwbDoc;
	}	
	
	public boolean isShowSelectToAwbDoc() {
		if(chargeType.getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT 
				|| chargeType.getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
			return false;
		}
		return true;
	}
	
	public Object clone(){
		AwbCostSale obj=null;
        try{
            obj=(AwbCostSale)super.clone();
        }catch(CloneNotSupportedException ex){
        	log.error("no se puede duplicar el la entidad AwbCostSale. An Exception has been thrown " + ex);
        }    
        obj.chargeType=(MasterValue)obj.chargeType.clone();
        return obj;
    }

	public boolean isShowInMaster() {
		return showInMaster;
	}

	public void setShowInMaster(boolean showInMaster) {
		this.showInMaster = showInMaster;
	}
	
	
}
