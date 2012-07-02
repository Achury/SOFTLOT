package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;
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
import com.lotrading.softlot.LODepartment.shipments.logic.IBlLogic;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;
import com.lotrading.softlot.util.base.UtilFunctions;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 19-Jan-2012 12:00:00 AM
 */
public class BlLogicImpl implements IBlLogic {
	private IBlDao blDao;
	private Log log = LogFactory.getLog(BlLogicImpl.class);
	
	public BlLogicImpl(){

	}

	/**
	 * 
	 * @param bl
	 */
	public int saveBl(Bl bl) throws Exception{
		int _tmpReturnId = 0;		
			try{
				if ( bl == null) return _tmpReturnId;	
				if(bl.getBlId() <=0){
					bl.setCreatedDate(new Date());
					_tmpReturnId = blDao.createBl(bl);
				}else if (bl.getBlId() > 0){
					if(blDao.updateBl(bl)) {
						_tmpReturnId = bl.getBlId();
					}
				}					
			}catch (Exception e){
				log.error("An Exception has been thrown " + e);
				 e.printStackTrace();
				throw e;
			}
			return _tmpReturnId;
		}
		
	

	/**
	 * 
	 * @param bl
	 */
	public List<Bl> searchBl(Bl bl) throws Exception{
		List <Bl> bls = null;
		if(bl != null){
			try {
				bls = blDao.searchBl(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return bls;
	}
	
	public List<Bl> loadBlList(Bl bl) throws Exception{
		List<Bl> blList = null;
		try {
			blList = blDao.loadBlList(bl);		
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
			throw e;
		}
		return blList;
	}
	
	public Bl loadBl(Bl bl) throws Exception {
		Bl _tmpBl = null;
		if(bl != null){
			try {
				_tmpBl = blDao.loadBl(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBl;
	}
	
	public List<Bl> loadHouseBls(Bl bl) throws Exception{
		List<Bl> blHouseList = null;
		if(bl != null){
			try {
				blHouseList = blDao.loadHouseBls(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blHouseList;
	}
	
	public boolean updateHouse(Bl bl) throws Exception{
		boolean updated = false;
		if(bl != null){
			try {
				updated = blDao.updateBlHouse(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return updated;
	}
	
	public List<Bl> updateBlHouseList(List<Bl> blHousesList) throws Exception{
		if(blHousesList != null){
			try {
				blHousesList = blDao.updateBlHouseList(blHousesList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blHousesList;
	}

	/**
	 * 
	 * @param bl
	 */
	public List<BlItem> loadBlItems(Bl bl) throws Exception{
		List<BlItem> _tmpBlItems = null; 
		if(bl != null){
			try {
				_tmpBlItems = blDao.loadBlItems(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlItems;
	}
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlPalletizedItem> loadBlPalletizedItems(Bl bl) throws Exception{
		List<BlPalletizedItem> _tmpBlPalletizedItems = null; 
		if(bl != null){
			try {
				_tmpBlPalletizedItems = blDao.loadBlPalletizedItems(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlPalletizedItems;
	}

	/**
	 * 
	 * @param bl
	 */
	public List<BlFreightInvoice> loadFreightInvoices(Bl bl) throws Exception{
		List<BlFreightInvoice> _tmpBlFreightInvoices = null; 
		if(bl != null){
			try {
				_tmpBlFreightInvoices = blDao.loadFreightInvoices(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlFreightInvoices;
	}

	/**
	 * 
	 * @param bl
	 */
	public List<BlOtherInvoice> loadOtherInvoices(Bl bl) throws Exception{
		List<BlOtherInvoice> _tmpBlOtherInvoices = null; 
		if(bl != null){
			try {
				_tmpBlOtherInvoices = blDao.loadOtherInvoices(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlOtherInvoices;
	}

	
	/**
	 * 
	 * @param bl
	 */
	public List<BlInlandCS> loadInlandCS(Bl bl) throws Exception{
		List<BlInlandCS> _tmpBlInlandCSs = null; 
		if(bl != null){
			try {
				_tmpBlInlandCSs = blDao.loadInlandsCS(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlInlandCSs;
	}

	/**
	 * 
	 * @param bl
	 */
	public List<BlUnCode> loadUnCodes(Bl bl) throws Exception{
		List<BlUnCode> _tmpUnCodes = null; 
		if(bl != null){
			try {
				_tmpUnCodes = blDao.loadUnCodes(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpUnCodes;
	}
	
	/**
	 * 
	 * @param bl
	 */
	public Bl loadCostsSales(Bl bl) throws Exception{
		
		if(bl != null){
			if(bl.getBlId() > 0 ){			
				try {
					List<BlCostSale> blCostsSales =  blDao.loadCostsSales(bl);
					bl.setBlOtherCostsSales(new ArrayList<BlCostSale>());
					bl.setBlCostsSales(new ArrayList<BlCostSale>());
					
					for(BlCostSale _tmpCS : blCostsSales){
						if(_tmpCS.isOtherCost()){
							bl.getBlOtherCostsSales().add(_tmpCS);
						}else{
							bl.getBlCostsSales().add(_tmpCS);
						}
					}
					
				} catch (Exception e) {
					log.error("An Exception has been thrown " + e);
					throw e;
				}
			}else{
				
				bl.setBlCostsSales(blDao.loadInitialCostsSales(bl));		     
				bl.setBlOtherCostsSales(new ArrayList<BlCostSale>());
			}
		}
		return bl;
	}
	
	public List<BlCostSale> loadInitialCostsSales(Bl bl) throws Exception {
		List<BlCostSale> blCostsSales = null;
		if(bl != null){
			blCostsSales = blDao.loadInitialCostsSales(bl);
		}
		return blCostsSales;
	}
	
	
	public List<BlEEI> loadEEIs(Bl bl) throws Exception {
		List<BlEEI> _tmpBlEEIs = null; 
		if(bl != null){
			try {
				_tmpBlEEIs = blDao.loadEEIs(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlEEIs;
	}
	
	/**
	 * 
	 * @param bl
	 */
	public List<BlContainer> loadBlContainers(Bl bl) throws Exception{
		List<BlContainer> _tmpBlContainers = null; 
		if(bl != null){
			try {
				_tmpBlContainers = blDao.loadBlContainers(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlContainers;
	}
	
	
	// bl_rates es quien contiene el puerto de origen, destino 
	public Bl fillAutomaticCosts(Bl bl_target, Bl bl_Rates){
		List<CarrierRate> carrierRates = null;		
		MasterValue rateType = new MasterValue();
		
		// set to 0 all cost and sales
		resetAllBlCost(bl_target, bl_Rates);
				
		
		// LOAD Carrier rates to LCL or FCL Rate Type
		if (bl_target.isFCL() && !bl_Rates.isMaster()){
			rateType.setValueId(Constants.MASTER_VALUE_RATE_TYPE_40);				
			carrierRates = loadCarrierRates(bl_Rates, rateType);		
			
			rateType.setValueId(Constants.MASTER_VALUE_RATE_TYPE_20);
			carrierRates.addAll( loadCarrierRates(bl_Rates, rateType));
			
		}else{
			if (bl_target.isLCL()){
				rateType.setValueId(Constants.MASTER_VALUE_RATE_TYPE_LCL);				
				carrierRates = loadCarrierRates(bl_Rates, rateType);
			}
		}	
	
		
		/* Esto lo que hace es recorrer la lista de carrierRates y compararlo con la de costSales 
		 * si el rubro de carrierRates existe en costSales y no es OtherCost entonces se escribe el valor del rate del carrierRates
		 * en el valor Sale del CostSale; si existe en costSales y es OtherCost entonces se suma el valor del rate del carrierRates 
		 * en el valor Sale del CostSale; si no existe en costSales y es OtherCost entonces se crea un nuevo CostSale en la lista de costSales 
		 * y se le escribe lo que hay en el valor rate del carrierRates sobre el valor Sale del CostSale.
		 */
		// si el bl de las rates es master
		if(bl_Rates.isMaster()){
			for(CarrierRate _tmpItem : carrierRates){
				if(!_tmpItem.isOtherCharge() ){
					for(BlCostSale costSale : bl_target.getBlCostsSales()){	
						if(_tmpItem.getChargeType().getValueId() == costSale.getChargeType().getValueId()){
							// si el costSale es de los que se muestra en la master entonces recalculelo en la hija
							if(costSale.isShowInMaster()){
															
								costSale.setFlat(_tmpItem.isFlat());
								costSale.setCost(_tmpItem.getRate());
								costSale.setCostMin(_tmpItem.getMinimum());
								
								break;
							}					
						}					
					}
				}
			}
			
			//set cost and sales when they are not flat rates
			for(BlCostSale _tmpCS : bl_target.getBlCostsSales()){
				if(!_tmpCS.isFlat()){
					
					if (!bl_target.isFCL()){					
					// case LCL use Weight [ton] or volume [m3]	
						// if CostSale es de los que se muestra en las masters
						if (_tmpCS.isShowInMaster()) {						
						
							double _tmpWorVHouse;
							double _tmpWorVMaster;
							if(bl_Rates.getTotalRealWTon() < bl_Rates.getTotalOceanVolM3()){
								_tmpWorVHouse = bl_target.getTotalOceanVolM3() ;
								_tmpWorVMaster = bl_Rates.getTotalOceanVolM3() ;
							}else{
								_tmpWorVHouse =  bl_target.getTotalRealWTon();
								_tmpWorVMaster = bl_Rates.getTotalRealWTon();
							}
														
							// si el costo por el factor mayor que el minimo, use el costo por valor
							if( (_tmpCS.getCost() * (_tmpWorVMaster) > _tmpCS.getCostMin())){
								_tmpCS.setCost(UtilFunctions.roundDecimalPlaces(_tmpCS.getCost() * (_tmpWorVHouse), 2));
								
							}else{
								
								_tmpCS.setCost(UtilFunctions.roundDecimalPlaces( (_tmpCS.getCostMin()/_tmpWorVMaster ) * (_tmpWorVHouse), 2));
								
							}
						}
					}
				}
			}
			//si el bl de las rates no es master
		}else{
			
			for(CarrierRate _tmpItem : carrierRates){
				if(!_tmpItem.isOtherCharge() ){
					for(BlCostSale costSale : bl_target.getBlCostsSales()){	
						if(_tmpItem.getChargeType().getValueId() == costSale.getChargeType().getValueId()){
							// si el costSale es de los que se muestra en la master entonces recalculelo en la hija
							
							if((!costSale.isShowInMaster() && bl_Rates.isHouse() ) || bl_Rates.isRegular()){							
								// If BL is FCL and the rate is not flat
								if(bl_target.isFCL() && !_tmpItem.isFlat()){
					
									calculateOceanFCLCostSale(bl_target, costSale, _tmpItem.getRateType(),  _tmpItem.getRate(),true);
									
								}else{
									
									costSale.setFlat(_tmpItem.isFlat());
									costSale.setCost(_tmpItem.getRate());
									costSale.setCostMin(_tmpItem.getMinimum());
									
									break;
								}
							}
						}					
					}
				}else{
					boolean exist = false;
					for(BlCostSale othercostSale : bl_target.getBlOtherCostsSales()){
						if(_tmpItem.getChargeType().getValueId() == othercostSale.getChargeType().getValueId()){
							
							// If BL is FCL and the rate is not flat
							if(bl_target.isFCL() && !_tmpItem.isFlat() ){
								
								calculateOceanFCLCostSale(bl_target, othercostSale, _tmpItem.getRateType(),  _tmpItem.getRate(),true);							
								exist = true;
								
							}else{						
							
								othercostSale.setFlat(_tmpItem.isFlat());
								othercostSale.setCost(_tmpItem.getRate());
								othercostSale.setCostMin(_tmpItem.getMinimum());
								exist = true;
								break;
							}						
							
						}
					}
					if(!exist){
						BlCostSale _tmpCostSale = new BlCostSale();
						_tmpCostSale.setBlId(bl_target.getBlId());
						_tmpCostSale.setChargeType(_tmpItem.getChargeType());				
						_tmpCostSale.setOtherCost(true);
						
						// If BL is FCL and the rate is not flat
						if(bl_target.isFCL() && !_tmpItem.isFlat() ){
							
							calculateOceanFCLCostSale(bl_target, _tmpCostSale, _tmpItem.getRateType(),  _tmpItem.getRate(),true);
													
						}else{
							_tmpCostSale.setCost(_tmpItem.getRate());
							_tmpCostSale.setFlat(_tmpItem.isFlat());
							_tmpCostSale.setCostMin(_tmpItem.getMinimum());
						}					
						
						bl_target.getBlOtherCostsSales().add(0,_tmpCostSale);
					}
				}
				
			}
			
			//set cost and sales when they are not flat rates
			for(BlCostSale _tmpCS : bl_target.getBlCostsSales()){
				if (  (!_tmpCS.isShowInMaster() && bl_Rates.isHouse() ) || bl_Rates.isRegular()){
					if(!_tmpCS.isFlat()){
						
						if (!bl_target.isFCL()){					
						// case LCL use Weight [ton] or volume [m3]	
							// if CostSale not is INLAND FREIGHT y no es LOCAL DELIVERY
							if ( (_tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_INLAND_FREIGHT_FCL)  
									&&	(_tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_INLAND_FREIGHT_LCL)
									&& (_tmpCS.getChargeType().getValueId() != Constants.MASTER_VALUE_LOCAL_DELIVERY_LCL))   {						
							
								double _tmpFactor = (bl_target.getTotalRealWTon() < bl_target.getTotalOceanVolM3())? bl_target.getTotalOceanVolM3() : bl_target.getTotalRealWTon();						
								
								// si el costo por el factor es menor que el minimo, use el minimo
								_tmpCS.setCost( _tmpCS.getCost() * (_tmpFactor) > _tmpCS.getCostMin()? UtilFunctions.roundDecimalPlaces(_tmpCS.getCost() * (_tmpFactor),2):  _tmpCS.getCostMin());
								
							}
							// si el costsale es LOCAL DELIVERY
							if ( _tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_LOCAL_DELIVERY_LCL)  {
								
								// si el costo por el Peso real es menor que el minimo, use el minimo
								_tmpCS.setCost( _tmpCS.getCost() * (bl_target.getTotalRealWKg()) > _tmpCS.getCostMin()? UtilFunctions.roundDecimalPlaces(_tmpCS.getCost() * (bl_target.getTotalRealWKg()),2):  _tmpCS.getCostMin());
							}
							
						}else if (bl_target.isFCL()){	
							// si el costo por el factor es menor que el minimo, use el minimo
							_tmpCS.setCost( _tmpCS.getCost() > _tmpCS.getCostMin()? UtilFunctions.roundDecimalPlaces(_tmpCS.getCost(),2): _tmpCS.getCostMin());
							
						}
					}
				}
			}
			//set other cost and sales when they are not flat rates
			for(BlCostSale _tmpOtherCS : bl_target.getBlOtherCostsSales()){
				if(!_tmpOtherCS.isFlat()){
					
					if (!bl_target.isFCL()){
						double _tmpFactor = (bl_target.getTotalRealWTon() < bl_target.getTotalOceanVolM3())? bl_target.getTotalOceanVolM3() : bl_target.getTotalRealWTon();
						
						// si el costo por el factor es menor que el minimo, use el minimo
						_tmpOtherCS.setCost( _tmpOtherCS.getCost() * (_tmpFactor) > _tmpOtherCS.getCostMin()? UtilFunctions.roundDecimalPlaces(_tmpOtherCS.getCost() * (_tmpFactor),2):  _tmpOtherCS.getCostMin());
						
					}else if (bl_target.isFCL()){	
						// si el costo por el factor es menor que el minimo, use el minimo
						_tmpOtherCS.setCost( _tmpOtherCS.getCost() > _tmpOtherCS.getCostMin()? UtilFunctions.roundDecimalPlaces(_tmpOtherCS.getCost(),2): _tmpOtherCS.getCostMin());
							
					}
				}
			}
		}
		
		
		return bl_target;
	}
	
	public Bl fillAutomaticSales(Bl bl){
		
		List<ClientRate> clientRates = null;
		MasterValue rateType = new MasterValue();
		
		// set to 0 all cost and sales
		resetAllBlSale(bl);
				
		
		// LOAD Carrier and client rates to LCL or FL Rate Type
		if (bl.isFCL()){
			rateType.setValueId(Constants.MASTER_VALUE_RATE_TYPE_40);
			clientRates = loadClientRates(bl, rateType);
			
			rateType.setValueId(Constants.MASTER_VALUE_RATE_TYPE_20);
			clientRates.addAll( loadClientRates(bl, rateType));
			
		}else{								
			
			rateType.setValueId(Constants.MASTER_VALUE_RATE_TYPE_LCL);	
			clientRates = loadClientRates(bl, rateType);
			
		}	
	
		
		/* Esto lo que hace es recorrer la lista de clientRates y compararlo con la de costSales 
		 * si el rubro de clientRates existe en costSales y no es OtherCost entonces se escribe el valor del rate del clientRate
		 * en el valor Sale del CostSale; si existe en costSales y es OtherCost entonces se suma el valor del rate del clientRate 
		 * en el valor Sale del CostSale; si no existe en costSales y es OtherCost entonces se crea un nuevo CostSale en la lista de costSales 
		 * y se le escribe lo que hay en el valor rate del clientRate sobre el valor Sale del CostSale.
		 */
		
		for(ClientRate _tmpItem : clientRates){
			if(!_tmpItem.isOtherCharge()){				
				for(BlCostSale costSale : bl.getBlCostsSales()){
					costSale.setBlId(bl.getBlId());					
					if(_tmpItem.getChargeType().getValueId() == costSale.getChargeType().getValueId()){
						// If BL is FCL and the rate is not flat
						if(bl.isFCL() && !_tmpItem.isFlat()){
			
							calculateOceanFCLCostSale(bl, costSale, _tmpItem.getRateType(),  _tmpItem.getRate(), false);
							
						}else{
							costSale.setFlat(_tmpItem.isFlat());
							costSale.setSale(_tmpItem.getRate());
							costSale.setSaleMin(_tmpItem.getMinimumRate());
							break;
						}
					}
				}
				
			}else{
				boolean exist = false;
				for(BlCostSale othercostSale : bl.getBlOtherCostsSales()){
					othercostSale.setBlId(bl.getBlId());					
					if(_tmpItem.getChargeType().getValueId() == othercostSale.getChargeType().getValueId()){
						// If BL is FCL and the rate is not flat
						if(bl.isFCL() && !_tmpItem.isFlat() ){
							
							calculateOceanFCLCostSale(bl, othercostSale, _tmpItem.getRateType(),  _tmpItem.getRate(),false);							
							exist = true;
							
						}else{						
						
							othercostSale.setFlat(_tmpItem.isFlat());
							othercostSale.setSale(_tmpItem.getRate());
							othercostSale.setSaleMin(_tmpItem.getMinimumRate());
							exist = true;
							break;
						}
					}
				}
				if(!exist){
					BlCostSale _tmpCostSale = new BlCostSale();
					_tmpCostSale.setBlId(bl.getBlId());
					_tmpCostSale.setChargeType(_tmpItem.getChargeType());
					_tmpCostSale.setOtherCost(true);	
					
					// If BL is FCL and the rate is not flat
					if(bl.isFCL() && !_tmpItem.isFlat() ){
						
						calculateOceanFCLCostSale(bl, _tmpCostSale, _tmpItem.getRateType(),  _tmpItem.getRate(),false);
												
					}else{
						_tmpCostSale.setSale(_tmpItem.getRate());
						_tmpCostSale.setFlat(_tmpItem.isFlat());
						_tmpCostSale.setSaleMin(_tmpItem.getMinimumRate());
						
					}					
									
					bl.getBlOtherCostsSales().add(0,_tmpCostSale);
				}
			}
		}
		
		
		//set cost and sales when they are not flat rates
		for(BlCostSale _tmpCS : bl.getBlCostsSales()){
			if(!_tmpCS.isFlat()){
				
				if (!bl.isFCL()){					
				// case LCL use Weight [ton] or volume [m3]	
					// if CostSale not is INLAND FREIGHT
					if (! ((_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL)  
							||	(_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL)
							|| (_tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_LOCAL_DELIVERY_LCL))) {						
					
						double _tmpFactor = (bl.getTotalRealWTon() < bl.getTotalOceanVolM3())? bl.getTotalOceanVolM3() : bl.getTotalRealWTon();						
						
						// si la venta por el factor es menor que el minimo, use el minimo
						_tmpCS.setSale( _tmpCS.getSale() * (_tmpFactor) > _tmpCS.getSaleMin()?UtilFunctions.roundDecimalPlaces( _tmpCS.getSale() * (_tmpFactor),2):  _tmpCS.getSaleMin());						
						
					}
					// si el costsale es LOCAL DELIVERY
					if ( _tmpCS.getChargeType().getValueId() == Constants.MASTER_VALUE_LOCAL_DELIVERY_LCL)  {
						
						// si la venta por el Peso real es menor que el minimo, use el minimo
						_tmpCS.setSale( _tmpCS.getSale() * (bl.getTotalRealWKg()) > _tmpCS.getSaleMin()? UtilFunctions.roundDecimalPlaces(_tmpCS.getSale() * (bl.getTotalRealWKg()),2):  _tmpCS.getSaleMin());
					}
					
				}else if (bl.isFCL()){	
					
					// si la venta por el factor es menor que el minimo, use el minimo
					_tmpCS.setSale( _tmpCS.getSale() > _tmpCS.getSaleMin()?UtilFunctions.roundDecimalPlaces( _tmpCS.getSale(),2) :  _tmpCS.getSaleMin());	
					
				}
			}
		}
		//set other cost and sales when they are not flat rates
		for(BlCostSale _tmpOtherCS : bl.getBlOtherCostsSales()){
			if(!_tmpOtherCS.isFlat()){
				
				if (!bl.isFCL()){
					double _tmpFactor = (bl.getTotalRealWTon() < bl.getTotalOceanVolM3())? bl.getTotalOceanVolM3() : bl.getTotalRealWTon();
					
					// si la venta por el factor es menor que el minimo, use el minimo
					_tmpOtherCS.setSale( _tmpOtherCS.getSale() * (_tmpFactor) > _tmpOtherCS.getSaleMin()? UtilFunctions.roundDecimalPlaces(_tmpOtherCS.getSale() * (_tmpFactor),2):  _tmpOtherCS.getSaleMin());					
					
				}else if (bl.isFCL()){	
					
					// si la venta por el factor es menor que el minimo, use el minimo
					_tmpOtherCS.setSale( _tmpOtherCS.getSale() > _tmpOtherCS.getSaleMin()? UtilFunctions.roundDecimalPlaces(_tmpOtherCS.getSale(),2) :  _tmpOtherCS.getSaleMin());	
				}
			}
		}
		return bl;
	}
	
	public List<CarrierRate> loadCarrierRates(Bl bl, MasterValue rateType){
		List<CarrierRate> carrierRates = null;		
				
		CarrierPorts _tmpCarrierPort = new CarrierPorts();			
		_tmpCarrierPort.setCarrierId(bl.getCarrier().getCarrierId());
		_tmpCarrierPort.setPortOrigin(bl.getPortOrigin().getPortId());
		_tmpCarrierPort.setPortDestination(bl.getPortDestination().getPortId());
		_tmpCarrierPort.setRateType(rateType);
		
		
	
		try {
			carrierRates = blDao.loadCarrierRates(_tmpCarrierPort);
			
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
		}
		return carrierRates;
	}
	
	
	public List<ClientRate> loadClientRates(Bl bl, MasterValue rateType) {
		List<ClientRate> clientRates = null;
		
		ClientRatesPort _tmpClientRatesPort = new ClientRatesPort();
		_tmpClientRatesPort.setClientId(bl.getClient().getPartnerId());
		_tmpClientRatesPort.setCarrierId(bl.getCarrier().getCarrierId());
		_tmpClientRatesPort.setPortOrigin(bl.getPortOrigin().getPortId());
		_tmpClientRatesPort.setPortDestination(bl.getPortDestination().getPortId());
		_tmpClientRatesPort.setRateType(rateType);
		
		try {			
			clientRates = blDao.loadClientRates(_tmpClientRatesPort);
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
		}
		return clientRates;
		
	}
	
	private void resetAllBlCostSale(Bl bl) {
		for(BlCostSale costSale : bl.getBlCostsSales()){	
			// if CostSale not is INLAND FREIGHT
			if (! ((costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL)  
					||	(costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL))) {
				
				costSale.setSale(0);
				costSale.setCost(0);				
			}
				
			
		}
		for(BlCostSale otherCostSale : bl.getBlOtherCostsSales()){			
			otherCostSale.setSale(0);
			otherCostSale.setCost(0);
		}
		
	}
	
	private void resetAllBlCost(Bl bl_target, Bl bl_Rate) {
		
		if (bl_Rate.isMaster()){
			for(BlCostSale costSale : bl_target.getBlCostsSales()){		
				if (costSale.isShowInMaster()) {									
					costSale.setCost(0);				
				}
			}
			
		}else{
			
			for(BlCostSale costSale : bl_target.getBlCostsSales()){	
				
				
				// if CostSale not is INLAND FREIGHT
				if ( ((costSale.getChargeType().getValueId() != Constants.MASTER_VALUE_INLAND_FREIGHT_FCL)  
						&&	(costSale.getChargeType().getValueId() != Constants.MASTER_VALUE_INLAND_FREIGHT_LCL))
						&& (  (!costSale.isShowInMaster() && bl_Rate.isHouse() ) || bl_Rate.isRegular()   )) {
									
					costSale.setCost(0);				
				}
			}
			
		
			for(BlCostSale otherCostSale : bl_target.getBlOtherCostsSales()){
				otherCostSale.setCost(0);
			}
		}
		
	}
	
	private void resetAllBlSale(Bl bl) {
		for(BlCostSale costSale : bl.getBlCostsSales()){	
			// if CostSale not is INLAND FREIGHT
			if (! ((costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_FCL)  
					||	(costSale.getChargeType().getValueId() == Constants.MASTER_VALUE_INLAND_FREIGHT_LCL))) {
				
				costSale.setSale(0);
			}
		}
		for(BlCostSale otherCostSale : bl.getBlOtherCostsSales()){			
			otherCostSale.setSale(0);
		}
		
	}
	
	private void calculateOceanFCLCostSale(Bl bl, BlCostSale costSale, MasterValue rateType, double rateValue, boolean isCost ){
		for(BlContainer _tmpContainer : bl.getBlContainers()){
			if (_tmpContainer.getType().getValueId() ==  Constants.MASTER_VALUE_CONTAINER_20 ||
					_tmpContainer.getType().getValueId() ==  Constants.MASTER_VALUE_CONTAINER_20_FR){
				
				if (rateType.getValueId() == Constants.MASTER_VALUE_RATE_TYPE_20){
					if (isCost){	
						costSale.setCost(costSale.getCost() + rateValue );						
					}else{
						costSale.setSale(costSale.getSale() + rateValue );						
					}
					
				}								
			}
			if (_tmpContainer.getType().getValueId() ==  Constants.MASTER_VALUE_CONTAINER_40||
					_tmpContainer.getType().getValueId() ==  Constants.MASTER_VALUE_CONTAINER_40_FR	||
					_tmpContainer.getType().getValueId() ==  Constants.MASTER_VALUE_CONTAINER_40_HC){
			
				if (rateType.getValueId() == Constants.MASTER_VALUE_RATE_TYPE_40){
					if (isCost){
						costSale.setCost(costSale.getCost() + rateValue );
					}else{
						costSale.setSale(costSale.getSale() + rateValue );
					}
				}
			}
		}		
	}
	
	

	public Bl loadInitialOtherCostsSalesFromClientAndCarrier(Bl bl){
		List<CarrierRate> carrierRates = null;
		List<ClientRate> clientRates = null;
		
		CarrierPorts _tmpCarrierPort = new CarrierPorts();			
		_tmpCarrierPort.setCarrierId(bl.getCarrier().getCarrierId());
		_tmpCarrierPort.setPortOrigin(bl.getPortOrigin().getPortId());
		_tmpCarrierPort.setPortDestination(bl.getPortDestination().getPortId());
		
		ClientRatesPort _tmpClientRatesPort = new ClientRatesPort();
		_tmpClientRatesPort.setClientId(bl.getClient().getPartnerId());
		_tmpClientRatesPort.setCarrierId(bl.getCarrier().getCarrierId());
		_tmpClientRatesPort.setPortOrigin(bl.getPortOrigin().getPortId());
		_tmpClientRatesPort.setPortDestination(bl.getPortDestination().getPortId());
		try {
			carrierRates = blDao.loadCarrierRates(_tmpCarrierPort);
			clientRates = blDao.loadClientRates(_tmpClientRatesPort);
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
		}	
		
		for(ClientRate _tmpItem : clientRates){
			if(_tmpItem.isOtherCharge()){					
				BlCostSale _tmpCostSale = new BlCostSale();				
				_tmpCostSale.setChargeType(_tmpItem.getChargeType());				
				_tmpCostSale.setOtherCost(true);
				bl.getBlOtherCostsSales().add(_tmpCostSale);
			}
		}
		
		
		for(CarrierRate _tmpItem : carrierRates){
			if(_tmpItem.isOtherCharge() ){
				BlCostSale _tmpCostSale = new BlCostSale();				
				_tmpCostSale.setChargeType(_tmpItem.getChargeType());
				_tmpCostSale.setOtherCost(true);
				bl.getBlOtherCostsSales().add(_tmpCostSale);
			}
		}
		
		return bl;
	}

	public IBlDao getBlDao() {
		return blDao;
	}

	public void setBlDao(IBlDao blDao) {
		this.blDao = blDao;
	}

	@Override
	public List<BLDeclaredValue> loadBlDeclaredValues(Bl bl) throws Exception {
		List<BLDeclaredValue> _tmpBlDeclaredValues = null; 
		if(bl != null){
			try {
				_tmpBlDeclaredValues = blDao.loadBlDeclaredValues(bl);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlDeclaredValues;
	}
	
	public CarrierPorts loadEffectiveDate(CarrierPorts carrierPort) throws Exception{
		if(carrierPort != null){
			try {
				carrierPort = blDao.loadEffectiveDate(carrierPort);
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return carrierPort;	
	}
	
	public List<ItemProrated> fillBlItemsProratedInformation(List<ItemProrated> itemsProrated) throws Exception{
		if(itemsProrated != null){
			try {
				itemsProrated = blDao.fillBlItemsProratedInformation(itemsProrated);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return itemsProrated;
	}
	
	public String loadWhRemarks(Bl bl) throws Exception{
		String result = "";
		// se usa para que no hayan wh repetidos
		List<Integer> whIds = new ArrayList<Integer>();
		if(bl.getBlItems() != null){
			for(BlItem _tmpItem : bl.getBlItems()){
				String _tmp = _tmpItem.getWhReceipt().getRemarks();
				Integer _whId = _tmpItem.getWhReceipt().getWhReceiptId();				
				if(_tmp != null && !whIds.contains(_whId)){
					whIds.add(_whId);
					result = result.concat(_tmp + ",");
				}	
			}
		}
		return result;
	}

	@Override
	public List<BlInlandCS> createInlandCSFromClientOrder(BlItem blItem) throws Exception {
		List<BlInlandCS> _tmpBlInlandCSs = null; 
		if(blItem != null){
			try {
				_tmpBlInlandCSs = blDao.createInlandCSFromClientOrder(blItem);				
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return _tmpBlInlandCSs;
		
	}

	

}