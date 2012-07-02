package test.LODepartment.shipments;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;
import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.LODepartment.shipments.entity.Bl;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.entity.BlFreightInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.BlOtherInvoice;
import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlLogic;


public class BlTestNG extends PruebaUnitaria {

	private IBlLogic blLogic;

	public BlTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			blLogic = (IBlLogic) ConfiguracionPrueba
					.obtenerBean("blLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createBl() throws Exception {
		try {
			assertNotNull(blLogic);

			Bl bl = new Bl();
			
			bl.getRegion().setValueId(400);
			bl.setBlNumber("O-999");
			bl.setCompleted(true);
			bl.setCreatedDate(new Date(1000));				
			bl.getBlType().setValueId(420);
			bl.getBlShippingType().setValueId(485);
			bl.getSupplier().setPartnerId(10341);
			bl.getPickupFrom().setShipPickUpId(14259);	
			bl.getClient().setPartnerId(6103);			
			bl.getShipTo().setShipPickUpId(7226);			
			bl.getSalesRep().setEmployeeId(64);
			bl.setClientPoNumber("clientePo1");
			bl.getCarrier().setCarrierId(1);
			bl.setNotifyParty("notoficar a todos");
			bl.getPlaceOfReceipt().setCityId(5);
			bl.getPortOrigin().setPortId(1);
			bl.getPortDestination().setPortId(7);
			bl.setBooking("booking");
			bl.setFullBlNumber("f-O999");
			bl.setRemarks("Remarks");
			bl.setShipmentNotif(true);
			bl.setArrivalNotif(true);
			bl.setDocsDelNotif(true);
			bl.setShipDelNotif(true);
			bl.setBlMasterId(2);				
			bl.setBlMasterNumber("O-10000");
			
			/*bl.setFreightLCLCost(10);
			bl.setFreightFCLCost(20);
			bl.setOceanCost(3.2);
			bl.setBunkerCost(3.8);
			bl.setLocalCost(10.20);
			bl.setInlandFreightCost(2.3);
			bl.setBlCost(62);
			bl.setUnCost(65);
			bl.setOtherCost(80);
			bl.setSecurityCost(20);
			bl.setChassisCost(1.2);
			bl.setTerminalCost(5.3);
			bl.setDryageCost(2);
			bl.setLoadingCost(0.23);
			bl.setTotalCost(0.020);
			bl.setOceanFreightSale(75);
			bl.setHandlingSale(85);
			bl.setLoadingSale(10);
			bl.setDocumentationSale(20);
			bl.setInlandfreightSale(0.98);
			bl.setOtherSale(2.30);
			bl.setTotalSale(800);
			bl.setMargin(10.10);
			bl.setVessel_voyage("vessel voy");
			bl.setIfReference("IF REFERENCE");
			bl.setPlaceOfDelivery("LA MOJICA");
			//bl.setPierOfOrigin(20);
			bl.setAdditionalNumbers("NUMBER ADDITIONAL");
			bl.setExportInstructions("EXPORTAR ASI");
			bl.setEtd( new Date(1000));
			bl.setEta(new Date(1000));
			bl.setLockCosts(true);

			int _tmpReturn = blLogic.saveBl(bl);
			System.out.println(_tmpReturn);	
			assertTrue(_tmpReturn > 0);*/
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	
	@Test(groups = { "grupo-actualizar" })
	public void updateBl() throws Exception {
		try {
			assertNotNull(blLogic);

			Bl bl = new Bl();
			bl.setBlId(4893);
			bl.getRegion().setValueId(400);
			bl.setBlNumber("O-9991");
			bl.setCompleted(true);
			bl.setCreatedDate(new Date(1000));				
			bl.getBlType().setValueId(420);
			bl.getBlShippingType().setValueId(485);
			bl.getSupplier().setPartnerId(10341);
			bl.getPickupFrom().setShipPickUpId(14259);	
			bl.getClient().setPartnerId(6103);			
			bl.getShipTo().setShipPickUpId(7226);			
			bl.getSalesRep().setEmployeeId(64);
			bl.setClientPoNumber("clientePo11");
			bl.getCarrier().setCarrierId(1);
			bl.setNotifyParty("notoficar a todos1");
			bl.getPlaceOfReceipt().setCityId(5);
			bl.getPortOrigin().setPortId(1);
			bl.getPortDestination().setPortId(7);
			bl.setBooking("booking1");
			bl.setFullBlNumber("f-O9991");
			bl.setRemarks("Remarks1");
			bl.setShipmentNotif(true);
			bl.setArrivalNotif(true);
			bl.setDocsDelNotif(true);
			bl.setShipDelNotif(true);
			bl.setBlMasterId(2);				
			bl.setBlMasterNumber("O-100001");
			
			/*bl.setFreightLCLCost(10);
			bl.setFreightFCLCost(20);
			bl.setOceanCost(3.2);
			bl.setBunkerCost(3.8);
			bl.setLocalCost(10.20);
			bl.setInlandFreightCost(2.3);
			bl.setBlCost(62);
			bl.setUnCost(65);
			bl.setOtherCost(80);
			bl.setSecurityCost(20);
			bl.setChassisCost(1.2);
			bl.setTerminalCost(5.3);
			bl.setDryageCost(2);
			bl.setLoadingCost(0.23);
			bl.setTotalCost(0.020);
			bl.setOceanFreightSale(75);
			bl.setHandlingSale(85);
			bl.setLoadingSale(10);
			bl.setDocumentationSale(20);
			bl.setInlandfreightSale(0.98);
			bl.setOtherSale(2.30);
			bl.setTotalSale(800);
			bl.setMargin(10.10);
			bl.setVessel_voyage("vessel voy1");
			bl.setIfReference("IF REFERENCE1");
			bl.setPlaceOfDelivery("LA MOJICA1");
			//bl.setPierOfOrigin(20);
			bl.setAdditionalNumbers("NUMBER ADDITIONAL1");
			bl.setExportInstructions("EXPORTAR ASI1");
			bl.setEtd( new Date(1000));
			bl.setEta(new Date(1000));
			bl.setLockCosts(true);
			*/

			int _tmpReturn = blLogic.saveBl(bl);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn >0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	

	@Test(groups = { "grupo-obtener" })
	public void searchBls() throws Exception {
		try {
			assertNotNull(blLogic);

			Bl bl = new Bl();

			bl.setBlNumber("O-9991");

			
			List<Bl> bls = null;			
			bls = blLogic.searchBl(bl);

			// Asserts generales para la lista			
			assertTrue(bls != null);
			assertTrue(bls.size() > 0);

			for (Bl _tmpBl : bls) {
				System.out.println(_tmpBl.getBlId()
						+ " -- "
						+ _tmpBl.getBlNumber()
						+ " -- "
						+ _tmpBl.getPortOrigin().getName()
						+ " -- "
						+ _tmpBl.getRegion().getValueId()
						+ " -- "
						+ _tmpBl.getSalesRep().getLogin());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadBl() throws Exception {
		try {
			assertNotNull(blLogic);
			Bl bl = new Bl();
			bl.setBlId(4887);
			Bl rBl = null;
			rBl = blLogic.loadBl(bl);

			// Asserts generales para la lista
			assertTrue(rBl != null);
			
			System.out.println(rBl.getBlId()
					+ " -- "
					+ rBl.getBlNumber()
					+ " -- "
					+ rBl.getPortOrigin().getPortId()
					+ " -- "
					+ rBl.getRegion().getValueId()
					+ " -- "
					+ rBl.getSalesRep().getEmployeeId());
		

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
	
	

	@Test(groups = { "grupo-obtener" })
	public void loadBlDetails() throws Exception {
		 try  {
	         assertNotNull(blLogic);
	         
	         List<BlItem> blItems = null;
	         Bl bl = new Bl();
	         bl.setBlId(4885);	
	         blItems = blLogic.loadBlItems(bl);
	         for(BlItem _tmpBlItem : blItems){
	        	 /*System.out.println(">> id: "+_tmpBlItem.getDetailId()
	        			 + " ponum: " + _tmpBlItem.getPoNumber()
	        			 + " height: " + _tmpBlItem.getItemHeight()
	        			 + " remarks: " + _tmpBlItem.getRemarks()
	        			 );*/
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadFreightInvoices() throws Exception {
		 try  {
	         assertNotNull(blLogic);
	         
	         List<BlFreightInvoice> blFreightInvoices = null;
	         Bl bl = new Bl();
	         bl.setBlId(4885);
	         blFreightInvoices = blLogic.loadFreightInvoices(bl);
	         for(BlFreightInvoice _tmpBlFreightInvoice : blFreightInvoices){
	        	 System.out.println(">> id: "+_tmpBlFreightInvoice.getFreightInvoiceId()
	        			 + " invoice id: " + _tmpBlFreightInvoice.getInvoiceId()
	        			 + " date: " + _tmpBlFreightInvoice.getCreatedDate()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadOtherInvoices() throws Exception {
		 try  {
	         assertNotNull(blLogic);
	         
	         List<BlOtherInvoice> blOtherInvoices = null;
	         Bl bl = new Bl();
	         bl.setBlId(3198);
	         blOtherInvoices = blLogic.loadOtherInvoices(bl);
	         for(BlOtherInvoice _tmpBlOtherInvoice : blOtherInvoices){
	        	 System.out.println(">> id: "+_tmpBlOtherInvoice.getOtherInvoiceId()
	        			 + " invoice id: " + _tmpBlOtherInvoice.getInvoiceId()
	        			 + " date: " + _tmpBlOtherInvoice.getCreatedDate()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
		
	@Test(groups = { "grupo-obtener" })
	public void loadInlandsCS() throws Exception {
		 try  {
	         assertNotNull(blLogic);
	         
	         List<BlInlandCS> blInlandCSs = null;
	         Bl bl = new Bl();
	         bl.setBlId(3261);
	         blInlandCSs = blLogic.loadInlandCS(bl);
	         for(BlInlandCS _tmpBlInlandCS : blInlandCSs){
	        	 System.out.println(">> id: "+_tmpBlInlandCS.getInlandCSId()
	        			 + " cost: " + _tmpBlInlandCS.getCost()
	        			 + " tracking: " + _tmpBlInlandCS.getTrackingNumber()
	        			 + " amount: " + _tmpBlInlandCS.getPoNumber()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}
	
	@Test(groups = { "grupo-obtener" })
	public void loadUnCodes() throws Exception {
		 try  {
	         assertNotNull(blLogic);
	         
	         List<BlUnCode> blUnCodes = null;
	         Bl bl = new Bl();
	         bl.setBlId(3261);
	         blUnCodes = blLogic.loadUnCodes(bl);
	         for(BlUnCode _tmpBlUnCode : blUnCodes){
	        	 System.out.println(">> id: "+_tmpBlUnCode.getUnCodeId()
	        			 + " group: " + _tmpBlUnCode.getPackingGroup()
	        			 + " class: " + _tmpBlUnCode.getUnClass()
	        			 + " code: " + _tmpBlUnCode.getUnCode()
	        			 );
	         }         
	      } catch (Exception ex)  {
	         ex.printStackTrace();
	         assertNull("Se produjo un error:"+ex.getMessage(),ex);
	      } 	   
	}

}