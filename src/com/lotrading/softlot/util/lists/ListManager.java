package com.lotrading.softlot.util.lists;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jcs.JCS;

import com.lotrading.softlot.util.base.spring.SpringContextUtil;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public class ListManager {
	private static Log log = LogFactory.getLog(ListManager.class);
	private static JCS listCache;
	
	static 
    {
        try {
        	listCache = JCS.getInstance("listCache");
        } catch (Exception ex) {
            log.error("error al crear instancia de cache de listas",ex);
        }
    }
	
	public ListManager(){

	}

	/**
	 * 
	 * @param listHandler
	 */
	public static synchronized void addList(ListHandler listHandler) throws Exception{
		if (listHandler == null)
            throw new Exception("");
    
        if (listHandler.getListId() == null)
            throw new Exception("");
        
        ListHandler cacheObject = (ListHandler) listCache.get(listHandler.getListId());
        
        if ( cacheObject != null ) {
            throw new Exception("The list already exists ["+listHandler.getListId()+"]");
        }
        else {
        	listHandler.clearElements();
        	listHandler.refreshList();
            synchronized(listCache) {
            	listCache.putInGroup(listHandler.getListId(), "ManagedList", listHandler);
            }
        }
	}

	/**
	 * 
	 * @param listId
	 */
	public synchronized static ListHandler getList(java.lang.String listId) throws Exception{
		ListHandler list = null;
        try
        {
            synchronized(listCache) {
            	list = (ListHandler) listCache.getFromGroup(listId, "ManagedList");
            }
            
            if ( list == null) 
            {
            	list = loadList(listId);
                if (list != null) {
                    addList(list);
                }                
            }
        }
        catch(Exception ex) {
            log.error("Error ["+listId+"]", ex);
            throw new Exception(ex);
        }
        return list;
	}

	/**
	 * 
	 * @param listId
	 */
	public static ListHandler loadList(java.lang.String listId) throws Exception{
		ListHandler _tmpReturn = null;
        try 
        {
            if (listId == null || listId.equals(""))
                throw new IllegalArgumentException("");
            
            log.debug("Cargando lista {"+listId+"}");
            
            _tmpReturn = (ListHandler)SpringContextUtil.getBean(listId);
        }
        catch (Exception ex) {
            log.error("Fallo la carga de la lista : "+listId, ex);
            throw ex;
        }
        return _tmpReturn;
	}
}