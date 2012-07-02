package com.lotrading.softlot.businessPartners.logic;

import java.util.List;

import com.lotrading.softlot.businessPartners.entity.ShipPickUp;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IShipPickupLogic {

	/**
	 * 
	 * @param shipPickup
	 * @throws Exception
	 */
	public int saveShipPickup(ShipPickUp shipPickup) throws Exception;

	public List<ShipPickUp> filterByShipPickup(ShipPickUp shipPickup)
			throws Exception;

}