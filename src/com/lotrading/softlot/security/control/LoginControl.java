package com.lotrading.softlot.security.control;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.util.base.control.BaseControl;

public class LoginControl extends BaseControl {
	
	private Employee employee;
	private IEmployeeLogic employeeLogic;
	private boolean passExpired;
	private String newPass;
	
	public LoginControl(){
		employee = new Employee();
	}
	
	public String authenticateAction() {
        String _tmpRetorno = "";
        try
        {
            Employee _tmpEmployee = employeeLogic.authenticate(employee);
            
            if (_tmpEmployee != null) {
            	if(_tmpEmployee.isPasswordExpired()){
            		setInfoMessage("Password has expired. Please change the password\n" +
            				"or contact the System Administrator");
            		setPassExpired(true);
                    _tmpRetorno = "NO_AUTH";
            	}else{
	            	super.setSessionAttribute("#{employee_auth}", _tmpEmployee);
	                _tmpRetorno = "AUTH";
            	}
            } else {
            	setWarningMessage("Username or Password is incorrect.\n");
                _tmpRetorno = "NO_AUTH";
            }
        } catch(Exception e) {
            //TODO: imprimir mensaje de error
        	
        }
        return _tmpRetorno;
    }
	
	public String logoutAction(){
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
            session.invalidate();            
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "LOGIN";
	}
	
	public void changePasswordAction(){
		try {
			employee = employeeLogic.authenticate(employee);
			if (employee != null) {
			 	if(employee.getEmployeeId() > 0){
					employee.setPassword(newPass);
					boolean _tmpRet = employeeLogic.changePassword(employee);
					if(_tmpRet){
						setInfoMessage("The Password has been changed successfully");
						setPassExpired(false);
					}else{
						setErrorMessage("error trying to change the password");
					}
			 	}
			} else {
	            	setWarningMessage("The Username or Password is incorrect.\n");
			}
		} catch (Exception e) {
			setErrorMessage("Exception: error trying to change the password");
		}
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public IEmployeeLogic getEmployeeLogic() {
		return employeeLogic;
	}

	public void setEmployeeLogic(IEmployeeLogic employeeLogic) {
		this.employeeLogic = employeeLogic;
	}

	public boolean isPassExpired() {
		return passExpired;
	}

	public void setPassExpired(boolean passExpired) {
		this.passExpired = passExpired;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
}
