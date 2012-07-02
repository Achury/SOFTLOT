package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlCostSaleDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlCostSaleDaoImpl extends DAOTemplate implements IBlCostSaleDao {

	private Log log = LogFactory.getLog(BlCostSaleDaoImpl.class);
	
	public BlCostSaleDaoImpl(){

	}

	/**
	 * 
	 * @param blCostSale
	 * @throws Exception 
	 */
	public int createBlCostSale(BlCostSale blCostSale) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_cost_sale_CREATE(?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blCostSale.getBlId());
			cst.setInt(3, blCostSale.getChargeType().getValueId());
			cst.setDouble(4, blCostSale.getCost());
			cst.setDouble(5, blCostSale.getSale());
			cst.setString(6, blCostSale.getNotes());
			cst.setBoolean(7, blCostSale.isReviewed());
			cst.setBoolean(8, blCostSale.isOtherCost());
			cst.setBoolean(9, blCostSale.isSelectedToBlDoc());
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
	 * @param blCostSale
	 * @throws Exception 
	 */
	public boolean updateBlCostSale(BlCostSale blCostSale) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_cost_sale_UPDATE(?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blCostSale.getBlCostSaleId());
			cst.setInt(2, blCostSale.getChargeType().getValueId());
			cst.setDouble(3, blCostSale.getCost());
			cst.setDouble(4, blCostSale.getSale());
			cst.setString(5, blCostSale.getNotes());
			cst.setBoolean(6, blCostSale.isReviewed());
			cst.setBoolean(7, blCostSale.isOtherCost());
			cst.setBoolean(8, blCostSale.isSelectedToBlDoc());
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
	
	public List<BlCostSale> saveBlCostSale(List<BlCostSale> blCostSales) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlCostSale _tmpCostSale: blCostSales){
				if(_tmpCostSale.getBlCostSaleId() > 0){
					String sql = "{call sp_bl_cost_sale_UPDATE(?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpCostSale.getBlCostSaleId());
					cst.setInt(2, _tmpCostSale.getChargeType().getValueId());
					cst.setDouble(3, _tmpCostSale.getCost());
					cst.setDouble(4, _tmpCostSale.getSale());
					cst.setString(5, _tmpCostSale.getNotes());
					cst.setBoolean(6, _tmpCostSale.isReviewed());
					cst.setBoolean(7, _tmpCostSale.isOtherCost());
					cst.setBoolean(8, _tmpCostSale.isSelectedToBlDoc());
					
					cst.executeUpdate();			
					
				
				}else if(   ( _tmpCostSale.isOtherCost() && !_tmpCostSale.isEmpty()) || (!_tmpCostSale.isOtherCost())  ){
					String sql = "{call sp_bl_cost_sale_CREATE(?,?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpCostSale.getBlId());
					cst.setInt(3, _tmpCostSale.getChargeType().getValueId());
					cst.setDouble(4, _tmpCostSale.getCost());
					cst.setDouble(5, _tmpCostSale.getSale());
					cst.setString(6, _tmpCostSale.getNotes());					
					cst.setBoolean(7, _tmpCostSale.isReviewed());
					cst.setBoolean(8, _tmpCostSale.isOtherCost());
					cst.setBoolean(9, _tmpCostSale.isSelectedToBlDoc());
					if(cst.executeUpdate() > 0){
						_tmpCostSale.setBlCostSaleId(cst.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blCostSales;
	}

	/**
	 * 
	 * @param blCostSale
	 * @throws Exception 
	 */
	public boolean deleteBlCostSale(BlCostSale blCostSale) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_cost_sale_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blCostSale.getBlCostSaleId());
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