package com.lotrading.softlot.invoice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.invoice.dao.IPalletizedItemDao;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PalletizedItem;
import com.lotrading.softlot.util.base.dao.DAOTemplate;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 29-Feb-2012 04:15:55 p.m.
 */
public class PalletizedItemDaoImpl extends DAOTemplate implements IPalletizedItemDao {

	private Log log = LogFactory.getLog(PalletizedItemDaoImpl.class);
	
	public List<PalletizedItem> loadPalletizedItems(Invoice invoice) throws Exception {
		List<PalletizedItem> itemList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;		
			sql = "{call lotradingdb.sp_invoice_palletizedItem_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, invoice.getInvoiceId());
			rs = cst.executeQuery();
			if (rs.next()) {
				itemList = new ArrayList<PalletizedItem>();
				do {
					PalletizedItem _tmpItem = new PalletizedItem();
					_tmpItem.setPalletizedId(rs.getInt(1));
					_tmpItem.setWeight(rs.getDouble(2));
					_tmpItem.setHazardous(rs.getBoolean(3));
					_tmpItem.setPieces(rs.getInt(4));
					_tmpItem.getType().setValue1(rs.getString(5));
					_tmpItem.getType().setValueId(rs.getInt(6));
					_tmpItem.setLength(rs.getDouble(7));
					_tmpItem.setWidth(rs.getDouble(8));
					_tmpItem.setHeight(rs.getDouble(9));
					_tmpItem.setWhPosition(rs.getString(10));
					
					itemList.add(_tmpItem);
					
				} while (rs.next());
				invoice.setPalletizedItemsList(itemList);
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
