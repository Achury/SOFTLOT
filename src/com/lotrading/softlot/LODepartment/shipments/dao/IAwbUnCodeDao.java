package com.lotrading.softlot.LODepartment.shipments.dao;
import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbUnCode;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbUnCodeDao {

	/**
	 * 
	 * @param awbUnCode
	 * @throws Exception 
	 */
	public int createUnCode(AwbUnCode awbUnCode) throws Exception;

	/**
	 * 
	 * @param awbUnCode
	 */
	public boolean updateUnCode(AwbUnCode awbUnCode) throws Exception;

	/**
	 * 
	 * @param awbUnCode
	 */
	public boolean deleteUnCode(AwbUnCode awbUnCode) throws Exception;
	
	public List<AwbUnCode> saveUnCodes(List<AwbUnCode> awbUnCodes) throws Exception;

}