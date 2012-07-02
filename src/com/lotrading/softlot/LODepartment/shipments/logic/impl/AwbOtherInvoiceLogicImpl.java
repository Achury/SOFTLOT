package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbOtherInvoiceDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbOtherInvoiceLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbOtherInvoiceLogicImpl implements IAwbOtherInvoiceLogic {

	private IAwbOtherInvoiceDao awbOtherInvoiceDao;
	private Log log = LogFactory.getLog(AwbOtherInvoiceLogicImpl.class);
	
	public AwbOtherInvoiceLogicImpl(){

	}

	/**
	 * 
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public List<AwbOtherInvoice> saveAwbOtherInvoice(List<AwbOtherInvoice> awbOtherInvoiceList) throws Exception{
		if(awbOtherInvoiceList != null){
			try {
				awbOtherInvoiceList = awbOtherInvoiceDao.saveAwbOtherInvoice(awbOtherInvoiceList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbOtherInvoiceList;
	}

	/**
	 * 
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public boolean deleteAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception{
		boolean deleted = false;
		if(awbOtherInvoice != null){
			try {
				deleted = awbOtherInvoiceDao.deleteAwbOtherInvoice(awbOtherInvoice);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbOtherInvoiceDao getAwbOtherInvoiceDao() {
		return awbOtherInvoiceDao;
	}

	public void setAwbOtherInvoiceDao(IAwbOtherInvoiceDao awbOtherInvoiceDao) {
		this.awbOtherInvoiceDao = awbOtherInvoiceDao;
	}

}