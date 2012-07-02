package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbInlandCsDao {

	/**
	 * 
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public int createAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception;

	/**
	 * 
	 * @param awbInlandCs
	 */
	public boolean updateAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception;

	/**
	 * 
	 * @param awbInlandCs
	 */
	public boolean deleteAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception;
	
	public List<AwbInlandCS> saveAwbInlandCSItems(List<AwbInlandCS> awbInlandCSItems) throws Exception;

}