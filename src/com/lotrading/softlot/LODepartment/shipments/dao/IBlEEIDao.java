package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlEEI;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 25-Apr-2012 08:00:00 AM
 */

public interface IBlEEIDao {

	public List<BlEEI> saveBlEEI(List<BlEEI> blEEIList ) throws Exception;
	
	public boolean deleteBlEEI(BlEEI blEEI) throws Exception;
}
