package com.lotrading.softlot.setup.logic;

import java.util.List;

import com.lotrading.softlot.setup.entity.CarrierAwbNumber;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface ICarrierAwbNumberLogic {

	/**
	 * 
	 * @param carrierAwbNumber
	 * @throws Exception 
	 */
	public List<CarrierAwbNumber> saveCarrierAwbNumber(List<CarrierAwbNumber> carrierAwbNumber) throws Exception;

	public boolean updateCarrierAwbBlNumber(CarrierAwbNumber carrierAwbNumber) throws Exception;
	
	/**
	 * 
	 * @param carrierAwbNumber
	 */
	public boolean deleteCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception;

}