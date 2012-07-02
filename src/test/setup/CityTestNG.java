package test.setup;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import com.lotrading.softlot.setup.dao.ICityDao;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class CityTestNG extends PruebaUnitaria {
	
	ICityDao cityDao;

	public CityTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			cityDao = (ICityDao) ConfiguracionPrueba
					.obtenerBean("cityDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createCity() throws Exception {
		try {
			assertNotNull(cityDao);

			City city = new City();
			city.setName("Medellin");
			city.setEnteredDate(new Date(10000));
			city.setCountry(new MasterValue(4));
			city.setStateProvince(new MasterValue(5));
			city.setEnteredDate(new Date(10000));

			boolean _tmpReturn = cityDao.createCity(city);
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
			assertNotNull(cityDao);

			City city = new City();
			city.setCityId(1);
			city.setName("Medellín");
			city.setEnteredDate(new Date(10000));
			city.setCountry(new MasterValue(4));
			city.setStateProvince(new MasterValue(5));
			city.setEnteredDate(new Date(10000));
			city.setDisabledDate(new Date(10000));
			

			boolean _tmpReturn = cityDao.updateCity(city);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-obtener" })
	public void searchCities() throws Exception {
		try {
			assertNotNull(cityDao);

			City city = new City();
			city.setName("Medellin");
			
			List<City> cities = null;	
			cities = cityDao.searchCity(city);			
		
			// Asserts generales para la lista
			assertTrue(cities  != null);
			assertTrue(cities.size() > 0);
			
			for(City _tmpCity : cities ){
				System.out.println(_tmpCity.getName()+" -- "+ _tmpCity.getCountry().getValue1()+" -- "+  _tmpCity.getStateProvince().getValue1());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

}
