package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbDao;
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
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbLogic;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.logic.IcarrierLogic;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.UtilFunctions;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbLogicImpl implements IAwbLogic {

	private IAwbDao awbDao;
	private IcarrierLogic carrierLogic;
	private Log log = LogFactory.getLog(AwbLogicImpl.class);
		
	public AwbLogicImpl(){

	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public Awb saveAwb(Awb awb) throws Exception{
		if(awb != null){
			try {
				if(awb.getAwbId() <= 0){
					awb = awbDao.createAwb(awb);
				}else{
					awbDao.updateAwb(awb);
				}
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awb;
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<Awb> searchAwb(Awb awb) throws Exception{
		List<Awb> awbList = null;
		if(awb != null){
			try {
				awbList = awbDao.searchAwb(awb);		
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbList;
	}
	
	public List<Awb> loadAwbList(Awb awb) throws Exception{
		List<Awb> awbList = null;
		try {
			awbList = awbDao.loadAwbList(awb);		
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
			throw e;
		}
		return awbList;
	}
	
	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public Awb loadAwb(Awb awb) throws Exception{
		if(awb != null){
			try {
				awb = awbDao.loadAwb(awb);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awb;		
	}
	
	public List<Awb> loadHouseAwbs(Awb awb) throws Exception{
		List<Awb> awbHouseList = null;
		if(awb != null){
			try {
				awbHouseList = awbDao.loadHouseAwbs(awb);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbHouseList;
	}
	
	public boolean updateHouse(Awb awb) throws Exception{
		boolean updated = false;
		if(awb != null){
			try {
				updated = awbDao.updateAwbHouse(awb);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return updated;
	}
	
	public List<Awb> updateAwbHouseList(List<Awb> awbHousesList) throws Exception{
		if(awbHousesList != null){
			try {
				awbHousesList = awbDao.updateAwbHouseList(awbHousesList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbHousesList;
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<AwbItem> loadAwbItems(AwbItem awbItem) throws Exception{
		List<AwbItem> awbItems = null;
		if(awbItem != null){
			try {
				awbItems = awbDao.loadAwbItems(awbItem);	
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbItems;
	}
	
	/**
	 * 
	 * @param awb
	 */
	public List<AwbPalletizedItem> loadAwbPalletizedItems(Awb awb) throws Exception{
		List<AwbPalletizedItem> _tmpAwbPalletizedItems = null; 
		if(awb != null){
			try {
				_tmpAwbPalletizedItems = awbDao.loadAwbPalletizedItems(awb);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpAwbPalletizedItems;
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<AwbFreightInvoice> loadFreightInvoices(AwbFreightInvoice awbFreightInvoice) throws Exception{
		List<AwbFreightInvoice> awbFreightInvoices = null;
		if(awbFreightInvoice != null){
			try {
				awbFreightInvoices = awbDao.loadFreightInvoices(awbFreightInvoice);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbFreightInvoices;
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<AwbOtherInvoice> loadOtherInvoices(AwbOtherInvoice awbOtherInvoice) throws Exception{
		List<AwbOtherInvoice> awbOtherInvoices = null;
		if(awbOtherInvoice != null){
			try {
				awbOtherInvoices = awbDao.loadOtherInvoices(awbOtherInvoice);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbOtherInvoices;
	}


	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<AwbInlandCS> loadInlandCS(AwbInlandCS awbInlandCS) throws Exception{
		List<AwbInlandCS> awbInlandCSList = null;
		if(awbInlandCS != null){
			try {
				awbInlandCSList = awbDao.loadInlandsCS(awbInlandCS);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbInlandCSList; 
	}

	/**
	 * 
	 * @param awb
	 * @throws Exception 
	 */
	public List<AwbUnCode> loadUnCodes(AwbUnCode awbUnCode) throws Exception{
		List<AwbUnCode> awbUnCodes = null;
		if(awbUnCode != null){
			try {
				awbUnCodes = awbDao.loadUnCodes(awbUnCode);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbUnCodes;
	}

	public IAwbDao getAwbDao() {
		return awbDao;
	}

	public void setAwbDao(IAwbDao awbDao) {
		this.awbDao = awbDao;
	}

	public Awb loadCostsSales(Awb awb) throws Exception {
		if(awb != null){
			if(awb.getAwbId() > 0){
				try {
					List<AwbCostSale> awbCostsSales = awbDao.loadCostsSales(new AwbCostSale(awb.getAwbId())); // Carga los CostSales almacenados.
					awb.setAwbOtherCostsSales(new ArrayList<AwbCostSale>());
					awb.setAwbCostsSales(new ArrayList<AwbCostSale>());
					for(AwbCostSale _tmpCS : awbCostsSales){
						if(_tmpCS.isOtherCost()){
							awb.getAwbOtherCostsSales().add(_tmpCS);
						}else{
							awb.getAwbCostsSales().add(_tmpCS);
						}
					}
				} catch (Exception e) {
					log.error("An Exception has been thrown " + e);
					e.printStackTrace();
					throw e;
				}
			} else {
				awb.setAwbCostsSales(awbDao.loadInitialCostsSales(awb));
				awb.setAwbOtherCostsSales(new ArrayList<AwbCostSale>());
			}		
		}
		return awb;
	}
	
	public List<AwbCostSale> loadInitialCostsSales(Awb awb) throws Exception {
		List<AwbCostSale> awbCostsSales = null;
		if(awb != null){
			awbCostsSales = awbDao.loadInitialCostsSales(awb);
		}
		return awbCostsSales;
	}

	
	public List<AwbEEI> loadEEIs(AwbEEI awbEEI) throws Exception {
		List<AwbEEI> awbEEIList= new ArrayList<AwbEEI>();
		if(awbEEI != null){
			try {
				awbEEIList = awbDao.loadEEIs(awbEEI);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbEEIList;
	}
	
	
	public Awb recalculateAwbSales(Awb awb, List<ClientRate> clientRates){
		
		
		/* Esto lo que hace es recorrer la lista de clientRates y compararlo con la de costSales 
		 * si el rubro de clientRates existe en costSales y no es OtherCost entonces se escribe el valor del rate del clientRate
		 * en el valor Sale del CostSale; si existe en costSales y es OtherCost entonces se suma el valor del rate del clientRate 
		 * en el valor Sale del CostSale; si no existe en costSales y es OtherCost entonces se crea un nuevo CostSale en la lista de costSales 
		 * y se le escribe lo que hay en el valor rate del clientRate sobre el valor Sale del CostSale.
		 */
		if(clientRates != null){
			for(ClientRate _tmpItem : clientRates){
				if(!_tmpItem.isOtherCharge()){
					for(AwbCostSale costSale : awb.getAwbCostsSales()){			
						if(_tmpItem.getChargeType().getValueId() == costSale.getChargeType().getValueId()){
							costSale.setFlat(_tmpItem.isFlat());
							costSale.setSale(_tmpItem.getRate());
							costSale.setSaleMin(_tmpItem.getMinimumRate());
							costSale.setSelectedToAwbDoc(_tmpItem.isSelectedToAwbDoc());
							break;
						}
						
					}
				}else{
					boolean exist = false;
					int index = -1;
					for(AwbCostSale OthercostSale : awb.getAwbOtherCostsSales()){
						if(_tmpItem.getChargeType().getValueId() == OthercostSale.getChargeType().getValueId()){
							OthercostSale.setFlat(_tmpItem.isFlat());
							OthercostSale.setSale(_tmpItem.getRate());
							OthercostSale.setSaleMin(_tmpItem.getMinimumRate());
							OthercostSale.setSelectedToAwbDoc(_tmpItem.isSelectedToAwbDoc());
							exist = true;
							break;
						}
						if(OthercostSale.isEmpty()){
							index = awb.getAwbOtherCostsSales().indexOf(OthercostSale); /* Esto captura el indice de un objeto vacio para posteriormente eliminarlo. */
						}
					}
					if(index >= 0){
						awb.getAwbOtherCostsSales().remove(index); /* Esto elimina de la lista de otherCharges el objeto especificado en el indice que se capturo arriba.*/
					}
					if(!exist){
						AwbCostSale _tmpCostSale = new AwbCostSale();
						_tmpCostSale.setAwbId(awb.getAwbId());
						_tmpCostSale.setChargeType(_tmpItem.getChargeType());
						_tmpCostSale.setSale(_tmpItem.getRate());
						_tmpCostSale.setSaleMin(_tmpItem.getMinimumRate());
						_tmpCostSale.setOtherCost(true);
						_tmpCostSale.setFlat(_tmpItem.isFlat());
						awb.getAwbOtherCostsSales().add(_tmpCostSale);
					}
				}
			}
		}
		double totalWt_ITEMS_GOODS = 0;					/* Peso total de los items de la guia que son tipo GOOD en libras.*/
		double totalWt_ITEMS_DANGEROUS_GOODS = 0;		/* Peso total de los items de la guia que son tipo DANGEROUS GOOD en libras.*/
		double totalWt_ITEMS_OVERSIZE = 0;				/* Peso total de los items de la guia que son tipo OVERSIZE en libras.*/
		double totalWt_ITEMS_REFRIGERATED = 0;			/* Peso total de los items de la guia que son tipo REFRIGERATED en libras.*/
		
		double totalWtVol_ITEMS_GOODS = 0;					/* Peso volumetrico total de los items de la guia que son tipo GOOD en libras.*/
		double totalWtVol_ITEMS_DANGEROUS_GOODS = 0;		/* Peso volumetrico total de los items de la guia que son tipo DANGEROUS GOOD en libras.*/
		double totalWtVol_ITEMS_OVERSIZE = 0;				/* Peso volumetrico total de los items de la guia que son tipo OVERSIZE en libras.*/
		double totalWtVol_ITEMS_REFRIGERATED = 0;			/* Peso volumetrico total de los items de la guia que son tipo REFRIGERATED en libras.*/
		
		double totalWt_ITEMS = awb.getTotalWeightLbs();						/* Peso total de los items de la guia en libras.*/
		double totalWtVol_ITEMS = awb.getTotalWeightVol();					/* Peso volumetrico total de los items de la guia en libras.*/

		
		if(awb.getAwbItems() == null)awb.setAwbItems(new ArrayList<AwbItem>());
		if(awb.getAwbPalletizedItems() == null)awb.setAwbPalletizedItems(new ArrayList<AwbPalletizedItem>());
		
		for(AwbItem _tmpItem : awb.getAwbItems()){
			if(_tmpItem.getPalletId() == null || _tmpItem.getPalletId().isEmpty()){
				if (_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_GOODS) {
					
					totalWt_ITEMS_GOODS += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_GOODS += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
					
				} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
					
					totalWt_ITEMS_DANGEROUS_GOODS += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_DANGEROUS_GOODS += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
					
				} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
					
					totalWt_ITEMS_OVERSIZE += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_OVERSIZE += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
					
				} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
					
					totalWt_ITEMS_REFRIGERATED += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_REFRIGERATED += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
				}
			}
		}
		
		for(AwbPalletizedItem _tmpPalletizedItem : awb.getAwbPalletizedItems()){
			if (_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_GOODS) {
				
				totalWt_ITEMS_GOODS += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_GOODS += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
				
			} else if(_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
				
				totalWt_ITEMS_DANGEROUS_GOODS += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_DANGEROUS_GOODS += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
				
			} else if(_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
				
				totalWt_ITEMS_OVERSIZE += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_OVERSIZE += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
				
			} else if(_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
				
				totalWt_ITEMS_REFRIGERATED += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_REFRIGERATED += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
			}
		}
		
		for(AwbCostSale _tmpCS : awb.getAwbCostsSales()){
			if(totalWt_ITEMS <= 0 || totalWtVol_ITEMS <= 0){ /* Este if comprueba si la guia tiene peso=0 o pesoVolumentrico=0 entonces pone las ventas en cero.*/
				_tmpCS.setSale(0);
				_tmpCS.setSaleMin(0);
				
			}else if(!_tmpCS.isFlat()){
				if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
					_tmpCS.setSale(_tmpCS.getSale() * awb.getNumUNCodes());
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_GOODS > totalWtVol_ITEMS_GOODS ? totalWt_ITEMS_GOODS : totalWtVol_ITEMS_GOODS);
					
					/* Este if mira si el cargo es tipo freight, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que no son hazardous. */
					_tmpCS.setSale(_tmpCS.getSale() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_DANGEROUS_GOODS > totalWtVol_ITEMS_DANGEROUS_GOODS ? totalWt_ITEMS_DANGEROUS_GOODS : totalWtVol_ITEMS_DANGEROUS_GOODS);
					
					/* Este if mira si el cargo es tipo dangerous, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son hazardous. */
					_tmpCS.setSale(_tmpCS.getSale() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_OVERSIZE > totalWtVol_ITEMS_OVERSIZE ? totalWt_ITEMS_OVERSIZE : totalWtVol_ITEMS_OVERSIZE);
					
					/* Este if mira si el cargo es tipo oversize, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son oversize. */
					_tmpCS.setSale(_tmpCS.getSale() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_REFRIGERATED > totalWtVol_ITEMS_REFRIGERATED ? totalWt_ITEMS_REFRIGERATED : totalWtVol_ITEMS_REFRIGERATED);
					
					/* Este if mira si el cargo es tipo refrigerated, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son refrigerated. */
					_tmpCS.setSale(_tmpCS.getSale() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_LOCAL_DELIVERY){
					/* Este siempre multiplica el rate por el peso real de los items*/
					double factor = totalWt_ITEMS;
					_tmpCS.setSale(_tmpCS.getSale() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else{
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS > totalWtVol_ITEMS ? totalWt_ITEMS : totalWtVol_ITEMS);
					
					/* Multiplica el rate por el peso total o pesoVolumetrico total de todos los items. */
					_tmpCS.setSale(_tmpCS.getSale() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
				}
				if(_tmpCS.getSale() < _tmpCS.getSaleMin() && _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
					_tmpCS.setSale(_tmpCS.getSaleMin());
				}
			}
			
			if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_AWB){
				double auxSumSales = 0;
				if(awb.getAwbInlandCS() != null){
					for(AwbInlandCS inlandCSItem : awb.getAwbInlandCS()){
						if (inlandCSItem.getInlandCsId() >0){
							auxSumSales = auxSumSales + inlandCSItem.getSale();
						}
					}
				}
				_tmpCS.setSale(auxSumSales);
			}
		}
		for(AwbCostSale _tmpOtherCS : awb.getAwbOtherCostsSales()){
			if(totalWt_ITEMS <= 0 || totalWtVol_ITEMS <= 0){ /* Este if comprueba si la guia tiene peso=0 o pesoVolumentrico=0 entonces pone los costos y ventas en cero.*/
				_tmpOtherCS.setSale(0);
				_tmpOtherCS.setSaleMin(0);
			}
			/* No se hace nada mas con los otherSales porque los otherCostSales todos son FLAT.*/
		}
		return awb;
	}
	
	public Awb recalculateAwbCosts(Awb awb, List<CarrierRate> carrierRates){
		
		
		/* Esto lo que hace es recorrer la lista de carrierRates y compararlo con la de costSales 
		 * si el rubro de carrierRates existe en costSales y no es OtherCost entonces se escribe el valor del rate del carrierRate
		 * en el valor Cost del CostSale; si existe en costSales y es OtherCost entonces se suma el valor del rate del carrierRate 
		 * en el valor Cost del CostSale; si no existe en costSales y es OtherCost entonces se crea un nuevo CostSale en la lista de costSales 
		 * y se le escribe lo que hay en el valor rate del carrierRates sobre el valor Cost del CostSale.
		 */
		if(carrierRates != null){
			for(CarrierRate _tmpItem : carrierRates){
				if(!_tmpItem.isOtherCharge() ){
					for(AwbCostSale costSale : awb.getAwbCostsSales()){	
						if(_tmpItem.getChargeType().getValueId() == costSale.getChargeType().getValueId()){
							costSale.setFlat(_tmpItem.isFlat());
							costSale.setCost(_tmpItem.getRate());
							costSale.setCostMin(_tmpItem.getMinimum());
							break;
						}
					}
				}else{
					boolean exist = false;
					int index = -1;
					for(AwbCostSale OthercostSale : awb.getAwbOtherCostsSales()){
						if(_tmpItem.getChargeType().getValueId() == OthercostSale.getChargeType().getValueId()){
							OthercostSale.setCost(_tmpItem.getRate());
							OthercostSale.setFlat(_tmpItem.isFlat());
							OthercostSale.setCostMin(_tmpItem.getMinimum());
							exist = true;
							break;
						}
						if(OthercostSale.isEmpty()){
							index = awb.getAwbOtherCostsSales().indexOf(OthercostSale); /* Esto captura el indice de un objeto vacio para posteriormente eliminarlo. */
						}
					}
					if(index >= 0){
						awb.getAwbOtherCostsSales().remove(index); /* Esto elimina de la lista de otherCharges el objeto especificado en el indice que se capturo arriba.*/
					}
					if(!exist){
						AwbCostSale _tmpCostSale = new AwbCostSale();
						_tmpCostSale.setAwbId(awb.getAwbId());
						_tmpCostSale.setChargeType(_tmpItem.getChargeType());
						_tmpCostSale.setCost(_tmpItem.getRate());
						_tmpCostSale.setFlat(_tmpItem.isFlat());
						_tmpCostSale.setCostMin(_tmpItem.getMinimum());
						_tmpCostSale.setOtherCost(true);
						awb.getAwbOtherCostsSales().add(_tmpCostSale);
					}
				}
				
			}
		}
		double totalWt_ITEMS_GOODS = 0;					/* Peso total de los items de la guia que son tipo GOOD en libras.*/
		double totalWt_ITEMS_DANGEROUS_GOODS = 0;		/* Peso total de los items de la guia que son tipo DANGEROUS GOOD en libras.*/
		double totalWt_ITEMS_OVERSIZE = 0;				/* Peso total de los items de la guia que son tipo OVERSIZE en libras.*/
		double totalWt_ITEMS_REFRIGERATED = 0;			/* Peso total de los items de la guia que son tipo REFRIGERATED en libras.*/
		
		double totalWtVol_ITEMS_GOODS = 0;					/* Peso volumetrico total de los items de la guia que son tipo GOOD en libras.*/
		double totalWtVol_ITEMS_DANGEROUS_GOODS = 0;		/* Peso volumetrico total de los items de la guia que son tipo DANGEROUS GOOD en libras.*/
		double totalWtVol_ITEMS_OVERSIZE = 0;				/* Peso volumetrico total de los items de la guia que son tipo OVERSIZE en libras.*/
		double totalWtVol_ITEMS_REFRIGERATED = 0;			/* Peso volumetrico total de los items de la guia que son tipo REFRIGERATED en libras.*/
		
		double totalWt_ITEMS = awb.getTotalWeightLbs();						/* Peso total de los items de la guia en libras.*/
		double totalWtVol_ITEMS = awb.getTotalWeightVol();					/* Peso volumetrico total de los items de la guia en libras.*/
		
		
		if(awb.getAwbItems() == null)awb.setAwbItems(new ArrayList<AwbItem>());
		
		for(AwbItem _tmpItem : awb.getAwbItems()){
			if(_tmpItem.getPalletId() == null || _tmpItem.getPalletId().isEmpty()){
				if (_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_GOODS) {
					
					totalWt_ITEMS_GOODS += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_GOODS += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
					
				} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
					
					totalWt_ITEMS_DANGEROUS_GOODS += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_DANGEROUS_GOODS += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
					
				} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
					
					totalWt_ITEMS_OVERSIZE += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_OVERSIZE += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
					
				} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
					
					totalWt_ITEMS_REFRIGERATED += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
					totalWtVol_ITEMS_REFRIGERATED += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
				}
			}
		}
		
		for(AwbPalletizedItem _tmpPalletizedItem : awb.getAwbPalletizedItems()){
			if (_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_GOODS) {
				
				totalWt_ITEMS_GOODS += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_GOODS += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
				
			} else if(_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
				
				totalWt_ITEMS_DANGEROUS_GOODS += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_DANGEROUS_GOODS += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
				
			} else if(_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
				
				totalWt_ITEMS_OVERSIZE += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_OVERSIZE += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
				
			} else if(_tmpPalletizedItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
				
				totalWt_ITEMS_REFRIGERATED += (_tmpPalletizedItem.getItemWeight() * _tmpPalletizedItem.getPieces());
				totalWtVol_ITEMS_REFRIGERATED += ((_tmpPalletizedItem.getItemHeight() * _tmpPalletizedItem.getItemLength() * _tmpPalletizedItem.getItemWidth() / 166) * _tmpPalletizedItem.getPieces());
			}
		}
		
		for(AwbCostSale _tmpCS : awb.getAwbCostsSales()){
			if(totalWt_ITEMS <= 0 || totalWtVol_ITEMS <= 0){ /* Este if comprueba si la guia tiene peso=0 o pesoVolumentrico=0 entonces pone las ventas en cero.*/
				_tmpCS.setCost(0);
				_tmpCS.setCostMin(0);
				
			}else if(!_tmpCS.isFlat()){
				if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
					_tmpCS.setCost(_tmpCS.getCost() * awb.getNumUNCodes());
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_GOODS > totalWtVol_ITEMS_GOODS ? totalWt_ITEMS_GOODS : totalWtVol_ITEMS_GOODS);
					
					/* Este if mira si el cargo es tipo freight, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que no son hazardous. */
					_tmpCS.setCost(_tmpCS.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_DANGEROUS_GOODS > totalWtVol_ITEMS_DANGEROUS_GOODS ? totalWt_ITEMS_DANGEROUS_GOODS : totalWtVol_ITEMS_DANGEROUS_GOODS);
					
					/* Este if mira si el cargo es tipo dangerous, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son hazardous. */
					_tmpCS.setCost(_tmpCS.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_OVERSIZE > totalWtVol_ITEMS_OVERSIZE ? totalWt_ITEMS_OVERSIZE : totalWtVol_ITEMS_OVERSIZE);
					
					/* Este if mira si el cargo es tipo oversize, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son oversize. */
					_tmpCS.setCost(_tmpCS.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED){
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS_REFRIGERATED > totalWtVol_ITEMS_REFRIGERATED ? totalWt_ITEMS_REFRIGERATED : totalWtVol_ITEMS_REFRIGERATED);
					
					/* Este if mira si el cargo es tipo refrigerated, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son refrigerated. */
					_tmpCS.setCost(_tmpCS.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
					
				}else{
					/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
					double factor = (totalWt_ITEMS > totalWtVol_ITEMS ? totalWt_ITEMS : totalWtVol_ITEMS);
					
					/* Multiplica el rate por el peso total o pesoVolumetrico total de todos los items. */
					_tmpCS.setCost(_tmpCS.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
				}
				if(_tmpCS.getCost() < _tmpCS.getCostMin() && _tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_CHARGE_TYPE_UN_CHARGE){
					_tmpCS.setCost(_tmpCS.getCostMin());
				}
			}
			
			if(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_AWB){
				double auxSumCosts = 0;
				if(awb.getAwbInlandCS() != null){
					for(AwbInlandCS inlandCSItem : awb.getAwbInlandCS()){
						if (inlandCSItem.getInlandCsId() >0){
							auxSumCosts = auxSumCosts + inlandCSItem.getSale();
						}
					}
				}
				_tmpCS.setCost(auxSumCosts);
			}
		}
		for(AwbCostSale _tmpOtherCS : awb.getAwbOtherCostsSales()){
			if(totalWt_ITEMS <= 0 || totalWtVol_ITEMS <= 0){ /* Este if comprueba si la guia tiene peso=0 o pesoVolumentrico=0 entonces pone los costos y ventas en cero.*/
				_tmpOtherCS.setCost(0);
				_tmpOtherCS.setCostMin(0);
			}
			/* No se hace nada mas con los otherSales porque los otherCostSales todos son FLAT.*/
		}
		return awb;
	}
	
	public Awb recalculatePrincipalCosts(Awb awb, List<CarrierRate> carrierRates){  /* Esto es para recalcular los costos de una hija, pero solo los costos que estan 
																					chuleados para que se muestren en la master (Campo SHOW_IN_MASTER en la BD)*/
		
		double totalWt_ITEMS_GOODS = 0;					/* Peso total de los items de la guia que son tipo GOOD en libras.*/
		double totalWt_ITEMS_DANGEROUS_GOODS = 0;		/* Peso total de los items de la guia que son tipo DANGEROUS GOOD en libras.*/
		double totalWt_ITEMS_OVERSIZE = 0;				/* Peso total de los items de la guia que son tipo OVERSIZE en libras.*/
		double totalWt_ITEMS_REFRIGERATED = 0;			/* Peso total de los items de la guia que son tipo REFRIGERATED en libras.*/
		
		double totalWtVol_ITEMS_GOODS = 0;					/* Peso volumetrico total de los items de la guia que son tipo GOOD en libras.*/
		double totalWtVol_ITEMS_DANGEROUS_GOODS = 0;		/* Peso volumetrico total de los items de la guia que son tipo DANGEROUS GOOD en libras.*/
		double totalWtVol_ITEMS_OVERSIZE = 0;				/* Peso volumetrico total de los items de la guia que son tipo OVERSIZE en libras.*/
		double totalWtVol_ITEMS_REFRIGERATED = 0;			/* Peso volumetrico total de los items de la guia que son tipo REFRIGERATED en libras.*/
		
		double totalWt_ITEMS = awb.getTotalWeightLbs();						/* Peso total de los items de la guia en libras.*/
		double totalWtVol_ITEMS = awb.getTotalWeightVol();					/* Peso volumetrico total de los items de la guia en libras.*/
		
		if(awb.getAwbItems() == null)awb.setAwbItems(new ArrayList<AwbItem>());
		
		for(AwbItem _tmpItem : awb.getAwbItems()){
			if (_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_GOODS) {
				
				totalWt_ITEMS_GOODS += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
				totalWtVol_ITEMS_GOODS += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
				
			} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
				
				totalWt_ITEMS_DANGEROUS_GOODS += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
				totalWtVol_ITEMS_DANGEROUS_GOODS += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
				
			} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
				
				totalWt_ITEMS_OVERSIZE += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
				totalWtVol_ITEMS_OVERSIZE += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
				
			} else if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
				
				totalWt_ITEMS_REFRIGERATED += (_tmpItem.getWeightLbs() * _tmpItem.getPieces());
				totalWtVol_ITEMS_REFRIGERATED += ((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth() / 166) * _tmpItem.getPieces());
			}
		}
		
		if(carrierRates != null){
			for(CarrierRate _tmpCarrierRate : carrierRates){
				if(!_tmpCarrierRate.isOtherCharge() && _tmpCarrierRate.isShowInMaster()){
					for(AwbCostSale costSale : awb.getAwbCostsSales()){	
						if(_tmpCarrierRate.getChargeType().getValueId() == costSale.getChargeType().getValueId()){
							costSale.setFlat(_tmpCarrierRate.isFlat());
							costSale.setCost(_tmpCarrierRate.getRate());
							costSale.setCostMin(_tmpCarrierRate.getMinimum());
							
							if(totalWt_ITEMS <= 0 || totalWtVol_ITEMS <= 0){ /* Este if comprueba si la guia tiene peso=0 o pesoVolumentrico=0 entonces pone las ventas en cero.*/
								costSale.setCost(0);
								costSale.setCostMin(0);
								
							}else if(!costSale.isFlat()){
								if(costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT){
									/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
									double factor = (totalWt_ITEMS_GOODS > totalWtVol_ITEMS_GOODS ? totalWt_ITEMS_GOODS : totalWtVol_ITEMS_GOODS);
									
									/* Este if mira si el cargo es tipo freight, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que no son hazardous. */
									costSale.setCost(costSale.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
									
								}else if(costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
									/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
									double factor = (totalWt_ITEMS_DANGEROUS_GOODS > totalWtVol_ITEMS_DANGEROUS_GOODS ? totalWt_ITEMS_DANGEROUS_GOODS : totalWtVol_ITEMS_DANGEROUS_GOODS);
									
									/* Este if mira si el cargo es tipo dangerous, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son hazardous. */
									costSale.setCost(costSale.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
									
								}else if(costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE){
									/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
									double factor = (totalWt_ITEMS_OVERSIZE > totalWtVol_ITEMS_OVERSIZE ? totalWt_ITEMS_OVERSIZE : totalWtVol_ITEMS_OVERSIZE);
									
									/* Este if mira si el cargo es tipo oversize, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son oversize. */
									costSale.setCost(costSale.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
									
								}else if(costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED){
									/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
									double factor = (totalWt_ITEMS_REFRIGERATED > totalWtVol_ITEMS_REFRIGERATED ? totalWt_ITEMS_REFRIGERATED : totalWtVol_ITEMS_REFRIGERATED);
									
									/* Este if mira si el cargo es tipo refrigerated, si lo es, multiplica el rate por el peso total o pesoVolumetrico total de los items que son refrigerated. */
									costSale.setCost(costSale.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
									
								}else{
									/*Decide cual de los dos en mayor y lo utiliza para multiplicar al cargo*/
									double factor = (totalWt_ITEMS > totalWtVol_ITEMS ? totalWt_ITEMS : totalWtVol_ITEMS);
									
									/* Multiplica el rate por el peso total o pesoVolumetrico total de todos los items. */
									costSale.setCost(costSale.getCost() * UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(factor), 0));
								}
								if(costSale.getCost() < costSale.getCostMin()){
									costSale.setCost(costSale.getCostMin());
								}
							}
							break;
						}
					}
				}
			}
		} 
		return awb;
	}
	
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception{
		if(carrierPort != null){
			try {
				carrierPort = awbDao.loadEffectiveDate(carrierPort);
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return carrierPort;	
	}
	
	public List<AwbDeclaredValue> loadAwbDeclaredValues(Awb awb) throws Exception {
		List<AwbDeclaredValue> _tmpAwbDeclaredValues = null;
		if(awb != null){
			try {
				_tmpAwbDeclaredValues = awbDao.loadAwbDeclaredValues(awb);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpAwbDeclaredValues;
	}
	public List<CarrierRate> loadCarrierRates(Awb awb){
		List<CarrierRate> carrierRates = null;
		
		CarrierPorts _tmpCarrierPort = new CarrierPorts();			
		_tmpCarrierPort.setCarrierId(awb.getCarrier().getCarrierId());
		_tmpCarrierPort.setPortOrigin(awb.getAirportOrigin().getPortId());
		_tmpCarrierPort.setPortDestination(awb.getAirportDestination().getPortId());
		
	
		try {
			carrierRates = awbDao.loadCarrierRates(_tmpCarrierPort);
			
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
		}
		return carrierRates;
	}
	
	
	public List<ClientRate> loadClientRates(Awb awb) {
		List<ClientRate> clientRates = null;
	
		ClientRatesPort _tmpClientRatesPort = new ClientRatesPort();
		_tmpClientRatesPort.setClientId(awb.getClient().getPartnerId());
		_tmpClientRatesPort.setCarrierId(awb.getCarrier().getCarrierId());
		_tmpClientRatesPort.setPortOrigin(awb.getAirportOrigin().getPortId());
		_tmpClientRatesPort.setPortDestination(awb.getAirportDestination().getPortId());
		try {			
			clientRates = awbDao.loadClientRates(_tmpClientRatesPort);
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
		}
		return clientRates;
		
	}
	
	
	public List<AwbItem> loadAwbSummarizedItems(Awb awb){
		List<AwbItem> _auxItems = new ArrayList<AwbItem>();
		AwbItem itemG = new AwbItem();
		itemG.setEmpty(true);
		AwbItem itemDG = new AwbItem();
		itemDG.setEmpty(true);
		AwbItem itemOV = new AwbItem();
		itemOV.setEmpty(true);
		AwbItem itemRF = new AwbItem();
		itemRF.setEmpty(true);
		
		List<AwbItem> itemsToSummarize = new ArrayList<AwbItem>();
		
		
		if (awb.isMaster()){
			
			for(Awb _tmpHouse : awb.getAwbHouses()){
				try {
					itemsToSummarize.addAll(loadAwbItems(new AwbItem(_tmpHouse.getAwbId())));					
					
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
			}
			
		}else{
			itemsToSummarize = awb.getAwbItems();
			
		}
		
		for(AwbItem _tmpItem: itemsToSummarize){
			if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_GOODS){
				itemG.setPieces(itemG.getPieces() + _tmpItem.getPieces());
				itemG.setWeightLbs(itemG.getWeightLbs() +(_tmpItem.getPieces() * _tmpItem.getWeightLbs()));
				itemG.setVolumeTotal(itemG.getVolumeTotal() +(((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth())/166) * _tmpItem.getPieces() ));
			
				itemG.getRateClass().setValue1("G");
				itemG.setEmpty(false);
			}
			if(_tmpItem.getRateClass().getValueId()== Constants.MASTER_VALUE_RATE_CLASS_DANGEROUS){
				itemDG.setPieces(itemDG.getPieces() + _tmpItem.getPieces());
				itemDG.setWeightLbs(itemDG.getWeightLbs() +(_tmpItem.getPieces() * _tmpItem.getWeightLbs()));
				itemDG.setVolumeTotal(itemDG.getVolumeTotal() +(((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth())/166) * _tmpItem.getPieces() ));
				
				itemDG.getRateClass().setValue1("DG");
				itemDG.setEmpty(false);
			}
			if(_tmpItem.getRateClass().getValueId() == Constants.MASTER_VALUE_RATE_CLASS_OVERSIZE){
				itemOV.setPieces(itemOV.getPieces() + _tmpItem.getPieces());
				itemOV.setWeightLbs(itemOV.getWeightLbs() +(_tmpItem.getPieces() * _tmpItem.getWeightLbs()));
				itemOV.setVolumeTotal(itemOV.getVolumeTotal() +(((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth())/166) * _tmpItem.getPieces() ));
								
				itemOV.getRateClass().setValue1("OV");
				itemOV.setEmpty(false);
			}
			if(_tmpItem.getRateClass().getValueId()== Constants.MASTER_VALUE_RATE_CLASS_REFRIGERATED){
				itemRF.setPieces(itemRF.getPieces() + _tmpItem.getPieces());
				itemRF.setWeightLbs(itemRF.getWeightLbs() +(_tmpItem.getPieces() * _tmpItem.getWeightLbs()));
				itemRF.setVolumeTotal(itemRF.getVolumeTotal() +(((_tmpItem.getItemHeight() * _tmpItem.getItemLength() * _tmpItem.getItemWidth())/166) * _tmpItem.getPieces() ));
				
				itemRF.getRateClass().setValue1("RF");
				itemRF.setEmpty(false);
			}
		}
		//calculate and convert chargeableWeight , convert weight from Lb to Kg
		if(!itemG.isEmpty()) {
			
			if(itemG.getWeightLbs() > itemG.getVolumeTotal()){
				itemG.setChargeableWeight(itemG.getWeightLbs());
			}else{
				itemG.setChargeableWeight(itemG.getVolumeTotal());
			}
			itemG.setChargeableWeight(UtilFunctions.roundUpDecimalPlaces( UtilFunctions.convertLbToKg(itemG.getChargeableWeight()),0));
			itemG.setWeightLbs(UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(itemG.getWeightLbs()),0));
		}
		
		if(!itemDG.isEmpty()){
			
			if(itemDG.getWeightLbs() > itemDG.getVolumeTotal()){
				itemDG.setChargeableWeight(itemDG.getWeightLbs());
			}else{
				itemDG.setChargeableWeight(itemDG.getVolumeTotal());
			}
			
			itemDG.setChargeableWeight(UtilFunctions.roundUpDecimalPlaces( UtilFunctions.convertLbToKg(itemDG.getChargeableWeight()),0));
			itemDG.setWeightLbs(UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(itemDG.getWeightLbs()),0));
		}
			
		if(!itemOV.isEmpty()){
			
			if(itemOV.getWeightLbs() > itemOV.getVolumeTotal()){
				itemOV.setChargeableWeight(itemOV.getWeightLbs());
			}else{
				itemOV.setChargeableWeight(itemOV.getVolumeTotal());
			}
			
			itemOV.setChargeableWeight(UtilFunctions.roundUpDecimalPlaces( UtilFunctions.convertLbToKg(itemOV.getChargeableWeight()),0));
			itemOV.setWeightLbs(UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(itemOV.getWeightLbs()),0));
		}
			
		if(!itemRF.isEmpty()){
			
			if(itemRF.getWeightLbs() > itemRF.getVolumeTotal()){
				itemRF.setChargeableWeight(itemRF.getWeightLbs());
			}else{
				itemRF.setChargeableWeight(itemRF.getVolumeTotal());
			}
			
			itemRF.setChargeableWeight(UtilFunctions.roundUpDecimalPlaces( UtilFunctions.convertLbToKg(itemRF.getChargeableWeight()),0));
			itemRF.setWeightLbs(UtilFunctions.roundUpDecimalPlaces(UtilFunctions.convertLbToKg(itemRF.getWeightLbs()),0));
		}
				
			
		// set Rate
		for (ClientRate _auxClientaRate: loadClientRates(awb)){
				if(_auxClientaRate.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT){
					if(!itemG.isEmpty()){
						if(itemG.getChargeableWeight() * _auxClientaRate.getRate() > _auxClientaRate.getMinimumRate()){
							itemG.setCharacterRate(_auxClientaRate.getRate()+" USD/Kg");
							itemG.setRate(_auxClientaRate.getRate());							
						}else{
							itemG.setCharacterRate("MIN");
							itemG.setMinimun(true);
							itemG.setRate(_auxClientaRate.getMinimumRate());	
						}
					}
				}
				if(_auxClientaRate.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
					if(!itemDG.isEmpty()){
						if(itemDG.getChargeableWeight() * _auxClientaRate.getRate() > _auxClientaRate.getMinimumRate()){
							itemDG.setCharacterRate(_auxClientaRate.getRate()+" USD/Kg");
							itemDG.setRate(_auxClientaRate.getRate());					
						}else{
							itemDG.setCharacterRate("MIN");
							itemDG.setMinimun(true);
							itemDG.setRate(_auxClientaRate.getMinimumRate());	
						}		
					}
				}
				if(_auxClientaRate.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE){
					if(!itemOV.isEmpty()){
						if(itemOV.getChargeableWeight() * _auxClientaRate.getRate() > _auxClientaRate.getMinimumRate()){
							itemOV.setCharacterRate(_auxClientaRate.getRate()+" USD/Kg");
							itemOV.setRate(_auxClientaRate.getRate());	
						}else{
							itemOV.setCharacterRate("MIN");
							itemOV.setMinimun(true);
							itemOV.setRate(_auxClientaRate.getMinimumRate());	
						}
					}
				}
				if(_auxClientaRate.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED){
					if(!itemRF.isEmpty()){
						if(itemRF.getChargeableWeight() * _auxClientaRate.getRate() > _auxClientaRate.getMinimumRate()){
							itemRF.setCharacterRate(_auxClientaRate.getRate()+" USD/Kg");
							itemRF.setRate(_auxClientaRate.getRate());	
						}else{
							itemRF.setCharacterRate("MIN");
							itemRF.setMinimun(true);
							itemRF.setRate(_auxClientaRate.getMinimumRate());	
						}		
					}
				}
		}
		
		// set total sale
		if (awb.isMaster()){
			if(itemG.isMinimun()){
				itemG.setRateTotal(itemG.getRate());
			}else{ 
				itemG.setRateTotal(itemG.getRate() * itemG.getChargeableWeight());
			}
			
			if(itemDG.isMinimun()){
				itemDG.setRateTotal(itemDG.getRate());
			}else{ 
				itemDG.setRateTotal(itemDG.getRate() * itemDG.getChargeableWeight());
			}
			
			if(itemOV.isMinimun()){
				itemOV.setRateTotal(itemOV.getRate());
			}else{ 
				itemOV.setRateTotal(itemOV.getRate() * itemOV.getChargeableWeight());
			}
			
			if(itemRF.isMinimun()){
				itemRF.setRateTotal(itemRF.getRate());
			}else{ 
				itemRF.setRateTotal(itemRF.getRate() * itemRF.getChargeableWeight());
			}
							
			
		}else{
			for(AwbCostSale _tmpCostSales: awb.getAwbCostsSales()){
				if(_tmpCostSales.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT){
					itemG.setRateTotal(itemG.getRateTotal() +_tmpCostSales.getSale());
				}
				if(_tmpCostSales.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
					itemDG.setRateTotal(itemDG.getRateTotal() + _tmpCostSales.getSale());
				}
				if(_tmpCostSales.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_OVERSIZE){
					itemOV.setRateTotal(itemOV.getRateTotal() + _tmpCostSales.getSale());
				}
				if(_tmpCostSales.getChargeType().getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_REFRIGERATED){
					itemRF.setRateTotal(itemRF.getRateTotal() + _tmpCostSales.getSale());
				}
			}
		}
		
		
		_auxItems.add(itemG);
		_auxItems.add(itemDG);
		_auxItems.add(itemOV);
		_auxItems.add(itemRF);
				
		return _auxItems;
	}
	
	public double calculateTotalVolumeM3 (Awb awb){
		double result = 0;
		
		List<AwbItem> itemsToSummarize = new ArrayList<AwbItem>();
		
		if (awb.isMaster()){
			
			for(Awb _tmpHouse : awb.getAwbHouses()){
				try {
					itemsToSummarize.addAll(loadAwbItems(new AwbItem(_tmpHouse.getAwbId())));
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
			}
			
		}else{
			itemsToSummarize = awb.getAwbItems();
		}
		
		
		for(AwbItem _tmpItem: itemsToSummarize){
			result += _tmpItem.getItemVolume() * _tmpItem.getPieces() *  Math.pow(Constants.FOOT_TO_METERS, 3);
		}
		return result;
	}
	
	public CarrierAwbNumber loadAwbNumberFromCarriers(Awb awb) throws Exception{
		CarrierAwbNumber carrierAwbNumber = new CarrierAwbNumber();
		carrierAwbNumber.getCarrier().setCarrierId(awb.getCarrier().getCarrierId());
		List<CarrierAwbNumber> carrierNumbers = null;
		try {
			carrierNumbers = carrierLogic.loadCarrierAwbNumber(carrierAwbNumber);
			carrierAwbNumber = null;
			if(carrierNumbers != null){
				for(CarrierAwbNumber _tmpCarrierAwbNumber: carrierNumbers){
					if(!_tmpCarrierAwbNumber.isUsed()){
						return _tmpCarrierAwbNumber;
					}
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
			throw e;
		}
		return carrierAwbNumber;
	}
	
	public List<ItemProrated> fillAwbItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception{
		if(itemsProrated != null){
			try {
				itemsProrated = awbDao.fillAwbItemsProratedInformation(itemsProrated);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return itemsProrated;
	}

	public IcarrierLogic getCarrierLogic() {
		return carrierLogic;
	}

	public void setCarrierLogic(IcarrierLogic carrierLogic) {
		this.carrierLogic = carrierLogic;
	}	
}