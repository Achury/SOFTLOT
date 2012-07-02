package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;
import com.lotrading.softlot.LODepartment.shipments.dao.IAwbItemsDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbItemsDaoImpl extends DAOTemplate implements IAwbItemsDao {

	private Log log = LogFactory.getLog(AwbUnCodeDaoImpl.class);
	
	public AwbItemsDaoImpl(){

	}
	
	public List<AwbItem> saveAwbItems(List<AwbItem> awbItems) throws Exception{
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbItem _tmpItem : awbItems){
				if(_tmpItem.getItemId() <= 0){
					String sql = "{call sp_awb_items_CREATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpItem.getAwbId());
					cst.setInt(3, _tmpItem.getPieces());
					cst.setInt(4, _tmpItem.getType().getValueId());
					cst.setDouble(5, _tmpItem.getItemLength());
					cst.setDouble(6, _tmpItem.getItemWidth());		
					cst.setDouble(7, _tmpItem.getItemHeight());		
					cst.setDouble(8, _tmpItem.getWeightLbs());	
					cst.setInt(9, _tmpItem.getRateClass().getValueId());
					cst.setInt(10, _tmpItem.getWhItemId());
					cst.setString(11, _tmpItem.getPoNumber());
					cst.setInt(12, _tmpItem.getInvoice().getInvoiceId());
					cst.setString(13, _tmpItem.getRemarks());
					cst.setInt(14, 0); /* Aqui va el id del Whlocation */
					cst.setBoolean(15, _tmpItem.isHazardous());
					cst.setInt(16, _tmpItem.getClientOrderId());
					cst.setString(17, _tmpItem.getWhLocation().getWhLocationId());
					if(_tmpItem.getPalletId() == null || _tmpItem.getPalletId().isEmpty()){
						cst.setInt(18, 0);
					}else{
						cst.setInt(18, Integer.parseInt(_tmpItem.getPalletId()));
					}
					if(cst.executeUpdate() > 0){
						_tmpItem.setItemId(cst.getInt(1));
					}			
				}else if(_tmpItem.getItemId() > 0){
					String sql = "{call sp_awb_items_UPDATE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpItem.getItemId());
					cst.setInt(2, _tmpItem.getPieces());
					cst.setInt(3, _tmpItem.getType().getValueId());
					cst.setDouble(4, _tmpItem.getItemLength());
					cst.setDouble(5, _tmpItem.getItemWidth());		
					cst.setDouble(6, _tmpItem.getItemHeight());		
					cst.setDouble(7, _tmpItem.getWeightLbs());	
					cst.setInt(8, _tmpItem.getRateClass().getValueId());
					cst.setInt(9, _tmpItem.getWhItemId());
					cst.setString(10, _tmpItem.getPoNumber());
					cst.setInt(11, _tmpItem.getInvoice().getInvoiceId());
					cst.setString(12, _tmpItem.getRemarks());
					cst.setInt(13, 0); /* Aqui va el id del Whlocation */
					cst.setBoolean(14, _tmpItem.isHazardous());
					cst.setInt(15, _tmpItem.getClientOrderId());
					cst.setString(16, _tmpItem.getWhLocation().getWhLocationId());
					if(_tmpItem.getPalletId() == null || _tmpItem.getPalletId().equals("")){
						cst.setInt(17, 0);
					}else{
						cst.setInt(17, Integer.parseInt(_tmpItem.getPalletId()));
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
		return awbItems;
	}

	/**
	 * 
	 * @param awbItem
	 * @throws Exception 
	 */
	public boolean deleteAwbItem(AwbItem awbItem) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_items_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbItem.getItemId());
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