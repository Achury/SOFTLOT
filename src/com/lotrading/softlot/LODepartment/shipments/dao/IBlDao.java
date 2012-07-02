package com.lotrading.softlot.LODepartment.shipments.dao;

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
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlDao {

	/**
	 * 
	 * @param bl
	 * @throws Exception 
	 */
	public int createBl(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public boolean updateBl(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public List<Bl> searchBl(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<Bl> loadBlList(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public Bl loadBl(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<Bl> loadHouseBls(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public boolean updateBlHouse(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<Bl> updateBlHouseList(List<Bl> blHousesList) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public List<BlItem> loadBlItems(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlPalletizedItem> loadBlPalletizedItems(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public List<BlFreightInvoice> loadFreightInvoices(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public List<BlOtherInvoice> loadOtherInvoices(Bl bl) throws Exception;

	
	/**
	 * 
	 * @param bl
	 */
	public List<BlInlandCS> loadInlandsCS(Bl bl) throws Exception;

	/**
	 * 
	 * @param bl
	 */
	public List<BlUnCode> loadUnCodes(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlCostSale> loadCostsSales(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlCostSale> loadInitialCostsSales(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlEEI> loadEEIs(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlContainer> loadBlContainers(Bl bl) throws Exception;
	

	/**
	 * 
	 * @param clientRatesPort
	 */
	public List<ClientRate> loadClientRates(ClientRatesPort clientRatesPort) throws Exception;
	
	/**
	 * 
	 * @param carrierPorts
	 */
	public List<CarrierRate> loadCarrierRates(CarrierPorts carrierPorts) throws Exception;
	
	/**
	 * 
	 * @param bl
	 */
	public List<BLDeclaredValue> loadBlDeclaredValues(Bl bl) throws Exception;
	
	/**
	 * 
	 * @param carrierPort
	 */
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception;
	
	/**
	 * 
	 * @param itemsProrated
	 */
	public List<ItemProrated> fillBlItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception;
	
	/**
	 * 
	 * @param blItem
	 */
	public List<BlInlandCS> createInlandCSFromClientOrder(BlItem blItem)throws Exception;

}