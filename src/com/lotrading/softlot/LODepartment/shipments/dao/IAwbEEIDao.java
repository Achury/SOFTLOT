package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbEEI;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */

public interface IAwbEEIDao {

	public List<AwbEEI> saveAwbEEI(List<AwbEEI> awbEEIList ) throws Exception;
	
	public boolean deleteAwbEEI(AwbEEI awbEEI) throws Exception;
}
