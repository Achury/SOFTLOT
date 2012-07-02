package com.lotrading.softlot.setup.control;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class MasterValuesControl {

	private MasterValue masterValue;
	private java.util.List masterValues;
	private IMasterValueLogic masterValueLogic;

	public MasterValuesControl(){

	}

	public void finalize() throws Throwable {

	}

	public MasterValue getMasterValue(){
		return masterValue;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterValue(MasterValue newVal){
		masterValue = newVal;
	}

	public java.util.List getMasterValues(){
		return masterValues;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterValues(java.util.List newVal){
		masterValues = newVal;
	}

	public IMasterValueLogic getMasterValueLogic(){
		return masterValueLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterValueLogic(IMasterValueLogic newVal){
		masterValueLogic = newVal;
	}

	public java.lang.String searchMasterValuesAction(){
		return null;
	}

	public java.lang.String selectMasterValueAction(){
		return null;
	}

	public java.lang.String saveMasterValueAction(){
		return null;
	}

	public java.lang.String disableMasterValueAction(){
		return null;
	}

	public java.lang.String newMasterValueAction(){
		return null;
	}

}