package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbFreightInvoiceLogic {

	/**
	 * 
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public List<AwbFreightInvoice> saveFreightInvoice(List<AwbFreightInvoice> awbFreightInvoiceList) throws Exception;

	/**
	 * 
	 * @param awbFreightInvoice
	 */
	public boolean deleteFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception;

}