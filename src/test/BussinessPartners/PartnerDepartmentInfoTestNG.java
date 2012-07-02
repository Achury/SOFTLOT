package test.BussinessPartners;

import org.testng.annotations.Test;

import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.logic.IPartnerDepartmentInfoLogic;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.util.base.Constants;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class PartnerDepartmentInfoTestNG extends PruebaUnitaria{

	IPartnerDepartmentInfoLogic partnerDepartmentInfoLogic;
	
	public PartnerDepartmentInfoTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			partnerDepartmentInfoLogic = (IPartnerDepartmentInfoLogic) ConfiguracionPrueba
					.obtenerBean("partnerDepartmentInfoLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createPartnerDeptInfo() throws Exception {
		try {
			assertNotNull(partnerDepartmentInfoLogic);
			PartnerDepartmentInfo partnerDepartmentInfo = new PartnerDepartmentInfo();
			partnerDepartmentInfo.setPartnerId(1);
			partnerDepartmentInfo.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
			partnerDepartmentInfo.setEmployeeRep(1);
			partnerDepartmentInfo.setNotes("Nota, nota, nota, nota");
			partnerDepartmentInfo.setMargin(0.5f);
			partnerDepartmentInfo.setMarkup(0.4f);
			
			boolean _tmpReturn = partnerDepartmentInfoLogic.saveDepartmentInfo(partnerDepartmentInfo);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updatePartnerDeptInfo() throws Exception {
		try {
			assertNotNull(partnerDepartmentInfoLogic);
			PartnerDepartmentInfo partnerDepartmentInfo = new PartnerDepartmentInfo();
			partnerDepartmentInfo.setPartnerDepartmentInfoId(15);
			partnerDepartmentInfo.setPartnerId(30);
			partnerDepartmentInfo.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
			partnerDepartmentInfo.setEmployeeRep(1);
			partnerDepartmentInfo.setNotes("NOTA de prueba");
			partnerDepartmentInfo.setMargin(0.4f);
			partnerDepartmentInfo.setMarkup(0.3f);
			
			boolean _tmpReturn = partnerDepartmentInfoLogic.saveDepartmentInfo(partnerDepartmentInfo);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}
