package com.lotrading.softlot.LODepartment.clientOrder.entity;

import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.util.base.Constants;

/**
 * Clase que se utiliza para encapsular la informacion de las llamadas que se le
 * hacen a un Cliente o Proveedor de LO Trading en el modulo de clientOrders
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 09-nov-2011 11:51:50 a.m.
 */

public class CallHistoryClientOrder extends CallHistory{

	private int clientOrderId;
	private int moduleId;

	public CallHistoryClientOrder(){
		super();
		setModuleId(Constants.MASTER_VALUE_CLIENT_ORDER_MODULE);
	}

	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	
}
