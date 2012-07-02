package com.lotrading.softlot.setup.dao;
import java.util.List;

import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IMasterValuesDao {

	/**
	 * 
	 * @param masterValue
	 * @throws Exception 
	 */
	public boolean createMasterValue(MasterValue masterValue) throws Exception;

	/**
	 * 
	 * @param masterValue
	 * @throws Exception 
	 */
	public boolean updateMasterValue(MasterValue masterValue) throws Exception;

	/**
	 * 
	 * @param masterValue
	 * @throws Exception 
	 */
	public List<MasterValue> searchMasterValue(MasterValue masterValue) throws Exception;

}