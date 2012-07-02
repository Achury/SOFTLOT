package com.lotrading.softlot.security.dao;

import java.util.List;

import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Role;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface IEmployeeDao {

	/**
	 * 
	 * @param employee
	 * @throws Exception 
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
	 * @throws Exception 
	 */
	public int createEmployee(Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 * @throws Exception 
	 */
	public boolean updateEmployee(Employee employee) throws Exception;

	/**
	 * Devuelve los roles asociados o no asociados al empleado dependiendo del valor
	 * del parámetro booleano.
	 * 
	 * @param associated
	 * @param employee
	 * @throws Exception 
	 */
	public List<Role> loadEmployeeRoles(boolean associated, Employee employee) throws Exception;

	/**
	 * 
	 * @param employee
	 * @throws Exception 
	 */
	public boolean updateEmployeeRoles(Employee employee) throws Exception;
	
	public List<Option> loadOptions(boolean asociated, Employee employee) throws Exception;

}