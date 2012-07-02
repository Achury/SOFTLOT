package com.lotrading.softlot.util.base.jsf.components;

import java.text.DecimalFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class currencyEuroConverter implements Converter {

	private Log log = LogFactory.getLog(currencyEuroConverter.class);
	private DecimalFormat formatter = new DecimalFormat("####0.00");
	
	public currencyEuroConverter(){}
	
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String param) {
		double value = 0;
		try {
			String prefix = param.substring(0, 1);
			if(prefix.equalsIgnoreCase("€")){
				String suffix = param.substring(1, param.length());
				value = Double.parseDouble(suffix);
			}else{
				String suffix = param.substring(0, param.length());
				value = Double.parseDouble(suffix);
			}		
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("value \""+ param +"\" is not valid number.");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            log.error("An Exception has been thrown " + e);
            throw new ConverterException(facesMessage);
		}
		return value;
	}

	
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		try {
			String value = "€ ";
			value =  value.concat(formatter.format(obj));
			return value;
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

}
