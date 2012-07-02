package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbFreightInvoiceDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbFreightInvoiceLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbFreightInvoiceLogicImpl implements IAwbFreightInvoiceLogic {

	private IAwbFreightInvoiceDao awbFreightInvoiceDao;
	private Log log = LogFactory.getLog(AwbFreightInvoiceLogicImpl.class);
	
	public AwbFreightInvoiceLogicImpl(){

	}

	/**
	 * 
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public List<AwbFreightInvoice> saveFreightInvoice(List<AwbFreightInvoice> awbFreightInvoiceList) throws Exception{
		if(awbFreightInvoiceList != null){
			try {
				awbFreightInvoiceDao.saveAwbFreightInvoice(awbFreightInvoiceList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbFreightInvoiceList;
	}

	/**
	 * 
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public boolean deleteFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception{
		boolean deleted = false;
		if(awbFreightInvoice != null){
			try {
				deleted = awbFreightInvoiceDao.deleteAwbFreightInvoice(awbFreightInvoice);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbFreightInvoiceDao getAwbFreightInvoiceDao() {
		return awbFreightInvoiceDao;
	}

	public void setAwbFreightInvoiceDao(IAwbFreightInvoiceDao awbFreightInvoiceDao) {
		this.awbFreightInvoiceDao = awbFreightInvoiceDao;
	}

}