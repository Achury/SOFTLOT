package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlOtherInvoiceLogic {

	/**
	 * 
	 * @param blOtherInvoiceList
	 * @throws Exception 
	 */
	public List<BlOtherInvoice> saveBlOtherInvoices(List<BlOtherInvoice> blOtherInvoiceList) throws Exception;

	/**
	 * 
	 * @param blOtherInvoice
	 * @throws Exception 
	 */
	public boolean deleteBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception;

}