package com.lotrading.softlot.warehouse.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.util.base.dao.DAOTemplate;
import com.lotrading.softlot.warehouse.dao.IWhReceiptDao;
import com.lotrading.softlot.warehouse.entity.WhItem;
import com.lotrading.softlot.warehouse.entity.WhReceipt;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public class WhReceiptDaoImpl extends DAOTemplate implements IWhReceiptDao{

	private Log log = LogFactory.getLog(WhReceiptDaoImpl.class);
	
	public List<WhReceipt> searchWhReceipt(WhReceipt whReceipt) throws Exception {
		
		return null;
	}

	
	public WhReceipt loadWhItems(WhReceipt whReceipt) throws Exception {
		List<WhItem> whItemsList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_warehouse_items_LOAD(?)}";
			cst = conn.prepareCall(sql);
			if(whReceipt != null){
				cst.setString(1, whReceipt.getWhReceiptNumber());
			}
			rs = cst.executeQuery();
			if(rs.next()){
				whItemsList = new ArrayList<WhItem>();
				whReceipt.setRemarks(rs.getString(21));
				do{
					WhItem _tmpItem = new WhItem();
					
					_tmpItem.setWhItemId(rs.getInt(1));
					_tmpItem.setItemWeight(rs.getDouble(2));
					_tmpItem.getType().setValue1(rs.getString(3));
					_tmpItem.setPoNumber(rs.getString(4));
					_tmpItem.setLocationId(rs.getString(5));
					_tmpItem.setStatus(rs.getInt(6));
					_tmpItem.getWhReceipt().setWhReceiptNumber(whReceipt.getWhReceiptNumber());
					_tmpItem.setItemLength(rs.getDouble(7));
					_tmpItem.setItemHeight(rs.getDouble(8));
					_tmpItem.setItemWidth(rs.getDouble(9));
					_tmpItem.setPieces(rs.getInt(10));
					_tmpItem.setRemarks(rs.getString(11));
					_tmpItem.setHazardous(rs.getBoolean(12));
					_tmpItem.setShipped(rs.getBoolean(13));
					_tmpItem.setWorksheetId(rs.getInt(14));
					_tmpItem.setTrackingNumber(rs.getString(15));
					_tmpItem.getType().setValueId(rs.getInt(16));
					whReceipt.getClient().setName(rs.getString(17));
					whReceipt.getSupplier().setName(rs.getString(18));
					_tmpItem.getWhReceipt().setWhReceiptId(rs.getInt(19));
					_tmpItem.setClientOrderId(rs.getInt(20));
					whReceipt.setRemarks(rs.getString(21));
					whItemsList.add(_tmpItem);
				}while(rs.next());
				whReceipt.setWhItems(whItemsList);
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}finally{
			close(conn);
		}
		return whReceipt;
	}

}
