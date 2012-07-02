package com.lotrading.softlot.LODepartment.shipments.logic;

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
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbLogic {

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public Awb saveAwb(Awb awb) throws Exception;

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
	
	public List<Awb> loadHouseAwbs(Awb awb) throws Exception;
	
	public boolean updateHouse(Awb awb) throws Exception;
	
	public List<Awb> updateAwbHouseList(List<Awb> awbHousesList) throws Exception;
	
	/**
	 * 
	 * @param awb
	 */
	public List<AwbItem> loadAwbItems(AwbItem awbItem) throws Exception;

	public List<AwbPalletizedItem> loadAwbPalletizedItems(Awb awb) throws Exception;
	
	/**
	 * 
	 * @param awb
	 */
	public List<AwbFreightInvoice> loadFreightInvoices(AwbFreightInvoice awbFreightInvoice) throws Exception;

	/**
	 * 
	 * @param awb
	 */
	public List<AwbOtherInvoice> loadOtherInvoices(AwbOtherInvoice awbOtherInvoice) throws Exception;

	/**
	 * 
	 * @param awb
	 */
	public List<AwbInlandCS> loadInlandCS(AwbInlandCS awbInlandCS) throws Exception;

	/**
	 * 
	 * @param awb
	 */
	public List<AwbUnCode> loadUnCodes(AwbUnCode awbUnCode) throws Exception;
	
	public Awb loadCostsSales(Awb awb) throws Exception;
	
	public List<AwbCostSale> loadInitialCostsSales(Awb awb) throws Exception;
	
	public List<AwbEEI> loadEEIs(AwbEEI awbEEI) throws Exception;
	
	//public List<ClientRate> loadClientRates(ClientRatesPort clientRatesPort) throws Exception;
	
	//public List<CarrierRate> loadCarrierRates(CarrierPorts carrierPorts) throws Exception;
	
	//public Awb fillAutomaticCostsSales(Awb awb);  se paso para el control
	
	public Awb recalculateAwbSales(Awb awb, List<ClientRate> clientRates);
	
	public Awb recalculateAwbCosts(Awb awb, List<CarrierRate> carrierRates);
	
	public Awb recalculatePrincipalCosts(Awb awb, List<CarrierRate> carrierRates);
	 
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception;
	
	public List<AwbDeclaredValue> loadAwbDeclaredValues(Awb awb) throws Exception;
	
	public List<CarrierRate> loadCarrierRates(Awb awb);
	
	public List<ClientRate> loadClientRates(Awb awb);
	
	public List<AwbItem> loadAwbSummarizedItems(Awb awb);
	
	public double calculateTotalVolumeM3 (Awb awb);
	
	public CarrierAwbNumber loadAwbNumberFromCarriers(Awb awb) throws Exception;
	
	/**
	 * 
	 * @param List<ItemProrated>
	 * @throws Exception 
	 */
	public List<ItemProrated> fillAwbItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception;
}