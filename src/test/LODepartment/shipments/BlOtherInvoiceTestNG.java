package test.LODepartment.shipments;

import org.testng.annotations.Test;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlOtherInvoiceLogic;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class BlOtherInvoiceTestNG extends PruebaUnitaria{

	private IBlOtherInvoiceLogic blOtherInvoiceLogic;

	public BlOtherInvoiceTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			blOtherInvoiceLogic = (IBlOtherInvoiceLogic) ConfiguracionPrueba
					.obtenerBean("blOtherInvoiceLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createOtherInvoice() throws Exception {
		try {
			assertNotNull(blOtherInvoiceLogic);

			BlOtherInvoice blOtherInvoice = new BlOtherInvoice();
			
			blOtherInvoice.setBlId(4333);			
			blOtherInvoice.setInvoiceId(8554);		
		

			int _tmpReturn = blOtherInvoiceLogic.saveBlOtherInvoice(blOtherInvoice);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	
	@Test(groups = { "grupo-actualizar" })
	public void updateOtherInvoice() throws Exception {
		try {
			assertNotNull(blOtherInvoiceLogic);			

			BlOtherInvoice blOtherInvoice = new BlOtherInvoice();			
			
			blOtherInvoice.setOtherInvoiceId(171);
			blOtherInvoice.setBlId(4333);
			blOtherInvoice.setInvoiceId(8555);		

			int _tmpReturn = blOtherInvoiceLogic.saveBlOtherInvoice(blOtherInvoice);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn >0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-borrar" })
	public void deleteBlDetail() throws Exception {
		try {
			assertNotNull(blOtherInvoiceLogic);
			BlOtherInvoice blOtherInvoice = new BlOtherInvoice();			
			blOtherInvoice.setOtherInvoiceId(172);
			boolean _tmpReturn = blOtherInvoiceLogic.deleteBlOtherInvoice(blOtherInvoice);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	} 

}
