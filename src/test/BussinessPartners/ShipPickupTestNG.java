package test.BussinessPartners;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.businessPartners.dao.IShipPickupDao;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;

public class ShipPickupTestNG extends PruebaUnitaria{
	
	IShipPickupDao shipPickupDao;
	
	public ShipPickupTestNG() {
		setUp();
	}
	
	protected void setUp() {
		try {
			shipPickupDao = (IShipPickupDao) ConfiguracionPrueba
					.obtenerBean("shipPickupDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(groups = { "grupo-crear" })
	public void createShipPickup() throws Exception {
		try {
			assertNotNull(shipPickupDao);
			
			ShipPickUp shipPickup = new ShipPickUp();
			shipPickup.setPartnerId(1);
			shipPickup.setName("DHL");
			shipPickup.setAddress(new Address(1));
			shipPickup.setEmail("loquesea@dhl.com");
			shipPickup.setNotes("esto ues una nota para shipPickup DHL");
			shipPickup.setNotifyParty("asduiofyhasdifu");
			shipPickup.setEnteredDate(new Date(1236548963));
			
			int _tmpReturn = shipPickupDao.createShipPickup(shipPickup);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn >0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updateShipPickup() throws Exception {
		try {
			assertNotNull(shipPickupDao);

			ShipPickUp shipPickup = new ShipPickUp();
			shipPickup.setShipPickUpId(1);
			shipPickup.setPartnerId(1);
			shipPickup.setName("FEDEX");
			shipPickup.setAddress(new Address(1));
			shipPickup.setEmail("loquesea@fedex.com");
			shipPickup.setNotes("esto ues una nota para shipPickup FEDEX");
			shipPickup.setNotifyParty("FEDEXFEDEXFEDEXFEDEX");
			shipPickup.setEnteredDate(new Date(1236548963));
			
			boolean _tmpReturn = shipPickupDao.updateShipPickup(shipPickup);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadShipPickUps() throws Exception {
		 try  {
	         assertNotNull(shipPickupDao);
	         
	         List<ShipPickUp> shipPickUps = null;
	         ShipPickUp shipPick = new ShipPickUp();
	         Partner partner = new Partner();
	         partner.setPartnerId(1);
	         shipPick.setPartnerId(partner.getPartnerId());
	         shipPickUps = shipPickupDao.filterByShipPickup(shipPick);
	         
	         for(ShipPickUp _tmpShipPickUp : shipPickUps){
	        	 System.out.println(">> name: "+_tmpShipPickUp.getName()
	        			 + " Direccion: " + _tmpShipPickUp.getAddress().getAddress()
	        			 + " Postal Code: " + _tmpShipPickUp.getAddress().getPostalCode()
	        			 + " ciudad: " + _tmpShipPickUp.getAddress().getCity().getName()
	        			 + " Departamento: " + _tmpShipPickUp.getAddress().getCity().getStateProvince().getValue1()
	        			 + " Pais: " + _tmpShipPickUp.getAddress().getCity().getCountry().getValue1()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
}
