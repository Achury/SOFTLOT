package com.lotrading.softlot.security.logic;
import java.util.List;

import com.lotrading.softlot.security.entity.Option;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IOptionLogic {

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public boolean saveOption(Option option) throws Exception;

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public List<Option> searchOption(Option option) throws Exception;

}