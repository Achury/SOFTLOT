package test.security;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.security.control.OptionControl;
import com.lotrading.softlot.security.dao.IOptionsDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.logic.IOptionLogic;

public class OptionTestNG extends PruebaUnitaria {

	private IOptionLogic optionLogic;

	public OptionTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			optionLogic = (IOptionLogic) ConfiguracionPrueba
					.obtenerBean("optionLogic");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchOptions() throws Exception {
		try {
			assertNotNull(optionLogic);

			Option option = new Option();
			//option.setName("na");

			List<Option> options = null;
			options = optionLogic.searchOption(option);

			assertTrue(options != null);
			assertTrue(options.size() > 0);
			for (Option _tmpOption : options) {
				System.out.println(_tmpOption.getOptionId() + "--"+_tmpOption.getName() + "--"
						+ _tmpOption.getDescription());
				// assertNotNull(_tmpOption);
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertNull("Se produjo un error:" + e.getMessage(), e);
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createOption() throws Exception {
		try {
			assertNotNull(optionLogic);

			Option option = new Option();
			option.setName("name4");
			option.setDescription("Description4");
			option.setResource(new Resource(5));
			option.setParent(false);
			option.setOrder(3);

			boolean _tmpReturn = optionLogic.saveOption(option);
			System.out.println(_tmpReturn);
			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateOption() throws Exception {
		try {
			assertNotNull(optionLogic);

			Option option = new Option();
			option.setName("na");
			
			List<Option> options = null;
			options = optionLogic.searchOption(option);
			assertNotNull(options);
			option = (Option) options.get(0);
			System.out.println(option.getOptionId()+" -- "+ option.getName());
			
			option.setDescription("description actualizada");		
			option.setParent(false);
			option.setOrder(4);
			option.setEnteredDate(new Date(1000));

			boolean _tmpReturn = optionLogic.saveOption(option);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}