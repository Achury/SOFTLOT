package com.lotrading.softlot.setup.control;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.richfaces.component.html.HtmlExtendedDataTable;

import com.lotrading.softlot.setup.entity.Master;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.IMasterLogic;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;
import com.lotrading.softlot.util.base.control.BaseControl;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public class MasterControl extends BaseControl {

	private Master master;
	private List<Master> masters;
	private IMasterLogic masterLogic;
	private IMasterValueLogic masterValueLogic;
	private HtmlExtendedDataTable table;
	private HtmlExtendedDataTable table2;
	private List<MasterValue> masterValues;
	private MasterValue masterValue;
	private String tableState;
	private String tableState2;
	private String sortMode = "single";
	private String selectionMode = "single";
	private String masterValueTitleName = "Master Values";
	private boolean savedMasterValue = false;
	private boolean selectedMaster = false;

	public MasterControl(){
		masters = new ArrayList<Master>();
		masterValues = new ArrayList<MasterValue>();
		master = new Master();
		masterValue = new MasterValue();
	}
	
	public String searchMasterAction() {
		try {
			System.out.println("Hola");
			master.setName("");
			masters=  masterLogic.searchMaster(master);
			if (masters == null || masters.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return "";
	}
	public String selectedMasterAction() {
		master = (Master) table.getRowData();
		try{
			masterValue.setMasterId(master.getMasterId());
			masterValues = masterValueLogic.searchMasterValue(masterValue);
			masterValueTitleName = master.getName();
			selectedMaster = true;
			if (masterValues == null || masterValues.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
			
		}catch(Exception e){
			setErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public void selectedMasterValue(){
		masterValue = (MasterValue)table2.getRowData();
	}
	
	public void newMasterValueAction(){
		try {
			masterValue = new MasterValue();
			masterValue.setMasterId(master.getMasterId());
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new value.\n"
					+ e.getMessage());
		}
	}

	public void finalize() throws Throwable {

	}

	public Master getMaster(){
		return master;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMaster(Master newVal){
		master = newVal;
	}

	public List<Master> getMasters(){
		return masters;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasters(List<Master> newVal){
		masters = newVal;
	}

	public IMasterLogic getMasterLogic(){
		return masterLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterLogic(IMasterLogic newVal){
		masterLogic = newVal;
	}


	public java.lang.String selectMasterAction(){
		return null;
	}

	public void saveMasterValueAction(){
		try {
			if(masterValue.getValue1() == "" && masterValue.getValue2() == "" && masterValue.getValue3() == ""){
				setInfoMessage("Please enter at least one value.");
				savedMasterValue = true;
				return;
			}
			if(masterValueLogic.saveMasterValue(masterValue)){
				masterValue.setMasterId(master.getMasterId());
				masterValues = masterValueLogic.searchMasterValue(masterValue);
				setInfoMessage("");
			}else{
				setWarningMessage("Data was not saved");
			}
		}catch (Exception e) {
			setErrorMessage("Error trying to save the Master Value.\n"+e.getMessage());
		}
	}

	public java.lang.String disableMasterAction(){
		return null;
	}

	public IMasterValueLogic getMasterValueLogic() {
		return masterValueLogic;
	}

	public void setMasterValueLogic(IMasterValueLogic masterValueLogic) {
		this.masterValueLogic = masterValueLogic;
	}

	public HtmlExtendedDataTable getTable() {
		return table;
	}

	public void setTable(HtmlExtendedDataTable table) {
		this.table = table;
	}

	public List<MasterValue> getMasterValues() {
		return masterValues;
	}

	public void setMasterValues(List<MasterValue> masterValues) {
		this.masterValues = masterValues;
	}

	public MasterValue getMasterValue() {
		return masterValue;
	}

	public void setMasterValue(MasterValue masterValue) {
		this.masterValue = masterValue;
	}

	public String getTableState() {
		return tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public String getTableState2() {
		return tableState2;
	}

	public void setTableState2(String tableState2) {
		this.tableState2 = tableState2;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public boolean isSavedMasterValue() {
		return savedMasterValue;
	}

	public void setSavedMasterValue(boolean savedMasterValue) {
		this.savedMasterValue = savedMasterValue;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public HtmlExtendedDataTable getTable2() {
		return table2;
	}

	public void setTable2(HtmlExtendedDataTable table2) {
		this.table2 = table2;
	}

	public String getMasterValueTitleName() {
		return masterValueTitleName;
	}

	public void setMasterValueTitleName(String masterValueTitleName) {
		this.masterValueTitleName = masterValueTitleName;
	}

	public boolean isSelectedMaster() {
		return selectedMaster;
	}

	public void setSelectedMaster(boolean seletedMaster) {
		this.selectedMaster = seletedMaster;
	}

}