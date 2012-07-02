package com.lotrading.softlot.LODepartment.shipments.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbOtherInvoiceDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbOtherInvoice;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbOtherInvoiceDaoImpl extends DAOTemplate implements IAwbOtherInvoiceDao {

	private Log log = LogFactory.getLog(AwbOtherInvoiceDaoImpl.class);
	
	public AwbOtherInvoiceDaoImpl(){

	}

	/**
	 * 
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public int createAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception{
		int createdId = 0;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_other_invoice_CREATE(?,?,?)}";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, awbOtherInvoice.getAwbId());
			cst.setInt(3, awbOtherInvoice.getInvoice().getInvoiceId());
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
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public boolean updateAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception{
		boolean updated = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call sp_awb_other_invoice_UPDATE(?,?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbOtherInvoice.getOtherInvoiceId());
			cst.setInt(2, awbOtherInvoice.getInvoice().getInvoiceId());
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
	 * @param awbOtherInvoice
	 * @throws Exception 
	 */
	public boolean deleteAwbOtherInvoice(AwbOtherInvoice awbOtherInvoice) throws Exception{
		boolean deleted = false;
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "{call lotradingdb.sp_awb_other_invoice_DELETE(?)}";
			cst = conn.prepareCall(sql);
			cst.setInt(1, awbOtherInvoice.getOtherInvoiceId());
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

	public List<AwbOtherInvoice> saveAwbOtherInvoice(List<AwbOtherInvoice> awbOtherInvoiceList) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		try {
			conn = getConnection();
			for(AwbOtherInvoice _tmpOtherInvoice : awbOtherInvoiceList){
				if(_tmpOtherInvoice.getOtherInvoiceId() <= 0 && !_tmpOtherInvoice.isEmpty()){
					String sql = "{call sp_awb_other_invoice_CREATE(?,?,?)}";
					cst = conn.prepareCall(sql);
					cst.registerOutParameter(1, Types.INTEGER);
					cst.setInt(2, _tmpOtherInvoice.getAwbId());
					cst.setInt(3, _tmpOtherInvoice.getInvoice().getInvoiceId());
					if(cst.executeUpdate() > 0){
						_tmpOtherInvoice.setOtherInvoiceId(cst.getInt(1));
					}else{
						log.error("A new OtherInvoice can't be created");
					}
				}else if(_tmpOtherInvoice.getOtherInvoiceId() > 0){
					String sql = "{call sp_awb_other_invoice_UPDATE(?,?)}";
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
		return awbOtherInvoiceList;
	}

}