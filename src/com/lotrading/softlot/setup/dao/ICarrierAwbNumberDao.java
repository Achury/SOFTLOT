package com.lotrading.softlot.setup.dao;

import java.util.List;

import com.lotrading.softlot.setup.entity.CarrierAwbNumber;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface ICarrierAwbNumberDao {

	/**
	 * 
	 * @param carrierAwbNumber
	 * @throws Exception 
	 */
	public List<CarrierAwbNumber> createCarrierAwbNumber(List<CarrierAwbNumber> carrierAwbNumbers) throws Exception;

	public boolean updateCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception;
	
	/**
	 * 
	 * @param carrierAwbNumber
	 */
	public boolean deleteCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception;

}