package test.LODepartment.shipments;

import org.testng.annotations.Test;

import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlItemLogic;
import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class BlDetailTestNG extends PruebaUnitaria{

	private IBlItemLogic blItemLogic;

	public BlDetailTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			blItemLogic = (IBlItemLogic) ConfiguracionPrueba
					.obtenerBean("blItemLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void saveBlDetail() throws Exception {
		try {
			assertNotNull(blItemLogic);

			BlItem blItem = new BlItem();
			
			blItem.setBlId(4333);			
			blItem.setPieces(2);
			blItem.getType().setValueId(2);
			blItem.setItemLength(12);
			blItem.setItemWidth(12);
			blItem.setItemHeight(12);
			blItem.setItemWeight(12);
			blItem.setWhItemId(12);
			blItem.setPoNumber("avv01a11p");
			blItem.getInvoice().setInvoiceId(44112);
			blItem.setRemarks("un remark");
			blItem.setLocationId(12);
			blItem.getContainer().setContainerId(10);
		

			/*int _tmpReturn = blItemLogic.saveBlItem(blItem);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);*/
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updateBlDetails() throws Exception {
		try {
			assertNotNull(blItemLogic);

			BlItem blItem = new BlItem();
			
			blItem.setItemId(22182);
			blItem.setBlId(33);
			blItem.setPieces(2);
			blItem.getType().setValueId(2);
			blItem.setItemLength(12);
			blItem.setItemWidth(12);
			blItem.setItemHeight(12);
			blItem.setItemWeight(12);
			blItem.setWhItemId(12);
			blItem.setPoNumber("avv01a11p");
			blItem.getInvoice().setInvoiceId(123);
			blItem.setRemarks("un remark");
			blItem.setLocationId(12);
			blItem.getContainer().setContainerId(1);

			/*int _tmpReturn = blItemLogic.saveBlItem(blItem);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn >0);*/
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-borrar" })
	public void deleteBlDetail() throws Exception {
		try {
			assertNotNull(blItemLogic);
			BlItem blItem = new BlItem();			
			blItem.setItemId(22185);
			boolean _tmpReturn = blItemLogic.deleteBlItem(blItem);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

}
