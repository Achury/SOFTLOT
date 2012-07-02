package test.BussinessPartners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import com.lotrading.softlot.businessPartners.dao.ICallHistoryDao;
import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.security.entity.Employee;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

public class CallHistoryTestNG extends PruebaUnitaria {

	ICallHistoryDao callHistoryDao;

	public CallHistoryTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			callHistoryDao = (ICallHistoryDao) ConfiguracionPrueba
					.obtenerBean("callHistoryDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createCarrier() throws Exception {
		try {
			assertNotNull(callHistoryDao);

			CallHistory callHistory = new CallHistory();
			callHistory.setContact(new PartnerContact());
			callHistory.setEmployeeCreator(new Employee(1));
			callHistory.setEnteredDate(new Date(122));
			callHistory.setNotes("lotrading systems department");
			callHistory.setPartner(new Partner(1));

			int _tmpReturn = callHistoryDao.createCallHistory(callHistory);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateEmployee() throws Exception {
		try {
			assertNotNull(callHistoryDao);

			CallHistory callHistory = new CallHistory();

			callHistory.setCallId(1);
			callHistory.setContact(new PartnerContact());
			callHistory.setEmployeeCreator(new Employee(2));
			callHistory.setEnteredDate(new Date(122));
			callHistory
					.setNotes("EStas son las notas que uno pone en el campo de notas\n Otra linea de notas!!!");
			callHistory.setPartner(new Partner(1));

			boolean _tmpReturn = callHistoryDao.updateCallHistory(callHistory);
			System.out.println(_tmpReturn);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchEmployees() throws Exception {
		try {
			assertNotNull(callHistoryDao);

			CallHistory callHistory = new CallHistory();
			callHistory.setNotes("de");

			List<CallHistory> callsHistory = callHistoryDao.filterByNotes(callHistory);
			// Asserts generales para la lista
			assertTrue(callsHistory != null);
			assertTrue(callsHistory.size() > 0);

			for (CallHistory _tmpCallHistory : callsHistory) {

				// Assert a nivel de cada empleado
				System.out.println(_tmpCallHistory.getNotes()+" -- "+_tmpCallHistory.getCallId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}
