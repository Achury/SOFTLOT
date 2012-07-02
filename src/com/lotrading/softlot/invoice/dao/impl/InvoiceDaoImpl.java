package com.lotrading.softlot.invoice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.invoice.dao.IInvoiceDao;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public class InvoiceDaoImpl extends DAOTemplate implements IInvoiceDao {

	private Log log = LogFactory.getLog(InvoiceDaoImpl.class);
	
	public Invoice loadInvoice(Invoice invoice) throws Exception {
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;		
			sql = "{call lotradingdb.sp_invoice_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, invoice.getInvoiceNumber());
			rs = cst.executeQuery();
			if (rs.next()) {
				invoice.setInvoiceNumber(rs.getString(1));
				invoice.setInvoiceId(rs.getInt(2));
				invoice.getClient().setPartnerId(rs.getInt(3));
				invoice.setBillingAddress(rs.getString(4));
				invoice.setShippingAddress(rs.getString(5));
				invoice.setOrder(rs.getString(6));
				invoice.setPaymentTermId(rs.getInt(7));
				//invoice.setIncoterm
				invoice.setWay(rs.getString(9));
				invoice.setAwb_bl(rs.getString(10));
				invoice.setOtherReferences(rs.getString(11));
				invoice.setSubtotal(rs.getDouble(12));
				invoice.setTax(rs.getDouble(13));
				invoice.setInlandFreight(rs.getDouble(14));
				invoice.setOtherCharges(rs.getDouble(15));
				invoice.setTotal(rs.getDouble(16));
				invoice.setPoNumberId(rs.getInt(17));
				invoice.setAwbNumber(rs.getString(18));
				invoice.setBlNumber(rs.getString(19));
				invoice.setStatus(rs.getInt(20));
				invoice.setDate(rs.getDate(21));
				invoice.setIncotermCity(rs.getInt(22));
				invoice.setShipToAddressId(rs.getInt(23));
				invoice.setDescription(rs.getString(24));
				invoice.setTotalCost(rs.getDouble(25));
				invoice.setEmployeeId(rs.getInt(26));
				invoice.setInlandCost(rs.getDouble(27));
				invoice.setMargin(rs.getInt(28));
				invoice.setNotes(rs.getString(29));
				invoice.setReviewed(rs.getBoolean(30));
				invoice.setGroupId(rs.getInt(31));
				invoice.setEnsurance(rs.getDouble(32));
				invoice.setOtherCost(rs.getDouble(33));
				invoice.setRemarks(rs.getString(34));
				invoice.getClient().setName(rs.getString(35));
			}
			rs.close();
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		} finally {
			close(conn);
		}
		return invoice;
	}

	public List<Invoice> loadInvoicesList(Invoice invoice) throws Exception {
		List<Invoice> invoicesList =  null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql;		
			sql = "{call lotradingdb.sp_invoice_LOAD(?)}";
			cst = conn.prepareCall(sql);
			cst.setString(1, invoice.getInvoiceNumber());
			rs = cst.executeQuery();
			if (rs.next()) {
				invoicesList = new ArrayList<Invoice>();
				do{
					Invoice _tmpInvoice = new Invoice();
					_tmpInvoice.setInvoiceNumber(rs.getString(1));
					_tmpInvoice.setInvoiceId(rs.getInt(2));
					_tmpInvoice.setGroupId(rs.getInt(31));
					invoicesList.add(_tmpInvoice);
				}while(rs.next());
			}
			rs.close();
		} catch (Exception e) {
				log.error("An Exception has been thrown:" + e);
				throw e;
		} finally {
			close(conn);
		}
		return invoicesList;
	}
	
	

}
