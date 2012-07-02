package com.lotrading.softlot.security.dao;
import java.util.List;

import com.lotrading.softlot.security.entity.Option;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IOptionsDao {

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public boolean createOption(Option option) throws Exception;

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public boolean updateOption(Option option) throws Exception;

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public List<Option> searchOptions(Option option) throws Exception;

}