package com.lotrading.softlot.LODepartment.shipments.dao;
import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlFreightInvoiceDao {

	/**
	 * 
	 * @param blFreightInvoice
	 * @throws Exception 
	 */
	public int createBlFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception;

	/**
	 * 
	 * @param blFreightInvoice
	 */
	public boolean updateBlFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception;

	/**
	 * 
	 * @param blFreightInvoice
	 */
	public boolean deleteBlFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception;
	
	/**
	 * 
	 * @param blFreightInvoiceList
	 */	
	public List<BlFreightInvoice> saveBlFreightInvoices(List<BlFreightInvoice> blFreightInvoiceList) throws Exception ;

}