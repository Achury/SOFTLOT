package com.lotrading.softlot.setup.logic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.IPortDao;
import com.lotrading.softlot.setup.entity.Port;
import com.lotrading.softlot.setup.logic.IPortLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class PortLogicImpl implements IPortLogic {

	private Log log = LogFactory.getLog(PortLogicImpl.class);
	private IPortDao portDao;

	public PortLogicImpl() {

	}

	/**
	 * 
	 * @param port
	 */
	public boolean savePort(Port port) throws Exception {
		boolean _tmpReturn = false;
		try {
			if (port == null)
				return false;
			if (port.getPortId() <= 0) {
				port.setEnteredDate(new Date());
				_tmpReturn = portDao.createPort(port);
			} else if (port.getPortId() > 0) {
				_tmpReturn = portDao.updatePort(port);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param port
	 */
	public List<Port> searchPort(Port port) throws Exception {
		List<Port> ports = null;
		try {
			if (port != null) {
				ports = new ArrayList<Port>();
				ports = portDao.searchPort(port);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return ports;
	}

	public IPortDao getPortDao() {
		return portDao;
	}

	public void setPortDao(IPortDao portDao) {
		this.portDao = portDao;
	}

}