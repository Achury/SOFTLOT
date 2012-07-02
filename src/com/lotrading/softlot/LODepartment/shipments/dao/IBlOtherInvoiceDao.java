package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlOtherInvoiceDao {

	/**
	 * 
	 * @param blOtherInvoice
	 * @throws Exception 
	 */
	public int createBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception;

	/**
	 * 
	 * @param blOtherInvoice
	 */
	public boolean updateBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception;

	/**
	 * 
	 * @param blOtherInvoice
	 */
	public boolean deleteBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception;
	
	/**
	 * 
	 * @param blOtherInvoiceList
	 */
	public List<BlOtherInvoice> saveBlOtherInvoices(List<BlOtherInvoice> blOtherInvoiceList) throws Exception ;

}