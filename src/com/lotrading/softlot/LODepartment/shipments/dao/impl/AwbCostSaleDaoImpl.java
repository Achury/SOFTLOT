package com.lotrading.softlot.LODepartment.shipments.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbCostSaleDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbCostSale;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */

public class AwbCostSaleDaoImpl extends DAOTemplate implements IAwbCostSaleDao {
	
	private Log log = LogFactory.getLog(AwbCostSaleDaoImpl.class);

	public int createAwbCostSale(AwbCostSale awbCostSale) throws Exception {
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_cost_sale_CREATE(?,?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, awbCostSale.getAwbId());
			cst.setInt(3, awbCostSale.getChargeType().getValueId());
			cst.setDouble(4, awbCostSale.getCost());
			cst.setDouble(5, awbCostSale.getSale());
			cst.setBoolean(6, awbCostSale.isOtherCost());
			cst.setBoolean(7, awbCostSale.isReviewed());
			cst.setString(8, awbCostSale.getNotes());
			cst.setBoolean(9, awbCostSale.isSelectedToAwbDoc());
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

	public boolean updateAwbCostSale(AwbCostSale awbCostSale) throws Exception {
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_cost_sale_UPDATE(?,?,?,?,?,?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbCostSale.getAwbCostsaleId());
			cst.setInt(2, awbCostSale.getChargeType().getValueId());
			cst.setDouble(3, awbCostSale.getCost());
			cst.setDouble(4, awbCostSale.getSale());
			cst.setBoolean(5, awbCostSale.isOtherCost());
			cst.setBoolean(6, awbCostSale.isReviewed());
			cst.setString(7, awbCostSale.getNotes());
			cst.setBoolean(8, awbCostSale.isSelectedToAwbDoc());
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

	public List<AwbCostSale> saveAwbCostSale(List<AwbCostSale> awbCostSales) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbCostSale _tmpCostSale: awbCostSales){
				if(_tmpCostSale.getAwbCostsaleId() <= 0 ){
					if((_tmpCostSale.isOtherCost() && !_tmpCostSale.isEmpty()) || (!_tmpCostSale.isOtherCost())){					
						String sql = "{call sp_awb_cost_sale_CREATE(?,?,?,?,?,?,?,?,?)}";
						cst = conn.prepareCall(sql);
						cst.registerOutParameter(1, Types.INTEGER);
						cst.setInt(2, _tmpCostSale.getAwbId());
						cst.setInt(3, _tmpCostSale.getChargeType().getValueId());
						cst.setDouble(4, _tmpCostSale.getCost());
						cst.setDouble(5, _tmpCostSale.getSale());
						cst.setBoolean(6, _tmpCostSale.isOtherCost());
						cst.setBoolean(7, _tmpCostSale.isReviewed());
						cst.setString(8, _tmpCostSale.getNotes());
						cst.setBoolean(9, _tmpCostSale.isSelectedToAwbDoc());
						if(cst.executeUpdate() > 0){
							_tmpCostSale.setAwbCostsaleId(cst.getInt(1));
						}
					}
				}else if(_tmpCostSale.getAwbCostsaleId() > 0){
					String sql = "{call sp_awb_cost_sale_UPDATE(?,?,?,?,?,?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpCostSale.getAwbCostsaleId());
					cst.setInt(2, _tmpCostSale.getChargeType().getValueId());
					cst.setDouble(3, _tmpCostSale.getCost());
					cst.setDouble(4, _tmpCostSale.getSale());
					cst.setBoolean(5, _tmpCostSale.isOtherCost());
					cst.setBoolean(6, _tmpCostSale.isReviewed());
					cst.setString(7, _tmpCostSale.getNotes());
					cst.setBoolean(8, _tmpCostSale.isSelectedToAwbDoc());
					cst.executeUpdate();			
				}	
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbCostSales;
	}

	public boolean deleteCostSale(AwbCostSale awbCostSale) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_cost_sale_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbCostSale.getAwbCostsaleId());
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
