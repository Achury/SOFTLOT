package test.LODepartment.shipments;

import org.testng.annotations.Test;

import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;

import com.lotrading.softlot.LODepartment.shipments.logic.IBlInlandCSLogic;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class BlInlandCSTestNG extends PruebaUnitaria{

	private IBlInlandCSLogic blInlandCSLogic;

	public BlInlandCSTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			blInlandCSLogic = (IBlInlandCSLogic) ConfiguracionPrueba
					.obtenerBean("blInlandCSLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createBlInlandCS() throws Exception {
		try {
			assertNotNull(blInlandCSLogic);

			BlInlandCS blInlandCS = new BlInlandCS();
			
			blInlandCS.setBlId(4333);
			blInlandCS.setCost(120.2);
			blInlandCS.setPoNumber("abv0144121");
			blInlandCS.setSale(456);
			blInlandCS.setTrackingNumber("sdfkjhfg4");
			blInlandCS.getTruckCompany().setValueId(1);
			
		

			int _tmpReturn = blInlandCSLogic.saveBlInlandCS(blInlandCS);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	
	@Test(groups = { "grupo-actualizar" })
	public void updateBlInlandCS() throws Exception {
		try {
			assertNotNull(blInlandCSLogic);

			BlInlandCS blInlandCS = new BlInlandCS();			
			
			blInlandCS.setBlId(4333);
			blInlandCS.setInlandCSId(2631);
			blInlandCS.setCost(240.4);
			blInlandCS.setPoNumber("cer0821");
			blInlandCS.setSale(789);
			blInlandCS.setTrackingNumber("mak879");
			blInlandCS.getTruckCompany().setValueId(2);		

			int _tmpReturn = blInlandCSLogic.saveBlInlandCS(blInlandCS);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	
	@Test(groups = { "grupo-borrar" })
	public void deleteBlInlandCS() throws Exception {
		try {
			assertNotNull(blInlandCSLogic);
			BlInlandCS blInlandCS = new BlInlandCS();
			
			blInlandCS.setInlandCSId(2632);
			boolean _tmpReturn = blInlandCSLogic.deleteBlInlandCS(blInlandCS);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	} 

}
