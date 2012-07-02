package test.setup;

import java.util.Date;
import java.util.List;
import org.testng.annotations.Test;
import com.lotrading.softlot.setup.dao.IMasterValuesDao;
import com.lotrading.softlot.setup.entity.MasterValue;
import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class MasterValueTestNG extends PruebaUnitaria {

	IMasterValuesDao masterValueDao;

	public MasterValueTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			masterValueDao = (IMasterValuesDao) ConfiguracionPrueba
					.obtenerBean("masterValuesDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createMasterValue() throws Exception {
		try {
			assertNotNull(masterValueDao);

			MasterValue masterValue = new MasterValue();
			masterValue.setMasterId(4);
			masterValue.setValue1("Chile");
			masterValue.setValue2("ChL");
			masterValue.setValue3("57");
			masterValue.setEnteredDate(new Date(99999999));

			boolean _tmpReturn = masterValueDao.createMasterValue(masterValue);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateMasterValue() throws Exception {
		try {
			assertNotNull(masterValueDao);

			MasterValue masterValue = new MasterValue();
			masterValue.setValueId(6);
			masterValue.setMasterId(4);
			masterValue.setValue1("COL");
			masterValue.setValue2("57");
			masterValue.setValue3("Colombia");
			masterValue.setEnteredDate(new Date(125));
			masterValue.setDisabledDate(new Date(125));
			boolean _tmpReturn = masterValueDao.updateMasterValue(masterValue);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchMasterValue() throws Exception {
		try {
			assertNotNull(masterValueDao);

			MasterValue masterValue = new MasterValue();
			masterValue.setMasterId(4);
			
			List<MasterValue> masterValues = null;

			masterValues = masterValueDao.searchMasterValue(masterValue);

			// Asserts generales para la lista
			assertTrue(masterValues != null);
			assertTrue(masterValues.size() > 0);

			for(MasterValue _tmpMasterValue : masterValues){
				System.out.println(_tmpMasterValue.getMasterId()+" -- "+ _tmpMasterValue.getValue1());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

}
