package test.LODepartment.shipments;

import org.testng.annotations.Test;
import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlFreightInvoiceLogic;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class BlFreightInvoiceTestNG extends PruebaUnitaria{

	private IBlFreightInvoiceLogic blFreightInvoiceLogic;

	public BlFreightInvoiceTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			blFreightInvoiceLogic = (IBlFreightInvoiceLogic) ConfiguracionPrueba
					.obtenerBean("blFreightInvoiceLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createFreightInvoice() throws Exception {
		try {
			assertNotNull(blFreightInvoiceLogic);

			BlFreightInvoice blFreightInvoice = new BlFreightInvoice();
			
			blFreightInvoice.setBlId(4333);			
			blFreightInvoice.setInvoiceId(8554);		
		

			int _tmpReturn = blFreightInvoiceLogic.saveFreightInvoice(blFreightInvoice);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	
	@Test(groups = { "grupo-actualizar" })
	public void updateFreightInvoice() throws Exception {
		try {
			assertNotNull(blFreightInvoiceLogic);

			BlFreightInvoice blFreightInvoice = new BlFreightInvoice();			
			
			blFreightInvoice.setFreightInvoiceId(2168);
			blFreightInvoice.setInvoiceId(8555);		

			int _tmpReturn = blFreightInvoiceLogic.saveFreightInvoice(blFreightInvoice);
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
			assertNotNull(blFreightInvoiceLogic);
			BlFreightInvoice blFreightInvoice = new BlFreightInvoice();			
			blFreightInvoice.setFreightInvoiceId(2168);
			boolean _tmpReturn = blFreightInvoiceLogic.deleteFreightInvoice(blFreightInvoice);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	} 

}
