package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.LODepartment.shipments.dao.IBlPalletizedItemDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlPalletizedItemDaoImpl extends DAOTemplate implements IBlPalletizedItemDao {

	private Log log = LogFactory.getLog(BlPalletizedItemDaoImpl.class);
	
	public BlPalletizedItemDaoImpl(){

	}

	/**
	 * 
	 * @param blPalletizedItem
	 * @throws Exception 
	 */
	public int createBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_palletized_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blPalletizedItem.getBlId());
			cst.setInt(3, blPalletizedItem.getPieces());
			cst.setInt(4, blPalletizedItem.getType().getValueId());
			cst.setDouble(5, blPalletizedItem.getItemLength());
			cst.setDouble(6, blPalletizedItem.getItemWidth());		
			cst.setDouble(7, blPalletizedItem.getItemHeight());		
			cst.setDouble(8, blPalletizedItem.getItemWeight());	
			
			if(blPalletizedItem.getPalletId() != null && !blPalletizedItem.getPalletId().equals("")){
				cst.setInt(9,  Integer.parseInt(blPalletizedItem.getPalletId()));
			}else{
				cst.setInt(9,  0);
			}			
			
			cst.setString(10, blPalletizedItem.getRemarks());
			cst.setString(11, blPalletizedItem.getWhLocation().getWhLocationId());
			cst.setInt(12, blPalletizedItem.getContainer().getContainerId());
			cst.setBoolean(13, blPalletizedItem.isHazardous());
			
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
	 * @param blPalletizedItem
	 * @throws Exception 
	 */
	public boolean updateBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_palletized_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blPalletizedItem.getPalletizedItemId());			
			cst.setInt(2, blPalletizedItem.getPieces());
			cst.setInt(3, blPalletizedItem.getType().getValueId());
			cst.setDouble(4, blPalletizedItem.getItemLength());
			cst.setDouble(5, blPalletizedItem.getItemWidth());		
			cst.setDouble(6, blPalletizedItem.getItemHeight());		
			cst.setDouble(7, blPalletizedItem.getItemWeight());					

			if(blPalletizedItem.getPalletId() != null && !blPalletizedItem.getPalletId().equals("")){
				cst.setInt(8,  Integer.parseInt(blPalletizedItem.getPalletId()));
			}else{
				cst.setInt(8,  0);
			}			
			
			cst.setString(9, blPalletizedItem.getRemarks());
			cst.setString(10, blPalletizedItem.getWhLocation().getWhLocationId());
			cst.setInt(11, blPalletizedItem.getContainer().getContainerId());
			cst.setBoolean(12, blPalletizedItem.isHazardous());
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
	
	public List<BlPalletizedItem> saveBlPalletizedItems(List<BlPalletizedItem> blPalletizedItems) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlPalletizedItem _tmpItem : blPalletizedItems){
				if(_tmpItem.getPalletizedItemId() <= 0){
					String sql = "{call sp_bl_palletized_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpItem.getBlId());
					cst.setInt(3, _tmpItem.getPieces());
					cst.setInt(4, _tmpItem.getType().getValueId());
					cst.setDouble(5, _tmpItem.getItemLength());
					cst.setDouble(6, _tmpItem.getItemWidth());		
					cst.setDouble(7, _tmpItem.getItemHeight());		
					cst.setDouble(8, _tmpItem.getItemWeight());	
					
					if(_tmpItem.getPalletId() != null && !_tmpItem.getPalletId().equals("")){
						cst.setInt(9,  Integer.parseInt(_tmpItem.getPalletId()));
					}else{
						cst.setInt(9,  0);
					}
					
					cst.setString(10, _tmpItem.getRemarks());
					cst.setString(11, _tmpItem.getWhLocation().getWhLocationId());
					cst.setInt(12, _tmpItem.getContainer().getContainerId());					
					cst.setBoolean(13, _tmpItem.isHazardous());
					if(cst.executeUpdate() > 0){
						_tmpItem.setPalletizedItemId(cst.getInt(1));
					}			
				}else if(_tmpItem.getPalletizedItemId() > 0){
					String sql = "{call sp_bl_palletized_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpItem.getPalletizedItemId());
					cst.setInt(2, _tmpItem.getPieces());
					cst.setInt(3, _tmpItem.getType().getValueId());
					cst.setDouble(4, _tmpItem.getItemLength());
					cst.setDouble(5, _tmpItem.getItemWidth());		
					cst.setDouble(6, _tmpItem.getItemHeight());		
					cst.setDouble(7, _tmpItem.getItemWeight());
					
					if(_tmpItem.getPalletId() != null && !_tmpItem.getPalletId().equals("")){
						cst.setInt(8, Integer.parseInt( _tmpItem.getPalletId()));
					}else{
						cst.setInt(8,  0);
					}
					
					cst.setString(9, _tmpItem.getRemarks());
					cst.setString(10, _tmpItem.getWhLocation().getWhLocationId());
					cst.setInt(11, _tmpItem.getContainer().getContainerId());
					cst.setBoolean(12, _tmpItem.isHazardous());
					
					cst.executeUpdate();
				}
			}		
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blPalletizedItems;
	}

	/**
	 * 
	 * @param blPalletizedItem
	 * @throws Exception 
	 */
	public boolean deleteBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_palletized_items_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blPalletizedItem.getPalletizedItemId());
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