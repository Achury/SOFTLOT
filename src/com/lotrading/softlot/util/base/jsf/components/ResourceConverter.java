package com.lotrading.softlot.util.base.jsf.components;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.lotrading.softlot.security.entity.Resource;

public class ResourceConverter implements Converter {

	public ResourceConverter() {
	}

	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String param) {
		try {
			String res[] = param.split("-");
			int id = Integer.parseInt(res[0]);
			String name = res[1];
			Resource resource = new Resource();
			resource.setResourceId(id);
			resource.setName(name);
			return resource;
		} catch (Exception exception) {
			throw new ConverterException(exception);
		}
	}

	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object obj) {
		try {
			return obj.toString();
		} catch (Exception exception) {
			throw new ConverterException(exception);
		}
	}
}