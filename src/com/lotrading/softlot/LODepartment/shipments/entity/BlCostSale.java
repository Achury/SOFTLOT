package com.lotrading.softlot.LODepartment.shipments.entity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlCostSale implements Cloneable{
	private Log log = LogFactory.getLog(BlCostSale.class);

	private int blCostSaleId;
	private int blId;
	private MasterValue chargeType;
	private double cost;
	private double sale;
	private boolean otherCost;
	private String notes;
	private boolean reviewed;
	private boolean flat;
	private double costMin;
	private double saleMin;
	
	private boolean selectedToBlDoc;
	private boolean showInMaster;
	
	public BlCostSale(){
		chargeType = new MasterValue();

	}
	
	public BlCostSale(int blId){
		chargeType = new MasterValue();
		this.blId = blId;
	}
	
	public BlCostSale(int blId, boolean otherCost){
		chargeType = new MasterValue();
		this.blId = blId;
		this.otherCost = otherCost;
	}

	public int getBlCostSaleId() {
		return blCostSaleId;
	}


	public void setBlCostSaleId(int blCostSaleId) {
		this.blCostSaleId = blCostSaleId;
	}


	public int getBlId() {
		return blId;
	}


	public void setBlId(int blId) {
		this.blId = blId;
	}


	public MasterValue getChargeType() {
		return chargeType;
	}


	public void setChargeType(MasterValue chargeType) {
		this.chargeType = chargeType;
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

	public boolean isSelectedToBlDoc() {
		return selectedToBlDoc;
	}

	public void setSelectedToBlDoc(boolean selectedToBlDoc) {
		this.selectedToBlDoc = selectedToBlDoc;
	}

	public boolean isShowInMaster() {
		return showInMaster;
	}

	public void setShowInMaster(boolean showInMaster) {
		this.showInMaster = showInMaster;
	}

	public boolean isEmpty(){
		if(this.blCostSaleId > 0){
			return false;
		}else if( this.chargeType.getValueId() > 0 ){
			return false;
		}
		return true;
	}
	
	public boolean isShowSelectToBlDoc() {
		if(chargeType.getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT 
				|| chargeType.getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
			return false;
		}
		return true;
	}
	
	public boolean isInlandFreight() {
		if(chargeType.getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL 
				|| chargeType.getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL){
			return true;
		}
		return false;
	}
	
	public Object clone(){
		BlCostSale obj=null;
        try{
            obj=(BlCostSale)super.clone();
        }catch(CloneNotSupportedException ex){
        	log.error("no se puede duplicar el la entidad BlCostSale. An Exception has been thrown " + ex);
        }    
        obj.chargeType=(MasterValue)obj.chargeType.clone();
        return obj;
    }

}