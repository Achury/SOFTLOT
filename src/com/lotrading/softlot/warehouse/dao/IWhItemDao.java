package com.lotrading.softlot.warehouse.dao;

import java.util.List;

import com.lotrading.softlot.warehouse.entity.WhItem;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public interface IWhItemDao {
	
	public boolean createWhItem(WhItem whItem) throws Exception;
	
	public boolean updateWhItem(WhItem whItem) throws Exception;
	
	public List<WhItem> saveWhItem(List<WhItem> whItemList) throws Exception;
	
	public WhItem loadWhItem(WhItem whItem) throws Exception;
}
