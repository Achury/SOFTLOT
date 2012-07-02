package test.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.security.dao.IResourceDao;
import com.lotrading.softlot.security.dao.IRoleDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IRoleLogic;

public class RoleTestNG extends PruebaUnitaria {

	private IRoleDao roleDao;

	public RoleTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			roleDao = (IRoleDao) ConfiguracionPrueba.obtenerBean("roleDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createRole() throws Exception {
		try {
			assertNotNull(roleDao);

			Role role = new Role();
			role.setDescription("Ejecutivo Accounting");
			role.setEnteredDate(new Date(1000));
			role.setName("Accounting Executive");

			int _tmpReturn = roleDao.createRole(role);
			if(_tmpReturn > 0){
				System.out.println("Result >> " + "true");
				assertTrue(true);
			}else{
				System.out.println("Result >> " + "false");
				assertTrue(false);
			}		
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	//@Test(groups = { "grupo-actualizar" })
	public void updateRole() throws Exception {
		try {
			assertNotNull(roleDao);

			Role role = new Role();
			role.setRoleId(4);
			role.setEnteredDate(new Date(999999));
			role.setDescription("RoleTEST");
			role.setName("ROlETest");

			int _tmpReturn = roleDao.updateRole(role);
			if(_tmpReturn > 0){
				System.out.println("Result >> " + "true");
				assertTrue(true);
			}else{
				System.out.println("Result >> " + "false");
				assertTrue(false);
			}		
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchRole() throws Exception {
		try {
			assertNotNull(roleDao);

			Role role = new Role();
			role.setName("e");

			List<Role> roles = null;

			roles = roleDao.searchRole(role);

			// Asserts generales para la lista
			assertTrue(roles != null);
			assertTrue(roles.size() > 0);

			for (Role elemento : roles) {

				// Assert a nivel de cada empleado
				assertTrue(elemento.getDescription() != null);

				System.out.println(elemento.getName() + " "
						+ elemento.getDescription() + "  "
						+ elemento.getEnteredDate().getTime());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void loadRoleOptions() throws Exception {
		try {
			assertNotNull(roleDao);

			List<Option> options = null;
			Role role = new Role();
			role.setRoleId(1);
			options = roleDao.loadRoleOptions(false, role);

			for (Option _tmpOption : options) {
				System.out.println(">> " + _tmpOption.getName() + "---"
						+ _tmpOption.getDescription());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void loadRoleResources() throws Exception {
		try {
			assertNotNull(roleDao);

			List<Resource> resources = null;
			Role role = new Role();
			role.setRoleId(1);
			resources = roleDao.loadRoleResources(false, role);

			for (Resource _tmpResource : resources) {
				System.out.println(">> " + _tmpResource.getDescription()
						+ "---" + _tmpResource.getAction());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	//@Test(groups = { "grupo-actualizar" })
	public void updateRoleOptions() throws Exception {
		try {
			assertNotNull(roleDao);

			Role role = new Role();
			role.setRoleId(1);
			List<Option> options = new ArrayList<Option>();
			options.add(new Option(5));
			role.setOptions(options);

			boolean _tmpReturn = roleDao.updateRoleOptions(role);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateRoleResources() throws Exception {
		try {
			assertNotNull(roleDao);

			Role role = new Role();
			role.setRoleId(1);
			List<Resource> resources = new ArrayList<Resource>();
			resources.add(new Resource(1));
			resources.add(new Resource(2));
			resources.add(new Resource(3));
			resources.add(new Resource(4));
			role.setResources(resources);
			// TODO: prueba fallida - tener en cuenta tabla action_role.
			boolean _tmpReturn = roleDao.updateRoleResources(role);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}