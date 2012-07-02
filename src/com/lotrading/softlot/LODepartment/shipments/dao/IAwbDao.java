package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.Awb;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbDeclaredValue;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbEEI;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;
import com.lotrading.softlot.LODepartment.shipments.entity.ItemProrated;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbDao {

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public Awb createAwb(Awb awb) throws Exception;

	/**
	 * 
	 * @param awb
	 */
	public boolean updateAwb(Awb awb) throws Exception;

	/**
	 * 
	 * @param awb
	 */
	public List<Awb> searchAwb(Awb awb) throws Exception;

	public List<Awb> loadAwbList(Awb awb) throws Exception;
	
	/**
	 * 
	 * @param awb
	 */
	public Awb loadAwb(Awb awb) throws Exception;

	/**
	 * 
	 * @param awbItem
	 */
	public List<AwbItem> loadAwbItems(AwbItem awbItem) throws Exception;

	public List<AwbPalletizedItem> loadAwbPalletizedItems(Awb awb) throws Exception;
	
	public List<Awb> loadHouseAwbs(Awb awb) throws Exception;
	
	public boolean updateAwbHouse(Awb awb) throws Exception;
	
	public List<Awb> updateAwbHouseList(List<Awb> awbHousesList) throws Exception;
	
	/**
	 * 
	 * @param awbFreightInvoice
	 */
	public List<AwbFreightInvoice> loadFreightInvoices(AwbFreightInvoice awbFreightInvoice) throws Exception;

	/**
	 * 
	 * @param awbOtherInvoice
	 */
	public List<AwbOtherInvoice> loadOtherInvoices(AwbOtherInvoice awbOtherInvoice) throws Exception;


	/**
	 * 
	 * @param awbInlandCS
	 */
	public List<AwbInlandCS> loadInlandsCS(AwbInlandCS awbInlandCS) throws Exception;

	/**
	 * 
	 * @param awbUnCode
	 */
	public List<AwbUnCode> loadUnCodes(AwbUnCode awbUnCode) throws Exception;
	
	/**
	 * 
	 * @param awbCostSale
	 */
	public List<AwbCostSale> loadCostsSales(AwbCostSale awbCostSale) throws Exception;
	
	/**
	 * 
	 * @param awbCostSale
	 */
	public List<AwbCostSale> loadInitialCostsSales(Awb awb) throws Exception;
	
	/**
	 * 
	 * @param awbEEI
	 */
	public List<AwbEEI> loadEEIs(AwbEEI awbEEI) throws Exception;

	public List<ClientRate> loadClientRates(ClientRatesPort clientRatesPort) throws Exception;
	
	public List<CarrierRate> loadCarrierRates(CarrierPorts carrierPorts) throws Exception;
	
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception;
	
	public List<AwbDeclaredValue> loadAwbDeclaredValues(Awb awb) throws Exception;
	
	/**
	 * 
	 * @param itemsProrated
	 */
	public List<ItemProrated> fillAwbItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception;
}