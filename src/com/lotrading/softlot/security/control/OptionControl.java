package com.lotrading.softlot.security.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlExtendedDataTable;

import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.logic.IOptionLogic;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.util.base.control.BaseControl;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class OptionControl extends BaseControl {

	private Option option;
	private Option optionFilter;
	private List<Option> options;
	private List optionList;
	private List resourceList;
	private IOptionLogic optionLogic;
	private Integer pagina;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable table;
	private HtmlSelectBooleanCheckbox isParentCheckBox;
	
	private IResourceLogic resourceLogic;

	public OptionControl() {
		option = new Option();
		optionFilter = new Option();
		options = new ArrayList<Option>();
	}

	public java.lang.String searchOptionAction() {
		try {
			options = optionLogic.searchOption(optionFilter);
			if (options == null || options.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return "options";
	}

	public void selectOptionAction() {
		try {
			option = (Option) table.getRowData();
			cargarListas();
		} catch (Exception e) {
			setErrorMessage("Error trying to select the Option.\n"+e.getMessage());
		}
	}

	public void saveOptionAction() {
		try {
			boolean _tmpRet = optionLogic.saveOption(option);
			if (_tmpRet) {
				setInfoMessage("Option was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Option.\n"+e.getMessage());
		}
	}

	public void disableOptionAction() {
		try {
			option.setDisabledDate(new Date());
			this.saveOptionAction();
		} catch (Exception e) {
			setErrorMessage("Error trying to disable the Option.\n"+e.getMessage());
		}
	}
	
	public void cargarListas() {
		resourceList = new ArrayList();
		optionList = new ArrayList();
		List _tmpResources;
		List _tmpOptions;
		try {
			Resource _tmpResourc = new Resource();
			_tmpResourc.setType("F");
			_tmpResources = resourceLogic.searchResource(_tmpResourc);
			if(_tmpResources != null && !_tmpResources.isEmpty()){
				Iterator _resourceIt = _tmpResources.iterator();
				while(_resourceIt.hasNext()){
					_tmpResourc = new Resource();
					_tmpResourc = (Resource) _resourceIt.next();
					SelectItem item = new SelectItem(new Integer(_tmpResourc.getResourceId()),_tmpResourc.getName());
					resourceList.add(item);
				}
			}
			_tmpResourc = null;
			_tmpOptions = optionLogic.searchOption(new Option());
			if(_tmpOptions != null && !_tmpOptions.isEmpty()){
				Iterator _OptionIt = _tmpOptions.iterator();
				while(_OptionIt.hasNext()){
					Option _tmpOption = (Option) _OptionIt.next();
					SelectItem item = new SelectItem(new Integer(_tmpOption.getOptionId()),_tmpOption.getName());
					optionList.add(item);
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error filling the lists.\n"+e.getMessage());
		}
	}

	public void newOptionAction() {
		try {
			option = new Option();
			cargarListas();
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new Option.\n"+e.getMessage());
		}
	}
	
	public java.lang.String cancelAction() {
		return "options";
	}

	public Option getOption() {
		return option;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOption(Option newVal) {
		option = newVal;
	}

	public List<Option> getOptions() {
		return options;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOptions(List<Option> newVal) {
		options = newVal;
	}

	public IOptionLogic getOptionLogic() {
		return optionLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOptionLogic(IOptionLogic newVal) {
		optionLogic = newVal;
	}

	public void setTable(HtmlExtendedDataTable table) {
		this.table = table;
	}

	public HtmlExtendedDataTable getTable() {
		return table;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	public String getTableState() {
		return tableState;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getPagina() {
		return pagina;
	}

	public String getSize() {
		if (options == null) {
			return "Found:0";
		} else {
			return "Found:" + options.size();
		}

	}

	public void setOptionFilter(Option optionFilter) {
		this.optionFilter = optionFilter;
	}

	public Option getOptionFilter() {
		return optionFilter;
	}

	public void setOptionList(List optionList) {
		this.optionList = optionList;
	}

	public List getOptionList() {
		return optionList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}

	public List getResourceList() {
		return resourceList;
	}

	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}

	public void setResourceLogic(IResourceLogic resourceLogic) {
		this.resourceLogic = resourceLogic;
	}

	public void setIsParentCheckBox(HtmlSelectBooleanCheckbox isParentCheckBox) {
		this.isParentCheckBox = isParentCheckBox;
	}

	public HtmlSelectBooleanCheckbox getIsParentCheckBox() {
		return isParentCheckBox;
	}

	public void setValueIsparent(){
		Boolean checkBoxValue = (Boolean)isParentCheckBox.getValue();
		option.setParent(checkBoxValue);
	}
}