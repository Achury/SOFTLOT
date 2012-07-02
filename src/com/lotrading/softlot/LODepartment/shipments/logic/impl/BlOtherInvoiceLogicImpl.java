package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlOtherInvoiceDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlOtherInvoiceLogic;

/**
 * @author Marlon Pineda
 * @version 1.0
 * @created 12-Jan-2012 12:00:00 AM
 */
public class BlOtherInvoiceLogicImpl implements IBlOtherInvoiceLogic {
	private IBlOtherInvoiceDao blOtherInvoiceDao;
	private Log log = LogFactory.getLog(BlOtherInvoiceLogicImpl.class);
	
	public BlOtherInvoiceLogicImpl(){

	}

	/**
	 * 
	 * @param blOtherInvoice
	 */
	public List<BlOtherInvoice> saveBlOtherInvoices(List<BlOtherInvoice> blOtherInvoiceList) throws Exception{
		if(blOtherInvoiceList != null){
			try {
				blOtherInvoiceList = blOtherInvoiceDao.saveBlOtherInvoices(blOtherInvoiceList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blOtherInvoiceList;
	}

	/**
	 * 
	 * @param blOtherInvoice
	 */
	public boolean deleteBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception{
		boolean deleted = false;
		if(blOtherInvoice != null){
			if (blOtherInvoice.getOtherInvoiceId()>0){
				try {
					deleted = blOtherInvoiceDao.deleteBlOtherInvoice(blOtherInvoice);
				} catch (Exception e) {
					log.error("An Exception has been thrown " + e);
					e.printStackTrace();
					throw e;
				}
			}
		}
		return deleted;
	}

	public IBlOtherInvoiceDao getBlOtherInvoiceDao() {
		return blOtherInvoiceDao;
	}

	public void setBlOtherInvoiceDao(IBlOtherInvoiceDao blOtherInvoiceDao) {
		this.blOtherInvoiceDao = blOtherInvoiceDao;
	}

}