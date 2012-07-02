package com.lotrading.softlot.LODepartment.shipments.dao;
import java.util.List;


import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlUnCodeDao {

	/**
	 * 
	 * @param blUnCode
	 * @throws Exception 
	 */
	public int createUnCode(BlUnCode blUnCode) throws Exception;

	/**
	 * 
	 * @param blUnCode
	 */
	public boolean updateUnCode(BlUnCode blUnCode) throws Exception;

	/**
	 * 
	 * @param blUnCode
	 */
	public boolean deleteUnCode(BlUnCode blUnCode) throws Exception;
	
	/**
	 * 
	 * @param blUnCodes
	 */
	public List<BlUnCode> saveUnCodes(List<BlUnCode> blUnCodes) throws Exception;

}