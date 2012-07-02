package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.dao.IAwbFreightInvoiceDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbFreightInvoiceDaoImpl extends DAOTemplate implements IAwbFreightInvoiceDao {

	private Log log = LogFactory.getLog(AwbFreightInvoiceDaoImpl.class);
	
	public AwbFreightInvoiceDaoImpl(){

	}
	
	/**
	 * 
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public int createAwbFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_freight_invoice_CREATE(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, awbFreightInvoice.getAwbId());
			cst.setInt(3, awbFreightInvoice.getInvoice().getInvoiceId());		
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
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public boolean updateAwbFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_freight_invoice_UPDATE(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbFreightInvoice.getFreightInvoiceId());
			cst.setInt(2, awbFreightInvoice.getInvoice().getInvoiceId());
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
	 * @param awbFreightInvoice
	 * @throws Exception 
	 */
	public boolean deleteAwbFreightInvoice(AwbFreightInvoice awbFreightInvoice) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_freight_invoice_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbFreightInvoice.getFreightInvoiceId());
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

	public List<AwbFreightInvoice> saveAwbFreightInvoice(List<AwbFreightInvoice> awbFreightInvoiceList) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbFreightInvoice _tmpFreihtInvoice : awbFreightInvoiceList){
				if(_tmpFreihtInvoice.getFreightInvoiceId() <= 0 && !_tmpFreihtInvoice.isEmpty()){
					String sql = "{call sp_awb_freight_invoice_CREATE(?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpFreihtInvoice.getAwbId());
					cst.setInt(3, _tmpFreihtInvoice.getInvoice().getInvoiceId());		
					if(cst.executeUpdate() > 0){
						_tmpFreihtInvoice.setFreightInvoiceId(cst.getInt(1));
					}else{
						log.error("A new FreightInvoice can't be created");
					}
				}else if(_tmpFreihtInvoice.getFreightInvoiceId() > 0){
					String sql = "{call sp_awb_freight_invoice_UPDATE(?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpFreihtInvoice.getFreightInvoiceId());
					cst.setInt(2, _tmpFreihtInvoice.getInvoice().getInvoiceId());
					if(cst.executeUpdate() <= 0){
						log.error("A FreightInvoice can't be updated");
					}
				}
			}
			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return awbFreightInvoiceList;
	}

}