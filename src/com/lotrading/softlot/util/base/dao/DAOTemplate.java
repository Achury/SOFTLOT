package com.lotrading.softlot.util.base.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public class DAOTemplate {

	private DataSource dataSource;

	public DAOTemplate() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	protected Connection getConnection() {
		Connection con = DataSourceUtils.getConnection(getDataSource());
		return con;
	}
	
	protected void close(Connection con){
		 DataSourceUtils.releaseConnection(con, getDataSource());
	}
	
	public Date toDate(java.util.Date date){
		long t = date.getTime();
		Date dt = new java.sql.Date(t);
		return dt;
	}
	
	public Timestamp toTimeStamp(java.util.Date date){
		long t = date.getTime();
		Timestamp ts = new java.sql.Timestamp(t);
		return ts;
	}
}