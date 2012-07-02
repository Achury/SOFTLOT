package com.lotrading.softlot.setup.dao;

import java.util.List;

import com.lotrading.softlot.setup.entity.Port;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IPortDao {

	/**
	 * 
	 * @param port
	 * @throws Exception
	 */
	public boolean createPort(Port port) throws Exception;

	/**
	 * 
	 * @param port
	 * @throws Exception
	 */
	public boolean updatePort(Port port) throws Exception;

	/**
	 * 
	 * @param port
	 * @throws Exception
	 */
	public List<Port> searchPort(Port port) throws Exception;

}