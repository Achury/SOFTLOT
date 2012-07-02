package com.lotrading.softlot.setup.logic;
import java.util.List;
import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IMasterValueLogic {

	/**
	 * 
	 * @param masterValue
	 * @throws Exception 
	 */
	public boolean saveMasterValue(MasterValue masterValue) throws Exception;

	/**
	 * 
	 * @param masterValue
	 * @throws Exception 
	 */
	public List<MasterValue> searchMasterValue(MasterValue masterValue) throws Exception;

}