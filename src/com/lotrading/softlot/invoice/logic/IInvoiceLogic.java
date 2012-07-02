package com.lotrading.softlot.invoice.logic;

import java.util.List;

import com.lotrading.softlot.invoice.entity.Invoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public interface IInvoiceLogic {

	public Invoice loadInvoice(Invoice invoice) throws Exception;
	
	public List<Invoice> loadInvoicesList(Invoice invoice) throws Exception;
	
}
