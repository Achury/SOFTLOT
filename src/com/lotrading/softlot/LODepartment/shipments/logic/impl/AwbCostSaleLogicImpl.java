package com.lotrading.softlot.LODepartment.shipments.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbCostSaleDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbCostSaleLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbCostSaleLogicImpl implements IAwbCostSaleLogic{

	private IAwbCostSaleDao awbCostSaleDao;
	private Log log = LogFactory.getLog(AwbCostSaleLogicImpl.class);
	
	public List<AwbCostSale> saveAwbCostSale(List<AwbCostSale> awbCostSales) throws Exception {
		if(awbCostSales != null){
			try {
				awbCostSaleDao.saveAwbCostSale(awbCostSales);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbCostSales;
	}
	
	public boolean deleteCostSale(AwbCostSale awbCostSale) throws Exception {
		boolean deleted = false;
		if(awbCostSale != null){
			try {
				deleted = awbCostSaleDao.deleteCostSale(awbCostSale);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbCostSaleDao getAwbCostSaleDao() {
		return awbCostSaleDao;
	}

	public void setAwbCostSaleDao(IAwbCostSaleDao awbCostSaleDao) {
		this.awbCostSaleDao = awbCostSaleDao;
	}

}
