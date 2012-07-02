package com.lotrading.softlot.security.infraestructure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;

import com.lotrading.softlot.security.entity.Option;

public class NavegationMenuHelper {

	List items = new ArrayList();
	private Log log = LogFactory.getLog(NavegationMenuHelper.class);
	private String contextoWeb = "";

	public NavegationMenuHelper(String contextoWeb) {
		this.contextoWeb = contextoWeb;
	}

	public NavegationMenuHelper() {
	}

	public void addOption(Option opcion) {
		if (!addOption(opcion, items)) {
			log.debug("Adding option [" + opcion.getName() + "] to menu root");
			if (!items.contains(opcion)) {
				items.add(opcion);
			}
		}
	}

	private boolean addOption(Option option, List sublist) {
		boolean retorno = false;
		// si halla al padre y le puede agregar como subrecurso
		for (int i = 0; i < sublist.size(); i++) {
			if (option.getParentId() != 0) {
				boolean esElPadre = ((Option) sublist.get(i)).getOptionId() == option
						.getParentId();
				if (esElPadre) {
					((Option) sublist.get(i)).getSuboptions().add(option);

					log.debug("Adding option [" + option.getName()
							+ "] as a child of option ["
							+ ((Option) sublist.get(i)).getName() + "]");
					retorno = true;
					break;
				}
				if (((Option) sublist.get(i)).getSuboptions() != null) {
					retorno = addOption(option,
							((Option) sublist.get(i)).getSuboptions());
					if (retorno)
						break;
				}
			} else {
				if (((Option) sublist.get(i)).getSuboptions() != null) {
					retorno = addOption(option,
							((Option) sublist.get(i)).getSuboptions());
					if (retorno)
						break;
				}
			}
		}
		return retorno;
	}

	private NavigationMenuItem getMenuNavigationItem(String label, String action) {
		log.debug("getNavigatorItems(getMenuNavigationItem)");
		String _tmpAction = null;
		if (action != null && !action.equals("")) {
			_tmpAction = action;
		}
		NavigationMenuItem item = new NavigationMenuItem(label, _tmpAction);
		item.setActionListener("#{navegacionMenu.actionListener}");
		item.setValue(label);
		return item;
	}

	private String getUbicacionRecurso(String ubicacion) {
		return this.contextoWeb + "/faces" + ubicacion;
	}

}
