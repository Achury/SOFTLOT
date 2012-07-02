package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlFreightInvoiceLogic {

	/**
	 * 
	 * @param blFreightInvoice
	 * @throws Exception 
	 */
	public List<BlFreightInvoice> saveFreightInvoices(List<BlFreightInvoice> blFreightInvoiceList) throws Exception;

	/**
	 * 
	 * @param blFreightInvoice
	 */
	public boolean deleteFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception;

}