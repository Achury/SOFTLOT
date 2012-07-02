package test.BussinessPartners;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;
import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;
import com.lotrading.softlot.businessPartners.logic.IPartnerLogic;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.setup.entity.MasterValue;

public class PartnerTestNG extends PruebaUnitaria {

	private IPartnerLogic partnerLogic;

	public PartnerTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			partnerLogic = (IPartnerLogic) ConfiguracionPrueba
					.obtenerBean("partnerLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createPartner() throws Exception {
		try {
			assertNotNull(partnerLogic);

			Partner partner = new Partner();
			partner.setName("SLI");
			partner.setCode("SLI");

			Address address = new Address();
			address.setAddressId(1);

			partner.setAddress(address);

			Phone phone = new Phone();
			phone.setPhoneId(2);

			partner.setPhone(phone);
			partner.setFax(phone);
			partner.setWebsite("www.lotrading.com");

			MasterValue status = new MasterValue();
			status.setValueId(14);

			partner.setStatus(status);

			MasterValue payment = new MasterValue();
			payment.setValueId(16);

			partner.setPaymentTerm(payment);
			partner.setNotes("Una nota para SLI");
			partner.setInvoiceNotes("Invoice note SLI");
			partner.setClient(true);
			partner.setSupplier(true);
			partner.setTaxId("900208382-8");

			MasterValue language = new MasterValue();
			language.setValueId(17);

			partner.setLanguage(language);

			MasterValue truckCompany = new MasterValue();
			truckCompany.setValueId(18);

			partner.setTruckCompany(truckCompany);
			partner.setInformFinalDest(true);			

			partner.setEmployeeCreator(new Employee(1));
			partner.setEmployeeUpdate(new Employee(1));
			partner.setEnteredDate(new Date(1000));
			partner.setUpdatedDate(new Date(1000));

			int _tmpReturn = partnerLogic.savePartner(partner);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updatePartner() throws Exception {
		try {
			assertNotNull(partnerLogic);

			Partner partner = new Partner();
			partner.setPartnerId(2);
			partner.setName("LOTRADING");
			partner.setCode("LOT");

			Address address = new Address();
			address.setAddressId(1);

			partner.setAddress(address);

			Phone phone = new Phone();
			phone.setPhoneId(2);

			partner.setPhone(phone);
			partner.setFax(phone);
			partner.setWebsite("www.lotrading.com");

			MasterValue status = new MasterValue();
			status.setValueId(14);

			partner.setStatus(status);

			MasterValue payment = new MasterValue();
			payment.setValueId(16);

			partner.setPaymentTerm(payment);
			partner.setNotes("Una nota para LOT");
			partner.setInvoiceNotes("Invoice note LOT");
			partner.setClient(false);
			partner.setSupplier(false);
			partner.setTaxId("10800");

			MasterValue language = new MasterValue();
			language.setValueId(17);

			partner.setLanguage(language);

			MasterValue truckCompany = new MasterValue();
			truckCompany.setValueId(18);

			partner.setTruckCompany(truckCompany);
			partner.setInformFinalDest(true);

			
			partner.setEmployeeCreator(new Employee(1));
			partner.setEmployeeUpdate(new Employee(1));
			partner.setEnteredDate(new Date(1000));
			partner.setUpdatedDate(new Date(1000));

			int _tmpReturn = partnerLogic.savePartner(partner);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn >0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateEmployeeRoles() throws Exception {

	}

	@Test(groups = { "grupo-obtener" })
	public void searchPartners() throws Exception {
		try {
			assertNotNull(partnerLogic);

			Partner partner = new Partner();

			partner.setName("a");

			MasterValue status = new MasterValue();
			status.setValueId(14);
			partner.setStatus(status);

			List<Partner> partners = null;
			partners = partnerLogic.searchPartner(partner);

			// Asserts generales para la lista
			assertTrue(partners != null);
			assertTrue(partners.size() > 0);

			for (Partner _tmpPartner : partners) {
				System.out.println(_tmpPartner.getName()
						+ " -- "
						+ _tmpPartner.getCode()
						+ " -- "
						+ _tmpPartner.getPhone().getPhoneNumber()
						+ " -- "
						+ _tmpPartner.getFax().getPhoneNumber()
						+ " -- "
						+ _tmpPartner.getAddress().getCity().getName()
						+ " -- "
						+ _tmpPartner.getAddress().getCity().getStateProvince()
								.getValue1()
						+ " -- "
						+ _tmpPartner.getAddress().getCity().getCountry()
								.getValue1() + " -- "
						+ _tmpPartner.getAddress().getAddress());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void loadPartnertContacts() throws Exception {
		 try  {
	         assertNotNull(partnerLogic);
	         
	         List<PartnerContact> partnerContacts = null;
	         Partner partner = new Partner();
	         partner.setPartnerId(1);
	         partner.setDepartmentLotId(20);
	         
	         partnerContacts = partnerLogic.loadContacts(partner);
	         
	         for(PartnerContact _tmpPartnerContact : partnerContacts){
	        	 System.out.println(">> name: "+_tmpPartnerContact.getName()
	        			 + " tittle: " + _tmpPartnerContact.getTitle()
	        			 + " mail: " + _tmpPartnerContact.getEmail()
	        			 + " notes: " + _tmpPartnerContact.getNotes()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadCourierAccounts() throws Exception {
		 try  {
	         assertNotNull(partnerLogic);
	         
	         List<CourierAccount> courierAccounts = null;
	         Partner partner = new Partner();
	         partner.setPartnerId(1);
	         partner.setDepartmentLotId(20);
	         
	         courierAccounts = partnerLogic.loadCourierAccount(partner);
	         
	         for(CourierAccount _tmpCourierAccount : courierAccounts){
	        	 System.out.println(">> numero cta: "+_tmpCourierAccount.getAccountNumber()
	        			 + " depto: " + _tmpCourierAccount.getPartnerDeptInfo()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadDepartmentInfo() throws Exception {
		 try  {
	         assertNotNull(partnerLogic);
	         
	         PartnerDepartmentInfo partnerDepartmentInfo = null;
	         Partner partner = new Partner();
	         partner.setPartnerId(1);
	         partner.setDepartmentLotId(22);
	         
	         partnerDepartmentInfo = partnerLogic.loadDepartmentInfo(partner);	         
	         
        	 System.out.println(">> notes: "+ partnerDepartmentInfo.getNotes()
        			 + " markup: " + partnerDepartmentInfo.getMarkup()
        			 + " margin: " + partnerDepartmentInfo.getMargin()
        			 + " employee : " + partnerDepartmentInfo.getEmployeeRep()
        			 );
	            
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadCallHistory() throws Exception {
		 try  {
	         assertNotNull(partnerLogic);
	         
	         List<CallHistory> callsHistory = null;
	         Partner partner = new Partner();
	         partner.setPartnerId(1);
	         
	         callsHistory = partnerLogic.loadCallHistory(partner);
	         
	         for(CallHistory _tmpCallHistory : callsHistory){
	        	 System.out.println(">> contact: "+_tmpCallHistory.getContact().getName()
	        			 + " date: " + _tmpCallHistory.getEnteredDate()
	        			 + " Employee: " + _tmpCallHistory.getEmployeeCreator().getLogin()
	        			 + " Employee: " + _tmpCallHistory.getEmployeeCreator().getFirstName()
	        			 + " Employee: " + _tmpCallHistory.getEmployeeCreator().getLastName()
	        			 + " note: " + _tmpCallHistory.getNotes()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadPartnerByCode() throws Exception {
		try {
			assertNotNull(partnerLogic);
			Partner partner = new Partner();
			partner.setCode("lot");
			Partner rPartner = null;
			rPartner = partnerLogic.searchByCode(partner);

			// Asserts generales para la lista
			assertTrue(rPartner != null);
			
			System.out.println(rPartner.getName()
					+ " -- "
					+ rPartner.getCode()
					+ " -- "
					+ rPartner.getPhone().getPhoneNumber()
					+ " -- "
					+ rPartner.getFax().getPhoneNumber()
					+ " -- "
					+ rPartner.getAddress().getCity().getName()
					+ " -- "
					+ rPartner.getAddress().getCity().getStateProvince()
							.getValue1()
					+ " -- "
					+ rPartner.getAddress().getCity().getCountry()
							.getValue1() + " -- "
					+ rPartner.getAddress().getAddress());
		

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	

}