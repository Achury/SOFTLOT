package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.dao.IBlFreightInvoiceDao;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlFreightInvoiceDaoImpl extends DAOTemplate implements IBlFreightInvoiceDao {

	private Log log = LogFactory.getLog(BlFreightInvoiceDaoImpl.class);
	
	public BlFreightInvoiceDaoImpl(){

	}
	
	/**
	 * 
	 * @param blFreightInvoice
	 * @throws Exception 
	 */
	public int createBlFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_freight_invoice_CREATE(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blFreightInvoice.getBlId());
			cst.setInt(3, blFreightInvoice.getInvoice().getInvoiceId());		
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
	 * @param blFreightInvoice
	 * @throws Exception 
	 */
	public boolean updateBlFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_freight_invoice_UPDATE(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blFreightInvoice.getFreightInvoiceId());
			cst.setInt(2, blFreightInvoice.getInvoice().getInvoiceId());
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
	 * @param blFreightInvoice
	 * @throws Exception 
	 */
	public boolean deleteBlFreightInvoice(BlFreightInvoice blFreightInvoice) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_freight_invoice_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blFreightInvoice.getFreightInvoiceId());
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
	
	public List<BlFreightInvoice> saveBlFreightInvoices(List<BlFreightInvoice> blFreightInvoiceList) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlFreightInvoice _tmpFreihtInvoice : blFreightInvoiceList){
				if(_tmpFreihtInvoice.getFreightInvoiceId() <= 0 && !_tmpFreihtInvoice.isEmpty()){
					String sql = "{call sp_bl_freight_invoice_CREATE(?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpFreihtInvoice.getBlId());
					cst.setInt(3, _tmpFreihtInvoice.getInvoice().getInvoiceId());		
					if(cst.executeUpdate() > 0){
						_tmpFreihtInvoice.setFreightInvoiceId(cst.getInt(1));
					}else{
						log.error("A new FreightInvoice can't be created");
					}
				}else if(_tmpFreihtInvoice.getFreightInvoiceId() > 0){
					String sql = "{call sp_bl_freight_invoice_UPDATE(?,?)}";
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
		return blFreightInvoiceList;
	}


}