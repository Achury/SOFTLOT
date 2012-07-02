package com.lotrading.softlot.invoice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.invoice.dao.IPackingListItemDao;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PackingListItem;
import com.lotrading.softlot.util.base.dao.DAOTemplate;
import com.lotrading.softlot.warehouse.entity.WhReceipt;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public class PackingListItemDaoImpl extends DAOTemplate implements IPackingListItemDao{

	private Log log = LogFactory.getLog(PackingListItemDaoImpl.class);
	
	public List<PackingListItem> loadPackingListItem(Invoice invoice) throws Exception{
		List<PackingListItem> itemList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;		
			sql = "{call lotradingdb.sp_invoice_packingListItem_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, invoice.getInvoiceId());
			rs = cst.executeQuery();
			if (rs.next()) {
				itemList = new ArrayList<PackingListItem>();
				do {
					PackingListItem _tmpItem = new PackingListItem();
					_tmpItem.setPackingListId(rs.getInt(1));
					_tmpItem.setInvoiceId(rs.getInt(2));
					_tmpItem.setHazardous(rs.getBoolean(3));
					_tmpItem.setPieces(rs.getInt(4));
					_tmpItem.getType().setValueId(rs.getInt(5));
					_tmpItem.getType().setValue1(rs.getString(6));
					_tmpItem.setLength(rs.getDouble(7));
					_tmpItem.setWidth(rs.getDouble(8));
					_tmpItem.setHeight(rs.getDouble(9));
					_tmpItem.setWeight(rs.getDouble(10));
					if(_tmpItem.getWhReceipt() == null) _tmpItem.setWhReceipt(new WhReceipt());
					_tmpItem.getWhReceipt().setWhReceiptId(rs.getInt(11));
					_tmpItem.setWhDetailId(rs.getInt(12));
					_tmpItem.setPalletNumber(rs.getString(13));
					_tmpItem.getWhReceipt().setWhReceiptNumber(rs.getString(14));
					itemList.add(_tmpItem);
				} while (rs.next());
				
				invoice.setPackingListItemsList(itemList);	
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return itemList;	
	}
	
}
