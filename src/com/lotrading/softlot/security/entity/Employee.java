package com.lotrading.softlot.security.entity;
import java.util.Date;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;

/**
 * Clase que se utiliza para encapsular los datos de los empleados de LO Trading.
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:53 a.m.
 */
public class Employee {

	/**
	 * Identificador primario del empleado
	 */
	private int employeeId;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private MasterValue departmentLotId;
	private Phone workPhone;
	private Date enteredDate;
	private Date updatedDate;
	private Date disabledDate;
	private boolean passwordExpired;
	private Date lastChangePasswd;
	private String passAccess;
	private List roles;
	private MasterValue region;

	public Employee(){
		workPhone = new Phone();
		workPhone.setType(new MasterValue(Constants.MASTER_VALUE_PHONE_TYPE_TEL));
		departmentLotId = new MasterValue();
		region = new MasterValue();
	}

	public Employee(int  employeeId){
		this.employeeId = employeeId;
	}

	/**
	 * Identificador primario del empleado
	 */
	public int getEmployeeId(){
		return employeeId;
	}

	/**
	 * Identificador primario del empleado
	 * 
	 * @param newVal
	 */
	public void setEmployeeId(int newVal){
		employeeId = newVal;
	}

	public String getLogin(){
		return login;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLogin(String newVal){
		login = newVal;
	}

	public String getPassword(){
		return password;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPassword(String newVal){
		password = newVal;
	}

	public String getFirstName(){
		return firstName;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFirstName(String newVal){
		firstName = newVal;
	}

	public String getLastName(){
		return lastName;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLastName(String newVal){
		lastName = newVal;
	}

	public String getEmail(){
		return email;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmail(String newVal){
		email = newVal;
	}

	public MasterValue getDepartmentLotId(){
		return departmentLotId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDepartmentLotId(MasterValue newVal){
		departmentLotId = newVal;
	}


	public java.util.Date getEnteredDate(){
		return enteredDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEnteredDate(java.util.Date newVal){
		enteredDate = newVal;
	}

	public java.util.Date getUpdatedDate(){
		return updatedDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUpdatedDate(java.util.Date newVal){
		updatedDate = newVal;
	}

	public List getRoles(){
		return roles;
	}

	public Phone getWorkPhone(){
		return workPhone;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRoles(List newVal){
		roles = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setWorkPhone(Phone newVal){
		workPhone = newVal;
	}

	public void setDisabledDate(Date date) {
		disabledDate = date;
	}

	public java.util.Date getDisabledDate() {
		return disabledDate;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public Date getLastChangePasswd() {
		return lastChangePasswd;
	}

	public void setLastChangePasswd(Date lastChangePasswd) {
		this.lastChangePasswd = lastChangePasswd;
	}

	public String getPassAccess() {
		return passAccess;
	}

	public void setPassAccess(String passAccess) {
		this.passAccess = passAccess;
	}

	public MasterValue getRegion() {
		return region;
	}

	public void setRegion(MasterValue region) {
		this.region = region;
	}

}