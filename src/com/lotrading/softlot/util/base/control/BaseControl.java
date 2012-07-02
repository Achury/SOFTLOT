package com.lotrading.softlot.util.base.control;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.lotrading.softlot.security.entity.Employee;

public class BaseControl {
	
	public void setSessionAttribute(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	public Object getSessionAttribute(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public void removeSessionAttribute(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	public String getParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}
	
	public void setInfoMessage(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
	
	public void setWarningMessage(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

	public void setErrorMessage(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	public int getEmployeeLoggedId (){
		Employee emp = (Employee)this.getSessionAttribute("#{employee_auth}");
        if(emp!=null)
        	return emp.getEmployeeId();
        else
        	return 0;
	}
	
	public Employee getEmployeeLogged(){
		Employee emp = (Employee)this.getSessionAttribute("#{employee_auth}");
		 return emp;
	}
	
	public void clearMessages(){
		Iterator<FacesMessage> msgIterator = FacesContext.getCurrentInstance().getMessages();
	    while(msgIterator.hasNext()){
	        msgIterator.next();
	        msgIterator.remove();
	    }
	}
}
