package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BLDeclaredValue;
import com.lotrading.softlot.LODepartment.shipments.entity.Bl;
import com.lotrading.softlot.LODepartment.shipments.entity.BlContainer;
import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;
import com.lotrading.softlot.LODepartment.shipments.entity.BlEEI;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;
import com.lotrading.softlot.LODepartment.shipments.entity.ItemProrated;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlLogic {

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public int saveBl(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<Bl> searchBl(Bl bl) throws Exception ;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<Bl> loadBlList(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public Bl loadBl(Bl bl) throws Exception ;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<Bl> loadHouseBls(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public boolean updateHouse(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<Bl> updateBlHouseList(List<Bl> blHousesList) throws Exception;

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlItem> loadBlItems(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlPalletizedItem> loadBlPalletizedItems(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlFreightInvoice> loadFreightInvoices(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlOtherInvoice> loadOtherInvoices(Bl bl) throws Exception;

	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlInlandCS> loadInlandCS(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlUnCode> loadUnCodes(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public Bl loadCostsSales(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlCostSale> loadInitialCostsSales(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlEEI> loadEEIs(Bl bl)throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BlContainer> loadBlContainers(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	//public Bl fillAutomaticCostsSales(Bl bl);
	
	/**
	 * 
	 * @param bl_target 
	 * @param bl_Rates
	 * @throws Exception 
	 */
	public Bl fillAutomaticCosts(Bl bl_target, Bl bl_Rates);
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public Bl fillAutomaticSales(Bl bl);
	
	public List<CarrierRate> loadCarrierRates(Bl bl, MasterValue rateType);	
	
	public List<ClientRate> loadClientRates(Bl bl, MasterValue rateType) ;
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public Bl loadInitialOtherCostsSalesFromClientAndCarrier(Bl bl);
	
	
	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public List<BLDeclaredValue> loadBlDeclaredValues(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param carrierPort
	 * @throws Exception 
	 */
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception;
	
	/**
	 * 
	 * @param List<ItemProrated>
	 * @throws Exception 
	 */
	public List<ItemProrated> fillBlItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception;
	

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public String loadWhRemarks(Bl bl) throws Exception;

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public List<BlInlandCS> createInlandCSFromClientOrder(BlItem blItem) throws Exception;
}