package com.lotrading.softlot.security.control;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlExtendedDataTable;

import co.com.landsoft.devbase.util.listas.AdministradorListas;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.security.logic.IRoleLogic;
import com.lotrading.softlot.util.base.control.BaseControl;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:53 a.m.
 */
public class EmployeeControl extends BaseControl {

	private Employee employee;
	private Employee employeeFilter;
	private List<Employee> employees;
	private List<SelectItem> availableRoles;
	private List<SelectItem> regions;
	private List roles;
	private IEmployeeLogic employeeLogic;
	private IRoleLogic roleLogic;
	private Integer pagina;
	private String tableState;
	private String sortMode = "single";
	private String selectionMode = "single";
	private HtmlExtendedDataTable table;
	private List<SelectItem> departments;

	public EmployeeControl() {
		employee = new Employee();
		employeeFilter = new Employee();
		employees = new ArrayList<Employee>();
		availableRoles = new ArrayList<SelectItem>();
		roles = new ArrayList();
	}
	
	public String searchEmployeesAction() {
		try {
			employees = employeeLogic.searchEmployees(employeeFilter);
			if (employees == null || employees.isEmpty()) {
				setWarningMessage("The query did not return data");
			}
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
		}
		return "employees";
	}

	public void selectEmployeeAction() {	
			employee = (Employee) table.getRowData();
			employee = employeeLogic.decrypt(employee);	
			loadEmployeeRoles();
	}
	
	public void loadEmployeeRoles(){
		try {
			List _tmpAvailable = roleLogic.searchRole(new Role());
			List _tmpSelected = employeeLogic.loadEmployeeRoles(true, employee);
			
			this.setAvailableRoles(new ArrayList<SelectItem>());
			this.setRoles(new ArrayList());
			
			if(_tmpAvailable != null && !_tmpAvailable.isEmpty()){
				Iterator _availableIt = _tmpAvailable.iterator();
				while(_availableIt.hasNext()){
					Role _tmpRole = (Role)_availableIt.next();
					SelectItem item = new SelectItem(String.valueOf(_tmpRole.getRoleId()),_tmpRole.getName());
					availableRoles.add(item);
				}
			}
			
			if(_tmpSelected != null && !_tmpSelected.isEmpty()){
				Iterator _selectedIt = _tmpSelected.iterator();
				while(_selectedIt.hasNext()){
					Role _tmpRole = (Role)_selectedIt.next();
					roles.add(String.valueOf(_tmpRole.getRoleId()));
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to select the employee Roles.\n"+e.getMessage());
		}
	}

	public java.lang.String loadEmployeeByIdAction() {
		return null;
	}

	public void saveEmployeeAction() {
		try {
			employee.setRoles(roles);
			int _tmpRet = employeeLogic.saveEmployee(employee);			
			if(_tmpRet > 0){
				employee.setEmployeeId(_tmpRet);
				for (SelectItem _tmpDepartment: departments){
					if ( new Integer(employee.getDepartmentLotId().getValueId()).equals(_tmpDepartment.getValue())){
						employee.getDepartmentLotId().setValue1(_tmpDepartment.getLabel());
						break;
					}
				}
				setInfoMessage("Employee was successfully saved");
				loadEmployeeRoles();
			} else {
				setWarningMessage("Data was not saved");
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to save the Employee.\n"+e.getMessage());
		}
	}

	public void disableEmployeeAction() {
		try {
			employee.setDisabledDate(new Date());
			this.saveEmployeeAction();
		} catch (Exception e) {
			setErrorMessage("Error trying to disable the Employee.\n"+e.getMessage());
		}
	}

	public void newEmployeeAction() {
		try {
			availableRoles = new ArrayList<SelectItem>();
			employee = new Employee();
			List _tmpAvailable = roleLogic.searchRole(new Role());
			roles = new ArrayList();
			if(_tmpAvailable != null && !_tmpAvailable.isEmpty()){
				Iterator _availableIt = _tmpAvailable.iterator();
				while(_availableIt.hasNext()){
					Role _tmpRole = (Role)_availableIt.next();
					SelectItem item = new SelectItem(String.valueOf(_tmpRole.getRoleId()),_tmpRole.getName());
					availableRoles.add(item);
				}
			}
		} catch (Exception e) {
			setErrorMessage("Error trying to create a new empolyee.\n"+e.getMessage());
		}		
	}
	
	public String backAction(){
		return "employees";
	}
	
	

	public Employee getEmployee() {
		return employee;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployee(Employee newVal) {
		employee = newVal;
	}

	public List<Employee> getEmployees() {
		/*
		 * try { if(employees==null || employees.isEmpty()){ employees =
		 * employeeLogic.searchEmployees(employee); } } catch (Exception e) { //
		 * TODO: handle exception }
		 */
		return employees;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployees(List<Employee> newVal) {
		employees = newVal;
	}

	public IEmployeeLogic getEmployeeLogic() {
		return employeeLogic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeLogic(IEmployeeLogic newVal) {
		employeeLogic = newVal;
	}

	public IRoleLogic getRoleLogic() {
		return roleLogic;
	}

	public void setRoleLogic(IRoleLogic roleLogic) {
		this.roleLogic = roleLogic;
	}

	public String getSize() {
		if (employees == null) {
			return "Found:0";
		} else {
			return "Found:" + employees.size();
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
	
	public void setDepartments(List<SelectItem> departments){
		this.departments = departments;
	}
	
	public List<SelectItem> getDepartments(){
		try {
			setDepartments(AdministradorListas.obtenerLista("com.lotrading.softlot.util.lists.DepartmentList").getElements("faces"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return departments;
	}

	public void setEmployeeFilter(Employee employeeFilter) {
		this.employeeFilter = employeeFilter;
	}

	public Employee getEmployeeFilter() {
		return employeeFilter;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}

	public List getRoles() {
		return roles;
	}
	
	public void setAvailableRoles(List<SelectItem> availableRoles) {
		this.availableRoles = availableRoles;
	}

	public List<SelectItem> getAvailableRoles() {
		return availableRoles;
	}

	public List<SelectItem> getRegions() {
		try {
			setRegions(AdministradorListas.obtenerLista("com.lotrading.softlot.util.lists.RegionsList").getElements("faces"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regions;
	}

	public void setRegions(List<SelectItem> regions) {
		this.regions = regions;
	}
}