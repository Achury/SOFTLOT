package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbFreightInvoiceDao {

	/**
	 * 
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public int createAwbFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception;

	/**
	 * 
	 * @param awbFreightInvoice
	 */
	public boolean updateAwbFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception;

	/**
	 * 
	 * @param awbFreightInvoice
	 */
	public boolean deleteAwbFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception;

	public List<AwbFreightInvoice> saveAwbFreightInvoice(List<AwbFreightInvoice> awbFreightInvoiceList) throws Exception;
	
}