package com.lotrading.softlot.security.logic.impl;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.security.dao.IOptionsDao;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.logic.IOptionLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class OptionLogicImpl implements IOptionLogic {

	private Log log = LogFactory.getLog(OptionLogicImpl.class);
	private IOptionsDao optionDao;

	public OptionLogicImpl(){

	}

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public boolean saveOption(Option option) throws Exception{
		boolean _tmpReturn = false;
		try {
			if(option == null){
				return false;
			}
			if(option.isParent()){
				option.setOrder(1);
			}else{
				option.setOrder(2);
			}
			if(option.getOptionId() <= 0){
				option.setEnteredDate(new Date());
				
				_tmpReturn = optionDao.createOption(option);
			}else if(option.getOptionId() > 0){
				_tmpReturn = optionDao.updateOption(option);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param option
	 * @throws Exception 
	 */
	public List<Option> searchOption(Option option) throws Exception{
		List<Option> options = null;
		try {
			if(option != null){
				options = optionDao.searchOptions(option);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return options;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOptionDao(IOptionsDao newVal){
		optionDao = newVal;
	}

	public IOptionsDao getOptionDao(){
		return optionDao;
	}
	
}