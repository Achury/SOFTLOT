package com.lotrading.softlot.businessPartners.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.IShipPickupDao;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.logic.IAddressLogic;
import com.lotrading.softlot.businessPartners.logic.IShipPickupLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:04 a.m.
 */
public class ShipPickupLogicImpl implements IShipPickupLogic {

	private Log log = LogFactory.getLog(ShipPickupLogicImpl.class);
	private IShipPickupDao shipPickupDao;
	private IAddressLogic addressLogic;

	public ShipPickupLogicImpl(){

	}

	/**
	 * 
	 * @param shipPickup
	 * @throws Exception 
	 */
	public int saveShipPickup(ShipPickUp shipPickup) throws Exception{
		int _tmpShipPickupId = 0;
		try {
			if(shipPickup == null){
				return _tmpShipPickupId;
			}
			
			if (shipPickup.getAddress() != null){					
				shipPickup.getAddress().setAddressId( addressLogic.saveAddress(shipPickup.getAddress()));				
			}
			// get the Group or department information ID
			//shipPickup.setPartnerDeptInfoId(shipPickupDao.getDeptInfoID(shipPickup));
			
			if(shipPickup.getShipPickUpId() <= 0){
				shipPickup.setEnteredDate(new Date());
				_tmpShipPickupId = shipPickupDao.createShipPickup(shipPickup);
			}else if(shipPickup.getShipPickUpId() > 0){
				if (shipPickupDao.updateShipPickup(shipPickup)){
				_tmpShipPickupId = shipPickup.getShipPickUpId();
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpShipPickupId;
	}
	
	public List<ShipPickUp> filterByShipPickup(ShipPickUp shipPick){
		List<ShipPickUp> shipPickups = null;
		if(shipPick != null){
			shipPickups = new ArrayList<ShipPickUp>();
			try {
				shipPickups = shipPickupDao.filterByShipPickup(shipPick);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
			}
		}
		return shipPickups;
	}

	public IShipPickupDao getShipPickupDao(){
		return shipPickupDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setShipPickupDao(IShipPickupDao newVal){
		shipPickupDao = newVal;
	}

	public IAddressLogic getAddressLogic() {
		return addressLogic;
	}

	public void setAddressLogic(IAddressLogic addressLogic) {
		this.addressLogic = addressLogic;
	}

}