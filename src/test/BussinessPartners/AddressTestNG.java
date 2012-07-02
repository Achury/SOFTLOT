package test.BussinessPartners;

import org.testng.annotations.Test;

import com.lotrading.softlot.businessPartners.dao.IAddressDao;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.setup.entity.City;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class AddressTestNG extends PruebaUnitaria {

	IAddressDao addressDao;
	public AddressTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			addressDao = (IAddressDao) ConfiguracionPrueba
					.obtenerBean("addressDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createAddress() throws Exception {
		try {
			assertNotNull(addressDao);

			Address address = new Address();
			address.setAddress("carrera 20 N° 48B - 31. Barrio Centro");
			address.setCity(new City());
			address.setPostalCode("1235");
			
			int _tmpReturn = addressDao.createAddress(address);
			System.out.println(_tmpReturn);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updateAddress() throws Exception {
		try {
			assertNotNull(addressDao);

			Address address = new Address();
			address.setAddress("carrera 12 N° 43B - 31. Barrio Manila");
			address.setAddressId(3);
			address.setCity(new City(2));
			address.setPostalCode("000000");

			boolean _tmpReturn = addressDao.updateAddress(address);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}
