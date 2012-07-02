package test.BussinessPartners;

import org.testng.annotations.Test;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.businessPartners.dao.ICourierAccountDao;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;

public class CourierAccountTestNG extends PruebaUnitaria{

	ICourierAccountDao courierAccountDao;
	
	public CourierAccountTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			courierAccountDao = (ICourierAccountDao) ConfiguracionPrueba
					.obtenerBean("courierAccountDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createCourierAccount() throws Exception {
		try {
			assertNotNull(courierAccountDao);

			CourierAccount courrierAccount = new CourierAccount();
			courrierAccount.setPartnerDeptInfo(4);
			courrierAccount.setAccountNumber("123456789");
			courrierAccount.setCourier(new MasterValue(30));
			courrierAccount.setMain(true);
			
			int _tmpReturn = courierAccountDao.createCourierAccount(courrierAccount);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updateCourierAccount() throws Exception {
		try {
			assertNotNull(courierAccountDao);

			CourierAccount courrierAccount = new CourierAccount();
			courrierAccount.setPartnerDeptInfo(4);
			courrierAccount.setCourierAccountId(1);
			courrierAccount.setAccountNumber("56789");
			courrierAccount.setCourier(new MasterValue(30));
			courrierAccount.setMain(false);

			boolean _tmpReturn = courierAccountDao.updateCourierAccount(courrierAccount);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
}
