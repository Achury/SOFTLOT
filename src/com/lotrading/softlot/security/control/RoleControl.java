package com.lotrading.softlot.security.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlExtendedDataTable;
import org.richfaces.component.html.HtmlTab;
import org.richfaces.component.html.HtmlTabPanel;

import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IOptionLogic;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.security.logic.IRoleLogic;
import com.lotrading.softlot.util.base.control.BaseControl;
import com.lotrading.softlot.util.base.control.ResultList;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:03 a.m.
 */
public class RoleControl extends BaseControl {

	private Role role;
	private List<Role> roles;
	private List<SelectItem> availableOptions;
	private List options;
	private ResultList forms;
	private List availableResources;
	private List<SelectItem> availableActions;
	private List resources;
	private IRoleLogic roleLogic;
	private IOptionLogic optionLogic;
	private IResourceLogic resourceLogic;
	private Integer pagina;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private String selectedResource;
	private String selectedTab;
	private Set selectedResources;
	private HtmlExtendedDataTable table;
	private HtmlExtendedDataTable tableRes;
	
	private int cont;
	private HtmlTabPanel tabPanel;
	private HtmlTabPanel tabPanelPpal;


	public RoleControl() {
		role = new Role();
		roles = new ArrayList<Role>();
		availableOptions = new ArrayList<SelectItem>();
		options = new ArrayList();
		availableResources = new ArrayList<SelectItem>();
		resources = new ArrayList();
		selectedResources = new HashSet();
		selectedTab = "Options";
		forms = new ResultList();
	}
	
