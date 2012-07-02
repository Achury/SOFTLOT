package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.dao.IBlItemDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlItemDaoImpl extends DAOTemplate implements IBlItemDao {

	private Log log = LogFactory.getLog(BlItemDaoImpl.class);
	
	public BlItemDaoImpl(){

	}

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public int createBlItem(BlItem blItem) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blItem.getBlId());
			cst.setInt(3, blItem.getPieces());
			cst.setInt(4, blItem.getType().getValueId());
			cst.setDouble(5, blItem.getItemLength());
			cst.setDouble(6, blItem.getItemWidth());		
			cst.setDouble(7, blItem.getItemHeight());		
			cst.setDouble(8, blItem.getItemWeight());	
			cst.setInt(9, blItem.getWhItemId());
			cst.setString(10, blItem.getPoNumber());
			cst.setInt(11, blItem.getInvoice().getInvoiceId());
			cst.setString(12, blItem.getRemarks());
			cst.setString(13, blItem.getWhLocation().getWhLocationId());
			cst.setInt(14, blItem.getContainer().getContainerId());
			cst.setBoolean(15, blItem.isHazardous());
			cst.setInt(16, blItem.getClientOrderId());
			
			if(blItem.getPalletId() != null && !blItem.getPalletId().equals("")){
				cst.setInt(17, Integer.parseInt(blItem.getPalletId()));
			}else{
				cst.setInt(17,  0);
			}
			
			if(cst.executeUpdate() > 0){
				createdId = cst.getInt(1);				
			}			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return createdId;
	}

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public boolean updateBlItem(BlItem blItem) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blItem.getItemId());			
			cst.setInt(2, blItem.getPieces());
			cst.setInt(3, blItem.getType().getValueId());
			cst.setDouble(4, blItem.getItemLength());
			cst.setDouble(5, blItem.getItemWidth());		
			cst.setDouble(6, blItem.getItemHeight());		
			cst.setDouble(7, blItem.getItemWeight());				
			cst.setInt(8, blItem.getWhItemId());
			cst.setString(9, blItem.getPoNumber());
			cst.setInt(10, blItem.getInvoice().getInvoiceId());
			cst.setString(11, blItem.getRemarks());
			cst.setString(12, blItem.getWhLocation().getWhLocationId());
			cst.setInt(13, blItem.getContainer().getContainerId());
			cst.setBoolean(14, blItem.isHazardous());	
			
			if(blItem.getPalletId() != null && !blItem.getPalletId().equals("")){
				cst.setInt(15, Integer.parseInt( blItem.getPalletId()));
			}else{
				cst.setInt(15,  0);
			}
			
			if(cst.executeUpdate() > 0){
				updated = true;
			}			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return updated;
	}
	
	public List<BlItem> saveBlItems(List<BlItem> blItems) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlItem _tmpItem : blItems){
				if(_tmpItem.getItemId() <= 0){
					String sql = "{call sp_bl_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpItem.getBlId());
					cst.setInt(3, _tmpItem.getPieces());
					cst.setInt(4, _tmpItem.getType().getValueId());
					cst.setDouble(5, _tmpItem.getItemLength());
					cst.setDouble(6, _tmpItem.getItemWidth());		
					cst.setDouble(7, _tmpItem.getItemHeight());		
					cst.setDouble(8, _tmpItem.getItemWeight());	
					cst.setInt(9, _tmpItem.getWhItemId());
					cst.setString(10, _tmpItem.getPoNumber());
					cst.setInt(11, _tmpItem.getInvoice().getInvoiceId());
					cst.setString(12, _tmpItem.getRemarks());
					cst.setString(13, _tmpItem.getWhLocation().getWhLocationId());
					cst.setInt(14, _tmpItem.getContainer().getContainerId());					
					cst.setBoolean(15, _tmpItem.isHazardous());
					cst.setInt(16, _tmpItem.getClientOrderId());
					
					if(_tmpItem.getPalletId() != null && !_tmpItem.getPalletId().equals("")){
						cst.setInt(17,  Integer.parseInt(_tmpItem.getPalletId()));
					}else{
						cst.setInt(17,  0);
					}
					
					if(cst.executeUpdate() > 0){
						_tmpItem.setItemId(cst.getInt(1));
					}			
				}else if(_tmpItem.getItemId() > 0){
					String sql = "{call sp_bl_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpItem.getItemId());
					cst.setInt(2, _tmpItem.getPieces());
					cst.setInt(3, _tmpItem.getType().getValueId());
					cst.setDouble(4, _tmpItem.getItemLength());
					cst.setDouble(5, _tmpItem.getItemWidth());		
					cst.setDouble(6, _tmpItem.getItemHeight());		
					cst.setDouble(7, _tmpItem.getItemWeight());
					cst.setInt(8, _tmpItem.getWhItemId());
					cst.setString(9, _tmpItem.getPoNumber());
					cst.setInt(10, _tmpItem.getInvoice().getInvoiceId());
					cst.setString(11, _tmpItem.getRemarks());
					cst.setString(12, _tmpItem.getWhLocation().getWhLocationId());
					cst.setInt(13, _tmpItem.getContainer().getContainerId());
					cst.setBoolean(14, _tmpItem.isHazardous());
					
					if(_tmpItem.getPalletId() != null && !_tmpItem.getPalletId().equals("")){
						cst.setInt(15,  Integer.parseInt(_tmpItem.getPalletId()));
					}else{
						cst.setInt(15,  0);
					}
					
					
					cst.executeUpdate();
				}
			}		
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blItems;
	}

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public boolean deleteBlItem(BlItem blItem) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_items_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blItem.getItemId());
			if(cst.executeUpdate() > 0){
				deleted = true;
			}	
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return deleted;
	}

}