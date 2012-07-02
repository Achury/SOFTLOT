package test.BussinessPartners;

import java.sql.Date;

import org.testng.annotations.Test;

import com.lotrading.softlot.businessPartners.dao.IAddressDao;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.logic.IPhoneLogic;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class PhoneTestNG extends PruebaUnitaria {

	IPhoneLogic phoneLogic;

	public PhoneTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			phoneLogic = (IPhoneLogic) ConfiguracionPrueba
					.obtenerBean("phoneLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createPhone() throws Exception {
		try {
			assertNotNull(phoneLogic);
			Phone phone = new Phone();
			phone.setType(new MasterValue(8));
			phone.setCountryCode("58");
			phone.setPhoneNumber("3041500");
			phone.setEnteredDate(new Date(1));

			int _tmpReturn = phoneLogic.savePhone(phone);
			System.out.println("nuevo es: " + _tmpReturn);			
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updatePhone() throws Exception {
		try {
			assertNotNull(phoneLogic);

			Phone phone = new Phone();
			phone.setPhoneId(5);
			phone.setType(new MasterValue(8));
			phone.setCountryCode("53");
			phone.setPhoneNumber("4440200");
			phone.setEnteredDate(new Date(1235));
			
			int _tmpReturn = phoneLogic.savePhone(phone);
			System.out.println( "nuevo es: " + _tmpReturn);			
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}
