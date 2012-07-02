package com.lotrading.softlot.security.control;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.com.landsoft.devbase.jsf.comp.navigator.NavigatorItem;
import co.com.landsoft.devbase.jsf.comp.navigator.NavigatorItemList;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.ResourceRole;
import com.lotrading.softlot.security.logic.IResourceLogic;
import com.lotrading.softlot.util.base.control.BaseControl;

public class ToolbarControl extends BaseControl {
	private static final long serialVersionUID = 9165916273549943516L;
	private static final Log log = LogFactory.getLog(ToolbarControl.class);
	private IResourceLogic resourceLogic;
	Employee employee;

	@SuppressWarnings("rawtypes")
	public NavigatorItemList getToolBarItems() {
		NavigatorItemList tools = new NavigatorItemList();
		try {
			// TODO clear memory (done)
			String urlRecurso = (String) super.getSessionAttribute("RECURSO_DESPLEGADO");

			log.debug("Toolbar logic iniciado para URL [" + urlRecurso + "]");

			if (urlRecurso == null || urlRecurso.equals("")) {
				urlRecurso = "/jsp/menu/vacio.jsp";
			}

			//TODO : cargar tools del usuario para el recurso
			employee = (Employee) super.getSessionAttribute("#{employee_auth}");
			if (employee != null) {
				ResourceRole resourceRole = new ResourceRole();
				
				Resource resource = new Resource();
				resource.setUrl(urlRecurso);
				
				resource = resourceLogic.loadResourceByUrl(resource);
				if(resource!=null){
					resourceRole.setEmployeeId(employee.getEmployeeId());
					resourceRole.setResourceId(resource.getResourceId());
					List _tmpActions = resourceLogic.loadResourceActions(resourceRole, true);
					ordenar(_tmpActions);
					for (Iterator itr = _tmpActions.iterator(); itr.hasNext();) {
						Resource _tmpTool = (Resource) itr.next();
						StringBuffer _tmpUrl = new StringBuffer(_tmpTool.getAction());
						NavigatorItem newNav = getNavigatorItem(_tmpTool.getDescription().equals("-") ? "" : _tmpTool.getDescription(), _tmpUrl.toString());
						newNav.setIcon("/faces" + _tmpTool.getUrl());
						newNav.setAltText(_tmpTool.getDescription());
						if (!tools.contains(newNav))
							tools.add(newNav);
					}
				}
			}
			log.debug("Toolbar logic completado.");
		} catch (Exception e) {
			log.error("getToolBarItems()", e);
		} finally {
			super.removeSessionAttribute("RECURSO_DESPLEGADO");
		}
		return tools;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void ordenar(List items) {
		try {
			Comparator comparator = new Comparator() {
				public int compare(Object o1, Object o2) {
					Resource e1 = (Resource) o1;
					Resource e2 = (Resource) o2;
					return String.valueOf(e1.getResourceId()).compareTo(String.valueOf(e2.getResourceId()));
				}
			};
			// TODO clear memory (done)
			Collections.sort(items, comparator);
		} catch (Exception e) {
			log.error("ordenar()", e);
		}
	}

	private static NavigatorItem getNavigatorItem(String name, String link) {
		NavigatorItem item = new NavigatorItem(name, link);
		return item;
	}

	public String actionListener(ActionEvent event) {
		return "ok";
	}

	public boolean getDisabled() {
		return true;
	}
	
	public IResourceLogic getResourceLogic() {
		return resourceLogic;
	}

	public void setResourceLogic(IResourceLogic resourceLogic) {
		this.resourceLogic = resourceLogic;
	}
}
