package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlOtherInvoiceDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlOtherInvoiceDaoImpl extends DAOTemplate implements IBlOtherInvoiceDao {

	private Log log = LogFactory.getLog(BlOtherInvoiceDaoImpl.class);
	
	public BlOtherInvoiceDaoImpl(){

	}

	/**
	 * 
	 * @param blOtherInvoice
	 * @throws Exception 
	 */
	public int createBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_other_invoice_CREATE(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, blOtherInvoice.getBlId());
			cst.setInt(3, blOtherInvoice.getInvoice().getInvoiceId());
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
	 * @param blOtherInvoice
	 * @throws Exception 
	 */
	public boolean updateBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_bl_other_invoice_UPDATE(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blOtherInvoice.getOtherInvoiceId());
			System.out.println( blOtherInvoice.getOtherInvoiceId());
			cst.setInt(2, blOtherInvoice.getInvoice().getInvoiceId());
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
	 * @param blOtherInvoice
	 * @throws Exception 
	 */
	public boolean deleteBlOtherInvoice(BlOtherInvoice blOtherInvoice) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_bl_other_invoice_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, blOtherInvoice.getOtherInvoiceId());
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
	
	public List<BlOtherInvoice> saveBlOtherInvoices(List<BlOtherInvoice> blOtherInvoiceList) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(BlOtherInvoice _tmpOtherInvoice : blOtherInvoiceList){
				if(_tmpOtherInvoice.getOtherInvoiceId() <= 0 && !_tmpOtherInvoice.isEmpty()){
					String sql = "{call sp_bl_other_invoice_CREATE(?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpOtherInvoice.getBlId());
					cst.setInt(3, _tmpOtherInvoice.getInvoice().getInvoiceId());
					if(cst.executeUpdate() > 0){
						_tmpOtherInvoice.setOtherInvoiceId(cst.getInt(1));
					}else{
						log.error("A new OtherInvoice can't be created");
					}
				}else if(_tmpOtherInvoice.getOtherInvoiceId() > 0){
					String sql = "{call sp_bl_other_invoice_UPDATE(?,?)}";
					cst = conn.prepareCall(sql);
					cst.setInt(1, _tmpOtherInvoice.getOtherInvoiceId());
					cst.setInt(2, _tmpOtherInvoice.getInvoice().getInvoiceId());
					if(cst.executeUpdate() <= 0){
						log.error("A OtherInvoice can't be updated");
					}			
				}
			}			
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return blOtherInvoiceList;
	}

}