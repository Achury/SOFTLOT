package test.BussinessPartners;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;
import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.logic.IPartnerContactLogic;
import com.lotrading.softlot.security.entity.Employee;

public class PartnerContactTestNG extends PruebaUnitaria {

	private IPartnerContactLogic partnerContactLogic;

	public PartnerContactTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			partnerContactLogic = (IPartnerContactLogic) ConfiguracionPrueba
					.obtenerBean("partnerContactLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createPartnerContact() throws Exception {
		try {
			assertNotNull(partnerContactLogic);

			PartnerContact partnerContact = new PartnerContact();
			partnerContact.setPartnerDeptInfoId(1);
			partnerContact.setName("Julio Cesar");
			partnerContact.setTitle("Presidente");

			Address address = new Address();
			address.setAddressId(1);

			partnerContact.setAddress(address);

			Phone phone = new Phone();
			phone.setPhoneId(2);

			partnerContact.setEmail("ns@tata.com");
			partnerContact.setNotes("La nota para el Presidente");
			partnerContact.setMain(true);
			partnerContact.setEmployeeCreator(1);
			partnerContact.setEnteredDate(new Date(1000));

			int _tmpReturn = partnerContactLogic
					.savePartnerContact(partnerContact);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-actualizar" })
	public void updatePartnerContact() throws Exception {
		try {
			assertNotNull(partnerContactLogic);

			PartnerContact partnerContact = new PartnerContact();
			partnerContact.setContactId(3);			
			partnerContact.setPartnerDeptInfoId(1);
			partnerContact.setName("Marlon Pineda");
			partnerContact.setTitle("Asistente de sistemas");
			Address address = new Address();
			address.setAddressId(1);

			partnerContact.setAddress(address);

			partnerContact.setEmail("marlon.pineda@lotrading.com");
			partnerContact.setNotes("Una nota para MARLON");		
			partnerContact.setMain(false);
		
			partnerContact.setEmployeeCreator(1);
			
			partnerContact.setEnteredDate(new Date());
			partnerContact.setDisableDate(new Date());

			int _tmpReturn = partnerContactLogic.savePartnerContact(partnerContact);			
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadContactPhones() throws Exception {
		 try  {
	         assertNotNull(partnerContactLogic);
	         
	         List<Phone> contactPhones = null;
	         PartnerContact partnerContact = new PartnerContact();
	         partnerContact.setContactId(1);
	         
	         contactPhones = partnerContactLogic.loadContactPhones(partnerContact);
	         
	         for(Phone _tmpContactPhone : contactPhones){
	        	 System.out.println(">> tipo telefono: "+_tmpContactPhone.getType().getValue1()
	        			 + " tipo telefono: " + _tmpContactPhone.getType().getValue2()
	        			 + " country Code: " + _tmpContactPhone.getCountryCode()
	        			 + " area Code: " + _tmpContactPhone.getAreaCode()
	        			 + " Phone Number: " + _tmpContactPhone.getPhoneNumber()
	        			 + " Phone Extension: " + _tmpContactPhone.getPhoneExtension()
	        			 + " Entered: " + _tmpContactPhone.getEnteredDate()
	        			 + " Disabled: " + _tmpContactPhone.getDisabledDate()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	

}
