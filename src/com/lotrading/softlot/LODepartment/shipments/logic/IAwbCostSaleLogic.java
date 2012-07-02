package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbCostSaleLogic {

	public List<AwbCostSale> saveAwbCostSale(List<AwbCostSale> awbCostSales) throws Exception;
	
	public boolean deleteCostSale(AwbCostSale awbCostSale) throws Exception;
	
}