	public String searchRoleAction() {
		try {
			roles = roleLogic.searchRole(role);
			if (roles == null || roles.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to search Roles.\n" + e.getMessage());
		}
		return null;
	}
	
	public void addResource() {
		try {
			if(selectedResources != null && !selectedResources.isEmpty()){
				Iterator it = selectedResources.iterator();
				Resource _tmpResource = (Resource)it.next();
				
				if(resources!=null && !resources.isEmpty()){
					_tmpResource.setActions(resources);
				}
				
				boolean _tmpExist = forms.existeObjeto(_tmpResource);
				_tmpResource.setRoleId(role.getRoleId());
				
				boolean _tmpResult = roleLogic.saveResourceRole(_tmpResource,_tmpExist);
				if(_tmpResult){
					if(forms.existeObjeto(_tmpResource)){
						forms.actualizarObjeto(_tmpResource);
					}else{
						forms.agregarObjeto(_tmpResource);
					}
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to add the Resource.\n" + e.getMessage());
		}
	}
	
	public void deleteResource() {
		try {
			Resource _tmpResource = (Resource) tableRes.getRowData();
			_tmpResource.setRoleId(role.getRoleId());
			
			boolean _tmpResult = roleLogic.deleteResourceRole(_tmpResource);
			
			if(_tmpResult)
				forms.borrarObjeto(_tmpResource);
			
		} catch (Exception e) {
			setErrorMessage("Error trying to add the Resource.\n" + e.getMessage());
		}
	}
	
	public void selectResourceAction(){
		try {
			this.setAvailableActions(new ArrayList<SelectItem>());
			this.setResources(new ArrayList());
			
			selectedTab = "Resources";
			if(selectedResources != null && !selectedResources.isEmpty()){
				Iterator it = selectedResources.iterator();
				Resource _tmpResource = (Resource)it.next();
				Resource _tmpParam = new Resource();
				_tmpParam.setType("T");
				
				List _tmpActions = resourceLogic.searchResource(_tmpParam);
				if(_tmpActions != null && !_tmpActions.isEmpty()){
					Iterator _availableIt = _tmpActions.iterator();
					while(_availableIt.hasNext()){
						Resource _tmpAction = (Resource)_availableIt.next();
						SelectItem item = new SelectItem(String.valueOf(_tmpAction.getResourceId()),_tmpAction.getName());
						availableActions.add(item);
					}
				}
				
				ResourceRole _tmpRes = new ResourceRole();
				_tmpRes.setResourceId(_tmpResource.getResourceId());
				_tmpRes.setRoleId(role.getRoleId());
				List _tmpSelected = resourceLogic.loadResourceActions(_tmpRes, true);
				if(_tmpSelected != null && !_tmpSelected.isEmpty()){
					Iterator _selectedIt = _tmpSelected.iterator();
					while(_selectedIt.hasNext()){
						Resource _tmpAction = (Resource)_selectedIt.next();
						resources.add(String.valueOf(_tmpAction.getResourceId()));
					}
				}
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		//return "role";
	}

	public String selectRoleAction() {
		try {
			role = (Role) table.getRowData();
			
			//Set Options
			List _tmpAvailable = optionLogic.searchOption(new Option());
			List _tmpSelected = roleLogic.loadRoleOptions(true, role);
			
			this.setAvailableOptions(new ArrayList<SelectItem>());
			this.setOptions(new ArrayList());
			this.setAvailableActions(new ArrayList<SelectItem>());
			
			if(_tmpAvailable != null && !_tmpAvailable.isEmpty()){
				Iterator _availableIt = _tmpAvailable.iterator();
				while(_availableIt.hasNext()){
					Option _tmpOption = (Option)_availableIt.next();
					SelectItem item = new SelectItem(String.valueOf(_tmpOption.getOptionId()),_tmpOption.getName());
					availableOptions.add(item);
				}
			}
			
			if(_tmpSelected != null && !_tmpSelected.isEmpty()){
				Iterator _selectedIt = _tmpSelected.iterator();
				while(_selectedIt.hasNext()){
					Option _tmpOption = (Option)_selectedIt.next();
					options.add(String.valueOf(_tmpOption.getOptionId()));
				}
			}
			Resource _tmpParam = new Resource();
			_tmpParam.setType("F");
			this.setAvailableResources(resourceLogic.searchResource(_tmpParam));	
			List<Resource> _tmpListRes = roleLogic.loadRoleResources(true, role);
			if(_tmpListRes != null ){
				for(Resource _tmpRec : _tmpListRes){
					_tmpRec = roleLogic.loadRoleActions(_tmpRec);
					this.forms.agregarObjeto(_tmpRec);
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to select the Role.\n"
					+ e.getMessage());
		}
		return "role";
	}

	public String saveRoleAction() {
		try {
			role.setOptions(options);
			role.setResources(resources);
			int _tmpRet = roleLogic.saveRole(role);
			if (_tmpRet > 0) {
				role.setRoleId(_tmpRet);
				setInfoMessage("Role was successfully saved");
			} else {
				setWarningMessage("Data was not saved");
			}
			
			/*Saving options
			Iterator _optionsIt = options.iterator();
			while(_optionsIt.hasNext()){
				String option = (String)_optionsIt.next();
				int role = getRole().getRoleId();
			}
			
			Saving resources
			Iterator _resourcesIt = resources.iterator();
			while(_resourcesIt.hasNext()){
				String resource = (String)_resourcesIt.next();
				int role = getRole().getRoleId();
			}*/
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Role.\n" + e.getMessage());
		}
		return "role";
	}

	public String disableRoleAction() {
		String _tmpRet = "";
		try {
			role.setDisabledDate(new Date());
			_tmpRet = this.saveRoleAction();
		} catch (Exception e) {
			setErrorMessage("Error trying to disable the Role.\n"
					+ e.getMessage());
		}
		return _tmpRet;
	}

	public String newRoleAction() {
		try {
			role = new Role();
			List _tmpAvailable = optionLogic.searchOption(new Option());			
			this.setAvailableOptions(new ArrayList<SelectItem>());
			this.setOptions(new ArrayList());
			this.setAvailableActions(new ArrayList<SelectItem>());
			
			if(_tmpAvailable != null && !_tmpAvailable.isEmpty()){
				Iterator _availableIt = _tmpAvailable.iterator();
				while(_availableIt.hasNext()){
					Option _tmpOption = (Option)_availableIt.next();
					SelectItem item = new SelectItem(String.valueOf(_tmpOption.getOptionId()),_tmpOption.getName());
					availableOptions.add(item);
				}
			}
			Resource _tmpParam = new Resource();
			resources = new ArrayList();
			_tmpParam.setType("F");
			this.setAvailableResources(resourceLogic.searchResource(_tmpParam));
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new Role.\n"
					+ e.getMessage());
		}
		return "role";
	}
	
	public String backAction() {
		return "roles";
	}

	public void createTab() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
	 
		
			HtmlTab tab = (HtmlTab)application.createComponent(HtmlTab.COMPONENT_TYPE);
			tab.setLabel("Tab # "+(cont+1));
			tab.setName(cont);

			
            tabPanel.getChildren().add(tab);
            cont++;
            HtmlCommandButton boton = (HtmlCommandButton)application.createComponent(HtmlCommandButton.COMPONENT_TYPE);
            tab.getChildren().add(boton);
	}
	
	public void createTabPpal() {
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
		 
			
			HtmlTab tab = (HtmlTab)application.createComponent(HtmlTab.COMPONENT_TYPE);
			tab.setLabel(role.getName());
			tab.setName(role.getRoleId());
			tab.setId(role.getName().replaceAll(" ", ""));
			tabPanelPpal.getChildren().add(tab);		
            
		}catch (Exception e) {
			setErrorMessage("Error trying to create a Tab.\n"
					+ e.getMessage());
		}
	}
	
	public void loadOptions(){
		try{
			List _optionsTmp = optionLogic.searchOption(new Option());
			Iterator optionsIt = _optionsTmp.iterator();
			
			while(optionsIt.hasNext()){
				Option option = (Option)optionsIt.next();
				SelectItem item = new SelectItem(String.valueOf(option.getOptionId()),option.getName());
				availableOptions.add(item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadResources(){
		try{			
			List _resourcesTmp = resourceLogic.searchResource(new Resource());
			Iterator resourcesIt = _resourcesTmp.iterator();
			
			while(resourcesIt.hasNext()){
				Resource resource = (Resource)resourcesIt.next();
				SelectItem item = new SelectItem(String.valueOf(resource.getResourceId()),resource.getName());
				availableResources.add(item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Role getRole() {
		return role;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRole(Role newVal) {
		role = newVal;
	}

	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRoles(List<Role> newVal) {
		roles = newVal;
	}

	public IRoleLogic getRoleLogic() {
		return roleLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRoleLogic(IRoleLogic newVal) {
		roleLogic = newVal;
	}

	public String getSize() {
		if (roles == null) {
			return "Found:0";
		} else {
			return "Found:" + roles.size();
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

	public void setTabPanel(HtmlTabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}

	public HtmlTabPanel getTabPanel() {
		return tabPanel;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public int getCont() {
		return cont;
	}

	public void setTabPanelPpal(HtmlTabPanel tabPanelPpal) {
		this.tabPanelPpal = tabPanelPpal;
	}

	public HtmlTabPanel getTabPanelPpal() {
		return tabPanelPpal;
	}

	public void setOptionLogic(IOptionLogic optionLogic) {
		this.optionLogic = optionLogic;
		//loadOptions();
	}

	public IOptionLogic getOptionLogic() {
		return optionLogic;
	}

	public void setResourceLogic(IResourceLogic resourceLogic) {
		this.resourceLogic = resourceLogic;
		
		//loadResources();
	}

	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}

	public void setOptions(List options) {
		this.options = options;
	}

	public List getOptions() {
		return options;
	}

	public void setResources(List resources) {
		this.resources = resources;
	}

	public List getResources() {
		return resources;
	}

	public void setAvailableOptions(List<SelectItem> availableOptions) {
		this.availableOptions = availableOptions;
	}

	public List<SelectItem> getAvailableOptions() {
		return availableOptions;
	}

	public void setAvailableResources(List availableResources) {
		this.availableResources = availableResources;
	}

	public List getAvailableResources() {
		return availableResources;
	}

	public void setSelectedResource(String selectedResource) {
		this.selectedResource = selectedResource;
	}

	public String getSelectedResource() {
		return selectedResource;
	}

	public Set getSelectedResources() {
		return selectedResources;
	}

	public void setSelectedResources(Set selectedResources) {
		this.selectedResources = selectedResources;
	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public List<SelectItem> getAvailableActions() {
		return availableActions;
	}

	public void setAvailableActions(List<SelectItem> availableActions) {
		this.availableActions = availableActions;
	}

	public List getForms() {
		return forms.getObjetos();
	}

	public void setForms(List forms) {
		this.forms.setObjetos(forms);
	}

	public HtmlExtendedDataTable getTableRes() {
		return tableRes;
	}

	public void setTableRes(HtmlExtendedDataTable tableRes) {
		this.tableRes = tableRes;
	}
}