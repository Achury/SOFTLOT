package test.setup;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.security.dao.IEmployeeDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.dao.IMasterDao;
import com.lotrading.softlot.setup.entity.Master;
import com.lotrading.softlot.setup.logic.IMasterLogic;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class MasterTestNG extends PruebaUnitaria {

	IMasterLogic masterLogic;

	public MasterTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			masterLogic = (IMasterLogic) ConfiguracionPrueba
					.obtenerBean("masterLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createEmployee() throws Exception {
		try {
			assertNotNull(masterLogic);

			Master master = new Master();
			master.setName("Master2 prueba2");
			master.setDescription("descript Prueba");
			master.setEnteredDate(new Date(99999999));
			master.setFormat1("c");

			boolean _tmpReturn = masterLogic.saveMaster(master);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateEmployee() throws Exception {
		try {
			assertNotNull(masterLogic);

			Master master = new Master();
			master.setName("MasterPRUEBA");
			master.setMasterId(3);
			master.setDescription("DESCRIPT TEST");
			master.setFormat1("C");
			master.setFormat2("C");
			master.setEnteredDate(new Date(125));

			boolean _tmpReturn = masterLogic.saveMaster(master);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchMasters() throws Exception {
		try {
			assertNotNull(masterLogic);

			Master master = new Master();
			master.setName("pru");
			
			List<Master> masters = null;

			masters = masterLogic.searchMaster(master);

			// Asserts generales para la lista
			assertTrue(masters != null);
			assertTrue(masters.size() > 0);

			for(Master _tmpMaster : masters){
				System.out.println(_tmpMaster.getName()+" -- "+ _tmpMaster.getDescription());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

}
