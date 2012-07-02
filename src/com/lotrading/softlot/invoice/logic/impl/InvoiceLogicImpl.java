package com.lotrading.softlot.invoice.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.invoice.dao.IInvoiceDao;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.logic.IInvoiceLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public class InvoiceLogicImpl implements IInvoiceLogic{

	private Log log = LogFactory.getLog(InvoiceLogicImpl.class);
	private IInvoiceDao invoiceDao;
	
	public Invoice loadInvoice(Invoice invoice) throws Exception {
		try {
			if (invoice != null){
				invoice = invoiceDao.loadInvoice(invoice);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return invoice;
	}

	public IInvoiceDao getInvoiceDao() {
		return invoiceDao;
	}

	public void setInvoiceDao(IInvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}

	public List<Invoice> loadInvoicesList(Invoice invoice) throws Exception {
		List<Invoice> invoicesList = null;
		try {
			if (invoice != null){
				invoicesList = invoiceDao.loadInvoicesList(invoice);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return invoicesList;
	}
	
}
