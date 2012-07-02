package com.lotrading.softlot.security.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.richfaces.component.html.HtmlExtendedDataTable;

import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.util.base.control.BaseControl;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:02 a.m.
 */
public class ResourceControl extends BaseControl {

	private Resource resource;
	private Resource resourceFilter;
	private List<Resource> resources;
	private IResourceLogic resourceLogic;
	private Integer pagina;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable table;

	public ResourceControl() {
		resource = new Resource();
		resourceFilter = new Resource();		
		resources = new ArrayList<Resource>();
	}

	public Resource getResource() {
		return resource;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResource(Resource newVal) {
		resource = newVal;
	}

	public List<Resource> getResources() {
		/*try {
			if (resources == null || resources.isEmpty()) {
				resources = resourceLogic.searchResource(resource);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		return resources;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResources(List<Resource> newVal) {
		resources = newVal;
	}

	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResourceLogic(IResourceLogic newVal) {
		resourceLogic = newVal;
	}

	public String searchResourceAction() {
		try {
			resources = resourceLogic.searchResource(resourceFilter);
			if (resources == null || resources.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to search the resources.\n"
					+ e.getMessage());
		}
		return "resources";
	}

	public void selectResourceAction() {
		try {
			resource = (Resource) table.getRowData();
		} catch (Exception e) {
			setErrorMessage("Error trying to select the resource.\n"
					+ e.getMessage());
		}
	}

	public void saveResourceAction() {
		try {
			int _tmpRet = resourceLogic.saveResource(resource);
			if (_tmpRet > 0) {
				resource.setResourceId(_tmpRet);
				setInfoMessage("Resource was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Resource.\n"
					+ e.getMessage());
		}
	}

	public void disableResourceAction() {
		try {
			resource.setDisabledDate(new Date());
			this.saveResourceAction();
		} catch (Exception e) {
			setErrorMessage("Error trying to disable the Resource.\n"
					+ e.getMessage());
		}
	}

	public void newResourceAction() {
		try {
			resource = new Resource();
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new Resource.\n"
					+ e.getMessage());
		}
	}

	public String getSize() {
		if (resources == null) {
			return "Found:0";
		} else {
			return "Found:" + resources.size();
		}
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
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

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public HtmlExtendedDataTable getTable() {
		return table;
	}

	public void setTable(HtmlExtendedDataTable table) {
		this.table = table;
	}

	public void setResourceFilter(Resource resourceFilter) {
		this.resourceFilter = resourceFilter;
	}

	public Resource getResourceFilter() {
		return resourceFilter;
	}
}