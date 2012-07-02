package com.lotrading.softlot.warehouse.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.util.base.dao.DAOTemplate;
import com.lotrading.softlot.warehouse.dao.IWhItemDao;
import com.lotrading.softlot.warehouse.entity.WhItem;

/**
 * @author JUAN DAVID URIBE - MARLON PINEDA
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public class WhItemDaoImpl extends DAOTemplate implements IWhItemDao{

	private Log log = LogFactory.getLog(WhItemDaoImpl.class);
	
	public boolean createWhItem(WhItem whItem) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_warehouse_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setDouble(2, whItem.getItemWeight());
			cst.setString(3, whItem.getType().getValue1());
			cst.setString(4, whItem.getPoNumber());
			cst.setString(5, whItem.getLocationId());
			cst.setInt(6, whItem.getStatus());
			cst.setInt(7, whItem.getWhReceipt().getWhReceiptId());
			cst.setDouble(8, whItem.getItemLength());
			cst.setDouble(9, whItem.getItemWidth());
			cst.setDouble(10, whItem.getItemHeight());
			cst.setInt(11, whItem.getPieces());
			cst.setString(12, whItem.getRemarks());
			cst.setBoolean(13, whItem.isHazardous());
			cst.setBoolean(14, whItem.isShipped());
			cst.setInt(15, whItem.getWorksheetId());
			cst.setString(16, whItem.getTrackingNumber());
			cst.setInt(17, whItem.getType().getValueId());
			
			if(cst.executeUpdate() > 0){
				updated = true;
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			e.printStackTrace();
			throw e;
		}finally{
			close(conn);
		}
		return updated;
	}
	
	public boolean updateWhItem(WhItem whItem) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_warehouse_itmes_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, whItem.getWhItemId());
			cst.setDouble(2, whItem.getItemWeight());
			cst.setString(3, whItem.getType().getValue1());
			cst.setString(4, whItem.getPoNumber());
			cst.setString(5, whItem.getLocationId());
			cst.setInt(6, whItem.getStatus());
			cst.setDouble(7, whItem.getItemLength());
			cst.setDouble(8, whItem.getItemWidth());
			cst.setDouble(9, whItem.getItemHeight());
			cst.setInt(10, whItem.getPieces());
			cst.setString(11, whItem.getRemarks());
			cst.setBoolean(12, whItem.isHazardous());
			cst.setBoolean(13, whItem.isShipped());
			cst.setInt(14, whItem.getWorksheetId());
			cst.setString(15, whItem.getTrackingNumber());
			cst.setInt(16, whItem.getType().getValueId());
			
			if(cst.executeUpdate() > 0){
				updated = true;
			}
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			e.printStackTrace();
			throw e;
		}finally{
			close(conn);
		}
		return updated;
	}
	
	public List<WhItem> saveWhItem(List<WhItem> whItemList) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(WhItem whItem : whItemList){
				if(whItem.getWhItemId() > 0){
					String sql = "{call lotradingdb.sp_warehouse_itmes_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, whItem.getWhItemId());
					cst.setDouble(2, whItem.getItemWeight());
					cst.setString(3, whItem.getType().getValue1());
					cst.setString(4, whItem.getPoNumber());
					cst.setString(5, whItem.getLocationId());
					cst.setInt(6, whItem.getStatus());
					cst.setDouble(7, whItem.getItemLength());
					cst.setDouble(8, whItem.getItemWidth());
					cst.setDouble(9, whItem.getItemHeight());
					cst.setInt(10, whItem.getPieces());
					cst.setString(11, whItem.getRemarks());
					cst.setBoolean(12, whItem.isHazardous());
					cst.setBoolean(13, whItem.isShipped());
					cst.setInt(14, whItem.getWorksheetId());
					cst.setString(15, whItem.getTrackingNumber());
					cst.setInt(16, whItem.getType().getValueId());
					
					
					if(cst.executeUpdate() > 0){
						updated = true;
					}
				}else{
					String sql = "{call lotradingdb.sp_warehouse_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setDouble(2, whItem.getItemWeight());
					cst.setString(3, whItem.getType().getValue1());
					cst.setString(4, whItem.getPoNumber());
					cst.setString(5, whItem.getLocationId());
					cst.setInt(6, whItem.getStatus());
					cst.setInt(7, whItem.getWhReceipt().getWhReceiptId());
					cst.setDouble(8, whItem.getItemLength());
					cst.setDouble(9, whItem.getItemWidth());
					cst.setDouble(10, whItem.getItemHeight());
					cst.setInt(11, whItem.getPieces());
					cst.setString(12, whItem.getRemarks());
					cst.setBoolean(13, whItem.isHazardous());
					cst.setBoolean(14, whItem.isShipped());
					cst.setInt(15, whItem.getWorksheetId());
					cst.setString(16, whItem.getTrackingNumber());
					cst.setInt(17, whItem.getType().getValueId());
					
					if(cst.executeUpdate() > 0){
						whItem.setWhItemId(cst.getInt(1));
						updated = true;
					}
				}
			}
			
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			e.printStackTrace();
			throw e;
		}finally{
			close(conn);
		}
		return whItemList;
	}
	
	public WhItem loadWhItem(WhItem whItem) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_warehouse_item_LOAD_ITEM(?)}";
			cst = conn.prepareCall(sql);
			if(whItem != null){
				cst.setInt(1, whItem.getWhItemId());
			}
			rs = cst.executeQuery();
			if(rs.next()){
				whItem = new WhItem();
				
				whItem.setWhItemId(rs.getInt(1));
				whItem.setItemWeight(rs.getDouble(2));
				whItem.getType().setValue1(rs.getString(3));
				whItem.setPoNumber(rs.getString(4));
				whItem.setLocationId(rs.getString(5));
				whItem.setStatus(rs.getInt(6));
				whItem.getWhReceipt().setWhReceiptId(rs.getInt(7));
				whItem.setItemLength(rs.getDouble(8));
				whItem.setItemHeight(rs.getDouble(9));
				whItem.setItemWidth(rs.getDouble(10));
				whItem.setPieces(rs.getInt(11));
				whItem.setRemarks(rs.getString(12));
				whItem.setHazardous(rs.getBoolean(13));
				whItem.setShipped(rs.getBoolean(14));
				whItem.setWorksheetId(rs.getInt(15));
				whItem.setTrackingNumber(rs.getString(16));
				whItem.getType().setValueId(rs.getInt(17));
			}
			rs.close();
		}catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			e.printStackTrace();
			throw e;
		}finally{
			close(conn);
		}
		return whItem;
	}
}
