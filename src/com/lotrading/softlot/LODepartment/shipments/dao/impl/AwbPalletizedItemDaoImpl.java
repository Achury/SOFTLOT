package com.lotrading.softlot.LODepartment.shipments.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbPalletizedItemDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Jun-2012 11:29:00 AM
 */
public class AwbPalletizedItemDaoImpl extends DAOTemplate implements IAwbPalletizedItemDao{

	
	private Log log = LogFactory.getLog(AwbPalletizedItemDaoImpl.class);

	/**
	 * 
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public int creatAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_palletized_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, awbPalletizedItem.getAwbId());
			cst.setInt(3, awbPalletizedItem.getPieces());
			cst.setInt(4, awbPalletizedItem.getType().getValueId());
			cst.setDouble(5, awbPalletizedItem.getItemLength());
			cst.setDouble(6, awbPalletizedItem.getItemWidth());		
			cst.setDouble(7, awbPalletizedItem.getItemHeight());		
			cst.setDouble(8, awbPalletizedItem.getItemWeight());	
			
			if(awbPalletizedItem.getPalletId() != null && !awbPalletizedItem.getPalletId().equals("")){
				cst.setInt(9,  Integer.parseInt(awbPalletizedItem.getPalletId()));
			}else{
				cst.setInt(9,  0);
			}			
			
			cst.setString(10, awbPalletizedItem.getRemarks());
			cst.setString(11, awbPalletizedItem.getWhLocation().getWhLocationId());
			cst.setInt(12, awbPalletizedItem.getRateClass().getValueId());
			
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
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public boolean updateAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_palletized_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbPalletizedItem.getPalletizedItemId());			
			cst.setInt(2, awbPalletizedItem.getPieces());
			cst.setInt(3, awbPalletizedItem.getType().getValueId());
			cst.setDouble(4, awbPalletizedItem.getItemLength());
			cst.setDouble(5, awbPalletizedItem.getItemWidth());		
			cst.setDouble(6, awbPalletizedItem.getItemHeight());		
			cst.setDouble(7, awbPalletizedItem.getItemWeight());					

			if(awbPalletizedItem.getPalletId() != null && !awbPalletizedItem.getPalletId().equals("")){
				cst.setInt(8,  Integer.parseInt(awbPalletizedItem.getPalletId()));
			}else{
				cst.setInt(8,  0);
			}			
			
			cst.setString(9, awbPalletizedItem.getRemarks());
			cst.setString(10, awbPalletizedItem.getWhLocation().getWhLocationId());
			cst.setInt(11, awbPalletizedItem.getRateClass().getValueId());
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

	/**
	 * 
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public List<AwbPalletizedItem> saveAwbPalletizedItems(List<AwbPalletizedItem> awbPalletizedItems) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbPalletizedItem _tmpItem : awbPalletizedItems){
				if(_tmpItem.getPalletizedItemId() <= 0){
					String sql = "{call sp_awb_palletized_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpItem.getAwbId());
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
					cst.setInt(12, _tmpItem.getRateClass().getValueId());
					if(cst.executeUpdate() > 0){
						_tmpItem.setPalletizedItemId(cst.getInt(1));
					}			
				}else if(_tmpItem.getPalletizedItemId() > 0){
					String sql = "{call sp_awb_palletized_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?)}";
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
					cst.setInt(11, _tmpItem.getRateClass().getValueId());
					cst.executeUpdate();
				}
			}		
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbPalletizedItems;
	}

	/**
	 * 
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public boolean deleteAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception {
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_palletized_items_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbPalletizedItem.getPalletizedItemId());
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
