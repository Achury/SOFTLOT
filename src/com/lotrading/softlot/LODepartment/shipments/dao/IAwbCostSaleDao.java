package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 04-Feb-2012 01:32:00 PM
 */
public interface IAwbCostSaleDao {

	/**
	 * 
	 * @param awbCostSale
	 * @throws Exception 
	 */
	public int createAwbCostSale(AwbCostSale awbCostSale) throws Exception;

	/**
	 * 
	 * @param awbCostSale
	 * @throws Exception
	 */
	public boolean updateAwbCostSale(AwbCostSale awbCostSale) throws Exception;
	
	
	public List<AwbCostSale> saveAwbCostSale(List<AwbCostSale> awbCostSales) throws Exception;
	
	public boolean deleteCostSale(AwbCostSale awbCostSale) throws Exception;
}
