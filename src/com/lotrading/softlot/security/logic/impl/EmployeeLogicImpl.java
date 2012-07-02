package com.lotrading.softlot.security.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.logic.IPhoneLogic;
import com.lotrading.softlot.security.dao.IEmployeeDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.security.Encryption;
import com.lotrading.softlot.util.base.security.Encryption.EncryptionException;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public class EmployeeLogicImpl implements IEmployeeLogic {

	private Log log = LogFactory.getLog(EmployeeLogicImpl.class);
	private IEmployeeDao employeeDao;
	private IPhoneLogic phoneLogic;

	public EmployeeLogicImpl() {
		
	}
	/**
	 * 
	 * @param _tmpEmployee
	 */
	public int saveEmployee(Employee employee) throws Exception {
		int _tmpEmployeeId = 0;
		try {
			if (employee == null)
				return _tmpEmployeeId;
			Encryption _tmpEncryption = new Encryption("DESede");
			//if Employee is New
			if (employee.getEmployeeId() <= 0) {
				if (employee.getWorkPhone() != null) {
					int _idPhoneTemp = phoneLogic.savePhone(employee.getWorkPhone());
					employee.getWorkPhone().setPhoneId(_idPhoneTemp);
				}
				employee.setEnteredDate(new Date());
				employee.setUpdatedDate(new Date());
				employee.setLastChangePasswd(new Date());
				String pass = employee.getPassword();
				employee.setPassAccess(pass);
				employee.setPassword(_tmpEncryption.encrypt(employee
						.getPassword()));
				_tmpEmployeeId = employeeDao.createEmployee(employee);
				employee.setPassword(pass);
				employee.setEmployeeId(_tmpEmployeeId);
				if(employee.getRoles() != null){
					employeeDao.updateEmployeeRoles(employee);
				}
			//if Employee already exist
			} else if (employee.getEmployeeId() > 0) {
				if (employee.getWorkPhone() != null) {
					MasterValue _mvTemp = new MasterValue(Constants.MASTER_VALUE_PHONE_TYPE_TEL);
					employee.getWorkPhone().setType(_mvTemp);
					int _IdPhoneTemp = phoneLogic.savePhone(employee.getWorkPhone());
					employee.getWorkPhone().setPhoneId(_IdPhoneTemp);
				}
				if(employee.getRoles() != null){
					employeeDao.updateEmployeeRoles(employee);
				}
				employee.setUpdatedDate(new Date());
				String pass = employee.getPassword();
				employee.setPassAccess(pass);
				employee.setPassword(_tmpEncryption.encrypt(employee
						.getPassword()));
				if(employeeDao.updateEmployee(employee)){
					employee.setPassword(pass);
					_tmpEmployeeId = employee.getEmployeeId();
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			if(e.toString().indexOf("Encrypted chain is null or empty") > -1){
				throw new Exception("Password field is empty");
			}else{
				throw e;
			}			
		}
		return _tmpEmployeeId;
	}

	/**
	 * 
	 * @param employee
	 */
	public List<Employee> searchEmployees(Employee employee) throws Exception {
		List<Employee> employees = null;
		try {
			if (employee != null) {
				employees = employeeDao.searchEmployees(employee);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return employees;
	}

	/**
	 * 
	 * @param employee
	 */
	public Employee loadEmployeeByLogin(Employee employee) throws Exception {
		Employee _tmpEmployee = null;
		try {
			if (employee != null) {
				_tmpEmployee = employeeDao.loadEmployeeByLogin(employee);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpEmployee;
	}

	/**
	 * 
	 * @param employee
	 */
	public boolean updateEmployeeRoles(Employee employee) throws Exception {
		boolean _tmpReturn = false;
		try {
			if (employee != null) {
				_tmpReturn = employeeDao.updateEmployeeRoles(employee);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param associated
	 * @param employee
	 */
	public List<Role> loadEmployeeRoles(boolean associated, Employee employee)
			throws Exception {
		List<Role> roles = null;
		try {
			if (employee != null) {
				roles = new ArrayList<Role>();
				roles = employeeDao.loadEmployeeRoles(associated, employee);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return roles;
	}

	/**
	 * 
	 * @param employee
	 */
	public Employee authenticate(Employee employee) throws Exception {
		Employee _tmpEmployee = null;
		try {
			_tmpEmployee = employeeDao.loadEmployeeByLogin(employee);

			if (_tmpEmployee == null || _tmpEmployee.getPassword() == null
					|| _tmpEmployee.getPassword().equals("")) {
				log.warn("Unsuccessful authentication try. Login not valid");
				return null;
			}

			Encryption _tmpEncryption = new Encryption("DESede");
			String _tmpPassword = _tmpEncryption.decrypt(_tmpEmployee
					.getPassword());
			_tmpEmployee.setPassword(_tmpPassword);

			if (!_tmpEmployee.getPassword().equals(employee.getPassword())) {

				log.warn("Unsuccessful authentication try");
				_tmpEmployee = null;
			}else{
				_tmpEmployee.setPassword(null);
			}
		} catch (Exception e) {
			log.error("autenticarUsuario(Negocio)", e);
			throw e;
		}
		return _tmpEmployee;
	}
	
	public List<Option> loadOptions(boolean asociated, Employee employee) throws Exception {
		List<Option> options = null;
		try {
			if (employee != null) {
				options = employeeDao.loadOptions(asociated, employee);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return options;
	}

	public IEmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * 
	 * @param employee
	 * @throws Exception 
	 */
	public boolean changePassword(Employee employee) throws Exception {
		try {
			employee.setLastChangePasswd(new Date());
			if(saveEmployee(employee) > 0){
				return true;
			}
		} catch (Exception e) {
			log.error("error in changePassword method", e);
			throw e;
		}
		return false;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeDao(IEmployeeDao newVal) {
		employeeDao = newVal;
	}

	/**
	 * 
	 * @param employee
	 */
	public boolean authorize(Employee employee, String url) {
		return false;
	}
	public IPhoneLogic getPhoneLogic() {
		return phoneLogic;
	}
	public void setPhoneLogic(IPhoneLogic phoneLogic) {
		this.phoneLogic = phoneLogic;
	}
	
	public Employee decrypt(Employee employee) {		
		String _tmpPassword = null;
		try {
			Encryption _tmpEncryption = new Encryption("DESede");
			_tmpPassword = _tmpEncryption.decrypt(employee.getPassword());
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employee.setPassword(_tmpPassword);
		return employee;
	}
	
	@Override
	public Employee loadEmployeeById(Employee employee) throws Exception {
		Employee _tmpEmployee = null;
		try {
			if (employee != null) {
				_tmpEmployee = employeeDao.loadEmployeeById(employee);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpEmployee;
	}

}