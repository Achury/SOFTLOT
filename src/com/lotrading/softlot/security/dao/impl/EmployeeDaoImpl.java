package com.lotrading.softlot.security.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.security.dao.IEmployeeDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:53 a.m.
 */
public class EmployeeDaoImpl extends DAOTemplate implements IEmployeeDao {

	private Log log = LogFactory.getLog(EmployeeDaoImpl.class);

	public EmployeeDaoImpl() {

	}

	/**
	 * 
	 * @param employee
	 */
	public List<Employee> searchEmployees(Employee employee) throws Exception {
		List<Employee> employees = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_employees_SEARCH(?,?,?)}";
			cst = conn.prepareCall(sql);
			if (employee != null) {
				if (employee.getFirstName() != null) {
					cst.setString(1, employee.getFirstName());
				} else {
					cst.setString(1, "");
				}

				if (employee.getLastName() != null) {
					cst.setString(2, employee.getLastName());
				} else {
					cst.setString(2, "");
				}
				if (employee.getLogin() != null) {
					cst.setString(3, employee.getLogin());
				} else {
					cst.setString(3, "");
				}
			}
			rs = cst.executeQuery();

			if (rs.next()) {
				employees = new ArrayList<Employee>();
				do {
					Employee _tmpEmployee = new Employee();
					_tmpEmployee.setEmployeeId(rs.getInt(1));
					_tmpEmployee.setLogin(rs.getString(2));
					_tmpEmployee.setPassword(rs.getString(3));
					_tmpEmployee.setFirstName(rs.getString(4));
					_tmpEmployee.setLastName(rs.getString(5));
					_tmpEmployee.setEmail(rs.getString(6));
					_tmpEmployee.getDepartmentLotId().setValueId(rs.getInt(7));
					_tmpEmployee.getDepartmentLotId().setValue1(rs.getString(8));
					_tmpEmployee.setEnteredDate(rs.getTimestamp(9));
					_tmpEmployee.setUpdatedDate(rs.getTimestamp(10));
					_tmpEmployee.setDisabledDate(rs.getTimestamp(11));
					_tmpEmployee.setLastChangePasswd(rs.getTimestamp(12));

					// ********* begin fill phone data *********
					_tmpEmployee.getWorkPhone().getType().setValueId(rs.getInt(13));				
					_tmpEmployee.getWorkPhone().setPhoneId(rs.getInt(14));
					_tmpEmployee.getWorkPhone().setCountryCode(rs.getString(15));
					_tmpEmployee.getWorkPhone().setAreaCode(rs.getString(16));
					_tmpEmployee.getWorkPhone().setPhoneNumber(rs.getString(17));
					_tmpEmployee.getWorkPhone().setPhoneExtension(rs.getString(18));
					_tmpEmployee.getWorkPhone().setEnteredDate(rs.getTimestamp(19));
					_tmpEmployee.getWorkPhone().setDisabledDate(rs.getTimestamp(20));					
					// ********* end fill phone data *********
					employees.add(_tmpEmployee);
					
					MasterValue _tmpMasterVal = new MasterValue();
					_tmpMasterVal.setValueId(rs.getInt(21));
					_tmpMasterVal.setValue1(rs.getString(22));
					_tmpEmployee.setRegion(_tmpMasterVal);
				} while (rs.next());
			}
			System.out.println("search Employee ");
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return employees;
	}

	/**
	 * 
	 * @param employee
	 */
	public Employee loadEmployeeByLogin(Employee employee) throws Exception {
		Employee _tmpEmployee = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_employee_LOAD_BY_LOGIN(?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, employee.getLogin());
			rs = cst.executeQuery();

			if (rs.next()) {
				_tmpEmployee = new Employee();

				_tmpEmployee.setEmployeeId(rs.getInt(1));
				_tmpEmployee.setLogin(rs.getString(2));
				_tmpEmployee.setPassword(rs.getString(3));
				_tmpEmployee.setFirstName(rs.getString(4));
				_tmpEmployee.setLastName(rs.getString(5));
				_tmpEmployee.setEmail(rs.getString(6));
				_tmpEmployee.getDepartmentLotId().setValueId(rs.getInt(7));
				_tmpEmployee.setEnteredDate(rs.getTimestamp(8));
				_tmpEmployee.setUpdatedDate(rs.getTimestamp(9));
				_tmpEmployee.setDisabledDate(rs.getTimestamp(10));
				_tmpEmployee.setPasswordExpired(rs.getBoolean(11));
				_tmpEmployee.setLastChangePasswd(rs.getTimestamp(12));
				
				// ********* begin fill phone data *********
				Phone _tmpPhone = new Phone();
				MasterValue _tmpMasterVal = new MasterValue();
				_tmpMasterVal.setValue1(rs.getString(13));
				_tmpPhone.setType(_tmpMasterVal);
				_tmpPhone.setPhoneId(rs.getInt(14));
				_tmpPhone.setCountryCode(rs.getString(15));
				_tmpPhone.setAreaCode(rs.getString(16));
				_tmpPhone.setPhoneNumber(rs.getString(17));
				_tmpPhone.setPhoneExtension(rs.getString(18));
				_tmpPhone.setEnteredDate(rs.getTimestamp(19));
				_tmpPhone.setDisabledDate(rs.getTimestamp(20));
				// ********* end fill phone data *********
				_tmpEmployee.setWorkPhone(_tmpPhone);
				
				_tmpMasterVal = new MasterValue();
				_tmpMasterVal.setValueId(rs.getInt(21));
				_tmpMasterVal.setValue1(rs.getString(22));
				_tmpEmployee.setRegion(_tmpMasterVal);
			}
			System.out.println("load Employee by login");
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return _tmpEmployee;
	}

	/**
	 * 
	 * @param employee
	 * @throws Exception
	 */
	public int createEmployee(Employee employee) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_employees_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql); 
			if(employee.getLogin().isEmpty() || employee.getFirstName().isEmpty() || 
					employee.getPassword().isEmpty() || employee.getLastName().isEmpty()){
				return createdId;
			}
			cst.setString(1, employee.getLogin());
			cst.setString(2, employee.getPassword());
			cst.setString(3, employee.getFirstName());
			cst.setString(4, employee.getLastName());
			cst.setString(5, employee.getEmail());
			cst.setInt(6, employee.getDepartmentLotId().getValueId());
			if(employee.getWorkPhone() != null){
				cst.setInt(7, employee.getWorkPhone().getPhoneId());
			}else{
				cst.setInt(7, 0);
			}	
			cst.setTimestamp(8, toTimeStamp(employee.getEnteredDate()));
			cst.setTimestamp(9, toTimeStamp(employee.getUpdatedDate()));
			cst.setTimestamp(10, toTimeStamp(employee.getLastChangePasswd()));
			cst.setString(11, employee.getPassAccess());
			cst.setInt(12, employee.getRegion().getValueId());
			cst.registerOutParameter(13, Types.INTEGER);
			
			if (cst.executeUpdate() > 0) {
				createdId = cst.getInt(13);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return createdId;
	}

	/**
	 * 
	 * @param employee
	 * @throws Exception
	 */
	public boolean updateEmployee(Employee employee) throws Exception {

		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_employees_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, employee.getLogin());
			cst.setString(2, employee.getPassword());
			cst.setString(3, employee.getFirstName());
			cst.setString(4, employee.getLastName());
			cst.setString(5, employee.getEmail());
			cst.setInt(6, employee.getDepartmentLotId().getValueId());
			if(employee.getWorkPhone() != null){
				cst.setInt(7, employee.getWorkPhone().getPhoneId());
			}else{
				cst.setInt(7, 0);
			}
			cst.setTimestamp(8, toTimeStamp(employee.getEnteredDate()));
			cst.setTimestamp(9, toTimeStamp(employee.getUpdatedDate()));
			if (employee.getDisabledDate() != null)
				cst.setTimestamp(10, toTimeStamp(employee.getDisabledDate()));
			else
				cst.setDate(10, null);
			cst.setTimestamp(11, toTimeStamp(employee.getLastChangePasswd()));
			cst.setString(12, employee.getPassAccess());
			cst.setInt(13, employee.getRegion().getValueId());
			 int _tmpRegistos = cst.executeUpdate();
			if (_tmpRegistos > 0) {
				updated = true;
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}

	/**
	 * Devuelve los roles asociados o no asociados al empleado dependiendo del
	 * valor del parámetro booleano.
	 * 
	 * @param associated
	 * @param employee
	 * @throws Exception
	 */
	public List<Role> loadEmployeeRoles(boolean associated, Employee employee)
			throws Exception {

		List<Role> roles = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_employees_LOAD_EMPLOYEE_ROLES(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, employee.getEmployeeId());
			cst.setBoolean(2, associated);
			rs = cst.executeQuery();

			if (rs.next()) {
				roles = new ArrayList<Role>();
				do {
					Role _tmpRole = new Role();
					_tmpRole.setRoleId(rs.getInt(1));
					_tmpRole.setName(rs.getString(2));
					_tmpRole.setDescription(rs.getString(3));
					_tmpRole.setEnteredDate(rs.getTimestamp(4));
					_tmpRole.setDisabledDate(rs.getTimestamp(5));
					roles.add(_tmpRole);
				} while (rs.next());
			}
			System.out.println("load Employee Roles id employee " + employee.getEmployeeId());
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return roles;
	}

	/**
	 * 
	 * @param employee
	 * @throws Exception
	 */
	public boolean updateEmployeeRoles(Employee employee) throws Exception {

		boolean updated = false;
		boolean delete = true;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql;
			sql = "{call lotradingdb.sp_employees_UPDATE_EMPLOYEE_ROLES(?,?,?)}";
			List<String> _tmpRoles = new ArrayList<String>();
			_tmpRoles = employee.getRoles();
			if(_tmpRoles.isEmpty()){
				cst = conn.prepareCall(sql);
				cst.setInt(1, employee.getEmployeeId());
				cst.setInt(2, 0);
				cst.setBoolean(3, delete);
				if (cst.executeUpdate() > 0) {
					updated = true;
				} else {
					return false;
				}
			}else{
				for (String _tmpRol : _tmpRoles) {				
					cst = conn.prepareCall(sql);
					cst.setInt(1, employee.getEmployeeId());
					cst.setInt(2, Integer.valueOf(_tmpRol));
					cst.setBoolean(3, delete);
					delete = false;
					if (cst.executeUpdate() > 0) {
						updated = true;
					} else {
						return false;
					}
				}
			}
			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return updated;
	}
	
	public List<Option> loadOptions(boolean asociated, Employee employee) throws Exception {
		List<Option> options = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_employees_LOAD_EMPLOYEE_OPTIONS(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setBoolean(1, asociated);
			cst.setInt(2, employee.getEmployeeId());
			rs = cst.executeQuery();
			if (rs.next()) {
				options = new ArrayList<Option>();
				do {
					Option _tmpOption = new Option();
					_tmpOption.setOptionId(rs.getInt(1));
					_tmpOption.setName(rs.getString(2));
					_tmpOption.setDescription(rs.getString(3));
					_tmpOption.setResource(new Resource(rs.getInt(4)));
					_tmpOption.setParent(rs.getBoolean(5));
					_tmpOption.setParentId(rs.getInt(6));
					_tmpOption.setOrder(rs.getInt(7));
					_tmpOption.setEnteredDate(rs.getTimestamp(8));
					_tmpOption.setDisabledDate(rs.getTimestamp(9));
					options.add(_tmpOption);
				} while (rs.next());
			}
			System.out.println("load Employee options id employee " + employee.getEmployeeId());
			rs.close();
			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return options;
		}

	@Override
	public Employee loadEmployeeById(Employee employee) throws Exception {
		Employee _tmpEmployee = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_employee_LOAD_BY_ID(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, employee.getEmployeeId());
			rs = cst.executeQuery();

			if (rs.next()) {
				_tmpEmployee = new Employee();

				_tmpEmployee.setEmployeeId(rs.getInt(1));
				_tmpEmployee.setLogin(rs.getString(2));
				//_tmpEmployee.setPassword(rs.getString(3));
				_tmpEmployee.setFirstName(rs.getString(4));
				_tmpEmployee.setLastName(rs.getString(5));
				_tmpEmployee.setEmail(rs.getString(6));
				_tmpEmployee.getDepartmentLotId().setValueId(rs.getInt(7));
				_tmpEmployee.setEnteredDate(rs.getTimestamp(8));
				_tmpEmployee.setUpdatedDate(rs.getTimestamp(9));
				_tmpEmployee.setDisabledDate(rs.getTimestamp(10));

				// ********* begin fill phone data *********
				/*Phone _tmpPhone = new Phone();
				MasterValue _tmpType = new MasterValue();
				_tmpType.setValue1(rs.getString(11));
				_tmpPhone.setType(_tmpType);
				_tmpPhone.setPhoneId(rs.getInt(12));
				_tmpPhone.setCountryCode(rs.getString(13));
				_tmpPhone.setAreaCode(rs.getString(14));
				_tmpPhone.setPhoneNumber(rs.getString(15));
				_tmpPhone.setPhoneExtension(rs.getString(16));
				_tmpPhone.setEnteredDate(rs.getDate(17));
				_tmpPhone.setDisabledDate(rs.getDate(18));
				// ********* end fill phone data *********
				_tmpEmployee.setWorkPhone(_tmpPhone);*/
			}
			System.out.println("load Employee by id");
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return _tmpEmployee;
		
	}
}