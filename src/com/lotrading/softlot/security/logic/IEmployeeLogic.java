package com.lotrading.softlot.security.logic;
import java.util.List;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Role;



/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IEmployeeLogic {

	/**
	 * 
	 * @param employee
	 * @throws Exception 
	 */
	public int saveEmployee(Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 */
	public List<Employee> searchEmployees(Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 * @throws Exception 
	 */
	public Employee loadEmployeeByLogin(Employee employee) throws Exception;
	
	
	public Employee loadEmployeeById(Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 */
	public boolean updateEmployeeRoles(Employee employee) throws Exception;

	/**
	 * 
	 * @param associated
	 * @param employee
	 */
	public List<Role> loadEmployeeRoles(boolean associated, Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 * @throws Exception 
	 */
	public Employee authenticate(Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 */
	public boolean changePassword(Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 */
	public boolean authorize(Employee employee, String url) throws Exception;
	
	public List<Option> loadOptions(boolean asociated, Employee employee) throws Exception;
	
	public Employee decrypt(Employee employee);

}