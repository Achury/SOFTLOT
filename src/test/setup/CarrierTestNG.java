package test.setup;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.setup.dao.ICarrierDao;
import com.lotrading.softlot.setup.dao.ICityDao;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.City;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.logic.IcarrierLogic;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class CarrierTestNG extends PruebaUnitaria{
	
	IcarrierLogic carrierLogic;
	
	public CarrierTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			carrierLogic = (IcarrierLogic) ConfiguracionPrueba
					.obtenerBean("carrierLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createCarrier() throws Exception {
		try {
			assertNotNull(carrierLogic);

			Carrier carrier = new Carrier();
			carrier.setName("Carrier test");
			carrier.setCarrierType(new MasterValue(10));
			carrier.setIataCode("1234");
			carrier.setNotes("estas son unas notas de prueba \n Como dice el combo de las estrellas\n Que notaaa!!!!!");
			carrier.setEnteredDate(new Date(1000));

			boolean _tmpReturn = carrierLogic.saveCarrier(carrier);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updateCarrier() throws Exception {
		try {
			assertNotNull(carrierLogic);

			Carrier carrier = new Carrier();
			carrier.setCarrierId(2);
			carrier.setName("UPS CARRIER");
			carrier.setCarrierType(new MasterValue(3));
			carrier.setEnteredDate(new Date(1554));
			

			boolean _tmpReturn = carrierLogic.saveCarrier(carrier);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchCarriers() throws Exception {
		try {
			assertNotNull(carrierLogic);

			Carrier carrier = new Carrier();
			carrier.setName("");
			
			List<Carrier> carriers = null;	
			carriers = carrierLogic.searchCarrier(carrier);	
		
			// Asserts generales para la lista
			assertTrue(carriers  != null);
			assertTrue(carriers.size() > 0);
			
			for(Carrier _tmpCarrier : carriers ){
				System.out.println(_tmpCarrier.getName()+" -- ");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}
