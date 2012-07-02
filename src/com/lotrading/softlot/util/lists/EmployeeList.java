package com.lotrading.softlot.util.lists;

import java.util.List;

import javax.faces.model.SelectItem;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import co.com.landsoft.devbase.util.listas.ListaException;
import co.com.landsoft.devbase.util.listas.ListaHandler;

public class EmployeeList extends ListaHandler{
	
	private IEmployeeLogic employeeLogic;

	public EmployeeList(){
		super();
		try {
			setListId(this.getClass().getName());
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean refreshList() throws ListaException {
		boolean _tmpReturn = false;
		try {
			Employee employee = new Employee();
			List<Employee> _tmpList = employeeLogic.searchEmployees(employee);
			if(_tmpList != null && !_tmpList.isEmpty()){
				for(Employee _tmpEmployee : _tmpList){
					if(_tmpEmployee.getDisabledDate() == null){
						SelectItem element = new SelectItem(new Integer(_tmpEmployee.getEmployeeId()),_tmpEmployee.getFirstName() + " " + _tmpEmployee.getLastName());
						elements.add(element);
					}
				}
				_tmpReturn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _tmpReturn;
	}
	
	@Override
	public List getElements(String arg0) throws ListaException, Exception {
		// TODO Auto-generated method stub
		return elements;
	}

	public IEmployeeLogic getEmployeeLogic() {
		return employeeLogic;
	}

	public void setEmployeeLogic(IEmployeeLogic employeeLogic) {
		this.employeeLogic = employeeLogic;
	}
}
