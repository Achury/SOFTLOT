package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbOtherInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbOtherInvoiceDao {

	/**
	 * 
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public int createAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception;

	/**
	 * 
	 * @param awbOtherInvoice
	 */
	public boolean updateAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception;

	/**
	 * 
	 * @param awbOtherInvoice
	 */
	public boolean deleteAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception;

	/**
	 * 
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public List<AwbOtherInvoice> saveAwbOtherInvoice(List<AwbOtherInvoice> awbOtherInvoiceList) throws Exception;
	
}