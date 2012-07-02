package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbInlandCsLogic {

	/**
	 * 
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public List<AwbInlandCS> saveAwbInlandCsItems(List<AwbInlandCS> awbInlandCsList) throws Exception;

	/**
	 * 
	 * @param awbInlandCs
	 */
	public boolean deleteAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception;

}