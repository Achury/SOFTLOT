package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlFreightInvoiceDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlFreightInvoiceLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlFreightInvoiceLogicImpl implements IBlFreightInvoiceLogic {
	private IBlFreightInvoiceDao blFreightInvoiceDao;
	private Log log = LogFactory.getLog(BlFreightInvoiceLogicImpl.class);
	
	public BlFreightInvoiceLogicImpl(){

	}

	/**
	 * 
	 * @param blFreightInvoice
	 */
	public List<BlFreightInvoice> saveFreightInvoices(List<BlFreightInvoice> blFreightInvoiceList) throws Exception{
		if(blFreightInvoiceList != null){
			try {
				blFreightInvoiceDao.saveBlFreightInvoices(blFreightInvoiceList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blFreightInvoiceList;
	}

	/**
	 * 
	 * @param blFreightInvoice
	 */
	public boolean deleteFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception{
		boolean deleted = false;
		if(blFreightInvoice != null){
			if (blFreightInvoice.getFreightInvoiceId()>0){
				try {
					deleted = blFreightInvoiceDao.deleteBlFreightInvoice(blFreightInvoice);
				} catch (Exception e) {
					log.error("An Exception has been thrown " + e);
					e.printStackTrace();
					throw e;
				}
			}
		}
		return deleted;
	}

	public IBlFreightInvoiceDao getBlFreightInvoiceDao() {
		return blFreightInvoiceDao;
	}

	public void setBlFreightInvoiceDao(IBlFreightInvoiceDao blFreightInvoiceDao) {
		this.blFreightInvoiceDao = blFreightInvoiceDao;
	}

}